
import java.lang.*;

public class ThreadDemo implements Runnable {

   Thread t;
   ThreadDemo() {
    
      // thread created
      t = new Thread(this, "Admin Thread");
     
      // prints thread created
      System.out.println("thread  = " + t);
      
      // this will call run() function
      System.out.println("Calling run() function... ");
      t.start();
      System.out.println("This should execute before...");
   }

   public void run() {
	  try {
	  Thread.sleep(4000);
      System.out.println("This");
	  }
	  catch(Exception e) {}
}

   public static void main(String args[]) {
      new ThreadDemo();
   }
}