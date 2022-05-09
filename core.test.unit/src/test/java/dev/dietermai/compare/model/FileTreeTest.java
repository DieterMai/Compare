package dev.dietermai.compare.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

class FileTreeTest {
	RootRecord ROOT_A = RootRecord.of("foo");
	RootRecord ROOT_B = RootRecord.of("bar");

	Set<FileRecord> FILES_A = Set.of(FileRecord.of(ROOT_A, "aaa"), FileRecord.of(ROOT_A, "bbb"), FileRecord.of(ROOT_A, "ccc"));
	Set<FileRecord> FILES_B = Set.of(FileRecord.of(ROOT_B, "foo"), FileRecord.of(ROOT_B, "bar"), FileRecord.of(ROOT_B, "cin"));

	@Test
	void test_rootDirectory() {
		assertEquals(ROOT_A, new FileTree(ROOT_A, null).root());
		assertEquals(ROOT_B, new FileTree(ROOT_B, null).root());
	}

	@Test
	void test_getRootFiles() {
		Set<FileRecord> FILES_A = Set.of(FileRecord.of(ROOT_A, "aaa"), FileRecord.of(ROOT_A, "bbb"), FileRecord.of(ROOT_A, "ccc"));
		Set<FileRecord> FILES_B = Set.of(FileRecord.of(ROOT_A, "foo"), FileRecord.of(ROOT_A, "bar"), FileRecord.of(ROOT_A, "cin"));

		assertEquals(FILES_A, new FileTree(ROOT_A, FILES_A).getFiles());
		assertEquals(FILES_B, new FileTree(ROOT_A, FILES_B).getFiles());
	}
}
