**INSTRUCTIONS:** Move the Snake using the arrow keys. Consume apples to grow. Avoid landmines. The game ends if snake crashes into a wall or itself.

**CORE CONCEPTS USED:**

  1. **LinkedList:**
  The Snake is represented by a LinkedList. Every time the snake eats an Apple,
  units are added towards the end of the list so the snake can grow from the
  tail. Since the head was the most important part of the Snake--it is the part
  that drives the rest of the snake and its intersection with apples, walls,
  landmines, or itself changes the game state, a LinkedList allows me to easily
  modify the attributes. The LinkedList in GameObj also stores the Apples and
  Landmines. I originally wanted to use 2D arrays to represent the board, but
  Arrays are not designed to be mutable and it was far easier for me to use a
  LinkedList to distinguish between the items on the board.

  2. **JUnit Test:**
  I used JUnit tests to ensure the game state was properly modified by some of
  the methods. I ensured the correct number of apples were being added every
  time the add() method was called and ensured the snake moved properly on the
  map, among other tests. Running the game and playing it myself confirmed the
  accuracy of these tests, which were helpful in pinpointing issues during
  development.

  3. **File I/O:**
  I added a Challenge Map button that provides advanced players with more fun!
  The map has four apples in each corner with a LandMine precariously close to
  one of them. Given the high speed of the snake, it's difficult to weave it
  around the corners without hitting the landMine or the wall. This relies on
  the presetMap.csv file to import the objects and their coordinates, which
  would have been far more difficult to hardcode into the game state.
  Additionally, this allows for easier changes to the CSV file in case the
  challenge proves too hard or not challenging enough.

  4. **Inheritance/Subtyping:**
  For the first basis of inheritance, I expanded upon the given GameObj class.
  This abstract class is subclassed by Item, which is another abstract class.
  Item consists of Apple and LandMine, and it holds the velocity and size of
  each constant (it wouldn't make sense to have an Apple or LandMine move, at
  least in this current version of Snake). Item is then subclassed by Apple and
  LandMine, which differ in their implementations. For instance, Apple's add()
  function caps the number of Apples that can appear on the map as to not ramp
  up the difficulty too quickly, though there is no cap on the number of
  LandMines. Apple must also consider the effects of intersecting with a snake
  with respect to the fact that the Snake must therefore grow and new Apples may
  be formed to replenish the consumed one. This does not happen with LandMine.

**MY IMPLEMENTATION:**

  ```Apple```: Creates Apples to grow the Snake.
  
  ```Direction```: Enumeration to represent the UP, DOWN, LEFT, RIGHT movements
  possible
  
 ```Game```: Creates the GUI with buttons, panels, etc.
  
  ```GameObj```: Abstract class that represents Snake, Apples, and LandMines with
  functions that help determine intersection.
  
  ```GamePlay```: Contains the overall game logic (what happens when the Snake hits a
  wall or an Apple?) and is called consistently to progress the game.
  
  ```Item```: Abstract class that represents Apples and LandMines.
  
  ```LandMine```: Creates LandMines that can kill the Snake.
  
  ```Snake```: Creates the Snake and helps it move and grow.
