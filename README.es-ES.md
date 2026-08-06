

# Sudoku_with_constraint_propagation
El algoritmo de Propagación de Restricciones para resolver Sudoku utiliza técnicas avanzadas como AC-3 para reducir el dominio de valores posibles de cada celda y mejorar la eficiencia al resolver el rompecabezas. A continuación se listan las funciones utilizadas en la implementación del programa:

1. **print_board(board)**: Imprime el tablero de Sudoku, reemplazando el número 0 por un punto (.) y separando los bloques de 3x3 con líneas.
2. **is_valid(board, num, row, col)**: Verifica si el número `num` puede colocarse en la celda (row, col) asegurándose de que no exista ya en la misma fila, columna o bloque de 3x3. Devuelve `True` si es válido, de lo contrario, `False`.
3. **find_empty_location(board)**: Devuelve la primera celda vacía (valor 0) en el tablero de Sudoku. Si no se encuentran celdas vacías, devuelve `None`.
4. **initialize_domain(board, domain)**: Actualiza el dominio de números posibles para cada celda vacía según las reglas del Sudoku, asegurando que no haya conflictos con los números existentes en la fila, columna y bloque de 3x3.
5. **ac3(domain)**: Utiliza el algoritmo AC3 para reducir el dominio de números posibles para cada celda, asegurando que se mantengan todas las restricciones del Sudoku.
6. **solve_sudoku(board, domain)**: Resuelve el tablero de Sudoku mediante un método de retroceso recursivo combinado con el algoritmo AC3. Encuentra una celda vacía, calcula sus valores posibles, aplica el algoritmo AC3 para reducir el dominio y luego intenta completar el tablero de forma recursiva. Si se encuentra una solución, devuelve `True`; de lo contrario, devuelve `False`, indicando que no existe solución.

Para ejecutar el programa, simplemente puedes ejecutarlo en un entorno de desarrollo o abrir una terminal, navegar al directorio correspondiente y ejecutar el siguiente comando:
```bash
python Solving_the_Sudoku_table_with_constraint_propagation.py
```
Se mostrará el tablero de Sudoku resuelto finalmente. Puedes descomentar las líneas 114, 115 y 116 en el código Python para ver la ejecución paso a paso del programa.

Este proyecto también está implementado en Java. Puedes compilar y ejecutar la versión en Java con los siguientes comandos:

```bash
javac Solving_the_Sudoku_table_with_constraint_propagation.java
java Solving_the_Sudoku_table_with_constraint_propagation
```
