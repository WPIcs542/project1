// This is the function support file
// Fangyu Lin, Hongzhang Cheng, Zhaojun Yang
// Sep.26.2015

package vStore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Hashtable;

public class Vstore{
	private Hashtable<Integer, byte[]> data;
	private static final String file = "cs542.db";
	
	private Vstore(){
		String kk = "myName";
		int vKey = kk.hashCode();
		
		ObjectInputStream objectInput = null;
		try {
			objectInput = new ObjectInputStream (new FileInputStream(file));
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
		data = (Hashtable<Integer, byte[]>) object;
		try {
			objectInput.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Vstore init(){
		return new Vstore();
	}
	
	private boolean put(int key, byte[] value){
		return true;
	}
	
	private byte[] get(int key){
		return new byte[0];
	}
	
	private boolean remove(int key){
		return true;
	}
	
	private boolean clear(){
		return true;
	}
}