import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame;  

public class TweetsStats extends ApplicationFrame
{
 
	private static final long serialVersionUID = 1L;
	private float pos;
	private float nega;
   public TweetsStats( String applicationTitle , float positive, float negative)
   {
      super( applicationTitle );  
     pos = positive;
     nega = negative;
      JFreeChart barChart = ChartFactory.createBarChart(
         "",           
         "Percentage of Total Tweets",            
         "Tweets",            
         createDataset(),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
      setContentPane( chartPanel ); 
   }
   private CategoryDataset createDataset( )
   {
      final String posi = "POSITIVE%";        
      final String negat = "NEGATIVE";              
      
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  

      dataset.addValue(pos, posi , "" ); 
      dataset.addValue(nega, negat , "" ); 

      return dataset; 
   }
}