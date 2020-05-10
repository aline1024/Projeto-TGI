package iniciojogo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import iniciojogo.Inimigo1;

public class Fase<jogador> extends JPanel implements ActionListener {
	private Image fundo;
	private Image nebulosa;
	private Jogador jogador;
	private Timer timer;
	private List<Inimigo1> inimigo1;
	private List<Estrelas> estrelas;

	private boolean emJogo;

	public Fase() {
		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon referencia = new ImageIcon("res\\fundo01.png");
		fundo = referencia.getImage();

		jogador = (new Jogador());
		jogador.load();

		addKeyListener(new TecladoAdapter());

		timer = new Timer(5, this);
		timer.start();

		inicializaInimigos();
		inicializaEstrelas();
		emJogo = true;

	}

	public void inicializaInimigos() {
		int coordenadas[] = new int[40];
		inimigo1 = new ArrayList<Inimigo1>();

		for (int i = 0; i < coordenadas.length; i++) {
			int x = (int) (Math.random() * 8000 + 1024);
			int y = (int) (Math.random() * 650 + 30);
			inimigo1.add(new Inimigo1(x, y));

		}
	}

	public void inicializaEstrelas() {
		int coordenadas[] = new int[50];
		estrelas = new ArrayList<Estrelas>();

		for (int i = 0; i < coordenadas.length; i++) {
			int x = (int) (Math.random() * 1024 + 0);
			int y = (int) (Math.random() * 768 + 0);
			estrelas.add(new Estrelas(x, y));
		}
	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		if (emJogo == true) {

			graficos.drawImage(fundo, 0, 0, null);
			for (int p = 0; p < estrelas.size(); p++) {
				Estrelas q = estrelas.get(p);
				q.load();
				graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
			}
			
			graficos.drawImage(jogador.getImagem(), jogador.getX(), jogador.getY(), this);
			List<Tiro> tiros = jogador.getTiros();
			for (int i = 0; i < tiros.size(); i++) {
				Tiro m = tiros.get(i);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}

			for (int o = 0; o < inimigo1.size(); o++) {
				Inimigo1 in = inimigo1.get(o);
				in.load();
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}
		}

		else {
			ImageIcon fimJogo = new ImageIcon("res\\fimdejogo1.png");
			graficos.drawImage(fimJogo.getImage(), 0, 0, null);

		}
		{

			g.dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jogador.update();
		for (int p = 0; p < estrelas.size(); p++) {
			Estrelas on = estrelas.get(p);
			if (on.isVisivel()) {
				on.update();
			} else {
				estrelas.remove(p);

			}

		}

		List<Tiro> tiros = jogador.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			if (m.isVisivel()) {
				m.update();

			} else {
				tiros.remove(i);
			}
		}

		for (int o = 0; o < inimigo1.size(); o++) {
			Inimigo1 in = inimigo1.get(o);
			if (in.isVisivel()) {
				in.update();
			} else {
				inimigo1.remove(o);

			}
			checarColisoes();
			repaint();

		}
	}

	public void checarColisoes() {
		Rectangle formaNave = jogador.getBounds();
		Rectangle formaInimigo1;
		Rectangle formatiro;

		for (int i = 0; i < inimigo1.size(); i++) {
			Inimigo1 tempInimigo1 = inimigo1.get(i);
			formaInimigo1 = tempInimigo1.getBounds();
			if (formaNave.intersects(formaInimigo1)) {
				jogador.setVisivel(false);
				tempInimigo1.setVisivel(false);
				emJogo = false;

			}

		}
		List<Tiro> tiros = jogador.getTiros();
		for (int j = 0; j < tiros.size(); j++) {
			Tiro tempTiro = tiros.get(j);
			formatiro = tempTiro.getBounds();
			for (int o = 0; o < inimigo1.size(); o++) {
				Inimigo1 tempInimigo1 = inimigo1.get(o);
				formaInimigo1 = tempInimigo1.getBounds();
				if (formatiro.intersects(formaInimigo1)) {
					tempInimigo1.setVisivel(false);
					tempTiro.setVisivel(false);
				}

			}

		}
	}

	public List<Inimigo1> getInimigo1() {
		return inimigo1;
	}

	public void setInimigo1(List<Inimigo1> inimigo1) {
		this.inimigo1 = inimigo1;
	}

	private class TecladoAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			jogador.keyPressed(e);

		}

		@Override
		public void keyReleased(KeyEvent e) {
			jogador.keyRelease(e);
		}

	}
}
