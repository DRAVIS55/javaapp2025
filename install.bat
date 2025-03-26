@echo off
setlocal enabledelayedexpansion
title Student Management System Installer

:: Set colors
set "RED=[91m"
set "GREEN=[92m"
set "YELLOW=[93m"
set "BLUE=[94m"
set "RESET=[0m"

:: Clear screen
cls

:: Show welcome message
echo %BLUE%╔════════════════════════════════════════════════════════════════════════════╗
echo ║                     Student Management System Installer                      ║
echo ╚════════════════════════════════════════════════════════════════════════════╝%RESET%
echo.

:: Check Java
echo %YELLOW%Checking Java installation...%RESET%
java -version >nul 2>&1
if errorlevel 1 (
    echo %RED%Error: Java is not installed or not in PATH%RESET%
    echo Please install Java 8 or later and try again
    pause
    exit /b 1
)
echo %GREEN%✓ Java check passed%RESET%
echo.

:: Check Maven
echo %YELLOW%Checking Maven installation...%RESET%
mvn -version >nul 2>&1
if errorlevel 1 (
    echo %RED%Error: Maven is not installed or not in PATH%RESET%
    echo Please install Maven and try again
    pause
    exit /b 1
)
echo %GREEN%✓ Maven check passed%RESET%
echo.

:: Build progress bar
echo %YELLOW%Building the project...%RESET%
echo [                    ] 0%%
call mvn clean package >nul 2>&1
if errorlevel 1 (
    echo %RED%Error: Failed to build the project%RESET%
    pause
    exit /b 1
)
echo [====================] 100%%
echo %GREEN%✓ Build completed successfully%RESET%
echo.

:: Create desktop shortcut
echo %YELLOW%Creating desktop shortcut...%RESET%
set "DESKTOP=%USERPROFILE%\Desktop"
set "SHORTCUT=%DESKTOP%\Student Management System.lnk"
set "BATCH_PATH=%~dp0run_student_management.bat"
set "ICON_PATH=%~dp0chuka2.ico"

:: Create shortcut with custom icon using VBScript (more reliable than PowerShell)
echo Set oWS = WScript.CreateObject("WScript.Shell") > CreateShortcut.vbs
echo sLinkFile = "%SHORTCUT%" >> CreateShortcut.vbs
echo Set oLink = oWS.CreateShortcut(sLinkFile) >> CreateShortcut.vbs
echo oLink.TargetPath = "%BATCH_PATH%" >> CreateShortcut.vbs
echo oLink.WorkingDirectory = "%~dp0" >> CreateShortcut.vbs
echo oLink.IconLocation = "%ICON_PATH%" >> CreateShortcut.vbs
echo oLink.Save >> CreateShortcut.vbs

cscript //nologo CreateShortcut.vbs
del CreateShortcut.vbs

echo %GREEN%✓ Desktop shortcut created%RESET%
echo.

:: Show completion message
echo %BLUE%╔════════════════════════════════════════════════════════════════════════════╗
echo ║                     Installation Completed Successfully!                      ║
echo ╚════════════════════════════════════════════════════════════════════════════╝%RESET%
echo.
echo %GREEN%A shortcut has been created on your desktop.%RESET%
echo %GREEN%You can now run the application by double-clicking the shortcut.%RESET%
echo.
echo %YELLOW%Press any key to exit...%RESET%
pause >nul 