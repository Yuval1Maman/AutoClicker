import chess
import random

def main():
    board = chess.Board()
    player_color = choose_player_color()

    while not board.is_game_over():
        print_board(board)
        
        if board.turn == player_color:
            user_move = input("Enter your move (e.g., 'e2e4'): ")
            move = chess.Move.from_uci(user_move)
        else:
            move = choose_random_move(board)
            
        if move in board.legal_moves:
            board.push(move)
        else:
            print("Invalid move. Try again.")

    print("Game over.")
    print("Result: " + board.result())

def print_board(board):
    print(board)

def choose_player_color():
    color = input("Choose your color (w/b): ")
    if color.lower() == 'w':
        return chess.WHITE
    else:
        return chess.BLACK

def choose_random_move(board):
    legal_moves = list(board.legal_moves)
    return random.choice(legal_moves)

if __name__ == "__main__":
    main()
