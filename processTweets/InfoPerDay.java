
public class InfoPerDay {
	private int positiveTweets = 0;
	private int negativeTweets = 0;
	private String category;
	private int day = -1;
	
	public InfoPerDay(int Day, String Category, int pos, int nega){
		day = Day;
		category = Category;
		positiveTweets = pos;
		negativeTweets = nega;
	}

	public int getPositiveTweets() {
		return positiveTweets;
	}

	public void setPositiveTweets(int positiveWords) {
		this.positiveTweets = positiveWords;
	}

	public int getNegativeTweets() {
		return negativeTweets;
	}

	public void setNegativeTweets(int negativeWords) {
		this.negativeTweets = negativeWords;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String toString(){
		return "Day " + day + " \nPositive Tweets: " + positiveTweets + " \nNegative Tweets: " + negativeTweets;
	}
}
