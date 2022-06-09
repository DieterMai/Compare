package dev.dietermai.compare.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

	// TODO maybe have only one add-method and use a switch do different things
	// TODO file know its parent, so why have parent as its parameter?
	public void addFile(IParent parent, FileRecord file) {
		fileMap.get(parent).add(file);
	}

	public void addDirectory(IParent parent, DirectoryRecord dir) {
		fileMap.get(parent).add(dir);
		fileMap.put(dir, new HashSet<>());
	}

}
