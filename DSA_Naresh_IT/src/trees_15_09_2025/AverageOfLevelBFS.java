package trees_15_09_2025;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

public class AverageOfLevelBFS {
	public static void main(String[] args) {
		TreeNode root = new TreeNode();
		root.val = 3;
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		List<Double> averageOfBinaryTree = findAverageOfBinaryTree(root);
		System.out.println(averageOfBinaryTree);

	}

	static List<Double> findAverageOfBinaryTree(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		List<Double> result = new ArrayList<>();

		while (!queue.isEmpty()) {
			double sum = 0;
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				sum = sum + node.val;
				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
			}
			result.add(sum / size);
		}
		return result;
	}

}
