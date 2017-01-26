import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import interfaces.DrawableVertex;

enum COLOR{
	WHITE, GREY, BLACK;
}

public class MyVertex<T> implements DrawableVertex{
	static int R = 20;
	static int FONT_SIZE = 22;
	double d;
	double f;
	T label;
	COLOR color;
	MyVertex<T> parent;
	int index;
	int x;
	int y;
	Ellipse2D circle;
	
	public MyVertex(T label){
		this.label = label;
	}
	
	public MyVertex(T label, int x, int y){
		this.label = label;
		d = Double.POSITIVE_INFINITY;
		f = 0;
		color = COLOR.WHITE;
		parent = null;
		index = -1;
		this.x = x;
		this.y = y;
		circle = new Ellipse2D.Double();
	}
	
	public String toString(){
		return "["+label+"]";
	}

	@Override
	public void draw(Graphics2D g2) {
		//circle = new Ellipse2D.Double(x, y, 2*R, 2*R);
		circle = new Ellipse2D.Double(x-R, y-R, 2*R, 2*R);
		g2.setFont(new Font("San Seriff", Font.BOLD, 22));
		//g2.drawString(label.toString(), x+2*R/3, y+4*R/3);
		g2.drawString(label.toString(), x-R/3, y+R/3);
		g2.setStroke(new BasicStroke(2));
		g2.draw(circle);
	}

	@Override
	public Point2D getConnectingPoint(Point2D point) {
		//figure out position
		double x0 = circle.getCenterX();
	    double y0 = circle.getCenterY();
	    double x1 = point.getX();
	    double y1 = point.getY();
	  //  System.out.println("center of " +this.label +" "+ x0+","+y0);
	    
	    double newx = x0;
	    double newy = y0;
	    
	    double theta = Math.atan((x1-x0)/(y1-y0));
	    double dx = Math.abs(this.R*Math.sin(theta));
	    double dy = Math.abs(this.R*Math.cos(theta));
	    //cases 
	    //1st quadrant
	    if(x1 > x0 && y1 < y0){
	    //	System.out.println("case 1");
	    	newx += dx;
	    	newy -= dy;
	    }else if(x1 < x0 && y1 < y0){ //2nd
	    //	System.out.println("case 2");
	    	newx -= dx;
	    	newy -= dy;
	    }else if(x1 < x0 && y1 > y0){ //3rd
	    //	System.out.println("case 3");
	    	newx -= dx;
	    	newy += dy;
	    }else if(x1 > x0 && y1 > y0){	//4th
	    //	System.out.println("case 4");
	    	newx += dx;
	    	newy += dy;
	    }else{ //special cases
	    	if(y0==y1){
	    		if(x0 < x1)
	    			newx += R;
	    		else newx -= R;
	    	}else if(x0==x1){
	    		if(y0 < y1)
	    			newy += R;
	    		else newy -=R;
	    	}
	    }
	  //  System.out.println("dx "+dx+" dy "+dy);
	  //  System.out.println("new x "+newx+" newy "+newy);
	    return new Point2D.Double(newx, newy);
	}

	public Point2D getCenter(){
		return new Point2D.Double(circle.getCenterX(), circle.getCenterY());
	}
	

	@Override
	public void translate(int dx, int dy) {
		x += dx;
		y += dy;
	}

	@Override
	public DrawableVertex copy() {
		return new MyVertex(label, x, y);
	}

	@Override
	public boolean contains(Point2D p) {
		if(circle.contains(p))
			return true;
		else return false;
	}

	@Override
	public Rectangle2D getBounds() {
		return circle.getBounds2D();
	}
}
