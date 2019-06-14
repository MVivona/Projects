# Controller - @author: Michael Vivona
import model
import view


def main():
    score = 0
    computer_score = 0

    while 1:
        play = view.play_prompt().lower()
        if play == 'y':
            break
        else:
            if play == 'n':
                view.print_message('Would you prefer a good game of chess?')
                return ''

    board = model.init_pieces()
    view.refresh(board, model.get_score(board))
    # Main game loop
    while model.red_moves(board)[0] and model.black_moves(board)[0]:
        # Get user inputs
        source = view.get_input(moving=1)
        valid = model.validate_input(board, source, moving=1)
        if not valid[0]:
            view.print_message(valid[1])
            continue

        destination = view.get_input(moving=0)
        valid = model.validate_input(board, destination, moving=0)
        if not valid[0]:
            view.print_message(valid[1])
            continue

        move_from = [int(source[0]), int(source[2])]
        move_to = [int(destination[0]), int(destination[2])]

        # Check first if the player must perform a jump
        if model.player_jump_available(board, move_from, move_to):
            view.print_message("You must perform a jump")
            continue

        # Attempt move on user inputs
        move = model.move_piece(board, score, move_from, move_to)
        if move[0]:
            board = move[1]
            score = move[2]
            # Check if multiple jumps available
            if move[3]:
                view.refresh(board, model.get_score(board))
                view.print_message('Additional move is available')
                continue

            # Computer's move
            board = model.computer_move(board)[0]
            view.refresh(board, model.get_score(board))
        else:
            view.print_message(move[1])

    view.game_over(score, computer_score)


main()
