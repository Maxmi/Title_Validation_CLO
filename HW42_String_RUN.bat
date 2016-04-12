@ECHO OFF
::========================================
::SET GITHUB_ACCOUNT=ChukhraiUriy
::SET WS_DIR=Workspace
::SET REPO_NAME=Title_Validation_CLO
::SET APP_VERSION=1.1
::SET MAIN_CLASS_01=core.Selenium_CLO
::SET TEST_SUITE=AllTests
::SET COM_OPT_POS=-c TC-001.01 -u http://www.youtube.com -t YouTube
::SET COM_OPT_NEG=-c TC-001.01 -u http://www.youtube.com -t YouTube
::========================================

::========================================
SET GITHUB_ACCOUNT=%1
SET WS_DIR=%2
SET REPO_NAME=%3
SET APP_VERSION=%4
SET MAIN_CLASS_01=%5
SET MAIN_CLASS_02=%6
::SET TEST_SUITE=%5
::========================================

IF "%JAVA_HOME%" == "" GOTO EXIT_JAVA
ECHO Java installed
IF "%M2%" == "" GOTO EXIT_MVN
ECHO Maven installed
CALL git --version > nul 2>&1
IF NOT %ERRORLEVEL% == 0 GOTO EXIT_GIT
ECHO Git installed
IF NOT EXIST C:\%WS_DIR% GOTO NO_WORKSPACE

GOTO NEXT

::NEXT
::IF NOT EXIST C:\%WS_DIR% GOTO NO_WORKSPACE
::IF EXIST C:\%WS_DIR%\%REPO_NAME% RMDIR /S /Q C:\%WS_DIR%\%REPO_NAME%
::CD C:\%WS_DIR%
::git clone https://github.com/%GITHUB_ACCOUNT%/%REPO_NAME%.git

::CD %REPO_NAME%
::SLEEP 2


:NEXT
CD C:\%WS_DIR%\%REPO_NAME%

ECHO Building package Fat jar ...
::CALL mvn clean package -Dyc.version="%APP_VERSION%" -Dyc.mainClass="%MAIN_CLASS%" > report_HW42-%APP_VERSION%-%MAIN_CLASS%-Maven.txt
CALL mvn clean package -Dyc.version="%APP_VERSION%" > report_HW42-%REPO_NAME%-%APP_VERSION%-Maven.txt
DEL .\target\%REPO_NAME%-%APP_VERSION%.jar
COPY .\target\%REPO_NAME%-%APP_VERSION%-jar-with-dependencies.jar .\target\Fat-%APP_VERSION%.jar


ECHO Executing Java programm Selenium Web driver...
::java -jar C:\%WS_DIR%\%REPO_NAME%\target\Fat-%APP_VERSION%.jar -c TC-001.01 -u http://www.youtube.com -t YouTube >> C:\%WS_DIR%\%REPO_NAME%\report_HW42-%APP_VERSION%-%MAIN_CLASS%-Runner.txt
::java -jar C:\%WS_DIR%\%REPO_NAME%\target\Fat-%APP_VERSION%.jar -c TC-001.02 -u http://www.youtube.com -t abc >> C:\%WS_DIR%\%REPO_NAME%\report_HW42-%APP_VERSION%-%MAIN_CLASS%-Runner.txt

java -cp C:\%WS_DIR%\%REPO_NAME%\target\Fat-%APP_VERSION%.jar %MAIN_CLASS_01% -c TC-001.01 -u http://www.youtube.com -t YouTube >> C:\%WS_DIR%\%REPO_NAME%\report_HW42-String-%APP_VERSION%-%MAIN_CLASS_01%-Runner.txt
java -cp C:\%WS_DIR%\%REPO_NAME%\target\Fat-%APP_VERSION%.jar %MAIN_CLASS_01% -c TC-001.02 -u http://www.youtube.com -t abc >> C:\%WS_DIR%\%REPO_NAME%\report_HW42-String-%APP_VERSION%-%MAIN_CLASS_01%-Runner.txt

ECHO Executing Java programm HtmlUnit driver...
java -cp C:\%WS_DIR%\%REPO_NAME%\target\Fat-%APP_VERSION%.jar %MAIN_CLASS_02% -c TC-002.01 -u https://www.wikipedia.org -t Wikipedia >> C:\%WS_DIR%\%REPO_NAME%\report_HW42-String-%APP_VERSION%-%MAIN_CLASS_02%-Runner.txt
java -cp C:\%WS_DIR%\%REPO_NAME%\target\Fat-%APP_VERSION%.jar %MAIN_CLASS_02% -c TC-002.02 -u https://www.wikipedia.org -t abc >> C:\%WS_DIR%\%REPO_NAME%\report_HW42-String-%APP_VERSION%-%MAIN_CLASS_02%-Runner.txt


GOTO END

:EXIT_JAVA
ECHO No Java installed. Bye, bye ....
GOTO END

:EXIT_MVN
ECHO No Maven installed. Bye, bye ....
GOTO END

:EXIT_GIT
ECHO No Git installed. Bye, bye ....
GOTO END

:NO_WORKSPACE
ECHO %WS_DIR% is not exists. Bye, bye ....
GOTO END

:END
ECHO DONE. Bye!
CD\