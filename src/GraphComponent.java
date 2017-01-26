import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class GraphComponent extends JComponent{
	ArrayList<MyVertex> vertices;
	ArrayList<MyEdge> edges;
	MyGraph<String> graph;
	MyVertex selected1;
	MyVertex selected2;
	MyEdge selectedEdge;
	boolean first = true;
	
	
	public GraphComponent(){
		vertices = new ArrayList<>();
		edges = new ArrayList<>();
		graph = new MyGraph(10);
		this.addMouseMotionListener(new GraphComponent.MyMouseMotionListener());
		this.addMouseListener(new GraphComponent.MyMouseListener());
	}
	
	public void addVertex(MyVertex a){
		vertices.add(a);
		graph.addVertex(a);
	}
	
	public void sampleAddEdge(MyEdge e){
		edges.add(e);
		graph.addEdge((MyVertex) e.v1, (MyVertex) e.v2);
	}
	
	public void addEdge(){
		if(selected1!=null && selected2 != null){
			MyEdge e = new MyEdge(selected1, selected2);
			edges.add(e);
			graph.addEdge((MyVertex) selected1, (MyVertex) selected2);
		}else{
			System.out.println(selected1.toString() +"//"+ selected2);
		}
		clearSelected();
		
	}
	
	public void clearSelected(){
		selected1 = null;
		selected2 = null;
		selectedEdge = null;
	}
	
	public void paintComponent(Graphics g){
		//System.out.println("did draw");
		Graphics2D g2 = (Graphics2D) g;
		for(MyVertex a : vertices){
		//	System.out.println("Did have vertex");
			a.draw(g2);
		}
		for(MyEdge e : edges){
		//	System.out.println("Did have edge");
			e.draw(g2);
		}
		
	}
	
	private void drawSelected(Graphics2D g2){
		if(selected1 != null){
			Rectangle2D rect = selected1.getBounds();
		}
	}
	
	
	public String printAdjacencyList(){
		return graph.printAdjacencyList();
	}
	
	
	//Listeners for mouse motion
	class MyMouseMotionListener implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			Point2D p = e.getPoint();
			for(MyVertex v : vertices){
				if(v.contains(p)){
					selected1 = v;
					break;
				}
			}
			double x = p.getX();
			double y = p.getY();
			
			selected1.translate((int)x-selected1.x,(int) y-selected1.y);
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

		class MyMouseListener implements MouseListener{

			@Override
			public void mouseClicked(MouseEvent e) {
				Point2D p = e.getPoint();
				boolean found = false;
				System.out.println("Clicked");
				for(MyVertex v : vertices){
					if(v.contains(p)){
						if(first){
							selected1 = v;
							first = false;
						}else{
							selected2 = v;
							first = true;
						}
					}
				}
				for(MyEdge ed : edges){
					if(ed.contains(p))
						selectedEdge = ed;
				}
				if(!found){
					clearSelected();
				}
				System.out.println("selected 1 " + selected1);
				System.out.println("selected 2 " + selected2);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
		}
		
		
	}
	

