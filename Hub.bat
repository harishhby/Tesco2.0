set mypath=%cd%
@echo %mypath%
java -Dwebdriver.ie.driver=%mypath%/drivers/IEDriverServer.exe -jar %mypath%/jars/selenium-server-standalone-2.52.0.jar -role hub -hubConfig %mypath%/hub.json
pause