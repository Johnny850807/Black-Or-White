package mvc;

public class Log {
	public static void d(String message){
		System.out.println(message);
	}
	public static void e(String message){
		System.out.println( new Exception(message) );
	}
}
