package dev.dietermai.compare.model;

import java.nio.file.Path;

/**
 * Interface for all files. Each node in a FileTree are at least a ICommonFile
 */
public interface ICommonFile {
	String name();

	IParent parent();

	Path path();
	
	default boolean isParent() {
		return this.getClass().getAnnotation(Parent.class) != null;
	}
}
