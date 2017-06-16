import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Artem on 06.05.2017.
 */
public interface IModel {
    public String getLogin();
    public String getPassword();
    public ModelState getState();
    public boolean isOtherView();
    public String getDefaultIP();
    public String getCurrentIP();
    public ConnectionState getConnectionState();
    public ModelState getCurrentState();
    public Stack<ChatMessage> getLobbyMessage();
    public RegistrationState getRegistrationState();
    public IGame getGame();
    public boolean isPrepareOrientationShipHorizontal();
    public boolean isAllShipOnBoard();
    public IGame.ShipType getSelectedTypeShip();
    public ArrayList<ServerGame> getListOFServersGames();
    public boolean isNeedRefreshListOfGame();
    public boolean isOpponentReady();
    public boolean isThisReady();
    public boolean isNowMyTurn();
    public boolean isPrepareToShot();
    public int getCoordX();
    public int getCoordY();
    public int getOpponentShipCount(IGame.ShipType type);
    public boolean isMiss();
    public boolean isHit();
    enum ConnectionState{
        offline,
        tryToConnect,
        cantConnectToServer,
        online,
        cantLogin,
        isAuthorizedOnTheServer
    }
    enum ModelState{
        startFrame,
        loginFrame,
        registrationFrame,
        mainMenuFrame,
        lobbyFrame,
        createGameFrame,
        connectToGameFrame,
        inGameState
    };
    enum RegistrationState{
        none,
        success,
        forbidden
    }
}
