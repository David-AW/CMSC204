import java.util.ArrayList;
import java.util.Stack;

public class MorseCodeTree implements LinkedConverterTreeInterface<String>{

	private TreeNode<String> root;
	
	public MorseCodeTree() {
		buildTree();
	}
	
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
		return fetchNode(root, code);
	}

	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		if (code.length() > 0)
			return root.data;
		if (code.charAt(0) == '.')
			return fetchNode(root.left, code.substring(1));
		return fetchNode(root.right, code.substring(1));
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
		root = new TreeNode<String>("");
		
		String structure = "eish//v//uf///arl///wp//j//tndb//x//kc//y//mgz//q//o//";
		
		TreeNode<String> node = root;
		Stack<TreeNode<String>> prev_nodes = new Stack<TreeNode<String>>();
		
		boolean looking_left = true;
		for (char c : structure.toCharArray()) {
			if (c == '/') {
				if (looking_left)
					looking_left = false;
				else if (!prev_nodes.isEmpty())
					node = prev_nodes.pop();
				continue;
			}
			
			if (looking_left) {
				prev_nodes.add(node);
				node.left = new TreeNode<String>(c+"");
				node = node.left;
			}else {
				node.right = new TreeNode<String>(c+"");
				node = node.right;
				looking_left = true;
			}
		}
	}

	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> nodes = new ArrayList<String>();
		LNRoutputTraversal(root, nodes);
		return nodes;
	}

	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if (root.left != null)
			LNRoutputTraversal(root.left, list);
		list.add(root.data);
		if (root.right != null)
			LNRoutputTraversal(root.right, list);
	}

}
