import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;


//remove duplicates etc..


public class NER{
	
public static void main (String args[]) throws Exception{
	/*BufferedReader reader1 = new BufferedReader(new FileReader("NER/FinalCorrectedPersonNames3.txt"));
	Set<String> lines = new LinkedHashSet<String>(); 
	String uniqline;
	//String[] split;
	while ((uniqline = reader1.readLine()) != null) 
	{
		lines.add(uniqline);
	}
	reader1.close();
	BufferedWriter writer1 = new BufferedWriter(new FileWriter("NER/FinalCorrectedPersonName3.txt"));
	for (String unique : lines) 
	{
		writer1.write(unique);
		writer1.newLine();
	}
	writer1.flush();
	writer1.close();*/
	/*BufferedReader reader1 = new BufferedReader(new FileReader("PersonNames8.txt"));
	String uniqline=null;
	ArrayList<String> lines = new ArrayList<String>(); 
	while ((uniqline = reader1.readLine()) != null) 
	{
		String split[]=uniqline.split("\t");
		for(int i=0;i<split.length;i++)
			lines.add(split[i]);
	
	}
	reader1.close();
			LinkedHashSet<String> uniqueValues = new LinkedHashSet<>(lines);
	BufferedWriter writer1 = new BufferedWriter(new FileWriter("PersonNames9.txt"));
	for (String unique : uniqueValues) 
	{
		writer1.write(unique);
		writer1.newLine();
	}
	writer1.flush();
	writer1.close();
	
	*/
	//Code to get person names greater than 1 gram---removing persons with single names only
	/*BufferedReader reader1 = new BufferedReader(new FileReader("NER/CorrectedwithoutPersonNames2.txt"));
	String uniqline=null;
	ArrayList<String> lines = new ArrayList<String>(); 
	while ((uniqline = reader1.readLine()) != null) 
	{
		String split[]=uniqline.split(" ");
		if(split.length>1)
			lines.add(uniqline);
	
	}
	reader1.close();
			LinkedHashSet<String> uniqueValues = new LinkedHashSet<>(lines);
	BufferedWriter writer1 = new BufferedWriter(new FileWriter("NER/MultiwithoutPersonNamesCorrected.txt"));
	for (String unique : uniqueValues) 
	{
		writer1.write(unique);
		writer1.newLine();
	}
	writer1.flush();
	writer1.close();
*/
	
	// Code to remove leading and trailing whitespace from a document
/*	FileReader fr = new FileReader("C:/Users/himanshu/Documents/final/72265 .txt"); 
	BufferedReader br = new BufferedReader(fr); 
	FileWriter fw = new FileWriter("outfile.txt"); 
	String line;

	while((line = br.readLine()) != null)
	{ 
	    line = line.trim(); // remove leading and trailing whitespace
	    if (!line.equals("")) // don't write out blank lines
	    {
	        fw.write(line, 0, line.length());
	    }
	} 
	fr.close();
	fw.close();
	*/
	File folder1=new File ("C:/Users/himanshu/Documents/final");

	File[] listOfFiles1=folder1.listFiles();
	//FileReader fr = new FileReader("C:/Users/himanshu/Documents/final/72265 .txt"); 
	//FileReader fr = new FileReader("C:/Users/himanshu/workspace/Thesis/Ocr/73786.txt");
	//BufferedReader br = new BufferedReader(fr); 
	FileWriter fw = new FileWriter("AllArtLinedoc.txt"); 
	
	
	
	for (int i = 0; i < listOfFiles1.length; i++) 
	{
		BufferedReader br = new BufferedReader(new FileReader(listOfFiles1[i]));
	StringBuilder sb = new StringBuilder();
	// adds 9 character string at beginning
	String line;
	while((line = br.readLine()) != null)
	{
		//System.out.println(line);
		sb.append(line);
		
	}
	
	
	fw.write(sb.toString()+"\n");
	
	br.close();

	}
	fw.close();
	/*FileReader fr = new FileReader("C:/Users/himanshu/Documents/final/72265 .txt"); 
	BufferedReader br = new BufferedReader(fr); 
	FileWriter fw = new FileWriter("linedoc72265.txt"); 
	String line;
	
	StringBuilder sb = new StringBuilder();
	// adds 9 character string at beginning
	
	while((line = br.readLine()) != null)
	
	line = lline.replace("\n", "").replace("\r", "");

*/



}}
