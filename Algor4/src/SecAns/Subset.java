package SecAns;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
        
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        String[] str = StdIn.readAllStrings();
        StdRandom.shuffle(str);
        for (int i = 0; i < k; ++i)
            rq.enqueue(str[i]);
        for (int i = 0; i < k; ++i)
            StdOut.println(rq.dequeue());
    }
}