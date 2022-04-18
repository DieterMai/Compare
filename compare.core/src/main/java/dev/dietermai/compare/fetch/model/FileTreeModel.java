package dev.dietermai.compare.fetch.model;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public record FileTreeModel(File root, Map<String, Set<RegularFileModel>> fileMap, Map<String, Set<DirectoryModel>> dirMap) {

	public FileTreeModel(File root, Map<String, Set<RegularFileModel>> fileMap, Map<String, Set<DirectoryModel>> dirMap) {
		this.root = root;
		this.fileMap = Objects.requireNonNullElse(fileMap, Map.of());
		this.dirMap = Objects.requireNonNullElse(dirMap, Map.of());
	}

	public Set<RegularFileModel> getRegularFiles(String dirName) {
		return fileMap.getOrDefault(dirName, Set.of());
	}

	public Set<DirectoryModel> getDirectories(String string) {
		return Set.of();
	}
}
