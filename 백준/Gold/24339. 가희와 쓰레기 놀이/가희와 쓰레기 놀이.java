import java.io.*;
import java.util.*;

public class Main {
    static int O, E;
    static Set<Integer> roots;
    static Map<Integer, Node> nodes;
    static Map<Integer, Edge> edges;

    static class Node {
        int id;
        boolean isRoot;
        Set<Integer> weakEdges;
        Set<Integer> strongEdges;

        public Node(int id, String rootInfo) {
            this.id = id;
            this.isRoot = rootInfo.equals("ROOT");
            weakEdges = new HashSet<>();
            strongEdges = new HashSet<>();
        }
    }

    static class Edge {
        int id;
        int from;
        int to;
        boolean isStrong; 

        public Edge(int id, int from, int to, boolean isStrong) {
            this.id = id;
            this.from = from;
            this.to = to;
            this.isStrong = isStrong;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        O = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        init();

        for (int i = 0; i < O; i++) {
            st = new StringTokenizer(bf.readLine());
            int nodeId = Integer.parseInt(st.nextToken());
            String rootInfo = st.nextToken();
            createNode(nodeId, rootInfo);
        }

        for (int i = 0; i < E; i++) {
            String command = bf.readLine();
            if (command.startsWith("MADE")) {
                createNode(command);
            } else if (command.startsWith("ADD")) {
                addEdge(command);
            } else if (command.startsWith("REMOVE")) {
                removeEdge(command);
            } else if (command.charAt(0) == 'M') {
                System.out.println(performBFS(false)); 
            } else if (command.charAt(0) == 'm') {
                System.out.println(performBFS(true)); 
            }
        }

        bf.close();
    }

    private static void init() {
        roots = new HashSet<>();
        nodes = new HashMap<>();
        edges = new HashMap<>();
    }

    private static void createNode(int id, String rootInfo) {
        if (!nodes.containsKey(id)) {
            nodes.put(id, new Node(id, rootInfo));
            if (rootInfo.equals("ROOT")) {
                roots.add(id);
            }
        }
    }

    private static void createNode(String command) {
        StringTokenizer st = new StringTokenizer(command.substring(5));
        int id = Integer.parseInt(st.nextToken());
        String rootInfo = st.nextToken();
        createNode(id, rootInfo);
    }

    private static void addEdge(String command) {
        StringTokenizer st = new StringTokenizer(command.substring(4));
        int edgeId = Integer.parseInt(st.nextToken());
        int from = Integer.parseInt(st.nextToken());
        String type = st.nextToken();
        int to = Integer.parseInt(st.nextToken());

        if (!nodes.containsKey(from) || !nodes.containsKey(to)) return;

        Edge newEdge = new Edge(edgeId, from, to, type.equals("=>"));
        edges.put(edgeId, newEdge);

        if (newEdge.isStrong) {
            nodes.get(from).strongEdges.add(edgeId);
        } else {
            nodes.get(from).weakEdges.add(edgeId);
        }
    }

    private static void removeEdge(String command) {
        int edgeId = Integer.parseInt(command.substring(7));
        if (!edges.containsKey(edgeId)) return;

        Edge edge = edges.remove(edgeId);
        nodes.get(edge.from).strongEdges.remove(edgeId);
        nodes.get(edge.from).weakEdges.remove(edgeId);
    }

    private static int performBFS(boolean followWeak) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int root : roots) {
            if (nodes.containsKey(root)) {
                visited.add(root);
                queue.offer(root);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            Node currentNode = nodes.get(cur);
            if (currentNode == null) continue;

            for (int edgeId : currentNode.strongEdges) {
                Edge edge = edges.get(edgeId);
                if (edge == null || visited.contains(edge.to)) continue;
                visited.add(edge.to);
                queue.offer(edge.to);
            }


            if (followWeak) {
                for (int edgeId : currentNode.weakEdges) {
                    Edge edge = edges.get(edgeId);
                    if (edge == null || visited.contains(edge.to)) continue;
                    visited.add(edge.to);
                    queue.offer(edge.to);
                }
            }
        }
        removeUnvisitedNodes(visited);

        return nodes.size();
    }

    private static void removeUnvisitedNodes(Set<Integer> visited) {
        Set<Integer> toRemove = new HashSet<>();
        for (int nodeId : nodes.keySet()) {
            if (!visited.contains(nodeId)) {
                toRemove.add(nodeId);
            }
        }
        for (int nodeId : toRemove) {
            nodes.remove(nodeId);
        }
        edges.values().removeIf(edge -> !nodes.containsKey(edge.from) || !nodes.containsKey(edge.to));
    }
}
