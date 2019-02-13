package pisine;

import java.util.ArrayList;

public class pool {
	
	public void faire1(Swimmer s, basektpool baskets, Cubiclepool cubicles) throws InterruptedException{
   
	Thread t=Thread.currentThread();
    System.out.println(s.getNom() + "'s turn \n  trying "
				+ "to take resource from basket");
	
	}
	public void faire2(Swimmer s, basektpool baskets, Cubiclepool cubicles) throws InterruptedException{
	
	   Thread t=Thread.currentThread();
	    System.out.println(s.getNom()+"  's turn \n trying to take"
				+ " resource from cubicle");
	
	
	}
	
	public void faire3(Swimmer s, basektpool baskets, Cubiclepool cubicles) throws InterruptedException{
	   
		Thread t=Thread.currentThread();
	   
		System.out.println(s.getNom()+" 's turn \n undressing");
		Thread.sleep(s.getT_dehabille());
		
		}
	
	public void faire4(Swimmer s, basektpool baskets, Cubiclepool cubicles) throws InterruptedException{
		   
		Thread t=Thread.currentThread();
		System.out.println(s.getNom()+" 's turn \n baigner");
		Thread.sleep(s.getT_baigner());
		
		}
	
	public void faire5(Swimmer s, basektpool baskets, Cubiclepool cubicles) throws InterruptedException{
		   
		Thread t=Thread.currentThread();
	   
		
	System.out.println(s.getNom()+" 's turn \n dressing");
		Thread.sleep(s.getT_habiller());
		
		}
	

	public void faire6(Swimmer s, basektpool baskets, Cubiclepool cubicles) throws InterruptedException{
		   
		Thread t=Thread.currentThread();
	  
		System.out.println(s.getNom()+" 's turn \n freeing resource from basket");
		System.out.println(s.getNom()+" 's turn \n freeing resource from cubicle");
		
	
	}
	
	
	
	
}
