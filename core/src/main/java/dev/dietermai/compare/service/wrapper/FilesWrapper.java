package dev.dietermai.compare.service.wrapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesWrapper {

	public Iterable<Path> newDirectoryStream(Path path) throws IOException {
		return Files.newDirectoryStream(path);
	}

}
