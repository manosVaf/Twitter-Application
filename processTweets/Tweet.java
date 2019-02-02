import java.util.Date;

public class Tweet {
	private String createdDate;
	private long id;
	private String text;
	private boolean retweet;
	private String user;
	private int numberDate;
	private String category;
	
	public Tweet(Date Create, long Id, String txt, boolean Retweet, String Theuser){
		createdDate = Create.toString();
		id = Id;
		text = txt;
		retweet = Retweet;
		user = Theuser;
	}
	
	public Tweet(int number, String txt, boolean Retweet, String Category){
		numberDate = number;
		text = txt;
		retweet = Retweet;
		category = Category;		
	}
	
	public int getNumberDate() {
		return numberDate;
	}

	public void setNumberDate(int numberDate) {
		this.numberDate = numberDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isRetweet() {
		return retweet;
	}
	public void setRetweet(boolean retweet) {
		this.retweet = retweet;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String toString(){
		return "date = " + numberDate + " text: " + text + " isRetweet " + retweet;
	}
}
