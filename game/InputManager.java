package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener {

	private final int MAXKEYS = 1024;
	private boolean[] keysDown;
	private boolean[] keysUp;

	public InputManager() {
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

	public boolean isKeyDown(int keyCode) {
		return keysDown[keyCode];
	}

	public boolean isKeyUp(int keyCode) {
		return keysUp[keyCode];
	}
}
