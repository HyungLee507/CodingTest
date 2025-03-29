import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int n, idx, height;

    static class Node {
        Node left;
        Node right;
        int idx;
        int value;  // in-order traversal로 할당되는 열 번호
        int depth;  // DFS 시 결정되는 레벨 (1부터 시작)
    }

    static Map<Integer, Node> nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        nodes = new HashMap<>();
        // 자식으로 등장한 노드를 체크하기 위한 배열 (1 ~ n)
        boolean[] isChild = new boolean[n + 1];

        // 입력 처리: 각 줄에 대해 부모, 왼쪽 자식, 오른쪽 자식을 연결
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            // 부모 노드를 생성하거나 기존 객체 가져오기
            Node parentNode = nodes.getOrDefault(parent, new Node());
            parentNode.idx = parent;
            nodes.put(parent, parentNode);

            if (left != -1) {
                Node leftNode = nodes.getOrDefault(left, new Node());
                leftNode.idx = left;
                parentNode.left = leftNode;
                nodes.put(left, leftNode);
                isChild[left] = true;
            }

            if (right != -1) {
                Node rightNode = nodes.getOrDefault(right, new Node());
                rightNode.idx = right;
                parentNode.right = rightNode;
                nodes.put(right, rightNode);
                isChild[right] = true;
            }
        }

        // 루트 찾기: 어떤 노드도 자식으로 등장하지 않은 노드가 루트임
        Node root = null;
        for (int i = 1; i <= n; i++) {
            if (!isChild[i]) {
                root = nodes.get(i);
                break;
            }
        }
        if (root == null) {
            return;  // 예외 상황
        }

        // 초기값 설정
        idx = 1;
        height = 0;
        // DFS를 통해 in-order 번호와 깊이 할당 (루트의 깊이는 1)
        dfs(root, 1);

        // 각 레벨의 너비 계산 (in-order로 부여된 column 번호 활용)
        int[] result = calculateWidth();
        System.out.println(result[0] + " " + result[1]);
        bf.close();
    }

    // DFS: in-order 순회하면서 각 노드에 깊이와 in-order 번호(value)를 할당
    private static void dfs(Node node, int depth) {
        if (node == null) {
            return;
        }
        // 현재 깊이 업데이트
        height = Math.max(height, depth);
        // 왼쪽 서브트리 탐색
        dfs(node.left, depth + 1);
        // 현재 노드 처리
        node.depth = depth;
        node.value = idx++;
        // 오른쪽 서브트리 탐색
        dfs(node.right, depth + 1);
    }

    // 모든 노드를 그룹화하여 레벨별 너비(열 번호의 차이 + 1) 계산
    private static int[] calculateWidth() {
        int[] result = new int[2];  // result[0] = 레벨, result[1] = 너비
        // 각 레벨별로 in-order 번호(열 번호)를 저장할 리스트 (인덱스 1부터 사용)
        List<List<Integer>> levels = new ArrayList<>();
        for (int i = 0; i <= height; i++) {
            levels.add(new ArrayList<>());
        }
        // 모든 노드에 대해 해당하는 레벨의 리스트에 in-order 번호 추가
        for (Node node : nodes.values()) {
            levels.get(node.depth).add(node.value);
        }

        int maxWidth = 0;
        int levelWithMaxWidth = 0;
        // 각 레벨의 리스트가 비어있지 않다면 너비 계산
        for (int d = 1; d <= height; d++) {
            List<Integer> level = levels.get(d);
            if (level.isEmpty()) {
                continue;
            }
            // in-order 번호는 DFS 순서이지만 리스트에 저장된 순서는 보장되지 않으므로 정렬
            Collections.sort(level);
            int width = level.get(level.size() - 1) - level.get(0) + 1;
            if (width > maxWidth) {
                maxWidth = width;
                levelWithMaxWidth = d;
            }
        }
        result[0] = levelWithMaxWidth;
        result[1] = maxWidth;
        return result;
    }
}
