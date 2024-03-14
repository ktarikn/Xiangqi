import java.util.ArrayList;

/**
 * Vezir
 */
public class Vezir extends Item {
    public Vezir(String position){
        super(position);
        if(position.charAt(0)=='a'){
            this.setPieceName("v");
        }else this.setPieceName("V");
        setTasPuan(2);
    }
    @Override
    void whereMove(Item[][] bord) {
        ArrayList<String> temp = new ArrayList<String>();
        int[] ab = Board.posToIndexConverter(this.getPosition());
        
        if(ab[1]==4){
                if(bord[ab[0]+1][ab[1]+1] == null || !(Item.isBlack(bord[ab[0]+1][ab[1]+1]) == Item.isBlack(this)) ){
                    temp.add(Board.indexToPosConverter(ab[0]+1,ab[1]+1));
                } 
                if(bord[ab[0]-1][ab[1]-1] == null || !(Item.isBlack(bord[ab[0]-1][ab[1]-1]) == Item.isBlack(this)) ){
                    temp.add(Board.indexToPosConverter(ab[0]-1,ab[1]-1));
                } 
                if(bord[ab[0]-1][ab[1]+1] == null || !(Item.isBlack(bord[ab[0]-1][ab[1]+1]) == Item.isBlack(this)) ){
                    temp.add(Board.indexToPosConverter(ab[0]-1,ab[1]+1));
                } 
                if(bord[ab[0]+1][ab[1]-1] == null || !(Item.isBlack(bord[ab[0]+1][ab[1]-1]) == Item.isBlack(this)) ){
                    temp.add(Board.indexToPosConverter(ab[0]+1,ab[1]-1));
                } 
        }else{
            if(Item.isBlack(this)){
                if(bord[8][4] == null || !(Item.isBlack(bord[8][4]) == Item.isBlack(this)) ){
                    temp.add("i5");
                } 
            }else
            {
                if(bord[1][4] == null || !(Item.isBlack(bord[1][4]) == Item.isBlack(this)) ){
                    temp.add("b5");
                } 
            }
        }
        this.setCanMoveTo(temp);
    }
        
}
