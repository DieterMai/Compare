package dev.dietermai.compare.model;

public record FileRecord(RootRecord parent, String name) {
	
	public static FileRecord of(RootRecord parent, String name) {
		return new FileRecord(parent, name);
	}

}
