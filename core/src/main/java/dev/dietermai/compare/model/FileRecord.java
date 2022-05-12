package dev.dietermai.compare.model;

public record FileRecord(IParent parent, String name) implements ICommonFile {

	public static FileRecord of(IParent parent, String name) {
		return new FileRecord(parent, name);
	}

}
