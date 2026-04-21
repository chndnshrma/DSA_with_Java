public class patterns {
    public static void main(String[] args) {
        printPattern10();
        
    }
    public static void printPattern1() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j< 5; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void printPattern2() {
        for (int i = 0; i< 5; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        } 
    }

    public static void printPattern3() {
        for (int i = 1; i <= 5; i++) {
            for(int j = 1; j <= i; j++) {
                System.out.print (j + " ");
            }
            System.out.println();
        }
    }

    public static void printPattern4() {
        for (int i = 1; i <= 5; i++) {
            for(int j = 1; j <= i; j++) {
                System.out.print (i + " ");
            }
            System.out.println();
        }
    }
    //row - i + 1
    public static void printPattern5() {
        for (int i = 1; i <= 5; i++) {
            for(int j = 0; j < 5-i+1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void printPattern6() {
        for (int i = 1; i <= 5; i++) {
            for(int j = 1; j <= 5-i+1; j++) {
                System.out.print(j+ " ");
            }
            System.out.println();
        }
    }

    public static void printPattern7() {
        for (int i = 0; i< 5; i++) {
            for (int j = 0; j < 5 - i -1; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2*i+1; j++) {
                System.out.print("*");
            }
            for (int j = 0; j < 5 - i -1; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }    
    }

    public static void printPattern8() {
        for (int i = 0; i< 5; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2*5 - (2*i+1); j++) {
                System.out.print("*");
            }
            System.out.println();
        }    
    }

    public static void printPattern9() {
        printPattern7();
        printPattern8();   
    }

    public static void printPattern10() {
        for (int i = 1; i <= 2*5-1;i ++) {
            int stars = i;
            if (i > 5) stars = 2*5 - i;
            for (int j = 1; j <= stars ; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}