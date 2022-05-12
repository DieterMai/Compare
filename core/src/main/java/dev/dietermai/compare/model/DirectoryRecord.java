package dev.dietermai.compare.model;

public record DirectoryRecord(IParent parent, String name) implements IParent, ICommonFile {

	public static DirectoryRecord of(IParent parent, String name) {
		return new DirectoryRecord(parent, name);
	}

}
