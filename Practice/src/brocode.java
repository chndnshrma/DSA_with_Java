import java.util.HashMap;

public class brocode {
    public static void main(String[] args){
        HashMap<String, Double> map = new HashMap<>();
        map.put("Banana",2.5);
        map.put("Apple",3.5);
        map.put("Orange",5.6);
        map.put("Coconut",4.2);

        if(map.containsKey("Pineapple")){
            System.out.println(map.get("Apple"));
        }else{
            System.out.println("Key not found!");
        }
    }
}
