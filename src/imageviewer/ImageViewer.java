package imageviewer;

import imageviewer.architecture.Image;
import imageviewer.architecture.ImagePresenter;
import imageviewer.architecture.NextCommand;
import imageviewer.architecture.PrevCommand;
import imageviewer.architecture.RefreshCommand;
import java.io.*;

public class ImageViewer {

    public static void main(String[] args) throws IOException {
        MainFrame frame = MainFrame.create();
        Image image = new FileImageLoader(new File("images")).load();
        ImagePresenter presenter = ImagePresenter.with(frame.imageDisplay());
        frame.add("next", new NextCommand(presenter));
        frame.add("prev", new PrevCommand(presenter));
        frame.add("refresh", new RefreshCommand(presenter));
        frame.start();
        presenter.show(image);
    }
    
}
