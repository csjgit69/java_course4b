package course4b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
//        for(int k=0; k < myRatings.size(); k++){
//            if (myRatings.get(k).getItem().equals(item)){
//                return true;
//            }
//        }      
        return myRatings.containsKey(item);
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
//        for(int k=0; k < myRatings.size(); k++){
//            if (myRatings.get(k).getItem().equals(item)){
//                return myRatings.get(k).getValue();
//            }
//        }
    	if (myRatings.containsKey(item)) {
            return myRatings.get(item).getValue();    		
    	} else {
    		return -1.0;
    	}
//        return myRatings.get(item).getValue();    		
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
//        for(int k=0; k < myRatings.size(); k++){
//            list.add(myRatings.get(k).getItem());
//        }
        for(Rating tRating: myRatings.values()) {
            list.add(tRating.getItem());
        }
        Collections.sort(list);
        return list;
    }

}
