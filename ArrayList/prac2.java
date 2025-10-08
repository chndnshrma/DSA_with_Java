import java.util.*;
class prac2 {
    public static void main(String[] args){
        ArrayList<Integer> num = new ArrayList<>();
        for(int i = 0; i<10; i++){
            num.add(i);
        }

        System.out.println(evenOdd(num));
    }
    public static ArrayList<ArrayList<Integer>> evenOdd(ArrayList<Integer> list) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        
        ArrayList<Integer> even = new ArrayList<>();
        ArrayList<Integer> odd = new ArrayList<>();
        
        for(Integer x: list){
            if(x % 2 == 0){
                even.add(x);
            }else{
                odd.add(x);
            }
        }
        result.add(even);
        result.add(odd);
        return result;
    }
}