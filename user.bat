@echo off
setlocal

set PORT=8082
set PID=

rem Verificar si el puerto está en uso
netstat -ano | findstr :%PORT%
if %errorlevel% equ 0 (
    echo El puerto %PORT% esta en uso. Matando el proceso...
    
    rem Obtener el PID del proceso que está usando el puerto
    for /f "tokens=5" %%a in ('netstat -ano ^| findstr :%PORT%') do taskkill /F /PID %%a
    
	echo Proceso con PID %PID% terminado.
) else (
    echo El puerto %PORT% no esta en uso.
)

endlocal
