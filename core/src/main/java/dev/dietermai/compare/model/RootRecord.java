package dev.dietermai.compare.model;

public record RootRecord(String path) implements IParent {

	public static RootRecord of(String path) {
		return new RootRecord(path);
	}

	public String path() {
		return path;
	}
}
