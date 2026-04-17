# CapabilitiesShortcuts

Este repositorio es una implementación de referencia para la integración de **Android Shortcuts** y la **Capabilities API**, permitiendo que las funcionalidades de una aplicación sean accesibles a través de **Google Assistant** mediante **App Actions**.

El proyecto demuestra cómo exponer puntos de entrada específicos de la app al ecosistema de Google, mejorando la accesibilidad y la experiencia de usuario mediante comandos de voz e intents del sistema.


## Características principales

- **Shortcuts Estáticos:** Implementación de accesos directos predefinidos mediante `shortcuts.xml`.
- **Shortcuts Dinámicos:** Gestión de atajos en tiempo de ejecución utilizando `ShortcutManagerCompat` para adaptarse al contexto del usuario.
- **Capabilities API:** Configuración de BIIs (Built-in Intents) para vincular comandos de voz de Google Assistant con actividades específicas dentro de la aplicación.
- **Kotlin Idiomático:** Uso de las últimas prácticas de desarrollo en Android, asegurando un código limpio y escalable.

## Requisitos previos

- **Android Studio:** Giraffe (2022.3.1) o superior.
- **Google Play Services:** El dispositivo de prueba debe tener una cuenta de Google activa.
- **App Actions Test Tool:** Plugin de Android Studio para la validación de intents.

## Estructura del Proyecto

- `res/xml/shortcuts.xml`: Definición de las capacidades y los atajos estáticos.
- `MainActivity.kt`: Lógica para la gestión de shortcuts dinámicos y recepción de intents de Assistant.
- `AndroidManifest.xml`: Configuración del meta-data necesario para que el sistema reconozca el archivo de shortcuts.

## Configuración y Pruebas

Para probar la integración con Google Assistant:

1. Abre el proyecto en Android Studio.
2. Instala el plugin **Google Assistant** desde el Marketplace.
3. Dirígete a `Tools` > `Google Assistant` > `App Actions Test Tool`.
4. Crea un Preview de las acciones.
5. Invoca la acción mediante comandos de voz o directamente desde la herramienta de test.

## Stack Tecnológico

- **Lenguaje:** Kotlin
- **Mínimo SDK:** 21 (Lollipop)
- **Bibliotecas:** - `androidx.core:core-google-shortcuts`
  - `androidx.core:core-ktx`

## Contribuciones

Si deseas mejorar este ejemplo o añadir nuevos casos de uso para BIIs, los Pull Requests son bienvenidos.

---
## 👤 Autor

[![GitHub](https://img.shields.io/badge/GitHub-tohure-181717?logo=github&logoColor=white)](https://github.com/tohure)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Carlo%20Huaman-0A66C2?logo=linkedin&logoColor=white)](https://linkedin.com/in/tohure)
[![X](https://img.shields.io/badge/X-@tohure__-000000?logo=x&logoColor=white)](https://x.com/tohure_)
