import javax.swing.*;

/**
 * Created by Artem on 08.05.2017.
 */
public class MainMenuFrame extends MyParentFrame {
    JPanel myPanel;
    JButton joinButton;
    JButton createButton;
    JButton statisticButton;
    JButton backButton;

    public MainMenuFrame(IModel model, IController controller){
        super(model, controller, IModel.ModelState.mainMenuFrame);

        myPanel = new JPanel();
        joinButton = new JButton("Join");
        createButton = new JButton("Create");
        statisticButton = new JButton("Statistic");
        backButton = new JButton("Back");

        myPanel.setLayout(null);
        myPanel.setSize(720,1200);
        myPanel.setLocation(0,0);
        myPanel.setVisible(true);

        joinButton.setSize(150,50);
        joinButton.setLocation(300,270);
        joinButton.addActionListener(event->{
            controller.buttonJoinHandler();
        });

        createButton.setSize(150,50);
        createButton.setLocation(300,330);
        createButton.addActionListener(event->{
            controller.buttonCreateGameHandler();
        });

        statisticButton.setSize(150,50);
        statisticButton.setLocation(300,390);

        backButton.setSize(150,50);
        backButton.setLocation(300,450);
        backButton.addActionListener(event->{
            controller.activateViewHandler(IModel.ModelState.loginFrame);
        });

        myPanel.add(joinButton);
        myPanel.add(createButton);
        myPanel.add(statisticButton);
        myPanel.add(backButton);
        this.add(myPanel);
    }

    @Override
    public void refresh() {

    }
}
