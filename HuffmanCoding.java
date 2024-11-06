import java.util.PriorityQueue;

class Node implements Comparable<Node> {
    int freq;
    String symbol;
    Node left;
    Node right;
    String huff = "";

    public Node(int freq, String symbol, Node left, Node right) {
        this.freq = freq;
        this.symbol = symbol;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node other) {
        return this.freq - other.freq;
    }
}

public class HuffmanCoding {

    public static void printNodes(Node node, String val) {
        String newVal = val + node.huff;
        
        if (node.left != null) {
            printNodes(node.left, newVal);
        }
        
        if (node.right != null) {
            printNodes(node.right, newVal);
        }
        
        if (node.left == null && node.right == null) {
            System.out.println(node.symbol + " -> " + newVal);
        }
    }

    public static void main(String[] args) {
        String[] chars = {"a", "b", "c", "d", "e", "f"};
        int[] freqs = {5, 9, 12, 13, 16, 45};

        PriorityQueue<Node> nodes = new PriorityQueue<>();

        for (int i = 0; i < chars.length; i++) {
            nodes.add(new Node(freqs[i], chars[i], null, null));
        }

        while (nodes.size() > 1) {
            Node left = nodes.poll();
            Node right = nodes.poll();
            
            left.huff = "0";
            right.huff = "1";
            
            Node newNode = new Node(left.freq + right.freq, left.symbol + right.symbol, left, right);
            nodes.add(newNode);
        }

        printNodes(nodes.peek(), "");
    }
}

