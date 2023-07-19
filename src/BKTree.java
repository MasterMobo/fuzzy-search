import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

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

    private void search(String s, int tol, ArrayList<String> res, Node node, HashMap<String, Integer> map) {
        int dist = Levenshtein.dist(s, node.val);

        if (dist <= tol) {
            res.add(node.val);
            map.put(node.val, dist);
        }

        int i = dist-tol;
        if(i<0) i=1;

        while(i <= dist + tol){
            if (node.exists(i)) {
                search(s, tol, res, node.get(i), map);
            }
            i++;
        }
    }

    public ArrayList<String> search(String s, int tol) {
        ArrayList<String> res = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>(); // Maps path to levenshtein distance (to be sorted by distance)

        search(s, tol, res, root, map);
        res.sort(Comparator.comparing(map::get));

        return res;
    }

    public ArrayList<String> search(String s, int tol, int limit) {
        ArrayList<String> res = new ArrayList<>(); // Stores unsorted result
        HashMap<String, Integer> map = new HashMap<>(); // Maps path to levenshtein distance (to be sorted by distance)

        search(s, tol, res, root, map);
        res.sort(Comparator.comparing(map::get));

        if (res.size() <= limit) return res;

        ArrayList<String> sliced = new ArrayList<>(limit);
        for (int i = 0; i < limit; i++) {
            sliced.add(res.get(i));
        }
        return sliced;
    }
}
