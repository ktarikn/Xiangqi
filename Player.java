
public class Player {
	
	private String name;
	float puan; // her taş yedikçe oyuncunun puanı taşın puanına göre artar.

	public Player(String name){
		this.name = name;
		puan = 0;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void puanEkle(float puan){
		this.puan += puan; 
	}
	public float getPuan() {
		return puan;
	}
	public void setPuan(float puan) {
		this.puan = puan;
	}
}
