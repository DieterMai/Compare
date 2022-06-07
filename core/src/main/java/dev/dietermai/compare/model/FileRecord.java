package dev.dietermai.compare.model;

import java.nio.file.Path;

public record FileRecord(IParent parent, String name) implements ICommonFile {

	public static FileRecord of(IParent parent, String name) {
		return new FileRecord(parent, name);
	}

	@Override
	public Path path() {
		return parent.path().resolve(name);
	}

}
