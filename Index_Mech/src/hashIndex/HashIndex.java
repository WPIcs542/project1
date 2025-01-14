/**
 *  This is the Main.java for hash index project_2
 *  Team Members: Fangyu Lin, Hongzhang Cheng, Zhaojun Yang
 *  Date: Oct/26/2015
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
	//this class is our main data structure for store the data, it has the function
	//of hashing data to certain bucket based on their datavalue and getting keys of
	//datavalue and removing data 
	
	private ArrayList<Bucket> hashIndex;
	private int bucketSize = 10; 
    private static String filename = "cs542.db";
	
	/**
	 * The constructor for the hashtable we used for store data if its already exists,
	 *  then open it if its not we create a new one
	 */
	public HashIndex(){
		File df = new File(filename);
		if(df.exists() && !df.isDirectory()) { 
			System.out.println("Database file exist !");
		}else{
			System.out.println("Creating New Database file: "+ filename);
			hashIndex = new ArrayList<Bucket>();
			
			for(int n=0; n<bucketSize; n++){
				hashIndex.add(new Bucket());
			}
			try {
				//if there is no such file, create it.
				ObjectOutputStream obout = new ObjectOutputStream(new FileOutputStream(filename));
				
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
	 * Method to put data in hash table static hash tables are used, at first
	 * we get the bucketid from the datavalue of one data then we hash it to 
	 * the selected bucket,before that we need to check whether that bucket got
	 * enough space to store the data,if not we create a new bucket,and then link
	 * it with the old bucket and put the data in it
	 * @param key
	 * @param datavalue
	 */
	public synchronized void put(String key, String dataValue){
		
		int bucketId = Math.abs(dataValue.hashCode()) % hashIndex.size();
		Bucket bucket = hashIndex.get(bucketId);
//		Bucket bucketTemp = hashIndex.get(bucketId);
		while(bucket!=null){
			if(bucket.ifExistSpace()){
				bucket.insert(key, dataValue);
				break;
			}else{
				if(bucket.getNext()==null){
					bucket.setNext(new Bucket());
					bucket.getNext().insert(key, dataValue);
					break;
				}else{
					bucket = bucket.getNext();
				}
			}
		}

		
	}
	
//	
//	/**
//	 * Redistribute the elements in each block. 
//	 * Clean the block then put the elements in again.
//	 * 
//	 * @param bucket
//	 */
//	private void redistribute(Bucket bucket){
//		KeyValuePair tempContents[] = bucket.getBlockContents();
//		bucket.refreshBucket();
//		for(int i = 0; i< bucket.blockSize; i++){
//			put(tempContents[i].getKey(), tempContents[i].getValue());
//		}
//	}	

	/**
	 * Method to get data in hash table static hash tables are used.
	 * we doing the get based ont the type of input, if just input with year, 
	 * we try every possibilities with each year, then get the result, if input is its year 
	 * with other input we could directly search and get the keylist of these datavalue
	 * 
	 * @param value
	 * @return String is the key
	 */
	public String get(String value){
		ArrayList<String> keylist = new ArrayList<String>();
		String [] temp = {"|DVD", "|VHS", "|LaserDisc"};
		int n = 1;
		boolean flag = false;
		
		if(value.length() == 4){
			temp[0] = value + temp[0];
			temp[1] = value + temp[1];
			temp[2] = value + temp[2];
			n = 3;
			flag = true;
		}
		
		while(n > 0){
			if(flag){
				value = temp[n-1];
			}
			
			int bucketId = Math.abs(value.hashCode()) % hashIndex.size();
			Bucket bucket = hashIndex.get(bucketId);
			while(bucket!=null){
				if(bucket.getKey(value) != ""){
					keylist.add(bucket.getKey(value));
				}
				bucket = bucket.getNext();
			}
			if(keylist.size() > 1){
				return "same value with many Keys " + keylist.toString();
			}else if(keylist.size() == 1){
				return keylist.toString();
			}
			--n;
		}
		
		return "Key Not Found!";
	}
	
	
	
	/**
	 * method to remove index data_value and its key in hashIndex
	 * @param key
	 */
	public void remove(String key){

		for (Bucket bucket : hashIndex) {
			if(bucket!=null){
				for (int i=0;i<bucket.blockSize;i++) {
					if (bucket.getBlockContents()[i]!= null && bucket.getBlockContents()[i].getKey().equals(key)) {
					    bucket.remove(key, i);
					    return;
					}
				}
		    }
		}
		System.out.println("No such key exists"); 

	}

		/**
		 * Redistribute the elements in the bucket. 
		 * Move elements front if there exists space
		 * 
		 * @param bucket
		 */
//		private void redistribute(Bucket bucket){
//			//get the last bucket of the list
//			while(bucket.getNext()!=null){
//				bucket = bucket.getNext();
//			}
//			KeyValuePair tempContents[] = bucket.getBlockContents();
//			//Move the element front
//			for(int i = bucket.blockSize-1; i>=0; i--){
//				if(tempContents[i]!=null){
//					bucket.remove(tempContents[i].getKey(), i);
//					put(tempContents[i].getKey(),tempContents[i].getValue());
//					
//				}
//			}
//		}		
//		
	/**
	 * this method is used for save all the contents of of hashidex into the certain file	
	 */
	public void saveContents(){      
		try{
			FileOutputStream f =new FileOutputStream(filename);      
			ObjectOutputStream objOutput = new ObjectOutputStream(f);
			objOutput.writeObject(hashIndex);
			objOutput.close();
		}catch(IOException ex){
			ex.printStackTrace(); 
		}
	}
	/**
	 * this method is used for refreshing the hashindex clear all the data in the hashIndex
	 */
	public void clear_all(){
		this.hashIndex = new ArrayList<Bucket>();
		
		for(int n=0; n<bucketSize; n++){
			hashIndex.add(new Bucket());
		}
		System.out.println("All Index Data and Key have been removed from memory and disk.");
	}
}



