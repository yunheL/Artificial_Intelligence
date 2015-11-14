import java.util.ArrayList;
import java.util.Collections;

public class Yunhe_Liu_AStar 
{		
	public ArrayList<SearchPoint> frontier;
	public ArrayList<SearchPoint> explored;
	
	ArrayList<Map.Point> exploredPoints = new ArrayList<Map.Point>();
	
	// TODO - add any extra member fields that you would like here
	public ArrayList<Map.Point> all;
	public int option;
	public Map.Point startPoint;
	public Map.Point endPoint;
	
	public SearchPoint starting;
	public SearchPoint ending;
	public SearchPoint exploring;

	public class SearchPoint implements Comparable<SearchPoint>
	{
		//TODO: possible bug here that the way using non-static method
		public Map.Point mapPoint;

		// TODO - add any extra member fields or methods that you would like here
		//public Map.Point start;
		//public Map.Point end;
		float x = mapPoint.x;
		float y = mapPoint.y;
		
		public SearchPoint(float x, float y)
		{
			this.x = x;
			this.y = y;
		}
		
		// TODO - implement this method to return the minimum cost
		// necessary to travel from the start point to here
		public float g() 
		{
			float currDis = 0;
			float xDis = 0;
			float yDis = 0;
			float squareDis = 0;
			
			xDis = (x - startPoint.x) * (x - startPoint.x);
			yDis = (y - startPoint.y) * (y - startPoint.y);
			
			squareDis = xDis + yDis;
			currDis = (float)(Math.sqrt((double)(squareDis)));
			return currDis;
		}	
		
		// TODO - implement this method to return the heuristic estimate
		// of the remaining cost, based on the H parameter passed from main:
		// 0: always estimate zero, 1: manhattan distance, 2: euclidean l2 distance
		public float h()
		{
			float heuristic = 0;
			if(option == 0)
			{
				//do nothing;
			}
			else if(option == 1)
			{
				float xDis = 0;
				float yDis = 0;
				
				xDis = (endPoint.x - x);
				yDis = (endPoint.y - y);
				heuristic = xDis + yDis;
			}
			else if(option == 2)
			{
				float xDis = 0;
				float yDis = 0;
				float squareDis = 0;
				
				xDis = (endPoint.x - x) * (endPoint.x - x);
				yDis = (endPoint.y - y) * (endPoint.y - y);
				
				squareDis = xDis + yDis;
				heuristic = (float)(Math.sqrt((double)(squareDis)));
			}
			else
			{
				System.out.println("Option Error!");
			}
			return heuristic;
		}
		
		// TODO - implement this method to return to final priority for this
		// point, which include the cost spent to reach it and the heuristic 
		// estimate of the remaining cost
		public float f()
		{
			float estimateSumCost = 0;
			estimateSumCost = g() + h();
			return estimateSumCost;
		}
		
		// TODO - override this compareTo method to help sort the points in 
		// your frontier from highest priority = lowest f(), and break ties
		// using whichever point has the lowest g()
		@Override
		public int compareTo(SearchPoint other)
		{
			//TODO: possible bug location, the new searchpoint may not be right
			SearchPoint curr = new SearchPoint(x, y);
			if(curr.f() > other.f())
			{
				return 1;
			}
			else if(curr.f() > other.f())
			{
				return -1;
			}
			else
			{
				if(curr.g() > other.g())
				{
					return 1;
				}
				else if(curr.g() < other.g())
				{
					return -1;
				}
				else
				{
					return 0;
				}
			}
		}
		
		//TODO: Read instruction again to debug. Bug here. I think the method
		//is meant to be implemented in a different way. Make sure to modify
		//the iscomplete function after modifying this function.
		
		// TODO - override this equals to help you check whether your ArrayLists
		// already contain a SearchPoint referencing a given Map.Point
		@Override
		public boolean equals(Object other)
		{
			int i = 0;
			
			//check Frontier list
			for(i = 0; i < frontier.size(); i++)
			{
				//TODO: possible error using "==". May want consider .equals();
				if(frontier.get(i) == other)
				{
					return true;
				}
			}
			
			//check explored list
			for(i = 0; i < explored.size(); i++)
			{
				//TODO: possible error using "==". May want consider .equals();
				if(explored.get(i) == other)
				{
					return true;
				}
			}
			return false;
		}		
	}
	
	// TODO - implement this constructor to initialize your member variables
	// and search, by adding the start point to your frontier.  The parameter
	// H indicates which heuristic you should use while searching:
	// 0: always estimate zero, 1: manhattan distance, 2: euclidean l2 distance
	public Yunhe_Liu_AStar(Map map, int H)
	{
		option = H;
		//float = start.mapPoint.x
		//float test = map.start.x;
		startPoint = map.start;
		endPoint = map.end;
		
		starting.x = startPoint.x;
		starting.y = startPoint.y;
		
		frontier = new ArrayList<SearchPoint>();
		frontier.add(starting);
		all = map.allPoints;
	}
	
	// TODO - implement this method to explore the single highest priority
	// and lowest f() SearchPoint from your frontier.  This method will be 
	// called multiple times from Main to help you visualize the search.
	// This method should not do anything, if your search is complete.
	public void exploreNextNode() 
	{
		if(isComplete())
		{
			return;
		}
		
		Collections.sort(frontier);
		exploring = frontier.get(0);
		frontier.remove(0);
		explored = new ArrayList<SearchPoint>();
		explored.add(exploring);
		
	}

	// TODO - implement this method to return an ArrayList of Map.Points
	// that represents the SearchPoints in your frontier.
	public ArrayList<Map.Point> getFrontier()
	{
		int i = 0;
		//TODO: initializing to null, possible bug
		Map.Point curr = null;
		for (i = 0; i < all.size(); i++)
		{
			if(all.get(i).x == exploring.x)
			{
				if(all.get(i).y == exploring.y)
				{
					curr = all.get(i);
				}
			}
		}
		
		if(curr != null)
		{
			return curr.neighbors;
		}
		else
		{
			System.out.println("getFrontier error");
			return null;
		}
		
	}
	
	// TODO - implement this method to return an ArrayList of Map.Points
	// that represents the SearchPoints that you have explored.
	public ArrayList<Map.Point> getExplored()
	{
		
		
		int i = 0;
		for (i = 0; i < explored.size(); i++)
		{
			int j = 0;
			for (j = 0; j < all.size(); j++)
			{
				if(all.get(j).x == explored.get(i).x)
				{
					if(all.get(j).y == explored.get(i).y)
					{
						exploredPoints.add(all.get(j));
					}
				}
			}
		}
		return exploredPoints;
	}

	//TODO: rethink the stopping condition
	// TODO - implement this method to return true only after a solution
	// has been found, or you have determined that no solution is possible.
	public boolean isComplete()
	{
		ending.x = endPoint.x;
		ending.y = endPoint.y;
		
		//TODO: Read instruction again to debug. Bug here
		if(exploring.equals(ending)|| getFrontier().size() == 0)
		{
			return true;
		}
			
		return false;
	}

	// TODO - implement this method to return an ArrayList of the Map.Points
	// that are along the path that you have found from the start to end.  
	// These points must be in the ArrayList in the order that they are 
	// traversed while moving along the path that you have found.
	
	//TODO: Re-implement
	public ArrayList<Map.Point> getSolution()
	{
		return exploredPoints;
	}	
}
