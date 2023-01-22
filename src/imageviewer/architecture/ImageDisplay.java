package imageviewer.architecture;

public interface ImageDisplay {

    public int width();
    public int height();
    public void clear();
    public void paintImage(byte[] data, ImageWindow window);
    public void onDragged(OffsetEvent event);
    public void onReleased(OffsetEvent event);
    
    public interface OffsetEvent {
        static void Null(int offset) {}
        void handle(int offset);
    }
}
