import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {
    public static void main(String[] args) {
        // System.out.println("Hello world!");

        // Use of Server Socket

        // Define port number
        final int port = 8080;
        // Bind the ServerSocket to the Port Number
        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Running server at port "+port);

            while (true) {
                Socket socket = serverSocket.accept();
                handleRequest(socket);
            }
        } catch (IOException e){
            System.out.println("Error handling Request "+e.getMessage());
        }
    }

    // Socket Class
    private  static void handleRequest(Socket socket){
        try(InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream()){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = bufferedReader.readLine();
            String[] parts = line.split(" ");

            //The first part is GET method and second part is the URL path. Consider it invalid if there are less than two parts
            String requestMethod = parts[0];
            String path = parts[1];

            if("GET".equalsIgnoreCase(requestMethod) && "/messages".equalsIgnoreCase(path)){
                writeResponse(outputStream);
            }
        } catch (IOException e){
            System.out.println("Failed to handel " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e){
                System.out.println("Error closing Socket: "+e.getMessage());
            }
        }
    }

    // Send Response and Test URL
    private static void writeResponse(OutputStream outputStream) throws IOException {
        String message = "Hello From Deba951";
        String httpResponse = """
                HTTP/1.1 200 OK
                Content-Type: text/plain
                Content-Length: """ + message.length() + "\n\n" +
                message;
        outputStream.write(httpResponse.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}