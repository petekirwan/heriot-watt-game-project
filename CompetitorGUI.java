package assignment2;

//import all the GUI classes
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
* Basic GUI for CompetitorList application
* 
*/
// unsure why eclipse is flagging this as needing serialVersionUID but it runs
// and don't want to add code i don't understand
// quick research did not help
public class CompetitorGUI extends JFrame implements ActionListener
{
	// instance variables
	private CompetitorList competitorList; 
	JPanel panel;
	JLabel answerLabel;
	JButton fullDetailsButton;
	JButton shortDetailsButton;;
	JButton getScoresButton;
	JButton setScoresButton;
	JButton competitorDetailsButton;
	JButton guitaristDetailsButton;
	JButton dancerDetailsButton;
	JButton mimeDetailsButton;
	JButton closeButton;
	JTextField scoreField1;
	JTextField scoreField2;
	JTextField scoreField3;
	JTextField scoreField4;
	JTextField scoreField5;
	JTextField competitorSearch;
	JTextArea displayList;
	JScrollPane competitorScrollList;
	
	/** The constructor that creates a GUI
	 * 
	 */
	public CompetitorGUI(CompetitorList list) 
	{
		// takes the list given as argument and stores it
		this.competitorList = list;
		panel = new JPanel();
		// create the panel areas
		setupNorthPanel();
		setupSouthPanel();
		setupCenterPanel();
		setupWestPanel();
		setupEastPanel();
		// what will happen when the frame is closed
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set the frame title
		super.setTitle("View/Alter Competitor Details");
		// set the frame size as needed by subcomponents
		super.pack();
		// display the frame
		super.setVisible(true);	
	}
	
	/** triggers actions when buttons are pressed
	 * @return void
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		// Should be refactored to be case/switch rather than else if
		// not refactored due to time constraints
		// if the shortDetails button is clicked
		if (e.getSource() == shortDetailsButton) {
			// print out the short details of the competitor requested
			displayList.setText(competitorList.getShortDetailsFromList(competitorSearch.getText()));
		}
		// if the fullDetails button is clicked
		else if (e.getSource() == fullDetailsButton) {
			// print out the full details of the competitor requested
			displayList.setText(competitorList.checkFullDetails(competitorSearch.getText()));
		}	
		// if the get scores button is clicked
		else if (e.getSource() == getScoresButton) 
		{
			// display the scores in the large text area
			displayList.setText(competitorList.getScoresFromList(competitorSearch.getText()));	
		}
		// if the set scores button is clicked
		else if (e.getSource() == setScoresButton)
		{
			if (!scoreField1.getText().isEmpty() && !scoreField2.getText().isEmpty() && !scoreField3.getText().isEmpty() && !scoreField4.getText().isEmpty() && !scoreField5.getText().isEmpty())
			{
			// set the scores to those entered in text field boxes
			competitorList.setScoresFromList(competitorSearch.getText(), Integer.parseInt(scoreField1.getText()), 
					Integer.parseInt(scoreField2.getText()), Integer.parseInt(scoreField3.getText()), 
					Integer.parseInt(scoreField4.getText()), Integer.parseInt(scoreField5.getText()));
			// display the new scores in the large text area
			displayList.setText(competitorList.getScoresFromList(competitorSearch.getText()));
			// show the user the new overall score for that competitor
			JOptionPane.showMessageDialog(null, competitorList.checkOverallScore(competitorSearch.getText()));
			}
			else
			{
				JOptionPane.showMessageDialog(null, "You must enter a number in the scores boxes");
			}
		}
		// if the competitorDetailsButton is clicked
		else if (e.getSource() == competitorDetailsButton)
		{
			// display all competitor details in the large text area
			displayList.setText(competitorList.listDetails());
		}
		// if the air guitarist button is clicked
		else if (e.getSource() == guitaristDetailsButton)
		{
			// display details of all air guitarists in the large text area
			displayList.setText(competitorList.listGuitaristDetails());
		}
		// if the dancer button is clicked
		else if (e.getSource() == dancerDetailsButton)
		{
			// display details of all dancers in the large text area
			displayList.setText(competitorList.listDancerDetails());
		}
		// if the mime button is clicked
		else if (e.getSource() == mimeDetailsButton)
		{
			// display details of all mimes in the large text area
			displayList.setText(competitorList.listMimeDetails());
		}
		// if the close button is clicked
		else if (e.getSource() == closeButton)
		{
			// write the return value of Report() to regionalreport.txt
			competitorList.writeToFile("regionalreport.txt", competitorList.report());
			// confirm report writing to file and tell user program will close
			JOptionPane.showMessageDialog(null, "Your report has written to regionalreport.txt. This program will now close.");
			// end program
			System.exit(0);
		}		
	}
	
	/** sets up the north area of the main JPanel
	 * @return void
	 */
    private void setupNorthPanel() 
    {
        // create new Panel with GridLayout and 1 row, three columns
    	JPanel northPanel = new JPanel(new GridLayout(1,3));
		// create new button
    	shortDetailsButton = new JButton("Get Short Details");
		// listen for clicks on the short details button
		shortDetailsButton.addActionListener(this);
    	// add short details button to the top
    	northPanel.add(shortDetailsButton);
		// create new button
    	fullDetailsButton = new JButton("Get Full Details");
		// listen for clicks on the full details button
		fullDetailsButton.addActionListener(this);
		// add full details button to the top
		northPanel.add(fullDetailsButton);
		// create getScoresButton
    	getScoresButton = new JButton("Get Scores");
		// listen for clicks on the get Scores button
		getScoresButton.addActionListener(this);    
    	// add getScores button button to the center
		northPanel.add(getScoresButton);
        // add a close button
		closeButton = new JButton("Close");
		// listen for clicks on the close button
		closeButton.addActionListener(this);
		// set button color
		closeButton.setBackground(Color.red);
		// add close button to the top
		northPanel.add(closeButton);
		// add north area to the main JPanel
        this.add(northPanel, BorderLayout.NORTH);
    }
    
    /** sets up the center area of the main JPanel
	 * @return void
	 */
    private void setupCenterPanel() 
    {
    	// create new panel with GridLayout: four rows one column
    	JPanel centerPanel = new JPanel(new GridLayout(4,1)); 	    	  	
    	// new button
    	competitorDetailsButton = new JButton("Get Details of All Competitors");
    	// listen for clicks on this button
    	competitorDetailsButton.addActionListener(this); 
    	// add button to center panel
    	centerPanel.add(competitorDetailsButton);
    	// new button
    	guitaristDetailsButton = new JButton("Get Details of All Air Guitarists");
    	// listen for clicks on this button
    	guitaristDetailsButton.addActionListener(this); 
    	// add button to center panel
    	centerPanel.add(guitaristDetailsButton);    	
    	// new button
    	dancerDetailsButton = new JButton("Get Details of All Dancers");
    	// listen for clicks on this button
    	dancerDetailsButton.addActionListener(this); 
    	// add button to center panel
    	centerPanel.add(dancerDetailsButton);    	    	
    	// new button
    	mimeDetailsButton = new JButton("Get Details of All Mimes");
    	// listen for clicks on this button
    	mimeDetailsButton.addActionListener(this); 
    	// add button to center panel
    	centerPanel.add(mimeDetailsButton); 
        // put this panel in the main JPanel center area
    	this.add(centerPanel, BorderLayout.CENTER);
    }
    
    /** sets up the east area of the main JPanel
 	 * @return void
 	 */
    private void setupEastPanel() 
    {
    	// create new panel with Gridlayout and 6 rows one column
    	JPanel EastPanel = new JPanel(new GridLayout(6,1));
    	// create setScoresButton
    	setScoresButton = new JButton("Set Scores");
		// listen for clicks on the get Scores button
		setScoresButton.addActionListener(this); 
    	// add getScores button to panel
		EastPanel.add(setScoresButton);	
		scoreField1 = new JTextField(5);
		scoreField2 = new JTextField(5);
		scoreField3 = new JTextField(5);
		scoreField4 = new JTextField(5);
		scoreField5 = new JTextField(5);
		EastPanel.add(scoreField1);
		EastPanel.add(scoreField2);
		EastPanel.add(scoreField3);
		EastPanel.add(scoreField4);
		EastPanel.add(scoreField5);
		// put this panel at the east area of the main JPanel
    	this.add(EastPanel, BorderLayout.EAST);
    }
    
    /** sets up the west area of the main JPanel
 	 * @return void
 	 */
    private void setupWestPanel() 
    {    	
        // create new JPanel
    	JPanel westPanel = new JPanel();
    	// create a new text area that will hold all output
    	displayList = new JTextArea(50,50);
    	// do not allow user to edit the text area
    	displayList.setEditable(false);
    	// set initial text for the text area
    	displayList.setText("Please enter a valid competitor number below and then use the buttons to view or alter data");
    	// wrap text to limit user horizontal scrolling
    	displayList.setLineWrap(true);
    	// create a scrollpane for longer entries
    	competitorScrollList = new JScrollPane(displayList);
    	// add scrollpane to the panel
    	westPanel.add(competitorScrollList);  
    	// add this panel as the west area of the main JPanel
    	this.add(westPanel, BorderLayout.WEST); 
    }
    
    /** sets up the south area of the main JPanel
 	 * @return void
 	 */
    private void setupSouthPanel() 
    {    	
        // create new panel with Gridlayout and 2 columns, one row
    	JPanel southPanel = new JPanel(new GridLayout(2,1));
        // add a label requesting input of the competitor number
        southPanel.add(new JLabel("Enter a competitor number"));
        // create a text field to take the input competitor number
        competitorSearch = new JTextField(4);
        // add the text box to the panel
        southPanel.add(competitorSearch);
        //add this panel as the south area of the main JPanel
        this.add(southPanel, BorderLayout.SOUTH); 
    }
}