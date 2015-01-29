package tools.util.collection;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Map;

public class IncrementalTable<K,V> {
	Table<K,V, Long> content;
	
	public IncrementalTable() {
		this.content=HashBasedTable.create();
	}
	
	public void put(K row,V column){
		if(this.content.contains(row, column))
			this.content.put(row, column,this.content.get(row, column)+1);
		else
			this.content.put(row, column,1l);
	}
	
	public boolean count(K row,V column){
		if(this.content.contains(row,column))
			return true;
		return false;
	}
	
	public Long get(K row,V column){
		if(this.content.contains(row,column))
			return this.content.get(row,column);
		else
			return 0l;
	}
	
	public Table<K,V, Long> getTable(){
		return this.content;
	}
	
	public Map<K, Long> column(V value) {
		return this.content.column(value);
	}
	
	public Map<V, Long> row(K key) {
		return this.content.row(key);
	}
	
	public void clear(){
		this.content.clear();
	}
}
