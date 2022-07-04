package dev.dietermai.compare.model.compare;

import java.util.Set;

import dev.dietermai.compare.model.file.ICommonFile;

public record ComparedTree() {

	public boolean isEmpty() {
		return true;
	}

	public Set<ComparedCommonFile<? extends ICommonFile>> getRootChildren() {
		return Set.of();
	}

}
