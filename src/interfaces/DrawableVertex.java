package interfaces;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public interface DrawableVertex extends MoveableShape{
	Point2D getConnectingPoint(Point2D point);
	Point2D getCenter();
	
	DrawableVertex copy();
	
}

