package course4b;

import java.util.*;
import java.io.*;
import org.apache.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


import course4b.Rater;
import course4b.Movie;
import edu.duke.*;

public class FirstRatings {
	
	public ArrayList<Movie> loadMovies (String fName, boolean printAll) {		
		/*
		 * This method, loadMovies, has one parameter:
		 *   a String named filename
		 * This method returns:
		 *    an ArrayList of type Movie with all of the movie data from the file.   
		 * This method process every record from the CSV file whose name is filename, 
		 * a file of movie information, and return an ArrayList of type Movie with all of the movie data 
		 * 
		 * file fields:
		 *   id, title, year, country, genre, director, minutes, poster
		 * 
		 * movie class constructors:
		 *   public Movie (String anID, String aTitle, String aYear, String theGenres)
		 *   
		 *   public Movie (String anID, String aTitle, String aYear, String theGenres, 
		 *   String aDirector,String aCountry, String aPoster, int theMinutes)
		 */
		ArrayList<Movie> movies = new ArrayList<Movie>();
		
		String fPath = "data_ratings/"+fName;
		System.out.println("***** Processing Movie Data From: "+fPath+" *****");
		FileResource file = new FileResource(fPath);
		CSVParser parser = file.getCSVParser(); 
        for(CSVRecord record: parser){
        	String id 		= record.get("id");
        	String title 	= record.get("title");
        	String year 	= record.get("year");
        	String country 	= record.get("country");
        	String genre 	= record.get("genre");
        	String director = record.get("director");
        	int minutes 	= Integer.parseInt(record.get("minutes"));
        	String poster 	= record.get("poster");
        	Movie tmpM = new Movie(id, title, year, genre, director, country, poster, minutes);
        	if (printAll) {
        		System.out.println(">> Adding movie ["+title+"] movieId ["+id+"]");
        	}	
            movies.add(tmpM);
        }
		return movies;
	}
	
	public ArrayList<Rater> loadRaters (String fName, Boolean printAll) {
		/*
		 * This method has one parameter named filename. 
		 * This method should process every record from the CSV file whose name is filename, a file of raters and their ratings, 
		 * This method returns an ArrayList of type Rater with all the rater data from the file.
		 * File fields:
		 *   rater_id, movie_id, rating, time
		 * Rater Class is: 
		 * 	private String myID;
		 *  private ArrayList<Rating> myRatings;
		 * Ratings Class is:
		 *  private String item;
	     *  private double value;
		 *  
		 */
		String fPath = "data_ratings/"+fName;
		System.out.println("***** Processing Ratings Data From: "+fPath+" *****");
		ArrayList<Rater> raters = new ArrayList<Rater>();
		FileResource file = new FileResource(fPath);
		CSVParser parser = file.getCSVParser(); 
	    for(CSVRecord record: parser){
	        String raterId  = record.get("rater_id");
	        String movieId  = record.get("movie_id");
	       	double rating   = Integer.parseInt(record.get("rating"));
	       	//System.out.println(">> Adding raterID ["+raterId+"] movieId ["+movieId+"] rating ["+rating+"]");
	       	int rIdx = getRaterID(raters, raterId); 
	       	if ( rIdx != -1) {
	       		//System.out.println("Hit a match: "+raterId);
	       		raters.get(rIdx).addRating(movieId, rating);
	       	} else {
	           	Rater tmpR = new EfficientRater(raterId);
        		//System.out.println("Not a match: "+raterId+"  :: "+tmpR.getID());
	       		tmpR.addRating(movieId, rating);
	       		raters.add(tmpR);        		
	       	}
	    }
		return raters;
	}
	
	public int getRaterID (ArrayList<Rater> rList, String rID) {
		//System.out.println("getRaterID: "+rID);
		for (int i=0; i<rList.size(); i++) {
			//System.out.println("rList size: "+rList.size()+" list ID: "+rList.get(i).getID());
			if (rList.get(i).getID().equals(rID)) {
				return i;
			}
		}
		return -1;
	}	

	public int numRunTime (ArrayList<Movie> mList, int time, boolean printAll) {
		/*
		 * returns a count of the movies in mList that have a running time greater than 'min' minutes
		 */
		System.out.println("---- Printing Movies Longer Than "+time+" Minutes ----");
		int rCnt = 0;
		for(Movie tmp: mList) {
			if (tmp.getMinutes()>time) {
				rCnt++;
				if (printAll) {
					System.out.println(rCnt+" "+tmp.getTitle()+" is longer than: "+time+ " minutes.");
				}
			}
		}
		System.out.println("Total movies in longer than: "+time+" minutes: "+rCnt+"\n");
		return rCnt;
	}
	
	public int numComedies (ArrayList<Movie> mList) {
		/*
		 * Counts the number of comedies in the ArrayList of Moives
		 */
		int cnt = 0;
		
		for (Movie m:mList) {
			if (m.getGenres().contains("Comedy")) {
				cnt++;
			}
		}
		return cnt;
	}

	// 
	public void maxDirectors (ArrayList<Movie> mList, Boolean printAll) {
		/*
		 * Add code to determine the maximum number of movies by any director, 
		 * and who the directors are that directed that many movies. 
		 * Remember that some movies may have more than one director. 
		 * In the file ratedmovies_short.csv the maximum number of movies by any director is one, 
		 * and there are five directors that directed one such movie.
		 */
		System.out.format("---- Parsing Top Directors ----\n");
		// first make HashMap of directors and the movies they directed
		HashMap<String,ArrayList<String>> dList = new HashMap<String,ArrayList<String>>();		
		for (Movie tmp: mList) {
			String[] dirts = tmp.getDirector().trim().split(",\\s+");
			for (int i=0; i<dirts.length; i++) {
				if (dList.containsKey(dirts[i])) {
					dList.get(dirts[i]).add(tmp.getTitle());
					if (printAll) {
						System.out.println("Director ["+dirts[i]+"] Adding movie ["+tmp.getTitle()+"] ");
					}
				} else {
					ArrayList<String> tmpAry = new ArrayList<String>();
					tmpAry.add(tmp.getTitle());
					dList.put(dirts[i], tmpAry);
					if (printAll) {
						System.out.println("Adding Director ["+dirts[i]+"] Adding movie ["+tmp.getTitle()+"] ");
					}
				}
			}
		}
		// next run through the HashMap and find the longest list of movies for any directory
		int maxMovies = 0;
		for (String names: dList.keySet()) {
			if (dList.get(names).size() > maxMovies) {
				maxMovies = dList.get(names).size();
			}
		}
		// last run through the HashMap and count/print all the directors with the max number of movies
		int rCnt = 0;
		for (String names: dList.keySet()) {
			if (dList.get(names).size() == maxMovies) {
				rCnt++;
				if (printAll) {
					System.out.println(rCnt+" Director: \""+names+"\" had max movies of: "+maxMovies);
				}
				maxMovies = dList.get(names).size();
			}
		}
		System.out.println(">> Total Directors with max movies: "+rCnt+" which was: "+maxMovies+"\n");
	}
	
	public int getMaxRatingsByID (ArrayList<Rater> rList, String rID, Boolean printAll) {
		/*
		 * parses over the ArrayList of Rater Objects and finds the rater ID given and prints how many
		 * movies they rated.
		 * If no match is found it returns -1
		 */
		for (Rater tRater: rList) {
			if (tRater.getID().equals(rID)) {
				return tRater.numRatings();
			}
		}
		// return -1 if the rater ID passed in couldn't be found
		return -1;
	}
	
	public void getMaxRatings (ArrayList<Rater> rList, Boolean printAll) {
		/*
		 * Add code to find the maximum number of ratings by any rater. 
		 * Determine how many raters have this maximum number of ratings and who those raters are. 
		 * If you run this code on the file ratings_short.csv, you will see rater 2 has three ratings, 
		 * the maximum number of ratings of all the raters, and that there is only one rater with three ratings.	
		 */
		System.out.println("---- In Print Max Raters ----");
		// first parse ArrayList to find the max number of ratings for a rater
		int maxR = 0;
		for (Rater tRater: rList) {
			if (tRater.numRatings() > maxR) {
				maxR = tRater.numRatings();
			}
		}
		// next parse the ArrayList again and print all the raters that have the max ratings value
		StringBuilder maxRaters = new StringBuilder();
		for (Rater tRater: rList) {
			if (printAll) {
				//System.out.println("Rater ID ["+tRater.getID()+"] ratings ["+tRater.numRatings()+"]");
			}
			if (tRater.numRatings() == maxR) {
				maxRaters.append(tRater.getID());
				if (printAll) {
					//System.out.println("Rater ID ["+tRater.getID()+"] ratings ["+tRater.numRatings()+"] ADDED!!!");
				}
				
			}
		}
		if (printAll) {
			System.out.println("List of Maximum Raters:");
			System.out.println(maxRaters.toString());
		}	
		System.out.println("Maximum Ratings was: "+maxR+"\n");
	}

	public void getRatingsPerMovie (ArrayList<Rater> rList, String mID, Boolean printAll) {
		/*
		 * Add code to find the number of ratings a particular movie has.
		 * If you run this code on the file ratings_short.csv for the movie “1798709”, 
		 * you will see it was rated by four raters.	
		 */
		System.out.println("---- In Print Movie Raters ----");
		int rCnt = 0;
		// run through the Array list and parse into each rater's rating looking for the movie ID, tick
		// a counter if it's found, and move on to the next rater
		for (Rater tRater: rList) {
			if (tRater.hasRating(mID)) {
				rCnt++;
			}
		}
		System.out.println("Movie ID ["+mID+"] # of raters were: "+rCnt+"\n");	
	}
	
	public int getNumMoviesRated (ArrayList<Rater> rList, Boolean printAll) {
		/*
		 * Add code to determine how many different movies have been rated by all these raters. 
		 * If you run this code on the file ratings_short.csv, you will see there were four movies rated.	
		 */
		System.out.println("---- In Print Unique Movie Ratings ----");
		// create a temp rater object, add movies from all the movies in the ArrayList to the new object
		// see it's size after?
		Rater tmpRater = new EfficientRater("temp");
		for (Rater tRater: rList) {
			ArrayList<String> mList = tRater.getItemsRated();
			for (int k=0; k<mList.size(); k++) {
				if (!tmpRater.hasRating(mList.get(k))) {
					tmpRater.addRating(mList.get(k), 1);
				}
			}
		}
		System.out.println("Total Number of Movies Rated: "+tmpRater.numRatings()+"\n");
		return tmpRater.numRatings();
	}
	
	public void testLoadMovies (String tFile) {
		
	}
	
}
