import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JComponent;

public class GraphComponent extends JComponent{
	ArrayList<MyVertex> vertices;
	ArrayList<MyEdge> edges;
	MyGraph<String> graph;
	Queue<MyVertex> selected;
	MyVertex dragged;
	MyEdge selectedEdge;
	final int MAX_SIZE = 100;
	
	public GraphComponent(){
		vertices = new ArrayList<>();
		edges = new ArrayList<>();
		graph = new MyGraph(MAX_SIZE);
		selected = new LinkedList<>();
		this.addMouseMotionListener(new GraphComponent.MyMouseMotionListener());
		this.addMouseListener(new GraphComponent.MyMouseListener());
	}
	
	public void addVertex(MyVertex a){
		vertices.add(a);
		graph.addVertex(a);
	}
	
	public void delete(){
		while(!selected.isEmpty()){
			MyVertex v = selected.poll();
			vertices.remove(v);
			graph.removeVertex(v);
		}
		
		if(selectedEdge!=null && selectedEdge.isSelected()){
			edges.remove(selectedEdge);
			graph.removeEdge((MyVertex) selectedEdge.v1, (MyVertex) selectedEdge.v2);
		}
	}
	
	public void runDFS(){
		graph.dfs();
	}	
	
	public void runBFS(){
		MyVertex s = selected.peek();
		graph.bfs(s);
	}
	
	public String getPath(){
		if(!selected.isEmpty()){
			MyVertex s = selected.poll();
			s.isSelected = false;
			return graph.getPath(s);
		}else{
			return "nothing in queue";
		}
		
	}
	
	public boolean sourceSelected(){
		if(!selected.isEmpty())
			return true;
	    return false;
	}
	
	public void sampleAddEdge(MyEdge e){
		edges.add(e);
		graph.addEdge((MyVertex) e.v1, (MyVertex) e.v2);
	}
	
	public void addEdge(){
		if(selected.size() == 2){
			MyVertex selected1 = selected.poll();
			MyVertex selected2 = selected.poll();
			
			MyEdge e = new MyEdge(selected1, selected2);
			edges.add(e);
			graph.addEdge((MyVertex) selected1, (MyVertex) selected2);
			selected1.isSelected = false;
			selected2.isSelected = false;
		}
		
	}
	
	public void clearSelected(){
		System.out.println("Clearing " + selected.toString());
		while(!selected.isEmpty()){
			
			MyVertex temp = selected.poll();
			temp.isSelected = false;
			System.out.println("Clearing "+ temp);
		}
		
		if(selectedEdge!=null){
			selectedEdge.selected = false;
			selectedEdge = null;
		}
		repaint();
	}
	
	public void paintComponent(Graphics g){
		
		Graphics2D g2 = (Graphics2D) g;
		for(MyVertex a : vertices)
			a.draw(g2);
		
		for(MyEdge e : edges)
			e.draw(g2);
		
		
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
					dragged = v;
					break;
				}
			}
			double x = p.getX();
			double y = p.getY();
			if(dragged!=null)
				dragged.translate((int)x-dragged.x,(int) y-dragged.y);
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
				System.out.println("Start " + selected.toString());
				Point2D p = e.getPoint();
				boolean found = false;
				MyVertex temp;
				
				for(MyVertex v : vertices){
					if(v.contains(p)){
						found = true;
					
						//if v is selected
						if(v.isSelected){
							v.isSelected = false;
							if(selected.contains(v)){
								temp = selected.poll();
								if(temp!=v){
									selected.add(temp);
									temp = selected.poll();
								}
							}
						}else{
							v.isSelected = true;
							if(selected.size() == 2){
								temp = selected.poll();
								temp.isSelected = false;
								selected.add(v);
							}else if(selected.size() < 2){
								selected.add(v);
							}
						}	
					}
				}
				
				for(MyEdge ed : edges){
					//System.out.println("edge");
					if(ed.contains(p)){
					//	System.out.println("Edge found");
						selectedEdge = ed;
						selectedEdge.selected = true;
						found = true;
					}
				}
				
				if(!found)
					clearSelected();
				repaint();
				System.out.println("Selected list " + selected.toString());
				
				
			}

/*			@Override
			public void mouseClicked(MouseEvent e) {
				Point2D p = e.getPoint();
				boolean found = false;
				System.out.println("In mouse clicked");
				for(MyVertex v : vertices){
					System.out.println("comparing " + v);
					if(v.contains(p)){
						if(v.isSelected){
							System.out.println("undo selected " + v);
							v.isSelected = false;
							MyVertex temp;
							if(selected.contains(v)){
								temp = selected.poll();
								if(temp!=v){
									MyVertex newTemp = selected.poll();
									newTemp.isSelected = false;
									selected.offer(temp);
								}
							}
						}
						else{
							System.out.println("new stuff is selected");
							v.isSelected = true;
							if(selected.size()==2){
								MyVertex temp = selected.poll();
								temp.isSelected = false;
								selected.offer(v);
							}else{
								selected.offer(v);
							}	
						}
						found = true;
					}
				}
				for(MyEdge ed : edges){
					//System.out.println("edge");
					if(ed.contains(p)){
					//	System.out.println("Edge found");
						selectedEdge = ed;
						selectedEdge.selected = true;
						found = true;
					}
				}
				if(!found){
					clearSelected();
				}
				repaint();
			}
*/
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
	

