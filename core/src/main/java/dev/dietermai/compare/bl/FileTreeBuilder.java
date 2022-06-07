package dev.dietermai.compare.bl;

import java.nio.file.Path;
import java.util.Set;

import dev.dietermai.compare.model.FileRecord;
import dev.dietermai.compare.model.FileTree;
import dev.dietermai.compare.model.IParent;
import dev.dietermai.compare.model.RootRecord;
import dev.dietermai.compare.service.FSService;

public class FileTreeBuilder {
	
	private final FSService fs;

	FileTreeBuilder(FSService fs) {
		this.fs = fs;
	}

	public FileTree build(Path path) {
		FileTree tree = new FileTree(RootRecord.of(path));
		
		IParent parent = tree.root();
		
		Set<String> fileNames = fs.getFiles(parent);
		
		fileNames.forEach(name -> tree.addFile(parent, FileRecord.of(parent, name)));
		
		return tree;
	}
}
