package hashIndex;

import java.io.Serializable;
import java.util.HashMap;

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
	//	this.blockContents = new HashMap[blockSize];	
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

	
	public boolean ifExistSpace(){
		for(int i = 0; i<blockSize;i++){
			if(this.blockContents[i]==null)	return true;
		}
		return false;
	}
	
}
