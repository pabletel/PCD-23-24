package x64;

public class Main {

	public static volatile Cambiador c = new Cambiador();
	public static volatile boolean  x = true; //variable para confirmar la continuacion del programa, será false cuando termine
	
	public static void main(String[] args) {
		
		Main.c.start();
		

	}

}
