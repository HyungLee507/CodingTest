import java.util.*;
class Solution {
    public int[][] solution(int[][] nodeinfo) {
        LinkedList<Node> nodes = new LinkedList<>();
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1));
        }
        Collections.sort(nodes);
        Node root = nodes.pollLast();
        while (!nodes.isEmpty()) {
            Node child = nodes.pollLast();
            Node leaf = root.getLeaf(child);
            leaf.setChild(child);
        }
        root.preSearch();
        root.postSearch();
        int[][] answer = new int[2][nodeinfo.length];
        for (int i = 0; i < Node.pre.size(); i++) {
            answer[0][i] = Node.pre.get(i);
        }
        for (int i = 0; i < Node.pre.size(); i++) {
            answer[1][i] = Node.past.get(i);
        }
        return answer;
    }
    
    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int index;
        Node leftChild;
        Node rightChild;

        static List<Integer> pre = new ArrayList<>();
        static List<Integer> past = new ArrayList<>();

        public Node(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }

//        public void linkingLeft(Node left) {
//            this.leftChild = left;
//        }
//
//        public void linkingRight(Node right) {
//            this.rightChild = right;
//        }

        public void setChild(Node child) {
            if (child.x < x) {
                leftChild = child;
            } else {
                rightChild = child;
            }
        }

        public Node getLeaf(Node node) {
            Node temp = this;
            while (true) {
                if (temp.x > node.x) {
                    if (temp.leftChild == null) {
                        break;
                    }
                    temp = temp.leftChild;
                } else if (temp.x < node.x) {
                    if (temp.rightChild == null) {
                        break;
                    }
                    temp = temp.rightChild;
                }
            }
            return temp;
        }

        private void preSearch() {
            Node temp = this;
            pre.add(temp.index);
            if (temp.leftChild != null) {
                temp.leftChild.preSearch();
            }
            if (temp.rightChild != null) {
                temp.rightChild.preSearch();
            }
        }

        private void postSearch() {
            Node temp = this;
            if (temp.leftChild != null) {
                temp.leftChild.postSearch();
            }
            if (temp.rightChild != null) {
                temp.rightChild.postSearch();
            }
            past.add(temp.index);
        }


        @Override
        public int compareTo(Node o) {
            if (y == o.y) {
                return Integer.compare(x, o.x);
            } else {
                return Integer.compare(y, o.y);
            }
        }


    }
}