package tools.util.collection;

import java.util.HashMap;

public class HashSertDouble<K> {
	HashMap<K, Double> content;
    double allPutScore=0.;

	public HashSertDouble() {
		this.content=new HashMap<K, Double>();
	}

	public HashSertDouble(int size) {
		this.content=new HashMap<K, Double>(size);
	}

	public HashSertDouble(HashMap<K, Double> content) {
		this.content=new HashMap<K, Double>(content);
	}

	public void put(K item,double score){
        allPutScore+=score;
		if(this.content.containsKey(item))
			this.content.put(item,this.content.get(item)+score);
		else
			this.content.put(item,score);
	}
	
	public void add(K item,Integer count){
		put(item,count);
	}
	
	public boolean containsKey(K item){
		if(this.content.containsKey(item))
			return true;
		return false;
	}
	
	public Double get(K item){
		if(this.content.containsKey(item))
			return this.content.get(item);
		else
			return 0.;
	}
	
	public HashMap<K, Double> getHashMap(){
		return this.content;
	}
	
	public int size(){
		return this.content.size();
	}
	
	public void clear(){
        this.allPutScore=0.;
		this.content.clear();
	}

	public void setClear() {
		clear();		
	}

    public double getAllPutScore() {
        return allPutScore;
    }
}
