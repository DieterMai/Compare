package dev.dietermai.compare.model;

import java.nio.file.Path;

public interface ICommonFile {
	String name();

	IParent parent();
	
	Path path();
}
