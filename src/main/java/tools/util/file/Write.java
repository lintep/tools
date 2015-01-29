package tools.util.file;

import tools.util.Logger;
import tools.util.sort.Collection.SortType;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;

public class Write {

	public static void writeObjectToFile(Object obj, String outputFileAddress) {
		try {
			OutputStream file = new FileOutputStream(outputFileAddress);
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			try {
				output.writeObject(obj);
			} finally {
				output.close();
			}
		} catch (IOException ex) {
			Logger.log("Cannot perform output.");
		}
	}	

	public static <K, V extends Comparable<? super V>> void mapToTextFileSortedByValue(
			Map<K, V> map, String fileAddress,
			Boolean false_first_value__true_first_value) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(fileAddress));
			if (false_first_value__true_first_value)
				for (Entry<K, V> entry : tools.util.sort.Collection
						.mapSortedByValues(map))
					out.println(entry.getKey().toString() + " "
							+ entry.getValue());
			else
				for (Entry<K, V> entry : tools.util.sort.Collection
						.mapSortedByValues(map))
					out.println(entry.getKey().toString() + " "
							+ entry.getValue());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nwrite " + fileAddress + " complete");
	}

	public static <K extends Comparable<? super K>, V> void mapToTextFileSortedByKey(
			Map<K, V> map, String fileAddress,
			Boolean false_first_value__true_first_value) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(fileAddress));
			if (false_first_value__true_first_value)
				for (Entry<K, V> entry : tools.util.sort.Collection
						.mapSortedByKey(map))
					out.println(entry.getKey().toString() + " "
							+ entry.getValue());
			else
				for (Entry<K, V> entry : tools.util.sort.Collection
						.mapSortedByKey(map))
					out.println(entry.getKey().toString() + " "
							+ entry.getValue());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nwrite " + fileAddress + " complete");
	}

	public static <K> void setToTextFile(java.util.Set<K> inputSet,
			String fileAddress, boolean append) {
		try {
			tools.util.Time.setStartTimeForNow();
			PrintWriter out = new PrintWriter(new FileWriter(fileAddress,
					append));
			for (K k : inputSet) {
				out.println(k);
			}
			out.close();
			System.out.println("write compelet: " + inputSet.size()
					+ " DocID set to " + fileAddress + " on "
					+ tools.util.Time.getTimeLengthForNow() + " ms.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static <K extends Comparable<? super K>> void setToTextFileSortedByValue(
			java.util.Set<K> set, String fileAddress, SortType sortType) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(fileAddress));
			for (K k : tools.util.sort.Collection.set(set, sortType)) {
				out.println(k);
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nwrite " + fileAddress + " complete");
	}

	public static <E> void arrayListToTextFile(ArrayList<E> inputArrayList,
			String fileAddress, boolean append) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(fileAddress,
					append));
			for (int i = 0; i < inputArrayList.size(); i++) {
				E array_element = inputArrayList.get(i);
				out.println(array_element.toString());
			}
			out.close();
			System.out.println("write compelet: " + inputArrayList.size()
					+ " entity to " + fileAddress);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void arrayToTextFile(String[] inputArrayList,
			String fileAddress, boolean append) {
		try {

			BufferedWriter bw = new BufferedWriter(new FileWriter(fileAddress,
					append));
			for (int i = 0; i < inputArrayList.length; i++) {
				bw.write(inputArrayList[i]);
				bw.write(System.getProperty("line.separator"));
			}
			bw.close();
			System.out.println("write compelet: " + inputArrayList.length
					+ " DocID set to " + fileAddress);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void stringToTextFile(String inputString, String fileAddress,
			boolean append) {
		try {

			BufferedWriter bw = new BufferedWriter(new FileWriter(fileAddress,
					append));
			bw.write(inputString);
			bw.write(System.getProperty("line.separator"));
			bw.close();
			// System.out.println("write compelet: "+inputString+" to "+fileAddress);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static PrintWriter getPrintWriter(String fileAddress, boolean append)
			throws IOException {
		return new PrintWriter(new FileWriter(fileAddress, append));
	}

	public static void stringToTextFile(String input, String fileAddress) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(fileAddress));
			out.println(input);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("\nwrite " + fileAddress + " complete");
	}

	public static <K, V> void mapToTextFile(Map<K, V> map, String fileAddress) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(fileAddress));
			for (Entry<K, V> entry : map.entrySet())
				out.println(entry.getKey().toString() + "\t" + entry.getValue());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nwrite " + fileAddress + " complete");
	}

	public static <K, V extends Comparable<? super V>> void mapToTextFileSortedByValue(
			Map<K, V> map, String fileAddress, SortType sortType,
			HashMapWriteType hashMapWriteType, DecimalFormat keyFormat,
			DecimalFormat valueFormat) {
		try {
			PrintWriter out = new PrintWriter(fileAddress, "UTF-8");
			if (sortType == SortType.INCREMENTAL)
				for (Entry<K, V> entry : tools.util.sort.Collection
						.mapSortedByValuesIncremental(map))
					if (hashMapWriteType == HashMapWriteType.KEY)
						out.println(keyFormat.format(entry.getKey()));
					else if (hashMapWriteType == HashMapWriteType.VALUE)
						out.println(valueFormat.format(entry.getValue()));
					else if (hashMapWriteType == HashMapWriteType.KEYVALUE)
						out.println(keyFormat.format(entry.getKey()) + " "
								+ valueFormat.format(entry.getValue()));
					else
						out.println(valueFormat.format(entry.getValue()) + " "
								+ keyFormat.format(entry.getKey()));
			else
				for (Entry<K, V> entry : tools.util.sort.Collection
						.mapSortedByValuesDecremental(map))
					if (hashMapWriteType == HashMapWriteType.KEY)
						out.println(keyFormat.format(entry.getKey()));
					else if (hashMapWriteType == HashMapWriteType.VALUE)
						out.println(valueFormat.format(entry.getValue()));
					else if (hashMapWriteType == HashMapWriteType.KEYVALUE)
						out.println(keyFormat.format(entry.getKey()) + " "
								+ valueFormat.format(entry.getValue()));
					else
						out.println(valueFormat.format(entry.getValue()) + " "
								+ keyFormat.format(entry.getKey()));
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nwrite " + fileAddress + " complete");
	}

	public static <K, V extends Comparable<? super V>> void mapToTextFileSortedByValue(
			Map<K, V> map, String fileAddress, SortType sortType,
			HashMapWriteType hashMapWriteType, String delim) {
		tools.util.Time.setStartTimeForNow();
		try {
			PrintWriter out = new PrintWriter(fileAddress, "UTF-8");
			SortedSet<Entry<K, V>> sortedMap = sortType == SortType.INCREMENTAL ? tools.util.sort.Collection
					.mapSortedByValuesIncremental(map)
					: tools.util.sort.Collection
							.mapSortedByValuesDecremental(map);
			for (Entry<K, V> entry : sortedMap) {
				if (hashMapWriteType == HashMapWriteType.KEY)
					out.println(entry.getKey().toString());
				else if (hashMapWriteType == HashMapWriteType.VALUE)
					out.println(entry.getValue());
				else if (hashMapWriteType == HashMapWriteType.KEYVALUE)
					out.println(entry.getKey().toString() + delim
							+ entry.getValue());
				else
					out.println(entry.getValue().toString() + delim
							+ entry.getKey());
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nwrite " + fileAddress + " complete on "
				+ tools.util.Time.getTimeLengthForNow() + " ms.");
	}

	public static <K, V extends Comparable<? super V>> void mapToTextFileSortedByValue(
			Map<K, V> map, String fileAddress, SortType sortType,
			HashMapWriteType hashMapWriteType) {
		tools.util.Time.setStartTimeForNow();
		try {
			PrintWriter out = new PrintWriter(fileAddress, "UTF-8");
			SortedSet<Entry<K, V>> sortedMap = sortType == SortType.INCREMENTAL ? tools.util.sort.Collection
					.mapSortedByValuesIncremental(map)
					: tools.util.sort.Collection
							.mapSortedByValuesDecremental(map);
			for (Entry<K, V> entry : sortedMap) {
				if (hashMapWriteType == HashMapWriteType.KEY)
					out.println(entry.getKey().toString());
				else if (hashMapWriteType == HashMapWriteType.VALUE)
					out.println(entry.getValue());
				else if (hashMapWriteType == HashMapWriteType.KEYVALUE)
					out.println(entry.getKey().toString() + "\t"
							+ entry.getValue());
				else
					out.println(entry.getValue().toString() + "\t"
							+ entry.getKey());
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nwrite " + fileAddress + " complete on "
				+ tools.util.Time.getTimeLengthForNow() + " ms.");
	}

	public static <K extends Comparable<? super K>, V> void mapToTextFileSortedByKey(
			Map<K, V> map, String fileAddress, SortType sortType,
			HashMapWriteType hashMapWriteType) {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(fileAddress));
			if (sortType == SortType.INCREMENTAL)
				for (Entry<K, V> entry : tools.util.sort.Collection
						.mapSortedByKeyIncremental(map))
					if (hashMapWriteType == HashMapWriteType.KEY)
						out.println(entry.getKey().toString());
					else if (hashMapWriteType == HashMapWriteType.VALUE)
						out.println(entry.getValue());
					else if (hashMapWriteType == HashMapWriteType.KEYVALUE)
						out.println(entry.getKey().toString() + " "
								+ entry.getValue());
					else
						out.println(entry.getValue().toString() + " "
								+ entry.getKey());
			else
				for (Entry<K, V> entry : tools.util.sort.Collection
						.mapSortedByKeyDecremental(map))
					if (hashMapWriteType == HashMapWriteType.KEY)
						out.println(entry.getKey().toString());
					else if (hashMapWriteType == HashMapWriteType.VALUE)
						out.println(entry.getValue());
					else if (hashMapWriteType == HashMapWriteType.KEYVALUE)
						out.println(entry.getKey().toString() + " "
								+ entry.getValue());
					else
						out.println(entry.getValue().toString() + " "
								+ entry.getKey());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nwrite " + fileAddress + " complete");
	}

	public enum HashMapWriteType {
		KEY("key"), VALUE("value"), KEYVALUE("keyvalue"), VALUEKEY("valuekey");

		private String string = new String();

		HashMapWriteType(String string) {
			this.string = string;
		}

		public String getValue() {
			return string;
		}
	}

}
