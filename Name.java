package assignment2; 

// all of this is taken from the lecture programs with the exception of getInitials()

public class Name {

// instance variables	
private String firstName;
private String middleName;
private String lastName;

//constructor to create object with a first and last name
public Name(String fName, String lName) {
		firstName  = fName;
		middleName = "";
		lastName   = lName;
}

//constructor to create object with first, middle and last name
//if there isn't a middle name, that parameter could be an empty String
public Name(String fName, String mName, String lName) {
		firstName  = fName;
		middleName = mName;
		lastName   = lName;
}
//constructor to create name from full name
//in the format first name then space then last name
//or first name then space then middle name then space then last name
public Name (String fullName) {
	  int spacePos1 = fullName.indexOf(' ');
	  firstName = fullName.substring(0, spacePos1);
	  int spacePos2 = fullName.lastIndexOf(' ');
	  if (spacePos1 == spacePos2)
		  middleName = "";
	  else 
		  middleName = fullName.substring(spacePos1+1, spacePos2);
	  lastName = fullName.substring(spacePos2 + 1);
}

//returns the first name
public String getFirstName() {return firstName; }
//returns the last name
public String getLastName() {return lastName; }

//change the last name to the value provided in the parameter
public void setLastName(String ln) {
	  lastName = ln;
}

//returns the first name then a space then the last name
public String getFirstAndLastName() {
		return firstName + " " + lastName;
}

//returns the last name followed by a comma and a space
//  then the first name
public String getLastCommaFirst() {
		return lastName + ", "+ firstName;
}

//returns the full name
//either first name then space then last name
//or first name then space then middle name then space
//  and then last name
public String getFullName() {
	  String result = firstName + " ";
	  if (!middleName.equals("")) {
	    	result += middleName + " ";
	  }
	  result += lastName;
	  return result;
	 }

// gets the initials of the name 
// added by Peter Kirwan 
public String getInitials() {
	// creates String variable to hold the initials
	// and adds the first character of the firstName
	String result = "" + firstName.charAt(0);
	// if there is a middle name
	if (!middleName.equals("")) {
		// then add the first character of it to result
    	result += middleName.charAt(0) + "";
	}
	// add the first character of last name to result
	result += lastName.charAt(0) + ""; 
	// return the initials
	return result;
}
}