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
		addButton.addActionListener(event->{
			MyVertex a = new MyVertex(name.getText(), 50,50);
			comp.addVertex(a);
			repaint();
		});
		JButton addEdge = new JButton("Add edge");
		addEdge.addActionListener(event->{
			comp.addEdge();
			repaint();
		});
		
		nav.add(name);
		nav.add(addButton);
		nav.add(addEdge);
		
		
		nav.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	
		
		this.add(nav, BorderLayout.NORTH);
		this.add(comp, BorderLayout.CENTER);
		loadSamples();
		
		this.setSize(500,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void loadSamples(){
		MyVertex a = new MyVertex("a", 100, 100);
		MyVertex b = new MyVertex("b", 100, 200);
		comp.addVertex(a);
		comp.addVertex(b);
	    comp.sampleAddEdge(new MyEdge(a, b));
	}
	
}
