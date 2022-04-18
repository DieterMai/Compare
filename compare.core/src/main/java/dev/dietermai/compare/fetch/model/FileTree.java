package dev.dietermai.compare.fetch.model;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public record FileTree(File root, Map<String, Set<RegularFile>> fileMap, Map<String, Set<Directory>> dirMap) {

	public FileTree(File root, Map<String, Set<RegularFile>> fileMap, Map<String, Set<Directory>> dirMap) {
		this.root = root;
		this.fileMap = Objects.requireNonNullElse(fileMap, Map.of());
		this.dirMap = Objects.requireNonNullElse(dirMap, Map.of());
	}

	public Set<RegularFile> getRegularFiles(String dirName) {
		return fileMap.getOrDefault(dirName, Set.of());
	}

	public Set<Directory> getDirectories(String string) {
		return Set.of();
	}
}
