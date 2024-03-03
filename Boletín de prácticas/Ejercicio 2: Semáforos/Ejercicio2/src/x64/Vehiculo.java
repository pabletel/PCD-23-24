package x64;

public class Vehiculo extends Thread {
	public static volatile int nv = 0;
	private Direccion direccion;
	public static volatile Vehiculo[] vehiculos = new Vehiculo[50];
	
	public Vehiculo(Direccion direccion) {
		this.direccion = direccion;
	}
	
	public Direccion getDireccion() {
		return this.direccion;
	}
	
	private void setDireccion(Direccion direccion) {
		this.direccion= direccion;
	}
	
	private void chageDireccion() {
		if(this.getDireccion()==Direccion.NORTESUR) {
			this.setDireccion(Direccion.ESTEOESTE);
		}else {
			this.setDireccion(Direccion.NORTESUR);
		}
	}
	
	public static void lanzarVehiculos() {
		for(int i=0;i<vehiculos.length;i++) {
			if(i%2==0) {
				vehiculos[i] = new Vehiculo(Direccion.NORTESUR);
			}else {
				vehiculos[i] = new Vehiculo(Direccion.ESTEOESTE);
			}
		}
	}
	
	private void cruzar() throws InterruptedException {
		Cambiador.NorteSur.acquire();
		System.out.println("vehiculo cruzando");
		Vehiculo.nv++;
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Vehiculo.nv--;
		Cambiador.NorteSur.release();
	}
	
	public void run() {
		/*
		 * En este método el hilo vehiculo intenta cruzar, espera 7 segundos cambia la direccion y vuelve 
		 * a intentar cruzar
		 * */
		try {
			this.cruzar();
			this.wait(700);
			this.chageDireccion();
			this.cruzar();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
