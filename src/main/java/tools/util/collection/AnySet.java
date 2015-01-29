package tools.util.collection;

import java.util.ArrayList;
import java.util.HashSet;

public class AnySet {
	
	public static <E> ArrayList<E> getSubSet(ArrayList<E> inArrayList,HashSet<Integer> arraySelectIndex) {
		 ArrayList<E> outArrayList=new ArrayList<E>(arraySelectIndex.size());
		for (Integer index : arraySelectIndex) {
			outArrayList.add(inArrayList.get(index));
		}
		return outArrayList;
	}
	
	public static <E> HashSet<E> getSetsIntersection(HashSet<E> ... sets) {
		HashSet<E> outSet=new HashSet<E>(sets[0]);
		
		for (HashSet<E> set : sets) {
			outSet.retainAll(set);
		}
		
		return outSet;
	}
	
	public static <E> HashSet<E> getSetsUnion(HashSet<E> ... sets) {
		HashSet<E> outSet=new HashSet<E>();
		for (HashSet<E> set : sets) {
			for (E e : set) {
				outSet.add(e);
			}
		}
		return outSet;
	}
	
	public static <E> HashSet<E> getRandomSubSet(HashSet<E> inSet,int count) {
		HashSet<E> outSet=new HashSet<E>();
		
		ArrayList<E> temp=new ArrayList<E>(inSet);
		for (Integer index : RandomSet.getRandomIntegerSet(count, inSet.size())) {
			outSet.add(temp.get(index));
		}
		return outSet;
	}
}
