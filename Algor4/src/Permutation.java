import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomizedQueue<String> s=new RandomizedQueue<String>();
		/*int k=Integer.parseInt(args[0]);
		
		String str;
		while(!StdIn.isEmpty()){
		str=StdIn.readString();
		s.enqueue(str);
		}
		*/
		
		
		int k=3;
		s.enqueue("d");
		s.enqueue("g");
		s.enqueue("a");
		s.enqueue("c");
		s.enqueue("j");
		
		for(int i=0;i<k;i++){
			StdOut.print(s.dequeue());
		}

	}

}
