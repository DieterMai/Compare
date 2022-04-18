package dev.dietermai.compare.fetch.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FileTestModel {

	@Test
	void testPath() {
		String path = "foo/bar";
		FileModel fileModel = new FileModel(path);
		assertEquals(path, fileModel.path());
	}

}
