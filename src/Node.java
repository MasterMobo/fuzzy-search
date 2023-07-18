import java.util.HashMap;

public class Node {
    public String val;
    public HashMap<Integer, Node> children;

    public Node(String s) {
        val = s;
        children = new HashMap<>();
    }

    public void addChild(String s, int dist) {
        children.put(dist, new Node(s));
    }

    public Node get(int n) {
        return children.get(n);
    }

    public boolean exists(int n) {
        return children.containsKey(n);
    }
}
