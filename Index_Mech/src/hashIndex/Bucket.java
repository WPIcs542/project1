package hashIndex;

import java.io.Serializable;
import java.util.HashMap;

public class Bucket implements Serializable{
	private static final long serialVersionUID = -9184164241837197805L;
	private int totalLength;
	public static final int bucketSize = 4;
	private int count;
	private HashMap[] contents;
	
	/**
	 * This is constructor. 
	 * @param totalLength
	 */
	public Bucket(int totalLength){
		this.totalLength = totalLength;
	//	this.contents = new HashMap[bucketSize];	
	}
	
	public int getLength(){
		return totalLength;
	}
	
	public void insert(String key, String dataValue){
		for(int i=0 ; i<bucketSize ; i++){
			if(contents[i] ==null)
				contents[i].put(key, dataValue);
			    count++;
				return;
		}
	}

	
	public boolean existSpace(){
		if(count == bucketSize)
			return false;
		else
			return true;
	}
	
}
