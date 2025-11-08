public class Student {
    int roll;
    int marks;
    String name;

    Student(int r, String n, int m){
        roll = r;
        name = n;
        marks = m;
    }
    int getRoll(){return roll;}
    int getMarks(){return marks;}
    String getName(){return name;}
}
