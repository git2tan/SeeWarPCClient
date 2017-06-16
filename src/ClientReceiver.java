import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Artem on 06.05.2017.
 */
public class ClientReceiver implements Runnable{
    Decoder decoder;
    private ServerMessageHandler handler;
    private Socket socket;
    public ClientReceiver(ServerMessageHandler handler, Socket socket){
        this.handler = handler;
        this.socket = socket;
        this.decoder = new Decoder();
    }

    @Override
    public void run() {
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String fromServer;
            while(true){
                fromServer = r.readLine();
                if(fromServer != null){
                    System.out.println("Получил от сервера: " + fromServer);
                    Message message = decoder.decodeString(fromServer);
                    if(!message.isEmpty())
                        handler.handleMessage(message);               //если сообщение удачно расшифрованно - отправляем его клиенту (то есть геймеру) вызывая обработчик
                }else{
                    break;
                }
            }
        } catch (IOException e) {
            //добавить обработку отваливания
            e.printStackTrace();
        }
    }
}
