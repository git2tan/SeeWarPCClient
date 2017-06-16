import javax.swing.*;

/**
 * Created by Artem on 09.05.2017.
 */
public class GameBoardForFrame extends JPanel {
    private IGameBoard model;
    private IClickedController controller;
    Sprite[][] arrayOfSprites;
    public GameBoardForFrame(IGameBoard model, IClickedController controller){
        this.setLayout(null);
        this.model = model;
        this.controller = controller;
        arrayOfSprites = new Sprite[10][10];
        for(int i = 0; i < 10; i++)
            for(int j = 0; j < 10; j++)
            {
                Sprite one = new Sprite(model, controller,i,j);
                one.setSize(40,40);
                one.setLocation(i*40,j*40);
                one.setVisible(true);
                this.add(one);
                arrayOfSprites[i][j] = one;
            }

    }
    public void refresh(){
        for(int i = 0; i < 10; i++)
            for(int j = 0; j < 10; j++)
            {
                arrayOfSprites[i][j].refresh();
            }
    }
}
