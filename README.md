# PokeApp Android

PokeApp es una aplicación Android que permite a los usuarios explorar y capturar Pokémon. La aplicación proporciona una interfaz amigable para buscar Pokémon, ver detalles y gestionar tu colección.

## Requisitos

Antes de comenzar a desarrollar, asegúrate de tener instalados los siguientes requisitos:

- [Android Studio](https://developer.android.com/studio)
- [Java Development Kit (JDK) 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Git](https://git-scm.com/)

## Dependencias

Este proyecto utiliza las siguientes dependencias:

- [Retrofit](https://square.github.io/retrofit/): Para realizar solicitudes HTTP.
- [Dagger Hilt](https://dagger.dev/hilt/): Para la inyección de dependencias.
- [Room](https://developer.android.com/training/data-storage/room): Para la persistencia de datos.
- [Glide](https://bumptech.github.io/glide/): Para la carga y gestión de imágenes.
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata): Para la gestión de datos observables.

## Cómo empezar

Esta guía paso a paso te indicará cómo poner en funcionamiento el entorno de desarrollo.

```
$ git clone git@github.com:mxcruz/pokapp-android.git
$ cd pokeapp-android
```

Para correr los tests unitarios con Gradle, utiliza el siguiente comando:

```
$ ./gradlew test
```

## Contribuir

¡Nos encantaría tu ayuda para mejorar PokeApp! Para contribuir, sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama para tu funcionalidad (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -m 'Añadir nueva funcionalidad'`).
4. Sube tus cambios a tu fork (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request en GitHub.

Por favor, asegúrate de que tu código sigue las guías de estilo del proyecto y que todas las pruebas pasan antes de enviar tu Pull Request.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.
