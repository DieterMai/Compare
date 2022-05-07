package dev.dietermai.compare.model;

public record RootDirectory(String path) {

	public static RootDirectory of(String path) {
		return new RootDirectory(path);
	}

	public String path() {
		return path;
	}

}
