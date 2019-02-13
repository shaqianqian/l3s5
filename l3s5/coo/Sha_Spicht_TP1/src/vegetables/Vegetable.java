package vegetables;

public class Vegetable {
	
	private String type;
	private int size = 0;
	
	public Vegetable(int size, String type){
		this.type = type;
		this.size = (size);
	}

	public int getSize() {
		return size;
	}
	
	public String toString(){
		return "["+type+", size="+size+"]";
	}


}
