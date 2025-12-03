@echo off
echo Compilando proyecto...

REM Crear directorio de salida
if not exist build\classes mkdir build\classes

REM Compilar archivos fuente
javac -d build\classes -cp "libs\mysql\mysql-connector-j\9.1.0\mysql-connector-j-9.1.0.jar;libs\toedter\jcalendar\1.4\jcalendar-1.4.jar;libs\itextpdf\itextpdf\5.5.13.3\itextpdf-5.5.13.3.jar" -sourcepath src src\Main.java src\Configuracion\*.java src\Modelo\*.java src\DAO\*.java src\Controlador\*.java src\Util\*.java src\Vista\*.java

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ================================
    echo Compilacion exitosa!
    echo ================================
    echo Archivos compilados en: build\classes
) else (
    echo.
    echo ================================
    echo Error en la compilacion
    echo ================================
)

pause

