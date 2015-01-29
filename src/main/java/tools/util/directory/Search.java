package tools.util.directory;

import java.io.File;
import java.util.*;

public class Search {

	public static ArrayList<File> getFilesForPath(String dataDir, boolean filterYesNo,HashSet<String> filter,
			int level) {
		
		ArrayList<File> output_files = new ArrayList<File>();
		Queue<File> foldersI=new LinkedList<File>();
		

		File[] files = new File(dataDir).listFiles();
		if(files==null)
			return output_files;
		
		for (File f : files) {
			boolean filterd = false;
			if(filterYesNo==true)
				filterd=true;
			for (String filterTxt : filter) {
				if(filterYesNo){
					if(f.getName().indexOf(filterTxt)>=0){
						filterd=false;
						break;
					}
				}
				else{
					if(f.getName().indexOf(filterTxt)>=0){
						filterd=true;
						break;
					}
				}
					
			}
			
			if (f.isDirectory()) {
				foldersI.add(f);
			} else if( f.exists() &&
			          f.canRead() &&
			          (filter == null || filter.size()==0 || !filterd)) {
  			         	output_files.add(f);
  			        }
		}
		
		
		Queue<File> foldersII=new LinkedList<File>();
		foldersII.clear();
		for (int i = 1; i < level; i++) {
			while(!foldersI.isEmpty())
			{				
				for (File f: foldersI.remove().listFiles()){
					boolean filterd = false;
					if(filterYesNo==true)
						filterd=true;
					for (String filterTxt : filter) {
						if(filterYesNo){
							if(f.getName().indexOf(filterTxt)>=0){
								filterd=false;
								break;
							}
						}
						else{
							if(f.getName().indexOf(filterTxt)>=0){
								filterd=true;
								break;
							}
						}
							
					}
					if (f.isDirectory()) {
						foldersI.add(f);
					} else if(f.exists() &&
					          f.canRead() &&
					          (filter == null ||  filter.size()==0 || !filterd)) {
		  			         	output_files.add(f);
		  			        }
				}
			}
			for (Iterator<File> iterator = foldersII.iterator(); iterator.hasNext();) {
				foldersI.add((File) iterator.next());
			}			
			foldersII.clear();
		}
		
		return output_files;
	}

	public static ArrayList<String> getFilesNamesForPath(String dataDir, HashSet<String> postFilter) {
		
		ArrayList<String> output_files = new ArrayList<String>();
		Queue<File> foldersI=new LinkedList<File>();
		

		File[] files = new File(dataDir).listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				foldersI.add(f);
			} else if(f.exists() &&
			          f.canRead()){
			          if(postFilter == null){
  			         	output_files.add(f.getName());
  			          }else{
  			        	 for (String postFile : postFilter) {
							
						if(f.getName().toLowerCase().indexOf(postFile.toLowerCase())+postFile.length()==f.getName().toLowerCase().length()){
							output_files.add(f.getName());
							break;
						}
  			          }
  			          }	  
			          
			}
		}
		
		return output_files;
	}
	
}
