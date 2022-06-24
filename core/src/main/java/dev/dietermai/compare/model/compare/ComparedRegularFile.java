package dev.dietermai.compare.model.compare;

import dev.dietermai.compare.model.file.RegularFile;
import java.util.List;

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
