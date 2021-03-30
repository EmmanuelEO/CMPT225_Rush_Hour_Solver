package rushhour;

import java.util.*;

public class CustomPriorityQueue extends PriorityQueue<GameNode>
{
	private HashMap<Integer, GameNode> nodes;
	
	CustomPriorityQueue()
	{
		super();
		nodes = new HashMap<>();
	}
	
	@Override
	public boolean add(GameNode N)
	{
		nodes.put(N.hashCode(), N);
		return super.add(N);
	}
	
	public GameNode getNode(int hash)
	{
		return nodes.get(hash);
	}
	
	public boolean remove(GameNode N)
	{
		nodes.remove(N.hashCode(), N);
		return super.remove(N);
	}
}
