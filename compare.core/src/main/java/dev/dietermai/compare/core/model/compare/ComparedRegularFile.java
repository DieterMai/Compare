package dev.dietermai.compare.core.model.compare;

import java.util.List;

import dev.dietermai.compare.core.model.file.RegularFile;

public record ComparedRegularFile(String name, List<RegularFile> files)
implements ComparedCommonFile<RegularFile>
{
	public static ComparedRegularFile of(String name, List<RegularFile> files) {
		return new ComparedRegularFile(name, files);
	}

	public boolean perfect() {
		return existense();
	}

}
