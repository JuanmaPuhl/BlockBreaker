import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

public class Bloque extends Rectangle {
	private static final long serialVersionUID = 1L;
	private Rectangle bloque;
	private int xb=100,yb=50,heightb=20,widthb=50;
	private int tipo;
	private int valor=100;
	private boolean hayMejora=false;
	private int xm,ym;
	private boolean destroyable;
	private Color color;
	private String imagen;
	
	public Bloque() {
		bloque=new Rectangle(xb,yb,widthb,heightb);
	}
	
	public int getBlockX() {
		return xb;
	}
	public int getBlockY() {
		return yb;
	}

	public void setBlockX(int x) {
		bloque.x=xb;
		this.xb=x;
		xm=x;
	}
	
	public void setBlockY(int y) {
		bloque.y=yb;
		this.yb=y;
		ym=y;
	}
	
	public int getBlockHeight() {
		return heightb;
	}
	
	public int getBlockWidth() {
		return widthb;
	}
		
	public boolean chocaY(Ball b) {
		int ym=b.getBallY()+b.getBallHeight()/2;
		int xm=b.getBallX()+b.getBallWidth();
		if(ym>=yb-1 && ym<=yb+heightb+1 && (xm>=xb-1 && xm<=(xb+widthb+1))) {
			if(destroyable) {
				eliminar();
				GUI.gui.aumentarScore(valor);
			}
			return true;
		}
		if(ym>=yb-1 && ym<=yb+heightb+1 && b.getBallX()>=xb-1 && b.getBallX()<=(xb+widthb)+1) {
			if(destroyable) {
				eliminar();
				GUI.gui.aumentarScore(valor);
			}
			return true;
		}
		return false;
	}
	
	public boolean chocaX(Ball b) {
		for(int xm=b.getBallX(); xm<=b.getBallX()+b.getBallWidth(); xm++) {
		int ym=b.getBallY()+b.getBallHeight();
		if(xm> xb+3 && xm<xb+widthb-3 && ym>=yb && ym<=yb+heightb) {
			if(destroyable) {
				eliminar();
				GUI.gui.aumentarScore(valor);
			}
			return true;
		}
		if(xm> xb+3 && xm<xb+widthb-3 && b.getBallY()<=yb+heightb && b.getBallY()>=yb) {
			if(destroyable) {
				eliminar();
				GUI.gui.aumentarScore(valor);
			}
			return true;
		}
		}
		return false;
	}
	public void setSizeBloque(int h,int w) {
		heightb=h;
		widthb=w;
	}
	public void eliminar() {
		setSizeBloque(0,0);
		int num =(int)(Math.random()*3);
		if(num==2) {
			hayMejora=true;
		}
		else {
			hayMejora=false;
		}
	}
	
	public Rectangle getRectangle() {
		return bloque;
	}
	
	private void tablaValores() {
		if(tipo==1) 
			valor=100;
		if(tipo==2)
			valor=200;
		if(tipo==3)
			valor=400;
	}
	public Mejora soltarMejora() {
		Mejora mej=new Mejora(xm,ym+heightb);
		hayMejora=false;
		return mej;
	}
	public boolean hayMejora() {
		return hayMejora;
	}
	
	public boolean isDestroyable() {
		return destroyable;
	}
	public void cambiarTipo(int i) {
		tipo=i;
		tablaValores();
		if(tipo==3)
			destroyable=false;
		else
			destroyable=true;
	}
	public void cambiarColor() {
		if(tipo==1)
			color=Color.CYAN.darker();
		if(tipo==2)
			color=Color.RED.darker();
		if(tipo==3)
			color=Color.GRAY;
	}
	public Color getColor() {
		return color;
	}
	public String getImagen() {
		if(tipo==1)
			return "bloque2.png";
		if(tipo==3)
			return "bloqueMetal.png";
		else
			return "bloque2.png";
	}
	
}
