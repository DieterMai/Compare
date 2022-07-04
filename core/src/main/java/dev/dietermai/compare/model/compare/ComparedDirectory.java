package dev.dietermai.compare.model.compare;

import java.util.List;

import dev.dietermai.compare.model.file.Directory;

public record ComparedDirectory(String name, List<Directory> files)
implements ComparedCommonFile<Directory>
{
	public static ComparedDirectory of(String name, List<Directory> files) {
		return new ComparedDirectory(name, files);
	}
	
	public boolean perfect() {
		return existense();
	}

}
