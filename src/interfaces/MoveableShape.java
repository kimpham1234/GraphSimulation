package interfaces;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public interface MoveableShape {
	void draw(Graphics2D g2);
	void translate(int dx, int dy);
	boolean contains(Point2D p);
	Rectangle2D getBounds();
}
