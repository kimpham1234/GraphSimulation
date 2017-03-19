import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import interfaces.MoveableShape;

public abstract class SelectedShape implements MoveableShape{
	public void drawSelected(Graphics2D g2){
		Rectangle2D rect = this.getBounds();
		double x = rect.getX();
		double y = rect.getY();
		double w = rect.getWidth();
		double h = rect.getHeight();
		double size = 5;
		
		Rectangle2D rect1 = new Rectangle2D.Double(x,y,size, size);
		Rectangle2D rect2 = new Rectangle2D.Double(x+w, y, size, size);
		Rectangle2D rect3 = new Rectangle2D.Double(x,y+h,size, size);
		Rectangle2D rect4 = new Rectangle2D.Double(x+w,y+h,size, size);
		
		g2.setColor(Color.GREEN);
		
		g2.fill(rect1);
		g2.fill(rect2);
		g2.fill(rect3);
		g2.fill(rect4);
		
		g2.draw(rect1);
		g2.draw(rect3);
		
		g2.draw(rect2);
		g2.draw(rect4);
		
		g2.setColor(Color.BLACK);
		
	}
}
