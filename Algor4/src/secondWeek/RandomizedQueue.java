package secondWeek;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

private int N;
private Item[] a;
public RandomizedQueue(){
	a = (Item[]) new Object[1];
	N=0;
}
public boolean isEmpty(){
	return N==0;
	
}
public int size(){
	return N;
}
private void resize(int max){
	Item[] temp=(Item[]) new Object[max];
	for(int i=0;i<N;i++){
		temp[i]=a[i];
	}
		a=temp;
	
}
public void enqueue(Item item){
	if(item==null){
		throw new NullPointerException();
	}
	if(N==a.length) resize(2*a.length);
	a[N++]=item;
}
public Item dequeue(){
	if ( isEmpty()) {
        throw new NoSuchElementException ();
 }
	int i=StdRandom.uniform(N);
	Item temp=a[N-1];
	a[N-1]=a[i];
	a[i]=temp; 
	
	Item item=a[--N];
	a[N]=null;
	if(N>0 && N==a.length/4) resize(a.length/2);
	return item;
	
}
public Item sample(){
	if ( isEmpty()) {
        throw new NoSuchElementException ();
 }
	int i=StdRandom.uniform(N);
	return a[i];
	
}

public Iterator<Item> iterator(){
	return new RandomIterator();
}

private class RandomIterator implements Iterator<Item>{
	int n=N;
	Item[] ai;
	
	public void remove () {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException ();
     }
	
	
	
	public RandomIterator(){
		ai=(Item[])new Object[N];
		for(int i=0;i<N;i++){
			ai[i]=a[i];
		}
		StdRandom.shuffle(ai);
	}
	
	public boolean hasNext(){
		return n>0;
	}
	public Item next(){
		if ( n==0 ) {
            throw new NoSuchElementException ();
		}
		
		return ai[--n];
	}
}

public static void main(String[] args) {
	// TODO Auto-generated method stub
//		RandomizedQueue<Integer> r=new RandomizedQueue<Integer>();
//		r.enqueue(3);
//		r.enqueue(4);
//		r.enqueue(6);
//		r.enqueue(2);
//		r.dequeue();
//		Iterator<Integer> iter1=r.iterator();
//		while(iter1.hasNext()){
//			System.out.println("first time "+iter1.next());
//		}
//		System.out.println("first time number "+r.size());
		RandomizedQueue<String> rq = new RandomizedQueue<String>();

    while (!StdIn.isEmpty())
    {
        String item = StdIn.readString();
        if (!item.equals("-"))
            rq.enqueue(item);
        else if (!rq.isEmpty())
            rq.dequeue();
        for (String str : rq)
            StdOut.print(str + " ");
        StdOut.println("\t" + rq.isEmpty());
    }

}


}