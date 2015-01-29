package tools.util.sort;

import java.lang.reflect.Array;
import java.util.*;
import java.util.Map.Entry;

public class Collection {

	public static <K, V extends Comparable<? super V>> SortedSet<Entry<K, V>> mapSortedByValues(Map<K, V> map) {
		SortedSet<Entry<K, V>> sortedEntries = new TreeSet<Entry<K, V>>(new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Entry<K, V> e1, Entry<K, V> e2) {
				int res = e2.getValue().compareTo(e1.getValue());
				return res != 0 ? res : 1; // Special fix to preserve items with equal values
			}
		});
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}

	public static <K extends Comparable<? super K>, V> SortedSet<Entry<K, V>> mapSortedByKey(Map<K, V> map) {
		SortedSet<Entry<K, V>> sortedEntries = new TreeSet<Entry<K, V>>(new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Entry<K, V> e1, Entry<K, V> e2) {
				int res = e2.getKey().compareTo(e1.getKey());
				return res != 0 ? res : 1; // Special fix to preserve items with equal values
			}
		});
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}

	public static <E extends Comparable<? super E>> SortedSet<E> arrayListSortedByValuesDecremental(
			ArrayList<E> inArray) {
		SortedSet<E> sortedEntries = new TreeSet<E>(new Comparator<E>() {
			@Override
			public int compare(E e1, E e2) {
//				int res = e1.compareTo(e2);
//				return res != 0 ? 1 : res; // Special fix to preserve
//											// items with equal values
				int res = e2.compareTo(e1);
				return res != 0 ? res : 1;
			}
		});
		sortedEntries.addAll(inArray);
		return sortedEntries;
	}


	/**
     * this method sort Map<K,V> base values V
     * @param <K>
     * @param <V>
     * @param map usually  this structure is HashMap
     * @return sorted map base on values
     */
	public static <K, V> SortedSet<Entry<K, V>> mapSortedByValues(Map<K, V> map, Comparator<Entry<K, V>> comparator) {
		SortedSet<Entry<K, V>> sortedEntries = new TreeSet<Entry<K, V>>(comparator);
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}

	/**
     * this method incremental sort Map<K,V> base values V
     * @param <K>
     * @param <V>
     * @param map usually  this structure is HashMap
     * @return sorted map base on values
     */
	public static <K, V extends Comparable<? super V>> SortedSet<Entry<K, V>> mapSortedByValuesIncremental(Map<K, V> map) {
		return mapSortedByValues(map, new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Entry<K, V> e1, Entry<K, V> e2) {
				return e1.getValue().compareTo(e2.getValue());
//				return res != 0 ? res : 1; // Special fix to preserve items with equal values
			}
		});
	}

	/**
	 * this method decremental sort Map<K,V> base values V
	 * @param <K>
	 * @param <V>
	 * @param map usually  this structure is HashMap
	 * @return decremental sorted map base on values
	 */
	public static <K, V extends Comparable<? super V>> SortedSet<Entry<K, V>> mapSortedByValuesDecremental(Map<K, V> map) {
		return mapSortedByValues(map, new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Entry<K, V> e1, Entry<K, V> e2) {
				int res = e2.getValue().compareTo(e1.getValue());
				return res != 0 ? res : 1; // Special fix to preserve items with equal values
			}
		});
	}

	/**
	       * this method incremental sort Map<K,V> base values V
	       * @param <K>
	       * @param <V>
	       * @param map usually  this structure is HashMap
	       * @return incremental sorted map base on keys
	       */
	public static <K extends Comparable<? super K>, V> SortedSet<Entry<K, V>> mapSortedByKeyIncremental(Map<K, V> map) {
		SortedSet<Entry<K, V>> sortedEntries = new TreeSet<Entry<K, V>>(new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Entry<K, V> e1, Entry<K, V> e2) {
				int res = e1.getKey().compareTo(e2.getKey());
				return res != 0 ? res : 1; // Special fix to preserve items with equal values
			}
		});
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}

	/**
       * this method decremental sort Map<K,V> base Keys K
       * @param <K>
       * @param <V>
       * @param map usually  this structure is HashMap
       * @return decremental sorted map base on keys
       */
	public static <K extends Comparable<? super K>, V> SortedSet<Entry<K, V>> mapSortedByKeyDecremental(Map<K, V> map) {
		SortedSet<Entry<K, V>> sortedEntries = new TreeSet<Entry<K, V>>(new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Entry<K, V> e1, Entry<K, V> e2) {
				int res = e2.getKey().compareTo(e1.getKey());
				return res != 0 ? res : 1; // Special fix to preserve items with equal values
			}
		});
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}
	
	
	public static <K extends Comparable<? super K>> ArrayList<K[]> sortArrayListBaseOnColumn(ArrayList<K[]> arrayList,int columnIndex,SortType sortType) {		
		ArrayList<K[]> sortedArray=new ArrayList<K[]>();
		HashMap<Integer, K> columnIndexHashMap=new HashMap<Integer,K>();
		for (int i = 0; i < arrayList.size(); i++) {
			K[] currentRow = arrayList.get(i);
			columnIndexHashMap.put(i, currentRow[columnIndex]);
		}
		
		if(sortType== SortType.DECREMENTAL){
			SortedSet<Entry<Integer, K>> sortedEntries = mapSortedByValuesDecremental(columnIndexHashMap);
			for (Entry<Integer, K> entry : sortedEntries){
				sortedArray.add(arrayList.get(entry.getKey()));
	  	  	}
		}
		else{
			SortedSet<Entry<Integer, K>> sortedEntries = mapSortedByValuesIncremental(columnIndexHashMap);
			for (Entry<Integer, K> entry : sortedEntries){
				sortedArray.add(arrayList.get(entry.getKey()));
	  	  	}
		}
		
		return sortedArray;
	}
	
	public static <K extends Comparable<? super K>> K[][] sortArrayListBaseOnColumn(K[][] array,int columnIndex,SortType sortType) {
		@SuppressWarnings("unchecked")
		K[][] sortedArray=(K[][])Array.newInstance(array[0][0].getClass(),array.length,array[0].length);
		HashMap<Integer, K> columnIndexHashMap=new HashMap<Integer,K>();
		for (int i = 0; i < array.length; i++) {
			columnIndexHashMap.put(i, array[i][columnIndex]);
		}
		
		if(sortType== SortType.DECREMENTAL){
			SortedSet<Entry<Integer, K>> sortedEntries = mapSortedByValuesDecremental(columnIndexHashMap);
			int index = 0;
			for (Entry<Integer, K> entry : sortedEntries){
				sortedArray[index]=array[entry.getKey()];
				index++;
	  	  	}
		}
		else{
			SortedSet<Entry<Integer, K>> sortedEntries = mapSortedByValuesIncremental(columnIndexHashMap);
			int index=0;
			for (Entry<Integer, K> entry : sortedEntries){
				sortedArray[index]=array[entry.getKey()];
				index++;
	  	  	}
		}
		
		return sortedArray;
	}
	
	public static <E extends Comparable<? super E>> SortedSet<E> arrayListSortedDeccremental(
			ArrayList<E> inArray) {
		SortedSet<E> sortedEntries = new TreeSet<E>(new Comparator<E>() {
			@Override
			public int compare(E e1, E e2) {
//				return res != 0 ? 1 : res; // Special fix to preserve
//											// items with equal values
				int res = e2.compareTo(e1);
				return res != 0 ? res : 1; 
			}
		});
		sortedEntries.addAll(inArray);
		return sortedEntries;
	}
	
	public static <E extends Comparable<? super E>> SortedSet<E> set(
			Set<E> inSet,final SortType sortType) {
		SortedSet<E> sortedEntries = new TreeSet<E>(new Comparator<E>() {
			@Override
			public int compare(E e1, E e2) {
//				return res != 0 ? 1 : res; // Special fix to preserve
//											// items with equal values
				int res;
				if(sortType== SortType.DECREMENTAL)
					res = e2.compareTo(e1);
				else
					res = e1.compareTo(e2);
				return res != 0 ? res : 1; 
			}
		});
		sortedEntries.addAll(inSet);
		return sortedEntries;
	}
	
	public static <E extends Comparable<? super E>> SortedSet<E> collection(java.util.Collection<E> collection,
			final SortType sortType) {
		SortedSet<E> sortedEntries = new TreeSet<E>(new Comparator<E>() {
			@Override
			public int compare(E e1, E e2) {
//				return res != 0 ? 1 : res; // Special fix to preserve
//											// items with equal values
				int res;
				if(sortType== SortType.DECREMENTAL)
					res = e2.compareTo(e1);
				else
					res = e1.compareTo(e2);
				return res != 0 ? res : 1; 
			}
		});
		sortedEntries.addAll(collection);
		return sortedEntries;
	}
	
	public enum SortType {
		INCREMENTAL("incremental"),
		DECREMENTAL("decremental");
		
		private String string=new String();
		
		SortType(String string){
			this.string=string;
		}
		
		public String getValue(){
			return string;
		}
	}

}
