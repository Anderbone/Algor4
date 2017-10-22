
  import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
  import edu.princeton.cs.algs4.StdRandom;
 import edu.princeton.cs.algs4.StdStats;
  



public class PercolationStats {
	    private double mean;  
	    private double stddev;  
	    private double confLow;  
	    private double confUp;  
	    
	    private int trys = 0;
	    private double[] successTrials;
	
	
	
	 public PercolationStats(int N, int T) {
		 // perform trials independent experiments on an n-by-n grid
		 if(N <= 0 || T <= 0){  
	            throw new IllegalArgumentException();  
	        }  
		 trys=T;
		 successTrials=new double[trys];
		 for(int  i = 0; i < T; i++) {
			    successTrials[i] = 0;
			    Percolation percolationTries = new Percolation(N);
			  
			     while (!percolationTries.percolates()) {
			      int a = StdRandom.uniform(1, N + 1);
			      int b = StdRandom.uniform(1, N + 1);
			      
			      while (percolationTries.isOpen(a, b)) {
			         a = StdRandom.uniform(1, N + 1);
			         b = StdRandom.uniform(1, N + 1);
			          }
			    
			     percolationTries.open(a, b);
			     successTrials[i]++;
			               }
			    
			               successTrials[i] = successTrials[i] / (N * N);
			           }
		 mean = StdStats.mean(successTrials);
		 
		 
	 }
	   public double mean(){
		   // sample mean of percolation threshold
		   
		   return mean;
	   }
	   public double stddev()    {
		   // sample standard deviation of percolation threshold
		   stddev =  StdStats.stddev(successTrials);
		   return stddev;
	   }
	   public double confidenceLo()  {
		   // low  endpoint of 95% confidence interval
		   return mean - ((1.96 * stddev) / Math.sqrt(trys));
	   }
	   public double confidenceHi()     {
		   // high endpoint of 95% confidence interval
		   return mean + ((1.96 * stddev) / Math.sqrt(trys));
	   }

	   public static void main(String[] args)   {
		   // test client (described below)
		    int N = StdIn.readInt();  
	        int T = StdIn.readInt();  
	        PercolationStats pers = new PercolationStats(N,T);  
	        StdOut.println("mean=" + pers.mean());  
	        StdOut.println("stddev=" + pers.stddev());  
	        StdOut.println("95% confidence interval=" + pers.confidenceLo() + ", " + pers.confidenceHi());  
		 
	   }

}




