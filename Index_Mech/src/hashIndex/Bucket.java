package hashIndex;

import java.io.Serializable;

public class Bucket implements Serializable{
	private static final long serialVersionUID = -9184164241837197805L;
	private int blockBitsNumber; //"j" in textbook
	public static final int blockSize = 2;
	private KeyValuePair[] blockContents;
	
	/**
	 * This is constructor. 
	 * @param blockBitsNumber
	 */
	public Bucket(int blockBitsNumber){
		this.blockBitsNumber = blockBitsNumber;
	 	this.blockContents = new KeyValuePair[blockSize];	
	}
	
	public int getLength(){
		return blockBitsNumber;
	}
	
	public void incrementLength(){
		this.blockBitsNumber++;
	}
	
	public KeyValuePair[] getBlockContents(){
		return blockContents;
	}
	
	public void insert(String key, String dataValue){
		for(int i=0 ; i<blockSize ; i++){
			if(this.blockContents[i] ==null){
				this.blockContents[i] = new KeyValuePair(key, dataValue);
				return;
			}	
		}
	}

	public String getKey(String value){
		for(int i=0 ; i<blockSize ; i++){
			if(this.blockContents[i].getValue().equals(value)){
				return this.blockContents[i].getKey();
			}	
		}
		return "There is no such value index!!";
	}
	
	public boolean ifExistSpace(){
		for(int i = 0; i<blockSize;i++){
			if(this.blockContents[i]==null)	
				return true;
		}
		return false;
	}
	
	public void remove(String key){
		for(int i=0 ; i<blockSize ; i++){
			if(blockContents[i].getKey().equals(key)){
				blockContents[i] = null;
				return;
			}
		}
	}
	
	public void refreshBucket(){
		this.blockContents  = new KeyValuePair[blockSize];
	}
	
//	class ValuePair{
//		private String key;
//		private String value;
//		
//		public ValuePair(String key, String value){
//			this.key = key;
//			this.value = value;
//		}
//		
//		public String getValue(){
//			return this.value;
//		}
//		
//		public String getKey(){
//			return this.key;
//		}
//		
//		public void setValue(String value){
//			this.value = value;
//		}
//	}
}

