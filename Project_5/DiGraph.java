import java.util.LinkedList;
import java.util.Queue;

public class DiGraph {
    private LinkedList<Integer>[] arr;

    public DiGraph(int N) {
        this.arr = (LinkedList<Integer>[]) new LinkedList[N];
        for (int i = 0; i < N; i++) {
            this.arr[i] = new LinkedList<>();
        }
    }

    public void addEdge(int from, int to) {
        if (!this.arr[from - 1].contains(to - 1)) {
            this.arr[from - 1].add(to - 1);
        }
    }

    public void deleteEdge(int from, int to) {
        this.arr[from - 1].remove((Integer)(to - 1));
    }

    public int edgeCount() {
        int count = 0;
        for (LinkedList<Integer> list : this.arr) {
            count += list.size();
        }
        return count;
    }

    public int vertexCount() {
        return this.arr.length;   
    }

    public void print() {
        for (int i = 0; i < this.arr.length; i++) {
            System.out.print((i + 1) + " is connected to: ");
            for (int j = 0; j < this.arr[i].size() - 1; j++) {
                System.out.print((this.arr[i].get(j) + 1) + ", ");
            }
            if (this.arr[i].size() != 0) {
                System.out.print((this.arr[i].getLast() + 1) + "\n");
            } else {
                System.out.println();
            }
        }
    }

    private int[] indegrees() {
        int N = arr.length;
        int[] indegrees = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < arr[i].size(); j++) {
                indegrees[arr[i].get(j)] = indegrees[arr[i].get(j)] + 1;
            }
        }
        return indegrees;
    }

    public int[] topSort() {
        int N = arr.length;
        int[] indegrees = indegrees();
        int[] A = new int[N];
        LinkedList<Integer> Q = new LinkedList<Integer>();
        for (int i = 0; i < N; i++) {
            if (indegrees[i] == 0) {
                Q.addLast(i);
            }
        }
        int i = 0;
        int u;
        while (!Q.isEmpty()) {
            u = Q.removeFirst();
            A[i] = u;
            i++;
            for (int j = 0; j < arr[u].size(); j++) {
                indegrees[arr[u].get(j)] = indegrees[arr[u].get(j)] - 1;
                if (indegrees[arr[u].get(j)] == 0) {
                    Q.addLast(arr[u].get(j));
                }
            }
        }
        if (i != N) {
            throw new IllegalArgumentException();
        }
        return A;
    }

    private VertexInfo[] BFS(int s) {
        int N = arr.length;
        VertexInfo[] VA = new VertexInfo[N];
        for (int u = 0; u < N; u++) {
            VA[u] = new VertexInfo();
        }
        VA[s].distance = 0;
        LinkedList<Integer> Q = new LinkedList<>();
        Q.addLast(s);
        int u;
        while (!Q.isEmpty()) {
            u = Q.removeFirst();
            for (int i = 0; i < arr[u].size(); i++) {
                if (VA[arr[u].get(i)].distance == -1) {
                    VA[arr[u].get(i)].distance = VA[u].distance + 1;
                    VA[arr[u].get(i)].predecessor = u;
                    Q.addLast(arr[u].get(i));
                }
            }
        }
        return VA;
    }

    public boolean isTherePath(int from, int to) {
        VertexInfo[] VA = BFS(from - 1);
        if (VA[to - 1].distance == -1) {
            return false;
        } else {
            return true;
        }
    }
    
    public int lengthOfPath(int from, int to) {
        VertexInfo[] VA = BFS(from - 1);
        return VA[to - 1].distance;
    }

    public void printPath(int from, int to) {
        VertexInfo[] VA = BFS(from - 1);
        String output = "";

        if (!isTherePath(from, to)) {
            System.out.println("No Path");
            return;
        } else {
            to = to - 1;
            while (from - 1 != to) {
                output = "->" + (to + 1) + output;
                to = VA[to].predecessor;
            }
            output = from + output;
        }
        System.out.println(output);
    }

    private TreeNode buildTree(int s) {
        VertexInfo[] VA = BFS(s - 1);
        TreeNode root = new TreeNode(s);

        buildTree_recursive(root, VA);

        return root;
    }

    private void buildTree_recursive(TreeNode node, VertexInfo[] VA) {
        for (int i = 0; i < VA.length; i++) {
            if (VA[i].predecessor == node.vertexNum) {
                TreeNode newNode = new TreeNode(i); 
                node.list.add(newNode);
                buildTree_recursive(newNode, VA);
            }
        }
    }

    public void printTree(int s) {
        TreeNode root = buildTree(s);

        printTree_recursive(root, 0);
    }

    private void printTree_recursive(TreeNode node, int indentation) {
        for (int i = 0; i < indentation; i++) {
            System.out.print(" ");
        }

        System.out.println(node.vertexNum);
        int size = node.list.size();
        for (int i = 0; i < size; i++) {
            printTree_recursive(node.list.get(i), indentation + 4);
        }
        
    }

    private class VertexInfo {
        public int distance = -1;
        public int predecessor = -1;
    }

    private class TreeNode {
        public int vertexNum;
        public LinkedList<TreeNode> list = new LinkedList<TreeNode>();

        public TreeNode(int i) {
            this.vertexNum = i;
        }
    }
}