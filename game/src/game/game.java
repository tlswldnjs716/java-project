package game;

import javax.swing.JFrame;

public class game extends JFrame{
		public game() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			add(new MyPanel());
			setSize(500, 300);
			setTitle("���ޱ� ����");
			setVisible(true);
		}
		public static void main(String[] args) {
		game s = new game();
	}
}