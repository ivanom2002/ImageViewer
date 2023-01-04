package imageviewer;

import imageviewer.architecture.Command;
import imageviewer.architecture.ImageDisplay;
import imageviewer.architecture.ImageWindow;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements ImageDisplay{
    private final List<Order> orders;
    private OffsetEvent onDragged = OffsetEvent::Null;
    private OffsetEvent onReleased = OffsetEvent::Null;
    private int x;

    public ImagePanel() {
        this.orders = new ArrayList<>();
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                onReleased.handle(e.getX()-x);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                onDragged.handle(e.getX()-x);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
    }

    @Override
    public void clear() {
        this.orders.clear();
        repaint();
    }

    @Override
    public void paintImage(byte[] data, ImageWindow window) {
        this.orders.add(new Order(data, window));
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        clean(g);
        for (Order order : orders) 
            try {
                order.paint(g);
            } catch (IOException ex) {
            }
    }

    private void clean(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    @Override
    public int width() {
        return this.getWidth();
    }

    @Override
    public int height() {
        return this.getHeight();
    }

    @Override
    public void onDragged(OffsetEvent event) {
        this.onDragged = event;
    }

    @Override
    public void onReleased(OffsetEvent event) {
        this.onReleased = event;
    }
    
    private static class Order {
        public final byte[] data;
        public final ImageWindow window;

        public Order(byte[] data, ImageWindow window) {
            this.data = data;
            this.window = window;
        }


        private void paint(Graphics g) throws IOException {
            BufferedImage image = ImageIO.read(inputStream());
            g.drawImage(image, window.x(), window.y(), window.width(), window.height(), null);
        }

        private InputStream inputStream() {
            return new ByteArrayInputStream(data);
        }
    }
}
