package secondWeek;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Permutation {

public static void main(String[] args) {
	// TODO Auto-generated method stub
	RandomizedQueue<String> s=new RandomizedQueue<String>();
	int k=Integer.parseInt(args[0]);
	
	String str;
	while(!StdIn.isEmpty()){
	str=StdIn.readString();
	s.enqueue(str);
	}
	
	
	for(int i=0;i<k;i++){
		StdOut.print(s.dequeue());
	}

}
}