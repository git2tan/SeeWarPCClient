import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artem on 06.05.2017.
 */
public class MainContainerOfFrames implements IContainerOfFrames {
    private IModel model;
    private IController controller;
    private MyParentFrame curFrame;
    private MyParentFrame startFrame;
    private MyParentFrame loginFrame;
    private FramesGenerator factory;
    private Map<IModel.ModelState, MyParentFrame> loadedFrames;

    public MainContainerOfFrames(IModel model, IController controller){
        this.model = model;
        this.controller = controller;
        factory = new FramesGenerator(model,controller);
        loadedFrames = new HashMap<IModel.ModelState, MyParentFrame>();
    }

    @Override
    public JFrame getFrame() {
        return curFrame;
    }

    @Override
    public void update() {
        curFrame.refresh();
    }

    @Override
    public void activate(IModel.ModelState nameOfFrame){
        if(loadedFrames.containsKey(nameOfFrame))
        {
            if(curFrame.getNameOfFrame() != nameOfFrame)
            {
                MyParentFrame tmp = loadedFrames.get(nameOfFrame);

                curFrame.setVisible(false);
                tmp.setVisible(true);
                curFrame = tmp;
                curFrame.refresh();
            }
        }
        else
        {
            MyParentFrame tmpFrame = factory.createFrame(nameOfFrame);
            if(curFrame != null)
                curFrame.setVisible(false);
            tmpFrame.setVisible(true);
            curFrame = tmpFrame;
            loadedFrames.put(nameOfFrame,tmpFrame);
//            curFrame.refresh();
        }

    }
}
