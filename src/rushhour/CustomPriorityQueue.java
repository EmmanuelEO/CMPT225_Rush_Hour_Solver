package rushhour;

import java.util.*;

public class CustomPriorityQueue extends PriorityQueue<GameNode>
{
	private HashMap<Integer, GameNode> nodes;
	
	public CustomPriorityQueue()
	{
			super(new Comparator<GameNode>() {
				@Override
				public int compare(GameNode o1, GameNode o2) {
					return o1.getF() - o2.getF();
				}
			});
		
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
	
	public void update(GameNode N)
	{
		// The node that get(N.hashCode()) returns may have a different f than N
		nodes.remove(N.hashCode());
		nodes.put(N.hashCode(), N);
		
		// All this does is update the position of N in the priority queue
		super.remove(N); // O(n)
		super.add(N); // O(log(n))
	}
	
	@Override
	public GameNode poll()
	{
		GameNode N = super.poll();
		nodes.remove(N.hashCode(), N);
		return N;
	}

	@Override
	public boolean contains(Object o)
	{
		GameNode N = (GameNode)o;
		return nodes.containsKey(N.hashCode());		
	}
	
}
