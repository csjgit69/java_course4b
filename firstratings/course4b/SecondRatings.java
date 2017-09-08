package course4b;

import java.util.*;
import java.io.*;
import org.apache.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import course4b.Movie;
import course4b.Rating;
import course4b.FirstRatings;
import edu.duke.*;


/**
 * Write a description of SecondRatings here.
 * 
 * @author C Stuart Johnson 
 * @version 0.1
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings (String movieFile, String ratingsFile) {
        /*
         * Write an additional SecondRatings constructor that has two String parameters 
         *   named moviefile and ratingsfile. 
         * The constructor should create a FirstRatings object 
         * and then call the loadMovies and loadRaters methods in FirstRatings to read in all the movie 
         * and ratings data and store them in the two private ArrayList variables of the SecondRatings class, 
         * myMovies and myRaters.	
         */    	
    	FirstRatings ratings = new FirstRatings();
 		myMovies = ratings.loadMovies(movieFile, false);
 		myRaters = ratings.loadRaters(ratingsFile, false);	
    }
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public int getMovieSize() {
    	/*
    	 * This method getMovieSize, returns the number of movies that were read in and stored in 
    	 * the ArrayList of type Movie.	
    	 */    	
    	return myMovies.size();
    }
    
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
    
    public String getTitle(String movieID) {
    	/*
    	 * This method, getTitle that has one parameter:
    	 *   a String parameter named id, representing the ID of a movie
    	 * This method returns the title of the movie with that ID. 
    	 * If the movie ID does not exist, then this method returns a String indicating the ID was not found.	
    	 */
		for(Movie tmp: myMovies) {
			if (tmp.getID().equals(movieID)) {
				return tmp.getTitle();
			}
		}
		return "-1";    	
    }
    
    public String getID(String title) {
    /*
     * This method getID that has one parameter:
     *   a String parameter named title representing the title of a movie
     * This method returns the movie ID of this movie. 
     * If the title is not found, return an appropriate message such as “NO SUCH TITLE.” 
     * Note that the movie title must be spelled exactly as it appears in the movie data files.	
     */
    	System.out.println("Get Movie ID for: "+title);
		for(Movie tMovie: myMovies) {
			if (tMovie.getTitle().equals(title)) {
				return tMovie.getID();
			}
		}
		return "-1";    	
    }
    
    public double getAverageRatingOneMovie(String title, boolean printAll) {
    /*
     * This method getAverageRatingOneMovie, has no parameters
     * This method will print out the average ratings for a specific movie title, 
     * such as the movie “The Godfather”. If the moviefile is set to the file named ratedmovies_short.csv, 
     * and the ratingsfile is set to the file ratings_short.csv, then the average for the movie 
     * “The Godfather” would be 9.0.	
     */
    	
    	double avg = getAverageByID(getID(title), 1);
    	if (printAll) {
    		System.out.println("--- In getAverageRatingOneMovie ---");
    		System.out.println("Average for movie \""+title+"\" is: "+avg);
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
        	
       	for (Movie tMovie: myMovies) {
       		String movieID = tMovie.getID();
       		double tempAvg = getAverageByID(movieID, minRaters);
       		if (tempAvg > 0.0) {
       			Rating tempR = new Rating(movieID, tempAvg);
       			rateList.add(tempR);
       		}
       	}
//        Collections.sort(rateList); // uses the compareTo method in Rating class to sort
//       	if (printAll) {
//           	for (Rating tRating: rateList) {
//       			System.out.println("\""+getTitle(tRating.getItem())+"\" "+tRating.toString());
//       		}
//       	}
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
       		System.out.format("\n ---- Average rating for Movies with at least %d raters:\n",minRaters);
           	for (Rating tRating: rateList) {
       			System.out.format("%.3f \"%s\"\n",tRating.getValue(),getTitle(tRating.getItem()));
       		}
       	}
    }    
}
