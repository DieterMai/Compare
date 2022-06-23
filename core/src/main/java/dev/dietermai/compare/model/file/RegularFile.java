package dev.dietermai.compare.model.file;

import java.nio.file.Path;

/**
 * FileRecord represents a record of a regular file of the file system
 */
public record RegularFile(IParentFile parent, String name) implements ICommonFile {
	public static final RegularFile NULL = RegularFile.of(null, "");

	public static RegularFile of(IParentFile parent, String name) {
		return new RegularFile(parent, name);
	}

	@Override
	public Path path() {
		return parent.path().resolve(name);
	}

	public boolean exists() {
		return this == NULL;
	}
}
