package tools.util.collection;

public class KeyValue<K ,V extends Comparable<V>> implements Comparable<KeyValue<K,V>>{

	K key;
	V value;
	
	public KeyValue(K key,V value) {
		this.key=key;
		this.value=value;		
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	public String getKeyValue() {
		return this.key.toString()+'\t'+this.value.toString();
	}
	
	public String getKeyValue(char delimiter) {
		return this.key.toString()+delimiter+this.value.toString();
	}
	
	public String getKeyValue(String delimiter) {
		return this.key.toString()+delimiter+this.value.toString();
	}


	@Override
	public int compareTo(KeyValue<K, V> arg0) {
		return value.compareTo(arg0.getValue());
	}
}
