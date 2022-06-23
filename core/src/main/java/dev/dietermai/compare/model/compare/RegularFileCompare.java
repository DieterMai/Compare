package dev.dietermai.compare.model.compare;

import dev.dietermai.compare.model.file.RegularFile;
import java.util.List;

public record RegularFileCompare(String name, List<RegularFile> files) {
	public static RegularFileCompare of(String name, List<RegularFile> files) {
		return new RegularFileCompare(name, files);
	}

	public boolean existense() {
		return files.stream().allMatch(f -> !f.exists());
	}

	public boolean perfect() {
		return existense();
	}

}
