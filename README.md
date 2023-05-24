# digital-bank-qa-automation

### Digital Bank
http://digitalbank.upcamp.io/bank/login

### Casos de prueba:
https://docs.google.com/spreadsheets/d/15g7u67GLGJ7U_6EuwljMEBXX2bAXE1xk/edit#gid=1682089917
https://docs.google.com/spreadsheets/d/1wD-ZtN_Ml2dYcbaEL5WYhmGedhVB16YjBGcE_SqFrEo/edit#gid=0

## FUNCIONALIDADES A TESTEAR
- Testear el LOGIN (JUAN) 
- View Checking - ver movimientos cuenta corriente6 - View savings - ver movimientos caja de ahorro (FLOR)
- View Savings - Ver movimientos caja de ahorro

## TEST EN PROGRESO
- HealthCheckTest
- My profile: AccountDeletionTest => shouldDeleteAllAccountsWhenUserClickToDeleteData()
- New Checking: NewCheckingAccountCreation => shouldCreateNewAccountWhenValidDataIsProvided(String accountType, String ownershipType, String accountName, String initialDeposit) 
- View Checking: AccountExistViewTest => shouldDisplayCheckingViewPageWhenAccountsExist()
- View Checking: NoAccountExistViewTest => shouldDisplayNoAccountsAlertWhenNoAccountsExist()
- View Checking: SingleAccountActivationTest => shouldAllowOnlyOneActiveAccountAtATime()

### Criterios para elegir las funcionalidades a testear:
- Son las funcionalidades que más van a usar los usuarios de un banco y deben funcionar correctamente. 
- Los datos de estas funcionalidades luego son utilizados en otras partes de la aplicación como los dashboards

## Convención de nombres de las ramas
Para mantener el repositorio ordenado y facilitar la identificación de la funcionalidad en la que cada desarrollador está trabajando, seguimos una convención de nombres de ramas específica. 

*Cada rama debe ser nombrada siguiendo el formato:*
nombre-de-usuario/CP-<número-de-caso-de-prueba>-<funcionalidad>
  
- nombre-de-usuario es tu nombre
- CP-<número-de-caso-de-prueba> es el ID del caso de prueba con el que estás trabajando
- <funcionalidad> es la funcionalidad a la que pertenece ese caso

Por ejemplo, si estás trabajando en el caso de prueba CP-01 para automatizar el login de Digital Bank, y tu nombre de usuario es "johndoe", podrías nombrar tu rama de la siguiente manera:
johndoe/CP-01-login
  
Esto facilitará la identificación rápida de quién está trabajando en qué y en qué se está trabajando.
