package x64;

import java.util.concurrent.Semaphore;

public class Cambiador extends Thread {
	public static Semaphore peaton;
	public static Semaphore NorteSur;
	public static Semaphore EsteOeste;
	
	public static final int MAXNS = 4;
	public static final int MAXEO = 4;
	public static final int MAXP = 10;
	public static final int DEFAULT = 0;
	
	public Cambiador() {
		Cambiador.peaton = new Semaphore(Cambiador.DEFAULT);
		Cambiador.NorteSur = new Semaphore(Cambiador.DEFAULT);
		Cambiador.EsteOeste = new Semaphore(Cambiador.DEFAULT);
	}
	
	public void pasoNorteSur() {
		Cambiador.NorteSur = new Semaphore(Cambiador.MAXNS);
		
	}
	
	public void pasoEsteOeste() {
		Cambiador.EsteOeste = new Semaphore(Cambiador.MAXEO);
	}
	
	public void pasoPeatones() {
		Cambiador.peaton = new Semaphore(Cambiador.MAXP);
	}
	public void run() {
		/*
		 * Este método lo que hace es volver a crear los semaforos a unos con el numero de permisos igual
		 * al numero de coches o peatones que pueden  haber en la calzada
		 * cuanmdo acaba el tiempo  de un semaforo recoge todos sus permisos y lo cambia por uno  sin permisos disponibles
		 * y así mientras la variable global x no indique el final del programa
		 * */
		Vehiculo.lanzarVehiculos();
		//TODO lanzar peatones
		while(Main.x == true) {
			try {
				System.out.println("Norte-Sur en verde");
				this.pasoNorteSur();
				Thread.sleep(500);
				System.out.println("Norte-Sur en rojo");
				Cambiador.NorteSur.drainPermits(); //resetea permisos
				Cambiador.NorteSur = new Semaphore(Cambiador.DEFAULT);
				System.out.println("Este-Oeste en verde");
				this.pasoEsteOeste();
				Thread.sleep(500);
				System.out.println("Este-Oeste en rojo");
				Cambiador.EsteOeste.drainPermits();
				Cambiador.EsteOeste = new Semaphore(Cambiador.DEFAULT);
				System.out.println("Peatones en verde");
				this.pasoPeatones();
				Thread.sleep(500);
				System.out.println("Peatones en rojo");
				Cambiador.peaton.drainPermits();
				Cambiador.peaton = new Semaphore(Cambiador.DEFAULT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
