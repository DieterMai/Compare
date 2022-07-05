package dev.dietermai.compare.core.model.file;

import java.nio.file.Path;

/**
 * The DirectoryRecord represents a record of a file system directory
 */
public record Directory(IParentFile parent, String name) implements IParentFile {
	public static final Directory NULL = Directory.of(null, "");

	public static Directory of(IParentFile parent, String name) {
		return new Directory(parent, name);
	}

	@Override
	public Path path() {
		return parent().path().resolve(name());
	}

	@Override
	public boolean exists() {
		return this == NULL;
	}

}
