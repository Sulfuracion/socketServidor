import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException {
        // Crear el socket cliente
        Socket socketCliente = new Socket("localhost", 9999);

        // Abrir flujos de lectura / escritura
        InputStream inputStream = socketCliente.getInputStream();
        OutputStream outputStream = socketCliente.getOutputStream();

        // Intercambiar datos con el servidor
        System.out.println("Introduzca un mensaje para el servidor: ");
        String mensajeCliente = new Scanner(System.in).nextLine();
        escribirMensaje(outputStream, mensajeCliente);

        String mensajeServidor = leerMensaje(inputStream);
        System.out.println("Mensaje del servidor: " + mensajeServidor);

        // Cerrar el flujo de lectura / escritura y luego la conexión
        inputStream.close();
        outputStream.close();
        socketCliente.close();
    }

    private static String leerMensaje(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[1024];
        int bytesLeidos = inputStream.read(bytes);
        return new String(bytes, 0, bytesLeidos);
    }

    private static void escribirMensaje(OutputStream outputStream, String mensaje) throws IOException {
        outputStream.write(mensaje.getBytes());
        outputStream.flush();
    }
}
