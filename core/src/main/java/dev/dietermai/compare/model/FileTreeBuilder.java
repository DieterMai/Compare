package dev.dietermai.compare.model;

import dev.dietermai.compare.service.FSService;

public class FileTreeBuilder {

	FileTreeBuilder(FSService fs) {
	}

	public FileTree buildFileTreeOf(String ROOT_PATH) {
		return new FileTree(RootRecord.of(ROOT_PATH));
	}
}
