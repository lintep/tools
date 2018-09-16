package tools.util.collection;


public class PairValue<V1 extends Comparable<V1>,V2 extends Comparable<V2>> implements Comparable<PairValue<V1,V2>>{

	V1 value1;
	V2 value2;
	
	public PairValue(V1 value1, V2 value2) {
		this.value1=value1;
		this.value2=value2;		
	}

	public V1 getValue1() {
		return value1;
	}

	public V2 getValue2() {
		return value2;
	}

	public String getValue1Value2(char delimiter) {
		return this.value1.toString()+delimiter+this.value2.toString();
	}
	
	@Override
	public String toString(){
		return this.value1.toString()+'\t'+this.value2.toString();
	}
	
	@Override
	public int compareTo(PairValue<V1, V2> o) {
		int compareValue = value1.compareTo(o.getValue1());
		return compareValue!=0?compareValue:value2.compareTo(o.getValue2());
	}

	public void setValue1(V1 value1) {
		this.value1 = value1;
	}

	public void setValue2(V2 value2) {
		this.value2 = value2;
	}
}
