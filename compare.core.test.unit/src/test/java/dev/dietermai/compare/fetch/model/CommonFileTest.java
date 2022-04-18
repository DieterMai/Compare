package dev.dietermai.compare.fetch.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CommonFileTest {

	@Test
	void testPath() {
		String path = "foo/bar";
		CommonFile fileModel = new CommonFile(path);
		assertEquals(path, fileModel.path());
	}

}
