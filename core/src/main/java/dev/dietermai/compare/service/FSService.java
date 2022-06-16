package dev.dietermai.compare.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import dev.dietermai.compare.model.DirectoryRecord;
import dev.dietermai.compare.model.FileRecord;
import dev.dietermai.compare.model.ICommonFile;
import dev.dietermai.compare.model.IParent;

/**
 * Access provider to the file system
 */
public class FSService {

	public Set<ICommonFile> getFiles(IParent parent) {
		HashSet<ICommonFile> files = new HashSet<>();

		try {
			Files.newDirectoryStream(parent.path()).forEach(path -> addToSet(files, parent, path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return files;
	}

	private void addToSet(Set<ICommonFile> set, IParent parent, Path path) {
		File f = path.toFile(); // TODO UnsupportedOperationException

		if (!f.exists()) {
			return;
		}

		set.add(toCommonFile(parent, f));
	}

	private ICommonFile toCommonFile(IParent parent, File f) {
		if (f.isFile()) {
			return FileRecord.of(parent, f.getName());
		} else if (f.isDirectory()) {
			return DirectoryRecord.of(parent, f.getName());
		} else {
			throw new IllegalArgumentException();
		}
	}

}
