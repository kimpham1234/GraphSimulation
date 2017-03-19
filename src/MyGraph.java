import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JComponent;

public class MyGraph<T>{
	
	static MyVertex nil = new MyVertex<String>("leuleu");
	private ArrayList<MyVertex>[] adjacencyList;
	int vertexNum;
	int maxVertexNum;
	int time;
	String path;
	
	public MyGraph(int maxVertexNum){
		adjacencyList = new ArrayList[maxVertexNum];
		this.maxVertexNum = maxVertexNum;
		for(int i = 0; i < maxVertexNum; i++){
			adjacencyList[i] = new ArrayList<MyVertex>();
		}
		vertexNum = 0;
	}
	
	public boolean addVertex(MyVertex a){
		if(vertexNum == maxVertexNum)
			return false;
		else{
			adjacencyList[vertexNum].add(a);
			a.index = vertexNum;
			vertexNum++;
			return true;
		}
	}
	
	public void addEdge(MyVertex a, MyVertex b){
		if(a.index > vertexNum || a.index < 0)
			return;
		if(b.index > vertexNum || a.index < 0)
			return;
		adjacencyList[a.index].add(b);
		adjacencyList[b.index].add(a);
	}
	
	public void removeVertex(MyVertex a){
		adjacencyList[a.index].clear();
		for(int i = a.index; i < vertexNum-1; i++){
			adjacencyList[i] = adjacencyList[i+1];
		}
		vertexNum--;
		for(int i = 0; i < vertexNum; i++){
			if(adjacencyList[i].contains(a))
				adjacencyList[i].remove(a);
		}
	}
	
	public void removeEdge(MyVertex a, MyVertex b){
		adjacencyList[a.index].remove(b);
		adjacencyList[b.index].remove(a);
	}
	
	public String printAdjacencyList(){
		String adj = "";
		for(int i = 0; i < vertexNum; i++){
			ArrayList<MyVertex> currentList = adjacencyList[i];
			for(int j = 0; j < currentList.size(); j++){
				if(j != currentList.size()-1)
					adj += currentList.get(j)+"->";
				else
					adj += currentList.get(j)+"";
			}
			adj+="\n";
		}
		return adj;
	}
	
	public void bfs(MyVertex s){
		for(int i = 0; i < vertexNum; i++){
			MyVertex u = adjacencyList[i].get(0);
			u.d = 0;
			u.color = COLOR.WHITE;
			u.parent = nil;
		}
	
		Queue<MyVertex> q = new LinkedList<MyVertex>();
		q.add(s);
		s.color = COLOR.GREY;
		while(!q.isEmpty()){
			MyVertex u = q.remove();
			ArrayList<MyVertex> adjU = adjacencyList[u.index];
			for(int i = 0; i < adjU.size(); i++){
				MyVertex v = adjU.get(i);
                                 
				if(v.color == COLOR.WHITE){
					v.color = COLOR.GREY;
					v.d = u.d + 1;
					v.parent = u;
					q.add(v);
				}
			}
			u.color = COLOR.BLACK;	
		}
	}
	
	//depth first search
		public void dfs(){
			for(int i = 0; i < vertexNum; i++){
				MyVertex u = adjacencyList[i].get(0);
				u.color = COLOR.WHITE;
				u.d = 0;
				u.parent = nil;
			}
	        
			time = 0;
			for(int i = 0; i < vertexNum; i++){
				MyVertex u = adjacencyList[i].get(0);
				if(u.color == COLOR.WHITE)
					dfsVisit(u);
			}
		}
		
		//depth first search visit
		private void dfsVisit(MyVertex u){
			time++;
			u.d = time;
			u.color = COLOR.GREY;
			for(int i = 0; i < adjacencyList[u.index].size(); i++){
				MyVertex v = adjacencyList[u.index].get(i);
				if(v.color == COLOR.WHITE){
					v.parent = u;
					dfsVisit(v);
				}
			}
			u.color = COLOR.BLACK;
			time++;
			u.f = time;
		}
		
		//called after dfs or bfs to get path to a vertex
		public String getPath(MyVertex a){
	        path ="";
	        if(a.parent !=null)
			printPathRecursive(a);
			return path;
		}
	        
		
		//recursively get the path
		private void printPathRecursive(MyVertex a){
			if(a.parent!=nil)
				printPathRecursive(a.parent);
			path+=a.toString();
		}
		
}
