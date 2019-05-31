@echo off
set JAVA_HOME=C:\PROGRA~1\JAVA\JDK-12
set FULL_JAR=.\target\LibrariesAssay-jar-with-dependencies.jar
pushd %cd%
cd ..
if exist %FULL_JAR% call %JAVA_HOME%\bin\java -jar %FULL_JAR%
pause
popd