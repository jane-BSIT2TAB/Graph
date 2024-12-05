import java.util.*;

public class ShortestPath {

    static class Graph {
        private int vertices;
        private LinkedList<Edge>[] adjacencyList;

        Graph(int vertices) {
            this.vertices = vertices;
            adjacencyList = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjacencyList[i] = new LinkedList<>();
            }
        }

        static class Edge {
            int destination;
            int weight;

            Edge(int destination, int weight) {
                this.destination = destination;
                this.weight = weight;
            }
        }

        void addEdge(int source, int destination, int weight) {
            Edge edge = new Edge(destination, weight);
            adjacencyList[source].add(edge);
        }

        int[] dijkstra(int source) {
            int[] dist = new int[vertices];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[source] = 0;
            PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
            pq.add(new Node(source, 0));

            while (!pq.isEmpty()) {
                Node current = pq.poll();
                int u = current.vertex;

                for (Edge edge : adjacencyList[u]) {
                    int v = edge.destination;
                    int weight = edge.weight;
                    if (dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        pq.add(new Node(v, dist[v]));
                    }
                }
            }
            return dist;
        }

        static class Node {
            int vertex;
            int weight;

            Node(int vertex, int weight) {
                this.vertex = vertex;
                this.weight = weight;
            }
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();
        Graph graph = new Graph(vertices);

        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();
        for (int i = 0; i < edges; i++) {
            System.out.print("Enter source, destination, and weight of edge " + (i + 1) + ": ");
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(source, destination, weight);
        }

        System.out.print("Enter the source vertex: ");
        int sourceVertex = scanner.nextInt();

        int[] distances = graph.dijkstra(sourceVertex);

        System.out.println("Shortest distances from vertex " + sourceVertex + ":");
        for (int i = 0; i < vertices; i++) {
            System.out.println("To vertex " + i + ": " + (distances[i] == Integer.MAX_VALUE ? "Infinity" : distances[i]));
        }
        scanner.close();
    }
}
