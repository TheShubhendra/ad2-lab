import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffMan {
	static Map<Character, String> code;
	static Node head;
	
	static class Node implements Comparable<Node>{
		int freq;
		char ch;
		Node left;
		Node right;
		
		Node(int freq, char ch){
			this.freq = freq;
			this.ch = ch;
		}

		@Override
		public int compareTo(HuffMan.Node o) {
			return this.freq - o.freq;
		}
	}
	
	public static void printTree(Node node, String s) {
		if(node==null)
			return;
		if(node.ch != '-') {
			code.put(node.ch, s);
			System.out.println(node.ch + " : " + s);
		}else {
			printTree(node.left, s+'0');
			printTree(node.right, s+'1');
		}
	}
	
	
	public static String decode(String s) {
		StringBuilder res = new StringBuilder();
		Node curr = head;
		for(char ch: s.toCharArray()) {
			if(ch=='0') {
				curr = curr.left;
			}else {
				curr = curr.right;
			}
			if(curr.ch!='-') {
				res.append(curr.ch);
				curr = head;
			}
		}
		return res.toString();
	}
	
	public static String encode(String s) {
		StringBuilder res = new StringBuilder();
		for(char ch: s.toCharArray()) {
			res.append(code.get(ch));
		}
		return res.toString();
	}
	
	public static void main(String[] args) {
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		char[] symbols = {'C', 'A', 'K', 'T', 'R'};
		int[] freq = {75, 30, 105, 280, 110};
		for(int i=0;  i<symbols.length; i++) {
			q.add( new Node(freq[i], symbols[i]));
		}
		while(q.size() > 1) {
			Node a = q.poll();
			Node b = q.poll();
			Node p = new Node(a.freq + b.freq, '-');
			p.left = a;
			p.right = b;
			q.add(p);
		}
		code = new HashMap<>();
		head = q.poll();
		printTree(head, "");
		
		String encoded = encode("TAACRTK");
		System.out.println(encoded);
		String decoded = decode(encoded);
		System.out.println(decoded);
		
	}
}
