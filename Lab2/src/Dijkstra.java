import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;


class Vertex{
	int id,d;
	Vertex p;
	Vertex(int id){
		this.id = id;
		this.d = Integer.MAX_VALUE;
		this.p = null;
	}
	
	public int getD() {
		return d;
	}
	
	void printPath() {
		if(this.p!=null) {
			this.p.printPath();
		}
		System.out.print(this.id + " -> ");
		
	}

	@Override
	public String toString() {
		return id + " -> " +p;
	}
	
	
}
class Edge{
	Vertex u, v;
	int w;
	Edge(Vertex u, Vertex v, int w){
		this.u = u;
		this.v = v;
		this.w = w;
	}
	
}
class Graph{
	
	LinkedList<Edge> adj[];
	int v;
	List<Vertex> vertices;
	
	
	@SuppressWarnings("unchecked")
	Graph(int v){
		this.v = v;
		this.adj = new LinkedList [v];
		this.vertices = new ArrayList<Vertex>();
		for(int i=0; i<v; i++) {
			adj[i] = new LinkedList<Edge>();
			vertices.add(new Vertex(i));
		}

	}
	
	void addEdge(int u, int v, int w) {
		Edge e = new Edge(vertices.get(u), vertices.get(v), w);
		this.adj[u].add(e); 
	}
	
	
	void shortestPath(int s) {
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>((u,v)->(u.getD() - v.getD()));
		vertices.get(s).d = 0;
		q.addAll(vertices);
		
		while(!q.isEmpty()) {

			Vertex u = q.poll();
			for(Edge e: this.adj[u.id]) {
				this.relax(u,e.v, e.w, q);
				
			}
		}
	}
	
	void relax(Vertex u, Vertex v, int w, PriorityQueue<Vertex> q) {
		if(u.d + w < v.d) {
			v.d = u.d + w;
			v.p = u;
//			q.offer(v);
		}
	}
	
}
public class Dijkstra {

	public static void main(String[] args) {
		Graph g = new Graph(7);
		
		g.addEdge(0, 2, 1);
		g.addEdge(2, 3, 5);
		g.addEdge(2, 5, 20);
		g.addEdge(2, 6, 30);
		g.addEdge(3, 1, 4);
		g.addEdge(3, 4, 3);
		g.addEdge(3, 5, 9);
		g.addEdge(4, 5, 1);
		g.addEdge(5, 6, 3);
		
		g.shortestPath(0);
		for(Vertex v: g.vertices) {
			v.printPath();
			System.out.println("");
		}

		
	}

}
