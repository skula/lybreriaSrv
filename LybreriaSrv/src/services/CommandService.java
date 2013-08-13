package services;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.skula.lybreria.models.Command;
import com.skula.lybreria.models.ExplorerItem;

import definitions.Definitions;

public class CommandService {

	public static void main(String args[]) {

	}

	public static List<ExplorerItem> exec(Command cmd) {
		switch (cmd.getType()) {
		case Definitions.TYPE_EXPLORE:
			return ExplorerService.getChildren(new File(cmd.getArgument()));
		case Definitions.TYPE_PLAY_SINGLE:
			SMPlayerService.play(cmd.getArgument());
			return new ArrayList<ExplorerItem>();
		case Definitions.TYPE_PLAY_DIR:
			SMPlayerService.playDirectory(cmd.getArgument());
			return new ArrayList<ExplorerItem>();
		case Definitions.TYPE_QUEUE_SINGLE:
			SMPlayerService.queueSingle(cmd.getArgument());
			return new ArrayList<ExplorerItem>();
		case Definitions.TYPE_QUEUE_DIR:
			SMPlayerService.queueDirectory(cmd.getArgument());
			return new ArrayList<ExplorerItem>();
		case Definitions.TYPE_EXEC_CMD:
			handleCommand(cmd.getArgument());
			return new ArrayList<ExplorerItem>();
		default:
			return new ArrayList<ExplorerItem>();
		}
	}

	private static void handleCommand(String arg) {
		if (arg.equals(Definitions.CMD_PREVIOUS)) {
			RobotService.typeKey(KeyEvent.VK_LESS);
			System.out.println("Previous");
		} else if (arg.equals(Definitions.CMD_NEXT)) {
			RobotService.typeKeys(new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_LESS});
			System.out.println("Next");
		} else if (arg.equals(Definitions.CMD_PLAYPAUSE)) {
			 RobotService.typeKey(KeyEvent.VK_SPACE);
			System.out.println("Play/Pause");
		} else if (arg.equals(Definitions.CMD_STOP)) {
			RobotService.typeKey(KeyEvent.VK_Q);
			System.out.println("Stop");
		} else if (arg.equals(Definitions.CMD_SOUND_MINUS)) {
			 RobotService.typeKeys(new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_SLASH});
			System.out.println("Sound minus");
		} else if (arg.equals(Definitions.CMD_SOUND_PLUS)) {
			RobotService.typeKey(KeyEvent.VK_MULTIPLY);
			System.out.println("Sound plus");
		}

	}
}