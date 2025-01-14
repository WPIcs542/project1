package query_ex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

/**
 * CS542 Project_3
 * 
 * @author Fangyu Lin; Hongzhang Cheng; Zhaojun Yang
 * @date November/04/2015
 */

public class Relation {
	private Hashtable<Integer, byte[]> data;
	private String global_file = "cs542.db"; // defualt database filename
	private static final int MAX_VALUE_SIZE = 1024 * 1024; // set maximum size
															// to be 1.0 MB

	/**
	 * This is the object initialization
	 */
	@SuppressWarnings("unchecked")
	public Relation(String file) {
		File df = new File(file);
		// Need to check if the database filename is correct or not
		// If there is no such name, create new database for storage.
		if (df.exists() && !df.isDirectory()) {
			System.out.println("Database file exists !");
		} else {
			try {
				// if there is no such file, create it.
				ObjectOutputStream obout = new ObjectOutputStream(new FileOutputStream(file));
				obout.writeObject(new Hashtable<Integer, byte[]>());
				obout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		df.exists();

		// The following is to reach data from the 542.db which is in the disk
		ObjectInputStream objectInput = null;
		try {
			objectInput = new ObjectInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object object = null;
		try {
			object = objectInput.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// finally we put the data into memory space
		data = (Hashtable<Integer, byte[]>) object;
		global_file = file;
		try {
			objectInput.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Stores data under the given key First of all, we need to check the size
	 * of value should be no more than 1 MB Second, we need to check if there is
	 * enough space to put the incoming value Third, we want to commit memory
	 * save into disk 542.db
	 * 
	 * @param key
	 * @param value
	 * @return boolean
	 */
	public boolean put(int key, byte[] value) {
		// synchronized blocks can only have one thread executing at the same
		// time
		synchronized (this) {
			// Check if the value satisfies the size requirement
			if (value.length > MAX_VALUE_SIZE) {
				System.out.println("Value should not be larger than 1 MB!");
				return false;
			} else {
				Set<Integer> keys = data.keySet();
				int size = 0;
				for (Integer thekey : keys) {
					size += data.get(thekey).length;
				}
				if (size + value.length > MAX_VALUE_SIZE * 4.1) {
					System.out.println("Database has no space to put data! the attempt key=" + key);
					return false;
				} else {
					/*
					 * if(data.containsKey(key)){ System.out.println(
					 * "The key exist !"); return false; }
					 */
					data.put(key, value);
				}
			}
		}
		saveContents();
		return true;
	}

	/**
	 * Here is the get method to show the value with coresponding key We want to
	 * check if the key is not exist, print out information and return the empty
	 * byte array to the main shell.
	 * 
	 * @param key
	 * @return byte[]
	 */
	public byte[] get(int key) {
		// synchronized blocks can only have one thread executing at the same
		// time
		synchronized (this) {
			// Thread.sleep is for testing while doing get(), a thread starts
			// put()
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!data.containsKey(key)) {
				System.out.println("The key does not exist!");
				return new byte[0];
			} else {
				byte[] b = null;
				b = data.get(key);
				return b;
			}
		}
	}

	/**
	 * Here is the remove function to remove value from the Hashtable in memory.
	 * First, we want to know if the key is exist, if not, print out message and
	 * return false to the main shell. Second, if remove the key succeed, we
	 * want to commit to disk and save the updating result to 542.db in the
	 * disk.
	 * 
	 * @param key
	 * @return boolean
	 */
	public boolean remove(int key) {
		// test if the key exists
		if (!data.containsKey(key)) {
			System.out.println("The key does not exist!");
			return false;
		} else {
			// synchronized blocks can only have one thread executing at the
			// same time
			synchronized (this) {
				// Thread.sleep is for testing while doing remove(), a thread
				// starts get()
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				data.remove(key);
			}
		}
		saveContents();
		return true;
	}

	/**
	 * this is used in the reboot testing. Clear Hashtable in the memory, but
	 * not the disk.
	 * 
	 * @return boolean
	 */
	public boolean clear() {
		Hashtable<Integer, byte[]> newdata = new Hashtable<Integer, byte[]>();
		this.data = newdata;
		return true;
	}

	/**
	 * this method is to write data into cs542.db and the same as write to
	 * commit. Write the hashtable from memory to the disk is very important.
	 * The data will be safe with out of power or accident shut down.
	 */
	public void saveContents() {
		try {
			FileOutputStream f = new FileOutputStream(global_file);
			ObjectOutputStream objOutput = new ObjectOutputStream(f);
			objOutput.writeObject(data);
			objOutput.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * This is the function to show all the information in database. List the
	 * default file name 542.db you can change the filename as your own. First,
	 * we want to know if the file is exist or not. Second, we want to list all
	 * the key and the value size with corresponding key. The value size is
	 * return as bytes unit.
	 * 
	 * @param filename
	 * @return boolean
	 */
	public boolean listTable() {
		// if(!filename.equalsIgnoreCase("542.db")
		// || !filename.equalsIgnoreCase("city.db")
		// || !filename.equalsIgnoreCase("country.db")){
		// System.out.println("File does not exist !");
		// return false;
		// }
		Set<Integer> keys = data.keySet();
		int size = 0;
		int count = 0;
		for (Integer key : keys) {
			size = data.get(key).length;
			String value = new String(data.get(key), StandardCharsets.UTF_8);
			System.out.println("Value of Key:\t" + key + "\t" + "---The size of data:\t" + size + " Bytes" + "\t"
					+ "value: " + value);
			count++;
		}
		System.out.println("Size: " + count);
		return true;
	}
/**
 * this is the function for return all the data of our relation hashtable
 * @return all elements in our hash table as enumeration which contain several byte array
 */
	public Enumeration<byte[]> getValuesEnum() {
		return data.elements();
	};
}
