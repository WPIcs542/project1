/**
 *  This is the Main.java for hash index project_2
 *  Team Members: Fangyu Lin, Hongzhang Cheng, Zhaojun Yang
 *  Date: Oct/16/2015
 */

package hashIndex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
//import java.util.Hashtable;

public class HashIndex {
	private ArrayList<Bucket> hashIndex;
	private int decisionBitsNumber = 2; //"i" in textbook
	static int initialBlockBitsNumber = 2; //"j" in text book
	private static String filename = "cs542.db";
	
	/**
	 * The constructor
	 */
	public HashIndex(){
		File df = new File(filename);
		if(df.exists() && !df.isDirectory()) { 
			System.out.println("Database file exist !");
		}else{
			hashIndex = new ArrayList<Bucket>();
			try {
				//if there is no such file, create it.
				ObjectOutputStream obout = new ObjectOutputStream(new FileOutputStream(filename));
				for(int n=0; n<this.decisionBitsNumber; n++){
					hashIndex.add(new Bucket(initialBlockBitsNumber));
				}
				obout.writeObject(hashIndex);
				obout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		df.exists();
		
		ObjectInputStream objectInput = null;
		try {
			objectInput = new ObjectInputStream (new FileInputStream(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Object object = null;
		try {
			object = objectInput.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// finally we put the data into memory space
		@SuppressWarnings("unchecked")
		ArrayList<Bucket> object2 = (ArrayList<Bucket>) object;
		this.hashIndex = object2;	
		try {
			objectInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to put data and 
	 * @param key
	 * @param value
	 */
	public synchronized void put(String key, String dataValue){
		
		int bucketId = Math.abs(key.hashCode()) % hashIndex.size();
		Bucket bucket = hashIndex.get(bucketId);
		if(bucket.ifExistSpace()){
			bucket.insert(key, dataValue);
		}else{
			bucket.incrementLength();

			//if "i" equals "j"
			if(bucket.getLength() == decisionBitsNumber){
				decisionBitsNumber++;
			    for(int i = 0; i< Math.pow(2, decisionBitsNumber-1);i++ ){
			    	//create a new block
			    	if(i==bucketId){
			    		hashIndex.add(new Bucket(bucket.getLength()));
			    	}
			    	//reference to old block
			    	else{
			    		Bucket bucketTemp = hashIndex.get(i);
			    		hashIndex.add(bucketTemp);
			    	}
			    }
			}else{//if "i" > "j"

			

			}
		}
	}
	

	/**
	 * 
	 * @param value
	 * @return String is the key
	 */
	public String get(String key){
		
		return "";
	}
	
	
	
	/**
	 * 
	 * @param key
	 */
	public void remove(String key){
		
	}

}

