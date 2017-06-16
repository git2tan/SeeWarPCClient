import javax.swing.*;
import java.awt.*;

/**
 * Created by Artem on 09.05.2017.
 */
public class StaticShipComponent extends JComponent {
    private static final int DEFAULT_WIDTH = 40;
    private static final int DEFAULT_HEIGHT = 40;
    private IGame.ShipType shipType;   //количество палуб
    private Image imageOfShip;
    private boolean isOrientationHorizontal;

    public StaticShipComponent(IGame.ShipType type){
        this.shipType = type;
        isOrientationHorizontal = true;
    }

    private void loadImage(){
        if(isOrientationHorizontal) {
            String fileName = "media//StaticShips//";
            fileName += "static_" + (shipType.ordinal() + 1) + ".png";
            imageOfShip = new ImageIcon(fileName).getImage();
        }
        else{
            String fileName = "media//StaticShips//";
            fileName += "static_" + (shipType.ordinal() + 1) + "_v" + ".png";
            imageOfShip = new ImageIcon(fileName).getImage();
        }
    }

    public void paintComponent(Graphics g)
    {
        loadImage();
        if (imageOfShip == null) return;

        g.drawImage(imageOfShip,0,0,null);
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }

    public void setOrientationHorizontal(boolean flag){
        if(flag != isOrientationHorizontal){
            isOrientationHorizontal = flag;
            repaint();
        }
    }
}
