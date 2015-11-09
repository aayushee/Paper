import java.io.*;

//import java.util.*;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;



class Testing {
	static int totaltp=0;
	static int totaltn=0;
	static int totalfp=0;
	static int totalfn=0;
		/*public int lcs(String one, String two)
		{
        int i,j;
        String X = "barry";  // String X 
        String Y = "cry";    // String Y 
         initialize the n x m matrix B and C for dynamic programming 
          B[i][j] stores the directions, C[i][j] stores the length of LCS of
          X[0..i-1] and Y[0..j-1]
          
        int n = X.length();
        int m = Y.length();
        int[][] C = new int[n+1][m+1];
        int[][] B = new int[n+1][m+1];
	
        /* C[i][0] = 0 for 0<=i<=n 
        for (i = 0; i <= n; i++) {
            C[i][0] = 0;
        }
	
        /* C[0][j] = 0 for  0<=j<=m 
        for (j = 0; j <= m; j++) {
            C[0][j] = 0;
        }
        
        /* dynamic programming 
        for (i = 1; i <= n; i++) {
            for (j = 1; j <= m; j++) {
                if (X.charAt(i-1) == Y.charAt(j-1)) {
                    C[i][j]=C[i-1][j-1]+1;
                    B[i][j]=1;  /* diagonal 
                }
                else if (C[i-1][j]>=C[i][j-1]) {
                    C[i][j]=C[i-1][j];
                    B[i][j] = 2;  /* down 
                }
                else {
                    C[i][j]=C[i][j-1];     
                    B[i][j]=3;   /* forword 
                }
            }
        }
        /* Backtracking 
        String lcs = new String();
        i=n;
        j=m;
        while (i!=0 && j!=0) {
            if (B[i][j] ==1) {   /* diagonal 
                lcs =X.charAt(i-1) + lcs;
                i = i - 1;
                j = j - 1;
            
            if (B[i][j] == 2) {  /* up 
                i = i - 1;
            }
            if (B[i][j] == 3) {  /* backword 
                j = j - 1;
            }
        }
        
        /* print out the result 
        System.out.println("String X is " + X);
        System.out.println("String Y is " + Y);
        System.out.println("The length of LCS is " + C[n][m]);
        System.out.println("The LCS is " + lcs);
        return C[n][m];
    }
		
		*/
public static void main(String args[]) {
		
	        //System.out.println("Start time:" +System.currentTimeMillis());

try{
	 String  path = "Original"; 
	 
	 
	  File  file3 = new File (path);
	  File [] original = file3.listFiles(); 
	  /*String  files;
	  for (int i = 0; i < original.length; i++) 
	  {
	 
	   if (original[i].isFile()) 
	   {
	   files = original[i].getName();
	   System.out.println(files);
	      }
	  }*/
	
	  
		 String  path2 = "CorrectedTwice"; 
		  File  file2 = new File (path2);
		  File [] corrected = file2.listFiles(); 
		  /*Arrays.sort(corrected, new Comparator<File>(){
			    public int compare(File f1, File f2)
			    {
			        return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
			    } }); */
		  /*String  files1;
		  for (int i = 0; i < corrected.length; i++) 
		  {
		 
		   if (corrected[i].isFile()) 
		   {
		   files1 = corrected[i].getName();
		   System.out.println(files1);
		      }
		  }*/
		  
			 String  path3 = "Ocr"; 
			  File  file1 = new File (path3);
			  File [] ocr = file1.listFiles(); 
			  /*String  files3;
			  for (int i = 0; i < ocr.length; i++) 
			  {
			 
			   if (ocr[i].isFile()) 
			   {
			   files3 = ocr[i].getName();
			   System.out.println(files3);
			   
			      }
			  }*/
System.out.println(ocr.length);
System.out.println(original.length);
System.out.println(corrected.length);
			  BufferedReader reader4=null;
			  BufferedReader reader5=null;
			  BufferedReader reader6=null;
for(int x=0;x<ocr.length;x++){
	 reader4 = new BufferedReader(new FileReader(ocr[x]));
	 reader5 = new BufferedReader(new FileReader(corrected[x]));
	 reader6 = new BufferedReader(new FileReader(original[x]));
	String line1=null;
	
//while((line1=reader4.readLine().toLowerCase())!=null )
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
				  for(int j=i-2;j<i+3;j++)
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
	else if((i==(split3.length-2))||(i==(split3.length-1))||(i==(split3.length))||(i==(split3.length+1))||(i>=(split3.length+2)))
	{
	 for(int j=split3.length-3;j<split3.length;j++)
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
	
	/*else if((i>=(split3.length+2)))
	{
		int j=split3.length-1;
		System.out.println(split2[i]+" "+split3[j]);
		if (split2[i].equals(split3[j]))
       { tp=tp+1;
	    System.out.println("tp="+tp);
	    flag3=false;
	   break;
       }
	 
	 if(flag3==true)
	 		{
		    fp=fp+1;
			 System.out.println("fp="+fp);
	 		}
	}*/
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
System.out.println("True positives in all files: "+totaltp);
System.out.println("True negatives in all files: "+totaltn);
System.out.println("False positives in all files: "+totalfp);
System.out.println("False negatives in all files: "+totalfn);
int acc1=totaltp+totaltn;
	int acc2=totaltp+totaltn+totalfp+totalfn;
	float acc=(float)acc1/acc2;
System.out.println("Accuracy of algorithm="+acc*100);
//float balacc1=(float) totaltp/(totaltp+totalfn);
//float balacc2=(float)totaltn/(totaltn+totalfp);
//float balacc=balacc1+balacc2;
//System.out.println("Balanced Accuracy of algorithm="+balacc*50);
float prec=(float)totaltp/(totaltp+totalfp);
System.out.println("Precision of algorithm="+prec*100);
}
catch(Exception e){
	e.printStackTrace();
}
}}