package pcd.lab01.hello;

public class Main {

	public static void main(String[] args) throws Exception {

		log("Hello from the main thread");
		
		log("Number of processors: " + Runtime.getRuntime().availableProcessors());
		//La classe Runtime dà informazioni generali sul Runtime, in questo caso
		// ci dice il numero di processori disponibili.
		
		log("Launching new threads...");

		var myThreadOne = new MyThread("ThreadOne");  //creo il thread
		myThreadOne.start();	//inizia a runnare ciò che c'è nel metodo run

		var myThreadTwo = new Thread(new MyRunnable("ThreadTwo"));
		//creo un oggetto Thread a cui passo questo task (classe Runnable)
		myThreadTwo.start();		
		
		var t0 = System.currentTimeMillis();
		
		log("Threads spawned at: " + t0);
		
		log("Waiting for their termination.");

		/* launch also a clock thread, showing time elapsed */
		
		var clock = new SimpleClock(1000);
		clock.start();
		
		/* main thread blocks until the other threads terminate */
		
		myThreadOne.join();
		myThreadTwo.join();

		/* Notify clock to stop (deferred cancellation) -- "stop" method is deprecated */
		
		clock.notifyStop();
		
		var t1 = System.currentTimeMillis();
		log("Completed at " + t1 + " (" + (t1 - t0) + " secs)");
		
	}

	private static void log(String msg) {
		System.out.println("[ " + System.currentTimeMillis() +   " ][ " + Thread.currentThread().getName()+ " ] " + msg); 
	}
	
}
