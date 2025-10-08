public class practice1{
    static void fun(Test t){
            t.print();
        }
    public static void main(String[] args){
        fun(()-> System.out.println("Hello"));
    }
}
interface Test{
    void print();
}