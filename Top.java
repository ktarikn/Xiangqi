import java.util.ArrayList;
/**
 * Top
 */
public class Top extends Item{

    public Top(String position){
        super(position);
        if(position.charAt(0)=='c'){
            this.setPieceName("t");
        }else this.setPieceName("T");
        setTasPuan(4.5f);
    }
    void whereMove( Item[][] bord){
        boolean someThingInTheWay = false;//hmmmmmm mmmmmm
        ArrayList<String> temp = new ArrayList<String>();
        int[] ab = Board.posToIndexConverter(this.getPosition());
        for(int i = ab[0]+1; i<bord.length;i++){
            if(!inBounds(Board.indexToPosConverter(i,ab[1])))
                break;
            if(bord[i][ab[1]]== null){
                if(!someThingInTheWay)
                temp.add(Board.indexToPosConverter(i,ab[1]));
            }
            else if(!someThingInTheWay){
                someThingInTheWay= true;
                continue;
            }
            else if(bord[i][ab[1]]!= null && isBlack(this) != isBlack(bord[i][ab[1]]) ){
                temp.add(Board.indexToPosConverter(i,ab[1]));
                break;
            }else break;
            
        }
        someThingInTheWay = false;
        for(int i = ab[0]-1; i>=0;i--){
            if(!inBounds(Board.indexToPosConverter(i,ab[1])))
                break;
            if(bord[i][ab[1]]== null){
                if(!someThingInTheWay)
                temp.add(Board.indexToPosConverter(i,ab[1]));
            }
            else if(!someThingInTheWay){
                someThingInTheWay= true;
                continue;
            }
            else if(isBlack(this) != isBlack(bord[i][ab[1]]) ){
                temp.add(Board.indexToPosConverter(i,ab[1]));
                break;
            }else break;
            
        }
        someThingInTheWay = false;
        for(int i = ab[1]-1; i>=0;i--){
            if(!inBounds(Board.indexToPosConverter(ab[0],i)))
                break;
            if(bord[ab[0]][i]== null ){
                if(!someThingInTheWay)
                temp.add(Board.indexToPosConverter(ab[0],i));
            }
            else if(!someThingInTheWay){
                someThingInTheWay= true;
                continue;
            }
            else if(isBlack(this) != isBlack(bord[ab[0]][i]) ){
                temp.add(Board.indexToPosConverter(ab[0],i));
                break;
            }else break;
            
        }
        someThingInTheWay = false;
        for(int i = ab[1]+1; i<bord[0].length;i++){
            if(!inBounds(Board.indexToPosConverter(ab[0],i)))
                break;
            if(bord[ab[0]][i]== null){
                if(!someThingInTheWay)
                temp.add(Board.indexToPosConverter(ab[0],i));
            }
            else if(!someThingInTheWay){
                someThingInTheWay= true;
                continue;
            }
            else if(isBlack(this) != isBlack(bord[ab[0]][i]) ){
                temp.add(Board.indexToPosConverter(ab[0],i));
                break;
            }else break;
            
        }
        this.setCanMoveTo(temp);

    }
}