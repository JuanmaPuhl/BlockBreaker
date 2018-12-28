import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

public class GUI {
	private int vby=3;
	private JFrame frame;
	private Barra bar;
	private	Ball ball;
	private Bloque[] block;
	private Timer timer=null;
	private boolean gameOver=false, pausaDibujada=false, pausa=false, movingLeft=false,movingRight=false, won=false,start=true,menu=true;
	public static GUI gui;
	private int MAXBLOQUES=15,score=0,CANTPIEDRA=5;
	private Mejora[] mejora=new Mejora[MAXBLOQUES];
	private int contadorBloques=MAXBLOQUES-CANTPIEDRA,WIDTHGAME=500,HEIGHTGAME=500,HEIGHT=650;
	private ActionListener eventoTimer;
	private int tick=0;
	private int cantVidas=3;
	private String FUENTE="Monospaced";
	
	public static void main(String[] args) {
		gui=new GUI();
	}
	
	public GUI() {
		/**CREO OBJETOS UTILES*/
		Renderer renderer=new Renderer();
		bar=new Barra();
		ball=new Ball();
		block=new Bloque[MAXBLOQUES];
		
		/**COSAS BASICAS PARA JFRAME*/
		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(0, 0, WIDTHGAME, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(renderer, 0);
		/**FIN COSAS BASICAS*/
		
		
		frame.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
					movingRight=true;
					movingLeft=false;

				}
				else
				if(arg0.getKeyCode()==KeyEvent.VK_LEFT) {
						movingRight=false;
						movingLeft=true;
					
					
				}
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
				if(won || gameOver) 
					System.exit(0);
				else {
					if(!pausa) 
						pausa=true;
					else {
						pausa=false;
						timer=new Timer(5,eventoTimer);
						timer.start();
					}
				}
				if(arg0.getKeyCode()==KeyEvent.VK_SPACE) {
					if(start) {
						ball.setVDir(3);
						start=false;
					}
				}
				
			}
			public void keyReleased(KeyEvent arg0) {
				movingLeft=movingRight=false;
			}
			public void keyTyped(KeyEvent arg0) {
				
			}
		});
		if(timer!=null) {
			timer.stop();
		}
		eventoTimer=new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {			
				if(ball.getBallY()>=HEIGHTGAME && !gameOver) {
					perderVida();
				}
				if(start) {
					moverPelota();
				}
				
				if(movingLeft && !start) {
					if((bar.getBarX())>0) {
						bar.cambiarDir();
						bar.mover();
						bar.cambiarDir();
					}
				}
				if(movingRight && !start) {
					if((bar.getBarX()+bar.getBarWidth())<WIDTHGAME-5) 
						bar.mover();
					
				}
				for(int i=0; i<MAXBLOQUES; i++) {
					if(block[i]!=null) {
						if(block[i].chocaX(ball)) {
							if(block[i].hayMejora()) {
								mejora[i]=block[i].soltarMejora();
							}
							ball.setVDir(-ball.getVDir());
	
							ball.setBallY(ball.getBallY()+ball.getVDir());
							if(block[i].isDestroyable()) {
								contadorBloques--;
								block[i]=null;
							}
						}
						else
						if(block[i].chocaY(ball) ) {
							if(block[i].hayMejora()) {
								mejora[i]=block[i].soltarMejora();
							}
							ball.setXDir(-ball.getDir());
							ball.setBallY(ball.getBallY()+ball.getVDir());
							ball.setBallX(ball.getBallX()+ball.getDir());
							if(block[i].isDestroyable()) {
								contadorBloques--;
								block[i]=null;
							}
							
						}
					}
						if((mejora[i])!=null && bar.agarrarMejora(mejora[i])) {
							aumentarScore(mejora[i].getValor());
							mejora[i].eliminar();
							mejora[i]=null;
						}
					
				}			
				
				if(bar.chocaX(ball)) {
					ball.setVDir(-ball.getVDir());
					ball.setBallY(ball.getBallY()+ball.getVDir());
				}
				else
				if(bar.chocaY(ball)) {

					ball.setBallY(ball.getBallY()+ball.getVDir());
				}
				if(ball.getBallX()<=0) {
					ball.setXDir(-1*ball.getDir());
				}
				if(ball.getBallX()>=WIDTHGAME-8) {
					ball.setXDir(-1*ball.getDir());
					ball.setBallX(WIDTHGAME-8);
				}
				if(ball.getBallY()<=0) {
					ball.setVDir(-ball.getVDir());
					ball.setBallY(ball.getBallY()+5);
					ball.setBallY(ball.getBallY()+ball.getVDir());
				}
				ball.setBallY(ball.getBallY()+ball.getVDir());
				ball.setBallX(ball.getBallX()+ball.getDir());
				renderer.repaint();
			}
			
		};
			
		timer=new Timer(10,eventoTimer);
		
		if(!gameOver)
			timer.start();
		for(int i=0; i<5; i++) {
			block[i]=new Bloque();
			block[i].setBlockX(100+50*i+5*i);
			mejora[i]=null;
			block[i].cambiarTipo(3);
			block[i].cambiarColor();
		}			
		for(int i=5; i<10; i++) {
			block[i]=new Bloque();
			block[i].setBlockX(75+50*(i-5)+20*(i-5));
			block[i].setBlockY(block[0].getBlockY()+block[0].getBlockHeight()+5);
			mejora[i]=null;
			block[i].cambiarTipo(2);
			block[i].cambiarColor();
		}
		for(int i=10; i<15; i++) {
			block[i]=new Bloque();
			block[i].setBlockX(100+50*(i-10)+5*(i-10));
			block[i].setBlockY(block[5].getBlockY()+block[5].getBlockHeight()+5);
			mejora[i]=null;
			block[i].cambiarTipo(1);
			block[i].cambiarColor();
		}
		ball.setBallX(WIDTHGAME/2-5);
		ball.setBallY(bar.getBarY()-16);
		//ball.setBallY(55);
		//ball.setBallX(490);
		bar.setBarX(WIDTHGAME/2-35);
	
	}
	
	public void repaint(Graphics g) {
		if(!menu) {
			/**Dibujo el fondo*/
			dibujarFondo(g);
			
			/**Dibujo la barra*/
			dibujarBarra(g);
			
			/**Dibujo la pelota*/
			dibujarPelota(g);
			
			/**Dibujo el bloque*/
			dibujarBloques(g);
			
			/**Dibujo las mejoras*/
			dibujarMejora(g);
			
			/**Dibujo la interfaz*/
			dibujarInterfaz(g);
		}
		else {
			//dibujarMenu(g);
			menu=false;
		}
	
		
	}
	private void dibujarFondo(Graphics g) {
		g.drawImage(new ImageIcon(getClass().getResource(("/Resources/fondo1.png"))).getImage(), 0, 0,null);
	}
	
	private void dibujarPelota(Graphics g) {
		//g.drawImage(new ImageIcon(getClass().getResource(("/Resources/ball1.png"))).getImage(), ball.getBallX(),ball.getBallY(),ball.getBallHeight(),ball.getBallWidth(),null);
		g.drawImage(new ImageIcon(getClass().getResource(("/Resources/ball2.png"))).getImage(), ball.getBallX(),ball.getBallY(),ball.getBallHeight(),ball.getBallWidth(),null);
	}
	
	private void dibujarBloques(Graphics g) {
		for(int i=0; i<MAXBLOQUES; i++) {
			if(block[i]!=null)
				g.drawImage(new ImageIcon(getClass().getResource(("/Resources/"+block[i].getImagen()))).getImage(), block[i].getBlockX(),block[i].getBlockY(),null);
//			g.setColor(block[i].getColor());
//			g.fillRect(block[i].getBlockX(), block[i].getBlockY(), block[i].getBlockWidth(), block[i].getBlockHeight());
		}
	}
	
	private void dibujarInterfaz(Graphics g) {
		g.setColor(Color.decode("#2b2b28"));
		g.fillRect(0, HEIGHTGAME, WIDTHGAME, HEIGHT-HEIGHTGAME);
		if(gameOver) { 
			dibujarFondoOscuro(g);
			timer.stop();
			g.setColor(Color.WHITE);
			g.setFont(new Font(FUENTE,1,50));
			g.drawString("GAME OVER", 110, 250);
			g.setFont(new Font(FUENTE,1,20));
			g.drawString("Presione ENTER para salir", 100, 300);
		}
		if(contadorBloques==0) {
			dibujarFondoOscuro(g);
			timer.stop();
			g.setColor(Color.WHITE);
			g.setFont(new Font(FUENTE,1,50));
			g.drawString("¡VICTORIA!", 105, 250);
			won=true;
			g.setFont(new Font(FUENTE,1,20));
			g.drawString("Presione ENTER para salir", 100, 300);
		}
		if(pausa) {
			dibujarFondoOscuro(g);
			timer.stop();
			pausaDibujada=true;
			g.setColor(Color.WHITE);
			g.setFont(new Font(FUENTE,1,50));
			g.drawString("PAUSA", 165, 250);
			g.setFont(new Font(FUENTE,1,20));
			g.drawString("Presione ENTER para continuar", 80, 300);
			
		}
		else {
			if(pausaDibujada) 
				pausaDibujada=false;	
		}
		/**DIBUJO NIVEL*/
		g.setColor(Color.WHITE);
		g.setFont(new Font(FUENTE,1,20));
		g.drawString("Nivel 1", WIDTHGAME/2-40, HEIGHT-50);
		/**DIBUJO SCORE*/
		g.setFont(new Font(FUENTE,1,15));
		g.drawString("Score", WIDTHGAME-100, HEIGHTGAME+30);
		g.drawString(""+score, WIDTHGAME-100, HEIGHTGAME+50);
		
		/**DIBUJO VIDAS*/
		g.drawImage(new ImageIcon(getClass().getResource(("/Resources/vidas.png"))).getImage(), 10,HEIGHTGAME+20,null);
		g.setFont(new Font(FUENTE,1,20));
		g.drawString(" X "+cantVidas, 35, HEIGHTGAME+35);
	}
	
	private void dibujarBarra(Graphics g) {
		g.drawImage(new ImageIcon(getClass().getResource(("/Resources/bar.png"))).getImage(), bar.getBarX(),bar.getBarY(),null);
	}
	
	private void dibujarMejora(Graphics g) {
		for(int i=0; i<MAXBLOQUES; i++) {
			if(mejora[i]!=null) {
				g.drawImage(new ImageIcon(getClass().getResource(("/Resources/mejora.png"))).getImage(), mejora[i].getMejoraX(),mejora[i].getMejoraY(),null);
				if(mejora[i].getMejoraY()>=HEIGHTGAME) {
					mejora[i].eliminar();
					mejora[i]=null;
				}
				else
					mejora[i].caer();
			}
		}
	}
	
	public void aumentarScore(int valor) {
		score+=valor;
	}
	private void dibujarFondoOscuro(Graphics g) {
		int alpha = 127; // 50% transparent
		Color myColour = new Color(0, 0, 0, alpha);
		g.setColor(myColour);
		g.fillRect(0, 0, WIDTHGAME, HEIGHTGAME);
	}
	private void perderVida() {
		if(cantVidas>0) {
			cantVidas--;
			start=true;
			ball.setBallY(bar.getBarY()-16);
			ball.setBallX(bar.getBarX()+bar.getBarWidth()/2);
			ball.setVDir(0);
			ball.setXDir(0);
		}
		else {
			gameOver=true;
			start=false;
		}
	}
	private void moverPelota() {
		if(movingRight) {
			if(ball.getBallX()+ball.getBallWidth()/2<=bar.getBarX()+bar.getBarWidth()-10)
				ball.setBallX(ball.getBallX()+bar.getVX());
		}
		if(movingLeft)	{
			if(ball.getBallX()+ball.getBallWidth()/2>=bar.getBarX()+10) {
				bar.cambiarDir();
				ball.setBallX(ball.getBallX()+bar.getVX());
				bar.cambiarDir();
			}
		}
	}
	
	private void dibujarMenu(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0,0,WIDTHGAME,HEIGHT);
		
		g.setColor(Color.RED.darker());
		g.fillRect(50, 400, 100, 30);
		
		g.setColor(Color.WHITE);
		g.drawString("Nuevo Juego", 65, 420);
		
	}
	
}
