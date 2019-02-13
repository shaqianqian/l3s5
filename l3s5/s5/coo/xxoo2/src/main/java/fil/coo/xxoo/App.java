package fil.coo.xxoo;
import java.util.Scanner;
/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {
	     joueur player=new joueur();
		 Room room=new Room(0,0);
		 while(player.getHp()>=0)
	     {
	    
		 player.move(room);
		 room.makeMonster();
		 player.regard(room);

		 int n=player.combattre(room);
		 if(n==0){break;}
	     }
		 
		}
}
