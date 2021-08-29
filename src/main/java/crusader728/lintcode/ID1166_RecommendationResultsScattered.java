package java.crusader728.lintcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ID1166_RecommendationResultsScattered {
    public List<String> scatter(List<String> elements, int n) {
        // write your code here
        Queue<String> queueP = new LinkedList<String>();
        Queue<String> queueV = new LinkedList<String>();

        int firstP = -1;
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).charAt(0) == 'P') {
                if (firstP == -1) {
                    firstP = i;
                }
                queueP.offer(elements.get(i));
            } else {
                queueV.offer(elements.get(i));
            }
        }
        List<String> result = new ArrayList<>();

        while (firstP > 0) {
            firstP--;
            result.add(queueV.element());
            queueV.poll();
        }

        int step = 0;
        while (!queueP.isEmpty()) {
            result.add(queueP.element());
            queueP.poll();
            step = n;

            while (!queueV.isEmpty() && step > 1) {
                result.add(queueV.element());
                queueV.poll();
                step--;
            }

            if (step > 1) {
                break;
            }
        }
        return result;
    }
}
