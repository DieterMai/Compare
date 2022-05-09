package dev.dietermai.compare.model;

import java.util.Set;

public class FileTree {

	private final RootRecord rootDirectory;
	private final Set<FileRecord> files;

	public FileTree(RootRecord rootDirectory, Set<FileRecord> files) {
		this.rootDirectory = rootDirectory;
		this.files = files;
	}

	public RootRecord root() {
		return rootDirectory;
	}

	public Set<FileRecord> getFiles() {
		return files;
	}

}
