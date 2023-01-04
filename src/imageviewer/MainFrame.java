package imageviewer;

import imageviewer.architecture.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    private final Map<String,Command> commands = new HashMap<>();
    private final ImagePanel imageDisplay;

    public static MainFrame create() throws IOException {
        return new MainFrame();
    }
    private MainFrame() throws IOException {
        this.setTitle("Image Viewer");
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(imageDisplay = imagePanel());
        this.add(bottons(), BorderLayout.SOUTH);
        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                commands.get("refresh").execute();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }
            
        });
    }
    
   
    private JPanel bottons() {
        JPanel bottons = new JPanel();
        bottons.add(prevButton());
        bottons.add(nextButton());
        return bottons;
    }
    
    private Component prevButton() {
        final JButton button = new JButton("<");
        button.addActionListener((ActionEvent ae) -> {
            commands.get("prev").execute();
        });
        return button;
    }
    
    private Component nextButton() {
        final JButton button = new JButton(">");
        button.addActionListener((ActionEvent ae) -> {
            commands.get("next").execute();
        });
        return button;
    }

    void start() {
        this.setVisible(true);
    }
    
    public void add(String name, Command command) {
        commands.put(name, command);
    }

    private ImagePanel imagePanel()  {
        return new ImagePanel();
    }

    ImageDisplay imageDisplay() {
        return imageDisplay;
    }
    
}
