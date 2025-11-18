<div align="center">

# Flipmatch

</div>


## Proyecto de Programación Orientada a Objetos

### Autores

- Jhonatan Moreno Nuñez  
- Juan Camilo Moreno Altamirano  
- Stefania Mejía Velásquez  
- Ismael Santiago Matiz Cárdenas  

### Profesor

David Seligmann

### Institución

Institución Universitaria Politécnico Grancolombiano 
Facultad de Ingeniería  
Programa de Ingeniería de Software

### Fecha

16 de noviembre de 2025


## Testing
Para este proyecto se implementaron pruebas unitarias exclusivamente sobre la clase GameService, debido a que en esta se concentra la lógica fundamental del juego. En ella se gestionan las operaciones críticas como la creación del tablero, el volteo de cartas, la verificación de parejas, el bloqueo temporal tras fallos y el cálculo de estadísticas como puntaje e intentos. 

### Ejecución de pruebas en Eclipse
Para ejecutar las pruebas unitarias en Eclipse, se siguieron los siguientes pasos:
- Ubicación del archivo de prueba:
El archivo GameServiceTest.java se colocó dentro de un paquete separado (src/test) para mantener una estructura organizada.
- Configuración del entorno:
Se hizo clic derecho sobre el proyecto → Build Path → Configure Build Path → pestaña Libraries(a nivel de Modulepath) → Add Library… y se escogio la libreria de JUnit 5.
- Ejecución de la prueba:
Una vez agregado JUnit, se hizo clic derecho sobre el archivo de prueba(GameServiceTest.java) → Run As → JUnit Test. Eclipse ejecutó automáticamente los métodos anotados con @Test y mostró los resultados en la vista de JUnit.

# Guía para Generar la Documentación Javadoc

Esta guía explica paso a paso cómo generar la documentación **Javadoc** del proyecto *Flip & Match* utilizando **Eclipse**.

---

## 1. Abrir el generador de Javadoc

- En la barra superior de Eclipse, ve a:  
  **Project → Generate Javadoc…**
- Selecciona el proyecto **flipmatch**.
- Haz clic en **Next** hasta llegar a la pantalla final.

---

## 2. Generar la documentación

- Revisa las opciones seleccionadas.
- Haz clic en **Finish**.
- Eclipse generará los archivos **.html** correspondientes al Javadoc.

> **Nota:** Si aparece algún *warning*, no afecta la generación de la documentación.

---

## 3. Ver la documentación generada

- Dentro de la carpeta **doc/** abre el archivo:  
  **index.html**

Esto mostrará el portal completo de documentación Javadoc.

