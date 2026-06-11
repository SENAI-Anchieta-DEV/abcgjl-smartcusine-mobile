package com.senai.abcgjl_smartcuisine_mobile.app.mqtt

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.concurrent.thread

@Singleton
class MqttClientManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    // Usamos o MqttClient puro do Java com persistência em memória (sem depender do Android Service)
    private var client: MqttClient? = null

    fun connect(onMessage: (String) -> Unit) {
        // Rodamos em uma linha de execução separada (Thread) para o Android não reclamar de travar a tela
        thread {
            try {
                val serverUri = "tcp://172.28.2.234:1883"
                val clientId = "androidClient_" + System.currentTimeMillis()

                client = MqttClient(serverUri, clientId, MemoryPersistence())

                val options = MqttConnectOptions().apply {
                    isAutomaticReconnect = true
                    isCleanSession = true
                    connectionTimeout = 10
                }

                client?.setCallback(object : MqttCallback {
                    override fun messageArrived(topic: String?, message: MqttMessage?) {
                        message?.let {
                            // Devolve o JSON para o Repositório
                            onMessage(it.toString())
                        }
                    }

                    override fun connectionLost(cause: Throwable?) {
                        println("MQTT: Conexão perdida! Tentando reconectar...")
                    }

                    override fun deliveryComplete(token: IMqttDeliveryToken?) {}
                })

                println("MQTT: Tentando conectar em $serverUri...")
                client?.connect(options)
                println("MQTT: Conectado com sucesso!")

                // Se você já quiser assinar o tópico assim que conectar:
                client?.subscribe("cozinha/telemetria", 0)
                println("MQTT: Inscrito no tópico cozinha/telemetria")

            } catch (e: Exception) {
                println("MQTT ERRO: Falha ao conectar no Broker!")
                e.printStackTrace()
            }
        }
    }

    fun subscribe(topic: String) {
        // Como já nos inscrevemos no connect para garantir,
        // essa função pode apenas reforçar se o cliente estiver ativo.
        try {
            if (client?.isConnected == true) {
                client?.subscribe(topic, 0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}