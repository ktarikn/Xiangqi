import java.util.ArrayList;
public class Board extends AbstractBoard{

	String blackKingPos;
	String redKingPos;
	ArrayList<ArrayList<String>> previousMoves = new ArrayList<ArrayList<String>>();
	public Board(){
		board = new Item[10][9];
		items = new Item[32];
		items[0] = new Sah("a5");
		items[1] = new Vezir("a4");
		items[2] = new Vezir("a6");
		items[3] = new Fil("a3");
		items[4] = new Fil("a7");
		items[5] = new At("a2");
		items[6] = new At("a8");
		items[7] = new Kale("a1");
		items[8] = new Kale("a9");
		items[9] = new Top("c2");
		items[10] = new Top("c8");
		items[11] = new Piyon("d1");
		items[12] = new Piyon("d3");
		items[13] = new Piyon("d5");
		items[14] = new Piyon("d7");
		items[15] = new Piyon("d9");
		//siyah
		items[16] = new Sah("j5");
		items[17] = new Vezir("j4");
		items[18] = new Vezir("j6");
		items[19] = new Fil("j3");
		items[20] = new Fil("j7");
		items[21] = new At("j2");
		items[22] = new At("j8");
		items[23] = new Kale("j1");
		items[24] = new Kale("j9");
		items[25] = new Top("h2");
		items[26] = new Top("h8");
		items[27] = new Piyon("g1");
		items[28] = new Piyon("g3");
		items[29] = new Piyon("g5");
		items[30] = new Piyon("g7");
		items[31] = new Piyon("g9");

		board[0][4] = items[0];
		board[0][3] = items[1];
		board[0][5] = items[2];
		board[0][2] = items[3];
		board[0][6] = items[4];
		board[0][1] = items[5];
		board[0][7] = items[6];
		board[0][0] = items[7];
		board[0][8] = items[8];
		board[2][1] = items[9];
		board[2][7] = items[10];
		board[3][0] = items[11];
		board[3][2] = items[12];
		board[3][4] = items[13];
		board[3][6] = items[14];
		board[3][8] = items[15];

		board[9][4] = items[16];
		board[9][3] = items[17];
		board[9][5] = items[18];
		board[9][2] = items[19];
		board[9][6] = items[20];
		board[9][1] = items[21];
		board[9][7] = items[22];
		board[9][0] = items[23];
		board[9][8] = items[24];
		board[7][1] = items[25];
		board[7][7] = items[26];
		board[6][0] = items[27];
		board[6][2] = items[28];
		board[6][4] = items[29];
		board[6][6] = items[30];
		board[6][8] = items[31];
		for (int i = 0; i < items.length; i++) {
			items[i].whereMove(board);
		}
	}



	@Override
	public void print() {
		// TODO Auto-generated method stub
		for(int i = board.length-1; i>=0;i--){
			System.out.print(""+ (char)('a'+i)+ "\t");
			for(int j = 0; j <board[0].length; j++){
				if(board[i][j] != null && board[i][j].getPieceName().equals("S"))
				System.out.print("Ş");
				else if(board[i][j] != null && board[i][j].getPieceName().equals("s"))
				System.out.print("ş");
				else
				System.out.print(""+ (board[i][j] != null ? board[i][j].getPieceName() : "-"));
				if(j!=board[0].length-1)
				System.out.print("--");
			}
			System.out.println();
			if(i>0){
				if(i==2 || i == board.length-1){
					System.out.println(" \t|  |  |  |\\ | /|  |  |  |");
				}
				else if(i == 1 || i == board.length-2){
					System.out.println(" \t|  |  |  |/ | \\|  |  |  |");
				}
				else if(i == 5) 
					System.out.println(" \t|                       |");
				else 
					System.out.println(" \t|  |  |  |  |  |  |  |  |");
			}
		}
		System.out.println();
		System.out.println(" \t1--2--3--4--5--6--7--8--9");
		
	}
	/* 
	public void addBoard(){
		ArrayList<String> temp = new ArrayList<>();
		for (Item itemler : items) {
			temp.add(itemler.getPosition());
			
		}
		previousMoves.add(temp);
	}
	public boolean isRepetition(){
		boolean ischecking = false;
		int repTimes=0;
		for (ArrayList<String> arrays : previousMoves) {
			for (int i = 0; i < items.length; i++) {
				if(items[i].getPosition()!=arrays.get(i)){
					ischecking = false;
					break;
				}
				for (int j = 0; j < items.length; j++) {
					if(items[i].getCanMoveTo().contains(items[j].getPosition())){
						ischecking = true;
					}
				}
			}
			if(ischecking ) repTimes++;
		}
		return repTimes>4;
	}*/
	public static int[] posToIndexConverter(String pos){
		
		return new int[]{(int)(pos.charAt(0)-'a'), (int)(pos.charAt(1)-'1')};
		
	}
	public static String indexToPosConverter(int a , int b){
		return ""+(char)(a+'a')+(char)(b+1 +'0');
	}
}
