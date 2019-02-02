
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame;  

public class BarChart_AWT extends ApplicationFrame
{
 
	private static final long serialVersionUID = 1L;
	private ArrayList<Result> results;
   public BarChart_AWT( String applicationTitle , ArrayList<Result> result)
   {
      super( applicationTitle );  
      results = result;
      JFreeChart barChart = ChartFactory.createBarChart(
         "",           
         "Days",            
         "Positive Tweets",            
         createDataset(),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
      setContentPane( chartPanel ); 
   }
   private CategoryDataset createDataset( )
   {
      final String suriza = "������";        
      final String nd = "��� ����������";        
      final String tsipras = "�������";        
      final String day1 = "������ 24 �����";        
      final String day2 = "��������� 25 �����";        
      final String day3 = "������� 26 �����";        
      final String day4 = "������� 27 �����";
      final String day5 = "������� 28 �����";
      final String day6 = "����� 29 �����";
      final String day7 = "������� 30 �����";
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  

      dataset.addValue(results.get(0).getResults().get(0).getPositiveTweets(), suriza , day1);        
      dataset.addValue(results.get(1).getResults().get(0).getPositiveTweets(), suriza , day2) ;        
      dataset.addValue(results.get(2).getResults().get(0).getPositiveTweets(), suriza , day3); 
      dataset.addValue(results.get(3).getResults().get(0).getPositiveTweets(), suriza , day4); 
      dataset.addValue(results.get(4).getResults().get(0).getPositiveTweets(), suriza , day5);
      dataset.addValue(results.get(5).getResults().get(0).getPositiveTweets(), suriza , day6);
      dataset.addValue(results.get(6).getResults().get(0).getPositiveTweets(), suriza , day7);

      dataset.addValue(results.get(0).getResults().get(1).getPositiveTweets(), nd , day1);       
      dataset.addValue(results.get(1).getResults().get(1).getPositiveTweets(), nd , day2);  
      dataset.addValue(results.get(2).getResults().get(1).getPositiveTweets(), nd , day3); 
      dataset.addValue(results.get(3).getResults().get(1).getPositiveTweets(), nd , day4);  
      dataset.addValue(results.get(4).getResults().get(1).getPositiveTweets(), nd , day5);  
      dataset.addValue(results.get(5).getResults().get(1).getPositiveTweets(), nd , day6); 
      dataset.addValue(results.get(6).getResults().get(1).getPositiveTweets(), nd , day7);   

      dataset.addValue(results.get(0).getResults().get(1).getPositiveTweets(), tsipras , day1);
      dataset.addValue(results.get(1).getResults().get(1).getPositiveTweets(), tsipras , day2);   
      dataset.addValue(results.get(2).getResults().get(1).getPositiveTweets(), tsipras , day3);   
      dataset.addValue(results.get(3).getResults().get(1).getPositiveTweets(), tsipras , day4);   
      dataset.addValue(results.get(4).getResults().get(1).getPositiveTweets(), tsipras , day5);   
      dataset.addValue(results.get(5).getResults().get(1).getPositiveTweets(), tsipras , day6);   
      dataset.addValue(results.get(6).getResults().get(1).getPositiveTweets(), tsipras , day7);   


      return dataset; 
   }
}