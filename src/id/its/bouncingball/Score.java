package id.its.bouncingball;
import java.awt.*;

public class Score {
    int score = 0;

    public void addScore() {
        this.score = this.score + 1;
    }

    public void draw(Graphics g) {
		g.drawString("Score: " + this.score, 300, 50);
	}
}
