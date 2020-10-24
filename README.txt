# Proyecto2-TDP
El juego se inicializa con una matriz de enteros de 9x9, representadas cada una con una entidad gráfica. A su vez, teniendo en cuenta la posicion de la fila y la columna del
elemento, se determina el sector al cual pertenece. Estos se representan en submatrices de 3x3. Particularmente, se lee el siguiente archivo:
    
   -------------------------               --------------------------------
   | 5,3,4 | 6,7,8 | 9,1,2 |              |          |          |          |
   | 6,7,2 | 1,9,5 | 3,4,8 |              | Sector 1 | Sector 2 | Sector 3 |  
   | 1,9,8 | 3,4,2 | 5,6,7 |              |          |          |          | 
    -----------------------                --------------------------------
   | 8,5,9 | 7,6,1 | 4,2,3 |              |          |          |          |  
   | 4,2,6 | 8,5,3 | 7,9,1 |     ====>    | Sector 4 | Sector 5 | Sector 6 |
   | 7,1,3 | 9,2,4 | 8,5,6 |              |          |          |          |   
    -----------------------                --------------------------------
   | 9,6,1 | 5,3,7 | 2,8,4 |              |          |          |          |     
   | 2,8,7 | 4,1,9 | 6,3,5 |              | Sector 7 | Sector 8 | Sector 9 |
   | 3,4,5 | 2,8,6 | 1,7,9 |              |          |          |          |
   -------------------------               --------------------------------
   
Una vez elimnadas algunas celdas de manera aleatoria, con el botón "Comenzar", se habilitarán todas las celdas vacías. En caso de que una posición sea incorrecta, se remarca y se
deshabilitan el resto de las celdas hasta la corrección de la misma.
El boton "Validar" decide si todos los elementos del juego cumplen con las reglas de la misma.

El juego debe ejecutarse dentro de la carpeta.
