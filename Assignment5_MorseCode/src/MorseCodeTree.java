import java.util.ArrayList;
import java.util.Stack;

public class MorseCodeTree implements LinkedConverterTreeInterface<String>{

	private TreeNode<String> root;
	
	@Override
	public TreeNode<String> getRoot() {
		return root;
	}

	@Override
	public void setRoot(TreeNode<String> newNode) {
		
		
	}

	@Override
	public void insert(String code, String result) {
		
		
	}

	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		
		
	}

	@Override
	public String fetch(String code) {
		
		return null;
	}

	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		
		return null;
	}

	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		
		return null;
	}

	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		
		return null;
	}

	@Override
	public void buildTree() {
		int offset = 'a'; // 95
		root = new TreeNode<String>("");
		TreeNode<String>[] nodes = new TreeNode[26];
		for (int i = 0; i < 26; i++) {
			nodes[i] = new TreeNode<String>((char)(i+offset)+"");
		}
		
		String structure = "eish//v//uf///arl///wp//j//tndb//x//kc//y//mgz//q//o//";
		
		TreeNode<String> node = root;
		Stack<TreeNode<String>> left_nodes = new Stack<TreeNode<String>>();
		
		boolean looking_left = true;
		for (char c : structure.toCharArray()) {
			
			if (c == '/') {
				if (looking_left)
					looking_left = false;
				else
					node = left_nodes.pop();
				continue;
			}
			
			if (looking_left) {
				left_nodes.add(node);
				node.left = nodes[c-offset];
				node = node.left;
			}else {
				node.right = nodes[c-offset];
				looking_left = true;
			}
		}
	}

	@Override
	public ArrayList<String> toArrayList() {
		
		return null;
	}

	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		
		
	}

}
