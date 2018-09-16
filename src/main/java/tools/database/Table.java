package tools.database;

import java.util.ArrayList;
import java.util.Iterator;

public class Table {

	private String _tableName;
	private String[] _fieldNames;
	private ArrayList<String[]> _rows;
	
	public String get_tableName() {
		return _tableName;
	}
	
	public ArrayList<String[]> get_rows() {
		return (ArrayList<String[]>) _rows.clone();
	}
	
	public String[] get_fieldNames() {
		return _fieldNames;
	}
	
	public int columnCounts() {
		return this._fieldNames.length;		
	}
	
	public Table(String tableName, String[] fieldNames) {
		this._tableName=tableName;
		this._fieldNames=new String[fieldNames.length];
		for (int i = 0; i < fieldNames.length; i++) {
			this._fieldNames[i] = fieldNames[i];		
		}
		this._rows=new ArrayList<String[]>();
	}
	
	public void add(Table table_to_add) throws Exception{
		if(table_to_add.columnCounts()==this.columnCounts()){
			this._rows.addAll(table_to_add.get_rows());
		}
		else
			throw new Exception("tables columns count not same");
	}
	
	public void insertRow(String[] new_row) throws Exception {
		if(this._fieldNames.length==new_row.length)
			this._rows.add(new_row);
		else
			throw new Exception("new_row length not equal table fieldeNames length");
	}
	
	public Iterator<String[]> getRowsIterator() {
		return this._rows.iterator();
	}
	
	public void clear() {
		this._rows.clear();
	}
	
	public void print(){
		for (int i = 0; i < _fieldNames.length; i++) 
			System.out.print( "\t"+_fieldNames[i]);
		System.out.println("");
		for (Iterator iterator = getRowsIterator(); iterator.hasNext();) {
			String[] row = (String[]) iterator.next();
			
			for (int i = 0; i < row.length; i++){ 
				System.out.print("\t"+ row[i]);				
//				System.out.print("\t"+Utility.EncodeDecode.decodeURIComponent(row[i]));
			}
			System.out.println("\n\n");
		}
	}
}