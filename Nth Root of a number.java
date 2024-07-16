// Brute force approach
// Linear search
class Solution{
  public static long func(int b , int exp){
    long ans = 1;
    int base = b;

    while(exp > 0){
      if(exp % 2 == 1){ // odd exponent
        exp--;
        ans = ans * base;
      }else{  // even exponent
        exp /= 2;
        base = base * base;
    }
    return ans;
  }
}

public static int NthRoot(int n, int m){
  for(int i = 0; i <= m; i++){
    long val = func(i, n);
    if(val == (long)m){
      return i;
    }else if(val > (long)m){
      break;
    }
    return -1;
  }
}

Time Complexity: O(M), M = the given number.
Reason: Since we are using linear search, we traverse the entire answer space.

Space Complexity: O(1) as we are not using any extra space.

// Optimal solution 
Binary Search

class Solution{
  public static int NthRoot(int n, int m){
    int low = 1, high = m;
    int mid = (low + high)/2;

    int midN = func(mid, n, m);
    if(midN == 1){
       return mid;
    }else if(midN == 0){
      low = mid + 1;
    }else{
      high = mid - 1;
    }
  }
  return -1;
}

public static int func(int mid, int n, int m){
  long ans = 1;
  for(int i = 0; i <= n; i++){
    ans = ans * mid;
    if(ans > m) return 2; // val > m
  }
  if(ans == m) return 1;  // val == m
  return 0; // val < m
}

Time Complexity: O(logN), N = size of the given array.
Reason: We are basically using binary search to find the minimum.

Space Complexity: O(1)
Reason: We have not used any extra data structures, this makes space complexity, even in the worst case as O(1).
