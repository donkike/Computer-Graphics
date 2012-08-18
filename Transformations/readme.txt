Transformaciones
================

Lee una lista de puntos y líneas y aplica transformaciones sobre ellos.
El formato para el input es:

<num_puntos>
x0 y0
x1 y1
<num_lineas>
0 1
1 2
<num_transformaciones>
R 90
S 2
T 100 50

Cada línea se indica con dos números, indicando el punto incial y el punto final de acuerdo al orden de entrada.
Las transformaciones se indican con una letra inicial seguido de los argumentos, así:
R = Rotación, recibe 1 argumento (grados de rotación)
T = Traslación, recibe 1 o 2 argumentos, traslación en X y en Y; si es 1 solo argumento se aplica a ambas coordenadas.
S = Escalamiento, recibe 1 o 2 argumentos, escalamiento en X y en Y; si es 1 solo argumento se aplica a ambas coordenadas.