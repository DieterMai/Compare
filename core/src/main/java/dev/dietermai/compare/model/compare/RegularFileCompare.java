package dev.dietermai.compare.model.compare;

import dev.dietermai.compare.model.file.RegularFile;
import java.util.List;

public record RegularFileCompare(String name, List<RegularFile> files) {
	public boolean existense() {
		return files.stream().allMatch(f -> f != null);
	}
	
	public boolean perfect() {
		return existense();
	}
}
