package secondWeek;
import java.util.Iterator;
import java.util.NoSuchElementException;

    
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
private Node first;
private Node last;
private int N;

public Deque(){
	first=null;
	last=null;
	N=0;
	
}


private class Node{
	Item item;
	Node upnext;
	Node downnext;
}

public boolean isEmpty(){
	return N==0;
}
public int size(){
	return N;
}
public void addFirst(Item item){
	if(item==null){
		throw new NullPointerException();
	}
	Node oldfirst=first;
	first=new Node();
	first.item=item;
	first.downnext=oldfirst;
	first.upnext=null;
	
	if(isEmpty()) last=first;
	else oldfirst.upnext=first;	
	
	N++;
}
public void addLast(Item item){
	if(item==null){
		throw new NullPointerException();
	}
	Node oldlast=last;
	last=new Node();
	last.item=item;
	last.downnext=null;
	last.upnext=oldlast;
	if(isEmpty()) first=last;
	else oldlast.downnext=last;
	N++;
}
public Item removeFirst(){
   if ( isEmpty()) {
          throw new NoSuchElementException ();
   }
	Item item=first.item;
	 if ( first. downnext != null ) {
         first. downnext. upnext = null ; // avoid loitering
  }
	first=first.downnext;
	if(isEmpty()) last=null;
	N--;
	if ( isEmpty()) {
        last = null ;
	}
	return item;

}
public Item removeLast(){
	  if ( isEmpty()) {
          throw new NoSuchElementException ();
   }
	Item item=last.item;
	
	 if ( last. upnext != null ) {
         last. upnext. downnext = null ; // avoid loitering
  }
	 last=last.upnext;
	if(isEmpty()) first=null;
	N--;
	return item;
	
}
public Iterator<Item> iterator(){
	return new DequeIterator();
	
}
private class DequeIterator implements Iterator<Item>{
	private Node current = first;
	public boolean hasNext(){
		return current!=null;
	}
	public Item next(){
		if ( current == null || current .item == null) {
            throw new NoSuchElementException ();
     }
		Item item=current.item;
		current=current.downnext;
		return item;
	}
    public void remove () {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException ();
     }
	
}

public static void main(String[] args) {
	// TODO Auto-generated method stub
//		Deque<String> q=new Deque<String>();
//		q.addFirst("2");
//		q.addFirst("4");
//		q.addFirst("5");
//		q.addLast("7");
//		q.addLast("8");
//		Iterator<String> iter1=q.iterator();
//		while(iter1.hasNext()){
//			System.out.println("first time "+iter1.next());
//		}
//		System.out.println("first time number "+q.size());
//		
//		q.removeFirst();
//		q.removeLast();
//		
//		Iterator<String> iter2=q.iterator();
//		while(iter2.hasNext()){
//			System.out.println("second time "+iter2.next());
//		}
//		System.out.println("second time number "+q.size());
	Deque<String> deque = new Deque<String>();

    while (!StdIn.isEmpty())
    {
        String item = StdIn.readString();
        if (!item.equals("-"))
            deque.addLast(item);
        else if (!deque.isEmpty())
            deque.removeFirst();
        for (String str : deque)
            StdOut.print(str + " ");
        StdOut.println("\t" + deque.isEmpty());
    }
	
}
}