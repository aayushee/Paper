
import java.io.*;

//import java.util.*;

import java.io.File;



class SingleShort {
	static int totaltp=0;
	static int totaltn=0;
	static int totalfp=0;
	static int totalfn=0;
		
public void calculate( String [] ocrline,String [] correctline,String [] originalline,int jstart,int jend,int i)
	{
	int tp=0,fp=0,tn=0,fn=0;
	boolean flag0=true;
	boolean flag1=true;
	for(int j=jstart;j<jend;j++)
	 {System.out.println(correctline[i]+" "+originalline[j]);
	 if (correctline[i].equals(originalline[j])&&(!(ocrline[i].equals(correctline[i]))))
     {
	  tp=tp+1;
	  System.out.println("tp="+tp);
	  flag0=false;
	  break;
	  }
	 else if ((correctline[i].equals(originalline[j]))&&(ocrline[i].equals(correctline[i])))
	      {
				 tn=tn+1;
		  System.out.println("tn="+tn);
		  flag1=false;
		 break; 
	      }
	 }

	 if(!(ocrline[i].equals(correctline[i])) && flag0==true)
	 //if(flag0==true&&flag==true)
	 		{
		    fp=fp+1;
			 System.out.println("fp="+fp);
		    }
	 
	 else if((ocrline[i].equals(correctline[i])) && flag1==true)
	 		{
		    fn=fn+1;
			 System.out.println("fn="+fn);
		    }
}
	
	
	
public static void main(String args[]) {
		
	        //System.out.println("Start time:" +System.currentTimeMillis());
	
	
try{
	 String  path = "C:/Users/himanshu/workspace/Thesis/Original/73901-original.txt"; 
	 
	  File  file3 = new File (path);
	  
		 String  path2 = "C:/Users/himanshu/workspace/Thesis/Corrected/73901.txt"; 
		 
		  File  file2 = new File (path2);
		
		  String  path3 = "C:/Users/himanshu/workspace/Thesis/Ocr/73901.txt"; 
			 
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
		boolean flag5=true;
		boolean flag6=true;
		boolean flag7=true;
		boolean flag8=true;
		boolean flag9=true;
		boolean flag10=true;
		boolean flag11=true;
		
		
	
  
    if(split1.length<4||split3.length<4)
	{
    	//if (!split1[i].equals(split2[i]))
		 for(int j=0;j<split3.length;j++)
		 {System.out.println(split2[i]+" "+split3[j]);
		 if (split2[i].equals(split3[j])&&(!(split1[i].equals(split2[i]))))
	      {
		  tp=tp+1;
		  System.out.println("tp="+tp);
		  flag0=false;
		  break;
		  }
		 else if ((split2[i].equals(split3[j]))&&(split1[i].equals(split2[i])))
		      {
					 tn=tn+1;
			  System.out.println("tn="+tn);
			  flag5=false;
			 break; 
		      }
		 }
   
		 if(!(split1[i].equals(split2[i])) && flag0==true)
		 //if(flag0==true&&flag==true)
		 		{
			    fp=fp+1;
				 System.out.println("fp="+fp);
			    }
		 
		 else if((split1[i].equals(split2[i])) && flag5==true)
		 		{
			    fn=fn+1;
				 System.out.println("fn="+fn);
			    }
		 
		 
	}
    else{
    
    
    if (i==0)
   {
    for(int j=0;j<3;j++)
    {System.out.println(split2[i]+" "+split3[j]);
    
	if ((split2[i].equals(split3[j]))&&(!(split1[i].equals(split2[i]))))
     {tp=tp+1;
	 System.out.println("tp="+tp);
	 flag1=false;
	 break;
	 }
	else if ((split2[i].equals(split3[j]))&&(split1[i].equals(split2[i])))
    {tn=tn+1;
	 System.out.println("tn="+tn);
	 flag6=false;
	 break;
	 }
   }
   if((split1[i].equals(split2[i]))&& flag6==true)
    {
   fn=fn+1;
	 System.out.println("fn="+fn);
   }
    
   else if((!(split1[i].equals(split2[i])))&& flag1==true)
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
    if ((split2[i].equals(split3[j]))&&(!(split1[i].equals(split2[i]))))
      {tp=tp+1;
       System.out.println("tp="+tp);
       flag2=false;
       break;
      }
    else if ((split1[i].equals(split3[j]))&&(split1[i].equals(split2[i])))
    {tn=tn+1;
	  System.out.println("tn="+tn);
	  flag7=false;
	  break;
	  }
	 }
	 if((split1[i].equals(split2[i]))&& flag7==true)
	 		{
		    fn=fn+1;
			 System.out.println("fn="+fn);
		    }
	 else if((!(split1[i].equals(split2[i])))&& flag2==true)
    {fp=fp+1;
    System.out.println("fp="+fp);
    }	
   
   }
	else if((i==(split3.length-2))||(i==(split3.length-1))||(i==(split3.length))||(i==(split3.length+1)))
	{
	 for(int j=i-2;j<split3.length;j++)
	 {System.out.println(split2[i]+" "+split3[j]);
	  if ((split2[i].equals(split3[j]))&&(!(split1[i].equals(split2[i]))))
      {tp=tp+1;
	  System.out.println("tp="+tp);
	  flag3=false;
	  break;
	  }
	  
	  else if ((split1[i].equals(split3[j]))&&(split1[i].equals(split2[i])))
      { tn=tn+1;
	    System.out.println("tn="+tn);
	    flag8=false;
	    break;
	   }
		}
	 
	 if((split1[i].equals(split2[i])) && flag8==true)
	 		{
		    fn=fn+1;
			 System.out.println("fn="+fn);
	 		}
	 else if((!(split1[i].equals(split2[i]))) && flag3==true)
	 		{
		    fp=fp+1;
			 System.out.println("fp="+fp);
		    }
	 }
	
	else if((i>=(split3.length+2)))
	{
		for(int j=split3.length-2;j<split3.length;j++){
		System.out.println(split2[i]+" "+split3[j]);
		if ((split2[i].equals(split3[j]))&&(!(split1[i].equals(split2[i]))))
       { tp=tp+1;
	    System.out.println("tp="+tp);
	    flag4=false;
	   break;
       }
		else if ((split1[i].equals(split3[j]))&&(split1[i].equals(split2[i])))
       { tn=tn+1;
 	    System.out.println("tn="+tn);
 	    flag9=false;
 	    break;
 	   }
 		}
 	 
 	 if((split1[i].equals(split2[i]))&& flag9==true)
 	 		{
 		    fn=fn+1;
 			 System.out.println("fn="+fn);
 	 		}
	 
 	 else if((!(split1[i].equals(split2[i])))&& flag4==true)
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
	   if ((!(split1[i].equals(split2[i])))&& (split2[i].equals(split3[j])))
			{tp=tp+1;
			System.out.println("tp="+tp);
			flag10=false;
			break;
			}
	   
	   else if (split1[i].equals(split3[j])&&(split1[i].equals(split2[i])))
		{tn=tn+1;
		System.out.println("tn="+tn);
		flag11=false;
		break;
		}
 }
 if((split1[i].equals(split2[i]))&& flag11==true)
  		{
	    fn=fn+1;
		 System.out.println("fn="+fn);
	    }
 else if((!(split1[i].equals(split2[i])))&& flag10==true)
	   		{
		    fp=fp+1;
			 System.out.println("fp="+fp);
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
 System.out.println("True positives in this file: "+totaltp);
 System.out.println("True negatives in this file: "+totaltn);
 System.out.println("False positives in this file: "+totalfp);
 System.out.println("False negatives in this file: "+totalfn);
 int acc1=totaltp+totaltn;
 	int acc2=totaltp+totaltn+totalfp+totalfn;
 	float acc=(float)acc1/acc2;
 System.out.println("Accuracy of algorithm="+acc*100);
}
catch(Exception e){
	e.printStackTrace();
}
}}
