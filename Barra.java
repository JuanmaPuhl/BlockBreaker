import java.awt.Rectangle;

public class Barra extends Rectangle{
	private static final long serialVersionUID = 1L;
	private Rectangle barra;
	private int xb=100,yb=400,heightb=15,widthb=70,vx=5;
	
	
	public Barra() {
		barra=new Rectangle(xb,yb,widthb,heightb);
	}
	
	public int getBarX() {
		return xb;
	}
	public int getBarY() {
		return yb;
	}

	public void setBarX(int x) {
		barra.x=xb;
		this.xb=x;
	}
	
	public void setBarY(int y) {
		barra.y=yb;
		this.yb=y;
	}
	
	public int getBarHeight() {
		return heightb;
	}
	
	public int getBarWidth() {
		return widthb;
	}
	
	
	
	public boolean chocaX(Ball b) {
		for(int i=b.getBallX(); i<=b.getBallX()+b.getBallWidth(); i++) {
			if(i>=xb+10 && i<=xb+widthb-10 && (b.getBallY()+b.getBallHeight())>=yb && b.getBallY()<=yb+2) {
				if(i>=xb+10+widthb/2-20 && i<=xb+widthb-30) {
					//Si pega en el medio
					b.cambiarDir(2);
					return true;
				}
				if(i>=xb+10 && i<=xb+widthb/2-10) {
					//Si pega arriba a la izquierda
					b.cambiarDir(0);
					return true;
				}
				if(i>=xb+widthb-26 && i<=xb+widthb-10) {
					//Si pega arriba a la derecha
					b.cambiarDir(1);
					return true;
				}
			}
		}
		return false;
	}

	public boolean chocaY(Ball b) {
		for(int i=b.getBallY(); i<=b.getBallY()+b.getBallHeight(); i++) {
			if(i>=yb+5 && i<=yb+heightb) {
				for(int j=b.getBallX(); j<=b.getBallX()+b.getBallWidth(); j++) {
				if(j>=xb && j<=xb+10) {
					b.cambiarDir(3);
					return true;
				}
				if(j<=xb+widthb && j>= xb+widthb-10) {
					b.cambiarDir(4);
					return true;
				}
			}
			}
		}
		return false;
	}
	

	public int getVX() {
		return vx;
	}
	public void mover() {
		xb+=vx;
	}
	public void cambiarDir() {
		vx=-vx;
	}
	public Rectangle getRectangle() {
		return barra;
	}
	
	public boolean agarrarMejora(Mejora m) {
		for(int j=m.getMejoraX(); j<=m.getMejoraX()+m.getMejoraW(); j++) {
			if((j>=xb && j<=(xb+widthb))) {
				for(int i=m.getMejoraY(); i<m.getMejoraY()+m.getMejoraH(); i++)
					if((i>=yb) && (i<=yb+heightb))  {
						return true;
					}	
			}
		}
		return false;
	}
	
}
