package course4b;

public class FilterGenre implements Filter {
	private String myGenre;
	private String myName;
	
	public FilterGenre (String genre, String name) {
		myGenre = genre;
		myName  = name;
	}
    
	public String getName() {
		return myName;
	}
		
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getGenres(id).contains(myGenre);
	}
}
