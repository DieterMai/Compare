package dev.dietermai.compare;

import dev.dietermai.compare.model.FileTree;
import dev.dietermai.compare.service.FSService;

public class FileTreeBuilder {
	
	
	FileTreeBuilder(FSService fs){
	}

	public FileTree buildFileTreeOf(String ROOT_PATH) {
		return new FileTree(ROOT_PATH);
	}
}
