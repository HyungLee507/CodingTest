import java.util.*;
class Solution{
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a1, a2) -> {
            if (a1[0] == a2[0]) {
                return Integer.compare(a1[1], a2[1]);
            } else {
                return Integer.compare(a1[0], a2[0]);
            }
        });

        Queue<int[]> queue = new PriorityQueue<>((a1, a2) -> {
            return Integer.compare(a1[1], a2[1]);
        });
        int index = 0;
        int answer = 0;
        int endTime = 0;
        while (index < jobs.length || !queue.isEmpty()) {
            while (index < jobs.length && jobs[index][0] <= endTime) {
                queue.add(jobs[index]);
                index++;
            }
            if (queue.isEmpty()) {
                endTime += (jobs[index][0] - endTime + jobs[index][1]);
                answer += jobs[index][1];
                index++;
            } else {
                int[] job = queue.poll();
                answer += (job[1] + endTime - job[0]);
                endTime += job[1];
            }
        }
        return answer / jobs.length;
    }
    
}