import java.util.ArrayList;

public abstract class AbstractItem implements ItemInterface {
	
	private String position;  // tahtadaki konumu gosterir. Ornegin, a1
	private String pieceName; //holds the name of the piece
	private float tasPuan;
	public void setPieceName(String pieceName) {
		this.pieceName = pieceName;
	}
	public String getPieceName() {
		return pieceName;
	}
	public void setTasPuan(float tasPuan) {
		this.tasPuan = tasPuan;
	}
	public float getTasPuan() {
		return tasPuan;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPosition() {
		return position;
	}

}
