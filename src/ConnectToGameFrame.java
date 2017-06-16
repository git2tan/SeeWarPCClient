import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Artem on 14.05.2017.
 */
public class ConnectToGameFrame extends MyParentFrame {

    JPanel myPanel;             //родительская панель

    JLabel opponentNameHeaderLabel;     //заголовок для протвиника
    JLabel opponentNameLabel;           //Поле для имени противника
    JLabel opponentStateHeaderLabel;
    JLabel opponentStateLabel;
    JLabel observersCountHeaderLabel;
    JLabel observersCountLabel;

    JPanel staticShip1Panel;
    JPanel staticShip2Panel;
    JPanel staticShip3Panel;
    JPanel staticShip4Panel;
    JPanel orientationVisabilityPanel;

    GameBoardForFrame himSelfBoardPanel;

    JLabel message;
    JCheckBox readyCheckBox;

    StaticShipComponent ship1Image;
    StaticShipComponent ship2Image;
    StaticShipComponent ship3Image;
    StaticShipComponent ship4Image;
    StaticShipComponent shipOrientationImage;

    JLabel ship1CountLabel;
    JLabel ship2CountLabel;
    JLabel ship3CountLabel;
    JLabel ship4CountLabel;

    JButton undoButton;
    JButton turnButton;
    JButton setButton;
    JButton restartButton;
    JButton cancelButton;
    JButton startButton;



    public ConnectToGameFrame(IModel model, IController controller){
        super(model, controller, IModel.ModelState.createGameFrame);

        myPanel = new JPanel();
        myPanel.setLayout(null);
        myPanel.setSize(720,1200);
        myPanel.setLocation(0,0);
        myPanel.setVisible(true);

        opponentNameHeaderLabel = new JLabel("Opponent name");     //заголовок для протвиника
        opponentNameLabel = new JLabel(model.getGame().getOpponentName());           //Поле для имени противника
        opponentStateHeaderLabel = new JLabel("State");
        opponentStateLabel = new JLabel(model.getGame().isOpponentReady()?"V":"X");
        observersCountHeaderLabel = new JLabel("Observer count");
        observersCountLabel = new JLabel("" + model.getGame().getObserverCount());
        himSelfBoardPanel = new GameBoardForFrame(model.getGame().getHimselfGameBoard(), controller.getHimselfGameBoardController());
        himSelfBoardPanel.setSize(400,400);
        himSelfBoardPanel.setLocation(160,280);
        himSelfBoardPanel.setVisible(true);
        //himSelfBoardPanel.setBorder(new LineBorder(Color.darkGray));

        staticShip1Panel = new JPanel();
        staticShip2Panel = new JPanel();
        staticShip3Panel = new JPanel();
        staticShip4Panel = new JPanel();
        orientationVisabilityPanel = new JPanel();



        ship1Image = new StaticShipComponent(IGame.ShipType.oneDeckShip);
        ship2Image = new StaticShipComponent(IGame.ShipType.twoDeckShip);
        ship3Image = new StaticShipComponent(IGame.ShipType.threeDeckShip);
        ship4Image = new StaticShipComponent(IGame.ShipType.fourDeckShip);
        shipOrientationImage = new StaticShipComponent(IGame.ShipType.fourDeckShip);

        ship1CountLabel = new JLabel(Integer.toString(model.getGame().getRemainsSetToPosition(IGame.ShipType.oneDeckShip)));
        ship2CountLabel = new JLabel(Integer.toString(model.getGame().getRemainsSetToPosition(IGame.ShipType.twoDeckShip)));
        ship3CountLabel = new JLabel(Integer.toString(model.getGame().getRemainsSetToPosition(IGame.ShipType.threeDeckShip)));
        ship4CountLabel = new JLabel(Integer.toString(model.getGame().getRemainsSetToPosition(IGame.ShipType.fourDeckShip)));

        message = new JLabel();
        readyCheckBox = new JCheckBox("Ready");

        undoButton = new JButton("Undo");
        turnButton = new JButton("Turn");
        setButton = new JButton("Set ship");
        restartButton = new JButton("Restart");
        cancelButton = new JButton("Cancel");

        opponentNameHeaderLabel.setSize(120,20);
        opponentNameHeaderLabel.setLocation(120,100);

        opponentStateHeaderLabel.setSize(30,20);
        opponentStateHeaderLabel.setLocation(300,100);

        opponentNameLabel.setSize(120,20);
        opponentNameLabel.setLocation(120,130);

        opponentStateLabel.setSize( 30,20);
        opponentStateLabel.setLocation(300,130);

        observersCountHeaderLabel.setSize(120,20);
        observersCountHeaderLabel.setLocation(120,160);

        observersCountLabel.setSize(30,20);
        observersCountLabel.setLocation(300,160);

        staticShip1Panel.setSize(160,70);
        staticShip1Panel.setLocation(25,200);
        staticShip1Panel.setLayout(null);
        staticShip1Panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.shipSelectHandler(IGame.ShipType.oneDeckShip);
            }
        });


        staticShip2Panel.setSize(160,70);
        staticShip2Panel.setLocation(195,200);
        staticShip2Panel.setLayout(null);
        staticShip2Panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.shipSelectHandler(IGame.ShipType.twoDeckShip);
            }
        });

        staticShip3Panel.setSize(160,70);
        staticShip3Panel.setLocation(365,200);
        staticShip3Panel.setLayout(null);
        staticShip3Panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.shipSelectHandler(IGame.ShipType.threeDeckShip);
            }
        });

        staticShip4Panel.setSize(160,70);
        staticShip4Panel.setLocation(535,200);
        staticShip4Panel.setLayout(null);
        staticShip4Panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.shipSelectHandler(IGame.ShipType.fourDeckShip);
            }
        });

        orientationVisabilityPanel.setSize(160,160);
        orientationVisabilityPanel.setLocation(280,750);
        orientationVisabilityPanel.setLayout(null);

        ship1Image.setSize(160,40);
        ship1Image.setLocation(0,0);


        ship2Image.setSize(160,40);
        ship2Image.setLocation(0,0);


        ship3Image.setSize(160,40);
        ship3Image.setLocation(0,0);


        ship4Image.setSize(160,40);
        ship4Image.setLocation(0,0);


        shipOrientationImage.setSize(160,160);
        shipOrientationImage.setLocation(0,0);
        shipOrientationImage.setOrientationHorizontal(model.isPrepareOrientationShipHorizontal());


        ship1CountLabel.setSize(10,20);
        ship1CountLabel.setLocation(75,50);

        ship2CountLabel.setSize(10,20);
        ship2CountLabel.setLocation(75,50);

        ship3CountLabel.setSize(10,20);
        ship3CountLabel.setLocation(75,50);

        ship4CountLabel.setSize(10,20);
        ship4CountLabel.setLocation(75,50);

        message.setSize(300,20);
        message.setForeground(Color.RED);
        message.setLocation(120,70);

        readyCheckBox.setSize(100,50);
        readyCheckBox.setLocation (600,600);
        readyCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == 1){
                    undoButton.setEnabled(false);
                    turnButton.setEnabled(false);
                    setButton.setEnabled(false);
                    restartButton.setEnabled(false);
                    cancelButton.setEnabled(false);

                    controller.checkBoxIsReadyHandler(true);
                }
                else{
                    undoButton.setEnabled(true);
                    turnButton.setEnabled(true);
                    setButton.setEnabled(true);
                    restartButton.setEnabled(true);
                    cancelButton.setEnabled(true);

                    controller.checkBoxIsReadyHandler(false);
                }
            }
        });

        undoButton.setSize(150,100);
        undoButton.setLocation(80,700);

        turnButton.setSize(150,100);
        turnButton.setLocation(490,700);
        turnButton.addActionListener(event->{
            controller.buttonTurnOrientationHandler();
        });

        restartButton.setSize(150,100);
        restartButton.setLocation(80,800);

        setButton.setSize(150,100);
        setButton.setLocation(490,800);

        cancelButton.setSize(150,100);
        cancelButton.setLocation(490,900);

        staticShip1Panel.add(ship1Image);
        staticShip1Panel.add(ship1CountLabel);

        staticShip2Panel.add(ship2Image);
        staticShip2Panel.add(ship2CountLabel);

        staticShip3Panel.add(ship3Image);
        staticShip3Panel.add(ship3CountLabel);

        staticShip4Panel.add(ship4Image);
        staticShip4Panel.add(ship4CountLabel);

        orientationVisabilityPanel.add(shipOrientationImage);

        myPanel.add(message);
        myPanel.add(readyCheckBox);

        myPanel.add(opponentNameHeaderLabel);
        myPanel.add(opponentNameLabel);
        myPanel.add(opponentStateHeaderLabel);
        myPanel.add(opponentStateLabel);
        myPanel.add(observersCountHeaderLabel);
        myPanel.add(observersCountLabel);

        myPanel.add(staticShip1Panel);
        myPanel.add(staticShip2Panel);
        myPanel.add(staticShip3Panel);
        myPanel.add(staticShip4Panel);

        myPanel.add(orientationVisabilityPanel);

        myPanel.add(himSelfBoardPanel);

        myPanel.add(undoButton);
        myPanel.add(turnButton);
        myPanel.add(restartButton);
        myPanel.add(setButton);
        myPanel.add(cancelButton);

        add(myPanel);

    }

    @Override
    public void refresh() {
        if(model.isAllShipOnBoard()){
            message.setText("");
            readyCheckBox.setEnabled(true);
        }
        else{
            readyCheckBox.setEnabled(false);
            message.setText("Не все корабли установлены на поле");
        }

        shipOrientationImage.setOrientationHorizontal(model.isPrepareOrientationShipHorizontal());

        ship1CountLabel.setText(Integer.toString(model.getGame().getRemainsSetToPosition(IGame.ShipType.oneDeckShip)));
        ship2CountLabel.setText(Integer.toString(model.getGame().getRemainsSetToPosition(IGame.ShipType.twoDeckShip)));
        ship3CountLabel.setText(Integer.toString(model.getGame().getRemainsSetToPosition(IGame.ShipType.threeDeckShip)));
        ship4CountLabel.setText(Integer.toString(model.getGame().getRemainsSetToPosition(IGame.ShipType.fourDeckShip)));

        himSelfBoardPanel.refresh();

        opponentStateLabel.setText(model.getGame().isOpponentReady()?"V":"X");
        observersCountLabel.setText(model.getGame().getObserverCount() + "");
    }
}
