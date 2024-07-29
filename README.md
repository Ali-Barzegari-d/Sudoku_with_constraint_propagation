# Sudoku_with_constraint_propagation
The Constraint Propagation algorithm for solving Sudoku uses advanced techniques such as AC-3 to reduce the domain of possible values for each cell and improve the efficiency of solving the puzzle. Here are the functions used in the program implementation:

1. **print_board(board)**: Prints the Sudoku board, replacing the number 0 with a dot (.) and separating 3x3 blocks with lines.
2. **is_valid(board, num, row, col)**: Checks if the number `num` can be placed in the cell (row, col) by ensuring it doesn't already exist in the same row, column, or 3x3 block. Returns True if valid, otherwise False.
3. **find_empty_location(board)**: Returns the first empty cell (value 0) on the Sudoku board. If no empty cells are found, it returns None.
4. **initialize_domain(board, domain)**: Updates the domain of possible numbers for each empty cell based on Sudoku rules, ensuring no conflicts with existing numbers in the row, column, and 3x3 block.
5. **ac3(domain)**: Uses the AC3 algorithm to reduce the domain of possible numbers for each cell, ensuring that all Sudoku constraints are maintained.
6. **solve_sudoku(board, domain)**: Solves the Sudoku board using a recursive backtracking method combined with the AC3 algorithm. It finds an empty cell, computes its possible values, applies the AC3 algorithm to reduce the domain, and then attempts to fill the board recursively. If a solution is found, it returns True; otherwise, it returns False, indicating no solution exists.

To run the program, you can simply execute it in a development environment or open a terminal, navigate to the appropriate directory, and run the following command:
```bash
python Solving_the_Sudoku_table_with_constraint_propagation.py
```
The final solved Sudoku board will be displayed. You can uncomment lines 114, 115, and 116 in the Python code to see the step-by-step execution of the program.

This project is also implemented in Java. You can compile and run the Java version with the following commands:

```bash
javac Solving_the_Sudoku_table_with_constraint_propagation.java
java Solving_the_Sudoku_table_with_constraint_propagation
