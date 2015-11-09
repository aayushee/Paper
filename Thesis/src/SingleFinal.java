
import java.io.*;

//import java.util.*;

import java.io.File;



class SingleFinal {
	static int totaltp=0;
	static int totaltn=0;
	static int totalfp=0;
	static int totalfn=0;
	static int tp=0;
	static int fp=0;
	static int tn=0;
	static int fn=0;
		
public int calculate( String [] ocrline,String [] correctline,String [] originalline,int jstart,int jend,int i)
	{
	//int tp=0,fp=0,tn=0,fn=0;
	boolean flag0=true;
	boolean flag1=true;
	for(int j=jstart;j<jend;j++)
	 {
		System.out.println(correctline[i]+" "+originalline[j]);
	 if (correctline[i].equals(originalline[j])&&(!(ocrline[i].equals(correctline[i]))))
     {
	  tp=tp+1;
	  System.out.println("tp="+tp);
	  flag0=false;
	 return tp;
	  
	  }
	 else if ((correctline[i].equals(originalline[j]))&&(ocrline[i].equals(correctline[i])))
	      {
				 tn=tn+1;
		  System.out.println("tn="+tn);
		  flag1=false;
		return tn;
		  // break; 
	      }
	 }

	 if(!(ocrline[i].equals(correctline[i])) && flag0==true)
	 //if(flag0==true&&flag==true)
	 		{
		    fp=fp+1;
			 System.out.println("fp="+fp);
			 return fp;
		    }
	 
	 else if((ocrline[i].equals(correctline[i])) && flag1==true)
	 		{
		    fn=fn+1;
			 System.out.println("fn="+fn);
			 return fn;
		    }
	
	 return 1;
	//return tp,fp,tn,fn;
}
	
	
	
	
public static void main(String args[]) {
		
	        //System.out.println("Start time:" +System.currentTimeMillis());
	
	
try{
	SingleFinal obj=new SingleFinal(); 
	String  path = "C:/Users/himanshu/workspace/Thesis/Original/73867-original.txt"; 
	 
	  File  file3 = new File (path);
	  
		 String  path2 = "C:/Users/himanshu/workspace/Thesis/Corrected/73867.txt"; 
		 
		  File  file2 = new File (path2);
		
		  String  path3 = "C:/Users/himanshu/workspace/Thesis/Ocr/73867.txt"; 
			 
			  File  file1 = new File (path3);
			 

			  BufferedReader reader4=null;
			  BufferedReader reader5=null;
			  BufferedReader reader6=null;

	 reader4 = new BufferedReader(new FileReader(file1));
	 reader5 = new BufferedReader(new FileReader(file2));
	 reader6 = new BufferedReader(new FileReader(file3));
	String line1=null;
	//line1=reader4.readLine().toLowerCase();
while(reader4.ready())
				
{ 
	line1=reader4.readLine().toLowerCase();
	
	String line3=null;
  String line2=null;
  line2=reader5.readLine().toLowerCase();
  line3=reader6.readLine().toLowerCase();
  if(line2==null)
	  System.out.println("Error in reader 5");
    if(line3==null)
	  System.out.println("Error in reader 6");
  
  System.out.println(line1);
  System.out.println(line2);
  System.out.println(line3);
    String[] split1=line1.split(" ");
	String[] split2=line2.split(" ");
	String[] split3=line3.split(" ");			
	
	//int fp=0;
	//int tp=0;
	//int tn=0;
	//int fn=0; 
	for(int i=0;i<split1.length;i++) 
	{

    if(split1.length<4||split3.length<4)
	{		
    	obj.calculate(split1,split2,split3,0,split3.length,i); 
	}
    else{
    if (i==0)
   {
   obj.calculate(split1, split2, split3, 0, 3, 0);  	
   }
   else if (i==1)
   {
	   obj.calculate(split1, split2, split3, 0, 4, 1);   
   }
	else if(i==(split3.length-2))
	{
	 	obj.calculate(split1, split2, split3, i-2, split3.length, i);	
	 }  
	else if(i==(split3.length-1))
	{ 
		obj.calculate(split1, split2, split3, i-2, split3.length, i);	
	 }
	else if(i==(split3.length))
	{ 
		obj.calculate(split1, split2, split3, i-2, split3.length, i);	
	 }
    
	else if(i==(split3.length+1))
	{ 
		obj.calculate(split1, split2, split3, i-2, split3.length, i);	
	 }
	else if((i>=(split3.length+2)))
	{
		obj.calculate(split1, split2, split3, split3.length-3, split3.length, i);	
	}
	else
	 {
		obj.calculate(split1, split2, split3, i-2, i+2, i);
	}	
	
   }
 }
	//System.out.println("Statistics for this line:");
	//System.out.println("True Positives:"+tp);
	//System.out.println("True Negatives:"+tn);
	//System.out.println("False Positives"+fp);
	//System.out.println("False Negatives"+fn);

}
 reader4.close();
 reader5.close();
 reader6.close();
 System.out.println("True positives in all files: "+tp);
 System.out.println("True negatives in all files: "+tn);
 System.out.println("False positives in all files: "+fp);
 System.out.println("False negatives in all files: "+fn);
 int acc1=tp+tn;
 	int acc2=tp+tn+fp+fn;
 	float acc=(float)acc1/acc2;
 System.out.println("Accuracy of algorithm="+acc*100);
}
catch(Exception e){
	e.printStackTrace();
}
}}
