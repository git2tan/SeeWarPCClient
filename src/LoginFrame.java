import javax.swing.*;

/**
 * Created by Artem on 07.05.2017.
 */
public class LoginFrame extends MyParentFrame{
    JPanel myPanel2;
    JTextField loginField;
    JPasswordField passwordField;
    JButton loginButton;
    JButton registerButton;
    JLabel errorLabel;
    JButton backButton;


    public LoginFrame(IModel model, IController controller){
        super(model, controller, IModel.ModelState.loginFrame);

        myPanel2 = new JPanel();
        errorLabel = new JLabel("");
        errorLabel.setLocation(300,230);
        errorLabel.setSize(300,30);
        myPanel2.setLayout(null);
        myPanel2.setSize(700,1200);
        myPanel2.setLocation(0,0);


        loginField = new JTextField("Admin");
        loginField.setSize(150,20);
        loginField.setLocation(300,270);
        passwordField = new JPasswordField("1234");
        passwordField.setSize(150,20);
        passwordField.setLocation(300,300);

        loginButton = new JButton("Login");
        loginButton.setSize(150,50);
        loginButton.setLocation(300,330);
        loginButton.addActionListener(event ->{
            //TODO добавить проверку если находимся в режиме isAuthorizedOnTheServer
            //и текущий логин не совпадает с новым введенным то делаем дисконнект
            if(loginField.getText().length() < 3){
                errorLabel.setText("Логин не может быть меньше 3 символов");
            }
            else if(passwordField.getText().isEmpty()){
                errorLabel.setText("Пароль не может быть пустым");
            }
            else{
                controller.buttonLoginHandler(loginField.getText(), passwordField.getText());
            }
        });

        registerButton = new JButton("Register");
        registerButton.setSize(150,30);
        registerButton.setLocation(300,390);
        registerButton.addActionListener(event->{
            controller.buttonRegistrationHandler();
        });

        backButton = new JButton("Back");
        backButton.setSize(150,30);
        backButton.setLocation(300,420);
        backButton.addActionListener(event ->{
            controller.activateViewHandler(IModel.ModelState.startFrame);
        });

        myPanel2.add(loginField);
        myPanel2.add(passwordField);
        myPanel2.add(loginButton);
        myPanel2.add(registerButton);
        myPanel2.add(backButton);
        myPanel2.add(errorLabel);
        myPanel2.setVisible(true);
        this.add(myPanel2);
    }

    @Override
    public void refresh() {
        if(model.getConnectionState() == IModel.ConnectionState.isAuthorizedOnTheServer){
            loginField.setText(model.getLogin());
            errorLabel.setText("");
        }else if (model.getConnectionState() == IModel.ConnectionState.cantLogin)
        {
            errorLabel.setText("Пользователь не найден");
        }

    }

}
