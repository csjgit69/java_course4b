package course4b;

public class FilterTime implements Filter {	
	private int myMin;
	private int myMax;
	private String myName;
	
	public FilterTime (int min, int max, String name) {
		myMin 	= min;
		myMax	= max;
		myName  = name;
	}
    
	public String getName() {
		return myName;
	}

	public boolean satisfies(String id){
		//return false;
		return (MovieDatabase.getMinutes(id) >= myMin) && (MovieDatabase.getMinutes(id) <= myMax);
	}
	
}
