package dev.dietermai.compare.model;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

class DirectoryRecordTest {

	@Test
	void test_name() {
		RootRecord parent = RootRecord.of(Path.of("root"));

		assertEquals("foo", DirectoryRecord.of(parent, "foo").name());
		assertEquals("bar", DirectoryRecord.of(parent, "bar").name());
	}

	@Test
	void test_parent() {
		RootRecord root = RootRecord.of(Path.of("root"));
		DirectoryRecord dirA = DirectoryRecord.of(root, "dirA");
		DirectoryRecord dirB = DirectoryRecord.of(dirA, "dirB");
		DirectoryRecord dirC = DirectoryRecord.of(dirB, "dirC");

		assertEquals(root, dirA.parent());
		assertEquals(dirA, dirB.parent());
		assertEquals(dirB, dirC.parent());
	}

	@Test
	void test_path() {
		RootRecord root = RootRecord.of(Path.of("root"));
		DirectoryRecord dirA = DirectoryRecord.of(root, "dirA");
		DirectoryRecord dirB = DirectoryRecord.of(dirA, "dirB");
		DirectoryRecord dirC = DirectoryRecord.of(dirB, "dirC");

		assertEquals(Path.of("root", "dirA"), dirA.path());
		assertEquals(Path.of("root", "dirA", "dirB"), dirB.path());
		assertEquals(Path.of("root", "dirA", "dirB", "dirC"), dirC.path());

	}
}
