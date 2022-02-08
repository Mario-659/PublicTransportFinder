# Public Transport Finder

A JavaFx GUI app showing real-time positions of buses and trams in Wrocław. Inspired by official [MPK application](https://mpk.wroc.pl/strefa-pasazera/zaplanuj-podroz/mapa-pozycji-pojazdow).

## Building application

If Google Maps API key doesn't work, change it in **`googleMaps.html`**

### With binaries

To include JavaFX I used [javafx gradle plugin](https://github.com/openjfx/javafx-gradle-plugin).
It includes corresponding binaries to the platform it is built on.
For example if you build app on Windows it will run only on Windows. To run with gradle: `gradle run`. To build jar: `gradle fatJar`.

### Using specific binaries

To build app with specific JavaFX binaries comment out gradle plugin in *build.gradle* and add needed dependencies. For example:

```groovy
implementation group: 'org.openjfx', name: 'javafx-web', version: "17-ea+11", classifier: 'win'
```

[Here](https://repo.maven.apache.org/maven2/org/openjfx/) are all available binaries.

*Note that you may also need to add module-info file to project for this to work*

### Platform independent

To make cross-platform build add to *build.gradle* file:

```groovy
configuration = 'compileOnly'
```

and run with needed modules. For example:

```powershell
PS C:\Users\user\Downloads> java --module-path C:\Users\user\Downloads\javafx17\javafx-sdk-17.0.1\lib --add-modules javafx.controls,javafx.fxml -jar app.jar
```
    

You can download JavaFX SDK [here](https://gluonhq.com/products/javafx/)

## GUI

![Screenshot](https://user-images.githubusercontent.com/78920002/153023884-9ee87b53-495d-47b3-a82e-c2f91a2830ef.png)


