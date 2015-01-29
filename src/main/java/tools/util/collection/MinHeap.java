package tools.util.collection;

import java.util.Arrays;

/****************************************************************************
 *       This demonstrates binary heap operations along with the heapSort.
 *
 *****************************************************************************/
@SuppressWarnings("unchecked")
public class MinHeap<AnyType extends Comparable<AnyType>>
{
   private static final int CAPACITY = 2;

   private int size;            // Number of elements in heap
   private AnyType[] heap;     // The heap array

   public MinHeap()
   {
      size = 0;
      heap = (AnyType[]) new Comparable[CAPACITY];
   }

 /**
  * Construct the binary heap given an array of items.
  */
   public MinHeap(AnyType[] array)
   {
      size = array.length;
      heap = (AnyType[]) new Comparable[array.length+1];

      System.arraycopy(array, 0, heap, 1, array.length);//we do not use 0 index

      buildHeap();
   }
 /**
  *   runs at O(size)
  */
   private void buildHeap()
   {
      for (int k = size/2; k > 0; k--)
      {
         percolatingDown(k);
      }
   }
   private void percolatingDown(int k)
   {
      AnyType tmp = heap[k];
      int child;

      for(; 2*k <= size; k = child)
      {
         child = 2*k;

         if(child != size &&
            heap[child].compareTo(heap[child + 1]) > 0) child++;

         if(tmp.compareTo(heap[child]) > 0)  heap[k] = heap[child];
         else
                break;
      }
      heap[k] = tmp;
   }

 /**
  *  Sorts a given array of items.
  */
   public void heapSort(AnyType[] array)
   {
      size = array.length;
      heap = (AnyType[]) new Comparable[size+1];
      System.arraycopy(array, 0, heap, 1, size);
      buildHeap();

      for (int i = size; i > 0; i--)
      {
         AnyType tmp = heap[i]; //move top item to the end of the heap array
         heap[i] = heap[1];
         heap[1] = tmp;
         size--;
         percolatingDown(1);
      }
      for(int k = 0; k < heap.length-1; k++)
         array[k] = heap[heap.length - 1 - k];
   }

   public AnyType getMin(){
	   if (size == 0) throw new RuntimeException();
	   return heap[1];
   }
   
 /**
  * Deletes the top item
  */
   public AnyType deleteMin() throws RuntimeException
   {
      if (size == 0) throw new RuntimeException();
      AnyType min = heap[1];
      heap[1] = heap[size--];
      percolatingDown(1);
      return min;
	}

 /**
  * Inserts a new item
  */
   public void insert(AnyType x)
   {
      if(size == heap.length - 1) doubleSize();

      //Insert a new item to the end of the array
      int pos = ++size;

      //Percolate up
      for(; pos > 1 && x.compareTo(heap[pos/2]) < 0; pos = pos/2 )
         heap[pos] = heap[pos/2];

      heap[pos] = x;
   }
   private void doubleSize()
   {
      AnyType [] old = heap;
      heap = (AnyType []) new Comparable[heap.length * 2];
      System.arraycopy(old, 1, heap, 1, size);
   }

   public String toString()
   {
      String out = "";
      for(int k = 1; k <= size; k++) out += heap[k]+" ";
      return out;
   }

   public static void main(String[] args)
   {
	  
      
      
      MinHeap<String> h = new MinHeap<String>();

      h.insert("p");
      h.insert("r");
      h.insert("i");
      h.insert("o");
      System.out.println(h);
      System.out.println("min:"+h.getMin());
      h.deleteMin();
      System.out.println(h);


      
      MinHeap<Integer> hi = new MinHeap<Integer>();
      hi.insert(5);
      hi.insert(1);
      hi.insert(4);
      hi.insert(3);
      System.out.println(hi);
      System.out.println("min:"+hi.getMin());
      hi.deleteMin();
      System.out.println(hi);
      
      MinHeap<KeyValue<String, Integer>> keyValueMinHeap=new MinHeap<KeyValue<String,Integer>>();
      keyValueMinHeap.insert(new KeyValue<String, Integer>("salam", 2));
      keyValueMinHeap.insert(new KeyValue<String, Integer>("hey", 1));
      keyValueMinHeap.insert(new KeyValue<String, Integer>("dast", 5));
      System.out.println(keyValueMinHeap.getMin().getKeyValue('\t'));
      
      MinHeap<Integer> tmp = new MinHeap<Integer>();
      Integer[] a = {4,7,7,7,5,0,2,3,5,1};
      tmp.heapSort(a);
      System.out.println(Arrays.toString(a));
   }
}