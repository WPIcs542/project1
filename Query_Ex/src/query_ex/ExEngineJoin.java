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
	private ExEngineSelect exeSelect = new ExEngineSelect();

	public ExEngineJoin() {
	}

	public void init() throws IOException {
		city = new Relation("city.db");
		country = new Relation("country.db");
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
	
	
	public void getNext() throws UnsupportedEncodingException {
		String[] tumpleofcity = null;
		String[] tumpleofcountry = null;

		Enumeration<byte[]> enumofcity = city.getValuesEnum();
		Enumeration<byte[]> enumofcountry;
		while (enumofcity.hasMoreElements()) {
			byte[] city = enumofcity.nextElement();
			try {
				tumpleofcity = splitoftuple(city);
			} catch (UnsupportedEncodingException exp) {
				exp.printStackTrace();
			}
			enumofcountry = country.getValuesEnum();

			while (enumofcountry.hasMoreElements()) {
				byte[] country = enumofcountry.nextElement();
				try {
					tumpleofcountry = splitoftuple(country);
				} catch (UnsupportedEncodingException exp) {
					exp.printStackTrace();
				}
				if (tumpleofcity[2].equals(tumpleofcountry[0])) {
					String tuple = new String(country, StandardCharsets.UTF_8) + ","
							+ new String(city, StandardCharsets.UTF_8);
					pipelineExe(tuple);
				}
			}
		}
	}
	
	public String[] splitoftuple(byte[] tuple) throws UnsupportedEncodingException {
		String str = new String(tuple, StandardCharsets.UTF_8);
		return str.split(","); // ignores commas inside quotation marks
	}

	public void pipelineExe(String joinResult) throws UnsupportedEncodingException {
		exeSelect.getNext(joinResult);	
	}

	public void close() {
		//close the file read in;
	}
}
