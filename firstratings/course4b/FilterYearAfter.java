package course4b;

public class FilterYearAfter implements Filter {
	private int myYear;
	private String myName;
	
	public FilterYearAfter (int year, String name) {
		myYear 	= year;
		myName  = name;
	}
    
	public String getName() {
		return myName;
	}

	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getYear(id) >= myYear;
	}

}
