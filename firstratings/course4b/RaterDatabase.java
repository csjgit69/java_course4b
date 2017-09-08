package course4b;

import java.util.*;
import java.io.*;
import org.apache.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import edu.duke.*;

/**
 * Write a description of RaterDatabase here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class RaterDatabase {
    private static HashMap<String,Rater> ourRaters;
     
	private static void initialize() {
	    // this method is only called from addRatings 
		if (ourRaters == null) {
			ourRaters = new HashMap<String,Rater>();
		}
	}

    public static void initialize(String fName) {
 		if (ourRaters == null) {
 			ourRaters= new HashMap<String,Rater>();
 			addRatings("data/" + fName);
 		}
 	}	
 	
    public static void addRatings(String fName) {
        initialize();
        FileResource fr = new FileResource("data/" + fName);
        CSVParser csvp = fr.getCSVParser();
        for(CSVRecord rec : csvp) {
                String id = rec.get("rater_id");
                String item = rec.get("movie_id");
                String rating = rec.get("rating");
                addRaterRating(id,item,Double.parseDouble(rating));
        } 
    }
    
    public static void addRaterRating(String raterID, String movieID, double rating) {
        initialize(); 
        Rater rater =  null;
                if (ourRaters.containsKey(raterID)) {
                    rater = ourRaters.get(raterID); 
                } 
                else { 
                    rater = new EfficientRater(raterID);
                    ourRaters.put(raterID,rater);
                 }
                 rater.addRating(movieID,rating);
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
    	ArrayList<Rating> rList = new ArrayList<Rating>();
    	Rater me = RaterDatabase.getRater(id);
    	// add dot_product(r,me) to list if tRater != me
    	for (Rater tRater: RaterDatabase.getRaters()) {
    		
    	}
    	Collections.sort(rList, Collections.reverseOrder());
    	return rList;
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
	         
    public static Rater getRater(String id) {
    	initialize();
    	
    	return ourRaters.get(id);
    }
    
    public static ArrayList<Rater> getRaters() {
    	initialize();
    	ArrayList<Rater> list = new ArrayList<Rater>(ourRaters.values());
    	
    	return list;
    }
 
    public static int size() {
	    return ourRaters.size();
    }
           
}
