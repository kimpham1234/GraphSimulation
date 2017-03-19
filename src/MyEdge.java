import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import interfaces.DrawableEdge;
import interfaces.DrawableVertex;

public class MyEdge extends SelectedShape implements DrawableEdge{
	DrawableVertex v1;
	DrawableVertex v2;
	Line2D edge = new Line2D.Double();
	boolean selected;
	final int SIZE = 10;
	final int STROKE = 4;
	
	public MyEdge(DrawableVertex v1, DrawableVertex v2){
		this.v1 = v1;
		this.v2 = v2;
		selected = false;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		Point2D toConnect = v2.getCenter();
		Point2D p1 = v1.getConnectingPoint(toConnect);
		toConnect = v1.getCenter();
		Point2D p2 = v2.getConnectingPoint(toConnect);
		edge.setLine((int)p1.getX(),(int) p1.getY(),(int) p2.getX(), (int)p2.getY());
		g2.setStroke(new BasicStroke(STROKE));
		g2.draw(edge);
		
		if(selected)
			drawSelected(g2);
	}

	@Override
	public void translate(int dx, int dy) {
		
		
	}	
	
	
	@Override
	public boolean contains(Point2D p) {
		GeneralPath container = new GeneralPath();
		Point2D point1;
		Point2D point2;
		Point2D point3;
		Point2D point4;
		//horizontal line
		if(edge.getY1()==edge.getY2()){
			point1 = new Point2D.Double(edge.getX1(), edge.getY1()-SIZE);
			point2 = new Point2D.Double(edge.getX2(), edge.getY1()-SIZE);
			point3 = new Point2D.Double(edge.getX2(), edge.getY1()+SIZE);
			point4 = new Point2D.Double(edge.getX1(), edge.getY1()+SIZE);
		}else if(edge.getX1()==edge.getX2()){
			point1 = new Point2D.Double(edge.getX1()-SIZE, edge.getY1());
			point2 = new Point2D.Double(edge.getX1()+SIZE, edge.getY1());
			point3 = new Point2D.Double(edge.getX1()+SIZE, edge.getY2());
			point4 = new Point2D.Double(edge.getX1()-SIZE, edge.getY2());
		}else{
			point1 = new Point2D.Double(edge.getX1()-SIZE, edge.getY1());
			point2 = new Point2D.Double(edge.getX1()+SIZE, edge.getY1());
			point3 = new Point2D.Double(edge.getX2()+SIZE, edge.getY2());
			point4 = new Point2D.Double(edge.getX2()-SIZE, edge.getY2());
			
		}
		container.moveTo(point1.getX(), point1.getY());
		container.lineTo(point2.getX(), point2.getY());
		container.lineTo(point4.getX(), point4.getY());
		container.lineTo(point3.getX(), point3.getY());
		container.lineTo(point1.getX(), point1.getY());
		container.closePath();
		
		
		return container.contains(p);
		
		//return edge.getBounds().contains(p);
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
		return new Rectangle2D.Double(edge.getX1(), edge.getY1(),
				edge.getX2()-edge.getX1(),edge.getY2()-edge.getY1());
	}

	@Override
	public boolean isSelected() {
		return selected;
	}


}


