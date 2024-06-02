import java.util.*;
class Solution {
    int nodeNumber;
    List<Node>[] adjustNodes;
    List<Integer> summitIndexes;
    boolean[] visited;
    Result result = new Result(Integer.MAX_VALUE, Integer.MAX_VALUE);

    private static class Result {
        int summitIndex;
        int intensity;

        public Result(int summitIndex, int intensity) {
            this.summitIndex = summitIndex;
            this.intensity = intensity;
        }

        private void updateResult(int nodeIndex, int intensity) {
            if (this.intensity > intensity) {
                this.summitIndex = nodeIndex;
                this.intensity = intensity;
                return;
            }
            if (this.intensity == intensity) {
                this.summitIndex = Math.min(this.summitIndex, nodeIndex);
            }
        }

    }

    private static class Node implements Comparable<Node> {
        int nodeIndex;
        int destination;
        int cost;
        int intensity;

        public Node(int nodeIndex, int destination, int cost, int intensity) {
            this.nodeIndex = nodeIndex;
            this.destination = destination;
            this.cost = cost;
            this.intensity = intensity;
        }


        @Override
        public int compareTo(Node o) {
            return Integer.compare(intensity, o.intensity);
        }
    }
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        nodeNumber = n;
        summitIndexes = new ArrayList<>();
        for (int i = 0; i < summits.length; i++) {
            summitIndexes.add(summits[i]);
        }
        adjustNodes = new List[nodeNumber + 1];
        for (int i = 1; i <= nodeNumber; i++) {
            adjustNodes[i] = new ArrayList<>();
        }
        for (int i = 0; i < paths.length; i++) {
            int start = paths[i][0];
            int end = paths[i][1];
            int cost = paths[i][2];
            adjustNodes[start].add(new Node(end, start, cost, 0));
            adjustNodes[end].add(new Node(start, end, cost, 0));
        }
        visited = new boolean[nodeNumber + 1];
        dks(gates);
        return new int[]{result.summitIndex,(int)result.intensity};
    }
    private void dks(int[] gates) {
        Queue<Node> queue = new PriorityQueue<>();
        for (int i = 0; i < gates.length; i++) {
            queue.add(new Node(gates[i], 0, 0, 0));
        }
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if (!visited[now.nodeIndex]) {
                visited[now.nodeIndex] = true;
            } else {
                continue;
            }

            if (summitIndexes.contains(now.nodeIndex)) {
                result.updateResult(now.nodeIndex, now.intensity);
                continue;
            }
            for (Node next : adjustNodes[now.nodeIndex]) {
                if (!visited[next.nodeIndex]) {
                    queue.add(
                            new Node(next.nodeIndex, 0, next.cost, Math.max(next.cost, now.intensity)));
                }

            }

        }
    }
}