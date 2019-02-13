package fil.coo.xxoo;
import java.util.Random;

	public class Room {
	 int row;
	 int col;
	 private int awardType;
	public int getAwardType() {
		return awardType;
	}
	public void setAwardType(int awardType) {
		this.awardType = awardType;
	}
	public Room(int row, int col) {
		super();
		this.row = row;
		this.col = col;
		this.awardType=awardType;
	}
	 public void down()
	 {row++;}
	 public void up()
	 { row--; }
	 public void moveLeft()
	 {col--;}
	 public void moveRight()
	 {col++;}
	 public String getRoomInfo()
	 {return "row is "+row+" col is "+col  ;}
	 
	 public int makeMonster(){
	   Random rand = new Random();
	   int num_monster=rand.nextInt(5);
	  System.out.println("il y a"+" "+num_monster+" monsters");
	  return num_monster;
	 }
	 public int makeItem(){
		 Random rand=new Random();
		 awardType=rand.nextInt(4);
	    switch(awardType){
	    case 0:System.out.println("get a or");break;
	    case 1:System.out.println("get a soin"); break;
	    case 2:System.out.println("get a force");break;
	    case 3:System.out.println("get nothing");break;
	    default :break;
	 
	    }
		return awardType;


	    
	 }
		 
	 }







