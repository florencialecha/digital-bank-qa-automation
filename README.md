# digital-bank-qa-automation

Digital Bank
http://digitalbank.upcamp.io/bank/login

Casos de prueba:
https://docs.google.com/spreadsheets/d/15g7u67GLGJ7U_6EuwljMEBXX2bAXE1xk/edit#gid=1682089917
https://docs.google.com/spreadsheets/d/1wD-ZtN_Ml2dYcbaEL5WYhmGedhVB16YjBGcE_SqFrEo/edit#gid=0

FUNCIONALIDADES A TESTEAR
1 - Testear el LOGIN (JUAN) 
2 - Deposit - que se pueda hacer un depósito (FLOR)
5 - View Checking - ver movimientos cuenta corriente6 - View savings - ver movimientos caja de ahorro

SI LLEGAMOS:
3 - Withdraw - que se pueda hacer un retiro
4 - Trasfer Between Accounts - que se pueda hacer una transferencia

Criterios para elegir las funcionalidades a testear:
- Son las funcionalidades que más van a usar los usuarios de un banco y deben funcionar correctamente. 
- Los datos de estas funcionalidades luego son utilizados en otras partes de la aplicación como los dashboards

Convención de nombres de las ramas
Para mantener el repositorio ordenado y facilitar la identificación de la funcionalidad en la que cada desarrollador está trabajando, seguimos una convención de nombres de ramas específica. Cada rama debe ser nombrada siguiendo el formato:

php
Copy code
<nombre-de-usuario>/CP-<número-de-caso-de-prueba>-<descripción-breve>
Aquí, <nombre-de-usuario> es tu nombre de usuario o tus iniciales, CP-<número-de-caso-de-prueba> es el ID del caso de prueba con el que estás trabajando y <descripción-breve> es una descripción corta que indica qué estás haciendo en esa rama.

Por ejemplo, si estás trabajando en el caso de prueba CP-01 para automatizar el login de Digital Bank, y tu nombre de usuario es "johndoe", podrías nombrar tu rama de la siguiente manera:

Copy code
johndoe/CP-01-automate-login
Esto facilitará la identificación rápida de quién está trabajando en qué y en qué se está trabajando. Asegúrate de que la descripción breve sea clara y suficientemente descriptiva para entender el propósito de la rama solo con leer el nombre.
