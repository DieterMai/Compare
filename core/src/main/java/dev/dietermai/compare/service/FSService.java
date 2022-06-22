package dev.dietermai.compare.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import dev.dietermai.compare.error.ICompareErrorHandler;
import dev.dietermai.compare.model.file.Directory;
import dev.dietermai.compare.model.file.ICommonFile;
import dev.dietermai.compare.model.file.IParentFile;
import dev.dietermai.compare.model.file.RegularFile;
import dev.dietermai.compare.service.wrapper.FilesWrapper;

/**
 * Access provider to the file system
 */
public class FSService {
	@Inject
	private FilesWrapper filesWrapper;

	@Inject
	private ICompareErrorHandler errorHandler;

	/**
	 * Returns a set of common files that are located in the given parent
	 * 
	 * @param parent The parent file the children are searched in.
	 * @return A set of common files
	 */
	public Set<ICommonFile> getFiles(IParentFile parent) {
		HashSet<ICommonFile> files = new HashSet<>();

		try {
			filesWrapper.newDirectoryStream(parent.path()).forEach(path -> addToSet(files, parent, path));
		} catch (IOException | SecurityException e) {
			errorHandler.handleError(e);
		}

		return files;
	}

	private void addToSet(Set<ICommonFile> set, IParentFile parent, Path path) {
		try {
			File f = path.toFile();
			if (!f.exists()) {
				return;
			}
			var record = toCommonFile(parent, f);
			if (record != null) {
				set.add(toCommonFile(parent, f));
			}
		} catch (UnsupportedOperationException | SecurityException e) {
			errorHandler.handleError(e);
		}
	}

	private ICommonFile toCommonFile(IParentFile parent, File f) {
		if (f.isFile()) {
			return RegularFile.of(parent, f.getName());
		} else if (f.isDirectory()) {
			return Directory.of(parent, f.getName());
		} else {
			errorHandler.handleError("File " + f.getAbsolutePath() + " is not a file and not a directory");
			return null;
		}
	}
}
