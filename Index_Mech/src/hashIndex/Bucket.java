package hashIndex;

import java.io.Serializable;

public class Bucket implements Serializable{
	private static final long serialVersionUID = -9184164241837197805L;
//	private int blockBitsNumber; //"j" in textbook
	private int savebitNumber;  //"i" in textbook
	public int blockSize = 2;
	private KeyValuePair[] blockContents;
	private Bucket next;
	
	/**
	 * This is constructor. 
	 * @param blockBitsNumber
	 */
	public Bucket(){
//		this.blockBitsNumber = blockBitsNumber;
	 	this.blockContents = new KeyValuePair[blockSize];
	}
	

	
	public void setNext(Bucket bucket){
		this.next = bucket;
	}
	
	public Bucket getNext(){
		return this.next;
	}
	
	public KeyValuePair[] getBlockContents(){
		return blockContents;
	}
	
	public void saveBitNum(int n){
		this.savebitNumber = n;
	}
	
	public int getBitNum(){
		return this.savebitNumber;
	}
	
	public void insert(String key, String dataValue){
//		for(int i=0 ; i<blockSize ; i++){
//			if(this.blockContents[i] !=null&&this.blockContents[i].getKey().equals(key)){
//				System.out.println("used the same key before, choose a new key please !");
//				return;
//			}	
//		}
//		
		for(int i=0 ; i<blockSize ; i++){
			if(this.blockContents[i] ==null||(this.blockContents[i].getKey()==""&&this.blockContents[i].getValue()=="")){
				this.blockContents[i] = new KeyValuePair(key, dataValue);
				System.out.println("Put key into index succeed");
				return;
			}	
		}
		
	}

	public String getKey(String value){
		String localKey = "";
		for(int i=0 ; i<blockSize ; i++){
			if(blockContents[i] != null && this.blockContents[i].getValue().equals(value)){
				if(localKey == ""){
					localKey=new String(blockContents[i].getKey());
				}else{
				localKey+=new String(", "+blockContents[i].getKey());								
				}		
			}
		}
		return localKey;
	}
	
	public boolean ifExistSpace(){
		for(int i = 0; i<blockSize; i++){
			if(blockContents[i] == null||(this.blockContents[i].getKey()==""&&this.blockContents[i].getValue()=="")){	
				return true;
			}
		}
		return false;
	}
	
	public void remove(String key, int blockIndex){
			
				this.blockContents[blockIndex] = null;
//				this.blockContents[i].setKey("");
//				this.blockContents[i].setValue("");
				System.out.println("The key \""+ key + "\" has been removed from index!");
				return;
			}
	
	
	public void refreshBucket(){
		this.blockContents  = new KeyValuePair[blockSize];
	}

//	public void expandSpace(){
//		blockSize *= 2;
//		KeyValuePair newBucket[] = new KeyValuePair[blockSize];
//		for(int n=0; n<blockSize/2; n++){
//			newBucket[n] = this.blockContents[n];
//		}
//		this.incrementLength();
//		this.blockContents = newBucket;
//	}
}

