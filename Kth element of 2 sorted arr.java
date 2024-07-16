Solution 1: Brute force approach
class Solution {
    public long kthElement( int arr1[], int arr2[], int n, int m, int k) {
        ArrayList<Integer> arr3 = new ArrayList<>();
        
        int i = 0, j = 0;
        
        while(i < n && j < m){
            if(arr1[i] < arr2[j]){
                arr3.add(arr1[i++]);
            }else{
                arr3.add(arr2[j++]);
            }
        }
         
        // Add the remaining elements
        while(i < n){
            arr3.add(arr1[i++]);
        }
        
        while(j < m){
            arr3.add(arr2[j++]);
        }
        
        return arr3.get(k - 1); // 0 based indexing
    }
}
Time Complexity: O(m+n), where m and n are the sizes of the given arrays.
Reason: We traverse through both arrays linearly.

Space Complexity: O(m+n), where m and n are the sizes of the given arrays.
Reason: We are using an extra array of size (m+n) to solve this problem.

// Solution 2 : Extra space is removed 
class Solution {
    public long kthElement( int arr1[], int arr2[], int n, int m, int k) {
        int elem = -1;
        int cnt = 0;
        
        int i = 0, j = 0;
        
        while(i < n && j < m){
            if(arr1[i] < arr2[j]){
                if(cnt == k - 1){
                    elem = arr1[i];
                }
                cnt++;
                i++;
            }else{
                if(cnt == k - 1){
                    elem = arr2[j];
                }
                cnt++;
                j++;
            }
        }
        
        // Remaining elements
        while(i < n){
            if(cnt == k - 1) elem = arr1[i];
            cnt++;
            i++;
        }
        
        while(j < m){
            if(cnt == k - 1) elem = arr2[j];
            cnt++;
            j++;
        }
        return elem;
    }
}
Complexity Analysis
Time Complexity: O(m+n), where m and n are the sizes of the given arrays.
Reason: We traverse through both arrays linearly.

Space Complexity: O(1), as we are not using any extra space to solve this problem.
    
// Optimal soln - BINARY SEARCH
public static int kthElement(int arr1[], int arr2[], int n, int m, int k){
    // The first arr should be the smaller one
    if(n > m){
        return kthElement(arr2, arr1, m,n,k);
    }

    // Assign the value to left
    int left = k;

    //  Binary search - fix pointers 
    int low = Math.max(0, k - n), high = Math.min(k,m);

    while(low <= high){
        // Find mid1 and mid2
        int mid1 = (low + high)/2;
        int mid2 = left - mid1;

        // Initialize l1, l2, r1 and r2
        int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE;
        int r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

        if(mid1 - 1 >= 0){
            l1 = arr1[mid1 - 1];
        }

        if(mid2 - 1 >= 0){
            l2 = arr2[mid2 - 1];
        }

        if(mid1 < n){
            r1 = arr1[mid1];
        }
        if(mid2 < m){
            r2 = arr2[mid2];
        }

        // check for the validity condition
        if(l1 <= r2 && l2 <= r1){
            return Math.max(l1, l2);
        }
        else if (l1 > r2) high = mid1 - 1;
        else low = mid1 + 1;
    }
    return 0;
}

Time Complexity: O(log(min(m, n))), where m and n are the sizes of two given arrays.
Reason: We are applying binary search on the range [max(0, k-n2), min(k, n1)]. The range length <= min(m, n).

Space Complexity: O(1), as we are not using any extra space to solve this problem.
