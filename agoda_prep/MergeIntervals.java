package agoda_prep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//merge intervals
//Approach: sort by start and merge into an output list.
public class MergeIntervals {

    public static void main(String[] args) {
        MergeIntervals m = new MergeIntervals();
        int[][] result = m.merge(new int[][]{{1,3},{2,6},{8,10},{15,18}});
        System.out.println(Arrays.deepToString(result));
    }
    public int[][] merge(int[][] intervals) {
        if(intervals.length == 0){
            return new int[0][];
        }
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));
        List<int[]> res = new ArrayList<>();
        int[] current = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= current[1]) {
                current[1] = Math.max(current[1], intervals[i][1]);
            } else {
                res.add(current);
                current = intervals[i];
            }
        }
        res.add(current);
        return res.toArray(new int[0][]);
    }
}
