package spaceShooterGame;

/* Daniel Lorenzo and Ryan Duncan
 * CSIS 1410 - 013 
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerShip {
	// Fields.
	private int shipPos = 300; // Tested with JUnit 2.
	private String playerName;

	// Constructor.
	public PlayerShip(String playerName) {
		this.playerName = playerName;
	}

	// Methods.
	public int getShipPos() // Tested with JUnit 2.
	{
		return shipPos;
	}

	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Updates the players position by having a positive or negative number
	 * passed to it.
	 */
	public void updateShipPos(int direction) // Tested with JUnit 2.
	{
		shipPos = shipPos + direction;
	}

	/**
	 * Paints the player ship on the JPanel.
	 */
	public void createPlayerShip(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(getShipPos(), 612, 10, 38);
		g.fillRect(getShipPos() + 10, 625, 5, 15);

		g.fillRect(getShipPos() - 20, 629, 50, 5);
		g.fillRect(getShipPos() - 30, 633, 68, 5);

		g.fillRect(getShipPos() - 5, 625, 5, 15);
		g.fillRect(getShipPos() + 15, 620, 7, 35);
		g.fillRect(getShipPos() - 12, 620, 7, 35);
		g.fillRect(getShipPos() + 1, 610, 8, 2);
		g.fillOval(getShipPos(), 600, 9, 50);
		g.setColor(Color.BLUE);
		g.fillOval(getShipPos() + 2, 612, 5, 7);

	}

	// Creates PlayerShip explosion.
	public void createPlayerShip2(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(getShipPos(), 612, 10, 38);
		g.fillRect(getShipPos() + 10, 625, 5, 15);
		g.fillRect(getShipPos() - 5, 625, 5, 15);
		g.fillRect(getShipPos() + 15, 620, 7, 35);
		g.fillRect(getShipPos() - 12, 620, 7, 35);
		g.fillRect(getShipPos() + 1, 610, 8, 2);
		g.fillOval(getShipPos(), 600, 9, 50);

		g.setColor(Color.ORANGE);
		g.drawRect(getShipPos(), 612 + 20, 10, 38);
		g.drawRect(getShipPos() + 20, 625, 5, 15);
		g.drawRect(getShipPos() - 15, 625, 5, 15);
		g.drawRect(getShipPos() + 25, 620, 7, 35);
		g.drawRect(getShipPos() - 22, 620, 7, 35);
		g.drawOval(getShipPos() - 4, 610, 18, 22);
		g.drawOval(getShipPos(), 600 - 10, 9, 50);

		g.drawRect(getShipPos() - 30, 629, 70, 5);
		g.drawRect(getShipPos() - 40, 633, 88, 5);

		g.setColor(Color.RED);
		g.drawOval(getShipPos() - 45, 580, 100, 100);
		g.setColor(Color.BLUE);
		g.fillOval(getShipPos() + 2, 612, 5, 7);
	}

	// This sets the boundaries for the player ship Imaginary Rectangle for our
	// collision detector.
	public Rectangle getPlayerBounds() // Test with JUnit 3.
	{
		return (new Rectangle(shipPos - 12, 612, 34, 58));
	}
}
