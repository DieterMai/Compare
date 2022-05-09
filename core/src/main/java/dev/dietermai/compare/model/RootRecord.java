package dev.dietermai.compare.model;

public record RootRecord(String path) {

	public static RootRecord of(String path) {
		return new RootRecord(path);
	}

	public String path() {
		return path;
	}

}
