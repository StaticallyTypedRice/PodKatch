# PodKatch ![PodKatch Icon](./assets/icon/icon.svg)

 A free and open source podcast app for Windows, Mac and Linux. Currently in development.
 
 Subscribe to, download and listen to poscasts.

## Building and Running

PodKatch is still in development, so currently there is no official production build procedure. If you're testing the app or plan on contributing to it, it is recommended to build it directly from within IntelliJ, whether Community or Ultimate edition.

First, clone this repository or otherwise download the source code. The `master` branch tends to contain the most recent *non-breaking* change.

Open the project in Intellij, allow it to resolve dependencies, and use it to run or debug either `podkatch.cli.MainKt` or `podkatch.gui.MainKt`.

You can also build using the Gradle command in your `PATH` or with the included `gradlew` script:

```bash
./gradlew assemble
```
