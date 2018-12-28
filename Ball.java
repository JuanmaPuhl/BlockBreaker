import java.awt.Rectangle;

public class Ball extends Rectangle{
	private static final long serialVersionUID = 1L;
	private Rectangle ball;
	private int vxOriginal=3,vyOriginal=3;
	private int xb=5,yb=55,heightb=15,widthb=15,vx=0,vy=0;
	private boolean movVertical=true,movDiagIzq=false,movDiagDer=false;
	
	public Ball() {
		ball=new Rectangle(xb,yb,widthb,heightb);
	}
	
	public int getBallX() {
		return xb;
	}
	public int getBallY() {
		return yb;
	}
	
	public void setBallX(int x) {
		ball.x=xb;
		this.xb=x;
	}
	
	public void setBallY(int y) {
		ball.y=yb;
		this.yb=y;
	}
	
	public int getBallHeight() {
		return heightb;
	}
	
	public int getBallWidth() {
		return widthb;
	}
	public void setXDir(int i) {
		vx=i;
	}
	public Rectangle getRectangle() {
		return ball;
	}
	public void cambiarDir(int i) {
		
		if(i==4) {
			vy=-2;
			vx=5;
		}
		if(i==3) {
			vx=-5;
			vy=-2;
		}
		if(i==2) {
			vy=vyOriginal;
			vx=0;
		}
		if(i==1) {
			vy=vyOriginal;
			vx=vxOriginal;
		}
		if(i==0) {
			vy=vyOriginal;
			vx=-vxOriginal;
			
		}
	}
	public int getDir() {
		return vx;
	}
	public int getVDir() {
		return vy;
	}
	public void aumentarV() {
		if(vx>0)
			vx++;
		if(vx<0)
			vx--;
		
	}
	public void setVDir(int x) {
		vy=x;
	}

}
