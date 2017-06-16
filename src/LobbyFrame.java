import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Artem on 08.05.2017.
 */
public class LobbyFrame extends MyParentFrame {
    JPanel myPanel;
    JTextArea chat;
    JScrollPane chatScrollPane;

    JTextField messageField;
    JButton sendMessageButton;
    JButton connectToGameButton;
    JButton observerToGameButton;
    JButton createGameButton;
    JButton backButton;
    DefaultListModel<String> listModel;
    JList<String> listOfGame;
    JScrollPane paneForGames;

    public LobbyFrame(IModel model, IController controller){
        super(model, controller, IModel.ModelState.lobbyFrame);

        myPanel = new JPanel();

        chat = new JTextArea();
        chatScrollPane = new JScrollPane(chat);

        messageField = new JTextField();
        sendMessageButton = new JButton("Send");
        connectToGameButton = new JButton("Connect to game");
        observerToGameButton = new JButton("Observer");
        createGameButton = new JButton("Create");
        backButton = new JButton("Back");

        listModel = new DefaultListModel();
        for(ServerGame one : model.getListOFServersGames())
            listModel.addElement(one.toString());
        listOfGame = new JList(listModel);
        listOfGame.setSelectedIndex(0);
        listOfGame.setSize(620,350);

        myPanel.setLayout(null);
        myPanel.setSize(720,1200);
        myPanel.setLocation(0,0);
        myPanel.setVisible(true);

        paneForGames = new JScrollPane(listOfGame);
        paneForGames.setSize(620,300);
        paneForGames.setLocation(50,50);
        paneForGames.setVisible(true);
        //paneForGames.add(listOfGame);

        chat.setSize(620,300);
        chat.setLineWrap(true);
        chat.setLocation(50,350);
        chat.setVisible(true);
        chat.setEditable(false);

        chatScrollPane.setSize(620,300);
        chatScrollPane.setLocation(50,350);
        chatScrollPane.setAutoscrolls(true);

        messageField.setSize(520,100);
        messageField.setLocation(50,650);

        sendMessageButton.setSize(100,100);
        sendMessageButton.setLocation(570,650);
        sendMessageButton.addActionListener(event->{
            if(!messageField.getText().isEmpty())
                controller.buttonSendHandler(messageField.getText());
        });

        connectToGameButton.setSize(200,100);
        connectToGameButton.setLocation(50,760);
        connectToGameButton.addActionListener(event->{
            int indx = listOfGame.getSelectedIndex();
            controller.buttonConnectToGameHandler(indx);
        });

        observerToGameButton.setSize(200,100);
        observerToGameButton.setLocation(250,760);

        createGameButton.setSize(200,100);
        createGameButton.setLocation(450,760);
        createGameButton.addActionListener(event->{

        });

        backButton.setSize(100,50);
        backButton.setLocation(570,880);

        myPanel.add(chatScrollPane);
        myPanel.add(messageField);
        myPanel.add(sendMessageButton);
        myPanel.add(connectToGameButton);
        myPanel.add(observerToGameButton);
        myPanel.add(createGameButton);
        myPanel.add(backButton);
        myPanel.add(paneForGames);
        this.add(myPanel);
    }
    @Override
    public void refresh() {
        Stack<ChatMessage> stack = model.getLobbyMessage();
        Stack<ChatMessage> inversionStack = new Stack<ChatMessage>();
        while(!stack.isEmpty())
        {
            inversionStack.push(stack.pop());
        }
        while(!inversionStack.isEmpty()){
            ChatMessage tmp = inversionStack.pop();
            //TextArea
            if(model.getLogin().equals(tmp.login)){
                chat.append("Я" + ": " + tmp.message + "\n");
            }
            else if(tmp.login.equals("SERVER")){
                String serverMessage = tmp.login +" --> " + tmp.message + "\n";
                chat.append(serverMessage.toUpperCase());
            }
            else{
                chat.append(tmp.login + ": "+tmp.message + "\n");
            }
            chat.setCaretPosition(chat.getDocument().getLength());
        }
        if(model.isNeedRefreshListOfGame())
        {
            listModel.clear();
            //listModel.addElement("!!!!!!!!!!!!!!!!!!!!!!!!!");
            for(ServerGame one : model.getListOFServersGames()){
                listModel.addElement(one.toString());
            }
        }

//        listOfGame.removeAll();
//        listOfGame.setListData(Decoder.ListToVector(model.getListOFServersGames()));
//        listOfGame.repaint();
    }
}
