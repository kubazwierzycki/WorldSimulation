# World Simulation - JAVA
A turn-based game presents a simulation of the world. Each organism on the board is an instace of a specific, inherited class. All organisms can take actions and interact with each other.
### MyFrame (UI window)
This class extends JFrame from javax.swing. It contains all UI components like buttons to set new objects on the board. What is more, it implemets KeyListner to get information about player move.
### Swiat (World)
An instance of this class has information about all organisms in the game and decides who is able to make a move. Moreover, it is responsible for sending messages to change the game state on the board.
### Organizm (Organism)
An abstract class, which constains all basic fields and methods for each organism in the game. It is extened by Zwierze and Roslina classes.
### Zwierze (Animal)
An abstract class for organism which is able to move, it is extended in classes:
- Czlowiek (Player)
- Antylopa (Antelope)
- Lis (Fox)
- Owca (Sheep)
- Wilk (Wolf)
- Zolw (Turtle)

### Roslina (Plant)
An abstract class for organism which is able to spread, it is extended in classes:
- BarszczSosnowskiego (Sosnowski Borscht)
- Guarana (Guarana)
- Mlecz (Sow-Thistle)
- Trawa (Grass)
- WilczeJagody (Berries)

## What could be done better?
- The code should be fully written in English
- There should be more comments
- Organisms should be kept in sorted collection, now the collection has to be sorted every turn
