package query_ex;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;


/**
 * CS542 Project_3
 * 
 * @author Fangyu Lin; Hongzhang Cheng; Zhaojun Yang
 * @date November/04/2015
 */

public class ExEngineJoin {
	Relation city;
	Relation country;
	
	String cityfile = "city.txt";
	String countryfile = "country.txt";
	// to store result and to be used in PROJECT operator
	private Relation joinResult = new Relation("joinResult.db");
	

	public ExEngineJoin() {
	}

	public void init() throws IOException {
		String cityfile = "city.txt";
		String countryfile = "country.txt";
		FileInputStream fs;
		InputStreamReader isr;
		BufferedReader b_read;
		String templine;
		try {
			fs = new FileInputStream(cityfile);
			isr = new InputStreamReader(fs);
			b_read = new BufferedReader(isr);
			while ((templine = b_read.readLine()) != null) {
				city.put(templine.getBytes().hashCode(), templine.getBytes());
			}
			city.saveContents();
			b_read.close();
			isr.close();
			fs.close();

			fs = new FileInputStream(countryfile);
			isr = new InputStreamReader(fs);
			b_read = new BufferedReader(isr);
			while ((templine = b_read.readLine()) != null) {
				country.put(templine.getBytes().hashCode(), templine.getBytes());
			}
			country.saveContents();
			b_read.close();
			isr.close();
			fs.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Initialization of \"city.db\" and \"country.db\" successful!");
	}

	public void open() {
		city = new Relation("city.db");
		country = new Relation("country.db");
	}

	// put the whole joined tuple into joinResult
	public void getNext()  {	
	String[] tumpleofcity = null;
	String [] tumpleofcountry = null;
	String cjc ="";
	String cityjoincountry[]=null;
	
	Enumeration<byte[]> enumofcity =city.getValuesEnum();
	Enumeration<byte[]> enumofcountry= country.getValuesEnum();
	while(enumofcity.hasMoreElements()){
	  try{
		  tumpleofcity =splitoftuple(enumofcity.nextElement());
	  }catch(UnsupportedEncodingException exp){
		  exp.printStackTrace();
	  }
	      while(enumofcountry.hasMoreElements()){
	    	  try{
	    	      tumpleofcountry =splitoftuple(enumofcountry.nextElement());
	    	  }catch (UnsupportedEncodingException exp){
	    		exp.printStackTrace();  
	    	  }
	    	  if(tumpleofcity[2].equals(tumpleofcountry[0])){	    		
	    		  Double cityP = Double.parseDouble(tumpleofcity[4]);
                  Double countryP=Double.parseDouble(tumpleofcountry[6]);
                  if(cityP/countryP>0.4){
                	  cityjoincountry = new String[tumpleofcity.length+tumpleofcountry.length];
	                  System.arraycopy(tumpleofcity,0,cityjoincountry,0, tumpleofcity.length);
	                  System.arraycopy(tumpleofcountry,0,cityjoincountry,tumpleofcity.length , tumpleofcountry.length);
		    		     for(int i=0;i<cityjoincountry.length-1;i++){    		          
		    		    	 cjc +=  cityjoincountry[i] + "," ;  
	    		         }	
		    		     cjc+=cityjoincountry[cityjoincountry.length-1];
	    	         }   	    
	    	    joinResult.put(cjc.getBytes().hashCode(),cjc.getBytes()); }
	    	
	      }
	
	  }
	
	}
	
	public String[]splitoftuple(byte[] tuple) throws UnsupportedEncodingException {
		String str = new String(tuple, StandardCharsets.UTF_8);
		return str.split(","); // ignores commas inside quotation marks
	    }

	
	

	public void close() {
//		joinResult.saveContents();
	}
}