import java.net.*;
import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;



class DocumentSimilarity
{


 public static void main(String[] args) throws Exception {
    
    String folder="Corrected";
    String text1="";
    String text2="";
	 String urlToCall = "http://www.scurtu.it/apis/documentSimilarity";
    String content = "doc1=" + URLEncoder.encode(text1, "UTF-8") +
		      "&doc2=" + URLEncoder.encode(text2,"UTF-8");
    
    
    
    URL url = new URL(urlToCall); 
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();                       
    connection.setDoOutput(true); 
    connection.setDoInput (true);
    connection.setUseCaches (false);        
    connection.setInstanceFollowRedirects(false); 
    connection.setRequestMethod("POST"); 
    connection.setRequestProperty("Content-Type", "text/plain"); 
    connection.setRequestProperty("charset", "utf-8");
    connection.connect();
    
    
    DataOutputStream output = new DataOutputStream(connection.getOutputStream());
    output.writeBytes(content);
    output.flush();
    output.close();
    
    StringBuilder strBuffer = new StringBuilder();
    String str = null;
    BufferedReader input = new BufferedReader (new InputStreamReader(connection.getInputStream()));
            while (null != ((str = input.readLine()))) {
                strBuffer.append(str);
      }
      
    Object obj=JSONValue.parse(strBuffer.toString());
    JSONObject jsonObj = (JSONObject)obj;    
   // System.out.println(jsonObj);
    Double score = (Double) jsonObj.get("result");
	System.out.println(score);
    
    
    }
}
