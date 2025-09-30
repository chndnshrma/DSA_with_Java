import java.util.Iterator;
import java.util.LinkedList;

class practice1{
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        System.out.println(list.remove(20));
    }
    public static int josephor(int n, int k){
        LinkedList<Integer> list = new LinkedList<>();

        for(int i = 0; i<n; i++){
            list.add(i);
        }

        Iterator<Integer> it = list.iterator();

        while(list.size() > 1){
            int count = 0;
            while(count < k){
                while(it.hasNext() && count < k){
                    it.next();
                    count++;
                }
                if(count < k){
                    it = list.iterator();
                    it.next();
                    count++;
                }
            }
            it.remove();
        }
        return list.getFirst();
    }
}

class myDS{
    LinkedList<Integer> list = new LinkedList<>();

    void add(int x){
        list.add(x);
    }

    void removeAndPrint(int x){
        Iterator it = list.iterator();
        while(it.hasNext()){
            Integer n = (Integer)it.next();
            if(n.equals(x)){
                it.remove();
            }else{
                System.out.println(n + " ");
            }
        }
        System.out.println();
    }
}