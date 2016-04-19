package spaceShooterGame;

/* Daniel Lorenzo and Ryan Duncan
 * CSIS 1410 - 013 
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyShip {

	// Fields.
	Random rand = new Random();
	int enemyYPos = -15;
	int enemyXPos = rand.nextInt(610);

	// Methods.
	public int getEnemyXPos() {
		return enemyXPos;
	}

	public void updateEnemyXPos(int x) {
		this.enemyXPos += x;
	}

	public int getEnemyYPos() {
		return enemyYPos;
	}

	// Sets enemy back to top of the screen at -15.
	public void resetEnemyYPos() {
		this.enemyYPos = -15;
	}

	public void enemyProceed() // Moves it down by 1 pixel. // Test with JUnit
								// 5.
	{
		enemyYPos += 1;
	}

	// Creates Enemy.
	public void createEnemyShip(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillOval(getEnemyXPos(), enemyYPos, 40, 15);
		g.setColor(Color.BLUE);
		g.fillOval(getEnemyXPos() + 12, enemyYPos - 7, 15, 10);
		g.setColor(Color.GREEN);
		g.fillOval(getEnemyXPos() + 17, enemyYPos - 3, 5, 5);
	}

	// Creates Enemy explosion graphic.
	public void createEnemyShip2(Graphics g) // THE ALIEN SHIP WILL TURN RED
												// WHEN HIT.
	{
		g.setColor(Color.RED);
		g.fillOval(getEnemyXPos(), enemyYPos, 40, 15);
		g.setColor(Color.BLUE);
		g.fillOval(getEnemyXPos() + 12, enemyYPos - 7, 15, 10);
		g.setColor(Color.GREEN);
		g.fillOval(getEnemyXPos() + 17, enemyYPos - 3, 5, 5);
	}

	// This sets the boundaries for the enemy ship for our collision detector.
	public Rectangle getEnemyBounds() // JUnit Tests 6.
	{
		return (new Rectangle(enemyXPos, enemyYPos, 40, 22));
	}
}
