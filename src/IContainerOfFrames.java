import javax.swing.*;

/**
 * Created by Artem on 06.05.2017.
 */
public interface IContainerOfFrames {
    public void update();
    public void activate(IModel.ModelState nameOfFrame);
    public JFrame getFrame();
}
