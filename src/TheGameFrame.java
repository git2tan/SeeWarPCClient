import javax.swing.*;

/**
 * Created by Artem on 20.05.2017.
 */
public class TheGameFrame extends MyParentFrame{
    JPanel myPanel;             //родительская панель

    JLabel opponentNameHeaderLabel;     //заголовок для протвиника
    JLabel opponentNameLabel;           //Поле для имени противника
    JLabel observersCountHeaderLabel;
    JLabel observersCountLabel;
    GameBoardForFrame himSelfBoardPanel;
    GameBoardForFrame opponentGameBoardPanel;

    JButton buttonFire;
    JButton buttonSurrender;

    public TheGameFrame(IModel model, IController controller){
        super(model, controller, IModel.ModelState.inGameState);

        myPanel = new JPanel();
        myPanel.setLayout(null);
        myPanel.setSize(720,1200);
        myPanel.setLocation(0,0);
        myPanel.setVisible(true);

        opponentNameHeaderLabel = new JLabel("Opponent name");     //заголовок для протвиника
        opponentNameHeaderLabel.setSize(120,20);
        opponentNameHeaderLabel.setLocation(120,20);

        opponentNameLabel = new JLabel(model.getGame().getOpponentName());
        opponentNameLabel.setSize(120,20);
        opponentNameLabel.setLocation(120,50);

        observersCountHeaderLabel = new JLabel("Observer count");
        observersCountHeaderLabel.setSize(120,20);
        observersCountHeaderLabel.setLocation(320,30);

        observersCountLabel = new JLabel("" + model.getGame().getObserverCount());
        observersCountLabel.setSize(30,20);
        observersCountLabel.setLocation(450,30);

        himSelfBoardPanel = new GameBoardForFrame(model.getGame().getHimselfGameBoard(), null);
        himSelfBoardPanel.setSize(400,400);
        himSelfBoardPanel.setLocation(160,100);
        himSelfBoardPanel.setVisible(true);

        opponentGameBoardPanel = new GameBoardForFrame(model.getGame().getOpponentGameBoard(), controller.getOpponentGameBoardController());
        opponentGameBoardPanel.setSize(400,400);
        opponentGameBoardPanel.setLocation(160,520);
        opponentGameBoardPanel.setVisible(true);

        buttonFire = new JButton("Fire!");
        buttonFire.setEnabled(model.isNowMyTurn());
        buttonFire.setSize(150,100);
        buttonFire.setLocation(490,950);
        buttonFire.addActionListener(event->{
            controller.buttonFireHandler();
        });

        buttonSurrender = new JButton("Surrender");
        buttonSurrender.setSize(150,100);
        buttonSurrender.setLocation(80,950);

        myPanel.add(opponentNameHeaderLabel);
        myPanel.add(opponentNameLabel);
        myPanel.add(observersCountHeaderLabel);
        myPanel.add(observersCountLabel);

        myPanel.add(himSelfBoardPanel);
        myPanel.add(opponentGameBoardPanel);
        myPanel.add(buttonFire);
        myPanel.add(buttonSurrender);

        add(myPanel);
    }
    @Override
    public void refresh() {
        opponentGameBoardPanel.refresh();
        himSelfBoardPanel.refresh();

        buttonFire.setEnabled(model.isNowMyTurn());
    }
}
