# PodKatch ![PodKatch Icon](./assets/icon/icon.svg)

 A free and open source podcast app for Windows, Mac and Linux. Currently in development.
 
 Subscribe to, download and listen to poscasts.

## Building and Running

First, clone this repository or otherwise download the source code. The `master` branch tends to contain the most recent *non-breaking* change.

Run the build script from the root of the repository:

```bash
# If you're on Linux, Mac or other Unix-like operating systems

./scripts/build.sh
```

```batch
:: If you're on Windows

.\scripts\build.cmd
```

You can also directly build the project using `gradle` or `gradlew` commands.

If you're testing the app or plan on contributing to it, it is recommended to run it directly from within IntelliJ, whether Community or Ultimate edition. Open the project in Intellij, allow it to resolve dependencies, and use it to run or debug either `podkatch.cli.MainKt` or `podkatch.gui.MainKt`.

## Licenses

Code for PodKatch is licensed under the MIT license. [See the license file for details.](LICENSE)

Third party code in this repository have their own licenses, different from PodKatch code. [See the third party licenses.](LICENSE-3RD-PARTY.md)
