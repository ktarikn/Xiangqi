
import java.util.ArrayList;
/**
 * Kale
 */
public class Kale extends Item {
    /*@Override
    public void move(String destination) {
        
    };*/
    public Kale(String position){
        super(position);
        if(position.charAt(0)=='a'){
            this.setPieceName("k");
        }else this.setPieceName("K");
        setTasPuan(9);
    }
    void whereMove( Item[][] bord){
        ArrayList<String> temp = new ArrayList<String>();
        int[] ab = Board.posToIndexConverter(this.getPosition());
        for(int i = ab[0]+1; i<bord.length;i++){
            if(!inBounds(Board.indexToPosConverter(i,ab[1])))
                break;
            if(bord[i][ab[1]]== null){
                temp.add(Board.indexToPosConverter(i,ab[1]));
            }
            else if(isBlack(bord[i][ab[1]]) == isBlack(this)){
                break;
            }
            else {

                temp.add(Board.indexToPosConverter(i,ab[1]));
                break;
            }
        }
        for(int i = ab[0]-1; i>=0;i--){
            if(!inBounds(Board.indexToPosConverter(i,ab[1])))
                break;
            if(bord[i][ab[1]]== null){
                temp.add(Board.indexToPosConverter(i,ab[1]));
            }
            else if(isBlack(bord[i][ab[1]]) == isBlack(this)){
                break;
            }
            else {

                temp.add(Board.indexToPosConverter(i,ab[1]));
                break;
            }
        }
        for(int i = ab[1]-1; i>=0;i--){
            if(!inBounds(Board.indexToPosConverter(ab[0],i)))
                break;
            if(bord[ab[0]][i]== null){
                temp.add(Board.indexToPosConverter(ab[0],i));
            }
            else if(isBlack(bord[ab[0]][i]) == isBlack(this)){
                break;
            }
            else {

                temp.add(Board.indexToPosConverter(ab[0],i));
                break;
            }
        }
        for(int i = ab[1]+1; i<bord[0].length;i++){
            if(!inBounds(Board.indexToPosConverter(ab[0],i)))
                break;
            if(bord[ab[0]][i]== null){
                temp.add(Board.indexToPosConverter(ab[0],i));
            }
            else if(isBlack(bord[ab[0]][i]) == isBlack(this)){
                break;
            }
            else {

                temp.add(Board.indexToPosConverter(ab[0],i));
                break;
            }
        }
        this.setCanMoveTo(temp);

    }
    
}