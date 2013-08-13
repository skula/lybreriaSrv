package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class SMPlayerService {

	public static void queueSingle(String path) {

	}

	public static void queueDirectory(String path) {

	}

	public static void play(String path) {
		String[] play = { "/usr/bin/smplayer", path };
		handleProcess(play);
	}

	public static void playDirectory(String path) {
		List<String> list = ExplorerService.getMediasFromDir(path);
		String play[] = new String[list.size() + 1];
		play[0] = "/usr/bin/smplayer";
		for (int i = 0; i < list.size(); i++) {
			play[i + 1] = list.get(i);
		}
		handleProcess(play);
	}

	private static void handleProcess(String args[]) {
		try {
			final Process process = new ProcessBuilder(args).start();

			new Thread() {
				public void run() {
					try {
						BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
						String line = "";
						try {
							while ((line = reader.readLine()) != null) {
							}
						} finally {
							reader.close();
						}
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
			}.start();

			new Thread() {
				public void run() {
					try {
						BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
						String line = "";
						try {
							while ((line = reader.readLine()) != null) {
							}
						} finally {
							reader.close();
						}
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
			}.start();
		} catch (Exception e) {

		}
	}
}