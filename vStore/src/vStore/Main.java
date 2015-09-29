// This is the Main Shell
// Sep.26.2015
// Fangyu Lin, Hongzhang Cheng, Zhaojun Yang

package vStore;

import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{
    static Vstore store = new Vstore();
    static final int vKey = 428657931;
    
    public static void main(String [] args){
        boolean flag;
        boolean running = true;
        Scanner scan = new Scanner(System.in);
        Scanner scan1 = new Scanner(System.in);;
        int option = 0;
        
        while(running){
        	if(option==0){
        		System.out.println("------Welcome to the Value Store !------");
        		System.out.println("---------Here is the Main Menu----------");
        		System.out.println("--------Select the number options below:");
        		System.out.println("1) Put String data----------------------");
        		System.out.println("2) Get String Data by Key---------------");
        		System.out.println("3) Testing get and put at the same time-");
        		System.out.println("4) Testing remove and get at the same time");
        		System.out.println("5) Test reboot, then get data-----------");
        		System.out.println("6) Show all database info---------------");
        		System.out.println("7) Show Me the Main Menu !!-------------");
        		System.out.println("8) Put Data with your key---------------");
        		System.out.println("9) Get Data with your key---------------");
        		System.out.println("10) Remove Data with your key-----------");
        		System.out.println("11) Quit Value Store--------------------");
        	}
        	flag = false;
        	System.out.print("==>");
        	try{
        		option = scan.nextInt();
        	}catch(InputMismatchException e){
        		option = 0;
        		System.out.println("This is not an integer:" + e);
        		break;
        	}
        	
        	if(option==11){
        		System.out.println("Thank you for using value store! Bye");
        		running = false;
        		break;
        	}else if(option==7){
        		option=0;
        	}else if(option==1){
        		System.out.println("You are puting data");
        		byte[] vl = new byte[1024]; 
        		vl = "That's put something into it, sounds fun right, awesome!!".getBytes();
        		flag = store.put(vKey, vl);
        	}else if(option==2){
        		System.out.println("You are getting data, Enter your key:");
        		byte[] temp = new byte [1024];
        		temp = store.get(vKey);
        		String text = "";
				try {
					text = new String(temp, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		System.out.println("The value is: " + text);
        		if(temp.length == 0){
        			System.out.println("Not found");
        		}else{
        			flag = true;
        		}
        	}else if(option==3){
        		System.out.println("Testing get and put");
        		flag = getAndput(vKey);
        	}else if(option==4){
        		System.out.println("Testing remove and get");
        		flag = removeAndget(vKey);
        	}else if(option==5){
        		System.out.println("Testing reboot and get");
        		flag = rebootAndget(vKey);
        	}else if(option==6){
        		System.out.println("Show all data, done!");
        		flag = showAll();
        	}else if(option==8){
        		System.out.println("Please Enter your key to put data: ");
        		int k = -1;
        		try{
            		k = scan.nextInt();
            	}catch(InputMismatchException e){
            		option = 0;
            		System.out.println("This is not an integer: " + e);
            		break;
            	}
        		System.out.println("DataString: ");
        		String vals = scan1.nextLine();
        		byte[] val = new byte[1024]; //The maximum size is 1MB dataStream.
        		val = vals.getBytes();
        		flag = store.put(k, val);
        	}else if(option==9){
        		System.out.println("Enter your key to get data: ");
        		int k = -1;
        		try{
            		k = scan.nextInt();
            	}catch(InputMismatchException e){
            		option = 0;
            		System.out.println("This is not an integer: " + e);
            		break;
            	}
        		byte[] result = new byte[1024];
        		result = store.get(k);
        		String text = "";
				try {
					text = new String(result, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		System.out.println("The value is: " + text);
        		if(result.length == 0){
        			System.out.println("Not found");
        		}else{
        			flag = true;
        		}
        	}else{
        		System.out.println("The number is not correct, try again!");
        	}
        	
        	if(flag){
                System.out.println("Job Done!");
            }else{
                System.out.println("Job Failed, try again.");
            }
        }
        scan.close();
        scan1.close();
    }
    
    //here is the test get() and put() at the same time
    public static boolean getAndput(int key){
        return true;
    }
    
    //here is the test remove() and get() at the same time
    public static boolean removeAndget(int key){
        return true;
    }
    
    //here is the test of reboot put() and then run get()
    public static boolean rebootAndget(int key){
        return true;
    }
    
    //here is the test of Fragmentation put() A B C D .....
    public static boolean fragmentPut(){
        return true;
    }
    
    public static boolean showAll(){
    	return store.listTable();
    }
}