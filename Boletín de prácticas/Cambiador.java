package x64;

import java.util.concurrent.Semaphore;

public class Cambiador extends Thread {
	private Semaphore peaton;
	private Semaphore NorteSur;
	private Semaphore EsteOeste;
	
	public Cambiador() {
		this.peaton = new Semaphore(0);
		this.NorteSur = new Semaphore(1);
		this.EsteOeste = new Semaphore(0);
	}
	
	public void pasoNorteSur() {
		try {
			this.NorteSur.acquire();
			System.out.println("Norte-Sur en verde");
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.NorteSur.release();
		System.out.println("Norte-Sur en rojo");
	}
	
	public void pasoEsteOeste() {
		try {
			this.EsteOeste.acquire();
			System.out.println("Este-Oeste en verde");
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.EsteOeste.release();
		System.out.println("Este-Oeste en rojo");
	}
	
	public void pasoPeatones() {
		try {
			this.peaton.acquire();
			System.out.println("Peatones en verde");
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.peaton.release();
		System.out.println("Peatones en rojo");
	}
}
