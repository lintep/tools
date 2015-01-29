package tools.util.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class RandomSet {
	
	static Random random = new Random();
	
	public static int getRandom(int n) {
		return random.nextInt(n);
	}

	public static List<HashSet<Integer>> getPartitionedRandomIntegerSet(int setSize, int partionCount) {
		List<HashSet<Integer>> result=new ArrayList<HashSet<Integer>>(partionCount);
		ArrayList<Integer> randomIntegerSet = getRandomIntegerSet(setSize, setSize);
		if(setSize%partionCount>0){
			ArrayList<Integer> remainedBlock = getRandomIntegerSet(setSize%partionCount, partionCount);
			for (int i = 0; i < partionCount; i++) {
				HashSet<Integer> newSet=new HashSet<Integer>();
				for (int j = i*(int)(setSize/partionCount); j < (i+1)*(int)(setSize/partionCount); j++) {
					newSet.add(randomIntegerSet.get(j));
				}
				if(remainedBlock.indexOf(i)>=0)
					newSet.add(randomIntegerSet.get(setSize-setSize%partionCount+remainedBlock.indexOf(i)));
				result.add(newSet);
			}
		}
		else
			for (int i = 0; i < partionCount; i++) {
				HashSet<Integer> newSet=new HashSet<Integer>();
				for (int j = i*(int)(setSize/partionCount); j < (i+1)*(int)(setSize/partionCount); j++) {
					newSet.add(randomIntegerSet.get(j));
				}
				result.add(newSet);
			}
		return result;
	}
	
	public static ArrayList<Integer> getRandomIntegerSet(int count, int ranMaxRang) {
		ArrayList<Integer> result = new ArrayList<Integer>(count);
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for (Integer i = 0; i < ranMaxRang; i++) {
			numbers.add(i);
		}
		for (int i = 0; i < count; i++) {
			int randIndex = random.nextInt(numbers.size());
			result.add(numbers.get(randIndex));
			numbers.remove(randIndex);
		}
		return result;
	}

	public static ArrayList<HashSet<Integer>> getRandomIntegerSet(int setCount,int setMinSize,int setMaxSize, int ranMaxRang, WithDuplication withDuplicationInSets) throws Exception {
		if(setCount<0 || setMinSize<0 || setMaxSize<0 || ranMaxRang<2)
			throw new Exception("incorrect parameters.");
		ArrayList<HashSet<Integer>> result = new ArrayList<HashSet<Integer>>();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for (Integer i = 0; i < ranMaxRang; i++) {
			numbers.add(i);
		}

		int[] setSize=new int[setCount];
		if(setMaxSize!=0){
			for (int i = 0; i < setCount; i++) {
				int rand = setMinSize+random.nextInt(setMaxSize-setMinSize);
				result.add(i, new HashSet<Integer>(rand));
				setSize[i]=rand;
		    }
		}
		else{
			for (int i = 0; i < setCount; i++) {
				result.add(i, new HashSet<Integer>(ranMaxRang/setCount));
				setSize[i]=ranMaxRang/setCount;
		    }
		}

		if (withDuplicationInSets == WithDuplication.NO) {
			int setCounter = 0;
			for (int i = 0; i < ranMaxRang; i++) {
				int randIndex = random.nextInt(numbers.size());
				if (result.get(setCounter).size() == setSize[setCounter]){
					setCounter++;
					if(setCounter>=setCount)
						break;
				}
				result.get(setCounter).add(new Integer(numbers.get(randIndex)));
				numbers.remove(randIndex);
			}
		}
		else{
			for (int setIndex = 0; setIndex < setSize.length; setIndex++) {
				for (int i = 0; i < ranMaxRang; i++) {
					int randIndex = random.nextInt(numbers.size());
					result.get(setIndex).add(
							new Integer(numbers.get(randIndex)));
					if(result.get(setIndex).size()>setSize[setIndex])
						break;
					numbers.remove(randIndex);
				}
				numbers.clear();
				for (Integer i = 0; i < ranMaxRang; i++) {
					numbers.add(i);
				}
			}
		}
		return result;
	}
	
	/*public static <E> HashSet<E> getRandomSubSet(HashSet<E> inSet,int count) {
		HashSet<E> result = new HashSet<E>(count);
		Random random = new Random();
		HashMap<Integer,E> inSetValues = new HashMap<Integer,E>();
		int index = 0;
		for (E e : inSet) {
			inSetValues.put(index, e);
			index++;
		}
		for (int i = 0; i < count; i++) {
			int randIndex = random.nextInt(inSetValues.size());
			result.add(inSetValues.get(randIndex));
			inSetValues.remove(randIndex);
		}
		return result;
	}
	
	public static <E> HashSet<E> getRandomSubSet(HashSet<E> inSet,int count) {
		HashSet<E> result = new HashSet<E>(count);
		Random random = new Random();
		HashMap<Integer,E> inSetValues = new HashMap<Integer,E>();
		int index = 0;
		for (E e : inSet) {
			inSetValues.put(index, e);
			index++;
		}
		for (int i = 0; i < count; i++) {
			int randIndex = random.nextInt(inSetValues.size());
			result.add(inSetValues.get(randIndex));
			inSetValues.remove(randIndex);
		}
		return result;
	}*/
	
	public static enum WithDuplication  {
		YES("yes"),
		NO("no");
		
		private String state;
		
		WithDuplication(String state){
			this.state=state;
		}
		
		public String getValue(){
			return state;
		}
	}
}
