# Model - @author: Michael Vivona
import re
from copy import deepcopy


# Initialize checkerboard with starting locations of the board
def init_pieces():
    board = dict()
    board[0] = [' ', 'b', ' ', 'b', ' ', 'b', ' ', 'b']
    board[1] = ['b', ' ', 'b', ' ', 'b', ' ', 'b', ' ']
    board[2] = [' ', 'b', ' ', 'b', ' ', 'b', ' ', 'b']
    board[3] = [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ']
    board[4] = [' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ']
    board[5] = ['r', ' ', 'r', ' ', 'r', ' ', 'r', ' ']
    board[6] = [' ', 'r', ' ', 'r', ' ', 'r', ' ', 'r']
    board[7] = ['r', ' ', 'r', ' ', 'r', ' ', 'r', ' ']

    return board


# Function handles the computer's movement
# First see if any jumps are available
# Then run the mini max algorithm and return the resulting board
def computer_move(board):
    moves = get_black_jumps(board, move_set=dict(), moves=0)[0]

    if len(moves) > 0:
        move = mini_max(board, moves, depth=6, maximizing_player=True)
        return move

    move_set = black_moves(board)[1]
    move = mini_max(board, move_set, depth=6, maximizing_player=True)
    return move


# Perform the mini max algorithm
def mini_max(board, move_set, depth, maximizing_player):
    if depth == 0 or game_over(board):
        return board, heuristic_value(board, move_set, maximizing_player)
    best_board = board
    best_move = ''
    best_value = ''

    if maximizing_player:
        best_value = float('-inf')
        for position in move_set:
            for move in move_set[position]:
                max_board = deepcopy(board)
                max_board = quick_move(max_board, position, move)
                moves = black_moves(max_board)[1]
                value = mini_max(max_board, moves, depth - 1, False)[1]
                if value > best_value:
                    best_value = value
                    best_move = position, move
                    best_board = max_board

    else:
        best_value = float('inf')
        for position in move_set:
            for move in move_set[position]:
                mini_board = deepcopy(board)
                mini_board = quick_move(mini_board, position, move)
                moves = red_moves(mini_board)[1]
                value = mini_max(mini_board, moves, depth - 1, True)[1]
                best_value = min(best_value, value)

                if value < best_value:
                    best_value = value
                    best_move = position, move
                    best_board = mini_board

    return best_board, best_value, best_move


# Return a heuristic value using the number of pieces and the number of kings
def heuristic_value(board, moves, maximizing_player):
    red_pieces = 0
    red_kings = 0
    black_pieces = 0
    black_kings = 0

    for row in xrange(8):
        for col in xrange(8):
            piece = board[row][col]
            if piece == 'r':
                red_pieces += 1
            if piece == 'R':
                red_kings += 1
            if piece == 'b':
                black_pieces += 1
            if piece == 'B':
                black_kings += 1

    return red_pieces - black_pieces + 1.4*(red_kings - black_kings)


# Move the player's piece
def move_piece(board, score, move_from, move_to):
    if move_from[0] == move_to[0] or move_from[1] == move_to[1]:
        return False, "Moves must be diagonal"

    if board[move_from[0]][move_from[1]] == 'r':
        if move_from[0] < move_to[0]:
            return False, "Uncrowned pieces may not move backwards"

    jumped = False
    if abs(move_to[0] - move_from[0]) == 2:

        if move_to[0] - move_from[0] == -2:
            jump_row = move_from[0] - 1
        else:
            jump_row = move_from[0] + 1

        if move_to[1] - move_from[1] == -2:
            jump_col = move_to[1] + 1
        else:
            jump_col = move_to[1] - 1

        if board[jump_row][jump_col].lower() != 'b':
            return False, "That piece cannot jump"
        else:
            board[jump_row][jump_col] = ' '
            score += 1
            jumped = True

    board[move_to[0]][move_to[1]] = board[move_from[0]][move_from[1]]
    board[move_from[0]][move_from[1]] = ' '

    is_crowned(board, move_to)

    return True, board, score, jumped and extra_player_jump(board, move_to)


# Move the computer's piece
def quick_move(board, move_from, move_to):
    board[move_to[0]][move_to[1]] = board[move_from[0]][move_from[1]]
    board[move_from[0]][move_from[1]] = ' '

    if abs(move_to[0] - move_from[0]) == 2:

        if move_to[0] - move_from[0] == -2:
            jump_row = move_from[0] - 1
        else:
            jump_row = move_from[0] + 1

        if move_to[1] - move_from[1] == -2:
            jump_col = move_to[1] + 1
        else:
            jump_col = move_to[1] - 1
        board[jump_row][jump_col] = ' '

    is_crowned(board, move_to)

    return board


# Determines if the moved piece can make a jump
def extra_player_jump(board, move):
    red_jumps = get_red_jumps(board, move_set=dict(), moves=0)[0]

    position = move[0], move[1]
    if len(red_jumps) > 0 and position in red_jumps:
        return True
    else:
        return False


# Returns true if player can perform a jump but did not attempt to make a valid jump
def player_jump_available(board, move_from, move_to):
    red_jumps = get_red_jumps(board, move_set=dict(), moves=0)[0]

    position = move_from[0], move_from[1]
    move = move_to[0], move_to[1]
    if len(red_jumps) > 0:
        if position not in red_jumps:
            return True
        else:
            if move not in red_jumps[position]:
                return True
    return False


# Crown a piece if it made it to the other side of the board
def is_crowned(board, move_to):

    if board[move_to[0]][move_to[1]] == 'r' and move_to[0] == 0:
        board[move_to[0]][move_to[1]] = 'R'

    if board[move_to[0]][move_to[1]] == 'b' and move_to[0] == 7:
        board[move_to[0]][move_to[1]] = 'B'

    return board


# Return the current score
def get_score(board):
    reds = 0
    blacks = 0

    for row in xrange(8):
        for col in xrange(8):
            piece = board[row][col]
            if piece.lower() == 'r':
                reds += 1
            if piece.lower() == 'b':
                blacks += 1

    return (12 - blacks), (12 - reds)


# Force the player to make a valid move
def validate_input(board, selection, moving):
    if len(selection) != 3 or not re.search('[0-9][,][0-9]', selection):
        return False, "Improper input format: {}".format(selection)

    piece = board[int(selection[0])][int(selection[2])]

    if moving:
        if piece == ' ':
            return False, "This square has no piece: {}".format(selection)

        if piece.lower() == 'b':
            return False, "That is not one of your pieces: {}".format(selection)
    else:
        if piece.lower() == 'b' or piece.lower() == 'r':
            return False, "This square is occupied: {}".format(selection)
    return True, ""


# Determine whether the game has ended
def game_over(board):
    computer = black_moves(board)[0]
    player = black_moves(board)[0]
    return not computer and player


# Return a list of moves available to the player
def red_moves(board):
    moves = 0
    move_set = dict()

    for row in xrange(8):
        for col in xrange(8):
            piece = board[row][col]
            if piece.lower() == 'r':
                if row - 1 >= 0 and col - 1 >= 0:
                    try:
                        if board[row - 1][col - 1] == ' ':
                            moves += 1
                            move_set.setdefault((row, col), []).append((row - 1, col - 1))
                    except KeyError:
                        "Key Error"
                    except IndexError:
                        "Index Error"
                if row - 1 >= 0:
                    try:
                        if board[row - 1][col + 1] == ' ':
                            moves += 1
                            move_set.setdefault((row, col), []).append((row - 1, col + 1))
                    except KeyError:
                        "Key Error"
                    except IndexError:
                        "Index Error"

                if piece == 'R':
                    try:
                        if board[row + 1][col + 1] == ' ':
                            moves += 1
                            move_set.setdefault((row, col), []).append((row + 1, col + 1))
                    except KeyError:
                        "Key Error"
                    except IndexError:
                        "Index Error"
                    if col - 1 >= 0:
                        try:
                            if board[row + 1][col - 1] == ' ':
                                moves += 1
                                move_set.setdefault((row, col), []).append((row + 1, col - 1))
                        except KeyError:
                            "Key Error"
                        except IndexError:
                            "Index Error"
    move_set, moves = get_red_jumps(board, move_set, moves)
    return moves > 0, move_set


# Return a list of jumps the player can make
def get_red_jumps(board, move_set, moves):
    for row in xrange(8):
        for col in xrange(8):
            piece = board[row][col]
            if piece.lower() == 'r':
                if row - 1 >= 0 and col - 1 >= 0 and row - 2 >= 0 and col - 2 >= 0:
                    try:
                        if board[row - 1][col - 1].lower() == 'b' and board[row - 2][col - 2] == ' ':
                            moves += 1
                            move_set.setdefault((row, col), []).append((row - 2, col - 2))
                    except IndexError:
                        "Errors"
                    except KeyError:
                        ""
                if row - 1 >= 0 and row - 2 >= 0:
                    try:
                        if board[row - 1][col + 1].lower() == 'b' and board[row - 2][col + 2] == ' ':
                            moves += 1
                            move_set.setdefault((row, col), []).append((row - 2, col + 2))
                    except IndexError:
                        "Errors"
                    except KeyError:
                        ""
                if piece == 'R':
                    try:
                        if board[row + 1][col + 1].lower() == 'b' and board[row + 2][col + 2] == ' ':
                            moves += 1
                            move_set.setdefault((row, col), []).append((row + 2, col + 2))
                    except IndexError:
                        "Errors"
                    except KeyError:
                        ""
                    if col - 1 >= 0 and col - 2 >= 0:
                        try:
                            if board[row + 1][col - 1].lower() == 'b' and board[row + 2][col - 2] == ' ':
                                moves += 1
                                move_set.setdefault((row, col), []).append((row + 2, col - 2))
                        except IndexError:
                            "Errors"
                        except KeyError:
                            ""
    return move_set, moves


# Return a list of moves available to the computer
def black_moves(board):
    moves = 0
    move_set = dict()
    for row in xrange(8):
        for col in xrange(8):
            piece = board[row][col]
            if piece.lower() == 'b':
                try:
                    if board[row + 1][col + 1] == ' ':
                        moves += 1
                        move_set.setdefault((row, col), []).append((row + 1, col + 1))
                except KeyError:
                    "Key Error"
                except IndexError:
                    "Index Error"
                if col - 1 >= 0:
                    try:
                        if board[row + 1][col - 1] == ' ':
                            moves += 1
                            move_set.setdefault((row, col), []).append((row + 1, col - 1))
                    except KeyError:
                        "Key Error"
                    except IndexError:
                        "Index Error"

                if piece == 'B':
                    if row - 1 >= 0:
                        try:
                            if board[row - 1][col + 1] == ' ':
                                moves += 1
                                move_set.setdefault((row, col), []).append((row - 1, col + 1))
                        except KeyError:
                            "Key Error"
                        except IndexError:
                            "Index Error"
                    if row - 1 >= 0 and col - 1 >= 0:
                        try:
                            if board[row - 1][col - 1] == ' ':
                                moves += 1
                                move_set.setdefault((row, col), []).append((row - 1, col - 1))
                        except KeyError:
                            "Key Error"
                        except IndexError:
                            "Index Error"
    move_set, moves = get_black_jumps(board, move_set, moves)
    return moves > 0, move_set


# Return a list of jumps the computer can make
def get_black_jumps(board, move_set, moves):
    for row in xrange(8):
        for col in xrange(8):
            piece = board[row][col]
            if piece.lower() == 'b':
                try:
                    if board[row + 1][col + 1].lower() == 'r' and board[row + 2][col + 2] == ' ':
                        moves += 1
                        move_set.setdefault((row, col), []).append((row + 2, col + 2))
                except IndexError:
                    "Errors"
                except KeyError:
                    ""
                if col - 1 >= 0 and col - 2 >= 0:
                    try:
                        if board[row + 1][col - 1].lower() == 'r' and board[row + 2][col - 2] == ' ':
                            moves += 1
                            move_set.setdefault((row, col), []).append((row + 2, col - 2))
                    except IndexError:
                        "Errors"
                    except KeyError:
                        ""
                if piece == 'B':
                    if row - 1 >= 0 and row - 2 >= 0:
                        try:
                            if board[row - 1][col + 1].lower() == 'r' and board[row - 2][col + 2] == ' ':
                                moves += 1
                                move_set.setdefault((row, col), []).append((row - 2, col + 2))
                        except IndexError:
                            "Errors"
                        except KeyError:
                            ""
                    if row - 1 >= 0 and col - 1 >= 0 and row - 2 >= 0 and col - 2 >= 0:
                        try:
                            if board[row - 1][col - 1].lower() == 'r' and board[row - 2][col - 2] == ' ':
                                moves += 1
                                move_set.setdefault((row, col), []).append((row - 2, col - 2))
                        except IndexError:
                            "Errors"
                        except KeyError:
                            ""

    return move_set, moves
