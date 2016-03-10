import java.io.IOException;
import java.util.List;

import collect.Collect;
import transform.*;
import util.*;
import tests.*;

/**Output relevant calculations from a time series dataset.
 * Used as verification of calculations for now.
 *
 * @author navdeepgill
 */

public class CollectTest {

    //Define initial inputs for Collect
    public static int lag = 2;
    public static  String pathToData = "data/birth.txt";
    public static double lambda = 1.6;

     //Quick check of output from previous methods.
    public static void main (String[] args) throws IOException
    {
        //Make some objects that will show relevant output:

        //Setting up a dataset as a List<Double> for later use:
        List<Double> file = Util.ReadFile(pathToData);

        //Calling on Collect to get a bunch of metrics:
        Collect _tm  = new Collect(pathToData,lag,lag);

        //Test out a transformation on the dataset:
        List<Double> fileLog = Transform.sqrt(file);

        //Get the optimal lambda transform for a particular dataset:
        double optimalLam = BoxCox.lambdaSearch(file);

        //Give lambda manually
        List<Double> fileBoxCox = BoxCox.transform(file,lambda);

        //Output of calculations and verify:
        System.out.println("Optimal Lambda for the time series: " + pathToData + " is " + optimalLam);
        System.out.println("\n");

        System.out.println("Manually calculated Box Cox transformation of data with lambda = " + lambda);
        for(int i = 0; i < fileBoxCox.size(); i++) {
            System.out.println(fileBoxCox.get(i));
        }
        System.out.println("\n");

        System.out.println("Log data of Time Series: " + pathToData);
        for(int i = 0; i < fileLog.size(); i++) {
            System.out.println(fileLog.get(i));
        }
        System.out.println("\n");

        System.out.println("First 10 Rows of Time Series Dataset: " + pathToData);
        for(int i = 0; i < 10; i++) {
            System.out.println(file.get(i));
        }
        System.out.println("\n");

        System.out.println("Number of rows for " + pathToData + " = " + file.size());
        System.out.println("\n");

        System.out.println("Average of Time Series:");
        double average = _tm.getAverage();
        System.out.println(average);
        System.out.println("\n");

        System.out.println("Variance of Time Series:");
        double var = _tm.getVariance();
        System.out.println(var);
        System.out.println("\n");

        System.out.println("Standard Deviation of Time Series:");
        double sd = _tm.getStandardDeviation();
        System.out.println(sd);
        System.out.println("\n");

        System.out.println("Index of Time Series Minimum Value:");
        double min = _tm.getMinIndex();
        System.out.println(min);
        System.out.println("\n");

        System.out.println("Minimum Value of Time Series Based on Index:");
        double minVal = _tm.getMin();
        System.out.println(minVal);
        System.out.println("\n");

        System.out.println("Index of Time Series Maximum Value:");
        double max = _tm.getMaxIndex();
        System.out.println(max);
        System.out.println("\n");

        System.out.println("Maximum Value of Time Series Based on Index:");
        double maxVal = _tm.getMax();
        System.out.println(maxVal);
        System.out.println("\n");

        System.out.println("Autocovariance of Time Series:");
        double autocovar = _tm.getAutocovariance();
        System.out.println(autocovar);
        System.out.println("\n");

        System.out.println("Autocorrelation of Time Series with lag " + lag + ":");
        double autocor = _tm.getAutocorrelation();
        System.out.println(autocor);
        System.out.println("\n");

        System.out.println("Autocorrelation Function of Time Series with lag " + lag + ":");
        double[] acf= _tm.acf(2);
        for(int i = 0; i < acf.length; i++) {
            System.out.println(acf[i]);
        }
        System.out.println("\n");

        System.out.println("Partial Autocorrelation Function of Time Series with lag " +  lag + ":");
        double[] pacf= _tm.pacf();
        for(int i = 0; i < pacf.length; i++) {
            System.out.println(pacf[i]);
        }

        System.out.println("Dickey-Fuller Test with lag " + lag + ":");
        AugmentedDickeyFuller adf = new AugmentedDickeyFuller(file, lag);
        System.out.println(adf.isNeedsDiff());
        System.out.println(adf.getPValue());

    }
}
