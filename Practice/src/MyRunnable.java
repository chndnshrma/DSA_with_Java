public class MyRunnable implements Runnable{
    private final String text;

    public MyRunnable(String text) {
        this.text = text;
    }

    @Override
    public void run() {
        for(int i = 1;i<=5;i++){
            try{
               Thread.sleep(1000);
                System.out.println(text);
            }catch(InterruptedException e){
                System.out.println("Thread was interrupted!");
            }
        }
    }
}
