import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Renderer extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private int ancho=100;
	private int alto=20;
	public int x=225-(ancho/2),y=240-(alto/2);
	public int xb=100,yb=100;
	
	public Renderer() {
		//setBackground(Color.DARK_GRAY.darker());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		GUI.gui.repaint(g);
		
		
	}
	
	
}
