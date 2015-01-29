package tools.util.collection;

import java.util.HashMap;

public class HashSertInteger<K> {
	HashMap<K, Integer> content;
	
	public HashSertInteger() {
		this.content=new HashMap<K, Integer>();
	}
	
	public HashSertInteger(int size) {
		this.content=new HashMap<K, Integer>(size);
	}
	
	public HashSertInteger(HashMap<K, Integer> content) {
		this.content=new HashMap<K, Integer>(content);
	}
	
	public void put(K item){
		if(this.content.containsKey(item))
			this.content.put(item,this.content.get(item)+1);
		else
			this.content.put(item,1);
	}
	
	public void add(K item){
		put(item);
	}
	
	public void put(K item,Integer count){
		if(this.content.containsKey(item))
			this.content.put(item,this.content.get(item)+count);
		else
			this.content.put(item,count);
	}
	
	public void add(K item,Integer count){
		put(item,count);
	}
	
	public boolean containsKey(K item){
		if(this.content.containsKey(item))
			return true;
		return false;
	}
	
	public Integer get(K item){
		if(this.content.containsKey(item))
			return this.content.get(item);
		else
			return 0;
	}
	
	public HashMap<K, Integer> getHashMap(){
		return this.content;
	}
	
	public int size(){
		return this.content.size();
	}
	
	public void clear(){
		this.content.clear();
	}

	public void setClear() {
		clear();		
	}
}
