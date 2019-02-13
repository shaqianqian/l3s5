package postfixees;
public class Div  extends Operateur implements Yytoken{
 
  protected int calcul(int[] values){
    return values[1]/values[0];
  }
  
  public Div(String image){
    super(image,2);
  }
  
}
