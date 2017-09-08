package course4b;

public class FilterTrue implements Filter {
	private String myName;
	
	public FilterTrue (String name) {
		myName  = name;
	}
    
	public String getName() {
		return myName;
	}

	@Override
	public boolean satisfies(String id) {
		return true;
	}

}
