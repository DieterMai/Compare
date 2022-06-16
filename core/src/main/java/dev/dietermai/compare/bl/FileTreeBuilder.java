package dev.dietermai.compare.bl;

import java.nio.file.Path;
import java.util.Set;

import dev.dietermai.compare.model.FileRecord;
import dev.dietermai.compare.model.FileTree;
import dev.dietermai.compare.model.ICommonFile;
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

		Set<ICommonFile> children = fs.getFiles(parent);

		children.forEach(name -> tree.add(parent, name));

		return tree;
	}
}
