import javax.swing.*;

/**
 * Created by Artem on 06.05.2017.
 */
public abstract class MyParentFrame extends JFrame {
    private IModel.ModelState nameOfFrame;
    protected IModel model;
    protected IController controller;
    public MyParentFrame(IModel model, IController controller, IModel.ModelState nameOfFrame){
        this.nameOfFrame = nameOfFrame;
        this.model = model;
        this.controller = controller;
    };
    public abstract void refresh();
    public IModel.ModelState getNameOfFrame(){
        return nameOfFrame;
    }
}
