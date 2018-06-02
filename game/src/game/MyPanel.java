package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

// 소스를 입력하고 Ctrl+Shift+O를 눌러서 필요한 파일을 포함한다. 
class Ball{
	Color Color3 = new Color(150, 255, 56);
	Random randomValue = new Random();
	int x1 = randomValue.nextInt(151) + 50;
	int y1 = 20;
	int size = 30;
	int x1Speed = randomValue.nextInt(3) - 1;
	int y1Speed = 3;
	public void draw(Graphics g) {
		g.setColor(Color3);
		g.fillOval(x1, y1, size, size);
	}
	public void update() {
		x1 += x1Speed;
		y1 += y1Speed;
		if (y1+y1Speed >= 215) {
			x1 = randomValue.nextInt(151) + 50;
			y1 = 20;
	}
}
	
	public int getx1() {
		return x1;
	}
	public int gety1() {
		return y1;
	}
	public int getSize() {
		return size;
	}
}

class MyPanel extends JPanel{
	private Ball ball = new Ball();
	int x = 230;
	int y = 215;
	Color Color1 = new Color(252, 244, 222);
	Color Color2 = new Color(153, 102, 51);
	JLabel label = new JLabel();
	JLabel label1 = new JLabel();
	JLabel label2 = new JLabel();
	int score = 0;
	boolean game = true;

	public MyPanel() {
		class MyThread extends Thread {
			public void run() {
				for (int i = 1; i <= 30; i++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					label1.setText(i + "");
				}
				game = false;
			}
		}
		addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				switch (keycode){
				case KeyEvent.VK_LEFT:
					x -=10; break;
				case KeyEvent.VK_RIGHT:
					x +=10; break;
				}
				repaint();	
			}
			public void keyReleased(KeyEvent arg0) {		}
			public void keyTyped(KeyEvent arg0) {			}

		});
		this.requestFocus();
		setFocusable(true);
		setBackground(Color1);
		setLayout(new BorderLayout());
		Runnable task = () -> {
			while(true) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException ignore) {
				}
				ball.update();
				repaint();
				
				if(game == false)
					Thread.currentThread().stop();
			}
		};
		new Thread(task).start();
		
		setVisible(true);
		label.setText("점수" + score);
		label2.setText("시간");
		label1.setText("start");
		(new MyThread()).start();
		setLayout(null);
		add(label);
		add(label1);
		add(label2);
		label.setBounds(20, 5, 95, 30);
		label2.setBounds(20, 20, 95, 30);
		label1.setBounds(47, 20, 95, 30);
		
		Runnable task2 = () -> {
			while(true) {
				if(ball.gety1() > y-40 && x < ball.getx1() && ball.getx1() < x + 70)
				{
					label.setText("점수" + score++/11);
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException ignore) {
				}
			}
		};
		new Thread(task2).start();
		}
	
	public void paintComponent(Graphics g) { 
		super.paintComponent(g);
		draw(g);
		ball.draw(g);
		}

	private void draw(Graphics g) {
		g.setColor(Color2);
		g.fillRect(x, y, 70, 40);
	}
}