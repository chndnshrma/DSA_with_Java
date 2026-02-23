import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        String str = "Chandan";
        int num = 29;

        System.out.println(printMax(arr));
        System.out.println(printMin(arr));
        System.out.println(Arrays.toString(revArray(arr)));
        revStr(str);
        System.out.println(isPrime(num));
        printPrime(num);
        System.out.println("Factorial : " + fact(5));
        for(int i = 0; i<10; i++) {
            System.out.print(fiboNum(i) + " ");
        }
        System.out.println();
        System.out.println(palindrome("malayalam"));
        countChar("Chandan");
        System.out.println();
        System.out.println(secondLargest(arr));
        System.out.println(Arrays.toString(removeDuplicate(arr)));
        System.out.println(Arrays.toString((mergeToSort(arr,arr))));
        System.out.println(binarySearch(arr,7));
    }
    //1.to find the max element of array
    public static int printMax(int[] arr){
        int max = arr[0];
        for(int i = 1; i<arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        return max;
    }
    //2.to find the min element of array
    public static int printMin(int[] arr){
        int min = arr[0];
        for(int i = 1; i<arr.length; i++){
            if(arr[i] < min){
                min = arr[i];
            }
        }
        return min;
    }
    //3.to reverse without extra space element of array
    public static int[] revArray(int[] arr){
        int start = 0;
        int end = arr.length-1;
        while(start<end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
        return arr;
    }
    //4. Reverse a string manually
    public static void revStr(String str){
        char[] chr = str.toCharArray();
        int start = 0;
        int end = chr.length -1;
        while(start<end){
            char temp = chr[start];
            chr[start] = chr[end];
            chr[end] = temp;

            start++;
            end--;
        }
        String reversed = new String(chr);
        System.out.println(reversed);
    }
    //5.to check prime number
    public static boolean isPrime(int num){
        if(num <= 1) return false;
        if(num == 2) return true;
        if(num % 2 == 0) return false;

        for(int i = 3; i <= Math.sqrt(num); i+=2){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
    //6.print all prime numbers upto n
    public static void printPrime(int n) {
        ArrayList<Integer> lst = new ArrayList<>();
        for(int i = 2; i<n; i++){
            if(isPrime(i)){
                lst.add(i);
            }
        }
        for(int e: lst){
            System.out.print(e + " ");
        }
        System.out.println();
    }
    //7. find the factorial of a number using recursion
    public static int fact(int n){
        if(n == 0 || n == 1) return 1;
        return n* fact(n-1);
    }
    //8. Find the Fibonacci number at position n
    public static int fiboNum(int n){
        if(n <= 1) return n;
        return fiboNum(n-1) + fiboNum(n-2);
    }
    //9. check if a string is palindrome
    public static boolean palindrome(String str){
        char[] chr = str.toLowerCase().toCharArray();
        for(int i = 0; i<chr.length/2; i++){
            int j = chr.length - 1 - i;
            if(chr[i] != chr[j]){
                return false;
            }
        }
        return true;
    }
    //10. To count the frequency of each char in a String
    public static void countChar(String str){
        char[] chr = str.toCharArray();
        int count = 1;
        for(int i = 0; i<chr.length; i++){
            if(chr[i] == '0'){
                continue;
            }
            for(int j = i+1; j<chr.length;j++){
                if(chr[i] == chr[j]){
                    count++;
                    chr[j] = '0';
                }
            }
            System.out.print(chr[i] + ":" + count + " ");
        }
    }
    //11.Find the second-largest element in an array
    public static int secondLargest(int[] arr){
        if(arr == null || arr.length < 2){
            throw new IllegalArgumentException("Array must have at least two elements!");
        }
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for(int num : arr){
            if(num > largest){
                secondLargest = largest;
                largest = num;
            }else if(num > secondLargest && num != largest ){
                secondLargest = num;
            }
        }
        return secondLargest;
    }
    //12. Remove duplicate from an array
    public static int[] removeDuplicate(int[] arr){
        if(arr.length == 0) return arr;
        Arrays.sort(arr);

        int uniqueCount = 1;
        for(int i = 1; i< arr.length; i++){
            if(arr[i] != arr[i-1]){
                uniqueCount++;
            }
        }
        int[] result = new int[uniqueCount];
        result[0] = arr[0];
        int index = 1;
        for(int i = 1; i<arr.length;i++){
            if(arr[i] != arr[i-1]) {
                result[index++] = arr[i];
            }
        }
        return result;
    }
    //13. Merge Sorted array into one sorted array
    public static int[] mergeToSort(int[] arr1, int[] arr2){
        int n1 = arr1.length;
        int n2 = arr2.length;
        int[] result = new int[n1+n2];
        int i =0, j = 0, k = 0;
            //traversing both arrays
        while(i<n1 && j<n2){
            if(arr1[i] <= arr2[j]){
                result[k++] = arr1[i++];
            }else{
                result[k++] = arr2[j++];
            }
        }
            //store remaining elements of the first array
        while(i < n1){
            result[k++] = arr1[i++];
        }
            //Storing remaining elements of the second array
        while(j<n2){
            result[k++] = arr2[j++];
        }
        return result;
    }
    //14.Binary search in sorted array
    public static int binarySearch(int[] arr, int target){
        int left = 0;
        int right = arr.length - 1;

        System.out.println("Searching for " + target + " in Array" );

        while(left <= right){
            int mid = (left+right)/2;
            if(arr[mid] == target){
                return mid;
            }
            if(arr[mid] < target){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return -1;
    }
}