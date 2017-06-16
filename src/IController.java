/**
 * Created by Artem on 06.05.2017.
 */
public interface IController {
    void buttonConnectHandler(String IP);
    void buttonRegistrationHandler();
    void buttonRegistrationNewAccount(String login, String pass);
    void buttonLoginHandler(String login, String pass);
    void buttonSendHandler(String message);
    void connectErrorHandler();
    void activateViewHandler(IModel.ModelState state);
    void handleMessageFromServer(Message message);
    void buttonJoinHandler();
    void buttonCreateGameHandler();
    void buttonTurnOrientationHandler();
    void shipSelectHandler(IGame.ShipType type);
    void mouseClickedSpriteHandler(int coordX, int coordY);
    void buttonConnectToGameHandler(int indx);
    void cancelCreateGameButton();
    void checkBoxIsReadyHandler(boolean flag);
    void startGameButtonHandler();
    void mouseClickedSpriteOpponentHandler(int coordX, int coordY);
    void buttonFireHandler();
    IClickedController getHimselfGameBoardController();
    IClickedController getOpponentGameBoardController();
}
