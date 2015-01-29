package tools.util;

import java.io.File;
import java.util.*;

public class Directory {

	/**
	 * return current project directory address
	 * @return string as directory full address
	 */
	public static String getCurrentDirectoryAddress(){
		return (new File("")).getAbsolutePath();//System.getProperty("user.dir")
	}
	
	
	public static ArrayList<File> getFilesForPath(String dataDir, HashSet<String> filter,
			int level) {
		ArrayList<File> output_files = new ArrayList<File>();
		Queue<File> foldersI=new LinkedList<File>();
		File[] files = new File(dataDir).listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				foldersI.add(f);
			} else if(f.exists() &&
			          f.canRead() &&
			          (filter == null ||  filter.contains(f.getName().toLowerCase()))) {
  			         	output_files.add(f);
  			        }
		}
		Queue<File> foldersII=new LinkedList<File>();
		foldersII.clear();
		for (int i = 1; i < level; i++) {
			while(!foldersI.isEmpty())
			{				
				for (File f: foldersI.remove().listFiles())
					if (f.isDirectory()) {
						foldersI.add(f);
					} else if(f.exists() &&
					          f.canRead() &&
					          (filter == null ||  filter.contains(f.getName().toLowerCase()))) {
		  			         	output_files.add(f);
		  			        }
			}
			for (Iterator<File> iterator = foldersII.iterator(); iterator.hasNext();) {
				foldersI.add((File) iterator.next());
			}			
			foldersII.clear();
		}
		return output_files;
	}

	public static boolean create(String pathAddress) {
		return (new File(pathAddress)).mkdirs();
	}
}
