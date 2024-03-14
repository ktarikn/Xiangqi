import java.util.ArrayList;
/**
 * At
 */
public class At extends Item{
    /*@Override
    public void move(String destination) {
        
    };*/
    public At(String position){
        super(position);
        if(position.charAt(0)=='a'){
            this.setPieceName("a");
        }else this.setPieceName("A");
        setTasPuan(4);
    }
    void whereMove(Item[][] bord){
        ArrayList<String> temp = new ArrayList<String>();
        int[] ab = Board.posToIndexConverter(this.getPosition());
        
        if(inBounds(Board.indexToPosConverter(ab[0],ab[1]+1)) && bord[ab[0]][ab[1]+1] == null){
            if(inBounds(Board.indexToPosConverter(ab[0]+1, ab[1]+2)) &&  (bord[ab[0]+1][ab[1]+2] == null || isBlack(bord[ab[0]+1][ab[1]+2]) != isBlack(this)) )
                temp.add(Board.indexToPosConverter(ab[0]+1, ab[1]+2));
            if(inBounds(Board.indexToPosConverter(ab[0]-1, ab[1]+2)) && (bord[ab[0]-1][ab[1]+2] == null || isBlack(bord[ab[0]-1][ab[1]+2]) != isBlack(this)) )
                temp.add(Board.indexToPosConverter(ab[0]-1, ab[1]+2));
        }
        if(inBounds(Board.indexToPosConverter(ab[0],ab[1]-1)) && bord[ab[0]][ab[1]-1] == null){
            if(inBounds(Board.indexToPosConverter(ab[0]+1, ab[1]-2)) && ( bord[ab[0]+1][ab[1]-2] == null || isBlack(bord[ab[0]+1][ab[1]-2]) != isBlack(this)) )
                temp.add(Board.indexToPosConverter(ab[0]+1, ab[1]-2));
            if(inBounds(Board.indexToPosConverter(ab[0]-1, ab[1]-2)) && (bord[ab[0]-1][ab[1]-2] == null || isBlack(bord[ab[0]-1][ab[1]-2]) != isBlack(this)) )
                temp.add(Board.indexToPosConverter(ab[0]-1, ab[1]-2));
        }
        
        if(inBounds(Board.indexToPosConverter(ab[0]+1,ab[1])) && bord[ab[0]+1][ab[1]]  == null){
            if(inBounds(Board.indexToPosConverter(ab[0]+2, ab[1]+1)) && ( bord[ab[0]+2][ab[1]+1] == null || isBlack(bord[ab[0]+2][ab[1]+1]) != isBlack(this))) 
                temp.add(Board.indexToPosConverter(ab[0]+2, ab[1]+1));
            if(inBounds(Board.indexToPosConverter(ab[0]+2, ab[1]-1)) && (bord[ab[0]+2][ab[1]-1] == null || isBlack(bord[ab[0]+2][ab[1]-1]) != isBlack(this))) 
                temp.add(Board.indexToPosConverter(ab[0]+2, ab[1]-1));
        }
        
        if(inBounds(Board.indexToPosConverter(ab[0]-1, ab[1])) && bord[ab[0]-1][ab[1]] == null){
            if(inBounds(Board.indexToPosConverter(ab[0]-2, ab[1]+1)) &&  (bord[ab[0]-2][ab[1]+1] == null || isBlack(bord[ab[0]-2][ab[1]+1]) != isBlack(this)) )
                temp.add(Board.indexToPosConverter(ab[0]-2, ab[1]+1));
            if(inBounds(Board.indexToPosConverter(ab[0]-2, ab[1]-1)) && (bord[ab[0]-2][ab[1]-1] == null || isBlack(bord[ab[0]-2][ab[1]-1]) != isBlack(this)) )
                temp.add(Board.indexToPosConverter(ab[0]-2, ab[1]-1));
        }
        this.setCanMoveTo(temp);

    }
    
}