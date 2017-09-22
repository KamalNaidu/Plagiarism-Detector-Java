// package plag;

import java.util.*;
import java.io.*;

class Lcsdup{


		// Creating a  Static Function to open a file;

		public static String Getstring(String str)throws FileNotFoundException{
			int c=0;
			File  file = new File(str);
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




	// Creating Non-static method  to Delimit like @,#,$ etc in a  the given Files
	
	public String Delimit(String str){
		String tempst="";
		for (int i=0;i<str.length();++i ) {
			int a=(int)(str.charAt(i));
			if(a==95 || a==32  || (a>96 && a<123) || (a>47 && a<58)){
				tempst=tempst+str.charAt(i);
			}
			
		}
		//System.out.println(tempst);
		return tempst;
	}
	// Creating Non-static method to find longest common substring
	public int Frequency(String str,String st1){
		int temp=0;
		for (int i=0;i<str.length();i++) {
			int f=i;
			String st2="";
			for (int j=0;j<st1.length();j++) {
				int u=j;
				while(str.charAt(f)==st1.charAt(u) && f<str.length() && u<st1.length()){
					st2+=str.charAt(f);
					if( f+1<str.length() && u+1<st1.length())
					{
					f++;
					u++;
				}
				else
				{
						
					break;
				}}
				if(st2.length()>temp){
					temp=st2.length();
					
					}
				f=i;
				st2="";
				
			}			
		}
		return temp;
	}

}


public class Lcs{


	public static void main(String[] args)throws FileNotFoundException {

		Lcsdup obj=new Lcsdup();	

		Scanner scaner=new Scanner(System.in);// Creating Scanner object to scaner inputs
		System.out.println("Enter the path for test files");// Enter the path which contains files in the folders

		String path=scaner.nextLine();// Here we are scanning the path
		File folder=new File(path);// Creating folder to file path 
		int k=0;

		File[] Fileslist=folder.listFiles();  //Creating a listoffile array and copy files from folder


		String[] file_name=new String[Fileslist.length];//Creating a String array
		for (int i=0;i<Fileslist.length ;++i ) {    // and entering the files in it
			File file=Fileslist[i];
			if(file.getName().endsWith(".txt")){ // condition to have only .txt files
				file_name[k]=file.getName();
				k++;
			}	
		}

		for (int i=0;i<k;++i ) {
			System.out.print("	 "+file_name[i]);	// For Matrix printing in columns
		}
		System.out.println("\n");

		
		for (int i=0;i<k;++i ) {
			System.out.print(file_name[i]);
			for (int j=0;j<k;++j) {

				String str=obj.Getstring(Fileslist[i].toString()).toLowerCase();// Reading inputs into to two different Files
				String st1=obj.Getstring(Fileslist[j].toString()).toLowerCase();

				String st2=obj.Delimit(str);// Calling Delimit Function to remove special characters
				String st3=obj.Delimit(st1);
				
				int len=(st2.length()+st3.length());

				int z=obj.Frequency(st2,st3);

				double perc = ((2.0*z)/len)*100;

//				System.out.print("        "+Math.round(perc)+"    ");
				System.out.printf("     "+"%3.2f"+"     ",perc);
				// if(perc<100) {
				// System.out.printf("%.2f",perc);
				// }
				// else if(perc==0)
				// {
				// 	System.out.println("No file");
				// }
				// else {
				// 	System.out.println("same file");
				// }
			}
			System.out.println("\n");
			
			
		}scaner.close();
	}

}
	
