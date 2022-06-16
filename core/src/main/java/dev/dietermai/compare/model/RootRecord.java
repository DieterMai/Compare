package dev.dietermai.compare.model;

import java.nio.file.Path;

/**
 * Record of a root file of the FileTree
 */
public record RootRecord(Path path) implements IParent {
	public static RootRecord of(Path path) {
		return new RootRecord(path);
	}

	@Override
	public String name() {
		return path.toAbsolutePath().toString();
	}

	@Override
	public IParent parent() {
		return null;
	}
}
