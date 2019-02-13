package donjon;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;


public class joueur  {
private int num_monster;
private int hp=20;
private int force=1;
private int or=0;
private String obj_or;
private String obj_hp;
private String obj_force;
List<String> list=new ArrayList<String>();

public joueur(int num_monster, int type_monster, int hp, int force, int or, String obj_or, String obj_hp,
		String obj_force, List<String> list) {
	super();
	this.num_monster = num_monster;
	this.hp = hp;
	this.force = force;
	this.or = or;
	this.obj_or = obj_or;
	this.obj_hp = obj_hp;
	this.obj_force = obj_force;
	this.list = list;
}
public joueur() {
	
}
public static void printRoom(Room room)
{
	int totalRow=10;
	int totalCol=10;
 System.out.println("votre position is "+room.getRoomInfo());
	for(int row=0;row<totalRow;row++)
	{ for(int col=0;col<totalCol;col++){
		if(room.row==row&&room.col==col)
		{System.out.println("*");}
		else{System.out.println("-");}
	}  System.out.println("\r\n");}
	
}
public static void move(Room room)
{ 	
  
    printRoom(room);
    System.out.println("go to where"+"\n"+"choose 1 est up"+"\n"
    		+"choose 2 est down"+"\n"+"choose 3 est left"+"\n"
    		+"choose 4 est right"+"\n"
    		);
    Scanner scan=new Scanner(System.in);
    int direction=scan.nextInt();
    
    switch(direction){
    case 1:room.up();
    case 2:room.down();
    case 3:room.moveLeft();
    case 4:room.moveRight();
    default :break;
    
    }
    printRoom(room);}
 
public void regard(Room room)
{ int awardType=room.makeItem();
  switch(awardType){
  case 0:list.add("obj_or");break;
  case 1:list.add("obj_hp"); break;
  case 2:list.add("obj_force");break;
  case 3:list.add(null);break;
  default :break;}
  for(int i=0;i<list.size();i++){
		System.out.println(i+","+list.get(i));
  }
  System.out.println("you want to use a object,yes please enter 1,else enter 0");
  Scanner scan=new Scanner(System.in);
  int use=scan.nextInt();
  if(use==1){
	  System.out.println("please enter the num of object which you want to use");
  int num=scan.nextInt();
  if(list.get(num)=="obj_or"){this.or++;}
  else if(list.get(num)=="obj_hp"){this.hp++;}
  else if(list.get(num)=="obj_force"){this.force++;}
  list.remove(num);
  if(use==0){};
  }
  
}

public int combattre(Room room)
{	
	int n=room.makeMonster();  
    if(n<=hp){System.out.println("you win in the room");hp=hp-n;
    System.out.println("now your state is "+"hp= "+" "+hp+" "+"or= "+or+" "+
    "force ="+force);
    return 1;}
    else{System.out.println("echoue");return 0;}
    	
    	
 }
	
	
public int getHp() {
	return hp;
}
public void setHp(int hp) {
	this.hp = hp;
}
public int getForce() {
	return force;
}
public void setForce(int force) {
	this.force = force;
}


}
  

