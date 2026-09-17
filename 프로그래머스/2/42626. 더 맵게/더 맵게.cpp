#include <vector>
#include <iostream>
#include <queue>
#include <functional>
#include <string>

using namespace std;

int solution(vector<int> scoville, int K) {
    priority_queue<int, vector<int>, greater<int>> pq(
        scoville.begin(), scoville.end()
    );

    int answer = 0;

    while (!pq.empty() && pq.top() < K) {
        if (pq.size() < 2) {
            answer = -1;
            break;
        }

        int first = pq.top();
        pq.pop();

        int second = pq.top();
        pq.pop();

        pq.push(first + second * 2);
        answer++;
    }
    return answer;
}