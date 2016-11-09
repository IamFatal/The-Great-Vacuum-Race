# The-Great-Vacuum-Race  
An assignment for CSC207. Note to UofT students, please **do not plagarize**. MarkUs ***will*** catch you.  
---
### Description  
The game board is a 2-dimensional grid. Each cell in this grid represents either a section of hallway, a piece of wall, or a dumpster. We denote a wall with the symbol X, a hallway with a blank space, and dumpster with U. There are two players in this game | two vacuums. We denote them with symbols 1 and 2. Some of the cells are dirty: they contain dirt (.) or dustballs (o). The vacuums' objective is to clean up as many dirty cells as possible. The dirt is stationary, but the dustballs move about the grid and each cell that a dustball visits becomes dirty (if it wasn't already) when the dustball leaves. 

When a vacuum enters a cell, it cleans the cell. Each time a vacuum cleans dirt, the vacuum's score is incremented (dustballs are worth more). Each vacuum has a capacity. Cleaning up dirt or a dustball adds a constant amount to the fullness of the vacuum. When a vacuum becomes full, it cannot clean any more dirt. In order to resume cleaning dirt, it must visit a dumpster where it becomes empty.

The game ends when all dirt (including dustballs) is gone. The vacuum with the higher score wins, or, if the two scores are equal, we declare a tie.

### Controls:
|       | Player 1 | Player 2 |
| ----- |:---------| --------:|
| Up    | W        | I        |
| Left  | A        | J        |
| Down  | S        | K        |
| Right | D        | L        |

### Examples  
The grid below shows an example initial state of the vacuum game. Notice the two vacuums (1 and 2), 16 dirty cells (.), four dustballs (o), and three dumpsters (U).

```
XXXXXXXXXXXXXXXXXXXXXXXX
X U     ....   X  o  X X
X X   X      X oX  XXX X
XXX XXXXXXXX X  X ...  X
X.. X    X   X  XXXXXX X
X1  X UXXX   X   2   X X
X   X    ..o   XXXX  X X
X XXX XXXXXX      X    X
X X   X    X  X   XXXXXX
X XXXXX  X XXXXX       X
X .....  X   o   XU  X X
XXXXXXXXXXXXXXXXXXXXXXXX
```
