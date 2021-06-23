package com.tokyoghoul;

import java.util.*;

public class Main {
    private static <T> Collection<T> removeDuplicates(Collection<T> collection) {
        return new HashSet<>(collection);
    }

    private static <K, V> Map<V, Collection<K>> reverseKeys(Map<K, V> map) {
        Map<V, Collection<K>> resultMap = new HashMap<>();
        for (Map.Entry<K, V> entries : map.entrySet()) {
            V value = entries.getValue();
            K key = entries.getKey();
            if (!resultMap.containsKey(value)) {
                resultMap.put(value, new ArrayList<>());
            }
            resultMap.get(value).add(key);
        }

        return resultMap;
    }

    private static String[] splitPlayerAndScore(String str) {
        int spaceIndex = str.indexOf(' ');
        return new String[]{str.substring(0, spaceIndex), str.substring(spaceIndex + 1)};
    }

    private static String getWinner(String[] playerScores) {
        String winnerName = null;
        int winnerScore = Integer.MIN_VALUE;
        Map<String, Integer> scoreTable = new HashMap<>();

        for (String playerScore : playerScores) {
            String[] playerScoreArray = splitPlayerAndScore(playerScore);
            String player = playerScoreArray[0];
            int score = Integer.parseInt(playerScoreArray[1]);

            if (!scoreTable.containsKey(player)) {
                scoreTable.put(player, 0);
            }

            int newTotalScore = scoreTable.get(player) + score;
            scoreTable.put(player, newTotalScore);

            if (winnerScore < newTotalScore) {
                winnerName = player;
                winnerScore = newTotalScore;
            }
        }
        return winnerName;
    }

    public static void main(String[] args) {
        String[] test = new String[]{"Ivan 5", "Petr 3", "Alex 10", "Petr 8", "Ivan 6", "Alex 5", "Ivan 1", "Petr 5", "Alex 1"};
        System.out.println(getWinner(test));
    }
}
