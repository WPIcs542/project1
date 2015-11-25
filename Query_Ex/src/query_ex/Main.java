package query_ex;

import java.io.UnsupportedEncodingException;

/**
 * CS542 Project_3
 * 
 * @author Fangyu Lin; Hongzhang Cheng; Zhaojun Yang
 * @date November/04/2015
 */

/**
 *this is the test for our query program 
 * @author hongzhangcheng
 *
 */
public class Main {
	static Relation relation = new Relation("cs542.db"); // Initialize value
															// store object
	static final int vKey = 428657931; // this key is used for testing only
	static ExEngineJoin ex = new ExEngineJoin();

	public static void main(String[] args) throws UnsupportedEncodingException {
		

		ExEngineJoin joinContryCity = new ExEngineJoin();
		joinContryCity.open();
		joinContryCity.getNext();
		joinContryCity.close();
		Relation result = new Relation("projectResult.db");
		result.listTable();

		// ExEngineProject projectCity = new ExEngineProject();
		// projectCity.open();
		// projectCity.getNext();
		// projectCity.close();

		// test if the data retrieved match result
		// System.out.println(projectCity.getFinalResult().toString());

	}

}
