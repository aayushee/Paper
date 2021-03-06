<<<<<<< HEAD
 
//Edit distance Spelling correction on the articles repository
import java.io.*;
=======
 import java.io.*;
>>>>>>> 00cfaa4f6d9f464ba9dccbfc741c73ee2b9e8766
import java.util.*;
import java.util.regex.*;
import java.io.File;


class Spelling1 {
	static int WordsCorrected=0;

	private final HashMap<String, Integer> nWords = new HashMap<String, Integer>();

	public Spelling1(String file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		Pattern p = Pattern.compile("\\w+");
		for(String temp = ""; temp != null; temp = in.readLine()){
			Matcher m = p.matcher(temp);
			while(m.find()) nWords.put((temp = m.group()), nWords.containsKey(temp) ? nWords.get(temp) + 1 : 1);
		}
		in.close();
	}

	private final ArrayList<String> edits(String word) {
		ArrayList<String> result = new ArrayList<String>();
		for(int i=0; i < word.length(); ++i) result.add(word.substring(0, i) + word.substring(i+1));
		for(int i=0; i < word.length()-1; ++i) result.add(word.substring(0, i) + word.substring(i+1, i+2) + word.substring(i, i+1) + word.substring(i+2));
		for(int i=0; i < word.length(); ++i) for(char c='a'; c <= 'z'; ++c) result.add(word.substring(0, i) + String.valueOf(c) + word.substring(i+1));
		for(int i=0; i <= word.length(); ++i) for(char c='a'; c <= 'z'; ++c) result.add(word.substring(0, i) + String.valueOf(c) + word.substring(i));
		return result;
	}

	public final String correct(String word) {
		if(nWords.containsKey(word)||(nWords.containsKey(word.toLowerCase()))||(nWords.containsKey(word.toUpperCase())))
			return word;
		ArrayList<String> list = edits(word);
		HashMap<Integer, String> candidates = new HashMap<Integer, String>();
		 
		 for(String s : list) 
		 {if(nWords.containsKey(s))
				candidates.put(nWords.get(s),s);
<<<<<<< HEAD
		 else if(nWords.containsKey(s.toLowerCase()))
				candidates.put(nWords.get(s.toLowerCase()),s.toLowerCase());
		 else if(nWords.containsKey(s.toUpperCase())) 
				candidates.put(nWords.get(s.toUpperCase()),s.toUpperCase());
		// else if(nWords.containsKey(Character.toUpperCase(s.charAt(0))))
			//	candidates.put(nWords.get(Character.toUpperCase(s.charAt(0))),Character.toUpperCase(s.charAt(0)));
=======
			if(nWords.containsKey(s.toLowerCase()))
				candidates.put(nWords.get(s.toLowerCase()),s.toLowerCase());
			if(nWords.containsKey(s.toUpperCase())) 
				candidates.put(nWords.get(s.toUpperCase()),s.toUpperCase());
			if(nWords.containsKey(Character.toUpperCase(s.charAt(0))+s.substring(1)))
				candidates.put(nWords.get(Character.toUpperCase(s.charAt(0))+s.substring(1)),Character.toUpperCase(s.charAt(0))+s.substring(1));
>>>>>>> 00cfaa4f6d9f464ba9dccbfc741c73ee2b9e8766
		 }
	//System.out.println("Candidate list initially:"+ candidates);
			if(candidates.size() > 0) return candidates.get(Collections.max(candidates.keySet()));
			for(String s : list) for(String w : edits(s)) 
			 {if(nWords.containsKey(w))
					candidates.put(nWords.get(w),w);
<<<<<<< HEAD
			 else if(nWords.containsKey(w.toLowerCase()))
					candidates.put(nWords.get(w.toLowerCase()),w.toLowerCase());
			 else if(nWords.containsKey(w.toUpperCase())) 
					candidates.put(nWords.get(w.toUpperCase()),w.toUpperCase());
			 //else if(nWords.containsKey(Character.toUpperCase(w.charAt(0))))
				//	candidates.put(nWords.get(Character.toUpperCase(w.charAt(0))),Character.toUpperCase(w.charAt(0))));
=======
				if(nWords.containsKey(w.toLowerCase()))
					candidates.put(nWords.get(w.toLowerCase()),w.toLowerCase());
				if(nWords.containsKey(w.toUpperCase())) 
					candidates.put(nWords.get(w.toUpperCase()),w.toUpperCase());
				if(nWords.containsKey(Character.toUpperCase(w.charAt(0))+w.substring(1)))
					candidates.put(nWords.get(Character.toUpperCase(w.charAt(0))+w.substring(1)),Character.toUpperCase(w.charAt(0))+w.substring(1));
>>>>>>> 00cfaa4f6d9f464ba9dccbfc741c73ee2b9e8766
			 }
		return candidates.size() > 0 ? candidates.get(Collections.max(candidates.keySet())) : word;
	}
	
	public static void main(String args[]) throws IOException {
			        
		System.out.println("Start time:" +System.currentTimeMillis());

<<<<<<< HEAD
			File folder=new File ("C:\\Users\\AAYUSHEE\\Documents\\OCR ARTICLES");	//INPUT THE DIRECTORY CONTAINING ALL ARTICLES
=======
			File folder=new File ("Ocr");	//INPUT THE DIRECTORY CONTAINING ALL ARTICLES
>>>>>>> 00cfaa4f6d9f464ba9dccbfc741c73ee2b9e8766
	   File[] listOfFiles=folder.listFiles();   //LIST AND COMPARE THE FILES ACCORDING TO LAST DATE MODIFIED SO THAT NUMBERING OF FILES IS PRESERVED
	  Spelling1 obj=new Spelling1("Finaldict.txt");
	   
	    for (int i = 0; i < listOfFiles.length; i++) 
	{
	if (listOfFiles[i].isFile()) 
	{
			System.out.println("Correcting "+listOfFiles[i].getName());
			
			BufferedReader reader1 = new BufferedReader(new FileReader(listOfFiles[i]));
<<<<<<< HEAD
			File file2=new File ("C:\\Users\\AAYUSHEE\\Documents\\final\\"+listOfFiles[i].getName());		//NEW FILE WITH CORRECT SPELLINGS
=======
			File file2=new File ("Corrected/"+listOfFiles[i].getName());		//NEW FILE WITH CORRECT SPELLINGS
>>>>>>> 00cfaa4f6d9f464ba9dccbfc741c73ee2b9e8766
			BufferedWriter writer1 = new BufferedWriter(new FileWriter(file2));
			String line1;
				while((line1=reader1.readLine())!=null)
				{ 
				//System.out.println("line read: "+line1);
				
					String[] split = line1.split(" ");
				//String[] split1 =new String[50];
				for(int j=0;j<split.length;j++)
				{
					String corrected=null;
					//split1[j]=split[j].toLowerCase();
					corrected=obj.correct(split[j]);
					if(!(corrected.equals(split[j])))
						{WordsCorrected=WordsCorrected+1;
<<<<<<< HEAD
	//					System.out.println(split[j]+" corrected to "+corrected);
=======
						//System.out.println(split[j]+" corrected to "+corrected);
>>>>>>> 00cfaa4f6d9f464ba9dccbfc741c73ee2b9e8766
						}
				writer1.write(corrected + " ");  //INPUT THE DICTIONARY BIG.TXT HERE				
				}
				writer1.newLine();	
				writer1.flush();
				}
							
							writer1.close();
							reader1.close();
		}
			}
	   System.out.println("Number of words corrected:"+WordsCorrected);
        System.out.println("End time:" +System.currentTimeMillis());

					/*	log 	time for both  methods	and individually first 100 locations miscorrections, a correction, remained wrong after correction// 
					how to evaluate , time to generate lists to check scalability  1 page from each day and check the distribution o
					analyze output from NER how many locations r being recognized , are they being recognized and how many wrong and how many r not recognized
					pipeline , pdf or jpeg	, what's at fault? NER at fault or spell checker 		*/		

	}

}
