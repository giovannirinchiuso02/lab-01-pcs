package pcd.lab01.hello;

import java.util.*;

public class SimpleClock extends Thread {

	private volatile boolean stopped;
	private int interval;
	
	public SimpleClock(int interval){
		super("clock");
		stopped = false;
		this.interval = interval;
	}
	
	public void run(){
		log("started.");
		while (!stopped) {
			log(new Date().toString());
			waitAbit();
		}
		log("stopped.");
	}
	
	public void notifyStop() {
		stopped = true;
		interrupt();
		//serve per svegliare thread, funziona solo se il thread è in attesa, se no non fa nulla
		//utile quando si ha bisogno per terminare un altro thread, di svegliarne un altro che è in attesa
	}
	
	private void waitAbit() {
		try {
			Thread.sleep(interval);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	private void log(String msg) {
		System.out.println("[ " + System.currentTimeMillis() +   " ][ " + getName()+ " ] " + msg); 
	}
}
