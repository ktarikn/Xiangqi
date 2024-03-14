import java.util.Scanner;
public class main2 {
    
    public static void main(String[] args) {
        Game mygam = new Game("A","B");
        
        String a;
        Scanner scan = new Scanner(System.in);
        /*mygam.play("a1","c1");
        mygam.play("h8","h5");
        mygam.getBoard().print();
        */
        mygam.load_text("txtsave");
        mygam.getBoard().print();
        a = scan.next();
        while(!a.equals("e")){
            if(a.length()<2) {
                a = scan.next();
                continue;
            }
            mygam.play(a.substring(0,2).toLowerCase(),a.substring(2).toLowerCase());
            System.out.println(a.substring(0,2).toLowerCase() + " " +a.substring(2).toLowerCase() );
            mygam.getBoard().print();
            a=scan.next();
            
            if(a.equals("savetxt")){
                mygam.save_text("txtsave");
                a=scan.next();
            }
            else if(a.equals("savebin")){
                mygam.save_binary("binsave");
                a=scan.next();
            }
        }
        
        
    }
}

