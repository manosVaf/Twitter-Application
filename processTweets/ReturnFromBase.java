import java.sql.*;
import java.util.*;

public class ReturnFromBase {
	private Connection connect;
	private Statement statement;
	private ResultSet result;
	String query;
	private String dburl="jdbc:mysql://localhost/twitter?autoReconnect=true&useSSL=false";	
	private final String[] queries = {"select * from tweets where TweetDate like 'Thu Nov 24%'", "select * from tweets where TweetDate like 'Fri Nov 25%'", 
							"select * from tweets where TweetDate like 'Sat Nov 26%'", "select * from tweets where TweetDate like 'Sun Nov 27%'",
							"select * from tweets where TweetDate like 'Mon Nov 28%'", "select * from tweets where TweetDate like 'Tue Nov 29%'",
							"select * from tweets where TweetDate like 'Wed Nov 30%'", "select * from tweets "};

	public ArrayList<ArrayList<Tweet>> FetchTweets(){
		ArrayList<ArrayList<Tweet>> tweets = new ArrayList<ArrayList<Tweet>>();
		for(int i = 0; i < 8; i++){
			tweets.add(new ArrayList<Tweet>());
		}
		try{			
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(dburl, "yourusername", "yourpassword");
			statement = connect.createStatement();
			for(int i = 0; i < queries.length; i++){
				query = queries[i];
				result = statement.executeQuery(query);
				result.first();
				while(result.next()){
					tweets.get(i).add(new Tweet(i, result.getString(3), result.getBoolean(5), result.getString(6)));
				}			
			}
		}catch(ClassNotFoundException e){
			System.err.println("Driver Not Found!!!");
		} catch (SQLException e) {
			System.err.println("Cannot Conect to database");
		}
		return tweets;
	}
}
