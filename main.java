import java.util.Scanner;
public class main {
    
    public static void main(String[] args) {
        Game mygam = new Game("A","B");
        mygam.getBoard().print();
        String a;
        Scanner scan = new Scanner(System.in);
        /*mygam.play("a1","c1");
        mygam.play("h8","h5");
        mygam.getBoard().print();
        */
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
            a=""+a;
            System.out.println(a);
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
