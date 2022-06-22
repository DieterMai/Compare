package dev.dietermai.compare.model.file;

import java.nio.file.Path;

/**
 * Record of a root file of the FileTree
 */
public record FileTreeRoot(Path path) implements IParentFile {
	public static FileTreeRoot of(Path path) {
		return new FileTreeRoot(path);
	}

	@Override
	public String name() {
		return path.toAbsolutePath().toString();
	}

	@Override
	public IParentFile parent() {
		return null;
	}
}
