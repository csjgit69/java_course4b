package course4b;

import java.util.ArrayList;
import java.util.Collections;

public class ThirdRatings {
    //private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings (String ratingsFile) {
        /*
         * Write an additional SecondRatings constructor that has two String parameters 
         *   named moviefile and ratingsfile. 
         * The constructor should create a FirstRatings object 
         * and then call the loadMovies and loadRaters methods in FirstRatings to read in all the movie 
         * and ratings data and store them in the two private ArrayList variables of the SecondRatings class, 
         * myMovies and myRaters.	
         */    	
    	FirstRatings ratings = new FirstRatings();
 		//myMovies = ratings.loadMovies(movieFile, false);
 		myRaters = ratings.loadRaters(ratingsFile, false);	
    }
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
//    public int getMovieSize() {
//    	/*
//    	 * This method getMovieSize, returns the number of movies that were read in and stored in 
//    	 * the ArrayList of type Movie.	
//    	 */    	
//    	return myMovies.size();
//    }
    
    public int getRaterSize() {
    	/*
    	 * This method getRaterSize, returns the number of raters that were read in and stored in 
    	 * the ArrayList of type Rater.	
    	 */    	
    	return myRaters.size();
    }

    public double getAverageByID(String movieID, int minRaters) {
    	/*
    	 * This private helper method getAverageByID has two parameters:
    	 *   a String named id representing a movie ID 
    	 *   an integer named minimalRaters
    	 * This method returns a double representing the average movie rating for this ID if there are at least 
    	 * minimalRaters ratings. If there are not minimalRaters ratings, then it returns 0.0.
    	 */    	
    	int raters = 0;
    	double totalRatings = 0.0;
		//System.out.println("Get Average for ID: "+movieID);    	
    	for(Rater tRater: myRaters) {
    		double rating = tRater.getRating(movieID);
    		// System.out.println("raterID: "+tRater.getID()+", rated: "+tRater.getItemsRated());
    		if (rating != -1) {
    			raters++;
        		totalRatings = totalRatings + rating;
    		}
    	}
    	if (raters >= minRaters) {
    		return totalRatings/raters;
    	} else {
    		return 0.0;
    	}
    }
        
    public double getAverageRatingOneMovie(String title, boolean printAll) {
    /*
     * This method getAverageRatingOneMovie, has no parameters
     * This method will print out the average ratings for a specific movie title, 
     * such as the movie “The Godfather”. If the moviefile is set to the file named ratedmovies_short.csv, 
     * and the ratingsfile is set to the file ratings_short.csv, then the average for the movie 
     * “The Godfather” would be 9.0.	
     */
    	ArrayList<String> myMovies = MovieDatabase.filterBy(new FilterTrue("True"));
    	double avg = 0.0;
    	for (String movieID: myMovies) {
    		if (MovieDatabase.getTitle(movieID).equals(title)) {
    			avg = getAverageByID(movieID, 1);    			
    		}
    	}
    	if (printAll) {
    		System.out.println("--- In getAverageRatingOneMovie ---");
    		System.out.println("Average for movie \""+title+"\" is: "+avg+"\n");
    	}
    	return avg;
    }
    
    public ArrayList<Rating> getAverageRatings (int minRaters, boolean printAll) {
        /*
         * This public method, getAverageRatings, has one parameter:
         *   an int parameter named minimalRaters
         * This method should find the average rating for every movie that has been rated by at least
         * minimalRaters raters. 
         * Then store each such rating in a Rating object in which the movie ID and the average rating are used 
         * in creating the Rating object. 
         * The method getAverageRatings returns an ArrayList of all the Rating objects for movies that have at 
         * least the minimal number of raters supplying a rating. 
         * 
         * For example, if minimalRaters has the value 10, then this method returns rating information 
         * (the movie ID and its average rating) for each movie that has at least 10 ratings. 
         * You should consider calling the private getAverageByID method.
         */
    	
        ArrayList<Rating> rateList = new ArrayList<Rating>();        
    	//ArrayList<String> myMovies = MovieDatabase.filterBy(new FilterTrue());        	
       	for (String movieID: MovieDatabase.filterBy(new FilterTrue("True"))) {
       		//String movieID = tMovie.getID();
       		double tempAvg = getAverageByID(movieID, minRaters);
       		if (tempAvg > 0.0) {
       			Rating tempR = new Rating(movieID, tempAvg);
       			rateList.add(tempR);
       		}
       	}
        return rateList;
    }
    
    
    public void printAverageRatings (int minRaters, boolean printAll) {
        /*
         * This public method, getAverageRatings, has one parameter:
         *   an int parameter named minimalRaters
         * This method should find the average rating for every movie that has been rated by at least
         * minimalRaters raters. 
         * Then store each such rating in a Rating object in which the movie ID and the average rating are used 
         * in creating the Rating object. 
         * The method getAverageRatings returns an ArrayList of all the Rating objects for movies that have at 
         * least the minimal number of raters supplying a rating. 
         * 
         * For example, if minimalRaters has the value 10, then this method returns rating information 
         * (the movie ID and its average rating) for each movie that has at least 10 ratings. 
         * You should consider calling the private getAverageByID method.
         */
        ArrayList<Rating> rateList = getAverageRatings(minRaters, false);
        	
        Collections.sort(rateList); // uses the compareTo method in Rating class to sort        
       	if (printAll) {
       		System.out.format("\n ---- Average rating for Movies with number raters at least: %d\n",minRaters);
       		System.out.format(" ---- Movies Found: %d\n", rateList.size());
           	for (Rating tRating: rateList) {
       			System.out.format("%.3f \"%s\"\n",tRating.getValue(),MovieDatabase.getTitle(tRating.getItem()));       			
       		}
       	}
    }
   
    public ArrayList<Rating> getAverageRatingsByFilter (int minRaters, Filter mFilter) {
        ArrayList<Rating> rateList = new ArrayList<Rating>();        
    	//ArrayList<String> myMovies = MovieDatabase.filterBy(new FilterTrue());        	
       	for (String movieID: MovieDatabase.filterBy(mFilter)) {
       		//String movieID = tMovie.getID();
       		double tempAvg = getAverageByID(movieID, minRaters);
       		if (tempAvg > 0.0) {
       			Rating tempR = new Rating(movieID, tempAvg);
       			rateList.add(tempR);
       		}
       	}
        return rateList;    	
    }

    public void printAverageRatingsByFilter (int minRaters, Filter mFilter, boolean printAll) {
        /*
         * Like printAverageRatings, this prints the Average ratings for movies that have:
         *   - at least minRaters raters
         *   - satisfy the passed in filter
         */
        ArrayList<Rating> rateList = getAverageRatingsByFilter(minRaters, mFilter);
        Collections.sort(rateList); // uses the compareTo method in Rating class to sort        
       	if (printAll) {
       		System.out.format("\n ---- Average rating for Movies with number raters at least: %d\n",minRaters);
       		System.out.format(" ---- Movies Found: %d\n", rateList.size());
			System.out.format("%-8s%-6s%-9s%-35s%-30s%-30s\n","Rating", "Year", "Minutes", "Title", "Genres", "Directors"); 
           	for (Rating tRating: rateList) {
    			System.out.format("%-8.4f%-6s%-9s%-35s%-30s%-30s\n",tRating.getValue(), 
    					MovieDatabase.getYear(tRating.getItem()), 
    					MovieDatabase.getMinutes(tRating.getItem()), 
    					MovieDatabase.getTitle(tRating.getItem()),
    					MovieDatabase.getGenres(tRating.getItem()), 
    					MovieDatabase.getDirector(tRating.getItem()));
       		}
       	}
    }

    
}
