package interfaces;

import java.awt.Graphics2D;

public interface DrawableGraph {
	void draw(Graphics2D g2);
	void addVertex(DrawableVertex a);
	void addEdge(DrawableEdge e);
	void removeVertex(DrawableVertex a);
	void removeEdge(DrawableVertex b);
	
	
}
