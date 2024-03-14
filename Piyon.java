import java.util.ArrayList;
/**
 * Piyon
 */
public class Piyon extends Item{

    public Piyon(String position){
        super(position);
        if(position.charAt(0)=='d'){
            this.setPieceName("p");
        }else this.setPieceName("P");
        setTasPuan(1);
    }
    void whereMove(Item[][] bord){
        ArrayList<String> temp = new ArrayList<String>();
        int[] ab = Board.posToIndexConverter(this.getPosition());
        if(isBlack(this)){
            if(inBounds(Board.indexToPosConverter(ab[0]-1, ab[1])) && (bord[ab[0]-1][ab[1]] == null || !isBlack(bord[ab[0]-1][ab[1]])) ){
                
                temp.add(Board.indexToPosConverter(ab[0]-1, ab[1]));
            }
            if(inBounds(Board.indexToPosConverter(ab[0], ab[1]+1)) && (ab[0]<=4 && (bord[ab[0]][ab[1]+1] == null || !isBlack(bord[ab[0]][ab[1]+1] ))) ){
                temp.add(Board.indexToPosConverter(ab[0], ab[1]+1));
            }
            
            if(inBounds(Board.indexToPosConverter(ab[0], ab[1]-1)) && ab[0]<=4 && (bord[ab[0]][ab[1]-1] == null || !isBlack(bord[ab[0]][ab[1]-1] ) )){
                temp.add(Board.indexToPosConverter(ab[0], ab[1]-1));
            }
        }
        if(!isBlack(this)){
            if(inBounds(Board.indexToPosConverter(ab[0]+1, ab[1])) && (bord[ab[0]+1][ab[1]] == null || isBlack(bord[ab[0]+1][ab[1]])) ){
                temp.add(Board.indexToPosConverter(ab[0]+1, ab[1]));
            }
            if(inBounds(Board.indexToPosConverter(ab[0], ab[1]+1)) && ab[0]>=5 && (bord[ab[0]][ab[1]+1] == null || isBlack(bord[ab[0]][ab[1]+1] )) ){
                temp.add(Board.indexToPosConverter(ab[0], ab[1]+1));
            }
            if(inBounds(Board.indexToPosConverter(ab[0], ab[1]-1)) && ab[0]>=5 && (bord[ab[0]][ab[1]-1] == null || isBlack(bord[ab[0]][ab[1]-1] )) ){
                temp.add(Board.indexToPosConverter(ab[0], ab[1]-1));
            }
        }
        this.setCanMoveTo(temp);
    }
    
}