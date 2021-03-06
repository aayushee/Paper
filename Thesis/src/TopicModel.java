

//import cc.mallet.util.*;
import cc.mallet.types.*;
import cc.mallet.pipe.*;
import cc.mallet.pipe.iterator.*;
import cc.mallet.topics.*;

import java.util.*;
import java.util.regex.*;
import java.io.*;



    
    public class TopicModel 
    
    {
    	public static <K, V extends Comparable<V>> Map<K, V> RankScores(final Map<K, V> myMap)
        {
            Comparator<K> Comparator1 = new Comparator<K>() {
                public int compare(K k1, K k2)
                    {
                        int compare = myMap.get(k2).compareTo(myMap.get(k1));
                        if (compare == 0)
                            return 1;
                        else return compare;
                    }
            };
            Map<K, V> sortedMap = new TreeMap<K, V>(Comparator1);
            sortedMap.putAll(myMap);
            return sortedMap;
        }
    	
    	 
    	static Map<Integer,Integer> DocTopicMap=new HashMap<Integer,Integer>();
	public static void main(String[] args) throws Exception {
		Map<Integer,List<String>> hm4= new HashMap<Integer,List<String>>();
		// Begin by importing documents from text to feature sequences
		ArrayList<Pipe> pipeList = new ArrayList<Pipe>();

		// Pipes: lowercase, tokenize, remove stopwords, map to features
		pipeList.add( new CharSequenceLowercase() );
		pipeList.add( new CharSequence2TokenSequence(Pattern.compile("\\p{L}[\\p{L}\\p{P}]+\\p{L}")) );
		pipeList.add(new TokenSequenceLowercase());
		pipeList.add( new TokenSequenceRemoveStopwords(new File("C:/mallet/stoplists/en.txt"), "UTF-8", false, false, false) );
		pipeList.add( new TokenSequence2FeatureSequence() );

		InstanceList instances = new InstanceList (new SerialPipes(pipeList));

		Reader fileReader = new InputStreamReader(new FileInputStream(new File("50docslinetext.txt")), "UTF-8");
		instances.addThruPipe(new CsvIterator (fileReader, Pattern.compile("^(\\S*)[\\s,]*(\\S*)[\\s,]*(.*)$"),3, 2, 1)); // data, label, name fields
		//FileIterator iterator=new FileIterator(new File("Corrected"),new TxtFilter(),FileIterator.LAST_DIRECTORY);
		//instances.addThruPipe(iterator);								   
											   
											   
		// Create a model with 100 topics, alpha_t = 0.01, beta_w = 0.01
		//  Note that the first parameter is passed as the sum over topics, while
		//  the second is 
		int numTopics = 10;
		ParallelTopicModel model = new ParallelTopicModel(numTopics, 1.0, 0.01);

		model.addInstances(instances);

		// Use two parallel samplers, which each look at one half the corpus and combine
		//  statistics after every iteration.
		model.setNumThreads(2);

		// Run the model for 50 iterations and stop (this is for testing only, 
		//  for real applications, use 1000 to 2000 iterations)
		model.setNumIterations(50);
		model.estimate();

		// Show the words and topics in the first instance

		// The data alphabet maps word IDs to strings
		Alphabet dataAlphabet = instances.getDataAlphabet();
		for(int i=0;i<5;i++)
		{
		FeatureSequence tokens = (FeatureSequence) model.getData().get(i).instance.getData();  //document no?
		LabelSequence topics = model.getData().get(i).topicSequence;  //document no?
		
		Formatter out = new Formatter(new StringBuilder(), Locale.US);
		for (int position = 0; position < tokens.getLength(); position++) {
			out.format("%s-%d ", dataAlphabet.lookupObject(tokens.getIndexAtPosition(position)), topics.getIndexAtPosition(position));
		}
		System.out.println(out);
		
		// Estimate the topic distribution of the first instance, 
		//  given the current Gibbs state.
		double[] topicDistribution = model.getTopicProbabilities(i);  //array with probability distribution of each topic
		
		//sort the topic distribution array to get highest topic. topicDistribution.
	
	//	Arrays.sort(topicDistribution);
		
		
		Map<Integer,Float> TopicScoreMap = new HashMap<Integer,Float>();
	
		for (int j = 0; j < numTopics; j++) 
		//System.out.println("topic no: "+j+" topic probability: "+topicDistribution[j]);
		TopicScoreMap.put(j, (float)topicDistribution[j]);
		
		RankScores(TopicScoreMap);
	//System.out.println(RankScores(TopicScoreMap));
	Map.Entry<Integer,Float> maxEntry = null;

	for(Map.Entry <Integer,Float> entry : TopicScoreMap.entrySet()) {
	    if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
	        maxEntry = entry;
	    }
	}
		//System.out.println(maxEntry);
		
		DocTopicMap.put(i,maxEntry.getKey());
		//System.out.println("Document with its highest scoring topic:"+DocTopicMap);
		// Get an array of sorted sets of word ID/count pairs
		ArrayList<TreeSet<IDSorter>> topicSortedWords = model.getSortedWords();
		// Show top 5 words in topics with proportions for the first document
		for (int topic = 0; topic < numTopics; topic++) {
			List<String> TopicWords=new ArrayList <String>();
			Iterator<IDSorter> iterator1 = topicSortedWords.get(topic).iterator();
			
			out = new Formatter(new StringBuilder(), Locale.US);
			out.format("%d\t%.3f\t", topic, topicDistribution[topic]);
			int rank = 0;
			while (iterator1.hasNext() && rank < 5) {
				IDSorter idCountPair = iterator1.next();
				out.format("%s (%.0f) ", dataAlphabet.lookupObject(idCountPair.getID()), idCountPair.getWeight());
				
				TopicWords.add(dataAlphabet.lookupObject(idCountPair.getID()).toString());
				hm4.put(topic,TopicWords);
				
				//System.out.println("for topic "+topic+"topic words are: "+dataAlphabet.lookupObject(idCountPair.getID()));
				rank++;
			}
			System.out.println(out);
		}
		
		// Create a new instance with high probability of topic 0
		/*StringBuilder topicZeroText = new StringBuilder();
		Iterator<IDSorter> iterator1 = topicSortedWords.get(i).iterator();

		int rank = 0;
		while (iterator1.hasNext() && rank < 5) {
			IDSorter idCountPair = iterator1.next();
			topicZeroText.append(dataAlphabet.lookupObject(idCountPair.getID()) + " ");
			rank++;
		}

		// Create a new instance named "test instance" with empty target and source fields.
		InstanceList testing = new InstanceList(instances.getPipe());
	//	System.out.println("Printing instance list:\t" + testing);
		testing.addThruPipe(new Instance(topicZeroText.toString(), null, "test instance", null));

		TopicInferencer inferencer = model.getInferencer();
		double[] testProbabilities = inferencer.getSampledDistribution(testing.get(i), 10, 1, 5);
		System.out.println("0\t" + testProbabilities[i]);*/
	}
	System.out.println(DocTopicMap);	
	System.out.println(hm4);	

	}
}
    
 