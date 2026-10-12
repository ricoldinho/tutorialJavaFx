# 🏢 Sistema de Gestión Empresarial - JavaFX, FXML & JPMS

![Java 21](https://img.shields.io/badge/Java-21%2B-orange.svg)
![JavaFX](https://img.shields.io/badge/JavaFX-21.0.2-blue.svg)
![Maven](https://img.shields.io/badge/Maven-Build-brightgreen.svg)
![JPMS](https://img.shields.io/badge/Architecture-JPMS%20Modular-purple.svg)
![Theme](https://img.shields.io/badge/UI%20Theme-AtlantaFX%20PrimerDark-darkgreen.svg)

Bienvenido a la plantilla arquitectónica de desarrollo de aplicaciones de escritorio empresariales construida sobre **Java 21**, **JavaFX 21+**, **Apache Maven** y el sistema de módulos **JPMS**.

Este repositorio contiene la implementación paso a paso de los **Módulos 1, 2 y 3**, diseñados bajo los principios de **separación estricta de responsabilidades (MVC/MVVM)**, **programación reactiva pura mediante JavaFX Bindings** y un **sistema de navegación desacoplado**.

---

## 📐 Arquitectura y Estructura del Proyecto

El proyecto sigue la convención estándar de construcción de Apache Maven e integra la fuerte encapsulación del **Java Platform Module System (JPMS)** con el paquete fuente principal `edu.rico.tutorial.tutorialclase`.

```text
edu.rico.tutorial.tutorialclase
├── Launcher.java                       <-- Punto de entrada secundario (Classpath Fallback)
├── App.java                            <-- Clase Application JavaFX con ClassLoader seguro
├── controllers/
│   ├── MainLayoutController.java       <-- Control del layout principal y menú
│   └── PerfilController.java           <-- Control reactivo del configurador de avatar
├── navigation/
│   ├── NavigationManager.java          <-- Coordinador de navegación Singleton Reactivo
│   └── ViewType.java                   <-- Enum de rutas FXML y títulos tipados
└── resources/
    └── edu/rico/tutorial/tutorialclase/
        ├── views/
        │   ├── main-layout.fxml        <-- Marco contenedor (BorderPane + MenuBar + ToolBar)
        │   └── perfil-view.fxml        <-- Formulario FXML del configurador de perfil
        └── styles/
            └── theme.css               <-- Hoja de estilos CSS externa personalizada
```

## 🛠️ Resumen de Módulos Implementados

### 🔹 Módulo 1: Cimientos de la Infraestructura & JPMS

1. **Configuración de Apache Maven (pom.xml):** Gestión de dependencias de JavaFX (`javafx-controls`, `javafx-fxml`), el tema visual **AtlantaFX** (`atlantafx-base`) y automatización del ciclo de vida con `javafx-maven-plugin`.
2. **Encapsulación Fuerte con JPMS (module-info.java):** Reglas explícitas de reflexión (`opens ... to javafx.fxml`) para permitir que `FXMLLoader` instancie los controladores sin violar la seguridad del runtime de Java.
3. **Garantía de Carga de Recursos:** Uso del método `loader.setClassLoader(getClass().getClassLoader())` para prevenir fallos de resolución de paths `.fxml` en entornos modulares.
4. **Patrón Launcher:** Creación de una clase contenedora desacoplada para evitar bloqueos del ejecutable JAR.

### 🔹 Módulo 2: Vista Declarativa FXML & Programación Reactiva

1. **Separación Total de Capas:** Vista totalmente declarativa en FXML (`perfil-view.fxml`) y estilos desacoplados en CSS (`theme.css`), proscribiendo el uso de `.setStyle()` en código Java.
2. **Eliminación de Manejadores Imperativos:** Se eliminan los escuchadores de eventos manuales (`onKeyTyped`, `setOnAction`) en favor del **JavaFX Binding API**.
3. **Bindings Bidireccionales y Unidireccionales:** Sincronización automática entre un `Slider` y un `TextField` mediante `NumberStringConverter`, vinculación directa con el radio y color de un `Circle`, y evaluación reactiva de reglas de negocio (`BooleanBinding`) para habilitar/deshabilitar botones.

### 🔹 Módulo 3: Coordinador de Navegación Empresarial Reactivo

1. **Patrón Coordinador (NavigationManager):** Centralización de la navegación mediante un Singleton que reemplaza dinámicamente el área central (`center`) de un contenedor `BorderPane`.
2. **Seguridad de Tipos con ViewType:** Uso de Enums para gestionar las rutas FXML y títulos de vista, eliminando cadenas de texto mágicas.
3. **Sincronización Reactiva de UI:** Exposición de una propiedad observable `ReadOnlyObjectProperty<ViewType>` que actualiza automáticamente el título del encabezado y deshabilita el botón de la vista activa en el menú de navegación.

## 🚀 Requisitos Previos y Ejecución

### Requisitos

- **Java Development Kit (JDK):** Versión 21 o superior.
- **Apache Maven:** Versión 3.8+ (o el wrapper integrado del IDE).
- **IDE Recomendado:** JetBrains IntelliJ IDEA con el plugin de Scene Builder configurado.

### Comandos de Ejecución desde Terminal

#### 1. Clonar el repositorio

```bash
git clone https://github.com/TU_USUARIO/TU_REPOSITORIO.git
cd TU_REPOSITORIO
```

#### 2. Compilar el proyecto

```bash
mvn clean compile
```

#### 3. Ejecutar la aplicación con el plugin oficial de JavaFX

```bash
mvn javafx:run
```

## 🎨 Estilo y Apariencia Visual

La aplicación utiliza el tema oscuro moderno **AtlantaFX (PrimerDark)** aplicado de forma global en el arranque de la aplicación:

```java
Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
```

Toda la personalización estética de componentes se gestiona en:

```text
/resources/edu/rico/tutorial/tutorialclase/styles/theme.css
```

## 📝 Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.

---

## 📚 Explicación Didáctica de las Decisiones Adoptadas

Nos gustaría resaltar ante sus alumnos tres puntos clave que hemos plasmado en esta documentación:

1. **Delegación de ClassLoader:** Como explicamos en el Módulo 1, recalcar la instrucción `loader.setClassLoader(...)` en el `README.md` ayuda a recordar la importancia de alinear los cargadores de clases del módulo de la aplicación con los del deserializador de FXML.

2. **El poder de los Bindings:** Destacar el Módulo 2 ayuda a comprender la diferencia paradigmática entre ir a buscar un valor a la pantalla manualmente vs. **declarar una regla matemática o lógica** que se evalúa de forma perezosa (*lazy evaluation*) cuando una propiedad cambia.

3. **Escalabilidad del Coordinador de Navegación:** Explicar el Módulo 3 demuestra cómo el `NavigationManager` permite añadir nuevas pantallas (como las que se implementarán en los módulos posteriores) agregando únicamente una constante al enum `ViewType`, sin modificar el marco visual ni el controlador del menú principal.
