package mindstorm;

import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import mindstorm.tools.Engine;

public final class Application {

    public static void main(String[] args) {
        Engine engine = new Engine(SensorPort.S1, MotorPort.A, MotorPort.B);

        Program.colorFollowing(engine);

        engine.close();
    }

}
