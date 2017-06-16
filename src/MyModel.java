import java.util.*;

/**
 * Created by Artem on 06.05.2017.
 */
public class MyModel extends Observable implements IModel{

    public MyModel(){
        connectionState = ConnectionState.offline;
        curState = ModelState.startFrame;
        isOtherView = false;
        curIP ="";
        lobbyMessage = new Stack<ChatMessage>();
        curRegistrationState = RegistrationState.none;
        isPrepareOrientationShipHorizontal = true;
        selectedTypeShip = null;
        serverGamesList = new ArrayList<ServerGame>();
        isNeedToRefreshListOfGame = false;
        isNowMyTurn = false;
        isPrepareToShot = false;
        isMiss = false;
        isHit = false;
    }

    @Override
    public RegistrationState getRegistrationState() {
        RegistrationState tmp = curRegistrationState;
        if(tmp != RegistrationState.none)
            curRegistrationState = RegistrationState.none;
        return tmp;
    }

    @Override
    public IGame getGame() {
        return game;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public ModelState getState() {
        return curState;
    }

    @Override
    public boolean isOtherView() {
        return isOtherView;
    }

    @Override
    public String getDefaultIP() {
        return "192.168.5.198";
    }

    @Override
    public String getCurrentIP() {
        return curIP;
    }

    @Override
    public ConnectionState getConnectionState() {
        return connectionState;
    }

    @Override
    public ModelState getCurrentState() {
        return curState;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isPrepareOrientationShipHorizontal() {
        return isPrepareOrientationShipHorizontal;
    }

    @Override
    public boolean isAllShipOnBoard() {
        return game.isAllShipOnBoard();
    }

    @Override
    public IGame.ShipType getSelectedTypeShip() {
        return selectedTypeShip;
    }

    @Override
    public ArrayList<ServerGame> getListOFServersGames() {
        return serverGamesList;
    }

    @Override
    public boolean isNeedRefreshListOfGame() {
        return isNeedToRefreshListOfGame;
    }

    public void setConnectionState(ConnectionState connectionState) {
        if(this.connectionState != connectionState)
        {
            this.connectionState = connectionState;
            setChanged();
        }
        notifyObservers();
    }
    public void setCurIP(String IP){
        this.curIP = IP;
    }
    public void setChangedAndNeedNotify(){
        setChanged();
        notifyObservers();
    }
    public void setCurState(ModelState state){
        if(this.curState != state)
        {
            curState = state;
            isOtherView = true;
            setChanged();
        }
        notifyObservers();
    }
    public void isOtherView(boolean isOtherView){
        this.isOtherView = isOtherView;
    }
    public void setLogin(String login){
        this.login = login;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public void setPrepareOrientationShipHorizontal(boolean prepareOrientationShipHorizontal) {
        if(isPrepareOrientationShipHorizontal != prepareOrientationShipHorizontal)
            setChanged();
        isPrepareOrientationShipHorizontal = prepareOrientationShipHorizontal;
        notifyObservers();
    }

    @Override
    public Stack<ChatMessage> getLobbyMessage() {
        return lobbyMessage;
    }

    @Override
    public boolean isOpponentReady() {
        return game.isOpponentReady();
    }

    @Override
    public boolean isThisReady() {
        return game.isThisReady();
    }

    @Override
    public boolean isNowMyTurn() {
        return isNowMyTurn;
    }

    @Override
    public boolean isPrepareToShot() {
        return isPrepareToShot;
    }

    @Override
    public int getCoordX() {
        return coordX;
    }

    @Override
    public int getCoordY() {
        return coordY;
    }

    @Override
    public boolean isMiss() {
        boolean tmp = isMiss;
        isMiss = false;
        return tmp;
    }

    @Override
    public boolean isHit() {
        boolean tmp = isHit;
        isHit = false;
        return tmp;
    }

    public void addMessageToLobbyChat(ChatMessage message){
        lobbyMessage.push(message);
        //тест стека на работу вместимости
        //if(lobbyMessage.capacity() > 2)
            setChanged();
        notifyObservers();
    }

    public void setRegistrationState(RegistrationState state){
        if(this.curRegistrationState != state){
            curRegistrationState = state;
            setChanged();
        }
        notifyObservers();
    }
    public void CreateGame(){
        game = new Game();
    }

    public void setSelectedTypeShip(IGame.ShipType type){
        selectedTypeShip = type;
    }

    public void placeTheShip(int coordX, int coordY){
        game.placeTheShip(coordX, coordY, selectedTypeShip, isPrepareOrientationShipHorizontal);
        setChanged();
        notifyObservers();
    }

    public void setListOFServersGames(ArrayList<ServerGame> list){
        //TODO сделать нормальную реализацию, а то выбрасывается старый лист и устанавливается новый...
        serverGamesList = list;
        isNeedToRefreshListOfGame = true;
        setChanged();
        notifyObservers();
        //isNeedToRefreshListOfGame = false;
    }
    public void setOpponent(String login){
        if(game.getOpponentName() != login)
            setChanged();
        game.setOpponent(login);
        notifyObservers();
    }

    public void setObserverCount(int count){
        game.setObserverCount(count);
        setChanged();
        notifyObservers();
    }
    public void setThisReady(boolean flag){
        game.setThisReady(flag);
    }
    public void setOpponentReady(boolean flag){
        game.setOpponentReady(flag);
        setChanged();
        notifyObservers();
    }

    public void setNowMyTurn(boolean flag) {
        if(isNowMyTurn != flag)
            setChanged();

        isNowMyTurn = flag;
        notifyObservers();
    }
    public void setPrepareToShot(int coordX, int coordY){

        isPrepareToShot = true;
        this.coordX = coordX;
        this.coordY = coordY;
        setChanged();
        notifyObservers();
    }
    public void setNotPrepareToShot(){
        isPrepareToShot = false;
        setChanged();
        notifyObservers();
    }

    public void destroyHimselfShip(int coordX, int coordY){
        game.getHimselfGameBoard().markShipAsDestroyed(coordX, coordY);
        setChanged();
        notifyObservers();
    }
    public void destroyOpponentShip(int coordX, int coordY){
        game.markOpponentShipAsDestroyed(coordX, coordY);
        setChanged();
        notifyObservers();
    }

    public int getOpponentShipCount(IGame.ShipType type) {
        return game.getOpponentShipCount(type);
    }

    private ConnectionState connectionState;
    private ModelState curState;
    private boolean isOtherView;
    private String login;
    private String password;
    private String curIP;
    private Stack<ChatMessage> lobbyMessage;
    private RegistrationState curRegistrationState;
    private Game game;
    private boolean isPrepareOrientationShipHorizontal;
    private IGame.ShipType selectedTypeShip;
    private ArrayList<ServerGame> serverGamesList;
    private boolean isNeedToRefreshListOfGame;
    private boolean isNowMyTurn;
    private int coordX;
    private int coordY;
    private boolean isPrepareToShot;
    private boolean isMiss;
    private boolean isHit;
}
