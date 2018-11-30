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
}