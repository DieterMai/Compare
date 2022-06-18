package dev.dietermai.compare.model;

import java.nio.file.Path;

/**
 * Interface for all files. Each node in a FileTree are at least a ICommonFile
 */
public sealed interface ICommonFile permits IParent, FileRecord

{
	String name();

	IParent parent();

	Path path();
}
