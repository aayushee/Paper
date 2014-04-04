import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.*;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.AnswerAnnotation;
import edu.stanford.nlp.util.*;


import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.File.*;
import java.io.*;

//find all person names in documents
/** This is a demo of calling CRFClassifier programmatically.
 *  <p>
 *  Usage: <code> java -mx400m -cp "stanford-ner.jar:." NERDemo [serializedClassifier [fileName]]</code>
 *  <p>
 *  If arguments aren't specified, they default to
 *  ner-eng-ie.crf-3-all2006.ser.gz and some hardcoded sample text.
 *  <p>
 *  To use CRFClassifier from the command line:
 *  java -mx400m edu.stanford.nlp.ie.crf.CRFClassifier -loadClassifier
 *      [classifier] -textFile [file]
 *  Or if the file is already tokenized and one word per line, perhaps in
 *  a tab-separated value format with extra columns for part-of-speech tag,
 *  etc., use the version below (note the 's' instead of the 'x'):
 *  java -mx400m edu.stanford.nlp.ie.crf.CRFClassifier -loadClassifier
 *      [classifier] -testFile [file]
 *
 *  @author Jenny Finkel
 *  @author Christopher Manning
 */

public class NERDemo {
	public ArrayList<String> traverseFiles(File inputDir, ArrayList<String> Documents2 )
	{
		//ArrayList <String> Documents=new ArrayList <String>();
		if (inputDir.isDirectory()) {
			//System.out.println("Checking for directory...");
	        String[] children = inputDir.list();
	        for (int i = 0; children != null && i < children.length; i++) {
	            traverseFiles(new File(inputDir, children[i]), Documents2);
	        }
	    }
	    if (inputDir.isFile()) 
	    	{ Documents2.add(inputDir.getAbsolutePath());}//change it if needed
	  
	    return Documents2;
	   // System.out.println(Documents.size());
	}

    public static void main(String[] args) throws IOException {

      String serializedClassifier = "classifiers/english.all.3class.distsim.crf.ser.gz";

      if (args.length > 0) {
        serializedClassifier = args[0];
      }

      AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
   
	  try {
	 
		  File folder=new File ("50docslinetext.txt");
	  
	   ArrayList <String> Documents= new ArrayList <String>();
		NERDemo obj=new NERDemo();
		Documents=obj.traverseFiles(folder,Documents);
		 System.out.println(Documents.size());
		BufferedWriter outp = new BufferedWriter(new FileWriter("50ArtDoclinePersonNames.txt"));
		//File[] listOfFiles = null;
		for(int i=0;i<Documents.size();i++)
		
	
	{
				
			System.out.println(Documents.get(i));
			
			//BufferedWriter output= new BufferedWriter(new FileWriter("Newspapers/Location/Location"+i+".txt"));
			String fileContents = IOUtils.slurpFile(Documents.get(i));
			File f=new File(Documents.get(i));
			
			List<Triple<String, Integer, Integer>> entities =classifier.classifyToCharacterOffsets(fileContents);
		
			for (Triple<String, Integer, Integer> triple : entities) 
			{			
				String ename = fileContents.substring(triple.second,triple.third);
			
				String entity = ename.replaceAll("\\*", "").replaceAll("\\n", " ").replaceAll("\\!", "");
			
				if (entity.length()>1) 
				{
				
				   if (triple.first.equalsIgnoreCase("PERSON"))
					   outp.write(entity.trim()+" "+f.getName()+"\n");
				 
				      
				    
				  // if (triple.first.equalsIgnoreCase("LOCATION"))
					//   output.write(entity.trim()+"\n");
				}
			}
		
	
}
		outp.close();
}
		 catch (Exception e) 
    { 
       System.out.println("Exception "+e);
    }

       
		
		
		/*for (List<CoreLabel> sentence : out) {
          for (CoreLabel word : sentence) {
			if (word.get(AnswerAnnotation.class).equals("PERSON"))        
		outp.write(word.word() +"\n");
		if (word.get(AnswerAnnotation.class).equals("LOCATION"))        
		output.write(word.word()+ "\n");
        }
		 } 
        for (List<CoreLabel> sentence : out) {
          for (CoreLabel word : sentence) {
            System.out.print(word.word() + '/' + word.get(AnswerAnnotation.class) + ' ');
          }
        }*/

      //} 
    }

}
