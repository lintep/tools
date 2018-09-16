package tools.util.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HashSertInteger<K> {
	HashMap<K, Integer> content;
    long allPutCount=0l;

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
        allPutCount++;
		if(this.content.containsKey(item))
			this.content.put(item,this.content.get(item)+1);
		else
			this.content.put(item,1);
	}
	
	public void add(K item){
		put(item);
	}
	
	public void put(K item,Integer count){
        allPutCount+=count;
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
        this.allPutCount=0l;
		this.content.clear();
	}

	public void setClear() {
		clear();		
	}

    public long getAllPutCount() {
        return allPutCount;
    }

	public Map<K, Integer> getMap() {
		return content;
	}

	public Collection<Integer> values() {
		return content.values();
	}

	public void addAll(Map<K, Integer> map) {
		content.putAll(map);
	}
}
