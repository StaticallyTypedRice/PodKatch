@echo off

echo Please run this script from the root of the repository.

.\gradlew tasks --stacktrace
.\gradlew assemble --stacktrace
.\gradlew build --stacktrace
