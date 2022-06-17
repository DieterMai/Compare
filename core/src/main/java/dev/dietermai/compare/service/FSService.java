package dev.dietermai.compare.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import dev.dietermai.compare.bl.CompareBlErrorHandler;
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
		} catch (IOException | SecurityException e) {
			CompareBlErrorHandler.handleError(e);
		}

		return files;
	}

	private void addToSet(Set<ICommonFile> set, IParent parent, Path path) {
		try {
			File f = path.toFile();
			if (!f.exists()) {
				return;
			}
			set.add(toCommonFile(parent, f));
		} catch (UnsupportedOperationException | SecurityException e) {
			CompareBlErrorHandler.handleError(e);
		}
	}

	private ICommonFile toCommonFile(IParent parent, File f) {
		if (f.isFile()) {
			return FileRecord.of(parent, f.getName());
		} else if (f.isDirectory()) {
			return DirectoryRecord.of(parent, f.getName());
		} else {
			CompareBlErrorHandler.handleError("File " + f.getAbsolutePath() + " is not a file and not a directory");
			return null;
		}
	}
}
