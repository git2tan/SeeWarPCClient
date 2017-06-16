import javax.swing.*;
import java.awt.*;

/**
 * Created by Artem on 06.05.2017.
 */
public class StartFrame extends MyParentFrame {
    JPanel myPanel;
    JButton connectButton;
    JButton setDefaultIPButton;
    JLabel connectStateLabel;
    JTextField connectIPField;

    public StartFrame(IModel model, IController controller){
        super(model, controller, IModel.ModelState.startFrame);

        myPanel = new JPanel();
        myPanel.setLayout(null);
        myPanel.setSize(720,1200);
        myPanel.setLocation(0,0);
        myPanel.setVisible(true);

        connectIPField = new JTextField(model.getDefaultIP());
        connectIPField.setSize(150,20);
        connectIPField.setLocation(300,270);

        connectButton = new JButton("Connect");
        connectButton.setSize(150,50);
        connectButton.setLocation(300,300);
        connectButton.addActionListener(event ->{
            controller.buttonConnectHandler(connectIPField.getText());
        });

        setDefaultIPButton = new JButton("Get Default IP");
        setDefaultIPButton.setSize(150,20);
        setDefaultIPButton.setLocation(300,370);
        setDefaultIPButton.addActionListener(event ->{
            connectIPField.setText(model.getDefaultIP());

        });

        connectStateLabel = new JLabel();
        switch(model.getConnectionState()){
            case offline:{
                connectStateLabel.setText("Отключен от сервера");
            } break;
            case tryToConnect:{
                connectStateLabel.setText("Пытаюсь подключиться");
            } break;
            case cantConnectToServer:{
                connectStateLabel.setText("Сервер не отвечает");
            }break;
            case online:{
                connectStateLabel.setText("Подключен");
            }
        }

        connectStateLabel.setSize(150,20);
        connectStateLabel.setLocation(300,200);

        myPanel.add(connectIPField);
        myPanel.add(connectStateLabel);
        myPanel.add(connectButton);
        myPanel.add(setDefaultIPButton);
        this.add(myPanel);
    }
    @Override
    public void refresh() {
        switch(model.getConnectionState()){
            case offline:{
                connectStateLabel.setText("Отключен от сервера");
            } break;
            case tryToConnect:{
                connectStateLabel.setText("Пытаюсь подключиться");
            } break;
            case cantConnectToServer:{
                connectStateLabel.setText("Сервер не отвечает");
            }break;
            case online:{
                connectStateLabel.setText("Подключен");
            }
        }
        if(!model.getCurrentIP().isEmpty())
            connectIPField.setText(model.getCurrentIP());
        else
            connectIPField.setText(model.getDefaultIP());
        repaint();
    }
}
