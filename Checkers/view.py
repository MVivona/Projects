# View - @author: Michael Vivona


def play_prompt():
    return raw_input("Shall we play a game? (y/n) \n")


# Print the game board
def refresh(pieces, score):
    squares = '   _______'
    squares += ' _______' * 7
    board = dict({0: squares})
    squares = ''

    for row in xrange(1, 25):
        for column in xrange(0, 8):
            if column == 0:
                if row % 3 == 2:
                    squares += str(row/3) + ' '
                else:
                    squares += '  '
            if row % 3 == 0:
                squares += '|_______'
            else:
                if row % 3 == 2:
                    squares += '|   {}   '.format(pieces[row / 3][column])
                else:
                    squares += '|       '
        squares += '|'
        board[row] = squares
        squares = ''

    print '\n' * 100
    print '      0       1       2       3       4       5       6       7    '
    for square in board:
        print board[square]
    print 'Your score:', score[0], 'Computer score:', score[1]


# Update the display and inform the user with a message
def print_message(message):
    print message


# User input for move selection
def get_input(moving):
    if moving:
        return raw_input("Select the coordinates of the piece you would like to move (row,col): \n")
    else:
        return raw_input("Select the coordinates of the space you would like to move to (row,col): \n")


# End game message
def game_over(score, computer_score):
    print 'Game Over!'

    if score > computer_score:
        print "You won!"
    else:
        print "You lost!"
