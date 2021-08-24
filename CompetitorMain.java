package assignment2;

public class CompetitorMain 
{	
	
	public static void main(String[] args) 
	{
		// create a new object cm of the class CompetitorManager
		// CompetitorManager methods separate from main for modularity
		CompetitorManager cm = new CompetitorManager();
		// kickstarts cm's run method which will request the report etc
		cm.run();
	}
}
