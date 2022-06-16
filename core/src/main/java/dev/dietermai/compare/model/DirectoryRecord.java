package dev.dietermai.compare.model;

import java.nio.file.Path;

/**
 * The DirectoryRecord represents a record of a file system directory
 */
public record DirectoryRecord(IParent parent, String name) implements IParent {

	public static DirectoryRecord of(IParent parent, String name) {
		return new DirectoryRecord(parent, name);
	}

	@Override
	public Path path() {
		return parent().path().resolve(name());
	}

}
