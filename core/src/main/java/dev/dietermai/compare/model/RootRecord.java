package dev.dietermai.compare.model;

import java.nio.file.Path;

public record RootRecord(Path path) implements IParent {
	public static RootRecord of(Path path) {
		return new RootRecord(path);
	}

	@Override
	public String name() {
		return path.toString();
	}

	@Override
	public IParent parent() {
		return null;
	}
}
