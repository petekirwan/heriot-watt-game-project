package assignment2;

public class CompetitorMime extends Competitor  
{           
	// The imaginary prop used by the mime competitor
	private String imaginaryProp; 
	
	// the class constructor	
	public CompetitorMime(String cNumber, Name cName, String cLevel, String cCity, String cProp) 
	{
		// calls the Competitor constructor with arguments given by its own constructor
		super(cNumber, cName, cLevel, cCity);
		// sets the mime's prop using the passed argument
		imaginaryProp = cProp; 
	}
	
	// returns the mime's imaginary prop
	public String getImaginaryProp() 
	{
		return imaginaryProp;
	}
	
	// gives overall as average of highest and lowest score
	public double getOverallScore() 
	{
		// creates a variable to store the overall score
		double overallScore = getTopScore() + getLowScore();
		// divide the sum of high and low scores by 2
		return overallScore / 2;
	}
	
	// sets highest possible score in the sport
	public double getHighestPossibleScore()
	{
		return 100;
	}
	
	// takes an argument and uses it to set competitor's imaginary prop	
	public void setImaginaryProp(String cProp) 
	{
		imaginaryProp = cProp;
	}
	
}	
