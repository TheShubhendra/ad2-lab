import java.util.Iterator;
import java.util.LinkedList;

class Graph{
	LinkedList<Integer> adj[];
	int v;
	
	
	Graph(int v){
		this.v = v;
		this.adj = new LinkedList[v];
		for(int i=0; i<v; i++) {
			adj[i] = new LinkedList<>();
		}
	}
	
	void addEdge(int s, int t) {
		this.adj[s].add(t);
		this.adj[t].add(s);
	}
	void addDirectedEdge(int s, int t) {
		this.adj[s].add(t);
	}
	
	void degree() {
		for(int i=0; i<v; i++) {
			System.out.println("Degree of "+ i + " : "+ adj[i].size());
		}
	}
	
	void inOutDegree() {
		int[] degree = new int[v];
		for(LinkedList<Integer> v: adj) {
			for(int e: v) {
				degree[e]++;
			}
		}
		for(int i=0; i<v; i++) {
			System.out.println("Degree of "+ i + " Out: "+ adj[i].size()+ " In: "+ degree[i]);
		}
	}
}



public class GraphImplementation {

	public static void main(String[] args) {
		Graph g = new Graph(6);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 3);
		g.addEdge(0, 4);
		g.addEdge(0, 5);
		g.addEdge(2, 1);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.addEdge(4, 5);
		g.addEdge(1, 5);
//		g.degree();
		
		Graph g2 = new Graph(6);
		g2.addDirectedEdge(5, 1);
		g2.addDirectedEdge(1, 2);
		g2.addDirectedEdge(0, 5);
		g2.addDirectedEdge(0, 3);
		g2.addDirectedEdge(4, 0);
		g2.addDirectedEdge(5,4);
		g2.addDirectedEdge(2, 0);
		g2.inOutDegree();

		
		

	}

}
