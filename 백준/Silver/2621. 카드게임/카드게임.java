import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            cards.add(new Card(st.nextToken(), Integer.parseInt(st.nextToken())));
        }
        Map<String, List<Card>> colorGroup = cards.stream().collect(groupingBy(Card::getColor));
        Map<Integer, List<Card>> numberGroup = cards.stream().collect(groupingBy(Card::getNumber));
        int score = getScore(colorGroup, numberGroup);
        System.out.println(score);


    }

    private static int getScore(Map<String, List<Card>> colorGroup, Map<Integer, List<Card>> numberGroup) {
        Map<Integer, Integer> countByNumber = numberGroup.entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue().size()));
        List<Integer> sortedCounts = new ArrayList<>(countByNumber.values());
        Collections.sort(sortedCounts);
        if (colorGroup.size() == 1 && isConsecutive(numberGroup.keySet()) && numberGroup.keySet().size() == 5) {
            int maxNumber = numberGroup.keySet().stream().max(Integer::compareTo).orElse(0);
            return 900 + maxNumber;
        } else if (sortedCounts.equals(List.of(1, 4))) {
            int fourCardNumber = countByNumber.entrySet().stream().filter(entry -> entry.getValue() == 4)
                    .map(Entry::getKey)
                    .findFirst()
                    .orElse(0);
            return 800 + fourCardNumber;
        } else if (sortedCounts.equals(List.of(2, 3))) {
            int tripleCardNumber = countByNumber.entrySet().stream().filter(entry -> entry.getValue() == 3)
                    .map(Entry::getKey)
                    .findFirst()
                    .orElse(0);
            int pairCardNumber = countByNumber.entrySet().stream().filter(entry -> entry.getValue() == 2)
                    .map(Entry::getKey)
                    .findFirst()
                    .orElse(0);
            return 700 + tripleCardNumber * 10 + pairCardNumber;
        } else if (colorGroup.size() == 1 && !isConsecutive(numberGroup.keySet())) {
            int maxNumber = numberGroup.keySet().stream().max(Integer::compareTo).orElse(0);
            return 600 + maxNumber;
        } else if (colorGroup.size() != 1 && isConsecutive(numberGroup.keySet()) && numberGroup.keySet().size() == 5) {
            int maxNumber = numberGroup.keySet().stream().max(Integer::compareTo).orElse(0);
            return maxNumber + 500;
        } else if (sortedCounts.equals(List.of(1, 1, 3))) {
            int tripleCardNumber = countByNumber.entrySet().stream().filter(entry -> entry.getValue() == 3)
                    .map(Entry::getKey)
                    .findFirst()
                    .orElse(0);
            return 400 + tripleCardNumber;
        } else if (sortedCounts.equals(List.of(1, 2, 2))) {
            List<Integer> pairs = countByNumber.entrySet().stream()
                    .filter(entry -> entry.getValue() == 2)
                    .map(Entry::getKey)
                    .collect(Collectors.toList());

            return 300 + Math.max(pairs.get(0), pairs.get(1)) * 10 + Math.min(pairs.get(0), pairs.get(1));
        } else if (sortedCounts.equals(List.of(1, 1, 1, 2))) {
            int pairNumber = countByNumber.entrySet().stream().filter(entry -> entry.getValue() == 2)
                    .map(Map.Entry::getKey).findFirst().orElse(0);
            return 200 + pairNumber;
        } else {
            int maxNumber = numberGroup.keySet().stream().max(Integer::compareTo).orElse(0);
            return 100 + maxNumber;
        }
    }

    private static boolean isConsecutive(Set<Integer> integers) {
        List<Integer> numbers = integers.stream().sorted().collect(toList());
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) + 1 != numbers.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static class Card {
        private String color;
        private int number;

        public Card(String color, int number) {
            this.color = color;
            this.number = number;
        }

        public String getColor() {
            return color;
        }

        public int getNumber() {
            return number;
        }
    }


}