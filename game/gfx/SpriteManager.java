package game.gfx;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class SpriteManager {
	private Map<String, Sprite> sprites;
	private static final SpriteManager instance = new SpriteManager();

	private SpriteManager() {
		sprites = new HashMap<String, Sprite>();
	}

	public static SpriteManager getInstance() {
		return instance;
	}

	public Sprite getSprite(String ref) {
		Sprite s = sprites.get(ref);
		if (s != null)
			return s;

		BufferedImage sourceImage = null;

		try {
			URL url = getClass().getClassLoader().getResource(ref);

			if (url == null)
				throw new IOException("Could not find Image.");

			sourceImage = ImageIO.read(url);

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		GraphicsConfiguration gc = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		Image image = gc.createCompatibleImage(sourceImage.getWidth(),
				sourceImage.getHeight(), Transparency.BITMASK);

		image.getGraphics().drawImage(sourceImage, 0, 0, null);

		s = new Sprite(image);
		sprites.put(ref, s);

		return s;
	}
}
