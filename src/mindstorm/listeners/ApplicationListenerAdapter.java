package mindstorm.listeners;

public abstract class ApplicationListenerAdapter extends ApplicationListener {
    @Override
    public void start() {
    }

    @Override
    public void act() {
    }

    @Override
    public void end() {
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
