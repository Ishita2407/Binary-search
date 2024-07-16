class Solution {
    static int upperBound(int arr[], int x, int n){
        int low = 0;
        int high = n - 1;
        int ans = n;
        
        while(low <= high){
            int mid = low + (high - low) / 2;
            
            if(arr[mid] > x){
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
    
    static int cntSmallEqual(int matrix[][], int m, int n, int x){
        int cnt = 0;
        for(int i = 0; i < m; i++){
            cnt += upperBound(matrix[i], x, n);
        }
        return cnt;
    }
    
    int median(int matrix[][], int R, int C) {
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        
        for(int i = 0; i < R; i++){ // Fix the loop to iterate over rows
            low = Math.min(low, matrix[i][0]); // min elem -> min elem of the 1st col
            high = Math.max(high, matrix[i][C - 1]); // max elem -> max elem of the last col
        }
        
        int req = (R * C) / 2;
        
        while(low <= high){
            int mid = (low + high) / 2;
            int smaller = cntSmallEqual(matrix, R, C, mid);
            if(smaller <= req) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return low;
    }
}
Time Complexity: O(log(10^9)) X O(M(logN)), where M = number of rows in the given matrix, N = number of columns in the given matrix

Reason: Our search space lies between [0, 10^9] as the min(matrix) can be 0 and the max(matrix) can be 10^9. We are applying binary search in this search space and it takes O(log(10^9)) time complexity. Then we call countSmallEqual() function for every ‘mid’ and this function takes O(M(logN)) time complexity.

Space Complexity : O(1) as we are not using any extra space
