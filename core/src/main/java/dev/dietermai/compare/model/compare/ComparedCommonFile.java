package dev.dietermai.compare.model.compare;

import java.util.List;

import dev.dietermai.compare.model.file.ICommonFile;

public interface ComparedCommonFile<T extends ICommonFile> {
	List<T> files();
	
	default boolean existense() {
		return files().stream().allMatch(f -> !f.exists());
	}

	boolean perfect();
}
