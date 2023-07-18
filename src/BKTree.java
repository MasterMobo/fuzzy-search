import java.util.ArrayList;

public class BKTree {
    public Node root;

    public BKTree() {
        root = null;
    }

    public BKTree(String s) {
        root = new Node(s);
    }

    public void insert(String s) {
        if (root == null) {
            root = new Node(s);
            return;
        }

        Node current = root;
        int dist = Levenshtein.dist(s, current.val);

        if (dist == 0) return;

        while (current.exists(dist)) {
            current = current.get(dist);
            dist = Levenshtein.dist(s, current.val);
        }

        current.addChild(s, dist);
    }

    public void insertMany(String[] arr) {
        for (String s: arr) {
            insert(s);
        }
    }

    private void search(String s, int tol, ArrayList<String> res, Node node) {
        int dist = Levenshtein.dist(s, node.val);

        if (dist <= tol) res.add(node.val);

//        for (int i = 1; i <= tol; i++) {
//            if (node.exists(i)) {
//                search(s, tol, res, node.get(i));
//            }
//        }
        // TODO: Figure this shit out!!
        int i = dist-tol;
        if(i<0) i=1;

        while(i <= dist + tol){
            if (node.exists(i)) {
                search(s, tol, res, node.get(i));
            }
            i++;
        }
    }

    public ArrayList<String> search(String s, int tol) {
        ArrayList<String> res = new ArrayList<>();
        search(s, tol, res, root);
        return res;
    }
}
