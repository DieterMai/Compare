package dev.dietermai.compare.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FileTreeTest {

	@Test
	void test_rootDirectory() {
		assertEquals(RootDirectory.of("foo"), new FileTree("foo").root());
		assertEquals(RootDirectory.of("bar"), new FileTree("bar").root());
	}

}
