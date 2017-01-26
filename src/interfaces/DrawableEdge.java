package interfaces;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public interface DrawableEdge extends MoveableShape{
	void connect(DrawableVertex a, DrawableVertex b);
	Point2D getStartPoint();
	Point2D getEndPoint();
	
	
	DrawableEdge copy();
	
}
