// package plag;

import java.util.*;
import java.io.*;

class Finger{

		//open a file;
		public static String Getstring(String s)throws FileNotFoundException{
			int c=0;
			File  file = new File(s);
			String st1="";
			try                                // To catch error we use try catch method
			{
				Scanner sc= new Scanner(file);
			    while(sc.hasNextLine())
			    {
			      st1+=sc.nextLine();
			      st1=st1.replace("\n"," "); // To read n lines in the given file
			      c=c+1;
			    }
			    sc.close();
			}
			catch(Exception e)
			{
			    e.printStackTrace(); // it prints the error
			}

			return st1;
	  }


	//Delimit special characters in the given Files

	public String Delimit(String s){
		String tempstr="";
		for (int i=0;i<s.length();++i ) {
			int a=(int)(s.charAt(i));
			if((a>47 && a<58)  || (a>96 && a<123) || a==95 || a==32){
				tempstr=tempstr+s.charAt(i);
			}
			
		}
		return tempstr;
	}
	//create Non-static method
	// Here input are String and anagram
	public double Hash_value(String s,int anagram){
		int j=s.length();
		double sum=0;
		for (int i=0;i<s.length();i++) {
			int n=(int)(s.charAt(i));// Calculating the ascii values of char in string
			sum=sum+(n*(Math.pow(2,j)-1));//and finding the power of ascii by anagaram in
			j--;                           // decreasing valus
		}
		return sum;


	}
	//create Non-static method
	// Here input are String and anagram
	public String Hashgen(String s,int anagram){
		String st2="";
		int i=0;
		while(i<(s.length()-anagram+1)){
			String st1=s.substring(i,i+anagram);// create a substring from main string
			double n=Hash_value(st1,anagram); // Calling Hash_value function to find 
			st2=st2+" "+n;					 // Hash Values of a given Files
			i++;
			st1="";
			}	
		
		return st2;
	}
	//create Non-static method to find Output
	// Here inputs are Hash_Generators of two Files and len number of Hash_Generators  and return type is Double
	public double Getoutput(String[] s,String[] st1,int len){
		int count=0;
		for (int i=0;i<s.length;++i) {
			for (int j=0;j<st1.length;++j) {
				if(s[i].equals(st1[j])){	
					count++;
				}	
			}
		}
		double perc=((2.0*count)/len)*100;
		return perc;
	}
}

public class Fingeprnt{


	public static void main(String[] args)throws FileNotFoundException {

		Finger obj=new Finger();// create object for the class

		Scanner scaner=new Scanner(System.in);// create Scanner object to scaner inputs
		System.out.println("Enter the path");// Enter the path which contains files in the folders

		String path=scaner.nextLine();// Here we are scanning the path
		File folder=new File(path);// create folder to file path 
		int k=0;

		File[] Fileslist=folder.listFiles();  //create a listoffile array and copy files
					
		String[] file_name=new String[Fileslist.length];//create a String array and store files in it
		for (int i=0;i<Fileslist.length ;++i ) { 
			File file=Fileslist[i];
			if(file.getName().endsWith(".txt")){ // condition to have only .txt files
				file_name[k]=file.getName();
				k++;
			}	
		}
		String s11 = scaner.nextLine();
		System.out.println("enter the weight value");
		int anagram=Integer.parseInt(s11);// Enter the weight value
//		
		

		for (int i=0;i<k;++i ) {
			System.out.print("	 "+file_name[i]);	// For Matrix printing in columns
		}
		System.out.println("\n");

		
		for (int i=0;i<k;++i ) {
			System.out.print(file_name[i]);
			for (int j=0;j<k;++j) {
				String s=obj.Getstring(Fileslist[i].toString()).toLowerCase();// Reading inputs into to two different Files
				String st1=obj.Getstring(Fileslist[j].toString()).toLowerCase();
				
				String st2=obj.Delimit(s).replace(" ","");// Calling Delimit Function to remove 
				String st3=obj.Delimit(st1).replace(" ","");// special characters and replacing space by NUll

				String[] st4=obj.Hashgen(st2,anagram).trim().split(" ");//Calling Hashgen Function
				String[] st5=obj.Hashgen(st3,anagram).trim().split(" ");//and removing spaces and splitting them

				int len=(st4.length+st5.length);
				
				double perc=obj.Getoutput(st4,st5,len);// calling Getoutput Function to
																//calculate the perc
//				System.out.print("       "+Math.round(perc)+"    ");
				
				System.out.printf("      "+"%3.2f"+"      ",perc);
				
			}
			System.out.println("\n");
			scaner.close();
			
		}
	}
}
