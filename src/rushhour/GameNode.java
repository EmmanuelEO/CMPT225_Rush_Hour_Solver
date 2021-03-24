package rushhour;

import java.awt.Point;
import java.lang.Math.*;
import java.util.*;

public class GameNode
{
	private GameNode parent;
	private boolean checked = false;
	
	private ArrayList<Car> cars;
	
	public GameNode()
	{
		cars = new ArrayList<Car>();
	}
}

