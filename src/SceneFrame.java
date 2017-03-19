import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SceneFrame extends JFrame{
	GraphComponent comp = new GraphComponent();
	
	public SceneFrame(){
		JPanel nav = new JPanel();
		JTextField name = new JTextField("Name");
		JButton addButton = new JButton("Add");
		JButton deleteButton = new JButton("Delete");
		JButton addEdge = new JButton("Add edge");
		JButton bfsButton = new JButton("Run BFS");
		JButton dfsButton = new JButton("Run DFS");
		JButton seePath = new JButton("See Path");
		JTextField pathField = new JTextField(50);
		
		addButton.addActionListener(event->{
			MyVertex a = new MyVertex(name.getText(), 50,50);
			comp.addVertex(a);
			repaint();
		});
	
		addEdge.addActionListener(event->{
			comp.addEdge();
			repaint();
		});
		
		deleteButton.addActionListener(event->{
			comp.delete();
			repaint();
		});
		
		bfsButton.addActionListener(event->{
			if(comp.sourceSelected())
				comp.runBFS();
		});
		
		dfsButton.addActionListener(event->{
			comp.runDFS();
		});
		
		seePath.addActionListener(event->{
			if(comp.sourceSelected()){
				String path = comp.getPath();
				pathField.setText(path);
			}	
		});	
		
		nav.add(name);
		nav.add(addButton);
		nav.add(addEdge);
		nav.add(deleteButton);
		nav.add(bfsButton);
		nav.add(dfsButton);
		nav.add(seePath);
		
		JPanel pathPanel = new JPanel();
		pathPanel.add(pathField);
		
		nav.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	
		
		this.add(nav, BorderLayout.NORTH);
		this.add(comp, BorderLayout.CENTER);
		this.add(pathPanel, BorderLayout.SOUTH);
		//loadSamples();
		
		this.setSize(700, 700);;
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void loadSamples(){
		MyVertex a = new MyVertex("a", 100, 100);
		MyVertex b = new MyVertex("b", 100, 200);
		MyVertex c = new MyVertex("c", 300, 200);
		MyVertex d = new MyVertex("d", 100, 300);
		MyVertex e = new MyVertex("e", 400, 400);
		
		comp.addVertex(a);
		comp.addVertex(b);
		comp.addVertex(c);
		comp.addVertex(d);
		comp.addVertex(e);
		
		comp.sampleAddEdge(new MyEdge(a, c));
		comp.sampleAddEdge(new MyEdge(a, d));
		comp.sampleAddEdge(new MyEdge(c, b));
		comp.sampleAddEdge(new MyEdge(c, e));
		comp.sampleAddEdge(new MyEdge(b, b));
		comp.sampleAddEdge(new MyEdge(b, e));
		comp.sampleAddEdge(new MyEdge(d, e));	
	}
	
}
