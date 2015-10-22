package hashIndex;

import java.io.Serializable;
import java.util.Set;

public class Bucket implements Serializable{
	private static final long serialVersionUID = -9184164241837197805L;
	private int blockBitsNumber; //"j" in textbook
	public static final int blockSize = 2;
	private Set[] blockContents;
	
	/**
	 * This is constructor. 
	 * @param blockBitsNumber
	 */
	public Bucket(int blockBitsNumber){
		this.blockBitsNumber = blockBitsNumber;
	 	this.blockContents = new Set[blockSize];	
	}
	
	public int getLength(){
		return blockBitsNumber;
	}
	
	public void incrementLength(){
		this.blockBitsNumber *= 2;
	}
	
	public Set[] getBlockContents(){
		return blockContents;
	}
	
	public void insert(String key, String dataValue){
		for(int i=0 ; i<blockSize ; i++){
			if(this.blockContents[i] ==null){
				this.blockContents[i].add(new ValuePair(key, dataValue));
				return;
			}	
		}
	}

	public boolean ifExistSpace(){
		for(int i = 0; i<blockSize;i++){
			if(this.blockContents[i]==null)	return true;
		}
		return false;
	}
	
	public void remove(String key){
		for(int i=0 ; i<blockSize ; i++){
			if(blockContents[i].contains(key)){
				blockContents[i] = null;
				return;
			}	
		}
	}
	
	class ValuePair{
		private String key;
		private String value;
		
		public ValuePair(String key, String value){
			this.key = key;
			this.value = value;
		}
		
		public String getValue(){
			return this.value;
		}
		
		public String getKey(){
			return this.key;
		}
		
		public void setValue(String value){
			this.value = value;
		}
	}
}

