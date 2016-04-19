package spaceShooterGame;

/* Daniel Lorenzo and Ryan Duncan
 * CSIS 1410 - 013 
 */

import java.io.*;
import javax.sound.sampled.*;

public class Music {
	public Music() {

		// Creates background music file and loops.
		File BGMusic = new File("src/spaceShooterGame/dangerzone.wav");
		int counter = 0;
		try {
			while (counter <= 200000) {
				PlaySound(BGMusic);
				Thread.sleep(184000);
			}
		} catch (InterruptedException e) {
			System.out.println("Problem with music looping.");
		}
	}

	// Plays music.
	static void PlaySound(File Sound) throws InterruptedException {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
		} catch (Exception e) {
			System.out.println("Broken Music Class.");
		}

	}

}
