package x64;

public class Vehiculo extends Thread {
	
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
	
	private void cruzar() {
		System.out.println("vehiculo cruzando");
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.cruzar();
		try {
			this.wait(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.chageDireccion();
		this.cruzar();
		
	}
	
}
