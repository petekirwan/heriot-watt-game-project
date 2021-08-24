package assignment2;
/**
 * Assignment 2 Software Engineering Foundations F21SF
 * Competitor List class
 * This is a class that can hold competitors in an ArrayList
 * @author Peter Kirwan
 */

// imports the File class from io
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class CompetitorList 
{
	// holds a list of competitor objects
	private ArrayList<Competitor> competitorList;
	
	/** Constructor: creates a CompetitiorList object made up of generic Competitor objects
	 */
	public CompetitorList()
	{
		competitorList = new ArrayList<Competitor> ();
	}

	// structure taken from add() in StudentList.java
	/** Adds a competitor specified in parameters to the ArrayList
	 *  @param c a competitor
	 */
	public void add(Competitor c) 
	{
		competitorList.add(c);
	}
	
	// structure taken readFile() in StudentList.java
	/** reads each line of file specified in parameters and passes to process()
	 *  @param filename an input file to be read
	 */
	public void readFile(String filename) 
	{
		try 
		{ 
			// creates a new File object called k and passes it filename argument
			File k = new File (filename);
			// create a new scanner object
			Scanner scanner = new Scanner(k);
			// iterate through each line in filename
			while (scanner.hasNextLine()) 
			{
				// store each line in a variable
				String inputLine = scanner.nextLine();
				// if a line isn't empty
				if (inputLine.length() != 0) 
				{
					// pass the line to processLine()
					processLine(inputLine, k.getName());
				} 
			}
			// close the scanner to prevent leaks
			scanner.close();
		} 
		
		// if there is a filenotfoundexception stop with system exit
		catch (FileNotFoundException fnf) 
		{
			JOptionPane.showMessageDialog(null, "Seems like your file can't be found or has inadequate permissions. Please check the path, filename and permissions");
			fnf.printStackTrace();
			// exits with success message
			System.exit(0);
		}
	}
	
	// influenced by method getMaxYear() from Studentlist
	/** Finds highest universal score and returns competitor's full details
	 *  NOTE: Universal scores are normed overall scores that allow cross sport comparison
	 *  @return Full details for competitor with the highest universal score
	 */
	public String getCompetitorWithHighestScore() 
	{
		// create a placeholder for the result
		Competitor competitorWithHighestScore = null;
		// a variable to hold the highest score so far to compare against
		double highestScore = 0.0;
		// iterate through each competitor in competitorlist
		for (Competitor c : competitorList)
		{ 
			// if a competitor's universal score is the highest so far
			if (c.getUniversalScore(c.getOverallScore(), c.getHighestPossibleScore()) > highestScore) 
			{
				//store their universal score as highest so far
				highestScore = c.getUniversalScore(c.getOverallScore(), c.getHighestPossibleScore());
				// set them as the competitor with the highest universal score
				competitorWithHighestScore = c;
			}
		}
		// return the full details of the competitor with highest universal score	
		return competitorWithHighestScore.getFullDetails();
	}
	
	/** returns the sum of all points given out in the contest
	 * @return the sum of all points given
	 */
	public int getTotalOfAllScores() 
	{
		// This will hold the sum of all points.
		int totalScore = 0;
		// iterate through each competitor in the arraylist
		for (Competitor c : competitorList) 
		{
			// add each competitors total to the overall total
			totalScore += c.getTotalScore();	
		}	
		// return the sum of all points given out
		return totalScore;
	}
	
	/** gets the averages of all scores in the contest
	 * 
	 * @return average of all scores
	 */
	public double getAverageOfAllScores() 
	{
		// get number of Scores entered by multiplying the number of competitors by 5 
		double numberOfScores = competitorList.size() * 5;
		// divides total of all scores by number of scores
		double averageOfAllScores =  getTotalOfAllScores() / numberOfScores;
		// returns the average of all scores
		return averageOfAllScores;
	}
	
	// structure taken from getMaxYear() in Studentlist
	/** returns the highest score in the contest
	 * @return the highest score in the contest
	 */
	public int getMaxScore() 
	{ 
		// this will hold the highest score found so far as we go
		int maxScore = 0;
		// loops through all competitors in the ArrayList
		for (Competitor c : competitorList) 
		{
			// temporarily store each players top score in an int variable
			int sr = c.getTopScore();
			// if a players top score is highest so far in the loop
			if (c.getTopScore() > maxScore) 
			{
			// set maxScore to that player's high score
				maxScore = sr;
			}
		}
		// return the highest score in the contest
		return maxScore;
	}
	
	// structure based on getMaxYear() in Studentlist
	/** returns the lowest score by any player
	 *
	 * @return the lowest score by any player
	 */
	public int getMinScore() 
	{ 
		int minScore = 5;
		// loops through the ArrayList
		for (Competitor c : competitorList) 
		{
			// look at lowScore of each player
			int sr = c.getLowScore();
			// if players lowScore lowest so far
			if (sr < minScore) 
			{
			// set minScore to that player's high score
				minScore = sr;
			}
		}
		// return the lowest score by any player
		return minScore;
	}
	
	// structure from class program Studentlist
	/** returns a table of all competitors
	 *  
	 * @return table of all competitors
	 */
	public String getTableOfCompetitors() 
	{
		// These are the table headings. The exact spacing is important to line up with headings
		String tableReport = "NUMBER INITIALS OVERALLSCORE\n";
		// iterate through each competitors in the Arraylist
		for (Competitor c : competitorList) 
		{
			// alert user if they input a name that is too long
			if (c.getCompetitorName().getFullName().length() > 25) 
			{
				JOptionPane.showMessageDialog(null, "One of the inputted names is too long. Please use names with less than 25 characters including spaces. The appearance of your report may be affected if you do not.");
			} 			
			// give short details
			tableReport += String.format("%-6s", c.getShortDetails());
			// go to a new line before receiving next competitor details
			tableReport += "\n";
		}
		// return a table with all competitor details one competitor to a line
		return tableReport;
	}
	
	/** takes lines specified in parameters. Splits and and stores them in an array
	 * @param line
	 */
	private void processLine(String line, String filename) 
	{
		try 
		{
			//sets the type of competitor being entered
			// so input can be treated differently and scores normalised
			String competitorType = null;
			if (filename.contains("Guitar"))
			{
				competitorType = "guitarist";
			}
			else if (filename.contains("Dance"))
			{
				competitorType = "dancer";				
			}
			else if (filename.contains("Mime"))
			{
				competitorType = "mime";
			}
			// splits the line and stores resulting strings in an array parts
			String parts [] = line.split(",");
			// and assigns the competitor number to parts
			String compNumber = parts[0];
			// remove spaces from compNumber which should be one number
			compNumber = compNumber.trim();
			// assigns competitor name to parts
			// no trim to allow 'first middle last'
			Name name = new Name(parts[1]);
			// assign competitor level to parts
			String level = parts[2];
			// remove spaces from level which should be one number
			level = level.trim();
			// assign competitor city to parts
			// no trim used to allow cities like 'New York'
			String city = parts[3];
			// these subclass specific variables are created for use by constructors
			String song = null;
			String style = null;
			String prop = null;
			// depending on competitor type a different thing is stored for part[4]
			if (competitorType == "dancer")
			{
				song = parts[4];
			}
			else if (competitorType == "guitarist")
			{
				style = parts[4];
			}
			else if (competitorType == "mime")
			{
				prop = parts[4];
			}
			// get the scores out of the array and store as Strings
			String scoreString1 = parts[5];
			String scoreString2 = parts[6];
			String scoreString3 = parts[7];
			String scoreString4 = parts[8];
			String scoreString5 = parts[9];
			// change the scores to ints
			int score1 = Integer.parseInt(scoreString1);
			int score2 = Integer.parseInt(scoreString2);
			int score3 = Integer.parseInt(scoreString3);
			int score4 = Integer.parseInt(scoreString4);
			int score5 = Integer.parseInt(scoreString5);	
			//create Competitor objects according to the type of competitor uploaded
			if (competitorType == "dancer")
			{
				Competitor c = new CompetitorDance(compNumber,name, level, city, song);
				this.add(c);
				// add scores in to the list
				c.setScores(score1, score2, score3, score4, score5);
			}
			else if (competitorType == "guitarist")
			{
				Competitor c = new CompetitorAirGuitar(compNumber,name, level, city, style);
				this.add(c);
				// add scores in to the list
				c.setScores(score1, score2, score3, score4, score5);
			}
			else if (competitorType == "mime")
			{
				Competitor c = new CompetitorMime(compNumber,name, level, city, prop);
				this.add(c);
				// add scores in to the list
				c.setScores(score1, score2, score3, score4, score5);
			}
		} 
		// taken from processLine() in StudentList
		// this catches trying to convert a String to an integer
		catch (NumberFormatException nfe) 
		{
				String nfeError = "Number conversion error in '" + line + "'  - " + nfe.getMessage();
				nfe.printStackTrace();
				JOptionPane.showMessageDialog(null, nfeError);
		}
		// taken from processLine() in StudentList
		//this catches missing items if only one or two items
		//other omissions will result in other errors
		catch (ArrayIndexOutOfBoundsException air) 
		{
			String airError = "Not enough items in  : '" + line
			                        + "' index position : " + air.getMessage();
			air.printStackTrace();
			JOptionPane.showMessageDialog(null, airError);
		}
		// catches if input file line has too many commas or a null name and exits gracefully
		catch(StringIndexOutOfBoundsException sio) 
		{
			String sioError = "There's a problem with one or more of the fields you entered. You may have too many commas, have used alternative punctuation or a name may be null";
			sio.printStackTrace();
			JOptionPane.showMessageDialog(null, sioError);
			System.exit(0);
		}

		
	}
	// structure taken from StudentList
	/** writes contents of the report to file specified in parameters
	 * 
	 * @param filename a file to write to
	 * @param report the report that has been generated
	 */
	public void writeToFile(String filename, String report) 
	{
		FileWriter fw;
		// try to run this. If an exception occurs go to catch
		try 
		{
			// new FileWriter object created and assigned to fw
			fw = new FileWriter(filename);
			// write this String to the file
			fw.write(report);
				// make sure all data is written to file and none left buffered in memory
				fw.flush();
				// close the FileWriter object to prevent memory leaks 
				fw.close();		
		} 
		// if there is a file not found exception send user helpful suggestion
		catch (FileNotFoundException fnf) 
		{
			// send appropriate message
			String fnfError = "your file " + filename + "was not found. Please check path and filename";
			JOptionPane.showMessageDialog(null, fnfError);
			fnf.printStackTrace();
			// exit the program and indicate successful termination
			System.exit(0);
		}
		// If there is an IO Exception printer give user helpful suggestion
		catch (IOException ioe) 
		{
			String ioeError = "IOException: " + filename + "either exists but is a directory, does not exist or has another issue. Check file";
			JOptionPane.showMessageDialog(null, ioeError);
			ioe.printStackTrace();
			// exit the program and indicate abnormal termination
			System.exit(1);
		} 
		// If filename is left null suggest it might be null in more helpful language
		// this is one of my own exception catches
		catch (NullPointerException npe) 
		{
			String npeError = "Null Point Exception. " + filename + " may be set to null. Please check it.";
			JOptionPane.showMessageDialog(null, npeError);
			npe.printStackTrace();
			System.exit(0);
		}
	}	
	
	/** collects together everything for the report and returns it
	 * @return the report
	 */
	public String report() 
	{
		// assigns to report getTableOfCompetitors()
		String report = getTableOfCompetitors();
		// go to a new line
		report += "\n";
		// add all of these to the report
		report += "STATISTICAL ANALYSIS OF THE EVENT \n";
		try 
		{
			report += "The person with the highest universal score %: " + getCompetitorWithHighestScore() + "\n";
		}
		catch (ArithmeticException ae) 
		{
			String aeError = "Something went wrong with the calculations. Please check your numbers are valid. Your report will be affected.";
			JOptionPane.showMessageDialog(null, aeError);
			ae.printStackTrace();
			System.exit(0);
		}
		// return report with all its collected Strings
		return report;
	}	
	
	/** checks if a competitor number specified in parameters exists and returns short details
	 * 
	 * @param cNumber a competitor number input by user
	 * @return whether the competitor number is valid
	 */
	public String getShortDetailsFromList(String cNumber) 
	{
		// creating this to hold the result of the check
		String checkIDResult = "";
		// iterate through the ArrayList
		for (int i = 0; i < competitorList.size(); i++) 
		{
			// if the the number passed as argument (cNumber) matches one on file
			if (competitorList.get(i).getCompetitorNumber().equals(cNumber)) 
			{
				checkIDResult = "Yes " + cNumber + " is a valid competitor number and their short details are: " + competitorList.get(i).getShortDetails();
				// exit the loop having found a matching competitor number
				break;
			} 
			// if the number given is not a valid competitor number
			else 
			{
				checkIDResult = "Sorry " + cNumber + " is not a valid competitor number";
			}	
		}
		// return the short details
		return checkIDResult;
	}
	
	/** checks if a competitor number specified in parameters exists and returns full details
	 * 
	 * @param cNumber a competitor number input by user
	 * @return whether the competitor number is valid
	 */
	// this and the method for checking short details are so similar there's a lot of duplication
	// ideally would refactor to try and combine into one method 
	public String checkFullDetails(String cNumber) 
	{
		// creating this to hold the result of the check
		String fullDetails = "";
		// iterate through the ArrayList
		for (int i = 0; i < competitorList.size(); i++) 
		{
			// if the the number passed as argument (cNumber) matches one on file
			if (competitorList.get(i).getCompetitorNumber().equals(cNumber)) 
			{
				fullDetails = "Yes " + cNumber + " is a valid competitor number and their full details are: " + competitorList.get(i).getFullDetails();
				// exit the loop having found a matching competitor number
				break;
			} 
			// if the number given is not a valid competitor number
			else 
			{
				fullDetails = "Sorry " + cNumber + " is not a valid competitor number";
			}	
		}
		// return the competitor's full details
		return fullDetails;
	}
	
	/** Allows scores to be set for any competitor using arguments
	 * 
	 * @param cNumber a competitor number input by user, int s0-s4 the scores
	 */
	public void setScoresFromList(String cNumber, int s0, int s1, int s2, int s3, int s4) 
	{
		// iterate through the ArrayList
		for (int i = 0; i < competitorList.size(); i++) 
		{
			// if the the number passed as argument (cNumber) matches one on file
			if (competitorList.get(i).getCompetitorNumber().equals(cNumber)) 
			{
				// set the scores to those given as arguments in the method
				competitorList.get(i).setScores(s0, s1, s2, s3, s4);
				// exit the loop having found a matching competitor number and changed scores
				break;
			} 
		}
	}
	
	/** Gets scores from the list for a specified competitor
	 * 
	 * @param cNumber a competitor number input by user
	 * @return scores
	 */
	public String getScoresFromList(String cNumber) 
	{
		// creating this to hold the result of the check
		String scores = "";
		// iterate through the ArrayList
		for (int i = 0; i < competitorList.size(); i++) 
		{
			// if the the number passed as argument (cNumber) matches one on file
			if (competitorList.get(i).getCompetitorNumber().equals(cNumber)) 
			{
				scores = "Yes " + cNumber + " is a valid competitor number and their scores are: " + competitorList.get(i).getScores();
				// exit the loop having found a matching competitor number
				break;
			} 
			// if the number given is not a valid competitor number
			else 
			{
				scores = "Sorry " + cNumber + " is not a valid competitor number";
			}	
		}
		// return all five scores
		return scores;
	}
	
	/** Gets the overall score for a specified competitor
	 * 
	 * @param cNumber a competitor number input by user
	 * @return overall score for that competitor
	 */
	public String checkOverallScore(String cNumber) 
	{
		// creating this to hold the result of the check
		String overallScore = "";
		// iterate through the ArrayList
		for (int i = 0; i < competitorList.size(); i++) 
		{
			// if the the number passed as argument (cNumber) matches one on file
			if (competitorList.get(i).getCompetitorNumber().equals(cNumber)) 
			{
				// get that competitors overall score
				overallScore = "Overall score: " + competitorList.get(i).getOverallScore();
				// exit the loop having found a matching competitor number
				break;
			} 
		}
		// return the competitor's overall score
		return overallScore;
	}
	
	/**
	 * Adapted from StaffList
     * @return All the competitor details
     */
    public String listDetails()
    {
    	// creates new Stringbuffer to hold all data
    	StringBuffer allEntries = new StringBuffer();
        // iterates through all Competitors in competitorList
    	for(Competitor details : competitorList) {
            // adds their details to the StringBuffer
    		allEntries.append(details.getFullDetails());
            // adds two new lines for readability
    		allEntries.append('\n');
    		allEntries.append('\n');
        }
        // return all competitor details as a string
    	return allEntries.toString();
    }
    
	/**
	 * Adapted from StaffList
     * @return All the Air Guitar competitor details
     */
    // this and the listMimeDetails and listDancerDetails should be refactored to be one method that takes an argument
    public String listGuitaristDetails()
    {
    	StringBuffer allEntries = new StringBuffer();
        for(Competitor details : competitorList) 
        {
        	// research suggests instanceof may be problematic but using due to time constraints
        	// if the competitor is a Guitarist
            if (details instanceof CompetitorAirGuitar)
            {
        	allEntries.append(details.getFullDetails());
        	// next line run twice to leave space between list items for readability
            allEntries.append('\n');
            allEntries.append('\n');
            }
        }
     // return full details of all Air Guitarists
        return allEntries.toString();
    }

	/**
	 * Adapted from StaffList
     * @return All the Dance competitor details
     */
    // this and the listGuitaristDetails and listMimeDetails should be refactored to be one method that takes an argument
    public String listDancerDetails()
    {
    	StringBuffer allEntries = new StringBuffer();
        for(Competitor details : competitorList) 
        {
        	// research suggests instanceof may be problematic but using due to time constraints
        	// if the competitor is a Dancer
            if (details instanceof CompetitorDance)
            {
        	allEntries.append(details.getFullDetails());
        	// next line run twice to leave space between list items for readability
            allEntries.append('\n');
            allEntries.append('\n');
            }
        }
     // return full details of all Dancers
        return allEntries.toString();
    }
    
	/**
	 * Adapted from StaffList
     * @return All the Mime competitor details
     */
    // this and the listGuitaristDetails and listDancerDetails should be refactored to be one method that takes an argument
    public String listMimeDetails()
    {
    	StringBuffer allEntries = new StringBuffer();
        for(Competitor details : competitorList) 
        {
        	// research suggests instanceof may be problematic but using due to time constraints
        	// if the competitor is a Mime
            if (details instanceof CompetitorMime)
            {
        	allEntries.append(details.getFullDetails());
            // next line run twice to leave space between list items for readability
        	allEntries.append('\n');
            allEntries.append('\n');
            }
        }
        // return full details of all Mimes
        return allEntries.toString();
    } 
}