import java.util.ArrayList;
/**
 * Fil
 */
public class Fil extends Item {
    public Fil(String position){
        super(position);
        if(position.charAt(0)=='a'){
            this.setPieceName("f");
        }else this.setPieceName("F");
        setTasPuan(2);
    }
    @Override 
    void whereMove(Item[][] bord) {
        ArrayList<String> temp = new ArrayList<String>();
        int[] ab = Board.posToIndexConverter(this.getPosition());
        if(inBounds(Board.indexToPosConverter(ab[0]+1,ab[1]+1)) && bord[ab[0]+1][ab[1]+1] == null){
            if(inBounds(Board.indexToPosConverter(ab[0]+2,ab[1]+2)) &&  (bord[ab[0]+2][ab[1]+2] == null || Item.isBlack(this) != Item.isBlack(bord[ab[0]+2][ab[1]+2])))
                if(!isBlack(this) && ab[0]+2 <=4)
                    temp.add(Board.indexToPosConverter(ab[0]+2,ab[1]+2));
                else if(isBlack(this) && ab[0]+2 >=5)
                    temp.add(Board.indexToPosConverter(ab[0]+2,ab[1]+2));
        }
        if(inBounds(Board.indexToPosConverter(ab[0]+1,ab[1]-1)) && bord[ab[0]+1][ab[1]-1] == null){
            if(inBounds(Board.indexToPosConverter(ab[0]+2,ab[1]-2)) && (bord[ab[0]+2][ab[1]-2] == null || isBlack(this) !=  Item.isBlack(bord[ab[0]+2][ab[1]-2])))
                if(!isBlack(this) && ab[0]+2 <=4)
                    temp.add(Board.indexToPosConverter(ab[0]+2,ab[1]-2));
                else if(isBlack(this) && ab[0]+2 >=5)
                    temp.add(Board.indexToPosConverter(ab[0]+2,ab[1]-2));
        }
        
        if(inBounds(Board.indexToPosConverter(ab[0]-1,ab[1]+1)) && bord[ab[0]-1][ab[1]+1] == null){
            if(inBounds(Board.indexToPosConverter(ab[0]-2,ab[1]+2)) &&  (bord[ab[0]-2][ab[1]+2] == null || isBlack(this) != isBlack(bord[ab[0]-2][ab[1]+2])))
                if(!isBlack(this) && ab[0]-2 <=4)
                    temp.add(Board.indexToPosConverter(ab[0]-2,ab[1]+2));
                else if(isBlack(this) && ab[0]-2 >=5)
                    temp.add(Board.indexToPosConverter(ab[0]-2,ab[1]+2));
                
        }
        if(inBounds(Board.indexToPosConverter(ab[0]-1,ab[1]-1)) && bord[ab[0]-1][ab[1]-1] == null){
            if(inBounds(Board.indexToPosConverter(ab[0]-2,ab[1]-2)) && (bord[ab[0]-2][ab[1]-2] == null || isBlack(this) != isBlack(bord[ab[0]-2][ab[1]-2])))

                if(!isBlack(this) && ab[0]-2 <=4)
                    temp.add(Board.indexToPosConverter(ab[0]-2,ab[1]-2));
                else if(isBlack(this) && ab[0]-2 >=5)
                    temp.add(Board.indexToPosConverter(ab[0]-2,ab[1]-2));
        }
        this.setCanMoveTo(temp);
    }
    
}