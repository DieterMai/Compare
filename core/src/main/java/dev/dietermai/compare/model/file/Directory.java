package dev.dietermai.compare.model.file;

import java.nio.file.Path;

/**
 * The DirectoryRecord represents a record of a file system directory
 */
public record Directory(IParentFile parent, String name) implements IParentFile {

	public static Directory of(IParentFile parent, String name) {
		return new Directory(parent, name);
	}

	@Override
	public Path path() {
		return parent().path().resolve(name());
	}

}
