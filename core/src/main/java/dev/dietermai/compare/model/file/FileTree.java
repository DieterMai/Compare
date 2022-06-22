package dev.dietermai.compare.model.file;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The FileTree represents the a file system tree from a defined root
 */
public class FileTree {

	private final FileTreeRoot root;
	private final Map<IParentFile, Set<ICommonFile>> fileMap;

	public FileTree(FileTreeRoot root) {
		this.root = root;
		this.fileMap = new HashMap<>();
		this.fileMap.put(root, new HashSet<>());
	}

	public Set<ICommonFile> filesOf(IParentFile parent) {
		return fileMap.get(parent);
	}

	public FileTreeRoot root() {
		return root;
	}

	public void add(IParentFile parent, ICommonFile file) {
		fileMap.get(parent).add(file);
		if (file instanceof Directory directory) {
			fileMap.put(directory, new HashSet<>());
		}
	}
}
