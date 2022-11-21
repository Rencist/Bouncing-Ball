package id.its.bouncingball;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class BallPanel extends JPanel implements MouseListener, MouseMotionListener{
	private static final int REFRESH_RATE = 30; 
	private Ball ball; 
	private BallArea box; 
	private Basket basket;
	private int areaWidth;
	private int areaHeight;
	public BallPanel(int width, int height) {
		this.areaWidth = width; 
		this.areaHeight = height;
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
		
		Random rand = new Random();
		int radius = 30;
		int x = rand.nextInt(width - radius * 2 - 20) + radius + 10;
		int y = rand.nextInt(height - radius * 2 - 20) + radius + 10;
		int speed = 10;
		int angleInDegree = rand.nextInt(360);
		this.addMouseMotionListener(this);
        this.addMouseListener(this);
		
		ball = new Ball(x, y, radius, speed, angleInDegree, Color.BLUE);
		basket = new Basket(320, 240, 25, Color.GREEN);
		box = new BallArea(0, 0, width, height, Color.WHITE, Color.BLACK);
		
		//untuk mendapatkan ukuran area latar belakang jika frame diresize
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Component c = (Component)e.getSource();
				Dimension dim = c.getSize();
				areaWidth = dim.width;
				areaHeight = dim.height;
				box.set(0, 0, width, height);
			}
		});
		
		startThread();
	}
	
	public void startThread() {
		Thread gameThread = new Thread() {
		public void run() {
				while (true) {
					basket.collide(ball);
					ball.collide(box);
					repaint();
					try {
						Thread.sleep(1000 / REFRESH_RATE);
					} catch (InterruptedException ex) {}
				}
			}
		};
	
		gameThread.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		box.draw(g);
		ball.draw(g);
		basket.draw(g);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// if(canDrag){
		// 	ball.x = e.getX() - dragFromX;
		// 	ball.y = e.getY() - dragFromY;

		// 	ball.x = Math.max(ball.x,0);
		// 	ball.x = Math.min(ball.x,getWidth() - ball.radius);

		// 	ball.y = Math.max(ball.y,0);
		// 	ball.y = Math.min(ball.y,getHeight() - ball.radius);

		// 	this.repaint();
		// }
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		ball.speedX = (float)(10);
		ball.speedY = (float)(-10);
		this.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// int x = e.getX();
		// int y = e.getY();
		// Random rand = new Random();
		// if(x >= this.ball.x &&x <= (this.ball.x + this.ball.radius) && y >= this.ball.y && y <= (this.ball.y + this.ball.radius)) {
		// 	this.canDrag = true;
		// 	this.dragFromX = x - (int)this.ball.x;
		// 	this.dragFromY = y - (int)this.ball.y;
		// 	ball.speedX = 0;
		// 	ball.speedY = 0;
		// } else {
		// 	this.canDrag = false;
		// 	ball.speedX = (float)(10 * Math.cos(Math.toRadians(rand.nextInt(360))));
		// 	ball.speedY = (float)(-10 * (float)Math.sin(Math.toRadians(rand.nextInt(360))));
		// }
		// this.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
