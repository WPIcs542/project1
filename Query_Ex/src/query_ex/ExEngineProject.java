//package query_ex;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Enumeration;
//
///**
// * CS542 Project_3
// * 
// * @author Fangyu Lin; Hongzhang Cheng; Zhaojun Yang
// * @date November/04/2015
// */
//public class ExEngineProject {
//	private Relation joinResult;
//	private Enumeration<byte[]> joinResultEnume;
//	private Relation projectResult = new Relation("projectResult.db");
//	private ArrayList<String> finalResult = new ArrayList<String>();
//
//	public void open() {
//		Relation joinResult = new Relation("joinResult.db");
//		joinResultEnume = joinResult.getValuesEnum();
//	}
//
//	public void getNext() {
//		while (joinResultEnume.hasMoreElements()) {
//			String[] joinResultElement = getTupleValues(joinResultEnume.nextElement());
//			projectResult.put(key, value);
//		}
//
//	}
//
//	public void close() {
//		Enumeration<byte[]> projectResultEnume = projectResult.getValuesEnum();
//		while (projectResultEnume.hasMoreElements()) {
//			finalResult.add(new String(projectResultEnume.nextElement()));
//		}
//	}
//
//	public ArrayList<String> getFinalResult() {
//		return finalResult;
//	}
//
//}
