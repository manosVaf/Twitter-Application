import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StoreToBase extends Thread{
	private List<Tweet> lista;
	private String category;
	public StoreToBase(List<Tweet> list, String Category){
		lista = list;
		category = Category;
	}
	
		public void run(){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/twitter?useUnicode=true&autoReconnect=true&useSSL=false", "root", "1994astra");
				PreparedStatement statement = connect.prepareStatement("insert ignore into tweets(TweetDate, Userid, TweetText, IsRetweet, TheUser, Category) values (?, ?, ?, ?, ?, ?)");
				
				int tweetcounter = 0;
				
				for(Tweet tweet: lista){
					statement.setString(1, tweet.getCreatedDate());
					statement.setLong(2, tweet.getId());
					statement.setString(3, tweet.getText());
					statement.setBoolean(4, tweet.isRetweet());
					statement.setString(5, tweet.getUser());
					statement.setString(6, category);
					if(statement.executeUpdate() > 0)tweetcounter++;
				}
				System.out.println("Category: " + category + " is stored succesfully with " + tweetcounter  + "\n");
			}catch(ClassNotFoundException e){
				System.err.println("Driver Not Found!!!");
			}catch(SQLException e){
				System.err.println("Cannot Connect to database");
				System.err.println(e.initCause(e));
			}
		}
}
