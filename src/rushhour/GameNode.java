package rushhour;

import java.util.HashMap;
import java.util.HashSet;

public class GameNode
{
    private GameNode parent;
    private int g;
    private int h = 0;
    private int f;

    private HashMap<Character, Car> cars;

    public GameNode()
    {
        cars = new HashMap<>();
    }

    public GameNode(GameNode node)
    {
        this.cars = new HashMap<>();

        for(char name : node.getCars().keySet())
        {
            this.cars.put(name, new Car(node.getCars().get(name)));
        }
    }

    public void setParent(GameNode p)
    {
        this.parent = p;
    }

    public int getG() {
        return g;
    }

    public int getF() {
        return f;
    }

    public int getH() {
        return h;
    }

    public void setG(int g) {
        this.g = g;
        this.f = this.h + this.g;
    }

    public void setF(int f)
    {
        this.f = f;
    }

    @Override
    public String toString() {
        return "GameNode{\n" +
                "cars=\n" + cars + "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameNode node = (GameNode)o;
        for (Character chars : cars.keySet()) {
            if (cars.get(chars).getX() != node.getCars().get(chars).getX() ||
                    cars.get(chars).getY() != node.getCars().get(chars).getY() ||
                    cars.get(chars).getLength() != node.getCars().get(chars).getLength() ||
                    cars.get(chars).getDir() != node.getCars().get(chars).getDir()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        Integer res = 97;

        for(Character name : cars.keySet())
        {
            res += cars.get(name).getX().toString().hashCode() * 157 + cars.get(name).getY().toString().hashCode() * 127;
            res += res.toString().hashCode();
        }
        return res;
    }

    public GameNode getParent()
    {
        return this.parent;
    }

    public HashMap<Character, Car> getCars()
    {
        return cars;
    }

    public HashSet<GameNode> getNeighbors()
    {
        // STEPS:
        // 1. build board from car info
        // 2. loop through all cars
        // 2.1 find all available moves for each car
        // 2.1.1 create a new GameNode for each available move

        HashSet<GameNode> neighbors = new HashSet<>();

        // 1. BUILD BOARD by looping through cars
        char board[][] = new char[6][6];
        for(int n = 0; n < 36; n++)
            board[n % 6][n / 6] = ' '; // Initializing board to blank

        for(char name : cars.keySet())
        {
            if(cars.get(name).getDir() == 1) // the car is horizontal
            {
                for(int n = 0; n < cars.get(name).getLength(); n++)
                {
                    board[cars.get(name).getX() + n][cars.get(name).getY()] = name;
                }
            }

            else if(cars.get(name).getDir() == -1) // the car is vertical
            {
                for(int n = 0; n < cars.get(name).getLength(); n++)
                {
                    board[cars.get(name).getX()][cars.get(name).getY() + n] = name;
                }
            }

            else
            {
                System.out.println("ERROR: The direction of car " + name + " is invalid");
                return null;
            }
        } // Done building board


        // 2. LOOP THROUGH ALL CARS
        for(char name : cars.keySet())
        {
            // 2.1 FIND ALL AVAILABLE MOVES for the current car

            // If car is vertical
            if(cars.get(name).getDir() == -1)
            {
                // Find all moves that can be made going up
                for(int y = cars.get(name).getY() - 1; y >= 0; y--)
                {
                    if(board[cars.get(name).getX()][y] == ' ')
                    {
                        // This space is clear, so this is a valid move; add this as a neighbor
                        GameNode neighborNode = new GameNode(this);
                        neighborNode.getCars().get(name).setPos(cars.get(name).getX(), y);
                        // This makes the neighbor identical to this one except for the current cars position, which will be changed
                        neighbors.add(neighborNode);
                    }
                    else
                    { // There is another car blocking the path
                        break;
                    }
                }

                // Find all moves that can be made going down
                for(int y = cars.get(name).getY() + 1; y <= 6 - cars.get(name).getLength(); y++)
                {
                    if(board[cars.get(name).getX()][y + cars.get(name).getLength() - 1] == ' ')
                    {
                        // This space is clear, so this is a valid move; add this as a neighbor
                        GameNode neighborNode = new GameNode(this);
                        neighborNode.getCars().get(name).setPos(cars.get(name).getX(), y);
                        // This makes the neighbor identical to this one except for the current cars position, which will be changed
                        neighbors.add(neighborNode);
                    }
                    else // There is another car blocking the path
                    {
                        break;
                    }
                }
            } // end of if car is vertical


            // else if car is horizontal
            else
            {
                // Find all moves that can be made going left
                for(int x = cars.get(name).getX() - 1; x >= 0; x--)
                {
                    if(board[x][cars.get(name).getY()] == ' ')
                    {
                        // This space is clear, so this is a valid move; add this as a neighbor
                        GameNode neighborNode = new GameNode(this);
                        neighborNode.getCars().get(name).setPos(x, cars.get(name).getY());
                        // This makes the neighbor identical to this one except for the current cars position, which will be changed
                        neighbors.add(neighborNode);
                    }
                    else // There is another car blocking the path
                    {
                        break;
                    }
                }

                // Find all moves that can be made going right
                for(int x = cars.get(name).getX() + 1; x <= 6 - cars.get(name).getLength(); x++)
                {
                    if(board[x + cars.get(name).getLength() - 1][cars.get(name).getY()] == ' ')
                    {
                        // This space is clear, so this is a valid move; add this as a neighbor
                        GameNode neighborNode = new GameNode(this);
                        neighborNode.getCars().get(name).setPos(x, cars.get(name).getY());
                        // This makes the neighbor identical to this one except for the current cars position, which will be changed
                        neighbors.add(neighborNode);
                    }
                    else // There is another car blocking the path
                    {
                        break;
                    }
                }
            } // end of else if car is horizontal

            // All possible moves for this car have been added as neighbors

        } // end of loop through all cars

        return neighbors;
    }

    public void calculateHeuristic()
    {
        // 1. Find number of cars directly blocking car X
        // 1.1 Find number of cars blocking each car that is directly blocking car X (cars indirectly blocking car X)

        // First, build the board
        char board[][] = new char[6][6];
        for(int n = 0; n < 36; n++)
            board[n % 6][n / 6] = ' '; // Initializing board to blank

        for(char name : cars.keySet())
        {
            if(cars.get(name).getDir() == 1) // the car is horizontal
            {
                for(int n = 0; n < cars.get(name).getLength(); n++)
                {
                    board[cars.get(name).getX() + n][cars.get(name).getY()] = name;
                }
            }

            else if(cars.get(name).getDir() == -1) // the car is vertical
            {
                for(int n = 0; n < cars.get(name).getLength(); n++)
                {
                    board[cars.get(name).getX()][cars.get(name).getY() + n] = name;
                }
            }

            else
            {
                System.out.println("ERROR: The direction of car " + name + " is invalid");
                return;
            }
        } // Done building board


        h = 0; // cumulative h cost of the node
        int directCost = 2; // relative cost of each car directly blocking car X
        int indirectCost = 1; // relative cost of each car indirectly blocking car X


        // 1. Find number of cars directly blocking car X
        for(int x = cars.get('X').getX() + cars.get('X').getLength(); x < 6; x++)
        {
            if(board[x][cars.get('X').getY()] != ' ')
            {
                h += directCost;

                Car directBlocker = cars.get(board[x][cars.get('X').getY()]); // Must be vertical or else the board isn't solvable

                // 1.1 Find number of cars blocking each car that is directly blocking car X (cars indirectly blocking car X)
                // First, find all cars blocking it on the top
                for(int y = directBlocker.getY() - 1; y >= 0; y--)
                {
                    if(board[x][y] != ' ')
                        h += indirectCost;
                }
                // Find all cars blocking it on the bottom
                for(int y = directBlocker.getY() + directBlocker.getLength(); y < 6; y++)
                {
                    if(board[x][y] != ' ')
                        h += indirectCost;
                }
            }
        } // end of for x

        f = g + h;
    }
}










