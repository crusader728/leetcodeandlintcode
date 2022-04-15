package withJava.crusader728.leetcode.binarysearch;

import java.util.ArrayList;
import java.util.List;

public class SnapshotArray1146 {
    private static class SnapshotArray {
        List<List<Info>> array;
        int currentSnapshot;
        public SnapshotArray(int length) {
            array = new ArrayList<>();    
            for(int i = 0; i < length; ++i) {
                array.add(new ArrayList<>());
            }
            currentSnapshot = 0;
        }
        
        public void set(int index, int val) {
            List<Info> infos = array.get(index);
            if(infos.isEmpty()) {
                Info info = new Info();
                info.snapshotId = currentSnapshot;
                info.value = val;
                infos.add(info);
            } else {
                Info info = infos.get(infos.size() - 1);
                if(info.snapshotId == currentSnapshot) {
                    info.value = val;
                } else {
                    Info newInfo = new Info();
                    newInfo.snapshotId = currentSnapshot;
                    newInfo.value = val;
                    infos.add(newInfo);
                }
            }
        }
        
        public int snap() {
            int ans = currentSnapshot;
            currentSnapshot++;
            return ans;
        }
        
        public int get(int index, int snap_id) {
            List<Info> infos = array.get(index);
            if(infos.isEmpty()) {
                return 0;
            } else {
                int l = 0;
                int r = infos.size();
                while(l < r) {
                    int mid = l + (r - l) / 2;
                    if(infos.get(mid).snapshotId < snap_id) {
                        l = mid + 1;
                    } else {
                        r = mid;
                    }
                }
                if(l == infos.size()) {
                    return infos.get(l - 1).value;
                } else if(infos.get(l).snapshotId > snap_id && l != 0) {
                    return infos.get(l - 1).value;
                } else if(infos.get(l).snapshotId > snap_id) {
                    return 0;
                } else {
                    return infos.get(l).value;
                }
            }
        }

        private static class Info {
            int snapshotId;
            int value;
        }
    }
}
