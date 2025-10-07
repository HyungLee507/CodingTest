import java.util.*;

class Solution {
    static class Song {
        int idx, plays;
        Song(int idx, int plays) { this.idx = idx; this.plays = plays; }
    }

    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> total = new HashMap<>();             
        Map<String, List<Song>> byGenre = new HashMap<>();        
        for (int i = 0; i < genres.length; i++) {
            String g = genres[i];
            int p = plays[i];
            total.put(g, total.getOrDefault(g, 0) + p);
            byGenre.computeIfAbsent(g, k -> new ArrayList<>()).add(new Song(i, p));
        }

        List<String> orderGenres = new ArrayList<>(total.keySet());
        orderGenres.sort((a, b) -> Integer.compare(total.get(b), total.get(a)));

        List<Integer> picked = new ArrayList<>();
        for (String g : orderGenres) {
            List<Song> list = byGenre.get(g);

            list.sort((s1, s2) -> {
                if (s1.plays != s2.plays) return Integer.compare(s2.plays, s1.plays);
                return Integer.compare(s1.idx, s2.idx);
            });

            for (int i = 0; i < Math.min(2, list.size()); i++) {
                picked.add(list.get(i).idx);
            }
        }

        return picked.stream().mapToInt(i -> i).toArray();
    }
}
