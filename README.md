Sobre o projeto

SmartCuisine é o aplicativo mobile (Android) do sistema SmartCuisine, desenvolvido em Kotlin com Jetpack Compose. O app permite que administradores, gerentes e cozinheiros façam login, gerenciem usuários e acompanhem, em tempo real, indicadores e a temperatura de equipamentos da cozinha (como fornos) através de um sensor conectado via ESP32, que publica dados em um broker MQTT.

O aplicativo consome uma API REST (backend já hospedado na nuvem) para autenticação e cadastro de usuários, e se conecta diretamente a um broker MQTT local para receber a telemetria dos equipamentos.

Projeto de namespace com.senai.abcgjl_smartcuisine_mobile, desenvolvido em contexto acadêmico (SENAI).

Tecnologias utilizadas

Categoria

Tecnologia

Versão

Linguagem

Kotlin

2.0.20

UI

Jetpack Compose (BOM)

2025.02.00

UI

Material 3

1.4.0

Build

Android Gradle Plugin (AGP)

8.7.0

Build

Gradle (wrapper)

9.1.0

Injeção de dependência

Hilt

2.56.2

Rede / HTTP

Retrofit

2.11.0

Rede / HTTP

OkHttp + Logging Interceptor

4.12.0

Serialização

Gson (via converter-gson)

—

Assincronismo

Kotlin Coroutines + Flow

1.7.3

Navegação

Navigation Compose

2.8.9

Persistência local

DataStore Preferences

1.1.2

Imagens

Coil Compose

2.7.0

QR Code

ZXing core

3.5.3

IoT / Mensageria

Eclipse Paho MQTT Client (org.eclipse.paho.client.mqttv3)

1.2.5

Testes

JUnit / Espresso / Compose UI Test

4.13.2 / 3.6.1 / BOM

Configurações do módulo app: compileSdk e targetSdk 36, minSdk 24, compatibilidade Java 11, build feature compose = true. Todas as versões de bibliotecas são centralizadas no catálogo gradle/libs.versions.toml.

Estrutura do projeto

O código segue uma organização por camadas e por funcionalidade (feature-based), aproximando-se de Clean Architecture:

app/src/main/java/com/senai/abcgjl_smartcuisine_mobile/
├── app/                         # Composição raiz do app
│   ├── App.kt                   # Composable raiz (tema + NavHost)
│   ├── MainActivity.kt          # Activity única (entry point)
│   ├── SmartCuisineApplication.kt  # Application class (@HiltAndroidApp)
│   ├── mqtt/                    # Cliente MQTT, módulo Hilt, repositório e ViewModel de sensores
│   ├── navigation/               # Rotas (AppDestinations) e grafo de navegação (AppNavHost)
│   └── shell/                   # TopBar, Drawer e "shells" de tela autenticada/não autenticada
│
├── core/                        # Código compartilhado entre features
│   ├── common/                  # Parsing de erros de API, UiState genérico
│   ├── datastore/                # Preferências persistidas (sessão e tema) via DataStore
│   ├── designsystem/             # Tema, cores, tipografia e componentes de UI reutilizáveis
│   ├── model/                    # Modelos de domínio (UserRole, SessionUser, ApprovalStatus, ThemeMode)
│   ├── network/api/               # ApiService, Retrofit, interceptor de autenticação
│   └── session/                  # SessionManager / SessionViewModel (estado de login)
│
├── di/                           # Módulos Hilt (AppModule, NetworkModule, StorageModule)
│
└── feature/                      # Funcionalidades isoladas por contexto de negócio
├── auth/                     # Login, cadastro (admin), Home administrativa, listagem de usuários
├── cuisine/                  # Componentes de exibição de dados da cozinha
└── signup/                   # Fluxo de cadastro de novo usuário (data/domain/presentation)

Cada feature segue, internamente, a divisão data → domain → presentation (repositórios, casos de uso/UseCases, ViewModels e telas Compose), o que facilita testes e manutenção isolada de cada parte do app.

Pré-requisitos

Antes de começar, tenha instalado:

Android Studio (versão recente, compatível com AGP 8.7 e Gradle 9.1 — recomendado Ladybug ou superior)

JDK 11 (normalmente já incluso na instalação do Android Studio)

Emulador Android configurado (API 24+) ou um dispositivo físico com Android 7.0 ou superior, em modo de depuração USB habilitado

Conexão com a internet, para sincronizar as dependências do Gradle e para o app consumir a API REST

(Opcional, apenas para testar o monitoramento de sensores) um broker MQTT acessível na mesma rede local e um dispositivo ESP32 publicando os dados de telemetria do forno

Instalação e configuração

1. Clonar o repositório

bash

git clone <url-do-repositorio>
cd SmartCuisineApp-master

2. Abrir o projeto

Abra a pasta raiz do projeto no Android Studio através de File > Open. O Android Studio detectará automaticamente o arquivo settings.gradle.kts e configurará o projeto.

3. Instalação das dependências

Este é um projeto Gradle, portanto não há um passo manual de "instalar dependências" como em projetos Node/NPM: todas as bibliotecas declaradas em app/build.gradle.kts e centralizadas em gradle/libs.versions.toml são baixadas automaticamente durante a sincronização do Gradle.

No Android Studio, isso ocorre ao abrir o projeto (clique em "Sync Now" caso a sincronização não inicie automaticamente).

Pelo terminal, a sincronização e o download das dependências também ocorrem ao rodar qualquer task do Gradle wrapper:

bash

# Linux/macOS
./gradlew build

# Windows
gradlew.bat build

O próprio wrapper (gradlew/gradlew.bat) baixa a versão correta do Gradle (9.1.0) automaticamente — não é necessário instalar o Gradle manualmente.

Variáveis de ambiente e configurações

Por se tratar de um aplicativo Android nativo, o projeto não utiliza arquivos .env. As configurações de ambiente ficam definidas como constantes diretamente no código-fonte Kotlin. Para alterar o ambiente (por exemplo, apontar para outro backend ou outro broker), edite os arquivos abaixo:

Configuração

Arquivo

Valor padrão

Descrição

URL base da API

core/network/api/ApiConfig.kt

https://abcgjl-smartcusine-backend-api.onrender.com/

Endpoint base usado pelo Retrofit injetado via Hilt (NetworkModule)

Endereço do broker MQTT

app/mqtt/MqttClientManager.kt (serverUri)

tcp://172.28.2.234:1883

Endereço da rede local onde está o broker MQTT/ESP32. Precisa ser atualizado para o IP do broker da sua rede antes de testar o monitoramento de sensores

Tópico MQTT assinado

app/mqtt/MqttClientManager.kt / feature/auth/data/repository/SensorRepository.kt

cozinha/telemetria

Tópico onde o ESP32 publica a telemetria do forno (forno_temp, forno_status)

Observações importantes:

Existem dois outros objetos (RetrofitClient.kt e RetrofitInstance.kt) que também declaram a mesma BASE_URL de forma independente, usados por fluxos específicos do app. Caso troque o endereço do backend, atualize os três arquivos para manter a consistência.

O arquivo AndroidManifest.xml declara android:usesCleartextTraffic="true", e res/xml/network_security_config.xml permite tráfego não criptografado (cleartextTrafficPermitted="true"). Isso é necessário porque a conexão MQTT ocorre via TCP simples na rede local — a comunicação com a API REST já utiliza HTTPS.

Não há chaves de API, segredos ou tokens fixos no código: o token de autenticação é obtido dinamicamente no login e persistido localmente via DataStore (ver seção Integração com a API REST).


Execução do projeto

Pelo Android Studio

Conecte um dispositivo físico (com depuração USB habilitada) ou inicie um emulador.

Selecione o dispositivo no seletor de execução.

Clique em Run ▶ (ou Shift + F10).

Pela linha de comando

bash

# Gerar o APK de debug
./gradlew assembleDebug

# Instalar diretamente em um dispositivo/emulador conectado
./gradlew installDebug

O APK gerado fica disponível em:

app/build/outputs/apk/debug/app-debug.apk

Para instalar manualmente um APK já gerado:

bash

adb install app/build/outputs/apk/debug/app-debug.apk


Integração com a API REST

O app consome um backend REST (já implantado em produção na URL configurada em ApiConfig.BASE_URL) através de Retrofit + Gson, com OkHttp como cliente HTTP.

Principais endpoints consumidos

Método

Rota

Uso no app

Arquivo

POST

auth/login

Autenticação (e-mail e senha → token + dados do usuário)

AuthApiService.kt / ApiService.kt

POST

usuarios

Cadastro de novo usuário (fluxo de signup)

SignupApiService.kt

POST

usuarios/cadastro

Cadastro de usuário (fluxo administrativo)

ApiService.kt

GET

usuarios

Listagem de usuários cadastrados

ApiService.kt

Autenticação e sessão

Após o login, o token retornado pela API é mantido em memória pelo AuthTokenProvider e persistido localmente via SessionPreferenceStore (DataStore Preferences), permitindo manter o usuário autenticado entre execuções do app.

O AuthInterceptor (OkHttp) injeta automaticamente o cabeçalho Authorization: Bearer {token} em toda requisição, exceto nas rotas públicas (/login, /register, /signup, /usuarios).

O SessionManager/SessionViewModel expõe o estado da sessão (Loading, Authenticated, Unauthenticated), que controla a navegação inicial do app (AppNavHost) — redirecionando automaticamente para a tela de login quando a sessão expira ou é encerrada (logout()).

Erros retornados pela API são tratados pelo ApiExceptionParser, que tenta extrair uma mensagem amigável dos campos message/mensagem/detail/erro ou errors/erros do corpo de erro, com fallback para uma mensagem padrão.


Integração com sensores (IoT/MQTT)

Além da API REST, o app possui uma camada independente de comunicação em tempo real com equipamentos de cozinha via MQTT, usando o cliente Eclipse Paho:

MqttClientManager conecta-se a um broker MQTT (tcp://<ip-do-broker>:1883) em uma thread separada e se inscreve no tópico cozinha/telemetria.

Um dispositivo ESP32 publica periodicamente nesse tópico um payload JSON contendo forno_temp (temperatura) e forno_status ("OK" ou status de alerta).

SensorRepository interpreta essas mensagens e as expõe como um StateFlow de SensorData, consumido pelo SensorViewModel.

A tela administrativa (HomeAdminScreen) exibe esses dados em tempo real, sinalizando visualmente quando o equipamento está em condição normal ou em superaquecimento.

Para testar essa funcionalidade, é necessário ter um broker MQTT ativo na mesma rede local do dispositivo Android e atualizar o IP em MqttClientManager.kt conforme descrito na seção Variáveis de ambiente.


Funcionalidades principais

Login com opção de acesso como convidado e recuperação de senha (Esqueci minha senha).

Cadastro de usuário, com seleção de perfil: ADMIN, GERENTE ou COZINHEIRO.

Fluxo de aprovação de cadastro (ApprovalStatus: Pendente, Aprovado, Rejeitado), controlado junto ao backend.

Dashboard administrativo com indicadores resumidos (estoque, pendências, etc.) e monitoramento de equipamentos em tempo real.

Listagem de usuários cadastrados no sistema.

Tema claro/escuro persistido localmente (ThemePreferenceStore).

Sessão persistente: o usuário permanece autenticado entre aberturas do app até realizar logout.


Permissões do Android

Declaradas em AndroidManifest.xml:

android.permission.INTERNET — necessária para chamadas à API REST e conexão MQTT.

android.permission.ACCESS_NETWORK_STATE — usada para verificar o estado da conectividade de rede.


Testes

O projeto inclui a estrutura padrão de testes do Android:

bash

# Testes unitários (JVM)
./gradlew test

# Testes instrumentados (necessitam de emulador/dispositivo conectado)
./gradlew connectedAndroidTest

Os testes de exemplo estão em app/src/test (unitários) e app/src/androidTest (instrumentados), prontos para serem expandidos conforme novas funcionalidades forem implementadas.


Licença

Projeto desenvolvido para fins acadêmicos.