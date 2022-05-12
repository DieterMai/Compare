package dev.dietermai.compare.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DirectoryRecordTest {

	@Test
	void test_name() {
		RootRecord parent = RootRecord.of("root");

		assertEquals("foo", DirectoryRecord.of(parent, "foo").name());
		assertEquals("bar", DirectoryRecord.of(parent, "bar").name());
	}

	@Test
	void test_parent() {
		RootRecord root = RootRecord.of("root");
		DirectoryRecord dirA = DirectoryRecord.of(root, "dirA");
		DirectoryRecord dirB = DirectoryRecord.of(dirA, "dirB");
		DirectoryRecord dirC = DirectoryRecord.of(dirB, "dirC");

		assertEquals(root, dirA.parent());
		assertEquals(dirA, dirB.parent());
		assertEquals(dirB, dirC.parent());
	}
}
