//Author : Kemal Tarık Nehrozoğlu, written for a homework for the class BIL211 in TOBB ETU
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
//import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;



public class Game extends AbstractGame{
    //567. SATIRI DEĞİŞTİRDİN YÜKLE

    //NOTE TO SELF: REWRITE SAH'S WHEREMOVETO AND DETECT CHECKMATE AND PAT AND INSUFFICENT METARIAL
    // i did so :)
    public Game(String P1, String P2){
        this.red = new Player(P1);
        this.black = new Player(P2);
        this.board = new Board();
        this.turRed = true;
        this.hasEnded = false;
    }
    @Override
    void play(String from, String to) {
        // TODO Auto-generated method stub
        if(from.length()!=2 && to.length()!= 2){
            System.out.println("hatali hareket");
            return;
        }
        
        int fr[] = Board.posToIndexConverter(from);
        int tou[] = Board.posToIndexConverter(to);
        
        
        int[] redSahindex = Board.posToIndexConverter(board.items[0].getPosition());
        int[] siySahindex = Board.posToIndexConverter(board.items[16].getPosition());
       
        int redHamle = 0;
        int siyahHamle=0;
        Item timpItem = new Piyon("xx");
        if( !timpItem.inBounds(from)||!timpItem.inBounds(to) || board.board[fr[0]][fr[1]] == null){
            System.out.println("hatali hareket");
            
            return;
        }
        timpItem = null;
        if(turRed != Item.isBlack(board.board[fr[0]][fr[1]]) && !hasEnded){ // tur oynatılan tasin oyuncusunda mı
            Item fromItem = board.board[fr[0]][fr[1]];
            fromItem.move(to);
            Item toItem = board.board[tou[0]][tou[1]];
            if(toItem != null)
            toItem.remove();
            if(board.board[fr[0]][fr[1]].getPosition() == to) {
            board.board[tou[0]][tou[1]] = board.board[fr[0]][fr[1]];
            board.board[fr[0]][fr[1]] = null;
            
            }
            else{
                fromItem.setPosition(from);
                System.out.println("hatali hareket");
                if(toItem != null)
                toItem.setPosition(to);
                return;

            } 
            for (int i = redSahindex[0]; (i < siySahindex[0]) && redSahindex[1]==siySahindex[1] ; i++) {
                // the kings can't face eachother
                if(board.board[i][redSahindex[0]] != null){
                    break;
                }
                if(i == (siySahindex[0]-1) && board.board[i][redSahindex[0]] == null ){
                    //System.out.println("Uyari: Imkansiz Hamle! Başka bir hamle yapiniz.");
                    System.out.println("hatali hareket");
                    board.board[fr[0]][fr[1]] = board.board[tou[0]][tou[1]];
                    board.board[tou[0]][tou[1]] = toItem;
                    fromItem.setPosition(from);
                    if(toItem != null)
                    toItem.setPosition(to);
                    return;
                }
            }
            for (int i = 0 ; i < 32; i++) {
                board.items[i].whereMove(board.board);
                if(!turRed && i<16){ // pinli tas
                    if(board.items[i].getCanMoveTo().contains(board.items[16].getPosition())){
                        //System.out.println("Uyari: Imkansiz Hamle! Başka bir hamle yapiniz.");
                        System.out.println("hatali hareket");
                        board.board[fr[0]][fr[1]] = board.board[tou[0]][tou[1]];
                        board.board[tou[0]][tou[1]] = toItem;
                        fromItem.setPosition(from);
                        if(toItem != null)
                        toItem.setPosition(to);
                        for (int j = 0; j <=i; j++) {
                            board.items[j].whereMove(board.board);
                            
                        }
                        return;
                    }
                }else if(turRed && i>15){
                    if(board.items[i].getCanMoveTo().contains(board.items[0].getPosition())){
                        //System.out.println("Uyari: Imkansiz Hamle! Başka bir hamle yapiniz.");
                        System.out.println("hatali hareket");
                        board.board[fr[0]][fr[1]] = board.board[tou[0]][tou[1]];
                        board.board[tou[0]][tou[1]] = toItem;
                        fromItem.setPosition(from);
                        if(toItem != null)
                        toItem.setPosition(to);
                        for (int j = 0; j <=i; j++) {
                            board.items[j].whereMove(board.board);
                            
                        }
                        return;
                    }
                }
                
                // pat
                if(i<16){
                    redHamle += board.items[i].getCanMoveTo().size();
                }
                if(i == 16 && redHamle == 0){
                    //System.out.println(red.getName() + " Oyuncusu Pat olup oyunu kaybetmiştir");
                    System.out.println("PAT");
                    hasEnded = true;
                    break;
                }
                if(i>=16){
                    siyahHamle += board.items[i].getCanMoveTo().size();
                }
                if(i == 32-1 && siyahHamle ==0){
                    //System.out.println(black.getName() + " Oyuncusu Pat olup oyunu kaybetmiştir");
                    System.out.println("PAT");
                    hasEnded = true;
                    break;
                }
                

               
            }
            //repetition
            /* 
            if(board.isRepetition()){
                System.out.println("hatali hareket");
                board.board[fr[0]][fr[1]] = board.board[tou[0]][tou[1]];
                        board.board[tou[0]][tou[1]] = toItem;
                        fromItem.setPosition(from);
                        if(toItem != null)
                        toItem.setPosition(to);
                        return;
            }
            else 
                board.addBoard();
            
            //point distribution
            if(turRed && toItem != null){
                this.red.puanEkle(toItem.getTasPuan());
            }
            else if(!turRed && toItem != null){
                this.black.puanEkle(toItem.getTasPuan());
            }
            */
            //DRAW
            boolean sufficentMet =false;
            for (int i = 5; i < 16; i++) {
                if(board.items[i].getPosition() != "xx"){
                    sufficentMet = true;
                }
            }
            for (int i = 21; i < 32; i++) {
                if(board.items[i].getPosition() != "xx"){
                    sufficentMet = true;
                }
            }
            if(!sufficentMet){
                System.out.println("Insufficent Metarial");
                System.out.println("DRAW");
                hasEnded = true;
            }
            
            
            // sah mat
            if(!turRed){
                
                ArrayList<String> blockThyCheck = new ArrayList<String>();
                boolean isSah = false;
                boolean yetAnotherPiece = false;
                boolean canEscape=false;
                for (int i = 16; i < board.items.length; i++) {
                    if(board.items[i].getCanMoveTo().contains(board.items[0].getPosition())){//sah
                        isSah=true;
                        //System.out.println("Sah");
                        int[] threat = Board.posToIndexConverter( board.items[i].getPosition());
                        int[] hisHighness = Board.posToIndexConverter( board.items[0].getPosition());
                        ArrayList<String> temp = new ArrayList<String>();
                        switch(board.items[i].getPieceName()){
                            case "A":
                                if(!yetAnotherPiece){    
                                temp.add( Board.indexToPosConverter(hisHighness[0] + (threat[0] - hisHighness[0])/Math.abs((threat[0] - hisHighness[0])),hisHighness[1] + (threat[1] - hisHighness[1])/Math.abs(threat[1] - hisHighness[1])));
                                temp.add(board.items[i].getPosition());
                                yetAnotherPiece=true;
                                }
                                else{
                                    
                                    if(blockThyCheck.contains(Board.indexToPosConverter(hisHighness[0] + (threat[0] - hisHighness[0])/Math.abs((threat[0] - hisHighness[0])),hisHighness[1] + (threat[1] - hisHighness[1])/Math.abs(threat[1] - hisHighness[1])))){
                                        temp.add(Board.indexToPosConverter(hisHighness[0] + (threat[0] - hisHighness[0])/Math.abs((threat[0] - hisHighness[0])),hisHighness[1] + (threat[1] - hisHighness[1])/Math.abs(threat[1] - hisHighness[1])));
                                    }
                                    if(blockThyCheck.contains(board.items[i].getPosition())){
                                        temp.add(board.items[i].getPosition());
                                    }
                                }
                                
                                break;
                            case "K":
                                temp = new ArrayList<String>();
                                if(yetAnotherPiece){
                                    if(blockThyCheck.contains(board.items[i].getPosition()))
                                    temp.add(board.items[i].getPosition());
                                }else
                                temp.add(board.items[i].getPosition());
                                for (int j = Math.abs(hisHighness[0]-threat[0] + hisHighness[1] - threat[1]); j >=0; j--) {
                                    if(!yetAnotherPiece){
                                        if(threat[0]>hisHighness[0]){
                                            temp.add(Board.indexToPosConverter(hisHighness[0]+j, hisHighness[1]));
                                        }else if(threat[0]<hisHighness[0]){
                                            temp.add(Board.indexToPosConverter(threat[0]+j,threat[1]));
                                        }else if(threat[1]>hisHighness[1]){
                                            temp.add(Board.indexToPosConverter(hisHighness[0],hisHighness[1]+j));
                                        }else if(threat[1]<hisHighness[1]){
                                            temp.add(Board.indexToPosConverter(threat[0],threat[1]+j));
                                        }        
                                    }else{
                                        if(threat[0]>hisHighness[0] && blockThyCheck.contains(Board.indexToPosConverter(hisHighness[0]+j, hisHighness[1]))){
                                            temp.add(Board.indexToPosConverter(hisHighness[0]+j, hisHighness[1]));
                                        }else if(threat[0]<hisHighness[0] && blockThyCheck.contains(Board.indexToPosConverter(threat[0]+j, hisHighness[1]))){
                                            temp.add(Board.indexToPosConverter(threat[0]+j,threat[1]));
                                        }else if(threat[1]>hisHighness[1] && blockThyCheck.contains(Board.indexToPosConverter(hisHighness[0], hisHighness[1]+j))){
                                            temp.add(Board.indexToPosConverter(hisHighness[0],hisHighness[1]+j));
                                        }else if(threat[1]<hisHighness[1] && blockThyCheck.contains(Board.indexToPosConverter(hisHighness[0], threat[1]+j))){
                                            temp.add(Board.indexToPosConverter(threat[0],threat[1]+j));
                                        } 
                                    }
                                    
                                }
                                
                                
                                break;
                                case "T":
                                    temp = new ArrayList<String>();
                                    if(yetAnotherPiece){
                                        if(blockThyCheck.contains(board.items[i].getPosition()))
                                        temp.add(board.items[i].getPosition());
                                    }else
                                    temp.add(board.items[i].getPosition());
                                    for (int j = Math.abs(hisHighness[0]-threat[0] + hisHighness[1] - threat[1]); j >=0; j--) {
                                        if(!yetAnotherPiece){
                                            
                                            if(threat[0]>hisHighness[0] && board.board[hisHighness[0]+j][hisHighness[1]] == null){
                                                
                                                temp.add(Board.indexToPosConverter(hisHighness[0]+j, hisHighness[1]));
                                            }else if(threat[0]<hisHighness[0] && board.board[threat[0]+j][hisHighness[1]] == null){
                                                temp.add(Board.indexToPosConverter(threat[0]+j,threat[1]));
                                            }else if(threat[1]>hisHighness[1] && board.board[hisHighness[0]][hisHighness[1]+j] == null){
                                                temp.add(Board.indexToPosConverter(hisHighness[0],hisHighness[1]+j));
                                            }else if(threat[1]<hisHighness[1] && board.board[hisHighness[0]][threat[1]+j] == null){
                                                temp.add(Board.indexToPosConverter(threat[0],threat[1]+j));
                                            }        
                                        }else{
                                            if(threat[0]>hisHighness[0] && blockThyCheck.contains(Board.indexToPosConverter(hisHighness[0]+j, hisHighness[1])) && board.board[hisHighness[0]+j][hisHighness[1]] == null){
                                                temp.add(Board.indexToPosConverter(hisHighness[0]+j, hisHighness[1]));
                                            }else if(threat[0]<hisHighness[0] && blockThyCheck.contains(Board.indexToPosConverter(threat[0]+j, hisHighness[1])) && board.board[threat[0]+j][hisHighness[1]] == null){
                                                temp.add(Board.indexToPosConverter(threat[0]+j,threat[1]));
                                            }else if(threat[1]>hisHighness[1] && blockThyCheck.contains(Board.indexToPosConverter(hisHighness[0], hisHighness[1]+j))&& board.board[hisHighness[0]][hisHighness[1]+j] == null){
                                                temp.add(Board.indexToPosConverter(hisHighness[0],hisHighness[1]+j));
                                            }else if(threat[1]<hisHighness[1] && blockThyCheck.contains(Board.indexToPosConverter(hisHighness[0], threat[1]+j)) && board.board[hisHighness[0]][threat[1]+j] == null){
                                                temp.add(Board.indexToPosConverter(threat[0],threat[1]+j));
                                            } 
                                        }
                                        
                                    }
                                    
                                    
                                    break;
                                    case "P":
                                        if(yetAnotherPiece){
                                            temp = new ArrayList<String>();
                                        }
                                        else{
                                            temp.add(board.items[i].getPosition());
                                            yetAnotherPiece=true;
                                        }
                                    break;

                                
                        }
                        blockThyCheck.clear();
                        for (String string : temp) {
                            blockThyCheck.add(string);
                        }
                        
                    
                    }
                }
                if(isSah){
                    
                    for (String move : board.items[0].getCanMoveTo()) {
                        int[] toto = Board.posToIndexConverter(move);
                        //int[] frfr = siySahindex;
                        Item fifthdim = board.board[toto[0]][toto[1]];
                        board.board[toto[0]][toto[1]] = board.items[0];
                        board.board[redSahindex[0]][redSahindex[1]] = null;
                        
                        boolean canGo = true;
                        for (int j = toto[0]+1; j < board.board.length; j++) {
                            if(board.board[j][toto[1]] != null){
                                if(board.board[j][toto[1]] == board.items[16]){
                                    canGo = false; break;
                                }
                                else {
                                    //System.out.println("1");
                                    //System.out.println(board.board[j][toto[1]].getPieceName());
                                    break;
                                }
                            }
                            
                        }
                        for (int j = 16; j < 32 && canGo; j++) {
                            board.items[j].whereMove(board.board);
                            if(board.items[j].getCanMoveTo().contains(Board.indexToPosConverter(toto[0], toto[1]))){
                                canGo=false;
                            }
                                
                            
                        }
                        board.board[redSahindex[0]][redSahindex[1]] = board.items[0];
                        board.board[toto[0]][toto[1]]=fifthdim;
                        if(canGo){
                            
                            canEscape=true;
                            break;
                        }
                    }
                    for (int j = 1; j <16 && !canEscape; j++) {
                        board.items[j].whereMove(board.board);
                        for (String block : blockThyCheck) {
                            if(board.items[j].getCanMoveTo().contains(block)){
                                canEscape = true;
                                int[] ab = Board.posToIndexConverter( board.items[j].getPosition());
                                int[] blo = Board.posToIndexConverter(block);
                                Item temItem = board.board[blo[0]][blo[1]];
                                board.board[blo[0]][blo[1]] = board.board[ab[0]][ab[1]];
                                board.board[ab[0]][ab[1]] = null;
                                for (int i = 16; i <32 ; i++) {
                                    board.items[i].whereMove(board.board);
                                    if(board.items[i].getCanMoveTo().contains(Board.indexToPosConverter(redSahindex[0], redSahindex[1]))){
                                        canEscape = false;
                                    
                                    }
                                    
                                }
                                board.board[ab[0]][ab[1]] = board.board[blo[0]][blo[1]];
                                board.board[blo[0]][blo[1]] = temItem;
                                for (int i = 0; i <16 ; i++) {
                                    board.items[i].whereMove(board.board);
                                }
                                break;
                            }
                            
                        }
                        if(canEscape) break;
                    }
                    if(!canEscape){
                        System.out.print("ŞAH MAT! " + black.getName() + " oyunu kazandı. ");
                        System.out.print(red.getName() + "\'in puani: " + red.getPuan()+ ", ");
                        System.out.println(black.getName() + "\'in puani: " + black.getPuan());
                        hasEnded=true;

                    }
                    
            }
            }
            if(turRed){
                ArrayList<String> blockThyCheck = new ArrayList<String>();
                boolean yetAnotherPiece = false;
                boolean canEscape = false;
                boolean isSah = false;
                for (int i = 0; i < 16; i++) {
                    if(board.items[i].getCanMoveTo().contains(board.items[16].getPosition())){//sah
                        isSah = true;
                        
                        int[] threat = Board.posToIndexConverter( board.items[i].getPosition());
                        int[] hisHighness = Board.posToIndexConverter( board.items[16].getPosition());
                        ArrayList<String> temp= new ArrayList<String>();
                        switch(board.items[i].getPieceName()){
                            case "a":
                                if(!yetAnotherPiece){    
                                temp.add( Board.indexToPosConverter(hisHighness[0] + (threat[0] - hisHighness[0])/2,hisHighness[1] + (threat[1] - hisHighness[1])/2));
                                temp.add(board.items[i].getPosition());
                                yetAnotherPiece=true;
                                }
                                else{
                                    
                                    if(blockThyCheck.contains(Board.indexToPosConverter(hisHighness[0] + (threat[0] - hisHighness[0])/2,hisHighness[1] + (threat[1] - hisHighness[1])/2))){
                                        temp.add(Board.indexToPosConverter(hisHighness[0] + (threat[0] - hisHighness[0])/2,hisHighness[1] + (threat[1] - hisHighness[1])/2));
                                    }
                                    if(blockThyCheck.contains(board.items[i].getPosition())){
                                        temp.add(board.items[i].getPosition());
                                    }
                                }
                                
                                break;
                            case "k":
                                temp = new ArrayList<String>();
                                if(yetAnotherPiece){
                                    if(blockThyCheck.contains(board.items[i].getPosition()))
                                    temp.add(board.items[i].getPosition());
                                }else
                                temp.add(board.items[i].getPosition());
                                for (int j = Math.abs(hisHighness[0]-threat[0] + hisHighness[1] - threat[1]); j >=0; j--) {
                                    if(!yetAnotherPiece){
                                        if(threat[0]>hisHighness[0]){
                                            temp.add(Board.indexToPosConverter(hisHighness[0]+j, hisHighness[1]));
                                        }else if(threat[0]<hisHighness[0]){
                                            temp.add(Board.indexToPosConverter(threat[0]+j,threat[1]));
                                        }else if(threat[1]>hisHighness[1]){
                                            temp.add(Board.indexToPosConverter(hisHighness[0],hisHighness[1]+j));
                                        }else if(threat[1]<hisHighness[1]){
                                            temp.add(Board.indexToPosConverter(threat[0],threat[1]+j));
                                        }        
                                    }else{
                                        if(threat[0]>hisHighness[0] && blockThyCheck.contains(Board.indexToPosConverter(hisHighness[0]+j, hisHighness[1]))){
                                            temp.add(Board.indexToPosConverter(hisHighness[0]+j, hisHighness[1]));
                                        }else if(threat[0]<hisHighness[0] && blockThyCheck.contains(Board.indexToPosConverter(threat[0]+j, hisHighness[1]))){
                                            temp.add(Board.indexToPosConverter(threat[0]+j,threat[1]));
                                        }else if(threat[1]>hisHighness[1] && blockThyCheck.contains(Board.indexToPosConverter(hisHighness[0], hisHighness[1]+j))){
                                            temp.add(Board.indexToPosConverter(hisHighness[0],hisHighness[1]+j));
                                        }else if(threat[1]<hisHighness[1] && blockThyCheck.contains(Board.indexToPosConverter(hisHighness[0], threat[1]+j))){
                                            temp.add(Board.indexToPosConverter(threat[0],threat[1]+j));
                                        } 
                                    }
                                    
                                }
                                
                                
                                break;
                                case "t":
                                    temp = new ArrayList<String>();
                                    if(yetAnotherPiece){
                                        if(blockThyCheck.contains(board.items[i].getPosition()))
                                        temp.add(board.items[i].getPosition());
                                    }else
                                    temp.add(board.items[i].getPosition());
                                    for (int j = Math.abs(hisHighness[0]-threat[0] + hisHighness[1] - threat[1]); j >=0; j--) {
                                        if(!yetAnotherPiece){
                                            if(threat[0]>hisHighness[0] && board.board[hisHighness[0]+j][hisHighness[1]] == null){
                                                temp.add(Board.indexToPosConverter(hisHighness[0]+j, hisHighness[1]));
                                            }else if(threat[0]<hisHighness[0] && board.board[threat[0]+j][hisHighness[1]] == null){
                                                temp.add(Board.indexToPosConverter(threat[0]+j,threat[1]));
                                            }else if(threat[1]>hisHighness[1] && board.board[hisHighness[0]][hisHighness[1]+j] == null){
                                                temp.add(Board.indexToPosConverter(hisHighness[0],hisHighness[1]+j));
                                            }else if(threat[1]<hisHighness[1] && board.board[hisHighness[0]][threat[1]+j] == null){
                                                temp.add(Board.indexToPosConverter(threat[0],threat[1]+j));
                                            }        
                                        }else{
                                            if(threat[0]>hisHighness[0] && blockThyCheck.contains(Board.indexToPosConverter(hisHighness[0]+j, hisHighness[1])) && board.board[hisHighness[0]+j][hisHighness[1]] == null){
                                                temp.add(Board.indexToPosConverter(hisHighness[0]+j, hisHighness[1]));
                                            }else if(threat[0]<hisHighness[0] && blockThyCheck.contains(Board.indexToPosConverter(threat[0]+j, hisHighness[1])) && board.board[threat[0]+j][hisHighness[1]] == null){
                                                temp.add(Board.indexToPosConverter(threat[0]+j,threat[1]));
                                            }else if(threat[1]>hisHighness[1] && blockThyCheck.contains(Board.indexToPosConverter(hisHighness[0], hisHighness[1]+j))&& board.board[hisHighness[0]][hisHighness[1]+j] == null){
                                                temp.add(Board.indexToPosConverter(hisHighness[0],hisHighness[1]+j));
                                            }else if(threat[1]<hisHighness[1] && blockThyCheck.contains(Board.indexToPosConverter(hisHighness[0], threat[1]+j)) && board.board[hisHighness[0]][threat[1]+j] == null){
                                                temp.add(Board.indexToPosConverter(threat[0],threat[1]+j));
                                            } 
                                        }
                                        
                                    }
                                    
                                    
                                    break;
                                    case "p":
                                        if(yetAnotherPiece){
                                            
                                            temp = new ArrayList<String>();
                                        }
                                        else 
                                            temp.add(board.items[i].getPosition());
                                        break;
                                
                        }
                        blockThyCheck.clear();
                        for (String string : temp) {
                            blockThyCheck.add(string);
                        }
                  }
                }
                if(isSah){
                    for (String move : board.items[16].getCanMoveTo()) {
                        int[] toto = Board.posToIndexConverter(move);
                        //int[] frfr = siySahindex;
                        Item fifthdim = board.board[toto[0]][toto[1]];
                        board.board[toto[0]][toto[1]] = board.items[16];
                        board.board[siySahindex[0]][siySahindex[1]] = null;
                        boolean canGo = true;
                        for (int j = toto[0]-1; j >= 0; j--) {
                            if(board.board[j][toto[1]] != null){
                                if(board.board[j][toto[1]] == board.items[0]){
                                    canGo = false; break;
                                }
                                else    break;
                            }
                            
                        }
                        for (int j = 0; j < 16 && canGo; j++) {
                            board.items[j].whereMove(board.board);
                            if(board.items[j].getCanMoveTo().contains(Board.indexToPosConverter(toto[0], toto[1]))){
                                canGo=false;
                            }
                                
                            
                        }
                        board.board[siySahindex[0]][siySahindex[1]] = board.items[16];
                        board.board[toto[0]][toto[1]]=fifthdim;
                        if(canGo){
                            canEscape=true;
                            break;
                        }
                    }
                    for (int j = 17; j <32 && !canEscape; j++) {
                        board.items[j].whereMove(board.board);
                        for (String block : blockThyCheck) {
                            if(board.items[j].getCanMoveTo().contains(block)){
                                canEscape = true;
                                int[] ab = Board.posToIndexConverter( board.items[j].getPosition());
                                int[] blo = Board.posToIndexConverter(block);
                                Item temItem = board.board[blo[0]][blo[1]];
                                board.board[blo[0]][blo[1]] = board.board[ab[0]][ab[1]];
                                board.board[ab[0]][ab[1]] = null;
                                for (int i = 0; i <16 ; i++) {
                                    board.items[i].whereMove(board.board);
                                    if(board.items[i].getCanMoveTo().contains(Board.indexToPosConverter(siySahindex[0], siySahindex[1]))){
                                        canEscape = false;
                                    
                                    }
                                    
                                }
                                board.board[ab[0]][ab[1]] = board.board[blo[0]][blo[1]];
                                board.board[blo[0]][blo[1]] = temItem;
                                for (int i = 0; i <16 ; i++) {
                                    board.items[i].whereMove(board.board);
                                }
                                break;
                            }
                            
                        }
                        if(canEscape) break;
                    }
                    if(!canEscape){
                        System.out.print("ŞAH MAT! " + red.getName() + " oyunu kazandı. ");
                        System.out.println(red.getName() + "\'in puani: " + red.getPuan());
                        System.out.println(black.getName() + "\'in puani: " + black.getPuan());
                        hasEnded=true;

                    }
                }
            } turRed =! turRed ;
        }else System.out.println("hatali hareket");  //:(   // BURASINI YÜKLE BU YOK
        
    }
    public void save_binary(String adress){
        // saves game in binary with the given file name
        try{
        File theFile = new File(adress);
        if(theFile.exists())
            theFile.delete();
        theFile.createNewFile();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(adress));
        for (Item itemler : board.items) {
            int[]ab = Board.posToIndexConverter(itemler.getPosition());
            out.writeInt(ab[0]);
            out.writeInt(ab[1]);
            
        }
        out.writeBoolean(turRed);
        out.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void save_text(String adress){
        // saves in humanly readable form
        try {
            File theFile = new File(adress);
            if(theFile.exists())
                theFile.delete();
            theFile.createNewFile();
            PrintWriter pw = new PrintWriter(new FileOutputStream(adress));
            for (Item itemler : board.items) {
                pw.println(itemler.getPieceName() + ":" + itemler.getPosition());

            }
            if(turRed){
                pw.println("red");
            }
            else{
                pw.println("black");
            }
            pw.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }
    public void load_binary(String adress){
        board.board = new Item[10][9];
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(adress));
            for (Item  itemler : board.items) {
                int[] ab = new int[2];
                ab[0] = in.readInt();
                ab[1] = in.readInt();
                String pos = Board.indexToPosConverter(ab[0], ab[1]);
                itemler.setPosition(pos);
                if(itemler.inBounds(pos)){
                    board.board[ab[0]][ab[1]] = itemler;
                }
                

                
            }
            for (Item  itemler : board.items) {
                itemler.whereMove(board.board);
            }
            turRed=in.readBoolean();
            in.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }
    public void load_text(String adress){
        board.board = new Item[10][9];
        try {
            BufferedReader bf = new BufferedReader(new FileReader(adress));
            for (Item itemler : board.items) {
                String a = bf.readLine();
                String pos = a.substring(a.indexOf(":")+1);
                itemler.setPosition(pos);
                int[]ab = Board.posToIndexConverter(pos);
                board.board[ab[0]][ab[1]]=itemler;
            }
            for (Item  itemler : board.items) {
                itemler.whereMove(board.board);
            }
            String a = bf.readLine();
            turRed = a.equals("red");
            bf.close();
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
}
