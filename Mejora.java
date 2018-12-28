import java.awt.Rectangle;

public class Mejora extends Rectangle{
	private int xm,ym,hm,wm,vy;
	private Rectangle rect;
	private int valor=200;
	
	public Mejora(int x,int y) {
		xm=x;
		ym=y;
		hm=30;
		wm=30;
		vy=2;
		rect=new Rectangle(xm,ym,hm,wm);
	}
	
	public int getMejoraX() {
		return this.xm;
	}
	public int getMejoraY() {
		return this.ym;
	}
	public int getMejoraH() {
		return this.hm;
	}
	public int getMejoraW() {
		return this.wm;
	}
	public int getMejoraV() {
		return this.vy;
	}
	public void setMejoraY(int y) {
		this.ym=y;
	}
	public void caer() {
		setMejoraY(ym+vy);
	}
	public void eliminar() {
		xm=-100;
		ym=-100;
	}
	public int getValor() {
		return valor;
	}

}
