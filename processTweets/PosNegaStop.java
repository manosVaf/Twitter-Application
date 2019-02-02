import java.util.*;
import java.io.*;

public class PosNegaStop {
	public HashMap<String, String> FetchPos(String file){
		HashMap<String, String> positive = new HashMap<String, String>();
		
		StringTokenizer tokenizer;
		
		File f;
		BufferedReader reader = null;
		String line;
		
		try{
			f = new File(file);
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		}catch(NullPointerException e){
			System.out.println("Path to file is wrong!");
		}catch(FileNotFoundException e){
			System.out.println("File not Found!!");
		}
		
		try{
			line = reader.readLine();
			line = reader.readLine();
			while(line != null){
				tokenizer = new StringTokenizer(line);
				if(tokenizer.countTokens() == 3){
					String word = tokenizer.nextToken();
					tokenizer.nextToken();
					String stemWord = tokenizer.nextToken();
					positive.put(word, stemWord);
					line = reader.readLine();
				}else{
					System.err.println("Error on Tokenizer");
				}
			}
		}catch(IOException e) {
			System.err.println("Error on processing file");
		}catch(NullPointerException a){
			System.err.println("Error on processing file");
		}
		return positive;
	}
	
	public HashMap<String, String> FetchNega(String file){
		HashMap<String, String> negative = new HashMap<String, String>();
		
		StringTokenizer tokenizer;
		
		File f;
		BufferedReader reader = null;
		String line;
		
		try{
			f = new File(file);
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		}catch(NullPointerException e){
			System.out.println("Path to file is wrong!");
		}catch(FileNotFoundException e){
			System.out.println("File not Found!!");
		}
		
		try{
			line = reader.readLine();
			line = reader.readLine();
			while(line != null){
				tokenizer = new StringTokenizer(line);
				if(tokenizer.countTokens() == 3){
					String word = tokenizer.nextToken();
					tokenizer.nextToken();
					String stemWord = tokenizer.nextToken();
					negative.put(word, stemWord);
					line = reader.readLine();
				}else{
					System.err.println("Error on Tokenizer");
				}
			}
		}catch(IOException e) {
			System.err.println("Error on processing file");
		}catch(NullPointerException a){
			System.err.println("Error on processing file");
		}
		return negative;
	}
	
	public ArrayList<String> FetchStopWords(String file){
		ArrayList<String> stopWords = new ArrayList<String>();
		File f;
		BufferedReader reader = null;
		String line;
		
		try{
			f = new File(file);
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		}catch(NullPointerException e){
			System.out.println("Path to file is wrong!");
		}catch(FileNotFoundException e){
			System.out.println("File not Found!!");
		}
		
		try{
			line = reader.readLine();
			while(line != null){
				stopWords.add(line);
				line = reader.readLine();
			}
		}catch(IOException e) {
			System.err.println("Error on processing file");
		}catch(NullPointerException a){
			System.err.println("Error on processing file");
		}
		return stopWords;
	}
}
