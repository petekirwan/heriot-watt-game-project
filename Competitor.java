package assignment2;

abstract public class Competitor 
{	
	// instance variable
	protected String competitorNumber;  
	protected Name competitorName;      
	protected String competitorLevel;   
	protected String competitorCity;    
	protected int[] scores;
	
	// creating a constant variable that can be accessed
	// without creation of object (static)
	protected static final int NUM_SCORES = 5;
	
	// the class constructor
	public Competitor(String cNumber, Name cName, String cLevel, String cCity) 
	{
		competitorNumber = cNumber; 
		competitorName = cName;
		competitorLevel = cLevel;
		competitorCity = cCity; 
		// sets the length for the scores int array to 5 
		scores = new int[NUM_SCORES]; 
	}
	
	// returns the competitor number
	public String getCompetitorNumber() 
	{
		return competitorNumber;
	}
	
	// returns the competitor name
	public Name getCompetitorName() 
	{
		return competitorName;
	}
	
	// returns the competitor level
	public String getCompetitorLevel() 
	{
		return competitorLevel;
	}
	
	// returns the competitor city
	public String getCompetitorCity() 
	{
		return competitorCity;
	}
	
	// takes a argument and uses it to sets a competitor's number
	public void setCompetitorNumber(String cNumber) 
	{
		competitorNumber = cNumber;
	}
	
	// takes an argument and uses it to sets a competitor's name
	public void setCompetitorName(Name cName) 
	{
		competitorName = cName;
	}
	
	// takes an argument and uses it to set competitor's level
	public void setCompetitorLevel(String cLevel) 
	{
		competitorLevel = cLevel;
	}
	
	// takes an argument and uses it to set competitor's city	
	public void setCompetitorCity(String cCity) 
	{
		competitorCity = cCity;
	}
	
	// returns a given competitor's scores
	public String getScores() 
	{
		// creates an empty variable that will hold the scores
		String allScores = "";
		// iterate through each item of the scores array
		for (int scoresIndex = 0; scoresIndex < scores.length; scoresIndex++) 
		{
			// add each score in to the allScores variable
			// along with comma to separate them
			allScores += scores[scoresIndex] + ", ";
		}
		// return the competitor's scores
		return allScores;
	}
	
	// gets the highest of a competitor's five scores
	public int getTopScore() 
	{
		// creates an int variable to use as running top score 
		int topScore = scores[0];
		// iterate through all the competitor's scores
		for (int scoresIndex = 1; scoresIndex < scores.length; scoresIndex++) 
		{
			// if a given score is higher than the running top score
			if (scores[scoresIndex] > topScore) 
			{
				// then make it the new running top score
				topScore = scores[scoresIndex];
			}
		}
		// return the competitor's top score
		return topScore;
	}
	
	// gets the sum of all the competitor's points
	public int getTotalScore() 
	{
		// creates a variables to hold the sum of the points
		int totalScore = scores[0];
		// iterate through all the competitor's scores
		for (int scoresIndex = 1; scoresIndex < scores.length; scoresIndex++) 
		{
			// add each score to total
			totalScore += scores[scoresIndex];
		}
		// return the sum of all the competitor's points
		return totalScore;
		
	}
	
	// get the competitor's average score
	public double getAverageScore() 
	{
		// divide the sum of a competitor's points
		// by the number of scores
		double averageScore = getTotalScore() / scores.length;
		// return the average score
		return averageScore;
	}
	
	// gets the lowest of the five scores
	public int getLowScore() 
	{
		// creates a variable to hold the lowest score
		int lowScore = scores[0];
		// iterate through all the scores
		for (int scoresIndex = 1; scoresIndex < scores.length; scoresIndex++) 
		{
			// if a given scores is the lowest seen so far
			if (scores[scoresIndex] < lowScore) 
			{
				// then store it in lowScore
				lowScore = scores[scoresIndex];
			}
		}
		// return the lowest score
		return lowScore;
	}
	
	// takes arguments and uses them to set all scores for a competitor
	// this can probably be better refactored as some sort of loop
	public void setScores(int score0, int score1, int score2, int score3, int score4) 
	{
		scores[0] = score0;
		scores[1] = score1;
		scores[2] = score2;
		scores[3] = score3;
		scores[4] = score4;
	}
	
	// calculates overall score with sport specific calculation
	public abstract double getOverallScore(); 
		
	// sets highest possible score for each sport
	public abstract double getHighestPossibleScore();
	
	// converts overall score to universal score to allow cross sport comparison
	public double getUniversalScore(double sportScore, double high)
	{
		double highestPossible = high;
		double universalScore2 = sportScore / highestPossible;
		double universalScore = universalScore2 * 100;
		return universalScore;
	}
	
	// returns the competitor's full details	
	public String getFullDetails() 
	{
		String fullDetails = "Competitor Number is " + getCompetitorNumber() + ", Competitor Name is " + getCompetitorName().getFullName() + " and Competitor City is " + getCompetitorCity() + ". " + getCompetitorName().getFirstName() + " is level " + getCompetitorLevel() + " has received these scores : " + getScores() + " This gives them a universal score percentage of " + String.format("%.1f", getUniversalScore(getOverallScore(), getHighestPossibleScore())) + "%."; 
		return fullDetails;
	}
	
	// returns the competitor's short details
	public String getShortDetails() 
	{
		String shortDetails = "CN " + getCompetitorNumber() + " (" + getCompetitorName().getInitials() + ") " + "has overall score " + String.format("%.1f", getOverallScore()) +  ".";
		return shortDetails;
	}	
}
