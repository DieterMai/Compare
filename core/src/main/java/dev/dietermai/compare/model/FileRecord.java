package dev.dietermai.compare.model;

import java.nio.file.Path;

/**
 * FileRecord represents a record of a regular file of the file system
 */
public record FileRecord(IParent parent, String name) implements ICommonFile {

	public static FileRecord of(IParent parent, String name) {
		return new FileRecord(parent, name);
	}

	@Override
	public Path path() {
		return parent.path().resolve(name);
	}

}
