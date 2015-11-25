package redoLog;

import java.io.IOException;

/**
 * CS542 Project_4
 * 
 * @author Fangyu Lin; Hongzhang Cheng; Zhaojun Yang
 * @date November/19/2015
 */

public class Main {
	static Relation country = new Relation("country.db"); // Read into memory
	static Relation city = new Relation("city.db");		//read into memory
	static Relation city2 = new Relation("city_backup.db");        //backup file
	static Relation country2 = new Relation("country_backup.db");  //backup file
	static RedoLog citylog = new RedoLog();
	static RedoLog countrylog = new RedoLog();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Here is the test line!");
//		country2.listTable();
//		city2.listTable();

		//this is only need to be ran once to initialization log files.
//		try {
//			citylog.init("city.log");
//			countrylog.init("country.log");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			citylog.readLargerTextFile("city.log");
			countrylog.readLargerTextFile("country.log");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		citylog.writeStart();
//		citylog.writeUpdate("Shanghai", 754321.0, 1934567.0);
		citylog.printlog();
//		countrylog.writeStart();
		countrylog.printlog();
//		countrylog.writeCommit();
		
//		try {
//			citylog.savelog("city.log");
//			countrylog.savelog("country.log");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
