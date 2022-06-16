package dev.dietermai.compare.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The FileTree represents the a file system tree from a defined root
 */
public class FileTree {

	private final RootRecord root;
	private final Map<IParent, Set<ICommonFile>> fileMap;

	public FileTree(RootRecord root) {
		this.root = root;
		this.fileMap = new HashMap<>();
		this.fileMap.put(root, new HashSet<>());
	}

	public Set<ICommonFile> filesOf(IParent parent) {
		return fileMap.get(parent);
	}

	public RootRecord root() {
		return root;
	}

	public void add(IParent parent, ICommonFile file) {
		fileMap.get(parent).add(file);
		if (file instanceof DirectoryRecord directory) {
			fileMap.put(directory, new HashSet<>());
		}
	}
}
