import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws IOException {
        // Crear el socket servidor
        ServerSocket socketServidor = new ServerSocket(9999);

        // Indicar al proceso que se quede a la espera de peticiones
        System.out.println("Servidor esperando peticiones...");

        // Aceptar el establecimiento de conexiones y obtención del socket
        Socket socketCliente = socketServidor.accept();

        // Abrir flujos de lectura / escritura
        InputStream inputStream = socketCliente.getInputStream();
        OutputStream outputStream = socketCliente.getOutputStream();

        // Intercambiar datos con el cliente
        String mensajeCliente = leerMensaje(inputStream);
        System.out.println("Mensaje del cliente: " + mensajeCliente);

        String mensajeServidor = "¡Hola cliente!";
        escribirMensaje(outputStream, mensajeServidor);

        // Cerrar el flujo de lectura / escritura y luego la conexión
        inputStream.close();
        outputStream.close();
        socketCliente.close();

        System.out.println("Conexión con el cliente cerrada.");
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