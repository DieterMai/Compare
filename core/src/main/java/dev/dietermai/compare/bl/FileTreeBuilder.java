package dev.dietermai.compare.bl;

import java.nio.file.Path;
import java.util.Set;

import javax.inject.Inject;

import dev.dietermai.compare.model.FileTree;
import dev.dietermai.compare.model.ICommonFile;
import dev.dietermai.compare.model.IParentFile;
import dev.dietermai.compare.model.FileTreeRoot;
import dev.dietermai.compare.service.FSService;

public class FileTreeBuilder {

	@Inject
	private FSService fs;

	public FileTree build(Path path) {
		FileTree tree = new FileTree(FileTreeRoot.of(path));
		buildSubTree(tree, tree.root());
		return tree;
	}

	private void buildSubTree(FileTree tree, IParentFile parent) {
		Set<ICommonFile> children = fs.getFiles(parent);
		for (ICommonFile child : children) {
			tree.add(parent, child);
			if (child instanceof IParentFile directory) {
				buildSubTree(tree, directory);
			}
		}

	}
}
