package game;

import game.input.Keyboard;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public abstract class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private Dimension windowSize;
	private BufferStrategy strategy;
	private String title;

	private boolean running;
	private Thread thread;

	private long lastFrame;

	public Game(String title, int width, int height) {
		this.title = title;
		windowSize = new Dimension(width, height);

		frame = new JFrame(this.title);

		// frame.addKeyListener(Keyboard.getInstance());
		addKeyListener(Keyboard.getInstance());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setIgnoreRepaint(true);

		setPreferredSize(windowSize);
		setMaximumSize(windowSize);
		setMinimumSize(windowSize);
		setIgnoreRepaint(true);
		setBackground(Color.BLACK);

		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		createBufferStrategy(3);
		strategy = getBufferStrategy();

		requestFocus();
		thread = new Thread(this);
	}

	public void run() {

		System.out.println("Running...");
		init();
		lastFrame = System.currentTimeMillis();

		int frames = 0, elapsedTime = 0;

		while (running) {
			long currentTime = System.currentTimeMillis();
			long deltaTime = currentTime - lastFrame;
			lastFrame = currentTime;

			update(deltaTime);

			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

			g.clearRect(0, 0, getWidth(), getHeight());

			render(g);

			g.dispose();
			strategy.show();

			frames++;
			elapsedTime += deltaTime;

			if (elapsedTime > 1000) {
				elapsedTime = 0;
				frame.setTitle("" + frames);
				frames = 0;
			}

			try {
				// Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public synchronized void start() {
		if (running)
			return;

		running = true;
		System.out.println("Starting...");
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;

		running = false;
		System.out.println("Stoping...");
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(ERROR);
		}
		System.exit(0);
	}

	public abstract void init();

	public abstract void update(long deltaTime);

	public abstract void render(Graphics2D g);
}
