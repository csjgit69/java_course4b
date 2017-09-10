package course4b;

import java.util.*;
import java.io.*;
import org.apache.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import edu.duke.*;

public class MainFirstRatings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFirstRatings testFR = new MainFirstRatings();
		
		testFR.quiz4();
		//testFR.testFourthRatings();
		//testFR.quiz3();
		//testFR.testThirdRatings();
		//testFR.quiz2();
		//testFR.testSecondRatings();
		//testFR.quiz1();
		//testFR.testFirstRatings();
	}
	
	public void quiz4() {	
		String movieFile = "ratedmoviesfull.csv";
		String raterFile = "ratings.csv";
		FourthRatings testFR = new FourthRatings();
		MovieDatabase.initialize(movieFile);
		RaterDatabase.initialize(raterFile);
		FiltersAll f = new FiltersAll();
		ArrayList<String> dirs = new ArrayList<String>();
		
		raterFile = "ratings_test.csv";
		RaterDatabase.initialize(raterFile);
		System.out.println("Number over raters read in: "+ RaterDatabase.size());
		System.out.println("Dot product for 20, 15: "+testFR.dotProduct(RaterDatabase.getRater("20"),RaterDatabase.getRater("15")));

		raterFile = "ratings.csv";
		RaterDatabase.initialize(raterFile);
		System.out.println("Number over movies read in: "+ MovieDatabase.size());
		System.out.println("Number over raters read in: "+ RaterDatabase.size());
		System.out.println("");

		System.out.println("-----");
		System.out.println("*** Question #6: ");
		testFR.printSimilarRatings("337", 3, 10, true);
		
		System.out.println("-----");
		System.out.println("*** Question #7: ");
		f = new FiltersAll();
		f.addFilter(new FilterGenre("Mystery", "Genre"));
		testFR.printSimilarRatingsByFilter("964", 5, 20, f, true);

		System.out.println("-----");
		System.out.println("*** Question #8: ");
		testFR.printSimilarRatings("71", 5, 20, true);

		System.out.println("-----");
		System.out.println("*** Question #9: ");
		//“Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh”
		f = new FiltersAll();
		dirs.add("Clint Eastwood");
		dirs.add("J.J. Abrams");		
		dirs.add("Alfred Hitchcock");
		dirs.add("Sydney Pollack");
		dirs.add("David Cronenberg");
		dirs.add("Oliver Stone");
		dirs.add("Mike Leigh");
		f.addFilter(new FilterDirectors(dirs, "Directors"));
		testFR.printSimilarRatingsByFilter("120", 2, 10, f, true);

		System.out.println("-----");
		System.out.println("*** Question #10: ");
		f = new FiltersAll();
		f.addFilter(new FilterGenre("Drama", "Genre"));
		f.addFilter(new FilterTime(80, 160, "Time"));		
		testFR.printSimilarRatingsByFilter("168", 3, 10, f, true);
		
		System.out.println("-----");
		System.out.println("*** Question #10: ");
		f = new FiltersAll();
		f.addFilter(new FilterYearAfter(1975, "YearAfter"));
		f.addFilter(new FilterTime(70, 200, "Time"));		
		testFR.printSimilarRatingsByFilter("314", 5, 10, f, true);
		
	}

	public void testFourthRatings() {	
		String movieFile = "ratedmoviesfull.csv";
		String raterFile = "ratings.csv";
		FourthRatings testFR = new FourthRatings();
		MovieDatabase.initialize(movieFile);
		RaterDatabase.initialize(raterFile);
		FiltersAll f = new FiltersAll();
		ArrayList<String> dirs = new ArrayList<String>();
		
		System.out.println("Number over movies read in: "+ MovieDatabase.size());
		System.out.println("Number over raters read in: "+ RaterDatabase.size());
		System.out.println("");

		System.out.println("-----");
		System.out.println("*** Question #4: ");
		f = new FiltersAll();
		f.addFilter(new FilterTrue("True"));
		testFR.printAverageRatingsByFilter(35, f, true);
		System.out.println("-----");
		
		testFR.printSimilarRatings("65", 5, 20, true);
		
		f = new FiltersAll();
		f.addFilter(new FilterGenre("Action", "Genre"));
		testFR.printSimilarRatingsByFilter("65", 5, 20, f, true);
		
		f = new FiltersAll();
		dirs.add("Clint Eastwood");
		dirs.add("Sydney Pollack");		
		dirs.add("David Cronenberg");
		dirs.add("Oliver Stone");
		f.addFilter(new FilterDirectors(dirs, "Directors"));
		testFR.printSimilarRatingsByFilter("1034", 3, 10, f, true);

		f = new FiltersAll();
		f.addFilter(new FilterGenre("Adventure", "Genre"));
		f.addFilter(new FilterTime(100, 200, "Time"));		
		testFR.printSimilarRatingsByFilter("65", 5, 10, f, true);
		
		f = new FiltersAll();
		f.addFilter(new FilterYearAfter(2000, "YearAfter"));
		f.addFilter(new FilterTime(80, 100, "Time"));		
		testFR.printSimilarRatingsByFilter("65", 5, 10, f, true);
		
		raterFile = "ratings_test.csv";
		RaterDatabase.initialize(raterFile);
		System.out.println("Number over raters read in: "+ RaterDatabase.size());
		System.out.println("Dot product for 20, 15: "+testFR.dotProduct(RaterDatabase.getRater("20"),RaterDatabase.getRater("15")));


	}
	
	public void quiz3() {	
		String movieFile = "ratedmoviesfull.csv";
		ThirdRatings testSR = new ThirdRatings("ratings.csv");
		MovieDatabase.initialize(movieFile);
		FiltersAll f = new FiltersAll();
		
		System.out.println("Number over movies read in: "+ MovieDatabase.size());
		System.out.println("Number over raters read in: "+ testSR.getRaterSize());
		System.out.println("");

		System.out.println("-----");
		System.out.println("*** Question #4: ");
		f = new FiltersAll();
		f.addFilter(new FilterTrue("True"));
		testSR.printAverageRatingsByFilter(35, f, true);
		System.out.println("-----");

		System.out.println("-----");
		System.out.println("*** Question #5: ");
		f = new FiltersAll();
		f.addFilter(new FilterYearAfter(2000, "YearAfter"));
		testSR.printAverageRatingsByFilter(20, f, true);
		System.out.println("-----");
		
		System.out.println("-----");
		System.out.println("*** Question #6: ");
		f = new FiltersAll();
		f.addFilter(new FilterGenre("Comedy", "Genre"));
		testSR.printAverageRatingsByFilter(20, f, true);
		System.out.println("-----");

		System.out.println("-----");
		System.out.println("*** Question #7: ");
		f = new FiltersAll();
		f.addFilter(new FilterTime(105, 135, "Time"));
		testSR.printAverageRatingsByFilter(5, f, true);
		System.out.println("-----");
		
		System.out.println("-----");
		System.out.println("*** Question #8: ");
		f = new FiltersAll();
		ArrayList<String> dirs = new ArrayList<String>();
		dirs.add("Clint Eastwood");
		dirs.add("Joel Coen");
		dirs.add("Martin Scorsese");
		dirs.add("Roman Polanski");
		dirs.add("Nora Ephron");
		dirs.add("Ridley Scott");
		dirs.add("Sydney Pollack");		
		f.addFilter(new FilterDirectors(dirs, "Directors"));
		testSR.printAverageRatingsByFilter(4, f, true);
		System.out.println("-----");

		System.out.println("-----");
		System.out.println("*** Question #9: ");
		f = new FiltersAll();
		f.addFilter(new FilterYearAfter(1990, "Date"));
		f.addFilter(new FilterGenre("Drama", "Genre"));
		testSR.printAverageRatingsByFilter(8, f, true);
		System.out.println("-----");

		System.out.println("-----");
		System.out.println("*** Question #10: ");
		f = new FiltersAll();
		f.addFilter(new FilterTime(90, 180, "Time"));		
		dirs = new ArrayList<String>();
		dirs.add("Clint Eastwood");
		dirs.add("Joel Coen");
		dirs.add("Tim Burton");
		dirs.add("Ron Howard");
		dirs.add("Nora Ephron");
		dirs.add("Sydney Pollack");		
		f.addFilter(new FilterDirectors(dirs, "Directors"));

		testSR.printAverageRatingsByFilter(3, f, true);
		System.out.println("-----");
		
		//testSR.printRaters(true);
		//testSR.printMovies(true);
		//testSR.getAverageRatings(1, true);
		//testSR.getAverageRatingOneMovie("The Godfather", true);
		
		f = new FiltersAll();
		//f.addFilter(new TrueFilter());
	
		//ArrayList<String> movies = MovieDatabase.filterBy(f);
		//testSR.getAverageRatingsFilters(movies, 1, true);
	}
	
	public void testThirdRatings() {
		MovieDatabase.initialize("ratedmovies_short.csv");
		ThirdRatings tTRShort = new ThirdRatings("ratings_short.csv");
		ThirdRatings tTRLong  = new ThirdRatings("ratings.csv");
		String mName = "";
		FiltersAll mFilter = new FiltersAll();
		ArrayList<String> dirs = new ArrayList<String>();
//		dirs.add("Clint Eastwood");
//		dirs.add("Joel Coen");
//		dirs.add("Martin Scorsese");
//		dirs.add("Roman Polanski");
//		dirs.add("Nora Ephron");
//		dirs.add("Ridley Scott");
//		dirs.add("Sydney Pollack");		
		
		System.out.println("\n******** Short Databases ********");
		//System.out.println("Movies processed: "+tTRShort.getMovieSize());
		System.out.println("Movies processed: "+MovieDatabase.size());
		System.out.println("Ratings processed: "+tTRShort.getRaterSize());
		tTRShort.printAverageRatings(1, true);
		
		mFilter.addFilter(new FilterYearAfter(2000, "YearAfter")); 
		tTRShort.printAverageRatingsByFilter(1, mFilter, true);

		mFilter = new FiltersAll();
		mFilter.addFilter(new FilterGenre("Crime", "Genre")); 
		tTRShort.printAverageRatingsByFilter(1, mFilter, true);
		
		dirs = new ArrayList<String>();
		dirs.add("Charles Chaplin");
		dirs.add("Michael Mann");
		dirs.add("Spike Jonze");
		mFilter = new FiltersAll();
		mFilter.addFilter(new FilterDirectors(dirs, "Directors")); 
		tTRShort.printAverageRatingsByFilter(1, mFilter, true);
		
		mFilter = new FiltersAll();
		mFilter.addFilter(new FilterYearAfter(1980, "YearAfter")); 
		mFilter.addFilter(new FilterGenre("Romance", "Genre")); 
		tTRShort.printAverageRatingsByFilter(1, mFilter, true);

		System.out.println("Ratings processed: "+tTRShort.getRaterSize());
		mName = "The Godfather";
		System.out.println("Average rating for \""+mName+"\" is: "+tTRShort.getAverageRatingOneMovie(mName, false));
		
		
		
//		MovieDatabase.initialize("ratedmoviesfull.csv");
//		System.out.println("\n******** Long Databases ********");
//		System.out.println("Movies processed: "+MovieDatabase.size());
//		System.out.println("Ratings processed: "+tTRLong.getRaterSize());
//		tTRLong.printAverageRatings(50, true);
//		mName = "The Godfather";
//		System.out.println("Average rating for \""+mName+"\" is: "+tTRLong.getAverageRatingOneMovie(mName, false));		
	}

	
/*	public void quiz2() {
		SecondRatings tSRShort = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
		SecondRatings tSRLong  = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
		String mName = "";
		int qNum = 0;
		
		System.out.println("\n******** Long Databases ********");
		
		qNum = 5;
		System.out.println("---- Quiz 2 question __"+qNum+"__ ------");
		mName = "The Maze Runner";
		System.out.println("Average rating for \""+mName+"\" is: "+tSRLong.getAverageRatingOneMovie(mName, false));
		System.out.println("---- Quiz 2 question __"+qNum+"__ ------");
		
		qNum = 6;
		System.out.println("---- Quiz 2 question __"+qNum+"__ ------");
		mName = "Moneyball";
		System.out.println("Average rating for \""+mName+"\" is: "+tSRLong.getAverageRatingOneMovie(mName, false));
		System.out.println("---- Quiz 2 question __"+qNum+"__ ------");
		
		qNum = 7;
		System.out.println("---- Quiz 2 question __"+qNum+"__ ------");
		mName = "Vacation";
		System.out.println("Average rating for \""+mName+"\" is: "+tSRLong.getAverageRatingOneMovie(mName, false));
		System.out.println("---- Quiz 2 question __"+qNum+"__ ------");
		
		qNum = 8;
		System.out.println("---- Quiz 2 question __"+qNum+"__ ------");
		tSRLong.printAverageRatings(50, true);
		System.out.println("---- Quiz 2 question __"+qNum+"__ ------");

		qNum = 9;
		System.out.println("---- Quiz 2 question __"+qNum+"__ ------");
		tSRLong.printAverageRatings(20, true);
		System.out.println("---- Quiz 2 question __"+qNum+"__ ------");
		
		qNum = 10;
		System.out.println("---- Quiz 2 question __"+qNum+"__ ------");
		tSRLong.printAverageRatings(12, true);
		System.out.println("---- Quiz 2 question __"+qNum+"__ ------");

	}

	public void testSecondRatings() {
		SecondRatings tSRShort = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
		SecondRatings tSRLong  = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
		String mName = "";
		
		System.out.println("\n******** Short Databases ********");
		System.out.println("Movies processed: "+tSRShort.getMovieSize());
		System.out.println("Ratings processed: "+tSRShort.getRaterSize());
		tSRShort.printAverageRatings(3, true);
		System.out.println("Ratings processed: "+tSRShort.getRaterSize());
		mName = "The Godfather";
		System.out.println("Average rating for \""+mName+"\" is: "+tSRShort.getAverageRatingOneMovie(mName, false));
		
		System.out.println("\n******** Long Databases ********");
		System.out.println("Movies processed: "+tSRLong.getMovieSize());
		System.out.println("Ratings processed: "+tSRLong.getRaterSize());
		tSRLong.printAverageRatings(50, true);
		mName = "The Godfather";
		System.out.println("Average rating for \""+mName+"\" is: "+tSRLong.getAverageRatingOneMovie(mName, false));		
	}
*/	
	public void quiz1 () {
		FirstRatings tFR = new FirstRatings();
		ArrayList<Movie> mList = new ArrayList<Movie>();
		ArrayList<Rater> rList = new ArrayList<Rater>();
		String rID = new String();
		int qNum = 0;

		mList = tFR.loadMovies("ratedmoviesfull.csv", false);
		rList = tFR.loadRaters("ratings.csv", true);

		qNum = 2;
		System.out.println("---- Quiz 1 question __"+qNum+"__ ------");
		System.out.println("Number of Comedies is: "+tFR.numComedies(mList));
		System.out.println("---- Quiz 1 question __"+qNum+"__ ------");
		
		qNum = 3;
		System.out.println("---- Quiz 1 question __"+qNum+"__ ------");
		tFR.numRunTime(mList, 150, false);
		System.out.println("---- Quiz 1 question __"+qNum+"__ ------");

		qNum = 4;
		System.out.println("---- Quiz 1 question __"+qNum+"__ ------");
		tFR.maxDirectors(mList, false);
		System.out.println("---- Quiz 1 question __"+qNum+"__ ------");

		qNum = 5;
		System.out.println("---- Quiz 1 question __"+qNum+"__ ------");
		tFR.maxDirectors(mList, true);
		System.out.println("---- Quiz 1 question __"+qNum+"__ ------");
		
		qNum = 6;
		System.out.println("---- Quiz 1 question __"+qNum+"__ ------");
		rID = "193";
		System.out.println("Number of items rated by rater ["+rID+"] is: "+tFR.getMaxRatingsByID(rList, rID, true));
		System.out.println("---- Quiz 1 question __"+qNum+"__ ------");
		
		qNum = 7;
		System.out.println("---- Quiz 1 question __"+qNum+"__ ------");
		tFR.getMaxRatings(rList, true);
		System.out.println("---- Quiz 1 question __"+qNum+"__ ------");
		
		qNum = 9;
		System.out.println("---- Quiz 1 question __"+qNum+"__ ------");
		tFR.getRatingsPerMovie(rList, "1798709", false);
		System.out.println("---- Quiz 1 question __"+qNum+"__ ------");
		
		tFR.getNumMoviesRated(rList, true);

	}

	public void testFirstRatings () {
		FirstRatings tFR = new FirstRatings();
		ArrayList<Movie> mList = new ArrayList<Movie>();
		ArrayList<Rater> rList = new ArrayList<Rater>();
		String rID = new String();
		
		mList = tFR.loadMovies("ratedmovies_short.csv", true);
		System.out.println("Size of ratedmovies_short.csv is: "+mList.size());
		System.out.println("Number of Comedies is: "+tFR.numComedies(mList));
		tFR.numRunTime(mList, 150, true);
		tFR.maxDirectors(mList, true);
		
		mList = tFR.loadMovies("ratedmoviesfull.csv", false);
		System.out.println("Size of ratedmoviesfull.csv is: "+mList.size());
		System.out.println("Number of Comedies is: "+tFR.numComedies(mList));
		tFR.numRunTime(mList, 150, false);
		tFR.maxDirectors(mList, false);

		rList = tFR.loadRaters("ratings_short.csv", true);
		System.out.println("Size of raters DB is: "+rList.size());
		rID = "2";
		System.out.println("Number of items rated by rater ["+rID+"] is: "+tFR.getMaxRatingsByID(rList, rID, true));
		tFR.getMaxRatings(rList, true);
		tFR.getRatingsPerMovie(rList, "1798709", true);
		tFR.getNumMoviesRated(rList, true);

		rList = tFR.loadRaters("ratings.csv", false);
		System.out.println("Size of raters DB is: "+rList.size());
		rID = "2";
		System.out.println("Number of items rated by rater ["+rID+"] is: "+tFR.getMaxRatingsByID(rList, rID, true));
		tFR.getMaxRatings(rList, false);
		tFR.getRatingsPerMovie(rList, "1798709", true);
		tFR.getNumMoviesRated(rList, true);
	}
}
