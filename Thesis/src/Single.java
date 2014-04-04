import java.io.*;

//import java.util.*;

import java.io.File;



class Single {
	static int totaltp=0;
	static int totaltn=0;
	static int totalfp=0;
	static int totalfn=0;
		
public static void main(String args[]) {
		
	        //System.out.println("Start time:" +System.currentTimeMillis());

try{
	 String  path = "61110-original.txt"; 
	 
	  File  file3 = new File (path);
	  
		 String  path2 = "61110-corrected.txt"; 
		 
		  File  file2 = new File (path2);
		
		  String  path3 = "61110-ocr.txt"; 
			 
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
	
	int fp=0;
	int tp=0;
	int tn=0;
	int fn=0; 
	for(int i=0;i<split1.length;i++) 
	{
		boolean flag0=true;
		boolean flag1=true;
		boolean flag2=true;
		boolean flag3=true;
		boolean flag4=true;
		//---------------NO SPELLLING CHANGE----------------------
		//System.out.println(split3[i]);
		if(split1[i].equals(split2[i]))
		{   //no spelling change
			
			
			//----------------FOR SENTENCES OF LENGTH 1,2 OR 3---------------------
			if(split1.length<4||split3.length<4)
			{
			
				 for(int j=0;j<split3.length;j++)
				 {System.out.println(split2[i]+" "+split3[j]);
				  if (split1[i].equals(split3[j]))
			      {tn=tn+1;
				  System.out.println("tn="+tn);
				  flag0=false;
				 break;
			      }
				 }
				 if(flag0==true)
				 		{
					    fn=fn+1;
						 System.out.println("fn="+fn);
					    }	
			}
			
			//--------------FOR SENTENCES OF LENGTH 4 AND ABOVE-----------------
			
			else{
			
			
			if (i==0)
			   {
			    for(int j=0;j<3;j++)
			    {System.out.println(split2[i]+" "+split3[j]);
			    
				if (split1[i].equals(split3[j]))
			     {tn=tn+1;
				 System.out.println("tn="+tn);
				 flag1=false;
				 break;
				 }
			    }
			    if(flag1==true)
			    //  x[j]= obj.lcs(split2[i], split3[j]);
			    //sort(x[j]);
			     {
			    fn=fn+1;
				 System.out.println("fn="+fn);
			    }
			    }
			   
			   else if (i==1)
			   {
			    for(int j=0;j<4;j++)
				{
				System.out.println(split2[i]+" "+split3[j]);
			    if (split1[i].equals(split3[j]))
			      {tn=tn+1;
			       System.out.println("tn="+tn);
			       flag2=false;
			       break;
			      }
			    
				}
			    	if(flag2==true)
			    {fn=fn+1;
			    System.out.println("fn="+fn);}	
			   
			   }
				else if((i==(split3.length-2))||(i==(split3.length-1))||(i==(split3.length))||(i==(split3.length+1)))
				{
				 for(int j=i-2;j<split3.length;j++)
				 {System.out.println(split2[i]+" "+split3[j]);
				  if (split1[i].equals(split3[j]))
			      {tn=tn+1;
				  System.out.println("tn="+tn);
				  flag3=false;
				  break;
				  }
				 }
				 if(flag3==true)
				 		{
					    fn=fn+1;
						 System.out.println("fn="+fn);
					    }
				 }
				
				else if((i>=(split3.length+2)))
				{
					for(int j=split3.length-3;j<split3.length;j++)  
					{System.out.println(split2[i]+" "+split3[j]);
					if (split1[i].equals(split3[j]))
			       { tn=tn+1;
				    System.out.println("tn="+tn);
				    flag3=false;
				    break;
				   }
					}
				 
				 if(flag3==true)
				 		{
					    fn=fn+1;
						 System.out.println("fn="+fn);
				 		}
				}
			
			
				 else
				 {
				  for(int j=i-2;j<=i+2;j++)
				  {
				   System.out.println(split2[i]+" "+split3[j]);
				   if (split1[i].equals(split3[j]))
						{tn=tn+1;
						System.out.println("tn="+tn);
						flag4=false;
						break;
						}
				  }
				  if(flag4==true)
				   		{
					    fn=fn+1;
						 System.out.println("fn="+fn);
					    }
				 }
				  	
	
		}
		}
		
			

			//----------------------SPELLING GOT CHANGED------------------------------------
		else if (!split1[i].equals(split2[i]))
   //there has been a spelling change
   { 
    System.out.println(split1[i]+ "  "+ split2[i]);
   /* if (split2[i].equals(split3[i]))
			{
			tp=tp+1;
			System.out.println("tp="+tp);
			}*/
    if(split1.length<4||split3.length<4)
	{
	
		 for(int j=0;j<split3.length;j++)
		 {System.out.println(split2[i]+" "+split3[j]);
		  if (split2[i].equals(split3[j]))
	      {tp=tp+1;
		  System.out.println("tp="+tp);
		  flag0=false;
		  break;
		  }
		 }
		 if(flag0==true)
		 		{
			    fp=fp+1;
				 System.out.println("fp="+fp);
			    }	
	}
    else{
    
    
    if (i==0)
   {
    for(int j=0;j<3;j++)
    {System.out.println(split2[i]+" "+split3[j]);
    
	if (split2[i].equals(split3[j]))
     {tp=tp+1;
	 System.out.println("tp="+tp);
	 flag1=false;
	 break;
	 }
    }
    if(flag1==true)
    //  x[j]= obj.lcs(split2[i], split3[j]);
    //sort(x[j]);
     {
    fp=fp+1;
	 System.out.println("fp="+fp);
    }
    }
   
   else if (i==1)
   {
    for(int j=0;j<4;j++)
	{
	System.out.println(split2[i]+" "+split3[j]);
    if (split2[i].equals(split3[j]))
      {tp=tp+1;
       System.out.println("tp="+tp);
       flag2=false;
       break;
      }
    
	}
    	if(flag2==true)
    {fp=fp+1;
    System.out.println("fp="+fp);}	
   
   }
	else if((i==(split3.length-2))||(i==(split3.length-1))||(i==(split3.length))||(i==(split3.length+1)))
	{
	 for(int j=i-2;j<split3.length;j++)
	 {System.out.println(split2[i]+" "+split3[j]);
	  if (split2[i].equals(split3[j]))
      {tp=tp+1;
	  System.out.println("tp="+tp);
	  flag3=false;
	  break;
	  }
	 }
	 if(flag3==true)
	 		{
		    fp=fp+1;
			 System.out.println("fp="+fp);
		    }
	 }
	
	else if((i>=(split3.length+2)))
	{
		for(int j=split3.length-2;j<split3.length;j++){
		System.out.println(split2[i]+" "+split3[j]);
		if (split2[i].equals(split3[j]))
       { tp=tp+1;
	    System.out.println("tp="+tp);
	    flag3=false;
	   break;
       }}
	 
	 if(flag3==true)
	 		{
		    fp=fp+1;
			 System.out.println("fp="+fp);
	 		}
	}
	 else
	 {
	  for(int j=i-2;j<=i+2;j++)
	  {
	   System.out.println(split2[i]+" "+split3[j]);
	   if (split2[i].equals(split3[j]))
			{tp=tp+1;
			System.out.println("tp="+tp);
			flag4=false;
			break;
			}
	  }
	  if(flag4==true)
	   		{
		    fp=fp+1;
			 System.out.println("fp="+fp);
		    }
	  
	}	
		
	}
   }
 }
	System.out.println("Statistics for this line:");
	System.out.println("True Positives:"+tp);
	System.out.println("True Negatives:"+tn);
	System.out.println("False Positives"+fp);
	System.out.println("False Negatives"+fn);
totaltp+=tp;
totaltn+=tn;
totalfp+=fp;
totalfn+=fn;
}
 reader4.close();
 reader5.close();
 reader6.close();

}
catch(Exception e){
	e.printStackTrace();
}
}}
