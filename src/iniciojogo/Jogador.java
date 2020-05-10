package iniciojogo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Jogador {

	private int x, y;
	private int dx, dy;
	private Image imagem;
	private int altura, largura;
	private List <Tiro> tiros;
	private boolean isVisivel;

	public Jogador() {
		this.x= 100;
		this.y = 728/2;	
		isVisivel= true;

		tiros = new ArrayList<Tiro>();
	}

	public void load() {
		ImageIcon referencia = new ImageIcon("res\\jogador05.png");
		imagem = referencia.getImage();
		altura = imagem.getHeight(null);
		largura = imagem.getHeight(null);
	}

	public void update() {
		System.out.println("update");
		System.out.println("x era " + x ); 
		System.out.println("y era " + y );
		System.out.println("dx é " + dx);
		System.out.println("dy é " + dy);
		x += dx;
		y += dy;
		dy = 0;
		dx = 0;
	}

	public void tiroSimples() {
		this.tiros.add(new Tiro(x + (largura/2), y + (altura/2)));
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}
	

	public void keyPressed(KeyEvent tecla) {
		final int speed = 3;
		
		int codigo = tecla.getKeyCode();
		
		if (codigo == KeyEvent.VK_A) {
			tiroSimples();
		}
		
		if (codigo == KeyEvent.VK_NUMPAD8) {
			dy = -speed;
			System.out.println("^"); 
		}
		
		if (codigo == KeyEvent.VK_NUMPAD2) {
			dy = speed;
			System.out.println("_");
		}

		
		if (codigo == KeyEvent.VK_NUMPAD4) {
			dx = -speed;
			System.out.println("<");
		}

		
		if (codigo == KeyEvent.VK_NUMPAD6) {
			dx = speed;
			System.out.println(">");
		}
	}
	public void keyRelease(KeyEvent tecla) {
		dy = 0;
		dx = 0;
		
	}


	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

	public List<Tiro> getTiros() {
		return tiros;
	}

	

}