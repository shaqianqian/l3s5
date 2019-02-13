package ooxx.ooxx;

import java.rmi.activation.ActivationException;
import java.util.ArrayList;
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {int i=0;
    ArrayList<Swimmer> swimmers = new ArrayList<Swimmer>();
    swimmers.add(new Swimmer("Canmille", 6000, 4000, 8000));
    swimmers.add(new Swimmer("Lois", 2000, 10000, 4000));
    swimmers.add(new Swimmer("mae", 10000, 18000, 10000));
    swimmers.add(new Swimmer("ange", 3000, 7000, 5000));
    swimmers.add( new Swimmer("louison", 18000, 3000, 3000));
    swimmers.add(new Swimmer("charlie", 3000, 6000, 10000));
    swimmers.add(new Swimmer("alexis", 6000, 5000, 7000));
    final basektpool baskets=new basektpool(6);
    final Cubiclepool cubicles=new Cubiclepool(3);
    int nb=baskets.listb.size();
    int nc=cubicles.listc.size();
    int step=0;
    final pool p=new pool();

    int j=0;

    for(int k=0;k<swimmers.size();k++)
    {	
    for(i=0; i<nb; i++)
    {
    	p.faire1(swimmers.get(i),baskets,cubicles);
    }
     {  step++;
    	
       System.out.println("  success");
    } 
     p.faire1(swimmers.get(i),baskets,cubicles);
      
     System.out.println("  failed"); 
    }

    for(int f=0;f<nb;f++)
    { 
    for(j=0;j<nc;j++)
      { p.faire2(swimmers.get(j),baskets,cubicles);
    	step++;   
    	System.out.println("  success");
      }  
    p.faire2(swimmers.get(j),baskets,cubicles);
    System.out.println("  failed");}
    int m=0;
    for(m=0;m<j;m++)
    { p.faire3(swimmers.get(m),baskets,cubicles);step++;}
    for(m=0;m<j;m++)
    { p.faire4(swimmers.get(m),baskets,cubicles);step++;}
    for(m=0;m<j;m++)
    { p.faire5(swimmers.get(m),baskets,cubicles);step++;}
    for(m=0;m<j;m++)
    { p.faire6(swimmers.get(m),baskets,cubicles);step=step+2;
    }


    for(i=nb-nc-1;i<swimmers.size();i++)
    {	 p.faire1(swimmers.get(i),baskets,cubicles);
         step++;
    	 System.out.println("  success");
    } 
    for(int f=nb-nc;i<swimmers.size();f++)
    { 
    for(j=nb-nc;j<swimmers.size()-1;j++)
      { p.faire2(swimmers.get(j),baskets,cubicles);
    	step++;   System.out.println("  success");}  
       p.faire2(swimmers.get(j),baskets,cubicles); 
       
     System.out.println("  failed");}
      for(m=nb-nc;m<j;m++)
    { p.faire3(swimmers.get(m),baskets,cubicles);step++;}
    for(m=nb-nc;m<j;m++)
    { p.faire4(swimmers.get(m),baskets,cubicles);step++;}
    for(m=nb-nc;m<j;m++)
    { p.faire5(swimmers.get(m),baskets,cubicles);step++;}
    for(m=nb-nc;m<j;m++)
    { p.faire6(swimmers.get(m),baskets,cubicles);step=step+2;
    }

    for(i=nb-(nb-nc);i<swimmers.size();i++)
      { p.faire2(swimmers.get(i),baskets,cubicles);
    	step++;   System.out.println("  success");
    	p.faire2(swimmers.get(i),baskets,cubicles);
    	step++;   System.out.println("  success");
      }  
       
    for(m=nb-(nb-nc);m<i;m++)
    { p.faire3(swimmers.get(m),baskets,cubicles);step++;}
    for(m=nb-(nb-nc);m<i;m++)
    { p.faire4(swimmers.get(m),baskets,cubicles);step++;}
    for(m=nb-(nb-nc);m<i;m++)
    { p.faire5(swimmers.get(m),baskets,cubicles);step++;}
    for(m=nb-(nb-nc);m<i;m++)
    { p.faire6(swimmers.get(m),baskets,cubicles);step=step+2;
    }


    System.out.println("step = "+step);
        
    	
    	
    	
    	
    	
    }
}
