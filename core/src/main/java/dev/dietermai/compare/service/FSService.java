package dev.dietermai.compare.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import dev.dietermai.compare.bl.ICompareErrorHandler;
import dev.dietermai.compare.model.DirectoryRecord;
import dev.dietermai.compare.model.FileRecord;
import dev.dietermai.compare.model.ICommonFile;
import dev.dietermai.compare.model.IParent;
import dev.dietermai.compare.service.wrapper.FilesWrapper;

/**
 * Access provider to the file system
 */
public class FSService {
	@Inject
	private final FilesWrapper filesWrapper;

	@Inject
	private final ICompareErrorHandler errorHandler;

	public FSService(FilesWrapper filesWrapper, ICompareErrorHandler errorHandler) {
		this.filesWrapper = filesWrapper;
		this.errorHandler = errorHandler;
	}

	/**
	 * Returns a set of common files that are located in the given parent
	 * 
	 * @param parent The parent file the children are searched in.
	 * @return A set of common files
	 */
	public Set<ICommonFile> getFiles(IParent parent) {
		HashSet<ICommonFile> files = new HashSet<>();

		try {
			filesWrapper.newDirectoryStream(parent.path()).forEach(path -> addToSet(files, parent, path));
		} catch (IOException | SecurityException e) {
			errorHandler.handleError(e);
		}

		return files;
	}

	private void addToSet(Set<ICommonFile> set, IParent parent, Path path) {
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

	private ICommonFile toCommonFile(IParent parent, File f) {
		if (f.isFile()) {
			return FileRecord.of(parent, f.getName());
		} else if (f.isDirectory()) {
			return DirectoryRecord.of(parent, f.getName());
		} else {
			errorHandler.handleError("File " + f.getAbsolutePath() + " is not a file and not a directory");
			return null;
		}
	}
}
