package query_ex;

import java.io.IOException;

/**
 * CS542 Project_3
 * 
 * @author Fangyu Lin; Hongzhang Cheng; Zhaojun Yang
 * @date November/04/2015
 */

public class Main {
	static Relation relation = new Relation("cs542.db"); // Initialize value
															// store object
	static final int vKey = 428657931; // this key is used for testing only
	private static final int MAX_VALUE_SIZE = 1024 * 1024; // set maximum size
															// 1MB
	// static Ex_Engine ex = new Ex_Engine();

	public static void main(String[] args) {
		System.out.println("Here is a testing line");
		// try { //this needs to be run only once to setup the database file.
		// ex.init();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		Relation test = new Relation("city.db");
		// test.listTable("city.db");
		System.out.println(new String(test.get(1058921154)));
		System.out.println(new String(test.get(959629210)));

		Relation test2 = new Relation("country.db");
		// test2.listTable("country.db");
		System.out.println(new String(test2.get(1362842287)));
		System.out.println(new String(test2.get(1298350375)));

		ExEngineJoin joinContryCity = new ExEngineJoin();
		joinContryCity.open();
		joinContryCity.getNext();
		joinContryCity.close();

		ExEngineProject projectCity = new ExEngineProject();
		projectCity.open();
		projectCity.getNext();
		projectCity.close();

		// test if the data retrieved match result
		System.out.println(projectCity.getFinalResult().toString());

	}

}
