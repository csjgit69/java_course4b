package course4b;

import java.util.ArrayList;
import java.util.Arrays;

public class RecommendationRunner implements Recommender {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
	 /**
     * This method returns a list of movie IDs that will be used to look up 
     * the movies in the MovieDatabase and present them to users to rate. 
     *  
     * The movies returned in the list will be displayed on a web page, so
     * the number you choose may affect how long the page takes to load and
     * how willing users are to rate the movies.  For example, 10-20 should
     * be fine, 50 or more would be too many.
     * 
     * There are no restrictions on the method you use to generate this list
     * of movies: the most recent movies, movies from a specific genre, 
     * randomly chosen movies, or simply your favorite movies.
     * 
     * The ratings for these movies will make the profile for a new Rater 
     * that will be used to compare to for finding recommendations.
     */
	public ArrayList<String> getItemsToRate () {
		ArrayList<String> iList = new ArrayList<String>();
		iList.addAll(Arrays.asList("1615065","0317919","1408101","1650062","0091763","0102138","0110632",
									"1027718","0101761","0796366","0094291"));
		return iList;
	}
	
    /**
     * This method returns nothing, but prints out an HTML table of the 
     * movies recommended for the given rater.
     * 
     * The HTML printed will be displayed on a web page, so the number you
     * choose to display may affect how long the page takes to load.  For 
     * example, you may want to limit the number printed to only the top 
     * 20-50 movies recommended or to movies not rater by the given rater.
     * 
     * You may also include CSS styling for your table using the &lt;style&gt;
     * tag before you print the table.  There are no restrictions on which 
     * movies you print, what order you print them in, or what information
     * you include about each movie. 
     * 
     * @param webRaterID the ID of a new Rater that has been already added to 
     *        the RaterDatabase with ratings for the movies returned by the 
     *        method getItemsToRate
     */
	public void printRecommendationsFor (String webRaterID) {
		FourthRatings tempFR = new FourthRatings();
		
		ArrayList<Rating> rList = tempFR.getSimilarRatingsByFilter (webRaterID, 0, 0, new FilterTrue("True"));
		
		int rCnt = 0;
		System.out.println("<table>");
		System.out.println("<tbody>");
		for (Rating tRating: rList) {
			String movieName  = MovieDatabase.getTitle(tRating.getItem());
			int movieYear  = MovieDatabase.getYear(tRating.getItem());
			String movieGenre = MovieDatabase.getGenres(tRating.getItem());
		    System.out.println("<tr><td>"+movieName+"</td>"
			                      +"<td>"+movieYear+"</td>"
			                      +"<td>"+movieGenre+"</td></tr>");
		    rCnt++;
		    if (rCnt>10) break;
		}
		System.out.println("</tbody>");
		System.out.println("</table>");
		//<style> .movieTitle{//CSS Styling}
		
//		<table >
//		<tbody>
//			<tr>
//				<td> </td>
//				<td> </td>
//				<td> </td>
//				<td> </td>
//			</tr>
//			<tr>
//				<td> </td>
//				<td> </td>
//				<td> </td>
//				<td> </td>
//			</tr>
//		</tbody>
//	</table>
	}
	
}
