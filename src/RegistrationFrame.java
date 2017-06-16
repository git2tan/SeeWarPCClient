import javax.swing.*;
import java.awt.*;

/**
 * Created by Artem on 08.05.2017.
 */
public class RegistrationFrame extends MyParentFrame{
    JPanel myPanel;
    JTextField addLoginField;
    JPasswordField addPasswordField1;
    JPasswordField addPasswordField2;
    JButton registrationButton;
    JLabel errorLabel;
    JButton backButton;
    public RegistrationFrame(IModel model, IController controller){
        super(model, controller, IModel.ModelState.registrationFrame);

        myPanel = new JPanel();
        errorLabel = new JLabel("Пароль не может быть пустым");
        addLoginField = new JTextField("Add here your Login");
        addPasswordField1 = new JPasswordField();
        addPasswordField2 = new JPasswordField();
        registrationButton = new JButton("Registration");
        backButton = new JButton("Back");

        myPanel.setLayout(null);
        myPanel.setSize(720,1200);
        myPanel.setLocation(0,0);
        myPanel.setVisible(true);

        errorLabel.setForeground(Color.RED);
        errorLabel.setSize(300,20);
        errorLabel.setLocation(300,360);
        errorLabel.setVisible(true);

        addLoginField.setSize(150,20);
        addLoginField.setLocation(300,270);

        addPasswordField1.setSize(150,20);
        addPasswordField1.setLocation(300,300);
        addPasswordField1.addActionListener(event ->{
            if((addPasswordField1.getText().isEmpty()) ||
                    addPasswordField1.getText().equals(addPasswordField2.getText())){
                errorLabel.setText("Пароли не совпадают или введен пустой пароль");
            }
            else{
                errorLabel.setText("");
            }
        });

        addPasswordField2.setSize(150,20);
        addPasswordField2.setLocation(300,330);
        addPasswordField2.addActionListener(event->{
            if((addPasswordField1.getText().isEmpty()) ||
                    addPasswordField1.getText().equals(addPasswordField2.getText())){
                errorLabel.setText("Пароли не совпадают или введен пустой пароль");
            }else{
                errorLabel.setText("");
            }
        });

        registrationButton.setSize(150,50);
        registrationButton.setLocation(300, 390);
        registrationButton.addActionListener(event->{
            if(addLoginField.getText().length() < 3){
                errorLabel.setText("Логин не может быть короче 3 символов");
            }
            else if(addPasswordField1 .getText().isEmpty()){
                errorLabel.setText("Пароль не может быть пустым");
            }
            else if(!addPasswordField1.getText().equals(addPasswordField2.getText())){
                errorLabel.setText("Пароли не совпадают");
            }
            else{
                errorLabel.setText("Отправлен запрос серверу");
                controller.buttonRegistrationNewAccount(addLoginField.getText(), addPasswordField1.getText());
            }
        });

        backButton.setSize(150,30);
        backButton.setLocation(300,450);
        backButton.addActionListener(event ->{
            controller.activateViewHandler(IModel.ModelState.loginFrame);
        });

        myPanel.add(addLoginField);
        myPanel.add(addPasswordField1);
        myPanel.add(addPasswordField2);
        myPanel.add(registrationButton);
        myPanel.add(backButton);
        myPanel.add(errorLabel);

        this.add(myPanel);
    }

    @Override
    public void refresh() {
        IModel.RegistrationState state = model.getRegistrationState();

        if(state == IModel.RegistrationState.none){
            errorLabel.setText("");
        }
        else if(state == IModel.RegistrationState.forbidden){
            errorLabel.setText("Логин занят");
        }else if(state == IModel.RegistrationState.success){
            JOptionPane.showMessageDialog(this,"Пользователь создан!");
            errorLabel.setText("Зарегистирован");
        }
    }
}
