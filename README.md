# Conversor Android

Aplicación Android nativa escrita en Java para convertir unidades de longitud, masa,
temperatura y volumen.

## Características

- Cuatro categorías y dieciséis unidades disponibles.
- Conversión en ambos sentidos e intercambio rápido de unidades.
- Admite separador decimal con punto o coma.
- Interfaz adaptable y accesible, sin conexión a Internet.
- Motor de conversión desacoplado y cubierto por pruebas unitarias.

## Requisitos

- Android Studio con JDK 17 o superior.
- Android SDK 37.

## Ejecutar el proyecto

1. Abre esta carpeta desde Android Studio.
2. Espera a que finalice la sincronización de Gradle.
3. Selecciona un emulador o dispositivo con Android 6.0 (API 23) o superior.
4. Ejecuta la configuración `app`.

Desde la terminal también puedes compilar y probar el proyecto:

```powershell
.\gradlew.bat test assembleDebug
```
