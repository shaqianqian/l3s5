package fil.coo.COO_Pisine;

import java.util.ArrayList;

public class Cubiclepool {
	
ArrayList<Cubicle> listc =new ArrayList<Cubicle>();

public Cubiclepool(int nb) {
	for(int j=0;j<nb;j++){
		this.listc.add(new Cubicle());}}

public ArrayList<Cubicle> getListc() {
	return listc;
}

public void setListc(ArrayList<Cubicle> listc) {
	this.listc = listc;
}
	
	
	
	
}

	
	
