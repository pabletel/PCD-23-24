package x64;

public class Main {

	public static volatile Cambiador c = new Cambiador();
	public static volatile boolean  x = true;
	
	public static void main(String[] args) {
		
		Main.c.start();
		

	}

}
