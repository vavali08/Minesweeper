=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: vavali 89598956
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D ARRAYS - I will be creating an 9x9 Minesweeper board with fifteen bombs, and I will use a 2D array to store the
  state of each square/location on the board. This 2D array will contain values of a new class Square which I will
  create, which contains four pieces of instance data: isCovered(boolean), isMine(boolean), isFlagged(boolean), and
  numMines(integer). At the beginning, all the squares will have an isCovered value of True. The values within the 2D
  array will change after each click by the user, and will be updated to store the current state of the game.

  2.  FILE I/O - I will use an I/O to store the state of the game so the save button saves the current state of the
  game. The user can reload any saved game at any point and the program will continue running the game from this point.
  The FileWriter stores the values of the pieces of instance data for the Minesweeper class, and then iterates through
  each Square on the game board and stores the instance data for each square on a new line. The FileReader reads through
  this information, expecting it to be in the same format. If the formatting is off, an Exception is thrown, which is
  later caught and handled in the RunMinesweeper class.

  3. JUNIT TESTABLE COMPONENT - I wrote many tests to test the game model, which functions independently of the GUI.
   Specifically, I tested the functions in the Minesweeper class. I first created a standard testing board that could be
   used for easier testing, as I would know where all the mines are. Some of the testable functions that I implemented
   were reset(resets the board game to the original state), firstClick(simulates the first click in a Minesweeper game),
   minePlaceable(checks whether a mine is placeable on a certain square), click(simulates a left click on a square),
   rightClick(simulates a right click on a square), and didUserWin(checks if the user has won the game yet). When
   testing these functions, I called each function on the testing board and checked if the updated version of the board
   matched the expected value.


  4. RECURSION - I created a recursive function called uncoverSurrounding, which uncovers the squares adjacent to a
  square which has no mines surrounding it. If a square next to the original square also has numMines(the number of
  surrounding mines) = 0, then this same recursive function will be called on this square, and the function will
  uncover all the squares until it recognizes squares that are near mines. However, if a square next to the original
  square has numMines = 0 but is already uncovered, then the recursive function will be not called. Hence, the base
  case occurs when either numMines > 0 for all surrounding squares or all surrounding square are uncovered.


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

  Flag - loads the Flag image and creates a function to draw the flag.
  Mine - loads the Mine image and creates a function to draw the mine.
  Square - stores information for each Square on the game board. This information includes whether a Square is covered,
  whether a Square has a mine, whether a Square has been flagged, and the number of mines in the Squares surrounding
  this Square. There are also accessors and modifiers to change the instance data.
  Minesweeper - This class represents the game's model. It stores instance data representing the game board(2D array of
  Squares), whether the game is over and who won, whether the game has started, and the coordinates of the first point
  clicked. This class has various functions that update the state of the game board and represent turns in the game
  when called and controls all of the game play.
  GameBoard - This class creates a GUI for the GameBoard and includes functions that shows how to draw the game board
  on the screen. This class also adds the mouse click listeners that allow the user to interact with the game.
  RunMinesweeper - This class sets up the top level frame and widgets. It includes the GameBoard but also incorporates
  elements to show the instructions and load and save games.




- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  I did not have any significant stumbling blocks during the implementation.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  I think I separated the functionality well, as the game is completely playable without the use of the GUI. Each of the
  Squares, as well as the Minesweeper class itself, all have private pieces of instance data that can only be altered
  by calling functions, so I think the private state is well encapuslated. I do not see any obvious areas for
  refactoring.



========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.

IMAGES

Mine: https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.amazon.com%2FArclite-Systems-Ltd-Minesweeper-Classic%2Fdp%2F
B00EJO59PE&psig=AOvVaw2F7BC41mJaXXrUaUPonQ91&ust=1670364838659000&source=images&cd=vfe&ved=0CBAQjhxqFwoTCNCz1sTA4_sCFQAA
AAAdAAAAABAD

Flag:https://www.google.com/url?sa=i&url=https%3A%2F%2Fcommons.wikimedia.org%2Fwiki%2FFile%3ARed_flag_waving_transparent
.png&psig=AOvVaw0nsPkV-TMV1jk8gi52AW-H&ust=1670365213765000&source=images&cd=vfe&ved=0CBAQjhxqFwoTCLiRiK3E4_sCFQAAAAAdAA
AAABAD

