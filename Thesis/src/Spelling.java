import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.io.File;


class Spelling {
	static int WordsCorrected=0;
	private final HashMap<String, Integer> nWords = new HashMap<String, Integer>();

	public Spelling(String file) throws IOException {
		// System.out.println("Start time for dictionary phase:" +System.currentTimeMillis());
		BufferedReader in = new BufferedReader(new FileReader(file));
		Pattern p = Pattern.compile("\\w+");
		for(String temp = ""; temp != null; temp = in.readLine()){
			Matcher m = p.matcher(temp);
			while(m.find()) nWords.put((temp = m.group()), nWords.containsKey(temp) ? nWords.get(temp) + 1 : 1);
		}
		//File file5=new File ("C:/Users/himanshu/Desktop/dictionary2.txt");
		//BufferedWriter reader5 = new BufferedWriter(new FileWriter(file5));
		
		//reader5.write(nWords.toString());
		//reader5.close();
		in.close();
		// System.out.println("End time for reading dictionary:" +System.currentTimeMillis());
	}

	private final ArrayList<String> edits(String word) {
		 //System.out.println("Start time for finding word combos:" +System.currentTimeMillis());
		ArrayList<String> result = new ArrayList<String>();
		for(int i=0; i < word.length(); ++i) result.add(word.substring(0, i) + word.substring(i+1));
		for(int i=0; i < word.length()-1; ++i) result.add(word.substring(0, i) + word.substring(i+1, i+2) + word.substring(i, i+1) + word.substring(i+2));
		for(int i=0; i < word.length(); ++i) for(char c='a'; c <= 'z'; ++c) result.add(word.substring(0, i) + String.valueOf(c) + word.substring(i+1));
		for(int i=0; i <= word.length(); ++i) for(char c='a'; c <= 'z'; ++c) result.add(word.substring(0, i) + String.valueOf(c) + word.substring(i));
		//System.out.println("printing result arraylist"+result);
		 //System.out.println("End time for finding word combos:" +System.currentTimeMillis());
		return result;
	}

	public final String correct(String word) {
		//System.out.println("Start time of correct method:" +System.currentTimeMillis());
		if((nWords.containsKey(word))||(nWords.containsKey(word.toLowerCase()))||(nWords.containsKey(word.toUpperCase()))) 
			
		{	System.out.println(word+" is already correct");
		 //System.out.println("End time for correct method:" +System.currentTimeMillis());
			return word;
					}
		ArrayList<String> list = edits(word);
		HashMap<Integer, String> candidates = new HashMap<Integer, String>();
		
		//String word2=Character.toUpperCase(word.charAt(0))+word.substring(1);
		 for(String s : list) 
		 {if(nWords.containsKey(s))
				candidates.put(nWords.get(s),s);
			if(nWords.containsKey(s.toLowerCase()))
				candidates.put(nWords.get(s.toLowerCase()),s.toLowerCase());
			if(nWords.containsKey(s.toUpperCase())) 
				candidates.put(nWords.get(s.toUpperCase()),s.toUpperCase());
			if(nWords.containsKey(Character.toUpperCase(s.charAt(0))+s.substring(1)) )
				candidates.put(nWords.get(Character.toUpperCase(s.charAt(0))+s.substring(1)),Character.toUpperCase(s.charAt(0))+s.substring(1));
		 }
	//System.out.println("Candidate list initially:"+ candidates);
			if(candidates.size() > 0) return candidates.get(Collections.max(candidates.keySet()));
			for(String s : list) for(String w : edits(s)) 
			 {if(nWords.containsKey(w))
					candidates.put(nWords.get(w),w);
				if(nWords.containsKey(w.toLowerCase()))
					candidates.put(nWords.get(w.toLowerCase()),w.toLowerCase());
				if(nWords.containsKey(w.toUpperCase())) 
					candidates.put(nWords.get(w.toUpperCase()),w.toUpperCase());
				if(nWords.containsKey(Character.toUpperCase(w.charAt(0))+w.substring(1)))
					candidates.put(nWords.get(Character.toUpperCase(w.charAt(0))+w.substring(1)),Character.toUpperCase(w.charAt(0))+w.substring(1));
			 }
			//System.out.println("Candidate list finally:"+ candidates);
		//	for(String s : list) for(String w : edits(s)) for (String str: edits(w)) if(nWords.containsKey(str)) candidates.put(nWords.get(str),str);
			return candidates.size() > 0 ? candidates.get(Collections.max(candidates.keySet())) : word;
		
	}
	
	public static void main(String args[]) throws IOException {
			        System.out.println("Start time:" +System.currentTimeMillis());

			//File file1=new File ("C:/Users/himanshu/workspace/Thesis/74541.txt");  //INPUT THE FILE WHERE SPELLING IS TO BE CORRECTED
			File file1=new File ("outfile.txt");
			BufferedReader reader1 = new BufferedReader(new FileReader(file1));
			File file2=new File ("outfile2.txt");		//NEW FILE WITH CORRECT SPELLINGS
			BufferedWriter writer1 = new BufferedWriter(new FileWriter(file2));
			//System.out.println("hello");
			String line1;
			Spelling sObj = new Spelling("Finaldict.txt");
			//writer1.write("hello2");
				while((line1=reader1.readLine())!=null)
				{ 
				//String line2=line1.toLowerCase();
				//System.out.println(line2);
				String[] split = line1.split(" ");
				for(int i=0;i<split.length;i++)
				{ 
					String corrected=null;
				//writer1.write(split[i]+":"+(new Spelling("big.txt")).correct(split[i]));
					corrected=sObj.correct(split[i]);
					if(!(corrected.equals(split[i])))
							{WordsCorrected=WordsCorrected+1;
							System.out.println(WordsCorrected);
							}
				writer1.write(corrected + " ");	
				//System.out.println("End time for correct method:" +System.currentTimeMillis());
				System.out.println(split[i]+" is corrected to "+corrected);
				//writer1.newLine();	
					
				}
				writer1.newLine();	
				writer1.flush();
				}
							
							writer1.close();
							reader1.close();
							 System.out.println("Number of words corrected:"+WordsCorrected);
        System.out.println("End time:" +System.currentTimeMillis());

					/*	log 	time for both  methods	and individually first 100 locations miscorrections, a correction, remained wrong after correction// 
					how to evaluate , time to generate lists to check scalability  1 page from each day and check the distribution o
					analyze output from NER how many locations r being recognized , are they being recognized and how many wrong and how many r not recognized
					pipeline , pdf or jpeg	, what's at fault? NER at fault or spell checker 		*/		

	}

}
