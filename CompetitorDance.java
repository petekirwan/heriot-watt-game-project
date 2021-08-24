package assignment2;

public class CompetitorDance extends Competitor
{
	// The song the competitor Dances to
	private String routineSong;            
	
	// the class constructor	
	public CompetitorDance(String cNumber, Name cName, String cLevel, String cCity, String cSong) 
	{
		// calls the Competitor constructor with arguments given by its own constructor
		super(cNumber, cName, cLevel, cCity);
		// sets the dancer's routine song to the passed argument
		routineSong = cSong; 
	}
	
	// returns the competitor's routine song
	public String getRoutineSong() 
	{
		return routineSong;
	}
	
	// removes lowest score
	// and gives an average of the remaining four scores
	public double getOverallScore() 
	{
		// creates a variable to store the overall score
		double overallScore = getTotalScore();
		overallScore -= getLowScore();
		// divide the three remaining scores by 4
		return overallScore / 4;
	}
	
	// sets highest possible score in the sport
	public double getHighestPossibleScore()
	{
		return 10;
	}
	
	// takes an argument and uses it to set competitor's routine song
	public void setRoutineSong(String cSong) 
	{
		routineSong = cSong;
	}
	
}	
