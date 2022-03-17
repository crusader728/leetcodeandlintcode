package withJava.crusader728.leetcode.pq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class CampusBikes1057 {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        Comparator<Info> distComparator = new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                int distResult = Integer.compare(o1.dist, o2.dist);
                if(distResult != 0) {
                    return distResult;
                } 

                int wIdCompare = Integer.compare(o1.workerId, o2.workerId);
                if(wIdCompare != 0) {
                    return wIdCompare;
                }

                return Integer.compare(o1.bikeId, o2.bikeId);
            }
        };
        List<List<Info>> workerToBikerDist = new ArrayList<>();
        for(int i = 0; i < workers.length; ++i) {
            workerToBikerDist.add(new ArrayList<>());
            for(int j = 0; j < bikes.length; ++j) {
                workerToBikerDist.get(i).add(new Info(i, j, getDist(workers[i], bikes[j])));
            }
            Collections.sort(workerToBikerDist.get(i), distComparator);
        }
        Set<Integer> takenBikes = new HashSet<>();
        Set<Integer> assignedWorkers = new HashSet<>();
        int[] result = new int[workers.length];
        int[] pivots = new int[workers.length];
        PriorityQueue<Info> pq = new PriorityQueue<>(distComparator);
        for(int i = 0; i < pivots.length; ++i) {
            pq.offer(workerToBikerDist.get(i).get(pivots[i]++));
        }
        while(!pq.isEmpty()) {
            Info head = pq.poll();
            int wId = head.workerId;
            int bId = head.bikeId;
            if(assignedWorkers.contains(wId) || takenBikes.contains(bId)) {
                Info next = workerToBikerDist.get(wId).get(pivots[wId]++);
                pq.add(next);
            } else {
                assignedWorkers.add(wId);
                takenBikes.add(bId);
                result[wId] = bId;
            }
        }
        return result;

    }

    private int getDist(int[] is, int[] is2) {
        return Math.abs(is[0] - is2[0]) + Math.abs(is[1] - is2[1]);
    }

    private static class Info {
        int workerId;
        int bikeId;
        int dist;

        Info(int wId, int bId, int d) {
            this.workerId = wId;
            this.bikeId = bId;
            this.dist = d;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "workerId=" + workerId +
                    ", bikeId=" + bikeId +
                    ", dist=" + dist +
                    '}';
        }
    }

    public static void main(String[] args) {
        CampusBikes1057 campusBikes1057 = new CampusBikes1057();
        campusBikes1057.assignBikes(new int[][] {
                {0,0},
                {1,0},
                {2,0},
                {3,0},
                {4,0},
                {5,0},
                {6,0},
                {7,0}
        }, new int[][] {
                {0,999},
                {1,999},
                {2,999},
                {3,999},
                {4,999},
                {5,999},
                {6,999},
                {7,999}
        });
    }
}
