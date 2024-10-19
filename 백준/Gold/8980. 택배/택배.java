

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, C;
    static List<Parcel> parcels;

    static class Car {
        int now;
        int count;
        Queue<Parcel> parcels;

        public Car(int now) {
            this.now = now;
            count = 0;
            parcels = new ArrayDeque<>();
        }

        // 짐 실기
        private void load(Parcel parcel, int size) {
            if (size != parcel.count) {
                parcel.count = size;
            }
            this.count += size;
            parcels.add(parcel);
        }

        // 짐 내리기
        private void drop(int town) {
            int size = this.parcels.size();
            for (int i = 0; i < size; i++) {
                Parcel poll = parcels.poll();
                if (poll.to != town) {
                    this.parcels.add(poll);
                } else {
                    this.count -= poll.count;
                }
            }
        }
    }

    static class Parcel implements Comparable<Parcel> {
        int from;
        int to;
        int count;

        public Parcel(int from, int to, int count) {
            this.from = from;
            this.to = to;
            this.count = count;
        }

        @Override
        public int compareTo(Parcel o) {
            if (this.to != o.to) {
                return Integer.compare(this.to, o.to);
            }
            return Integer.compare(this.from, o.from);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        parcels = new ArrayList<>();
        int M = Integer.parseInt(bf.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            parcels.add(new Parcel(from, to, count));
        }
        Collections.sort(parcels);
        int box[] = new int[N + 1];
        int max, possible, total = 0;
        for (int i = 0; i < M; i++) {
            int from = parcels.get(i).from;
            int to = parcels.get(i).to;
            int cnt = parcels.get(i).count;
            max = 0;
            // 지나가는 구간에 이미 실린 박스의 최댓값
            for (int j = from; j < to; j++) {
                max = Math.max(max, box[j]);
            }
            // 실을 수 있는 박스 수
            possible = Math.min(C - max, cnt);
            total += possible;
            for (int j = from; j < to; j++) {
                box[j] += possible;
            }
        }
        // 배송할 수 있는 최대 박스 수
        System.out.println(total + box[N]);

    }

}
