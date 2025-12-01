public class NextGreater {
    public static void main(String[] args){
        int[] arr = {5,15,10,8,6,12,7};
        nextGreater(arr);
    }
    public static void nextGreater(int[] arr){
        for(int i = 0; i< arr.length; i++){
            int ng = -1;
            for(int j = i+1; j < arr.length; j++){
                if(arr[j] > arr[i]){
                    ng = arr[i];
                    break;
                }
            }
            System.out.println(ng + " ");
        }
    }
}
