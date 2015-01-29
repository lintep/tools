package tools.util.file;

import tools.util.Logger;
import tools.util.collection.HashSertInteger;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;

public class Reader {

	/**
	 * this method used for load object from file (writed before to file)
	 * @param inputFileAddress
	 * @param outClassType
	 * @return
	 */
	public static <T> T readObjectToFile(String inputFileAddress,Class<T> outClassType) {
		Class<Integer> a = Integer.class;
		try{
		      //use buffering
		      InputStream file = new FileInputStream(inputFileAddress);
		      InputStream buffer = new BufferedInputStream(file);
		      ObjectInput input = new ObjectInputStream (buffer);
		      try{
		        //deserialize the List
		        try {
					return (T)input.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		      finally{
		        input.close();
		      }
		} catch (IOException ex) {
			Logger.log("Cannot perform intput.");
		}
		return null;
	}
	
	/**
	 * this method usually used for reading big file for avoid heap space error
	 * Notice: at the end of use you should close that
	 * 
	 * @param fileAddress
	 * @return BufferedReader
	 * @throws java.io.FileNotFoundException
	 */
	public static BufferedReader getFileBufferReader(String fileAddress)
			throws FileNotFoundException {
		return new BufferedReader(new FileReader(fileAddress));
	}

	/**
	 * this method usually used for reading big file for avoid heap space error
	 * Notice: at the end of use you should close that
	 *
	 * @param file
	 * @return BufferedReader
	 * @throws java.io.FileNotFoundException
	 */
	public static BufferedReader getFileBufferReader(File file)
			throws FileNotFoundException {
		return new BufferedReader(new FileReader(file));
	}

	/**
	 * this method best for read text file lines
	 * 
	 * @param textFileAddress
	 * @param printMessage
	 * @return file lines text as ArrayList
	 */
	public static ArrayList<String> getTextLinesString(String textFileAddress,
			Boolean printMessage) {
		ArrayList<String> output = new ArrayList<String>();
		int lines = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(textFileAddress));
			String newLine;
			while ((newLine = reader.readLine()) != null) {
				output.add(newLine);
				lines++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (printMessage)
			System.out.println(tools.util.File.getName(textFileAddress)
					+ "  have " + lines + " Line");
		return output;
	}

	/**
	 * this method best for read text file lines
	 * 
	 * @param textFileAddress
	 * @param printMessage
	 * @return file lines text as ArrayList
	 */
	public static String[] getTextFileLinesAsArray(String textFileAddress,
			Boolean printMessage) {
		String[] output = new String[tools.util.File.getTextFileLineCount(
				textFileAddress, false)];
		int lines = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(textFileAddress));
			String newLine;
			while ((newLine = reader.readLine()) != null) {
				output[lines] = newLine;
				lines++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (printMessage)
			System.out.println(tools.util.File.getName(textFileAddress)
					+ "  have " + lines + " Line");
		return output;
	}

	public static ArrayList<String> getTextFileLimitLines(String fileAddress,
			int lineCount) {
		ArrayList<String> output = new ArrayList<String>();
		int lines = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileAddress));
			String newLine;
			while ((newLine = reader.readLine()) != null) {
				output.add(newLine);
				lines++;
				if (lines == lineCount)
					break;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(tools.util.File.getName(fileAddress) + "  have "
				+ lines + " Line");
		return output;
	}

	public static ArrayList<Long> getLongsFromTextFile(String fileAddress) {
		ArrayList<Long> output = new ArrayList<Long>();

		int lines = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileAddress));
			String newLine;
			while ((newLine = reader.readLine()) != null) {
				output.add(Long.parseLong(newLine));
				lines++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(tools.util.File.getName(fileAddress) + "  have "
				+ lines + " Line");

		return output;
	}

	public static ArrayList<Integer> getIntegersFromTextFile(String fileAddress) {
		ArrayList<Integer> output = new ArrayList<Integer>();

		int lines = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileAddress));
			String newLine;
			while ((newLine = reader.readLine()) != null) {
				output.add(Integer.parseInt(newLine));
				lines++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(tools.util.File.getName(fileAddress) + "  have "
				+ lines + " Line");

		return output;
	}

	public static Set<Integer> getIntegerSetFromTextFile(String fileAddress) {
		Set<Integer> output = new HashSet<Integer>();

		int lines = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileAddress));
			String newLine;
			while ((newLine = reader.readLine()) != null) {
				output.add(Integer.parseInt(newLine));
				lines++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(tools.util.File.getName(fileAddress) + "  have "
				+ lines + " Line");

		return output;
	}

	
	public static ArrayList<Float> getFloatsFromTextFile(String fileAddress) {
		ArrayList<Float> output = new ArrayList<Float>();

		int lines = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileAddress));
			String newLine;
			while ((newLine = reader.readLine()) != null) {
				output.add(Float.parseFloat(newLine));
				lines++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(tools.util.File.getName(fileAddress) + "  have "
				+ lines + " Line");

		return output;
	}

	public static <K> ArrayList<K> getArrayListFromTextFile(String fileAddress,
			String regex, int index, K defaultValue, boolean printMessage)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, SecurityException, NoSuchMethodException {
		long startTime = System.currentTimeMillis();
		ArrayList<K> output = new ArrayList<K>();

		int lines = 0;
		BufferedReader reader;
		Method method = defaultValue.getClass().getMethod("valueOf",
				String.class);
		int faultCounter = 0;
		try {
			reader = new BufferedReader(new FileReader(fileAddress));
			String newLine;
			K k;
			while ((newLine = reader.readLine()) != null) {
				k = defaultValue;
				try {
					k = (K) method.invoke(null, newLine.split(regex)[index]);
				} catch (Exception e) {
					faultCounter++;
					if (printMessage)
						System.err.println(newLine.split(regex)[index]
								+ " set default key( can not read value:"
								+ e.getMessage() + " )");
				}
				output.add(k);
				lines++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long totalTime = System.currentTimeMillis() - startTime;
		System.out.println(tools.util.File.getName(fileAddress) + "  have "
				+ lines + " Line \tmis read count:" + faultCounter + "\tin "
				+ totalTime / 1000 + "s .");

		return output;
	}

	public static ArrayList<String> getStringFromTextFile(String fileAddress,
			boolean printMessage) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			SecurityException, NoSuchMethodException {
		long startTime = System.currentTimeMillis();
		ArrayList<String> output = new ArrayList<String>();

		int lines = 0;
		BufferedReader reader;
		int faultCounter = 0;
		try {
			reader = new BufferedReader(new FileReader(fileAddress));
			String newLine;
			while ((newLine = reader.readLine()) != null) {
				output.add(newLine);
				lines++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (printMessage) {
			long totalTime = System.currentTimeMillis() - startTime;
			System.out.println(tools.util.File.getName(fileAddress) + "  have "
					+ lines + " Line \tmis read count:" + faultCounter
					+ "\tin " + totalTime / 1000 + "s .");
		}
		return output;
	}

	public static String getTextFromFile(File file, boolean printMessage)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, SecurityException, NoSuchMethodException {
		long startTime = 0l;
		if (printMessage)
			startTime = System.currentTimeMillis();
		StringBuilder stringBuilder = new StringBuilder();
		int lines = 0;
		BufferedReader reader;
		int faultCounter = 0;
		try {
			reader = new BufferedReader(new FileReader(file));
			String newLine;
			while ((newLine = reader.readLine()) != null) {
				stringBuilder.append(newLine);
				stringBuilder.append('\n');
				lines++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (printMessage) {
			long totalTime = System.currentTimeMillis() - startTime;
			System.out.println(tools.util.File.getName(file.getAbsolutePath())
					+ "  have " + lines + " Line \tmis read count:"
					+ faultCounter + "\tin " + totalTime / 1000 + "s .");
		}
		return stringBuilder.toString();
	}

	public static String getTextFromFile(String fileAddress,
			boolean printMessage) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			SecurityException, NoSuchMethodException {
		return getTextFromFile(new File(fileAddress), printMessage);
	}

	public static HashMap<Integer, Integer> getKeyValueIntegerIntegerFromTextFile(
			String textAddress, Integer defaultValue, Boolean printMessage) {
		HashMap<Integer, Integer> output = new HashMap<Integer, Integer>();
		int lines = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(textAddress));
			String newLine;
			while ((newLine = reader.readLine()) != null) {
				lines++;
				String[] splitLine = newLine.split("\t");
				try {
					int key = Integer.valueOf(splitLine[0]);
					int value = defaultValue;
					try {
						value = Integer.valueOf(splitLine[1]);
					} catch (Exception e) {
						System.err.println(key
								+ " set default value( can not read value:"
								+ e.getMessage() + " )");
					}
					output.put(key, value);
				} catch (Exception e) {
					System.err.println("can not read from line " + lines + "("
							+ e.getMessage() + " )");
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (printMessage)
			System.out.println(tools.util.File.getName(textAddress) + "  have "
					+ lines + " Line");
		return output;
	}

	public static HashMap<String, Double> getKeyValueStringDoubleFromTextFile(
			String fileAddress, Double defaultValue, String regix) {
		HashMap<String, Double> output = new HashMap<String, Double>();
		int lines = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileAddress));
			String newLine;
			while ((newLine = reader.readLine()) != null) {
				lines++;
				String[] splitLine = newLine.split(regix);
				try {
					String key = splitLine[0];
					Double value = defaultValue;
					try {
						value = Double.valueOf(splitLine[1]);
					} catch (Exception e) {
						System.err.println(key
								+ " set default value( can not read value:"
								+ e.getMessage() + " )");
					}
					output.put(key, value);
				} catch (Exception e) {
					System.err.println("can not read from line " + lines + "("
							+ e.getMessage() + " )");
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// if (printMessage)
		// System.out.println(tools.util.File.getName(fileAddress) + "  have "
		// + lines + " Line");
		return output;
	}

	public static HashMap<String, Character> getKeyValueStringCharacterFromTextFile(
			String fileAddress, char defaultValue, Boolean printMessage,
			String regex) {
		HashMap<String, Character> output = new HashMap<String, Character>();
		int lines = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileAddress));
			String newLine;
			while ((newLine = reader.readLine()) != null) {
				lines++;
				String[] splitLine = newLine.split(regex);
				try {
					String key = splitLine[0];
					Character value = defaultValue;
					try {
						value = Character.valueOf(splitLine[1].charAt(0));
					} catch (Exception e) {
						System.err.println(key
								+ " set default value( can not read value:"
								+ e.getMessage() + " )");
					}
					output.put(key, value);
				} catch (Exception e) {
					System.err.println("can not read from line " + lines + "("
							+ e.getMessage() + " )");
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (printMessage)
			System.out.println(tools.util.File.getName(fileAddress) + "  have "
					+ lines + " Line");
		return output;
	}

	public static HashMap<Character, Character> getKeyValueCharacterCharacterFromTextFile(
			String fileAddress, char defaultValue, String regex,
			Boolean printMessage) {
		HashMap<Character, Character> output = new HashMap<Character, Character>();
		int lines = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileAddress));
			String newLine;
			while ((newLine = reader.readLine()) != null) {
				lines++;
				String[] splitLine = newLine.split(regex);
				try {
					Character key = Character.valueOf(splitLine[0].charAt(0));
					Character value = defaultValue;
					try {
						value = Character.valueOf(splitLine[1].charAt(0));
					} catch (Exception e) {
						System.err.println(key
								+ " set default value( can not read value:"
								+ e.getMessage() + " )");
					}
					output.put(key, value);
				} catch (Exception e) {
					System.err.println("can not read from line " + lines + "("
							+ e.getMessage() + " )");
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (printMessage)
			System.out.println(tools.util.File.getName(fileAddress) + "  have "
					+ lines + " Line");
		return output;
	}

	public static HashMap<String, Integer> getKeyValueStringIntegerFromTextFile(
			String fileAddress, Integer defaultValue, Boolean printMessage,
			String regex) {
		HashMap<String, Integer> output = new HashMap<String, Integer>();
		int lines = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileAddress));
			String newLine;
			while ((newLine = reader.readLine()) != null) {
				lines++;
				String[] splitLine = newLine.split(regex);
				try {
					String key = splitLine[0];
					Integer value = defaultValue;
					try {
						value = Integer.valueOf(splitLine[1]);
					} catch (Exception e) {
						System.err.println(key
								+ " set default value( can not read value:"
								+ e.getMessage() + " )");
					}
					output.put(key, value);
				} catch (Exception e) {
					System.err.println("can not read from line " + lines + "("
							+ e.getMessage() + " )");
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (printMessage)
			System.out.println(tools.util.File.getName(fileAddress) + "  have "
					+ lines + " Line");
		return output;
	}

	public static HashMap<Character, Integer> getKeyValueCharacterIntegerFromTextFile(
			String fileAddress, Integer defaultValue, Boolean printMessage,
			String regex) {
		HashMap<Character, Integer> output = new HashMap<Character, Integer>();
		int lines = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileAddress));
			String newLine;
			while ((newLine = reader.readLine()) != null) {
				lines++;
				String[] splitLine = newLine.split(regex);
				try {
					Character key = splitLine[0].charAt(0);
					Integer value = defaultValue;
					try {
						value = Integer.valueOf(splitLine[1]);
					} catch (Exception e) {
						System.err.println(key
								+ " set default value( can not read value:"
								+ e.getMessage() + " )");
					}
					output.put(key, value);
				} catch (Exception e) {
					System.err.println("can not read from line " + lines + "("
							+ e.getMessage() + " )");
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (printMessage)
			System.out.println(tools.util.File.getName(fileAddress) + "  have "
					+ lines + " Line");
		return output;
	}

	public static HashMap<String, String> getKeyValueFromTextFile(
			String fileAddress, Boolean printMessage, String regex) {
		HashMap<String, String> output = new HashMap<String, String>();
		int lines = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileAddress));
			String newLine;
			while ((newLine = reader.readLine()) != null) {
				lines++;
				String[] splitLine = newLine.split(regex);
				try {
					String key = splitLine[0];
					String value = " ";
					try {
						value = splitLine[1];
					} catch (Exception e) {
						System.err.println(key
								+ " set default value( can not read value:"
								+ e.getMessage() + " )");
					}
					output.put(key, value);
				} catch (Exception e) {
					System.err.println("can not read from line " + lines + "("
							+ e.getMessage() + " )");
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (printMessage)
			System.out.println(tools.util.File.getName(fileAddress) + "  have "
					+ lines + " Line");
		return output;
	}

	public static HashMap<Integer, String> getKeyValueIntegerStringFromTextFile(
			String fileAddress, Integer defaultKey, Boolean printMessage,
			String regex) {
		HashMap<Integer, String> output = new HashMap<Integer, String>();
		int lines = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileAddress));
			String newLine;
			while ((newLine = reader.readLine()) != null) {
				lines++;
				String[] splitLine = newLine.split(regex);
				try {
					Integer key = defaultKey;
					try {
						String value = splitLine[1];
						key = Integer.valueOf(splitLine[0]);
						output.put(key, value);
					} catch (Exception e) {
						System.err.println(key
								+ " set default value( can not read value:"
								+ e.getMessage() + " )");
					}
				} catch (Exception e) {
					System.err.println("can not read from line " + lines + "("
							+ e.getMessage() + " )");
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (printMessage)
			System.out.println(tools.util.File.getName(fileAddress) + "  have "
					+ lines + " Line");
		return output;
	}

	public static HashMap<String, Long> getKeyValueStringLongFromTextFile(
			String fileAddress, Long defaultValue, Boolean printMessage,
			String regex) {
		HashMap<String, Long> output = new HashMap<String, Long>();
		int lines = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileAddress));
			String newLine;
			while ((newLine = reader.readLine()) != null) {
				lines++;
				String[] splitLine = newLine.split(regex);
				try {
					String key = splitLine[0];
					Long value = defaultValue;
					try {
						value = Long.valueOf(splitLine[1]);
					} catch (Exception e) {
						System.err.println(key
								+ " set default value( can not read value:"
								+ e.getMessage() + " )");
					}
					output.put(key, value);
				} catch (Exception e) {
					System.err.println("can not read from line " + lines + "("
							+ e.getMessage() + " )");
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (printMessage)
			System.out.println(tools.util.File.getName(fileAddress) + "  have "
					+ lines + " Line");
		return output;
	}

	public static HashMap<Long, Long> getKeyValueLongLongFromTextFile(
			String fileAddress, Long defaultValue, Boolean printMessage,
			String regex) {
		HashMap<Long, Long> output = new HashMap<Long, Long>();
		int lines = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileAddress));
			String newLine;
			while ((newLine = reader.readLine()) != null) {
				lines++;
				String[] splitLine = newLine.split(regex);
				try {
					try {
						Long value = defaultValue;
						Long key = Long.valueOf(splitLine[0]);
						value = Long.valueOf(splitLine[1]);
						output.put(key, value);
					} catch (Exception e) {
						System.err.println(splitLine[0] + " or " + splitLine[1]
								+ " set default value( can not read value:"
								+ e.getMessage() + " )");
					}
				} catch (Exception e) {
					System.err.println("can not read from line " + lines + "("
							+ e.getMessage() + " )");
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (printMessage)
			System.out.println(tools.util.File.getName(fileAddress) + "  have "
					+ lines + " Line");
		return output;
	}

	public static HashMap<Long, Integer> getKeyValueLongIntegerFromTextFile(
			String fileAddress, Integer defaultValue, Boolean printMessage,
			String regex) {
		HashMap<Long, Integer> output = new HashMap<Long, Integer>();
		int lines = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileAddress));
			String newLine;
			while ((newLine = reader.readLine()) != null) {
				lines++;
				String[] splitLine = newLine.split(regex);
				try {
					try {
						Integer value = defaultValue;
						Long key = Long.valueOf(splitLine[0]);
						value = Integer.valueOf(splitLine[1]);
						output.put(key, value);
					} catch (Exception e) {
						System.err.println(splitLine[0] + " or " + splitLine[1]
								+ " set default value( can not read value:"
								+ e.getMessage() + " )");
					}
				} catch (Exception e) {
					System.err.println("can not read from line " + lines + "("
							+ e.getMessage() + " )");
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (printMessage)
			System.out.println(tools.util.File.getName(fileAddress) + "  have "
					+ lines + " Line");
		return output;
	}
	
	public static void putKeyValueLongIntegerFromTextFilesToHashSertInteger(
			ArrayList<String> fileAddresses, int defaultValue,
			Boolean printMessage, String regex,HashSertInteger<Long> output) {
		tools.util.Time.setStartTimeForNow();
		BufferedReader reader;
		for (String fileAddress : fileAddresses) {
			int lines = 0;
			System.out.println("file "+fileAddress+" loading ... ");
			try {
				reader = new BufferedReader(new FileReader(fileAddress));
				String newLine;
				while ((newLine = reader.readLine()) != null) {
					lines++;
					String[] splitLine = newLine.split(regex);
					try {
						try {
							Integer value = defaultValue;
							Long key = Long.valueOf(splitLine[0]);
							value = Integer.valueOf(splitLine[1]);
							output.put(key, value);
						} catch (Exception e) {
							System.err.println(splitLine[0] + " or "
									+ splitLine[1]
									+ " set default value( can not read value:"
									+ e.getMessage() + " )");
						}
					} catch (Exception e) {
						System.err.println("can not read from line " + lines
								+ "(" + e.getMessage() + " )");
					}
					if(lines%10000==0)
						if(printMessage)
							System.out.println(lines+" louded in "+tools.util.Time.getTimeLengthForNow()+" ms");
				}
				reader.close();
				if (printMessage)
					System.out.println(tools.util.File.getName(fileAddress)
							+ "  have " + lines + " Line");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(printMessage)
				System.out.println("load "+lines+" in "+tools.util.Time.getTimeLengthForNow()+" ms");
		}
	}

	// @@@@@@@@@@@@ please check it not work
	public static <K, V> HashMap<K, V> getKeyValueFromTextFile(
			String fileAddress, K defaultKey, V defaultValue, String regex,
			Boolean printMessage) throws SecurityException,
			NoSuchMethodException {
		long startTime = System.currentTimeMillis();
		HashMap<K, V> output = new HashMap<K, V>();
		int lines = 0;
		BufferedReader reader;
		Method method = defaultValue.getClass().getMethod("valueOf",
				String.class);
		long faultCounter = 0;
		try {
			reader = new BufferedReader(new FileReader(fileAddress));
			String newLine;
			while ((newLine = reader.readLine()) != null) {
				lines++;
				String[] splitLine = newLine.split(regex);
				try {
					K key = defaultKey;
					V value = defaultValue;
					try {
						key = (K) method.invoke(null, splitLine[0]);
					} catch (Exception e) {
						faultCounter++;
						if (printMessage)
							System.err.println(splitLine[0]
									+ " set default key( can not read value:"
									+ e.getMessage() + " )");
					}
					try {
						value = (V) method.invoke(null, splitLine[1]);
					} catch (Exception e) {
						faultCounter++;
						if (printMessage)
							System.err.println(splitLine[1]
									+ " set default value( can not read value:"
									+ e.getMessage() + " )");
					}

					output.put(key, value);
				} catch (Exception e) {
					System.err.println("can not read from line " + lines + "("
							+ e.getMessage() + " )");
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long totalTime = System.currentTimeMillis() - startTime;
		System.out.println(tools.util.File.getName(fileAddress) + "  have "
				+ lines + " Line \tmis read count:" + faultCounter + "\tin "
				+ totalTime / 1000 + "s .");
		return output;
	}

	public static HashSet<Entry<Integer, Integer>> getKeyValueSetFromTextFile(
			String fileAddress, Integer defaultValue, Boolean printMessage) {
		HashSet<Entry<Integer, Integer>> output = new HashSet<Entry<Integer, Integer>>();
		int lines = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileAddress));
			String newLine;
			while ((newLine = reader.readLine()) != null) {
				lines++;
				String[] splitLine = newLine.split("\t");
				try {
					int key = Integer.valueOf(splitLine[0]);
					int value = defaultValue;
					try {
						value = Integer.valueOf(splitLine[1]);
					} catch (Exception e) {
						System.err.println(key
								+ " set default value( can not read value:"
								+ e.getMessage() + " )");
					}
					output.add(new AbstractMap.SimpleEntry<Integer, Integer>(
							key, value));

				} catch (Exception e) {
					System.err.println("can not read from line " + lines + "("
							+ e.getMessage() + " )");
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (printMessage)
			System.out.println(tools.util.File.getName(fileAddress) + "  have "
					+ lines + " Line");
		return output;
	}

}
