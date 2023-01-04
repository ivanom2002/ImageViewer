package imageviewer.architecture;

public class RefreshCommand implements Command {
    private final ImagePresenter presenter;

    public RefreshCommand(ImagePresenter presenter) {
        this.presenter = presenter;
    }
    
    @Override
    public void execute() {
        presenter.refresh();
    }  
}
