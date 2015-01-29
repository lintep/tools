package tools.util.collection;

import java.util.HashMap;

public class HashChert<K> {
	HashMap<K, Integer> content;
	HashMap<Integer, K> reversContent;
	
	public HashChert() {
		this.content=new HashMap<K, Integer>();
		this.reversContent=new HashMap<Integer, K>();
	}
	
	public HashChert(int initialCapacity) {
		this.content=new HashMap<K, Integer>(initialCapacity);
		this.reversContent=new HashMap<Integer, K>(initialCapacity);
	}
	
	private void put(K item){
		if(!this.content.containsKey(item)){
			this.content.put(item,this.content.size()+1);
			this.reversContent.put(this.content.get(item), item);
		}
	}
	
	public boolean containsKey(K item){
		if(this.content.containsKey(item))
			return true;
		return false;
	}
	
	public int putGet(K item){
		put(item);
		return this.content.get(item);
	}
	
	public HashMap<K, Integer> getHashMap(){
		return this.content;
	}
	
	public int size(){
		return this.content.size();
	}
	public HashMap<Integer, K> getReversHashMap() {
		return this.reversContent;
	}
}
