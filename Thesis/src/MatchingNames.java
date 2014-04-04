import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
public class MatchingNames {
//Code for calculating PNDR among files
	public static void main (String args[]) throws Exception{
		/*BufferedReader reader1 = new BufferedReader(new FileReader("NER/MultiPersonNamesOCR.txt"));
		BufferedReader reader2 = new BufferedReader(new FileReader("NER/MultiPersonNamesCorrected3.txt"));
		BufferedReader reader3 = new BufferedReader(new FileReader("NER/MultiPersonNamesOriginal.txt"));
		BufferedReader reader4 = new BufferedReader(new FileReader("NER/MultiwithoutPersonNamesCorrected.txt"));
		
		
		String personname3=null;
		String personname1=null;
		String personname2=null;
		String personname4=null;
		Set<String> lines1 = new LinkedHashSet<String>(); 
		Set<String> lines2 = new LinkedHashSet<String>(); 
		Set<String> lines3 = new LinkedHashSet<String>(); 
		Set<String> lines4 = new LinkedHashSet<String>(); 
		//String[] split;
		while ((personname1 = reader1.readLine()) != null) 
		{	
			lines1.add(personname1);
			
		}
		
		while ((personname2 = reader2.readLine()) != null) 
		{	
			lines2.add(personname2);
			
		}
		
		while ((personname3 = reader3.readLine()) != null) 
		{	
			lines3.add(personname3);
			
		}
		
		while ((personname4 = reader4.readLine()) != null) 
		{	
			lines4.add(personname4);
			
		}
		int Match=0;
	for(String s: lines1)
		for(String str: lines3)
			if(s.equals(str))
				{Match=Match+1;
				System.out.println(s);
				}
				
	System.out.println("Common names in original and ocr files:" +Match);

	int Match2=0;
	for(String s: lines2)
		for(String str: lines3)
			if(s.equals(str))
				{Match2=Match2+1;
				System.out.println(s);
				}
				
	System.out.println("Common names in original and corrected files:" +Match2);
	
	int Match3=0;
	for(String s: lines4)
		for(String str: lines3)
			if(s.equals(str))
				{Match3=Match3+1;
				System.out.println(s);
				}
	reader1.close();
	reader2.close();			

	reader3.close();			
	reader4.close();			

	System.out.println("Common names in original and corrected without person correction files:" +Match3);
	
	System.out.println("No. of names in OCR file: "+lines1.size());
	System.out.println("No. of names in Corrected file: "+lines2.size());
	System.out.println("No. of names in Original file: "+lines3.size());
	System.out.println("No. of names in Corrected without Persons file: "+lines4.size());

	System.out.println("PNDR for OCR text:"+(float)lines1.size()/lines3.size());
	System.out.println("PNDR for Corrected text: "+(float)lines2.size()/lines3.size());
	System.out.println("PNDR for Corrected text without persons corrected: "+(float)lines4.size()/lines3.size());
	*/
		  File folder=new File ("Corrected");
		  ArrayList <String> Documents= new ArrayList <String>();
		  ArrayList <String> PersonNames= new ArrayList <String>();
			NERDemo obj=new NERDemo();
		BufferedReader reader1 = new BufferedReader(new FileReader("NER/MultiPersonNamesCorrected3.txt"));
		Documents=obj.traverseFiles(folder,Documents);
		 System.out.println(Documents.size());
		//BufferedWriter outp = new BufferedWriter(new FileWriter("NER/FinalCorrectedPersonNames3.txt"));
		//File[] listOfFiles = null;
		BufferedReader reader6=null;
		String name=null;
		while((name=reader1.readLine())!=null)
		PersonNames.add(name);
		
		for(int i=0;i<Documents.size();i++)
			reader6 = new BufferedReader(new FileReader(Documents.get(i)));
		 while(reader6.ready())
				
		 { String line1=null;
		 	line1=reader6.readLine().toLowerCase();
	
		
		    }
		 
		 }

}
