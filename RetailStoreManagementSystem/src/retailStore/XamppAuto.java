package retailStore;

import java.io.IOException;

public class XamppAuto {
	static Process process;
	
	static void startXamppServer() {
		try {
			process = Runtime.getRuntime().exec("C:\\xampp\\xampp_start.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void closeXamppServer() {
		try {
			process = Runtime.getRuntime().exec("C:\\xampp\\xampp_stop.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
