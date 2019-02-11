package utils;

/**

 *  Java Program to Implement Binary Heap

 */

 

import java.util.NoSuchElementException;


import eveil.Item;
import eveil.Lucas;

 

/** Class BinaryHeap **/

public class BinaryHeap    

{

    /** The number of children each node has **/
    private static final int d = 2;
    private int heapSize;
    private Item[] heap;

 

    /** Constructor **/    
    public BinaryHeap(int capacity)
    {
        heapSize = 0;
        heap = new Item[capacity + 1];
        //Arrays.fill(heap, -1);
    }
    
    public boolean lower(Item i1, Item i2) {return i1.getY()<i2.getY();}
    /** Function to check if heap is empty **/
    public boolean isEmpty( )
    {
        return heapSize == 0;
    }


    /** Check if heap is full **/
    public boolean isFull( )
    {
        return heapSize == heap.length;
    }

 

    /** Clear heap */
    public void makeEmpty( )
    {
        heapSize = 0;
    }

 

    /** Function to  get index parent of i **/
    private int parent(int i) 
    {

        return (i - 1)/d;

    }

 

    /** Function to get index of k th child of i **/

    private int kthChild(int i, int k) 

    {

        return d * i + k;

    }

 

    /** Function to insert element */

    public void insert(Item x)

    {

        if (isFull( ) )

            throw new NoSuchElementException("Overflow Exception");

        /** Percolate up **/

        heap[heapSize++] = x;

        heapifyUp(heapSize - 1);

    }

 

    /** Function to find least element **/

    public Item findMin( )

    {

        if (isEmpty() )

            throw new NoSuchElementException("Underflow Exception");           

        return heap[0];

    }

 

    /** Function to delete min element **/

    public Item deleteMin()

    {

        Item keyItem = heap[0];

        delete(0);

        return keyItem;

    }

 

    /** Function to delete element at an index **/

    public Item delete(int ind)

    {

        if (isEmpty() )

            throw new NoSuchElementException("Underflow Exception");

        Item keyItem = heap[ind];

        heap[ind] = heap[heapSize - 1];

        heapSize--;

        heapifyDown(ind);        

        return keyItem;

    }

 

    /** Function heapifyUp  **/

    private void heapifyUp(int childInd)

    {

        Item tmp = heap[childInd];    

        while (childInd > 0 && lower(tmp, heap[parent(childInd)]))

        {

            heap[childInd] = heap[ parent(childInd) ];

            childInd = parent(childInd);

        }                   

        heap[childInd] = tmp;

    }

 

    /** Function heapifyDown **/

    private void heapifyDown(int ind)

    {

        int child;

        Item tmp = heap[ ind ];

        while (kthChild(ind, 1) < heapSize)

        {

            child = minChild(ind);

            if (lower(heap[child], tmp))

                heap[ind] = heap[child];

            else

                break;

            ind = child;

        }

        heap[ind] = tmp;

    }

 

    /** Function to get smallest child **/

    private int minChild(int ind) 

    {

        int bestChild = kthChild(ind, 1);

        int k = 2;

        int pos = kthChild(ind, k);

        while ((k <= d) && (pos < heapSize)) 

        {

            if (lower(heap[pos], heap[bestChild])) 

                bestChild = pos;

            pos = kthChild(ind, k++);

        }    

        return bestChild;

    }

 

    /** Function to print heap **/

    public void printHeap()

    {

        System.out.print("\nHeap = ");

        for (int i = 0; i < heapSize; i++)

            System.out.print(heap[i] +" ");

        System.out.println();

    }
    
    public int getSize() {return heapSize;}
    public Item getInd(int i) {return heap[i];}
    
    public static void main(String[] args) {
		BinaryHeap heap = new BinaryHeap(47);
		heap.printHeap();
		
		for (int i=0 ; i <42 ; i++) {heap.insert(new Lucas(i));}
		
		heap.printHeap();
		
		//System.out.println(heap.findMin());
		//System.out.println(heap.findMin());
		for (int i=0 ; i <20 ; i++) {heap.deleteMin();}
		//System.out.println(heap.findMin());
		
		for (int i=0 ; i < heap.getSize() ; i++) {
			System.out.println(heap.getInd(i));
		}

	}

}

 

