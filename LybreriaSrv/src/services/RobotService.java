package services;

import java.awt.AWTException;
import java.awt.Robot;

public class RobotService {
	private static Robot robot;

	static {
		try {
			robot = new Robot();
		} catch (AWTException e) {
		}
	}

	public static void main(String args[]) {

	}

	public static void typeKeys(int keys[]) {
		for (int i : keys) {
			robot.keyPress(i);
		}

		for (int i : keys) {
			robot.keyRelease(i);
		}
	}

	public static void typeKey(int key) {
		robot.keyPress(key);
		robot.keyRelease(key);
	}
}