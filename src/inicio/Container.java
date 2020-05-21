package inicio;

import javax.swing.JFrame;
import iniciojogo.Fase;

public class Container extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Container() {
		
		add(new Fase());
		setTitle("Meu TGI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024, 728);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);	

	}

	public static void main(String[] args) {
		new Container();

	}
}
