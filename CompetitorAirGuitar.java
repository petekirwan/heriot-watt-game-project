package assignment2;

public class CompetitorAirGuitar extends Competitor
{	
	// the musical style of song air-guitared
	private String musicalStyle;
	
	// the class constructor	
	public CompetitorAirGuitar(String cNumber, Name cName, String cLevel, String cCity, String cMusic) 
	{
		// calls the Competitor constructor with arguments given by its own constructor
		super(cNumber, cName, cLevel, cCity);
		// sets the musicalStyle to the passed argument
		musicalStyle = cMusic; 
	}
	
	// returns the competitor musical style
	public String getMusicalStyle() 
	{
		return musicalStyle;
	}

	// removes highest and lowest score
	// and gives an average of the remaining three scores
	public double getOverallScore() 
	{
		// creates a variable to store the overall score
		double overallScore = getTotalScore();
		overallScore -= getTopScore();
		// subtract their lowest score
		overallScore -= getLowScore();
		// divide the three remaining scores by 3
		return overallScore / 3;
	}
	
	// sets highest possible score in the sport
	public double getHighestPossibleScore()
	{
		return 5;
	}
	
	// takes an argument and uses it to set competitor's musical style	
	public void setMusicalStyle(String cStyle) 
	{
		musicalStyle = cStyle;
	}
		
}	