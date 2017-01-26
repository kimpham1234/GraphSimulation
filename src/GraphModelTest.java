
public class GraphModelTest {
	public static void main(String[] args){
		
	
		MyGraph<Integer> graph = new MyGraph<Integer>(5);
		MyVertex<Integer> a = new MyVertex<Integer>(1);
		MyVertex<Integer> b = new MyVertex<Integer>(2);
		MyVertex<Integer> c = new MyVertex<Integer>(3);
		MyVertex<Integer> d = new MyVertex<Integer>(4);
		MyVertex<Integer> e = new MyVertex<Integer>(5);
		
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		graph.addVertex(d);
		graph.addVertex(e);
		
		graph.addEdge(a, c);
		graph.addEdge(a, d);
		graph.addEdge(c, b);
		graph.addEdge(c, e);
		graph.addEdge(b, d);
		graph.addEdge(b, e);
		graph.addEdge(d, e);
		
		
		System.out.println(graph.printAdjacencyList());
		
	}

	
}
