import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        int rows = relation.length;
        int cols = relation[0].length;

        List<Integer> candidateKeys = new ArrayList<>();

        for (int size = 1; size <= cols; size++) {
            for (int mask = 1; mask < (1 << cols); mask++) {
                if (Integer.bitCount(mask) != size) continue;

                boolean violatesMinimality = false;
                for (int ck : candidateKeys) {
                    if ((mask & ck) == ck) { 
                        violatesMinimality = true;
                        break;
                    }
                }
                if (violatesMinimality) continue;

                Set<String> seen = new HashSet<>();
                for (int r = 0; r < rows; r++) {
                    StringBuilder key = new StringBuilder();
                    for (int c = 0; c < cols; c++) {
                        if ((mask & (1 << c)) != 0) {
                            key.append(relation[r][c]).append('\u0001');
                        }
                    }
                    seen.add(key.toString());
                }

                if (seen.size() == rows) {
                    candidateKeys.add(mask);
                }
            }
        }
        return candidateKeys.size();
    }
}
