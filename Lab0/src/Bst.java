import java.util.ArrayList;
import java.util.List;

class  Node{
		int info;
		Node left;
		Node right;
		
		Node(int info){
			this.info = info;
			this.left = null;
			this.right = null;
		}
	}

public class Bst {


	public static void insert(Node root, int k) {
		if(k<=root.info) {
			if(root.left==null) {
				root.left = new Node(k);
			}else {
				insert(root.left, k);
			}
		}else {
			if(root.right == null) {
				root.right = new Node(k);
			}else {
				insert(root.right, k);
			}
		}
	}
	
	public static Node create_bst(int arr[]) {
		if(arr.length<1) {
			return null;
		}
		Node root = new Node(arr[0]);
		for(int i=1; i<arr.length; i++) {
			insert(root, arr[i]);
			
		}
		return root;
	}
	
	public static void inorder(Node root) {
		if(root==null) {
			return;
		}
		inorder(root.left);
		System.out.print(root.info + " ");
		inorder(root.right);
	}
	public static void preorder(Node root) {
		if(root==null) {
			return;
		}
		System.out.print(root.info + " ");
		preorder(root.left);
		preorder(root.right);
	}
	public static void postorder(Node root) {
		if(root==null) {
			return;
		}
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.info + " ");
	}
	
	public static ArrayList<Node> inorderList(Node root, ArrayList<Node> list) {
		if(list==null) {
			list = new ArrayList<>();
		}
		if(root==null) {
			return list;
		}
		inorderList(root.left, list);
		list.add(root);
		inorderList(root.right, list);
		return list;
		
	}
	
	public static void delete(Node root, int k) {
		Node curr = root;
		Node parent = null;
		while(curr!=null) {
//			System.out.println("C " + curr.info);
			if(curr.info==k) {
				if(curr.left==null && curr.right == null) {
					if(parent.left == curr) {
						parent.left = null;
					}else {
						parent.right = null;
					}
				}else if(curr.left!=null && curr.right==null) {
					if(parent.left==curr) {
						parent.left = curr.left;
					}else {
						parent.right = curr.left;
					}
				}else if (curr.right != null && curr.left==null){
					if(parent.left==curr) {
						parent.left = curr.right;
					}else {
						parent.right = curr.right;
					}
				}else {
					
					List<Node> inorderl = inorderList(root, null);
					Node successor = inorderl.get(inorderl.indexOf(curr) + 1);
					System.out.println(" S "+ successor.info);
					System.out.println("C "+ curr.info);
					System.out.println("P "+ parent.info);
					delete(root, successor.info);
					successor.left = curr.left;
					successor.right = curr.right;
					if(parent.left==curr) {
						parent.left = successor;
					}else {
						parent.right = successor;
					}
//					
					
					
				}
			break;
			}else if(k<curr.info) {
				parent = curr;
				curr = curr.left;
			}else {
				parent = curr;
				curr = curr.right;
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = {15, 27, 6, 82, 18, 4, 9, 65};
		
		Node root = create_bst(arr);
		System.out.print("Inorder: ");
		inorder(root);
//		System.out.print("\nPreorder: ");
//		preorder(root);
//		System.out.print("\nPostorder: ");
//		postorder(root);
		
		delete(root, 27);
		System.out.print("\nInorder: ");
		inorder(root);
		
	}

}
