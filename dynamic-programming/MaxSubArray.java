/**
 * ╔══════════════════════════════════════════════════════════════════╗
 * ║ LeetCode #53 — Maximum Subarray ║
 * ║ Topic : Dynamic Programming / Greedy / Arrays ║
 * ║ Diff : Medium ║
 * ║ Pattern : Kadane's Algorithm ║
 * ╚══════════════════════════════════════════════════════════════════╝
 *
 * PROBLEM:
 * Given an integer array nums, find the subarray with the largest
 * sum and return its sum.
 *
 * EXAMPLE:
 * Input: nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 * Output: 6
 * Explanation: [4, -1, 2, 1] has the largest sum = 6.
 *
 * CORE DP INTUITION (Kadane's Algorithm):
 * At each index i, ask: "Is it better to...
 * (a) extend the current subarray? → currentSum + nums[i]
 * (b) start a fresh subarray at i? → nums[i]
 * Pick whichever is larger."
 *
 * Recurrence:
 * currentSum[i] = max(nums[i], currentSum[i-1] + nums[i])
 * answer = max over all currentSum[i]
 *
 * COMPLEXITY:
 * Time : O(n) — single pass
 * Space : O(1) — no extra array needed
 */
public class MaxSubArray {

    // ─── Solution (Kadane's Algorithm) ───────────────────────────────
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Extend current subarray OR start fresh at i
            currentSum = Math.max(nums[i] + currentSum, nums[i]);
            maxSum = Math.max(currentSum, maxSum);
        }

        return maxSum;
    }

    // ─── Tests ────────────────────────────────────────────────────────
    public static void main(String[] args) {
        MaxSubArray sol = new MaxSubArray();

        int[][] inputs = {
                { -2, 1, -3, 4, -1, 2, 1, -5, 4 }, // → 6 (subarray: [4,-1,2,1])
                { 1 }, // → 1 (single element)
                { 5, 4, -1, 7, 8 }, // → 23 (whole array)
                { -3, -2, -1 }, // → -1 (all negative → max single element)
                { -2, -1 }, // → -1
        };
        int[] expected = { 6, 1, 23, -1, -1 };

        System.out.println("Max Subarray (Kadane's) — Test Results:");
        System.out.println("─".repeat(50));
        for (int i = 0; i < inputs.length; i++) {
            int result = sol.maxSubArray(inputs[i]);
            String status = result == expected[i] ? "✅ PASS" : "❌ FAIL";
            System.out.printf("  %s  got=%d  expected=%d%n", status, result, expected[i]);
        }
    }
}

/*
 * ─── ALTERNATIVE APPROACHES ───────────────────────────────────────────
 *
 * 1. DIVIDE & CONQUER — O(n log n), O(log n) space
 * Split array in half; max subarray is either:
 * - entirely in left half
 * - entirely in right half
 * - crosses the midpoint
 *
 * 2. BRUTE FORCE — O(n²), O(1) space
 * Try every pair (i, j) and compute sum. Too slow for large inputs.
 *
 * ─── FOLLOW-UP VARIATIONS ──────────────────────────────────────────────
 *
 * • Print the actual subarray (not just the sum):
 * Track start, end, tempStart indices.
 *
 * • Maximum Product Subarray (LC #152):
 * Track both maxProduct and minProduct (because negative × negative = positive)
 *
 * • Circular array maximum subarray (LC #918):
 * max(kadane(normal), totalSum - kadane(inverted))
 *
 * ─── INTERVIEW TIPS ────────────────────────────────────────────────────
 *
 * Q: Why does resetting to nums[i] when nums[i] > currentSum + nums[i] work?
 * A: If the accumulated sum is negative, it only HURTS future elements.
 * It's always better to start fresh than to carry a negative prefix.
 *
 * Q: Does this work for all-negative arrays?
 * A: Yes! Initializing maxSum = nums[0] handles it — we return the
 * least-negative element.
 */
