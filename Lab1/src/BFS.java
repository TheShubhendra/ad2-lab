import java.util.Arrays;
import java.util.LinkedList;

class Graph{
	LinkedList<Integer> adj[];
	int v;
	int[] d;
	int [] pre;
	char [] col;
	int t=0;
	int [] time;
	
	
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
	
	void bfs(int s) {
		d = new int[v];
		col = new char[v];
		pre = new int[v];
		Arrays.fill(pre, -1);
		Arrays.fill(col, 'w');
		
		LinkedList<Integer> q = new LinkedList<Integer>();

		q.add(s);
		col[s] = 'b';
		pre[s] = -1;
		
		while(!q.isEmpty()) {
			int u = q.poll();
			for(int v: this.adj[u]) {
				if(col[v] == 'w') {
					d[v] = d[u]+1;
					pre[v] = u;
					col[v] = 'b';
					q.add(v);
				}
			}
			col[u] = 'g';
		}
		
	}
	
	void dfs_(int s) {
		d = new int[v];
		col = new char[v];
		pre = new int[v];
		Arrays.fill(pre, -1);
		Arrays.fill(col, 'w');
		
		LinkedList<Integer> q = new LinkedList<Integer>();

		q.add(s);
		col[s] = 'b';
		pre[s] = -1;
		
		while(!q.isEmpty()) {
			int u = q.pop();
			for(int v: this.adj[u]) {
				if(col[v] == 'w') {
					d[v] = d[u]+1;
					pre[v] = u;
					col[v] = 'b';
					q.push(v);
				}
			}
			col[u] = 'g';
		}
	}
	
	void dfs(int s) {
		time = new int[v];
		t = 0;
		d = new int[v];
		col = new char[v];
		pre = new int[v];
		Arrays.fill(pre, -1);
		Arrays.fill(col, 'w');
		
		for(int u: this.adj[s]) {
			if(col[u]=='w') {
				dfsVisit(u);
			}
		}
		
	}
	
	void dfsVisit(int u) {
		t++;
		time[u] = t;
		col[u] = 'b';
		for(int v: this.adj[u]) {
			if (col[v]=='w') {
				pre[v] = u;
				dfsVisit(v);
			}
		}
		col[u] = 'g';
		t++;
		time[u] = t;

	}
	
	void path(int s) {
		bfs(s);
		for(int i=0; i<v; i++) {
			int x = this.pre[i];
			while(x!=-1) {
				System.out.print(x + " <- ");
				x = this.pre[x];
			}
			System.out.print("\n");
		}
	}
	
	void printPath(int u, int v) {
		System.out.println(u + " "+ v);
		if(u==v) {
			System.out.print(u + " -> ");
		}else {
			printPath(u, this.pre[v]);
			System.out.print(v + "-> ");
		}
	}
}



public class BFS {

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
		
//		Graph g2 = new Graph(6);
//		g2.addDirectedEdge(5, 1);
//		g2.addDirectedEdge(1, 2);
//		g2.addDirectedEdge(0, 5);
//		g2.addDirectedEdge(0, 3);
//		g2.addDirectedEdge(4, 0);
//		g2.addDirectedEdge(5,4);
//		g2.addDirectedEdge(2, 0);
//		g2.inOutDegree();
		g.dfs(0);
		System.out.println(Arrays.toString(g.pre));
		for(int i = 0; i<g.v; i++) {
			g.printPath(0,3);
			System.out.println("");
		}
//		System.out.println(Arrays.toString(g.time));

//		System.out.println(Arrays.toString(g.col));

	}

}
