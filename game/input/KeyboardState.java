package game.input;

public class KeyboardState {

	private boolean[] keysUp;
	private boolean[] keysDown;

	public KeyboardState(boolean[] keysUp, boolean[] keysDown) {
		this.keysUp = keysUp;
		this.keysDown = keysDown;
	}

	public boolean isKeyDown(int keyCode) {
		return keysDown[keyCode];
	}

	public boolean isKeyUp(int keyCode) {
		return keysUp[keyCode];
	}
}
