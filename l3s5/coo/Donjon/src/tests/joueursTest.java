package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import donjon.*;


public class joueursTest {
	
    joueur j=new joueur();
 	Room room=new Room(3,3);
	@Test
	public void test1() {
		
        joueur.printRoom(room);		
		
	}
	@Test
	public void test2(){
		joueur.move(room);
		
		
	}

    @Test
    public void test3(){
    
    	j.regard(room);
    	
       
    }
    
    

}