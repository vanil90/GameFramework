package game.gfx;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

public class Sprite {
	private double rotation;

	private Image image;
	private double scale;

	public Sprite(Image image) {
		this.image = image;
		scale = 1;
	}

	public void draw(Graphics2D g, int x, int y) {
		AffineTransform transform = new AffineTransform();
		
		transform.setToIdentity();
		
		// Escalar de acordo com a escala pretendida
		transform.scale(scale, scale);
		// Aplicar a tranlacao para a posicao da imagem
		transform.translate(x, y);
		// Rodar de acordo com a rotacao pretendida
		transform.rotate(Math.toRadians(rotation), getWidth() / 2, getHeight() / 2);
		
		// Activar o antialiasing
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		g.drawImage(image, transform, null);
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public int getHeight() {
		return image.getHeight(null);
	}

	public double getRotation() {
		return rotation;
	}

	public int getWidth() {
		return image.getWidth(null);
	}

	public void setRotation(double rotation) {
		this.rotation = rotation % 360;
	}
}
