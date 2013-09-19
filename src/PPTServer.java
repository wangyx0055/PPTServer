import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class PPTServer {
	private final static int RIGHT = 1;
	private final static int LEFT = 2;
	private final static int SHIFTF5 = 0;
	private final static int ESC = 3;
	private static int key;

	public static void main(String[] args) throws IOException,
			ClassNotFoundException, AWTException, InterruptedException {
		ServerSocket sSocket = new ServerSocket(2011);
		System.out.println("waiting a connection from the client");
		Robot robot = new Robot();
		
		do {
			Socket socket = sSocket.accept();
			System.out.println("recv a connection");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String str = in.readLine();
			System.out.println("the flag is " + str);

			switch (str) {

			case "shift5":
				robot.keyPress(KeyEvent.VK_SHIFT);
				Thread.sleep(20);
				robot.keyPress(KeyEvent.VK_F5);
				Thread.sleep(10);
				robot.keyRelease(KeyEvent.VK_F5);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				Thread.sleep(10);
				break;

			case "left":
				robot.keyPress(KeyEvent.VK_LEFT);
				Thread.sleep(10);
				robot.keyRelease(KeyEvent.VK_LEFT);
				Thread.sleep(10);
				break;

			case "right":
				robot.keyPress(KeyEvent.VK_RIGHT);
				Thread.sleep(10);
				robot.keyRelease(KeyEvent.VK_RIGHT);
				Thread.sleep(10);
				break;

			case "esc":
				robot.keyPress(KeyEvent.VK_ESCAPE);
				Thread.sleep(10);
				robot.keyPress(KeyEvent.VK_ESCAPE);
				Thread.sleep(10);
				break;

			default:
				break;
			}
			in.close();
			socket.close();
	
		} while (key != -1);
		System.out.println("exit the app");
		
	}
}