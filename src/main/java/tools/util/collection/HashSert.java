package tools.util.collection;

import java.util.HashMap;

public class HashSert<K> {
	HashMap<K, Long> content;
	
	public HashSert() {
		this.content=new HashMap<K, Long>();
	}
	
	public HashSert(int size) {
		this.content=new HashMap<K, Long>(size);
	}
	
	public HashSert(HashMap<K, Long> content) {
		this.content=new HashMap<K, Long>(content);
	}
	
	public void put(K item){
		if(this.content.containsKey(item))
			this.content.put(item,this.content.get(item)+1);
		else
			this.content.put(item,1l);
	}
	
	public void add(K item){
		put(item);
	}
	
	public void put(K item,Long count){
		if(this.content.containsKey(item))
			this.content.put(item,this.content.get(item)+count);
		else
			this.content.put(item,count);
	}
	
	public void add(K item,Long count){
		put(item,count);
	}
	
	public boolean containsKey(K item){
		if(this.content.containsKey(item))
			return true;
		return false;
	}
	
	public Long get(K item){
		if(this.content.containsKey(item))
			return this.content.get(item);
		else
			return 0l;
	}
	
	public HashMap<K, Long> getHashMap(){
		return this.content;
	}
	
	public int size(){
		return this.content.size();
	}
	
	public void setClear(){
		this.content.clear();
	}
}
