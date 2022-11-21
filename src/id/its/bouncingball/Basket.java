package id.its.bouncingball;
import java.awt.*;

public class Basket {
    float x, y;  
	float radius; 
	private Color color; 

    public Basket(float x, float y, float radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
	}

    public void collide(Ball ball) {
        if(ball.x >= x - radius && ball.x <= x + radius && ball.y >= y - radius && ball.y <= y + radius) {
            this.color = Color.BLACK;
        }
	}
}
