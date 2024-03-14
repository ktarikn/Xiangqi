import java.util.ArrayList;

/**
 * Sah
 */
public class Sah extends Item{
    public Sah(String position){
        super(position);
        if(position.charAt(0)=='a'){
            this.setPieceName("s");
        }else this.setPieceName("S");
    }
    void whereMove( Item[][] bord){
        ArrayList<String> temp = new ArrayList<String>();
        int[] ab = Board.posToIndexConverter(this.getPosition());
        
        if(inBounds(Board.indexToPosConverter(ab[0], ab[1]+1))  && (bord[ab[0]][ab[1]+1] == null || isBlack(bord[ab[0]][ab[1]+1]) != isBlack(this))){
            temp.add(Board.indexToPosConverter(ab[0], ab[1]+1));
        }
        if(inBounds(Board.indexToPosConverter(ab[0], ab[1]-1))  && (bord[ab[0]][ab[1]-1] == null || isBlack(bord[ab[0]][ab[1]-1]) != isBlack(this))){
            temp.add(Board.indexToPosConverter(ab[0], ab[1]-1));
        }
        if(inBounds(Board.indexToPosConverter(ab[0]+1, ab[1]))  && (bord[ab[0]+1][ab[1]] == null || isBlack(bord[ab[0]+1][ab[1]]) != isBlack(this))){
            temp.add(Board.indexToPosConverter(ab[0]+1, ab[1]));
        }
        if(inBounds(Board.indexToPosConverter(ab[0]-1, ab[1]))  && (bord[ab[0]-1][ab[1]] == null || isBlack(bord[ab[0]-1][ab[1]]) != isBlack(this))){
            temp.add(Board.indexToPosConverter(ab[0]-1, ab[1]));
        }
        
        /*int[] pos = Board.posToIndexConverter(tem);
        if(isBlack(this)){
            if (inBounds(Board.indexToPosConverter(pos[0]+1, pos[1])) && (bord[pos[0]+1][pos[1]] == null || !isBlack(bord[pos[0]+1][pos[1]])) ){
                temp.add(Board.indexToPosConverter(pos[0]+1,pos[1]));
            }
            if(pos[0]-1 > bord.length - 3 && (bord[pos[0]-1][pos[1]] == null || !isBlack(bord[pos[0]-1][pos[1]])) ){
                temp.add(Board.indexToPosConverter(pos[0]+1,pos[1]));
                
            }
        }   
          
        }*/
        


        this.setCanMoveTo(temp);

            
    }
    
}