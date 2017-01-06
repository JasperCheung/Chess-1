# Chess
APCS1 pd 5

Fabiha Ahmed, Kenny Chen, Jasper Cheung

We will make the popular board game known as Chess playable in the terminal. It will be a 2-player game (no AI). If time allows, we plan to add Chess variants such as Progressive chess, Chess960, Atomic chess, etc.

Logistics:
* Need some way to differentiate between white and black pieces (FEN-style: black is lowercase, white uppercase) (Maybe put color after piece? Eg. Rw, Pb, Nb)
* Board of 8x8 (cells? Where cells can be a piece or nothing?)
* Basic movement of pieces
* Check if a move is legal (if possible move but leaves own king in check -> illegal)
* Check if checkmate or draw
* Special moves: castling, en passant, pawn promotion

Extension:
* Keep list of moves done
* Keep list of pieces taken
* Commands (to show moves done, pieces taken, resignation, agree to draw, help)
* More variants
* PGN export? (for normal chess), FEN export? (for Chess960)

TERMINAL OUTPUT (example):

(new game of normal chess)
```
8 │ r n b q k b n r
7 │ p p p p p p p p
6 │
5 │
4 │
3 │
2 │ P P P P P P P P
1 │ R N B Q K B N R
  └────────────────
    a b c d e f g h

White’s turn:
Enter command or coordinate of piece:   thaowih
Unrecognized command or coordinate of piece "thaowih"
Enter command or coordinate of piece:   a3
No piece at a3.
Enter command or coordinate of piece:   a1
No legal move for R at a1.
Enter command or coordinate of piece:   b1
N at b1; legal moves:   a3, c3
Enter move:   a3

(prints board)

Black’s turn:
Enter command or coordinate of piece:   history 
1. Na3
```
