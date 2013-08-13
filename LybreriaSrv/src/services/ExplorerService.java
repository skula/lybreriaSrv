package services;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.skula.lybreria.models.ExplorerItem;

public class ExplorerService {

	public static List<String> getMediasFromDir(String path) {
		List<String> list = new ArrayList<String>();
		File[] childs = new File(path).listFiles();
		for (File file : childs) {
			if (isMediaFile(file.getName())) {
				list.add(file.getPath());
			}
		}
		return list;
	}

	public static List<ExplorerItem> getChildren(File root) {
		List<ExplorerItem> list = new ArrayList<ExplorerItem>();
		ExplorerItem item = new ExplorerItem();
		String path = root.getPath();
		int pos = path.lastIndexOf("/");
		if (pos != 0) {
			item.setPath(path.substring(0, pos));
		} else {
			item.setPath(root.getPath());
		}
		item.setName("..");
		item.setSubtitled(false);
		item.setDirectory(true);
		list.add(item);

		File[] childs = root.listFiles();
		for (File file : childs) {
			item = new ExplorerItem();
			if (file.isDirectory() && !isHiddenDirectory(file)) {
				item.setDirectory(true);
				item.setPath(file.getPath());
				item.setName(file.getName());
				list.add(item);
			} else {
				if (isMediaFile(file.getName())) {
					item.setDirectory(false);
					item.setPath(file.getPath());
					item.setName(file.getName());
					item.setSubtitled(isSubtitles(item.getName(), childs));
					list.add(item);
				}
			}
		}
		Collections.sort(list);
		return list;
	}

	private static boolean isHiddenDirectory(File file) {
		return file.getName().startsWith(".");
	}

	private static boolean isSubtitles(String name, File[] files) {
		String tmp = name.substring(0, name.lastIndexOf("."));
		for (File file : files) {
			String fileName = file.getName();
			if (!file.isDirectory() && fileName.equals(tmp + ".srt")) {
				return true;
			}
		}
		return false;
	}

	private static boolean isMediaFile(String name) {
		boolean res = name.endsWith(".avi");
		res = res || name.endsWith(".mp4");
		res = res || name.endsWith(".mkv");
		res = res || name.endsWith(".mp3");
		return res;
	}
}