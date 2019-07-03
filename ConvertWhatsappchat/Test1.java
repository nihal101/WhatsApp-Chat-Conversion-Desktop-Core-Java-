

import java.util.*;
import java.io.*;
public class Test1 {
    
    public static String s1="";
    public static String s2="";
    public static void Find_name(String path) throws FileNotFoundException, IOException
    {
        int i=0;
        FileInputStream in=new FileInputStream(path);
        LinkedList al=new LinkedList();
        while(s1.isEmpty()||s2.isEmpty())
        {
            while((i=in.read())!=-1)
            {
                if((char)i=='-')
                {
                    while((i=in.read())!=':')
                    {
                        al.add((char)i);
                    }
                    if(s1.isEmpty())
                        s1=al.toString();
                    else if(!s1.equals(al.toString()))
                        s2=al.toString();
                    al.clear();
                }
            }
        }
    }
    
   public static String convert(String s)
    {
        int i;
        String s1="";
        char a='\u0000';
        for(i=1;i<s.length()-1;i++)
        {
            if(s.charAt(i)!=','&&s.charAt(i)!=' ')
            {
               a=s.charAt(i);
               s1=s1+a;
            }
        }
        return(s1);
    }
   
   public static String equal(String s)
   {
       
       if(s.equals(s1))
           return(s1);
       else if(s.equals(s2))
           return(s2);
       return("");    
   }
    public static void main(String[] args) throws IOException { 
        
        String r1="\r\n";
        byte[] Change_line=r1.getBytes();
        String r2="\r\n\r\n\t\t\t\t\t\t\t";
        byte[] Right=r2.getBytes();
        byte[] b;
        Scanner sc=new Scanner(System.in);
        System.out.println("set path from where to be read");
        String p1=sc.next();
        FileInputStream in =new FileInputStream(p1);
        System.out.println("set path where to be write");
        String p2=sc.next();
        FileOutputStream out=new FileOutputStream(p2);
        LinkedList al=new LinkedList();
        Find_name(p1);
        s1=convert(s1);
        s2=convert(s2);
        
        String s=null;
        int i=0;
        char c='\u0000';
        while((i=in.read())!=-1)
        {
            if((char)i=='-')
            {
                c=(char)i;
                while((i=in.read())!=':')
                {
                    al.add((char)i);
                }
               // System.out.println(al);
                s=al.toString();
                s=convert(s);
                s=equal(s);
                al.clear();
                
                if(s.equals(s1))
                {
                    out.write(Change_line);
                    b=s1.getBytes();
                    out.write(b);
                }
                else if(s.equals(s2))
                {
                    out.write(Right);
                    b=s2.getBytes();
                    out.write(b);
                }
            }
            if(i==13)
            {
                c='\u0000';
                out.write(Change_line);
            }
            if((char)i!=c&&c=='-')
            {
                if(s.equals(s1))
                    out.write((char)i);
                else if(s.equals(s2))
                {
                    out.write((char)i);
                }
            }
        }
    }
 
}
