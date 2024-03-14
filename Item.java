import java.util.ArrayList;

public class Item extends AbstractItem{

	//String position;
	private ArrayList<String> canMoveTo = new ArrayList<String>();
	public Item(String position){
		
		this.setPosition(position);
	}
	public void remove(){
		this.setPosition("xx");
	}
	@Override
	public void move(String destination) {
		if(canMoveTo.contains(destination)){
			this.setPosition(destination);
			
		}else {
			//System.out.println("Uyari: " + getPosition() + "'daki tas " + destination + " yerine gelemez, Başka bir hamle oynayiniz");
		}
		// TODO Auto-generated method stub
		
	}
	void whereMove(Item[][] bord){

	}
	protected void setCanMoveTo(ArrayList<String> canMoveTo) {
		this.canMoveTo = canMoveTo;
	}
	public ArrayList<String> getCanMoveTo() {
		return canMoveTo;
	}
	/*protected void setPosition(String position) {
		this.position = position;
	}
	public String getPosition() {
		return position;
	}*/
	public boolean inBounds(String position){
		if((position.charAt(0)>='a' && position.charAt(0)<='j') && position.charAt(1)<='9' && position.charAt(1)>='1'){
			return true;
		}else return false;
	}
	public static boolean isBlack(Item it){
		if(it.getPieceName().charAt(0)<='z' && it.getPieceName().charAt(0)>= 'a'){
			return false;
		}else 
			return true;
		
	}
}
