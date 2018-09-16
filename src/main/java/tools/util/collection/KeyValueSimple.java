package tools.util.collection;


public class KeyValueSimple<K ,V>{

	K key;
	V value;
	
	public KeyValueSimple(K key,V value) {
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

}
