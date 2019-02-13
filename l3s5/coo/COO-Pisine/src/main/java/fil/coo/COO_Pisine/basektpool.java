package fil.coo.COO_Pisine;

import java.util.ArrayList;

public class basektpool {
	ArrayList<Basket> listb =new ArrayList<Basket>();

	public  basektpool(int nb) {
		for(int j=0;j<nb;j++){
			this.listb.add(new Basket());}}

	public ArrayList<Basket> getListc() {
		return listb;
	}

	public void setListc(ArrayList<Cubicle> listc) {
		this.listb = listb;
	}
	
	
}
