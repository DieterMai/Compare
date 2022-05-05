package dev.dietermai.compare.io;

import java.io.File;
import java.util.Set;

public class FileAccess {

	public Set<File> getDirectoryContent(String dirFiles) {
		File f = new File(dirFiles);
		// TODO check corner cases
		
		return Set.of(f.listFiles());
	}
}
