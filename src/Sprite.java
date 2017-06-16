import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Artem on 09.05.2017.
 */
public class Sprite extends JComponent {
    IClickedController controller;
    IGameBoard model;
    private Image sprite;
    private int numberOfSprite;
    private static final int DEFAULT_WIDTH = 40;
    private static final int DEFAULT_HEIGHT = 40;
    int coordX;
    int coordY;
    public Sprite(IGameBoard model, IClickedController controller, int coordX, int coordY){
        this.model = model;
        this.controller = controller;
        this.coordX = coordX;
        this.coordY = coordY;

        loadSprite();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(controller != null)
                    controller.mouseClickedSpriteHandler(coordX, coordY);
            }
        });
    }

    public void paintComponent(Graphics g)
    {
        if (sprite == null)
            return;

        g.drawImage(sprite,0,0,null);
    }

    public void loadSprite(){
        numberOfSprite = model.getNumberOfSprite(coordX,coordY);

        String fileName = "c:\\Users\\Artem\\IdeaProjects\\SeeWarClient\\media\\Sprites\\";
        fileName += numberOfSprite + ".png";
        sprite = new ImageIcon(fileName).getImage();
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }

    public void refresh(){
        if(numberOfSprite != model.getNumberOfSprite(coordX,coordY)) {
            loadSprite();
            repaint();
        }
    }
}
