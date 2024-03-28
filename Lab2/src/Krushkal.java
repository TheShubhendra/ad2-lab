import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class DisjointSet{
	int v;
	Set<Integer>[] sets;
	
	DisjointSet(int v){
		this.v = v;
		this.sets = new HashSet[v];
		for(int i=0; i<v; i++) {
			this.sets[i] = new HashSet<Integer>();
			this.sets[i].add(i);
		}
	}
	
	public int findSet(int u) {
		for(int i=0; i<v; i++) {
			if(this.sets[i].contains(u)) {
				return i;
			}
		}
		return -1;
	}
	
	public void union(int a, int b) {
		this.sets[a].addAll(this.sets[b]);
		this.sets[b].clear();
	}
}

class fEdge implements Comparable<Edge>{
	int u,v,w;
	void Edge(int u, int v, int w){
		this.u = u;
		this.v = v;
		this.w = w;
	}
	@Override
	public int compareTo(Edge o) {
		return this.w - o.w;
	}
	
}

class Graph1{
	

	
	LinkedList<Integer> adj[];
	int v;
	List<Edge> edges;
	
	
	@SuppressWarnings("unchecked")
	Graph(int v){
		this.v = v;
		this.adj = new LinkedList [v];
		for(int i=0; i<v; i++) {
			adj[i] = new LinkedList<Integer>();
		}
		this.edges = new ArrayList<Edge>();

	}
	
	void addEdge(int u, int v, int w) {
		Edge e = new Edge(u,v,w);
		this.edges.add(e);
		this.adj[e.u].add(e.v);
		this.adj[e.v].add(e.u);
	}
	
	List<Edge> mst(){
		List<Edge> mstEdges = new ArrayList<Edge>();
		DisjointSet d = new DisjointSet(this.v);
		
		Collections.sort(this.edges);
		
		for(Edge e: this.edges) {
			int a = d.findSet(e.u);
			int b = d.findSet(e.v);
			if(a != b) {
				d.union(a, b);
				mstEdges.add(e);
			}
		}
		
		return mstEdges;
	}
}
public class Krushkal {

	public static void main(String[] args) {
		Graph g = new Graph(6);
		
		g.addEdge(0, 2, 1);
		g.addEdge(1, 2, 4);
		g.addEdge(1, 3, 2);
		g.addEdge(2, 3, 4);
		g.addEdge(2, 4, 1);
		g.addEdge(2, 5, 3);
		g.addEdge(3, 4, 7);
		g.addEdge(4, 5, 2);
		
		List<Edge> mst = g.mst();
		
		for(Edge e: mst) {
			System.out.println(e.u + " -> " + e.v);
		}
	}

}
