package course4b;

import java.util.ArrayList;

public class FilterDirectors implements Filter {
	private ArrayList<String> myDirectors;
	private String myName;
	
	public FilterDirectors(ArrayList<String> directors, String name) {
		myDirectors 	= directors;
		myName  = name;
	}
    
	public String getName() {
		return myName;
	}

	@Override
	public boolean satisfies(String id){
		for (String tDirector: myDirectors) {
			if (MovieDatabase.getDirector(id).contains(tDirector)) {
				return true;
			}
		}
		return false;
	}
}
