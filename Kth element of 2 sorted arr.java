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
