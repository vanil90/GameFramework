package game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	private final int MAXKEYS = 1024;
	private boolean[] keysDown;
	private boolean[] keysUp;
	private static Keyboard instance = new Keyboard();

	public static Keyboard getInstance() {
		return instance;
	}

	public static KeyboardState getState() {
		return new KeyboardState(instance.getKeysUp(), instance.getKeysDown());
	}

	public boolean[] getKeysUp() {
		return keysUp;
	}

	public boolean[] getKeysDown() {
		return keysDown;
	}

	private Keyboard() {
		keysDown = new boolean[MAXKEYS];
		keysUp = new boolean[MAXKEYS];

		for (int i = 0; i < MAXKEYS; i++) {
			keysDown[i] = false;
			keysUp[i] = true;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keysDown[e.getKeyCode()] = true;
		keysUp[e.getKeyCode()] = false;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keysDown[e.getKeyCode()] = false;
		keysUp[e.getKeyCode()] = true;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
