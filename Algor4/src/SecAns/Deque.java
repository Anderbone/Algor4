package SecAns;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    
    private Node first;
    private Node last;
    private Node nil;
    private int size;
    
    private class Node {
        
        private Node prev;
        private Node next;
        private Item item;
        
        Node(Node prev, Node next, Item item) {
            this.prev = prev;
            this.next = next;
            this.item = item;
        }
        
    }
    
     private class DequeIterator implements Iterator<Item> {
         
         private Node current = first;
         
         public boolean hasNext() { return current.next != null; }
         
         public void remove() { 
             throw new UnsupportedOperationException("remove operation unsupported");
         }
         
         public Item next() { 
             if (!hasNext()) throw new NoSuchElementException("no more items");
             Item item = current.item;
             current = current.next;
             return item;
         }
     }
    
    // construct an empty deque
    public Deque() {
        nil = new Node(null, null, null);
        first = nil; 
        last = nil;
        size = 0;
    }
    
    // is the deque empty?
    public boolean isEmpty() {
        return first.item == null && last.item == null;
    }
    
    // return the number of items on the deque
    public int size() {
        return size;
    }
    
    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException("adding null to first");
        Node newFirst = new Node(nil, first, item);
        if (isEmpty()) {
            last = newFirst;
            first = newFirst;
        } else {
            first.prev = newFirst;
            first = newFirst;
        }
        ++size;
    }
    
    // add the item to the end
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException("adding null to last");
        Node newLast = new Node(last, nil, item);
        if (isEmpty()) { 
            first = newLast;
            last = newLast;
        } else {
            last.next = newLast;
            last = newLast;
        }
        ++size;
    }
    
    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("nothing to remove");
        Item item = first.item;
        first = first.next;
        if (first.item == null) {
            last = nil;
        } else {
            first.prev = nil;
        }
        --size;
        return item;
    }
    
    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("nothing to remove");
        Item item = last.item;
        last = last.prev;
        if (last.item == null) {
            first = nil;
        } else {
            last.next = nil;
        }
        --size;
        return item;
    }
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    
    // unit testing
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        for (int i = 0; i < 6; i += 2) {
            deque.addFirst(i); 
            deque.addLast(i+1);
        }
        Iterator<Integer> it = deque.iterator();
        while (it.hasNext()) StdOut.print(it.next() + " ");
        StdOut.println(" size: " + deque.size());
        
        for (int i = 0; i < 3; ++i) {
            deque.removeLast(); 
            deque.removeFirst();
        }
        StdOut.println("size: " + deque.size());
        
        for (int i = 0; i < 6; i += 2) {
            deque.addLast(i); 
            deque.addFirst(i+1);
        }
        it = deque.iterator();
        while (it.hasNext()) StdOut.print(it.next() + " ");
        StdOut.println(" size: " + deque.size());
    }
}