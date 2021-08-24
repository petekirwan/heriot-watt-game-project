package assignment2;

import javax.swing.*;

public class CompetitorManager 
{
	
	// based on StudentManager.java
	// declares an instance variable of the CompetitorList type
	private CompetitorList allCompetitors; 
	
	// kick starts production of the report
	public void run() 
	{
		JOptionPane.showMessageDialog(null, "We are generating the report from the air guitar/dance/mime championships. "
				+ "\n" + "Each Player gives five performances which are rated" 
				+ "\n" + "out of 5 for guitarists"
				+ "\n" + "out of 10 for dancers"
				+ "\n" + "out of 100 for mimes"
				+ "\n" + "Each players overall score is normed to a Universal Score %"
				+ "\n" + "Universal Score: The % actual overall is of highest possible overall score in that sport"
				+ "\n" + "e.g. Mime's overall of 40 would be a 40/100 so 40% universal"
				+ "\n" + "This allows comparison of competitors. Higher is better");
		// creates a new CompetitorList object called allCompetitors
		allCompetitors = new CompetitorList();
		// take in air guitar competitors as input from a file
		allCompetitors.readFile("CompetitorInputAirGuitar.csv");
		// take in dance competitors as input from a file
		allCompetitors.readFile("CompetitorInputDance.csv");
		// take in mime competitors as input from a file
		allCompetitors.readFile("CompetitorInputMime.csv");
		// GUI for viewing and altering competitor data
		// The Eclipse error flag here has been noted and is not considered a problem
		CompetitorGUI cframe = new CompetitorGUI(allCompetitors);		
		// This code block from assignment 1 is 
		// commented out to avoid distracting from assessment of assignment 2 
		/*		
		// pop up box prompts user for a competitor number. More user friendly than scanner.nextLine()
		String queryCompetitorNumber = JOptionPane.showInputDialog(null, "Would you like to check for a particular competitor? If so please enter a valid competitor number. If not press cancel.");
		// passes the input to the CheckID function which looks it up and puts return in dialogue box
		// these blocks for taking queries can probably be combined into one in some way
		try 
		{
			if (!queryCompetitorNumber.equals(null)) 
			{
				JOptionPane.showMessageDialog(null, allCompetitors.checkId(queryCompetitorNumber));
			}
		} 
		catch (NullPointerException npe) 
		{
			// do nothing. The null pointer exception is just the user pressing cancel.
		}
		String anotherQuery = "";
		try 
		{
			while (!anotherQuery.equals(null) && !queryCompetitorNumber.equals(null)) 
			{
				anotherQuery = JOptionPane.showInputDialog(null, "Would you like to check another competitor? If so please enter a valid competitor number. If not press cancel");
				if (!anotherQuery.equals(null)) 
				{
					JOptionPane.showMessageDialog(null, allCompetitors.checkId(anotherQuery));			
				}
			}
		}
		catch (NullPointerException npe) 
		{	
		// do nothing. The null pointer exception is just the user pressing cancel 
		}
		JOptionPane.showMessageDialog(null, "Enjoy your report. Thank you");
	*/
	}
	
}