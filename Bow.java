// package plag;


import java.util.*;
import java.io.*;
class Bag{


		// Creating a  Static Function to open a file;
		public static String Getstring(String s){
			int c=0;
			File  file = new File(s);
			//System.out.println("file is"+file);
			String s1="";
			try                                // To catch error we use try catch method
			{
				Scanner sc= new Scanner(file);
			    while(sc.hasNextLine())
			    {
			      s1+=sc.nextLine();
			      s1=s1.replace("\n"," "); // To read n lines in the given file
			      c=c+1;
			    }
			    sc.close();
			}
			catch(Exception e)
			{
			     e.printStackTrace(); // it prints the error
			    //System.out.println("error\n");
			}

			return s1;
	    }



	// Creating Non-static method  to Delimit like @,#,$ etc in a  the given Files

	public String Delimit(String s){
		int l=s.length();
		String tempstr="";
		for (int i=0;i<s.length();++i ) {
			int a=(int)(s.charAt(i));
			if((a>47 && a<58)  || (a>96 && a<123) || a==95 || a==32){
				tempstr=tempstr+s.charAt(i);
			}
			
		}

		return tempstr;
	}
	//Creating Non-static method to find Frequency of the given Files

	public HashMap<String,Integer> freq(String[] s){
		HashMap<String,Integer> hm=new HashMap<String,Integer>();
		for (int i=0;i<s.length;++i ) {
			String s1=s[i];
			if(!hm.containsKey(s1)){
				hm.put(s1,1);
			}
			else{
				hm.put(s1,hm.get(s1)+1);
			}
			
		}
		//System.out.println(hm);
		return hm;
	} 
	// Creating Non-static method to find Sum of squares

	public int norm(HashMap<String,Integer> hm){
		int sum=0;
		for (String key:hm.keySet()){
			sum+=(hm.get(key)*hm.get(key));
		}
		
		return sum;
	}
		// Creating Non-static method to find sum

	public int prod(HashMap<String,Integer> hm,HashMap<String,Integer> hm1){
		int sum=0;
		for (String key:hm.keySet()) {
			for (String key1:hm1.keySet()) {
				String s=key;
				String s1=key1;
				if(s.equals(s1)){
					sum+=(hm.get(key)*hm1.get(key1));
				}
			}
		}
		return sum;
	}
	public double Getoutput(int dot,double norm){

		double percentage=(dot/norm)*100;

		return percentage;
	}
}
public class Bow{

	public static void main(String[] args) {

		Bag obj=new Bag();	// Creating object for the class

		Scanner scan=new Scanner(System.in); // Creating Scanner object to scan inputs
		System.out.println("Enter the path");
		String path=scan.nextLine();// Here we are scanning the path
		File folder=new File(path);// Craeting folder to file path 
		int k=0;

		File[] Fileslist=folder.listFiles();  //Creating a listoffile array and copy files
												// it from folder
		String[] file_name=new String[Fileslist.length];//Creating a String array

		for (int i=0;i<Fileslist.length ;++i ) {    // and entering the files in it
			File file=Fileslist[i];
			if(file.getName().endsWith(".txt")){ // condition to have only .txt files
				file_name[k]=file.getName();
				k++;
			}	
		}

		for (int i=0;i<k;++i ) {
			System.out.print("	      "+file_name[i]);	// For Matrix printing in columns
		}
		System.out.println("\n");

		for (int i=0;i<k;++i ) {
			System.out.print(file_name[i]);
			for (int j=0;j<k;++j) {
				
				String s=obj.Getstring(Fileslist[i].toString()).toLowerCase();// Reading inputs into to two different Files
				String s1=obj.Getstring(Fileslist[j].toString()).toLowerCase();// and converting into to LowerCase

				String[] s2=obj.Delimit(s).split(" ");// Calling Delimit Function to remove 
				String[] s3=obj.Delimit(s1).split(" ");// special characters and spliting them by space

				HashMap<String,Integer> hm=obj.freq(s2);//Calling freq Function to find
				HashMap<String,Integer> hm1=obj.freq(s3);// frequency of the Files

				int norm=obj.norm(hm);     //Calling norm Function to  find the Sum of squares of each File.
				int norm1=obj.norm(hm1);   //the Sum of squares of each File.
				double resultnorm = ((Math.sqrt(norm))*(Math.sqrt(norm1)));//finding squareroot of files 

				int dot=obj.prod(hm,hm1);//Calling prod Function to find dot product of two files

				// double perc = (dot/norm)*100;
				double percentage=obj.Getoutput(dot,resultnorm);

//				System.out.print("             "+ Math.round(perc));
				System.out.printf("      "+"%3.2f"+"      ",percentage);

			}
			System.out.println("\n");
		}	
	}
}