package rushhour;

import java.awt.Point;
import java.lang.Math.*;
import java.util.*;

public class GameNode
{
	private GameNode parent;
	private boolean checked = false;
	
	private HashMap<Character, Car> cars;
	
	public GameNode()
	{
		cars = new HashMap<Character, Car>();
	}
}

