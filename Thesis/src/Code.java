// Import io so we can use file objects
import java.io.*;
import java.util.*;
import java.net.*;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Code {
    public static void main(String args[])throws IOException{
        try{
           
		File folder1=new File ("Person");
		File folder2=new File ("Location");

		File[] listOfFiles1=folder1.listFiles();
		File[] listOfFiles2=folder2.listFiles();
	   Arrays.sort(listOfFiles1, new Comparator<File>(){
    public int compare(File f1, File f2)
    {
        return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
    } });
	 Arrays.sort(listOfFiles2, new Comparator<File>(){
    public int compare(File f1, File f2)
    {
        return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
    } });
	   //REMOVE DUPLICATES FROM PERSON FILES
	   
	    for (int i = 0; i < listOfFiles1.length; i++) 
		{

		BufferedReader reader1 = new BufferedReader(new FileReader(listOfFiles1[i]));
		Set<String> lines = new LinkedHashSet<String>(); 
		String uniqline;
		while ((uniqline = reader1.readLine()) != null) 
		{
			lines.add(uniqline);
		}
		reader1.close();
		BufferedWriter writer1 = new BufferedWriter(new FileWriter("Person2/Person"+i+".txt"));
		for (String unique : lines) 
		{
			writer1.write(unique);
			writer1.newLine();
		}
		writer1.flush();
		writer1.close();
	}
	
	
	//REMOVE DUPLICATES FROM LOCATION FILES
	
	    for (int i = 0; i < listOfFiles2.length; i++) 
	{	

		   BufferedReader reader2 = new BufferedReader(new FileReader(listOfFiles2[i]));

			Set<String> lines2 = new LinkedHashSet<String>(); 
			String uniqline2;
			while ((uniqline2 = reader2.readLine()) != null) 
		{
			lines2.add(uniqline2);
		}
			reader2.close();
			BufferedWriter writer2 = new BufferedWriter(new FileWriter("Location2/Location"+i+".txt"));
		for (String unique2 : lines2) 
		{
			writer2.write(unique2);
			writer2.newLine();
		}
		writer2.flush();
		writer2.close();
	}
	
		
		//GEOCODING ALL THE LOCATION FILES NOW
		
		File folder4=new File ("Location2");

		File[] listOfFiles4=folder4.listFiles();
		 Arrays.sort(listOfFiles4, new Comparator<File>(){
    public int compare(File f1, File f2)
    {
        return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
    } });
		for(int j=0;j< listOfFiles4.length;j++)
		{
	   
			BufferedReader buf = new BufferedReader(new FileReader(listOfFiles4[j]));
		    BufferedWriter out = new BufferedWriter(new FileWriter("Geocoded/Location"+j+".txt"));

		   URL url;
		   
			String unique1;
			//out.write("location,latitude,longitude\n");
			try{
		while ((unique1 = buf.readLine()) != null) 
		{
	
		String[] split=unique1.split(" ");
		if((split.length == 1))
		{
			String x="http://maps.googleapis.com/maps/api/geocode/xml?address="+split[0]+"&sensor=false";
			url = new URL(x);
		}
		else if((split.length == 2))

		{
			String y="http://maps.googleapis.com/maps/api/geocode/xml?address="+split[0]+"+"+split[1]+"&sensor=false";
			url = new URL(y);
		
		}
		
		else 
		{
			String z="http://maps.googleapis.com/maps/api/geocode/xml?address="+split[0]+"+"+split[1]+"+"+split[2]+"&sensor=false";
			url = new URL(z);			
			
		}
		
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
     
		  Document geocoderResultDocument = null;
    
		// open the connection and get results as InputSource.
		con.connect();
		InputSource geocoderResultInputSource = new InputSource(con.getInputStream());

		// read result and parse into XML Document
		try
		{
			geocoderResultDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(geocoderResultInputSource);
		}
		catch(Exception e)
		{
			System.out.println("Exception is:"+e);
		}
     
		con.disconnect();

		// prepare XPath
		XPath xpath = XPathFactory.newInstance().newXPath();

		// extract the result
		NodeList resultNodeList = null;
		 
		 try
			{ 
			resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result[1]/geometry/location/*", geocoderResultDocument, XPathConstants.NODESET);
			}
		catch(Exception e)
		{
			System.out.println("Exception is:"+e);
		}
		
		float lat = Float.NaN;
		float lng = Float.NaN;
		for(int i=0; i<resultNodeList.getLength(); ++i) 
		{
			Node node = resultNodeList.item(i);
			if("lat".equals(node.getNodeName())) lat = Float.parseFloat(node.getTextContent());
			if("lng".equals(node.getNodeName())) lng = Float.parseFloat(node.getTextContent());
		}
		String end= unique1+","+lat+","+lng;
		out.write(end+"\n");
		out.flush();
		}	//END OF WHILE LOOP
		//out.flush();
		out.close();
		//buf.flush();
		buf.close();
		}
		
		catch (MalformedURLException e)
		{
            e.printStackTrace();
        } 
		catch (IOException e) 
		{
            e.printStackTrace();
		}
		}
	   
	   
	   //ALGORITHM FOR LINKING PERSONS WITH LOCATIONS
		
		File folder5=new File ("Person2");		//person files
		File folder6=new File ("Geocoded");	//geocoded location files
		File folder7=new File ("Files");		//LOCATION WHERE ORIGINAL NEWSPAPER TEXT FILES WERE DOWNLOADED AND SAVED
		File[] listOfFiles5=folder5.listFiles();
		File[] listOfFiles6=folder6.listFiles();
		File[] listOfFiles7=folder7.listFiles();
		 Arrays.sort(listOfFiles5, new Comparator<File>(){
    public int compare(File f1, File f2)
    {
        return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
    } });
	 Arrays.sort(listOfFiles6, new Comparator<File>(){
    public int compare(File f1, File f2)
    {
        return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
    } });
	 Arrays.sort(listOfFiles7, new Comparator<File>(){
    public int compare(File f1, File f2)
    {
        return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
    } });
		
	   for(int l=0;l< listOfFiles7.length;l++)
	   {
			File file = listOfFiles7[l];
			if (file.isFile() && file.getName().endsWith(".txt")) 
			{
			BufferedReader bf = new BufferedReader(new FileReader(listOfFiles7[l]));	//for reading original newspaper text files
			BufferedReader br1 = new BufferedReader(new FileReader(listOfFiles6[l]));  //for taking last location from LOCATIONS file
			BufferedReader br0 = new BufferedReader(new FileReader(listOfFiles5[l]));  //for taking first person from PERSONS file
			BufferedReader br = new BufferedReader(new FileReader(listOfFiles5[l])); 	//reading PERSONS file
			//BufferedWriter br2 = new BufferedWriter(new FileWriter(newfile2));	//for Storing PERSONS POSITIONS in file
			BufferedReader br3 = new BufferedReader(new FileReader(listOfFiles6[l]));	//reading LOCATIONS file
			//BufferedWriter br4 = new BufferedWriter(new FileWriter(newfile4));	//for storing LOCATIONS POSITIONS in file
			BufferedWriter out = new BufferedWriter(new FileWriter("Database/Database"+l+".csv"));	//for storing final database!

			
			List<String> results=new ArrayList<String>();
			String line2,firstperson,lastlocation;
			firstperson=br0.readLine();
			System.out.println(firstperson);
			br0.close();
			String strLine = null, tmp;
 
			while ((tmp = br1.readLine()) != null)
			{
				strLine = tmp;
			}
			String arr[]=strLine.split(",");
			lastlocation =arr[0];
			System.out.println(lastlocation);
			br1.close();
			
			
			while ((line2 = br.readLine()) != null) 
			{
			results.add(line2);
			//System.out.println(line2);
			}
			br.close();
			List<String> results2=new ArrayList<String>();
			String line3;
			
			while ((line3 = br3.readLine()) != null) 
			{
			results2.add(line3);
			//System.out.println(line3);
			}
		br3.close();
			
		StringBuilder sb = new StringBuilder();
        String line = bf.readLine();

        while (line != null) 
		{
            sb.append(line);
            line = bf.readLine();
        }
		bf.close();  

		String content = sb.toString();
					content=content.replaceAll("(\\r|\\n)", " ");
		
			int x=content.indexOf(firstperson); 
			int y=content.lastIndexOf(lastlocation);			
			float z=y-x;
			//System.out.println(z);
				
				//FINDING DIFFERENCE IN POSITION OF PERSONS AND LOCATIONS IN TEXTFILE
			BufferedWriter br5 = new BufferedWriter(new FileWriter("Difference/Difference"+l+".txt"));	//for storing difference in positions

				for(String result: results)
		{
				for(String resulting:results2)
			{
					 
					 String splits[]=resulting.split(",");
					 int i=0;
					 int j=0;
					 
					while((i=(content.indexOf(result,i)+1))>0)
				{	
					while((j=(content.indexOf(splits[0],j)+1))>0)
					{
						float k=i-j;
						//System.out.println(Math.abs(k/z));
						if((Math.abs(k/z))<0.025)
						br5.write(result+","+resulting+",score=" + Math.abs(k/z) + "\n");
						System.out.println(result+","+resulting+",score=" + Math.abs(k/z) + "\n");
					}
				}	
			}
		}
					
            // Close the file after done searching
			br5.close();
	
				//FINDING POSITION OF PERSONS IN TEXTFILE
				
				/*for(String result: results)
				{
					 int i=0;	 
				while((i=(content.indexOf(result,i)+1))>0)        
                  br2.write("Word "+result+ " was found at position " + i + " in text \n");
                }
				br2.close();	
			
			
			//FINDING POSITION OF LOCATIONS IN TEXT FILE
			for(String resulting: results2)
				{
					String some[]=resulting.split(",");
					 int j =0;
					while((j=(content.indexOf(some[0],j)+1))>0)
                    br4.write("Word "+some[0]+ " was found at position " + j + " in text \n");  

				}
									br4.close();
									*/
			
			BufferedReader buf = new BufferedReader(new FileReader("Difference/Difference"+l+".txt"));	//for reading difference file again to remove redundancy

		   Set<String> output = new LinkedHashSet<String>(); 
			String unique1;
			String[] splitnew={};
			while ((unique1 = buf.readLine()) != null) 
			{
				splitnew = unique1.split(",");
				output.add(splitnew[0]+","+splitnew[1]+","+splitnew[2]+","+splitnew[3]);
			}
			for (String unique2 : output) 
			{
				out.write(unique2);
				out.write("\n");
			}	
			//out.flush();
			out.close();
			//buf.flush();
			buf.close();	
		}
		}
		}
        catch (IOException e)
		{
            System.out.println("IO Error Occurred: " + e.toString());
        }
    }
}