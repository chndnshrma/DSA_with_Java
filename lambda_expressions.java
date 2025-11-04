import java.util.*;
import java.io.*;
public class lambda_expressions {
    public static void main(String[] args) {
        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(2);
        lst.add(3);

        lst.stream().forEach(e -> System.out.println(e));
    }
}