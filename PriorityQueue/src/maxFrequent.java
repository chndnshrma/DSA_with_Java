
public class maxFrequent {
    public static void main(String[] args) {
        int arr[] = {4,2,5,2,3,5,2,7};

        int start = 0;
        int end = arr.length - 1;

        while(start < end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }

        for(int i : arr){
            System.out.print(i + " ");
        }
    }
}

