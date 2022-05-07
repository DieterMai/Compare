package dev.dietermai.compare.model;

public class FileTree {

	private final RootDirectory rootDirectory;
	
	public FileTree(String rootPath) {
		this.rootDirectory = RootDirectory.of(rootPath);
	}

	public RootDirectory root() {
		return rootDirectory;
	}

}
