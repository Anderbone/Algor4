package SecAns;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] items;
    private int capacity;
    private int size;
    
    // construct an empty randomized queue
    public RandomizedQueue() {
        capacity = 1;
        size = 0;
        updateItems();
    }
    
    private class RandomizedQueueIterator implements Iterator<Item> {
         
         private int[] random;
         private int current;
         
         public RandomizedQueueIterator() {
             random = new int[size];
             for (int i = 0; i < size; ++i) 
                 random[i] = i;
             StdRandom.shuffle(random);
             current = 0;
         }
         
         public boolean hasNext() { return current != random.length; }
         
         public void remove() { 
             throw new UnsupportedOperationException("remove operation unsupported");
         }
         
         public Item next() { 
             if (!hasNext()) throw new NoSuchElementException("no more items");
             return items[random[current++]];
         }
     }
    
    private void updateItems() {
        Item[] newItems = (Item[]) new Object[capacity];
        for (int i = 0; i < size; ++i) 
            newItems[i] = items[i];
        for (int i = size; i < capacity; ++i) 
            newItems[i] = null;
        items = newItems;
    }
    
    // is the queue empty?
    public boolean isEmpty() {
        return size == 0;
    }
    
    // return the number of items on the queue
    public int size() {
        return size;
    }
    
    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException("adding null to queue");
        if (size == capacity) {
            capacity *= 2;
            updateItems();
        }
        items[size++] = item;
    }
    
    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("nothing to dequeue");
        if (size == capacity/4) {
            capacity /= 2;
            updateItems();
        }
        int random = StdRandom.uniform(size);
        Item item = items[random];
        items[random] = items[--size];
        items[size] = null;
        return item;
    }
    
    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("nothing to sample");
        return items[StdRandom.uniform(size)];
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    
    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        for (int i = 0; i < 10; ++i) 
            rq.enqueue(i);
        Iterator<Integer> it = rq.iterator();
        while (it.hasNext()) StdOut.print(it.next() + " ");
        StdOut.println(" size: " + rq.size());
        it = rq.iterator();
        while (it.hasNext()) StdOut.print(it.next() + " ");
        StdOut.println(" size: " + rq.size());
        
        for (int j = 0; j < 3; ++j) {
            for (int i = 0; i < 3; ++i) 
                rq.dequeue();
            it = rq.iterator();
            while (it.hasNext()) StdOut.print(it.next() + " ");
            StdOut.println(" size: " + rq.size());
        }
    }
}