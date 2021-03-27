package rushhour;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class GameNode
{
	private GameNode parent;
	private byte f = 63; // f + h
	private byte g;
	private byte h;
	
	private HashMap<Character, Car> cars;
	
	public GameNode() {
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

	public byte getF() {
		return f;
	}

	public byte getG() {
		return g;
	}

	public byte getH() {
		return h;
	}

	public void setF(byte f) {
		this.f = f;
	}

	public void setG(byte g) {
		this.g = g;
	}

	public void setH(byte h) {
		this.h = h;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		GameNode gameNode = (GameNode) o;
		return f == gameNode.f && g == gameNode.g && h == gameNode.h && Objects.equals(parent, gameNode.parent) && Objects.equals(cars, gameNode.cars) && Objects.equals(neighbors, gameNode.neighbors);
	}

	@Override
	public int hashCode() {
		return Objects.hash(parent, f, g, h, cars, neighbors);
	}

	public int computeHashCode() {
		int arr[] = {5, 11, 17, 29, 37, 41, 53, 59, 67, 71, 79, 97, 101, 107, 127, 137, 149, 157};
		int i = 0, res = 0;
		for (Car car: cars.values()) {
			res = res + (car. * arr[i]);
			i++;
		}
		final int prime = 1327;
		return prime*((neighbors == null))
	}

	public GameNode getParent()
	{
		return this.parent;
	}
	
	public HashMap<Character, Car> getCars()
	{
		return cars;
	}



	// TODO
	//  Make a method that finds all neighbors of a given node

	// HashSet<GameNode> neighours;
	public HashSet<GameNode> getNeighbors()
	{
		// loop through all cars
		// find all available moves for each car
		// create a new GameNode for each available move
		
		return null;
	}

	
	// TODO make a method that calculates the h and f
	
	
	// TODO implement hashcode()




}










