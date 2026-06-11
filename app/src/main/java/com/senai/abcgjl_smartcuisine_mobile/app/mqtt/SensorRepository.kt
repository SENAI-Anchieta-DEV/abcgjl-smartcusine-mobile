package com.senai.abcgjl_smartcuisine_mobile.feature.auth.data.repository

import com.senai.abcgjl_smartcuisine_mobile.app.mqtt.MqttClientManager
import com.senai.abcgjl_smartcuisine_mobile.feature.auth.presentation.viewmodel.SensorData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SensorRepository @Inject constructor(
    private val mqtt: MqttClientManager
) {
    private val _sensores = MutableStateFlow<List<SensorData>>(emptyList())
    val sensores = _sensores.asStateFlow()

    fun iniciar() {
        mqtt.connect { messageJson ->
            try {
                val json = JSONObject(messageJson)

                // Mapeando as chaves exatas do JSON do ESP32
                val tempVal = json.getDouble("forno_temp")
                val statusOriginal = json.getString("forno_status")

                // Formatando o status para ficar amigável na tela do app
                val statusFormatado = if (statusOriginal == "OK") "Normal" else "Superaquecimento!"
                val isAlerta = statusOriginal != "OK"

                _sensores.value = listOf(
                    SensorData(
                        nomeEquipamento = "Forno 1",
                        temperaturaAtual = String.format("%.1f°C", tempVal),
                        status = statusFormatado,
                        faixaIdeal = "Ideal: Até 180°C",
                        isAlerta = isAlerta
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // Assina o tópico exato do ESP32
        mqtt.subscribe("cozinha/telemetria")
    }
}