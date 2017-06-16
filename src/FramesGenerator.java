import javax.swing.*;

/**
 * Created by Artem on 06.05.2017.
 */
public class FramesGenerator {
    private IModel model;
    private IController controller;
    public FramesGenerator(IModel model, IController controller){
        this.model = model;
        this.controller = controller;
    }
    public MyParentFrame createFrame(IModel.ModelState nameOfFrame){
        MyParentFrame tmp;
        switch (nameOfFrame){
            case startFrame:{
                tmp = new StartFrame(model, controller);
                tmp.setSize(720,1280);
                tmp.setResizable(false);
                tmp.setTitle("SeeWar:Start");
                tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                tmp.setVisible(false);
            } break;
            case loginFrame:{
                tmp = new LoginFrame(model, controller);
                tmp.setSize(720,1280);
                tmp.setResizable(false);
                tmp.setTitle("SeeWar:Login");
                tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                tmp.setVisible(false);
            } break;
            case registrationFrame:{
                tmp = new RegistrationFrame(model, controller);
                tmp.setSize(720,1280);
                tmp.setResizable(false);
                tmp.setTitle("SeeWar:Registration");
                tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                tmp.setVisible(false);
            } break;
            case mainMenuFrame:{
                tmp = new MainMenuFrame(model, controller);
                tmp.setSize(720,1280);
                tmp.setResizable(false);
                tmp.setTitle("SeeWar:MainMenu");
                tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                tmp.setVisible(false);
            } break;
            case lobbyFrame:{
                tmp = new LobbyFrame(model, controller);
                tmp.setSize(720,1280);
                tmp.setResizable(false);
                tmp.setTitle("SeeWar:MainMenu");
                tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                tmp.setVisible(false);
            } break;
            case createGameFrame:{
                tmp = new CreateGameFrame(model, controller);
                tmp.setSize(720,1280);
                tmp.setResizable(false);
                tmp.setTitle("SeeWar:CreateGame");
                tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                tmp.setVisible(false);
            } break;
            case connectToGameFrame:{
                tmp = new ConnectToGameFrame(model, controller);
                tmp.setSize(720,1280);
                tmp.setResizable(false);
                tmp.setTitle("SeeWar:ConnectToGame");
                tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                tmp.setVisible(false);
            } break;
            case inGameState:{
                tmp = new TheGameFrame(model, controller);
                tmp.setSize(720,1280);
                tmp.setResizable(false);
                tmp.setTitle("SeeWar:CreateGame");
                tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                tmp.setVisible(false);
            } break;
            default: tmp = new StartFrame(model, controller);
        }
        return tmp;
    }
}
