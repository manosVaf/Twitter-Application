import java.util.*;

import org.jfree.ui.RefineryUtilities;

public class Application {
	public static void main(String[] args){		
		ArrayList<ArrayList<Tweet>> tweets = new ReturnFromBase().FetchTweets();
		HashMap<String, String> positive = new PosNegaStop().FetchPos("PosLex.txt");
		HashMap<String, String> negative = new PosNegaStop().FetchNega("NegLex.txt");
		ArrayList<String> stopWords = new PosNegaStop().FetchStopWords("stopwords.txt");
		Result day1 = new Result(tweets.get(0), positive, negative, stopWords, 1);
		Result day2 = new Result(tweets.get(1), positive, negative, stopWords, 2);
		Result day3 = new Result(tweets.get(2), positive, negative, stopWords, 3);
		Result day4 = new Result(tweets.get(3), positive, negative, stopWords, 4);
		Result day5 = new Result(tweets.get(4), positive, negative, stopWords, 5);
		Result day6 = new Result(tweets.get(5), positive, negative, stopWords, 6);
		Result day7 = new Result(tweets.get(6), positive, negative, stopWords, 7);
		Result day8 = new Result(tweets.get(7), positive, negative, stopWords, 0);
		ArrayList<Result> results = new ArrayList<Result>();
		
		results.add(day1);
		results.add(day2);
		results.add(day3);
		results.add(day4);
		results.add(day5);
		results.add(day6);
		results.add(day7);
		results.add(day8);
		for(Result e: results){
			e.start();
		}
		
		try {
			day1.join();
			day2.join();
			day3.join();
			day4.join();
			day5.join();
			day6.join();
			day7.join();
			day8.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int sumpositives = 0;
		int sumnegatives = 0;
		float totalTweets = tweets.get(7).size();
		
		for(int j = 0; j < 7; j++){
			sumpositives += results.get(j).getResults().get(0).getPositiveTweets();
			sumpositives += results.get(j).getResults().get(1).getPositiveTweets();
			sumpositives += results.get(j).getResults().get(2).getPositiveTweets();
		
			sumnegatives += results.get(j).getResults().get(0).getNegativeTweets();
			sumnegatives += results.get(j).getResults().get(1).getNegativeTweets();
			sumnegatives += results.get(j).getResults().get(2).getNegativeTweets();
		}
		
		
		float perPositives = (float) ((sumpositives/totalTweets)*100.0);
		float perNegatives = (float) ((sumnegatives/totalTweets)*100.0);
		
		new Thread(new Runnable(){
			public void run(){
				BarChart_AWT chart = new BarChart_AWT("Positive Tweets Statistics", results);
				chart.pack( );        
				RefineryUtilities.centerFrameOnScreen( chart );        
				chart.setVisible( true ); 
			};
		}).start();
	    
		TweetsStats chart1 = new TweetsStats("Tweets% Statistics", perPositives, perNegatives);
	    chart1.pack( );        
	    RefineryUtilities.centerFrameOnScreen( chart1 );        
	    chart1.setVisible( true ); 
		}	
}