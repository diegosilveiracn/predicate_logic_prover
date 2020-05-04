import java.util.LinkedList;
import java.util.Queue;

import br.mia.parser.Node;
import br.mia.parser.SimpleNode;

public class ParserTreeReader {

	public ParserTreeReader(Node root) {
		traverseTree(root);
	}

	private void traverseTree(Node node) {
		Queue<Node> nodeQueue = new LinkedList<Node>();
		nodeQueue.offer(node);
		while (!nodeQueue.isEmpty()) {
			Node n = nodeQueue.poll();
			//System.out.println(((SimpleNode) n).first_token.image);
			System.out.println("Node = " + n + "\t" + "Token = " + ((SimpleNode) n).getToken());
			for (int i = 0; i < n.jjtGetNumChildren(); i++)
				nodeQueue.offer(n.jjtGetChild(i));

		}
	}

}