package be.wereldwijnen.entities;

import java.io.Serializable;

public class Mandje implements Serializable{
	private static final long serialVersionUID=1L;
	private Wijn wijn;
	private int aantal;
	public Mandje(Wijn wijn, int aantal) {
		// TODO Auto-generated constructor stub
		setWijn(wijn);
		setAantal(aantal);
	}
	public Wijn getWijn() {
		return wijn;
	}
	public void setWijn(Wijn wijn) {
		this.wijn = wijn;
	}
	public int getAantal() {
		return aantal;
	}
	public void setAantal(int aantal) {
		this.aantal = aantal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
