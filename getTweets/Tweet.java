import java.util.Date;

public class Tweet {
	private String createdDate;
	private long id;
	private String text;
	private boolean retweet;
	private String user;
	
	public Tweet(Date Create, long Id, String txt, boolean Retweet, String Theuser){
		createdDate = Create.toString();
		id = Id;
		text = txt;
		retweet = Retweet;
		user = Theuser;
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
}
