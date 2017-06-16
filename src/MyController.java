import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Artem on 06.05.2017.
 */
public class MyController implements IController, Observer {
    private MyModel model;
    private MainContainerOfFrames mainFrame;
    private Thread receiverThread;
    private MySender sender;
    private HimselfGameBoardController himselfGameBoardController;
    private OpponentGameBoardController opponentGameBoardController;

    public MyController(){
        model = new MyModel();
        mainFrame = new MainContainerOfFrames(model, this);
        model.addObserver(this);
        mainFrame.activate(IModel.ModelState.startFrame);
        himselfGameBoardController = new HimselfGameBoardController(this);
        opponentGameBoardController = new OpponentGameBoardController(this);
    }
    @Override
    public void buttonConnectHandler(String IP) {
        if(!(model.getConnectionState() == IModel.ConnectionState.online) || (model.getConnectionState() == IModel.ConnectionState.cantLogin)){
        ServerMessageHandler serverMessageHandler = new ServerMessageHandler( this);
        sender = new MySender(this);
        try{
            //Socket clientSocket = new Socket(IP, 4444);
            Socket clientSocket = new Socket();
            clientSocket.connect(new InetSocketAddress(IP, 4444), 500);

//            Socket clientSocket = new Socket();
//            SocketAddress adr = new InetSocketAddress(IP,4444);
//            clientSocket.connect(adr);
            //если удалось то
            ClientReceiver receiver = new ClientReceiver(serverMessageHandler, clientSocket);
            sender.setSocket(clientSocket);
            receiverThread = new Thread(receiver);
            receiverThread.start();
            model.setConnectionState(IModel.ConnectionState.online);
            model.setCurState(IModel.ModelState.loginFrame);
        } catch (IOException e){
            model.setConnectionState(IModel.ConnectionState.cantConnectToServer);
        }
        }
        else{
            model.setCurState(IModel.ModelState.loginFrame);
        }
        model.setCurIP(IP);
        //model.setChangedAndNeedNotify();
    }

    @Override
    public void buttonRegistrationHandler() {
        if(model.getConnectionState() == IModel.ConnectionState.online ||
                model.getConnectionState() == IModel.ConnectionState.isAuthorizedOnTheServer)
            model.setCurState(IModel.ModelState.registrationFrame);
        else{
            //???
        }
    }

    @Override
    public void buttonRegistrationNewAccount(String login, String pass) {
        sender.sendMessage(new Message(108, login, pass));
    }

    @Override
    public void buttonSendHandler(String message) {
        sender.sendMessage(new Message(105, model.getLogin(),message));
    }

    @Override
    public void buttonLoginHandler(String login, String pass) {
        sender.sendMessage(new Message(100, login, pass));
    }

    @Override
    public void connectErrorHandler() {
        //mainFrame.getCurrentFrame().
    }

    @Override
    public void activateViewHandler(IModel.ModelState state) {
        model.setCurState(state);
    }

    @Override
    public void buttonJoinHandler() {
        sender.sendMessage(new Message(103,"",""));
    }

    @Override
    public void handleMessageFromServer(Message message) {
        switch(message.getNumberOfCommand()){
            case 101:{
                model.setLogin(message.getLogin());
                model.setPassword(message.getPass());
                model.setConnectionState(IModel.ConnectionState.isAuthorizedOnTheServer);
                model.setCurState(IModel.ModelState.mainMenuFrame);
            }break;
            case 102:{
                model.setConnectionState(IModel.ConnectionState.cantLogin);
            }break;
            case 103:{
                //пропускаю
            }break;
            case 104:{
                model.setCurState(IModel.ModelState.lobbyFrame);
            }break;
            case 105:{
                //пропускаю такое сообщение гениртся клиентом и обрабатывается на сервере
            }break;
            case 106:{
                //сообщение в чат лобби от конкретного пользователя
                model.addMessageToLobbyChat(new ChatMessage(message.getLogin(),message.getMessage()));

            }break;
            case 107:{
                //служебное сообщение сервера в чат Лобби
                model.addMessageToLobbyChat(new ChatMessage(message.getLogin(),message.getMessage()));
            }break;
            case 108:{
                //пропускаем
            }break;
            case 109:{
                //Положительный ответ на запрос регистрации
                model.setRegistrationState(IModel.RegistrationState.success);
            }break;
            case 110:{
                //отрицательный ответ на запрос регистрации
                model.setRegistrationState(IModel.RegistrationState.forbidden);
            }break;
            case 111:{
                // пропускаем так как это клиент генерирует такие сообщения
            }break;
            case 112:{
                //удалось создать игру
                model.CreateGame();
                model.setCurState(IModel.ModelState.createGameFrame);
            }break;
            case 113:{
                // пропускаем
            }break;
            case 114:{
                // положительный ответ на запрос о подключении к игре
                // так мы подключились то работаем от лица второго пользователя и для нас оппонент это первый пользователь
                model.CreateGame();
                model.setOpponent(message.getGameInfo().login1);
                model.setObserverCount(message.getGameInfo().observerCount);
                model.setOpponentReady(message.getGameInfo().isReady1);
                model.setCurState(IModel.ModelState.connectToGameFrame);
            }break;
            case 115:{
                JOptionPane.showMessageDialog(mainFrame.getFrame(),"Не удалось подключиться к игре.");
            }break;
            case 116:{
                // пропускаем т.к. это мы генерируем такое сообщение
            }break;
            case 117:{
                model.setOpponent(message.getLogin());
            }break;
            case 118:{
                //ответ на наш запрос прервать создание игры
                model.setCurState(IModel.ModelState.lobbyFrame);
                //TODO сделать уничтожение данных об игре...
            }break;
            case 119:{
                // сообщение что произошли изменения в игровых данных
                GameInfo gameInfo = message.getGameInfo();
                //если сообщение пишло нам как хосту
                if(model.getLogin().equals(gameInfo.login1)){
                    model.setOpponent(gameInfo.login2);
                    model.setOpponentReady(gameInfo.isReady2);
                    model.setObserverCount(gameInfo.observerCount);

                }
                else{
                    model.setOpponent(gameInfo.login1);
                    model.setOpponentReady(gameInfo.isReady1);
                    model.setObserverCount(gameInfo.observerCount);

                }
            }break;
            case 120:{
                //пропускаем (это мы посылаем готовность и расстановку кораблей
            }break;
            case 121:{
                //пропускаем т.к. это мы посылаем что мы не готовы
            }break;
            case 122:{
                // сообщение от клиента о старте игры
            }break;
            case 123:{
                // пришел ответ на зпрос о старте игры что мы стартуем первым номером
                model.setNowMyTurn(true);
                model.setCurState(IModel.ModelState.inGameState);

            } break;
            case 124:{
                // пришло указание от сервера что хостовый игрок начал игру
                model.setNowMyTurn(false);
                model.setCurState(IModel.ModelState.inGameState);
            }break;
            case 125:{
                // пришло указание от сервера что игроки начали игру и мы должны начать наблюдать
            } break;
            case 126:{
                // это мы сгенерировали
            } break;
            case 127:{
                // ответ сервера попал по координатам (ход не переходит)
                model.getGame().getOpponentGameBoard().shot(message.getCoordX(),message.getCoordY(),false);
                model.setNotPrepareToShot();
                model.setNowMyTurn(true);
            } break;
            case 128:{
                // ответ сервера по координатам ()() - пусто  (ход переходит к оппоненту)
                model.getGame().getOpponentGameBoard().shot(message.getCoordX(),message.getCoordY(),true);
                model.setNotPrepareToShot();
                model.setNowMyTurn(false);
            }break;
            case 129:{
                // сообщение сервера что по игроку стрельнули и попали (не его ход)
                model.getGame().getHimselfGameBoard().shot(message.getCoordX(),message.getCoordY());
                model.setNotPrepareToShot();
                model.setNowMyTurn(false);
            }break;
            case 130:{
                // сообщение сервера что по игроку стрельнули и промазали (его ход)
                model.getGame().getHimselfGameBoard().shot(message.getCoordX(),message.getCoordY());
                model.setNotPrepareToShot();
                model.setNowMyTurn(true);
            }break;
            case 131:{
                // сообщение сервера что по указанным координатам "потопили" корабль (на вражеской доске)
                System.err.println("Мы потопили корабль по координатам " + message.getCoordX() + " : " + message.getCoordY());
                model.destroyOpponentShip(message.getCoordX(), message.getCoordY());
            } break;
            case 132:{
                // сообщение сервера что по указанным координатам нам потопили корабль (на нашей доске)
                System.err.println("Нам потопили корабль по координатам " + message.getCoordX() + " : " + message.getCoordY());
                model.destroyHimselfShip(message.getCoordX(),message.getCoordY());
            }break;
            case 201:{
                // пришел список игр от сервера
                model.setListOFServersGames(parseServerGames(message));
            }break;
            case 203:{
                // пришел пустой список игр
                ArrayList<ServerGame> emptyList = new ArrayList<ServerGame>();
                model.setListOFServersGames(emptyList);
            }break;

        }
    }

    @Override
    public void buttonCreateGameHandler() {
        sender.sendMessage(new Message(111,"",""));
    }

    @Override
    public void buttonConnectToGameHandler(int indx) {
        ServerGame game = null;
        if(indx != -1)
            game = model.getListOFServersGames().get(indx);

        if(game!=null)
            sender.sendMessage(new Message(113,"" + game.getId(),""));
    }

    @Override
    public void update(Observable o, Object arg) {
        if(model.isOtherView())
        {
            mainFrame.activate(model.getCurrentState());
            model.isOtherView(false);
        }
        mainFrame.update();
    }

    @Override
    public void buttonTurnOrientationHandler() {
        model.setPrepareOrientationShipHorizontal(!model.isPrepareOrientationShipHorizontal());
    }

    @Override
    public void shipSelectHandler(IGame.ShipType type) {
        model.setSelectedTypeShip(type);
    }

    @Override
    public void cancelCreateGameButton() {
        sender.sendMessage(new Message(116,"",""));
    }

    @Override
    public void mouseClickedSpriteHandler(int coordX, int coordY) {
        //проверяем что не самый первый клик (то есть еще не был выбран тип корабля
        IGame.ShipType type = model.getSelectedTypeShip();
        if (type != null){
            // проверяем что количество доступных кораблей выбранного типа больше 0
            if (model.getGame().getRemainsSetToPosition(type) > 0){
                //проверяем что в выбранное место можно установить корабль выбранноо типа
                if(model.getGame().getHimselfGameBoard().isPossibleToPlaceShip(coordX, coordY, type, model.isPrepareOrientationShipHorizontal())){
                    //ставим корабль на место
                    model.placeTheShip(coordX, coordY);
                }

            }

        }
    }

    @Override
    public void checkBoxIsReadyHandler(boolean flag) {
        if(flag){
            // посылаем на сервер сообщение с расположением кораблей
            sender.sendMessage(new Message(120,model.getGame().getHimselfGameBoard().toString(),""));
        }
        else{
            sender.sendMessage(new Message(121,"",""));
        }
    }

    @Override
    public void startGameButtonHandler() {
        sender.sendMessage(new Message(122, "",""));
    }

    @Override
    public IClickedController getHimselfGameBoardController() {
        return himselfGameBoardController;
    }

    @Override
    public IClickedController getOpponentGameBoardController() {
        return opponentGameBoardController;
    }

    @Override
    public void mouseClickedSpriteOpponentHandler(int coordX, int coordY) {
        //метод обработки нажатия на спрайт поля оппонента
        model.getGame().getOpponentGameBoard().mark(coordX, coordY);
        model.setPrepareToShot(coordX, coordY);
    }

    @Override
    public void buttonFireHandler() {
        if(model.isNowMyTurn() && model.isPrepareToShot())
        {
            model.setNowMyTurn(false);
            model.setNotPrepareToShot();
            sender.sendMessage(new Message(126, "" + model.getCoordX(), "" + model.getCoordY()));
        }
    }

    private ArrayList<ServerGame> parseServerGames(Message message){
        ArrayList<ServerGame> request = new ArrayList<ServerGame>();
        ArrayList<String> list = message.getListOfGame();
        for(String one : list){
            request.add(Decoder.decodeStringToServerGame(one));
        }
        return request;
    }
}
