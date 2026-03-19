import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // value -> index
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (seen.containsKey(need)) {
                int j = seen.get(need);
                // return smaller index first
                return new int[] { Math.min(i, j), Math.max(i, j) };
            }
            // store current value and its index
            seen.put(nums[i], i);
        }
        // Problem guarantees a solution, but include a fallback
        return new int[] {-1, -1};
    }
}
