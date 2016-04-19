package spaceShooterGame;

/* Daniel Lorenzo and Ryan Duncan
 * CSIS 1410 - 013 
 */

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {

	// Fields.
	private int bulletYPos = 596;
	private int bulletXPos;

	// Methods.
	public int getBulletYPos() {
		return bulletYPos;
	}

	public int getBulletXPos() {
		return bulletXPos;
	}

	// Puts space in between bullets.
	public void setBulletXPos(int bulletXPos) {
		this.bulletXPos = bulletXPos + 4;
	}

	// Moves bullet.
	public void moveBullet(int y) {
		bulletYPos -= y;
	}

	// Creates bullet.
	public void createPlayerBullet(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(bulletXPos, bulletYPos, 3, 5);
	}
}
