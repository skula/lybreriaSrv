import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import services.CommandService;

import com.skula.lybreria.models.Command;

public class Server {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		try {
			serverSocket = new ServerSocket(8889);
			System.out.println("Socket listening :8889");
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			try {
				socket = serverSocket.accept();
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());

				System.out.println("ip: " + socket.getInetAddress());
				Command cmd = (Command) ois.readObject();
				oos.writeObject(CommandService.exec(cmd));

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (ois != null) {
					try {
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (oos != null) {
					try {
						oos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
