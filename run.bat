@echo off
echo Ejecutando proyecto...

REM Verificar que el proyecto esté compilado
if not exist build\classes\Main.class (
    echo El proyecto no está compilado. Ejecutando compilacion...
    call compile.bat
    if %ERRORLEVEL% NEQ 0 (
        echo Error al compilar el proyecto
        pause
        exit /b 1
    )
)

REM Ejecutar el programa
echo.
echo ================================
echo Iniciando aplicacion...
echo ================================
echo.

java -cp "build\classes;libs\mysql\mysql-connector-j\9.1.0\mysql-connector-j-9.1.0.jar;libs\toedter\jcalendar\1.4\jcalendar-1.4.jar;libs\itextpdf\itextpdf\5.5.13.3\itextpdf-5.5.13.3.jar" Main

pause

