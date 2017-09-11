package course4b; 

import java.util.ArrayList;
import java.util.Collections;

public class FourthRatings {

	public double dotProduct (Rater me, Rater r) {
		/*
		 * Write the private helper method named dotProduct
 		 *   two parameters:
 		 *    a Rater named me and a Rater named r 
 		 * This method should first translate a rating from the scale 0 to 10 to the scale -5 to 5 
 		 * and return the dot product of the ratings of movies that they both rated. 
 		 * This method will be called by getSimilarities.
		 */
		double ratingDP = 0;
		ArrayList<String> mList = me.getItemsRated();		
		for (String id: mList) {
			if (r.hasRating(id)) {
				double myRating = me.getRating(id) - 5;
				double rRating  = r.getRating(id) - 5;
				ratingDP	    = ratingDP + (myRating * rRating);
			}
		}
		return ratingDP;		
	}
	
	private ArrayList<Rating> getSimilarities (String id) {
		/*
		 * Write the private method named getSimilarities, 
		 *   has one String parameter named id
		 * This method computes a similarity rating for each rater in the RaterDatabase 
		 *   (except the rater with the ID given by the parameter) to see how similar they are 
		 *   to the Rater whose ID is the parameter to getSimilarities. 
		 * This method returns an ArrayList of type Rating sorted by ratings from highest to lowest rating 
		 *   with the highest rating first 
		 *   and only including those raters who have a positive similarity rating 
		 *   since those with negative values are not similar in any way. 
		 * Note that in each Rating object the item field is a rater’s ID, 
		 *   and the value field is the dot product comparison between that rater and the rater whose ID 
		 *   is the parameter to getSimilarities. Be sure not to use the dotProduct method with parameter 
		 *   id and itself! 
		 */
    	ArrayList<Rating> rList = new ArrayList<Rating>();
    	Rater me = RaterDatabase.getRater(id);
    	// add dot_product(r,me) to list if tRater != me
    	for (Rater tRater: RaterDatabase.getRaters()) {
    		if (!tRater.equals(me)) {
    			double raterDP = dotProduct(me, tRater);
    			if (raterDP > 0) {
    				rList.add(new Rating(tRater.getID(), raterDP));
    			}
    		}
    	}
    	Collections.sort(rList, Collections.reverseOrder());
    	return rList;
	}
	
	private ArrayList<Rating> similarRatings (String raterID, int numSimilarRaters, int minimalRaters, 
											  ArrayList<Rating> simList, ArrayList<String> movies) {
		/*
		 * same code that is in the to getSimilarRatings(ByFilter) methods. 
		 * avoid dup code
		 */
		ArrayList<Rating> rList = new ArrayList<Rating>();
		
		for (String mID: movies) {
			double wAvg = 0;
			double ratingsTotal  = 0;
			double raterCnt = 0;
			for (int k=0; k<numSimilarRaters; k++) {
				double wRating = simList.get(k).getValue();
				String rID = simList.get(k).getItem();
				if (RaterDatabase.getRater(rID).hasRating(mID)) {
					ratingsTotal += wRating * RaterDatabase.getRater(rID).getRating(mID);
					raterCnt++;
				}
			}
			if (raterCnt >= minimalRaters) {
				wAvg = ratingsTotal/raterCnt;
				rList.add(new Rating(mID, wAvg));
			}
		}
		Collections.sort(rList, Collections.reverseOrder());
		return rList;
	}

	
	public ArrayList<Rating> getSimilarRatings (String raterID, int numSimilarRaters, int minimalRaters) {
		/*
		 * Write the public method named getSimilarRatings, 
		 *   three parameters: 
		 *   a String named id representing a rater ID 
		 *   an integer named numSimilarRaters 
		 *   an integer named minimalRaters. 
		 * This method should return an ArrayList of type Rating, 
		 *   of movies and their weighted average ratings using only the top numSimilarRaters 
		 *   with positive ratings and including only those movies that have at least minimalRaters 
		 *   ratings from those most similar raters (not just minimalRaters ratings overall). 
		 * For example, if minimalRaters is 3 and a movie has 4 ratings but only 2 of those ratings were made 
		 *   by raters in the top numSimilarRaters, that movie should not be included. 
		 * These Rating objects should be returned in sorted order by weighted average rating from largest to smallest ratings. 
		 * This method is very much like the getAverageRatings method you have written previously. 
		 * In particular this method should:
		 *   For every rater, get their similarity rating to the given parameter rater id. 
		 *     Include only those raters with positive similarity ratings—those that are more similar to rater id. 
		 *     Which method could you call?
		 *   For each movie, calculate a weighted average movie rating based on: 
		 *     Use only the top (largest) numSimilarRaters raters. For each of these raters, 
		 *     multiply their similarity rating by the rating they gave that movie. 
		 *     This will emphasize those raters who are closer to the rater id, since they have greater weights. 
		 *     The weighted average movie rating for a particular movie is the sum of these weighted average 
		 *     ratings (for each rater multiply their similarity rating by their rating for the movie), 
		 *     divided by the total number of such ratings.
		 *   This method returns an ArrayList of Ratings for movies and their calculated weighted ratings, in sorted order.
		 */
		// String raterID, int numSimilarRaters, int minimalRaters
		ArrayList<Rating> simList = getSimilarities(raterID);
		ArrayList<String> movies = MovieDatabase.filterBy(new FilterTrue("True"));
		ArrayList<Rating> rList = similarRatings(raterID, numSimilarRaters, minimalRaters, simList, movies);
		
		Collections.sort(rList, Collections.reverseOrder());
		return getSimilarRatingsByFilter (raterID, numSimilarRaters, minimalRaters, new FilterTrue("True"));
	}
	
	public ArrayList<Rating> getSimilarRatingsByFilter (String raterID, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
		/*
		 * Same as getSimilarRatings but with a Filter on movies being compared.
		 */
		ArrayList<Rating> simList = getSimilarities(raterID);
		ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
		ArrayList<Rating> rList = similarRatings(raterID, numSimilarRaters, minimalRaters, simList, movies);
		
		Collections.sort(rList, Collections.reverseOrder());
		return rList;
	}
	
    public void printSimilarRatings (String raterID, int minimalRaters, int numSimilarRaters, boolean printAll) {
        /*
         * 
         */
    	ArrayList<Rating> srList = getSimilarRatings(raterID, numSimilarRaters, minimalRaters);
        	
        Collections.sort(srList, Collections.reverseOrder()); // uses the compareTo method in Rating class to sort        
       	if (printAll) {
       		System.out.format("\n ---- Recommended movies for RaterID %s, numSimRaters: %d, minRaters/movie: %d\n", raterID, numSimilarRaters, minimalRaters );
       		System.out.format(" ---- Movies Found: %d\n", srList.size());
       		int pCnt = 0;
           	for (Rating tRating: srList) {
       			System.out.format("%.3f \"%s\"\n",tRating.getValue(),MovieDatabase.getTitle(tRating.getItem()));
       			pCnt++;
       			if (pCnt>10) break;
       		}
       	}
    }
	
    public void printSimilarRatingsByFilter (String raterID, int minimalRaters, int numSimilarRaters, Filter filterCriteria, boolean printAll) {
        /*
         * 
         */
    	ArrayList<Rating> srList = getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
        	
        Collections.sort(srList, Collections.reverseOrder()); // uses the compareTo method in Rating class to sort        
       	if (printAll) {
       		System.out.format("\n ---- Recommended movies for RaterID %s, numSimRaters: %d, minRaters/movie: %d\n", raterID, numSimilarRaters, minimalRaters );
       		System.out.format(" ---- Movies Found: %d\n", srList.size());
       		int pCnt = 0;
           	for (Rating tRating: srList) {
       			System.out.format("%.3f \"%s\"\n",tRating.getValue(),MovieDatabase.getTitle(tRating.getItem()));
       			pCnt++;
       			if (pCnt>10) break;
       		}
       	}
    }
    
    public ArrayList<Rating> getRecommendations(String id, int numRaters) {
    	ArrayList<Rating> rList = getSimilarities(id);
    	ArrayList<Rating> ret = new ArrayList<Rating>();
    	
    	for (String movieID: MovieDatabase.filterBy(new FilterTrue("True"))) {
    		for (int k=0; k < numRaters; k++) {
    			Rating r = rList.get(k);
    			// use Rater id and weight in r to update running totals
    			
    		}
    	}
    	
    	Collections.sort(ret, Collections.reverseOrder());
    	return ret;
    }
	
    public double getAverageByID (String movieID, int minRaters) {
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
    	for(Rater tRater: RaterDatabase.getRaters()) {
    		// System.out.println("raterID: "+tRater.getID()+", rated: "+tRater.getItemsRated());
    		if (tRater.hasRating(movieID)) {
    			raters++;
        		totalRatings = totalRatings + tRater.getRating(movieID);
    		}
    	}
    	if (raters >= minRaters) {
    		return totalRatings/raters;
    	} else {
    		return 0.0;
    	}
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
