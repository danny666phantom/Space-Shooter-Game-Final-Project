package spaceShooterGame;

/* Daniel Lorenzo and Ryan Duncan
 * CSIS 1410 - 013 
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SuperNova1 extends JPanel implements ActionListener, KeyListener {

	// Fields
	private Timer timer = new Timer(10, this);
	private int x = 0;
	private int ex1 = 1;
	public static String playerShip;
	private PlayerShip myShip = new PlayerShip(playerShip);
	private EnemyShip myEnemy = new EnemyShip();
	private EnemyShip myEnemy1 = new EnemyShip();
	private EnemyShip myEnemy2= new EnemyShip();
	private boolean collision = false;
	private boolean bulletFired = false;
	private static List<String> nameList = new ArrayList<String>();
	private static Music music;
	ArrayList<Bullet> myBulletList = new ArrayList<Bullet>();
	// Long separates the bullets.
	private long before = 0;

	// Methods
	/**
	 * This Constructor starts the timer and adds the KeyListener.
	 */
	public SuperNova1() {
		timer.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	private static final long serialVersionUID = -4458588367910426074L;

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setTitle("Space Shooter Game");
		jf.setSize(650, 720);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playerShip = JOptionPane.showInputDialog("Enter Fighter Pilot Name: ");
		if (playerShip == null) {
			System.exit(0);
		}
		while (playerShip == playerShip.substring(0, 0)) {
			playerShip = JOptionPane
					.showInputDialog("Please enter name or press Cancel");
			if (playerShip == null) {
				System.exit(0);
			}
		}
		nameList.add(playerShip);
		try (FileWriter writer = new FileWriter(
				"src/spaceShooterGame/myfile.csv", true)) {
			writer.write(playerShip + ",");
			writer.flush();
		} catch (Exception e) {
			System.out.println("Broken FileWriter!");
			e.printStackTrace();
		}
		SuperNova1 sn = new SuperNova1();
		jf.getContentPane().add(sn);
		sn.setBackground(Color.BLACK);
		jf.setVisible(true);
		music = new Music();

	}

	/**
	 * Calls the methods from PlayerShip and EnemyShip that define the look of
	 * the ship.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Collision Detector.
		if (myShip.getPlayerBounds().intersects(myEnemy.getEnemyBounds())) {
			if (!collision) {

				collision = true;
				playSound(2);
				// Reading player name from file.
				try {
					Scanner in = new Scanner(new File(
							"src/spaceShooterGame/myfile.csv"));
					while (in.hasNextLine()) {
						String[] line = in.nextLine().split(",");
						JOptionPane.showMessageDialog(null, "Pilot: "
								+ line[line.length - 1] + " Died");
					}
				} catch (FileNotFoundException e) {
					System.out.println("Broken Scanner!");
					e.printStackTrace();
				}

			}

			myShip.createPlayerShip2(g);
			repaint();
		} else {
			myShip.createPlayerShip(g);
			myEnemy.createEnemyShip(g);
			myEnemy1.createEnemyShip(g);
			myEnemy2.createEnemyShip(g);
			collision = false;
		}

		// Loops through and calls create player bullet which paints to the
		// screen.
		for (int i = 0; i < myBulletList.size(); i++) {
			myBulletList.get(i).createPlayerBullet(g);
		}

	}

	public int getX() {

		return x;
	}

	/**
	 * Will move the players ship as well as the enemies ships.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		checkPlayerPos();

		myShip.updateShipPos(x);

		checkEnemyYPos();
		checkEnemyXPos();
		myEnemy.updateEnemyXPos(ex1);
		myEnemy.enemyProceed();
		for (int i = 0; i < myBulletList.size(); i++) {
			// Removes bullets to check if its gone off the screen if so then
			// removes off the screen.
			if (myBulletList.get(i).getBulletYPos() < -5)
				myBulletList.remove(i);
			// Here moves the bullet but first makes sure there is actually a
			// bullet there.
			if (i < myBulletList.size())
				myBulletList.get(i).moveBullet(3);
		}

		// Puts space in between bullets.
		long now = System.nanoTime();
		if ((now - before) > 500_000_000) {
			if (bulletFired == true) {
				Bullet myBullet = new Bullet();
				myBullet.setBulletXPos(myShip.getShipPos());
				myBulletList.add(myBullet);
			}
			before = System.nanoTime();
		}

		repaint();
	}

	/**
	 * Will not let the players ship move out of bounds.
	 */
	private boolean checkPlayerPos() {
		if (myShip.getShipPos() < 12) {
			x = 0;
			myShip.updateShipPos(1);
			return true;
		}
		if (myShip.getShipPos() > 612) {
			x = 0;
			myShip.updateShipPos(-1);
			return true;
		}
		return false;
	}

	/**
	 * Causes the enemies to return to the top of the game screen.
	 */
	private boolean checkEnemyYPos() {
		if (myEnemy.getEnemyYPos() > 710) {
			myEnemy.resetEnemyYPos();
			return true;
		}

		return false;
	}

	/**
	 * Will not let the enemies ship move out of bounds top and bottom.
	 */
	private void checkEnemyXPos() {
		if (myEnemy.getEnemyXPos() > 596) {
			ex1 = -1;
		}
		if (myEnemy.getEnemyXPos() < 0) {
			ex1 = 1;
		}

	}

	/**
	 * listens for keys to be pressed.
	 */
	@Override
	public void keyPressed(KeyEvent e) // Tested with JUnit 1.
	{
		int c = e.getKeyCode();
		if (c == KeyEvent.VK_LEFT) {
			x = -2;

		} else if (c == KeyEvent.VK_RIGHT) {
			x = 2;
		}
		if (c == KeyEvent.VK_SPACE) {
			bulletFired = true;
		}

	}

	/**
	 * Listens for keys to be released and sets the x variable to zero to stop
	 * ship movement.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int c = e.getExtendedKeyCode();
		if (c == KeyEvent.VK_LEFT || c == KeyEvent.VK_RIGHT) {
			x = 0;
		}

		if (c == KeyEvent.VK_SPACE) {
			bulletFired = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public static void playSound(int i) {
		try {
			String file = "src/spaceShooterGame/";
			if (i == 2) {
				file = "src/spaceShooterGame/maverickgetshit.wav";
			}
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File(file).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

}
