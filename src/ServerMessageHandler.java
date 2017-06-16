/**
 * Created by Artem on 06.05.2017.
 */
public class ServerMessageHandler {
    private IController controller;
    public ServerMessageHandler(IController controller){
        this.controller = controller;
    }

    public void handleMessage(Message message){
        controller.handleMessageFromServer(message);
    }
}
