Solution 1 - Brute force approach
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Brute force approach

        int n1 = nums1.length;
        int n2 = nums2.length;

        //Merging both the array into 3 arr and then returning the median
        List<Integer> nums3 = new ArrayList<>();
        int i = 0, j = 0;
        while(i < n1 && j < n2){
            if(nums1[i] < nums2[j]){
                nums3.add(nums1[i++]);
            }else if(nums1[i] > nums2[j]){
                nums3.add(nums2[j++]);
            }
        }

        // For all the remaining elements
        while(i < n1){
            nums3.add(nums1[i++]);
        }

        while(j < n2){
            nums3.add(nums2[j++]);
        }

        int n = n1 + n2;
        if(n % 2 == 1) return nums3.get(n / 2);
        
        double median = ((double)nums3.get(n /2) + (double)(nums3.get((n/2) - 1)))/2.0;
        return median;
    }
}

Solution 2: Better approach -> Excluding the extra space complexity
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Better soln
        int n1 = nums1.length;
        int n2 = nums2.length;
        int n = n1 + n2;

        int elem2 = n / 2;
        int elem1 = elem2 - 1;
        int idx1elem = -1, idx2elem = -1;

        int i = 0, j = 0;
        int cnt = 0;

        while(i < n1 && j < n2){
            if(nums1[i] < nums2[j]){
                if(cnt == elem1) idx1elem = nums1[i];
                if(cnt == elem2) idx2elem = nums1[i];
                cnt++;
                i++;
            }else{
                if(cnt == elem1) idx1elem = nums2[j];
                if(cnt == elem2) idx2elem = nums2[j];
                cnt++;
                j++;
            }
        }

        // Remaining elements
        while(i < n1){
            if(cnt == elem1) idx1elem = nums1[i];
            if(cnt == elem2) idx2elem = nums1[i];
            cnt++;
            i++;
        }

        while(j < n2){
            if(cnt == elem1) idx1elem = nums2[j];
            if(cnt == elem2) idx2elem = nums2[j];
            cnt++;
            j++;
        }

        if (n % 2 == 1) {
            return (double)idx2elem;
        }

        return (double)((double)(idx1elem + idx2elem)) / 2.0;
    }
}

Solution 3: Optimal solution -> Binary search
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Optimal solution -> Binary search
        int n1 = nums1.length;
        int n2 = nums2.length;

        // Make sure nums1 is the smaller array
        if (n1 > n2) return findMedianSortedArrays(nums2, nums1);

        int n = n1 + n2;
        // Calculate length of left half
        int left = (n1 + n2 + 1) / 2;

        // Place low and high pointers
        int low = 0, high = n1;  // keeping high on the last index of smaller array to reduce the search space

        // Find mid1 and mid2
        while (low <= high) {
            int mid1 = (low + high) / 2;
            int mid2 = left - mid1;

            // Calculate l1, l2, r1, and r2
            int l1 = (mid1 > 0) ? nums1[mid1 - 1] : Integer.MIN_VALUE;
            int l2 = (mid2 > 0) ? nums2[mid2 - 1] : Integer.MIN_VALUE;

            int r1 = (mid1 < n1) ? nums1[mid1] : Integer.MAX_VALUE;
            int r2 = (mid2 < n2) ? nums2[mid2] : Integer.MAX_VALUE;

            // Eliminate halves on the basis of conditions
            if (l1 <= r2 && l2 <= r1) {
                if (n % 2 == 1) {  // odd number of elements in the array
                    return Math.max(l1, l2);
                } else {
                    return ((double) Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                }
            } else if (l1 > r2) {
                high = mid1 - 1; // eliminate the right half
            } else {
                low = mid1 + 1; // eliminate the left half
            }
        }
        return 0; // dummy
    }
}
