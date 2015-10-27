/**
 *  This is the Main.java for hash index project_2
 *  Team Members: Fangyu Lin, Hongzhang Cheng, Zhaojun Yang
 *  Date: Oct/16/2015
 */
 
package hashIndex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	static HashIndex hash;
	
	/**
	 * run testing through Hashindex.
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		hash = new HashIndex();
		BufferedReader in = new BufferedReader(new FileReader("cs542.txt"));
		String l;
		while ((l = in.readLine()) != null) {
			Scanner s = new Scanner(l);	
			s.useDelimiter("\\s*thisisfordivision\\s*");
			hash.put(s.next(),s.next());
			s.close();
		}
		
		System.out.println("Test line of Hash Index!!");
//		hash.put("1", "5");
//		hash.put("2", "6");
//		hash.put("1", "2");
//		hash.put("4", "2");
//		hash.put("9", "2");
//		hash.put("5", "1");
//		hash.put("5", "2");
//		hash.put("7", "5");
//		hash.put("8", "2");
//		
		System.out.println("The value \"1977|DVD\" has key: " + hash.get("1977|DVD"));
		System.out.println("The value \"1990|VHS\" has key: " + hash.get("1990|VHS"));
		System.out.println("The value \"2001|DVD\" has key: " + hash.get("2001|DVD"));
//		System.out.println("The value \"5\" has key: " + hash.get("5"));
		
//		hash.remove("8");
//		hash.remove("6");
//		hash.remove("4");
//		hash.remove("9");
//		hash.remove("3");
//		hash.remove("1");
//		hash.remove("5");
//		hash.remove("7");
//		hash.remove("2");
		
		//hash.saveContents();
		System.out.println();
		System.out.println("Job Done!");
	}


}
