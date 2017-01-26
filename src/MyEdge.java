import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import interfaces.DrawableEdge;
import interfaces.DrawableVertex;

public class MyEdge implements DrawableEdge{
	DrawableVertex v1;
	DrawableVertex v2;
	Line2D edge = new Line2D.Double();
	
	public MyEdge(DrawableVertex v1, DrawableVertex v2){
		this.v1 = v1;
		this.v2 = v2;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		Point2D toConnect = v2.getCenter();
		Point2D p1 = v1.getConnectingPoint(toConnect);
		toConnect = v1.getCenter();
		Point2D p2 = v2.getConnectingPoint(toConnect);
		edge.setLine((int)p1.getX(),(int) p1.getY(),(int) p2.getX(), (int)p2.getY());
		g2.draw(edge);
	}

	@Override
	public void translate(int dx, int dy) {
		
		
	}

	@Override
	public boolean contains(Point2D p) {
		if(edge.contains(p))
			return true;
		else return false;
	}

	@Override
	public void connect(DrawableVertex a, DrawableVertex b) {
		
		
	}

	@Override
	public Point2D getStartPoint() {
		return edge.getP1();
	}

	@Override
	public Point2D getEndPoint() {
		return edge.getP2();
	}

	@Override
	public DrawableEdge copy() {
		return null;
	}

	@Override
	public Rectangle2D getBounds() {
		return edge.getBounds2D();
	}

}
