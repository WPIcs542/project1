/**
 *  This is the Main.java for hash index project_2
 *  Team Members: Fangyu Lin, Hongzhang Cheng, Zhaojun Yang
 *  Date: Oct/26/2015
 */

package hashIndex;

import java.io.Serializable;

public class KeyValuePair implements Serializable{
	//this is the array inside the bucket we used for store the data
	private static final long serialVersionUID = -4426643114124451175L;
	private String key;
	private String dataValue;
	
    public KeyValuePair(String key, String dataValue) {
    	this.key = key;
    	this.dataValue = dataValue;
    }
    
    public String getValue(){
		return this.dataValue;
	}
	
	public String getKey(){
		return this.key;
	}
	
	public void setValue(String value){
		this.dataValue = value;
	}
	
	public void setKey(String key){
		this.key = key;
	}
	
	
}
