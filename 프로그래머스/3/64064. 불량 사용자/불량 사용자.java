import java.util.*;
class Solution {
    
    Set<Set<String>> candidates;
    String[] userIds;
    String[] bannedIds;
    
    public int solution(String[] user_id, String[] banned_id) {
        candidates = new HashSet<>();
        userIds = user_id;
        bannedIds = banned_id;
        dfs(0, 0, new HashSet<>());
        return candidates.size();
    }
    
    private void dfs(int depth, int matchCount, HashSet<String> set) {
        if (matchCount == bannedIds.length) {
            if (!isContainBannedId(set)) {
                HashSet<String> strings = new HashSet<>(set);
                candidates.add(strings);
            }
            return;
        }
        if (depth == userIds.length) {
            return;
        }

        for (int i = 0; i < userIds.length; i++) {
            if (matchBannedId(userIds[i], bannedIds[matchCount]) && !set.contains(userIds[i])) {
                set.add(userIds[i]);
                dfs(depth + 1, matchCount + 1, set);
                set.remove(userIds[i]);
            }
        }
    }

    private boolean isContainBannedId(Set<String> temp) {
        if (candidates.size() == 0) {
            return false;
        }
        for (Set<String> candidate : candidates) {
            if (candidate.equals(temp)) {
                return true;
            }
        }
        return false;
    }

    private boolean matchBannedId(String userId, String bannedId) {
        if (userId.length() != bannedId.length()) {
            return false;
        }
        for (int i = 0; i < userId.length(); i++) {
            if (userId.charAt(i) != bannedId.charAt(i) && bannedId.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }
}