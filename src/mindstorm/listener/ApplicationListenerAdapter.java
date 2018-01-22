package mindstorm.listener;

public abstract class ApplicationListenerAdapter implements ApplicationListener {
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
