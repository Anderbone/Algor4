
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
		
		private int N;
		
		//包括了 首行向上的虚拟点以及末行向下的虚拟点
		private WeightedQuickUnionUF FirstUF;
		
		//仅含有首行向上的虚拟点，防止倒流
		private WeightedQuickUnionUF SecondUF;
		
		
		//存放是否连通，true为open
		private boolean NN[];
		
		private int count;
		
	   public Percolation(int N){
		   // create n-by-n grid, with all sites blocked
		   this.N=N;
		   NN=new boolean[N*N];
		   if(N<=0){
			   throw new  IllegalArgumentException();
		   }
		  
		   FirstUF=new WeightedQuickUnionUF(N*N+2);
		   SecondUF=new WeightedQuickUnionUF(N*N+1);
		   
	   }
	   public void open(int row, int col)  {
		   // open site (row i, col j) if it is not open already
		   if(row<1 || row>N || col<1 || col>N){  
	            throw new IndexOutOfBoundsException();  
	        }  
		   
		   //如果没有open，就open这个点
		   int i=(row-1)*N+col-1;
		   if(NN[i]){
			   return;
		   }
		   NN[i]=true;
		   count ++;
		   //第一行的都与最上方虚拟点N*N相连，最后一行与最下方虚拟点相连
		   if(row==1){
			   FirstUF.union(i,N*N);
			   SecondUF.union(i,N*N);
		   }
		   if(row==N){
			   FirstUF.union(i, N*N+1);
		   }
		   
		   //left
		   if ((col-1-1)>=0&&(NN[i-1])){
			    FirstUF.union(i, i-1);
			    SecondUF.union(i, i-1);
		   }
		   //right
		   if ((col-1+1<=N-1)&&(NN[i+1])){      
				FirstUF.union(i, i+1);
				SecondUF.union(i, i+1);
			   }
		   
		   //up
		   if (((row-1-1)>=0)&&(NN[i-N])){
			    FirstUF.union(i, i-N);
			    SecondUF.union(i, i-N);
			   }
		   //down
		   if ((row-1+1)<N&&(NN[i+N])){
				FirstUF.union(i, i+N);
				SecondUF.union(i, i+N);
		   }
	   }
		   
	   public boolean isOpen(int row, int col)  {
		   // is site (row, col) open?
		   if(row<1 || row>N || col<1 ||  col>N){  
	            throw new IndexOutOfBoundsException();  
	        }  

		   return NN[(row-1)*N+(col-1)];
		   }
		  
	   
	   public boolean isFull(int row, int col){
		   // is site (row, col) full?
		   if(row<1 || row>N || col<1 ||  col>N){  
	            throw new IndexOutOfBoundsException();  
		   }
		   int i=(row-1)*N+col-1;
		   return SecondUF.connected(N*N, i);
	   }
	   public  int numberOfOpenSites() {
		   // number of open sites
		   return count;
	   }
	   public boolean percolates()  {
		   // does the system percolate?
		   return FirstUF.connected(N*N, N*N+1);
		   
	   }

	   public static void main(String[] args) {
		   // test client (optional)
		   
		   Percolation p=new Percolation(2);
		   p.open(1, 1);
		   boolean a1=p.percolates();
		   System.out.println(a1);
		   p.open(2, 1);
		   p.percolates();
		   boolean a2=p.percolates();
		   System.out.println(a2);
		   
	   }

}
