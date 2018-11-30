import java.util.Scanner;

public class DiGraphTest {

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of verticies: ");

        DiGraph graph = new DiGraph(sc.nextInt());

        System.out.println("Choose one of the following operations:");
        System.out.println("- add edge (enter a)");
        System.out.println("- delete edge (enter d)");
        System.out.println("- edge count (enter e)");
        System.out.println("- vertex count (enter v)");
        System.out.println("- print graph (enter p)");
        System.out.println("- Quit (enter q)");

        String input;
        String bad;
        char option;
        int vertex1;
        int vertex2; 
        while (true) {
            input = sc.next();
            bad = sc.nextLine();
            if (input.length() > 1) {
                option = 'x';
            } else {
                option = input.charAt(0);
            }
            for (int i = 0; i < bad.length(); i++) {
                if (bad.charAt(i) != ' ') {
                    option = 'x';
                }
            }
            switch (option) {
                case 'a':
                    System.out.println("Enter two vertices: ");
                    vertex1 = sc.nextInt();
                    vertex2 = sc.nextInt();
                    graph.addEdge(vertex1, vertex2);
                    System.out.println("Edge (" + (vertex1) + ", " + (vertex2) + ") added");
                    break;
                case 'd':
                    System.out.println("Enter two vertices: ");
                    vertex1 = sc.nextInt();
                    vertex2 = sc.nextInt();
                    graph.deleteEdge(vertex1, vertex2);
                    System.out.println("Edge (" + (vertex1) + ", " + (vertex2) + ") deleted");
                    break;
                case 'e':
                    System.out.println("Number of edges: " + graph.edgeCount());
                    break;
                case 'v':
                    System.out.println("Number of vertices: " + graph.vertexCount());
                    break;
                case 'p':
                    System.out.println("The graph is:");
                    graph.print();
                    break;
                case 't':
                    try {
                        int[] result = graph.topSort();
                        for (int i = 0; i < result.length; i++) {
                            if (i > 0) {
                                System.out.print(", ");
                            }
                            System.out.print(result[i] + 1);
                        }
                        System.out.println();
                    } catch (Exception e) {
                        System.out.println("Graph is cyclic!");
                    }
                    
                    break;
                case 'q':
                    System.out.println("Bye bye");
                    System.exit(0);
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}