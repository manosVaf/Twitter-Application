import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterAplication {
	public static void main(String[] args) throws TwitterException {
		String SinceDate = "2016-11-24";	//from which day we collect tweets
		
		Twitter twitter = new TwitterFactory().getInstance();				//connection with Twitter
		System.out.println("Fetching Data...\n");
		
		Query[] querys = new Query[3];
		querys[0] = new Query("#syriza -(#nea_dimokratia OR #ÍÄ)").since(SinceDate).count(100);
		querys[1] = new Query("(#nea_dimokratia OR #ÍÄ) -#syriza").since(SinceDate).count(100);		//creating querys
		querys[2] = new Query("@atsipras").since(SinceDate).count(100);
		
		QueryResult result;
		Query query;
		
		List<Tweet> syriza = new ArrayList<Tweet>();
		List<Tweet> nea_dimokratia = new ArrayList<Tweet>();				//intializing arraylist to store temporalily the results
		List<Tweet> tsipras = new ArrayList<Tweet>();
		
		for(int i = 0; i < querys.length; i++){
			query = querys[i];
			Map<String, RateLimitStatus> rateLimitStatus = twitter.getRateLimitStatus("search");		//checking our request limit 
			RateLimitStatus searchTweetsRateLimit = rateLimitStatus.get("/search/tweets");

			if(searchTweetsRateLimit.getRemaining() == 0){
				System.out.println("Sleeping for " + searchTweetsRateLimit.getSecondsUntilReset() + " cause we hitted the search limit \n");
				try {
					Thread.sleep((searchTweetsRateLimit.getSecondsUntilReset() + 2) * 10001);			//waiting for twitter to allow us to make more requests
				} catch (InterruptedException e) {
					System.err.println("Thread doesn't want to sleep \n");
				}
			}

			while(true){
				result= twitter.search(query);															//searching twitter
				if(i == 0){
					for(Status status: result.getTweets()){
						syriza.add(new Tweet(status.getCreatedAt(), status.getId(), status.getText(), status.isRetweet(), status.getUser().getName()));		//store result from the first query
					}
				}else if(i == 1){
					for(Status status: result.getTweets()){
						nea_dimokratia.add(new Tweet(status.getCreatedAt(), status.getId(), status.getText(), status.isRetweet(), status.getUser().getName()));	//store result from the second query
					}
				}else{
					for(Status status: result.getTweets()){
						tsipras.add(new Tweet(status.getCreatedAt(), status.getId(), status.getText(), status.isRetweet(), status.getUser().getName()));		//store result from the third query
					}
				}

				if(result.hasNext())
					query = result.nextQuery();						//checking for more results
				else
					break;
			}
			
			if(i == 0){
				new StoreToBase(syriza, "suriza").start();							// creating a thread to store results in database
			}else if(i == 1){				
				new StoreToBase(nea_dimokratia, "nd").start();						// creating a thread to store results in database
			}else{				
				new StoreToBase(tsipras, "tsipras").start();						// creating a thread to store results in database
			}	
		}

		//Print all positives tweets that are related to Syriza
		System.out.println("Positive tweets ( :) ) for Syriza is " + syriza.stream().filter(p->p.getText().contains(":)") || p.getText().contains(":D")).count() + "\n");
		
		//Print all negatives tweets that are related to Syriza
		System.out.println("Negatives tweets ( :( ) for Syriza is " + syriza.stream().filter(p->(p.getText().contains(":(") || p.getText().contains(":@"))).count()  + "\n");
		
		//Print all positives tweets that are related to Nea Dimokratia
		System.out.println("Positive tweets ( :) ) for Nea Dimokratia is " + nea_dimokratia.stream().filter(p->p.getText().contains(":)") || p.getText().contains(":D")).count()  + "\n");
		
		//Print all negatives tweets that are related to Nea Dimokratia
		System.out.println("Negatives tweets ( :( ) for Nea Dimokratia is " + nea_dimokratia.stream().filter(p->(p.getText().contains(":(") || p.getText().contains(":@"))).count() + "\n");
		
		//Print all positives tweets that are related to Alexis Tsipras
		System.out.println("Positive tweets ( : ) for Tsipras is " + tsipras.stream().filter(p->p.getText().contains(":)") || p.getText().contains(":D")).count() + "\n");
		
		//Print all negatives tweets that are related to Alexis Tsipras
		System.out.println("Negatives tweets ( :( ) for Tsipras is " + tsipras.stream().filter(p->(p.getText().contains(":(") || p.getText().contains(":@"))).count() + "\n");
		
	}
}
