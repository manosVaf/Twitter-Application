import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Result extends Thread {
	private ArrayList<InfoPerDay> results = new ArrayList<InfoPerDay>();
	private ArrayList<Tweet> tweets;
	private HashMap<String, String> posLexi;
	private HashMap<String, String> negLexi;
	private ArrayList<String> stopWords;
	private ArrayList<Tweet> syriza = new ArrayList<Tweet>();
	private ArrayList<Tweet> nd = new ArrayList<Tweet>();
	private ArrayList<Tweet> tsipras = new ArrayList<Tweet>();
	private int day;
	
	public Result(ArrayList<Tweet> list, HashMap<String, String> posLex, HashMap<String, String> negLex,ArrayList<String> stopwords, int Day){
		tweets = list;
		posLexi = posLex;
		negLexi = negLex;
		stopWords = stopwords;
		day = Day;
	}
	
	public String toString(){
		return "Day " + day + "\nCategory: " + results.get(0).getCategory() + "\nPositive Tweets: " +  results.get(0).getPositiveTweets() + "\nNegative Tweets: " +  results.get(0).getNegativeTweets() +
				"\nCategory: " + results.get(1).getCategory() + "\nPositive Tweets: " +  results.get(1).getPositiveTweets() + "\nNegative Tweets: " +  results.get(1).getNegativeTweets() +
				"\nCategory: " + results.get(2).getCategory() + "\nPositive Tweets: " +  results.get(2).getPositiveTweets() + "\nNegative Tweets: " +  results.get(2).getNegativeTweets();
	}

	public void run(){
		for(Tweet i: tweets){
			if(i.getCategory().equals("suriza")) syriza.add(i);
			if(i.getCategory().equals("tsipras")) tsipras.add(i);
			if(i.getCategory().equals("nd")) nd.add(i);
		}
		
		int[] countWords = new int[3];	//0 = positive, 1 = negative, 2 = non
		int positiveTweets = 0;
		int negativeTweets = 0;
		String[] tempWords;

		for(Tweet i: syriza){
			removeMetaData(i);					// removing Meta Data 
	
			tempWords = PreStemming(i.getText(), posLexi, negLexi);				//counting positive and Negative Tweets for each caterogy
			i.setText(tempWords[0]);
	
			countWords[0] = countWords[0] + Integer.parseInt(tempWords[1]);
			countWords[1] = countWords[1] + Integer.parseInt(tempWords[2]);		//count pos and Negative Tweets from all tweets in the arraylist per day
			countWords[2] = countWords[2] + Integer.parseInt(tempWords[3]);
			
			if(countWords[0] > countWords[1]) positiveTweets++;
			else if(countWords[1] > countWords[0])negativeTweets++;
			
			
			i.setText(replaceLetters(i.getText().toUpperCase()));
			i.setText(removeStopWords(i.getText(),stopWords));
			i.setText(stemming(i.getText()));
			
		}
		results.add(new InfoPerDay(day, "suriza", positiveTweets, negativeTweets));
		countWords[0] = 0;
		countWords[1] = 0;
		countWords[2] = 0;
		positiveTweets = 0;
		negativeTweets = 0;
		
		for(Tweet i: nd){
			removeMetaData(i);					// removing Meta Data 
			
			tempWords = PreStemming(i.getText(), posLexi, negLexi);
			i.setText(tempWords[0]);
			
			if(countWords[0] > countWords[1]) positiveTweets++;
			else if(countWords[1] > countWords[0])negativeTweets++;
			
			
			countWords[0] = countWords[0] + Integer.parseInt(tempWords[1]);
			countWords[1] = countWords[1] + Integer.parseInt(tempWords[2]);		//count pos and Negative Tweets from all tweets in the arraylist per day
			countWords[2] = countWords[2] + Integer.parseInt(tempWords[3]);
			
			i.setText(replaceLetters(i.getText().toUpperCase()));
			i.setText(removeStopWords(i.getText(),stopWords));
			i.setText(stemming(i.getText()));
		}
		results.add(new InfoPerDay(day, "nd", positiveTweets, negativeTweets));
		countWords[0] = 0;
		countWords[1] = 0;
		countWords[2] = 0;
		positiveTweets = 0;
		negativeTweets = 0;
		
		
		for(Tweet i: tsipras){
			removeMetaData(i);					// removing Meta Data 
			
			tempWords = PreStemming(i.getText(), posLexi, negLexi);
			i.setText(tempWords[0]);
			
			if(countWords[0] > countWords[1]) positiveTweets++;
			else if(countWords[1] > countWords[0])negativeTweets++;
			
			
			countWords[0] = countWords[0] + Integer.parseInt(tempWords[1]);
			countWords[1] = countWords[1] + Integer.parseInt(tempWords[2]);		//count pos and Negative Tweets from all tweets in the arraylist per day
			countWords[2] = countWords[2] + Integer.parseInt(tempWords[3]);
			
			i.setText(replaceLetters(i.getText().toUpperCase()));
			i.setText(removeStopWords(i.getText(),stopWords));
			i.setText(stemming(i.getText()));
		}
		results.add(new InfoPerDay(day, "tsipras", positiveTweets, negativeTweets));
		countWords[0] = 0;
		countWords[1] = 0;
		countWords[2] = 0;
		positiveTweets = 0;
		negativeTweets = 0;
	}
	
	

	private static String removeStopWords(String text, ArrayList<String> stopwords) {
		String[] txt = text.split(" ");
		String returnStr = " ";
		for(String s: txt){
			if(!stopwords.contains(s)){
				if(returnStr.equals(" ")){
					returnStr = s;
				}else{
					returnStr += " " + s;
				}
			}
		}
		return returnStr;
	}

	private static String removeUrl(String text){
        String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern p = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        int i = 0;
        while (m.find()) {
            try{
            	text = text.replaceAll(m.group(i),"").trim();
            }catch(IndexOutOfBoundsException e){
            	
            }
            i++;
        }
        return text;
    }
	
	private static String stemming(String s){
		String[] words = s.split(" ");
		String retstr = "";
		GreekStemmer stem = new GreekStemmer();
		for(String p: words){
			retstr += " " +stem.stem(p);
		}
		return retstr;
	}
	
	private static String replaceLetters(String s) {
	        ArrayList<Character> letters = new ArrayList<>();
	        ArrayList<Character> l = new ArrayList<>();
	        letters.add('Ђ');
	        letters.add('И');
	        letters.add('Й');
	        letters.add('К');
	        letters.add('М');
	        letters.add('О');
	        letters.add('П');
	        l.add('С');
	        l.add('Х');
	        l.add('Ч');
	        l.add('Щ');
	        l.add('Я');
	        l.add('е');
	        l.add('й');
	        boolean flag = false;
	        String n = "";
	        
	        if(s.startsWith(" ")){
	        	s = s.substring(1);
	        }

	        for (int i = 0; i < s.length(); i++) {
	            for (int j = 0; j < letters.size(); j++) {
	                if ((s.charAt(i)) == (letters.get(j))) {
	                    n += (l.get(j));
	                    flag = true;
	                    break;
	                }
	            }
	            if (!flag) {
	                n += s.charAt(i);
	            }
	            flag = false;
	        }
	        return n;
	    }
	
	private static String removeNumbers(String text){
        String numberPattern = "[0-9]";
        Pattern p = Pattern.compile(numberPattern, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        int i = 0;
        while (m.find()) {
            try{
            	text = text.replaceAll(m.group(i),"").trim();
            }catch(IndexOutOfBoundsException e){
            	
            }
            i++;
        }
        return text;
    }
	
	private static String removePunctuation(String text){
        return text = text.replaceAll("[^A-Za-zС-йс-љмнќўпо ]", "");
    }
	
	private static void removeMetaData(Tweet i){
        i.setText(removeUrl(i.getText()));
        i.setText(removeNumbers(i.getText()));
        i.setText(removePunctuation(i.getText()));
    }
	
	private static String[] PreStemming(String text, HashMap<String, String> pos, HashMap<String, String> nega){
		StringTokenizer token = new StringTokenizer(text, " ");
		int positive = 0;
		int negative = 0;
		int non = 0;
		String newText = "";
		while(token.hasMoreTokens()){
			String next = token.nextToken();
			if(pos.containsKey(next)){
				positive++;
				newText = newText + " " + pos.get(next);
			}else if(nega.containsKey(next)){
				negative++;
				newText = newText + " " + pos.get(next);
			}else{
				non++;
				newText = newText + " " + next;
			}
		}
		String[] info = {newText, Integer.toString(positive), Integer.toString(negative), Integer.toString(non)};
		return  info; 
	}

	
	public ArrayList<InfoPerDay> getResults() {
		return results;
	}
	

	public void setResults(ArrayList<InfoPerDay> results) {
		this.results = results;
	}

	
	public int getDay() {
		return day;
	}

	
	public void setDay(int day) {
		this.day = day;
	}

	
	public ArrayList<String> getStopWords() {
		return stopWords;
	}

	public void setStopWords(ArrayList<String> stopWords) {
		this.stopWords = stopWords;
	}
}
