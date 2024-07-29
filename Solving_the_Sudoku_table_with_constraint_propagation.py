import itertools
from collections import deque

N = 9

board = [
        [3, 0, 6, 5, 0, 8, 4, 0, 0],
        [5, 2, 0, 0, 0, 0, 0, 0, 0],
        [0, 8, 7, 0, 0, 0, 0, 3, 1],
        [0, 0, 3, 0, 1, 0, 0, 8, 0],
        [9, 0, 0, 8, 6, 3, 0, 0, 5],
        [0, 5, 0, 0, 9, 0, 6, 0, 0],
        [1, 3, 0, 0, 0, 0, 2, 5, 0],
        [0, 0, 0, 0, 0, 0, 0, 7, 4],
        [0, 0, 5, 2, 0, 6, 3, 0, 0]
        ]
def print_board(board):
    for r in range(N):
        for d in range(N):
            if board[r][d] == 0 and d % 3 == 2 and d != 8:
                print(". | ", end="")
            elif board[r][d] == 0 and d % 3 != 2:
                print(". ", end="")
            else:
                if d % 3 == 2 and d != 8:
                    print(f"{board[r][d]} | ", end="")
                else:
                    print(f"{board[r][d]} ", end="")
        print()
        if r % 3 == 2 and r != 8:
            print("---------------------")

def is_valid(board, num, row, col):
    for x in range(N):
        if board[row][x] == num or board[x][col] == num:
            return False

    start_row, start_col = row - row % 3, col - col % 3
    for i in range(3):
        for j in range(3):
            if board[i + start_row][j + start_col] == num:
                return False

    return True

def find_empty_location(board):
    for row in range(N):
        for col in range(N):
            if board[row][col] == 0:
                return row, col
    return None, None

def initialize_domain(board, domain):
    for row in range(N):
        for col in range(N):
            if board[row][col] == 0:
                for num in range(1, 10):
                    if is_valid(board, num, row, col):
                        domain[row][col].add(num)
            else:
                domain[row][col].add(board[row][col])

def AC3(domain):
    q = deque()

    for i, j in itertools.product(range(N), repeat=2):
        if len(domain[i][j]) > 1:
            for k in range(N):
                if k != j:
                    q.append((i, j))
                if k != i:
                    q.append((i, j))
            start_row, start_col = i - i % 3, j - j % 3
            for r, c in itertools.product(range(3), repeat=2):
                if start_row + r != i or start_col + c != j:
                    q.append((i, j))

    while q:
        row, col = q.popleft()

        if len(domain[row][col]) == 1:
            val = next(iter(domain[row][col]))
            for k in range(N):
                if k != col and val in domain[row][k] and len(domain[row][k]) == 1:
                    q.append((row, k))
                if k != row and val in domain[k][col] and len(domain[k][col]) == 1:
                    q.append((k, col))
            start_row, start_col = row - row % 3, col - col % 3
            for r, c in itertools.product(range(3), repeat=2):
                if (start_row + r != row or start_col + c != col) and val in domain[start_row + r][start_col + c] and len(domain[start_row + r][start_col + c]) == 1:
                    q.append((start_row + r, start_col + c))

        if not domain[row][col]:
            return False

    return True

def solve_sudoku(board, domain):
    row, col = find_empty_location(board)
    if row is None:
        return True

    initialize_domain(board, domain)

    if not AC3(domain):
        return False

    for num in domain[row][col]:
        if is_valid(board, num, row, col):
            board[row][col] = num
            if solve_sudoku(board, domain):
                return True
            board[row][col] = 0
        ##print_board(board)
        ##print("___________________________")
        ##print()

    return False

if __name__ == "__main__":
    
    domain = [[set() for _ in range(N)] for _ in range(N)]

    if solve_sudoku(board, domain):
        print_board(board)
    else:
        print("No solution exists")

##Ali Barzegari dahaj
