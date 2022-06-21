package dev.dietermai.compare.bl;

import java.nio.file.Path;
import java.util.Set;

import javax.inject.Inject;

import dev.dietermai.compare.model.FileTree;
import dev.dietermai.compare.model.ICommonFile;
import dev.dietermai.compare.model.IParent;
import dev.dietermai.compare.model.RootRecord;
import dev.dietermai.compare.service.FSService;

public class FileTreeBuilder {

	@Inject
	private FSService fs;

	public FileTree build(Path path) {
		FileTree tree = new FileTree(RootRecord.of(path));
		buildSubTree(tree, tree.root());
		return tree;
	}

	private void buildSubTree(FileTree tree, IParent parent) {
		Set<ICommonFile> children = fs.getFiles(parent);
		for (ICommonFile child : children) {
			tree.add(parent, child);
			if (child instanceof IParent directory) {
				buildSubTree(tree, directory);
			}
		}

	}
}
