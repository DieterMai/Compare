package dev.dietermai.compare.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class FileTreeTest {
	@Test
	void test_root_afterConsturctore() {
		RootRecord rootA = RootRecord.of(Path.of("foo"));
		RootRecord rootB = RootRecord.of(Path.of("bar"));

		assertEquals(rootA, new FileTree(rootA).root());
		assertEquals(rootB, new FileTree(rootB).root());
	}

	@Test
	void test_filesOf_afterConsturctore() {
		RootRecord rootA = RootRecord.of(Path.of("foo"));

		assertEquals(Set.of(), new FileTree(rootA).filesOf(rootA));
	}

	@Test
	void test_addFile_filesOf() {
		RootRecord root = RootRecord.of(Path.of("foo"));
		Set<ICommonFile> rootContent = createFiles(root, "aaa", "bbb", "c");

		FileTree tree = new FileTree(root);
		rootContent.forEach(f -> tree.add(root, (FileRecord) f));

		assertEquals(rootContent, tree.filesOf(root));
	}

	@Test
	void test_complexTree() {
		RootRecord ROOT = RootRecord.of(Path.of("root"));
		DirectoryRecord dirA = DirectoryRecord.of(ROOT, "a");
		DirectoryRecord dirAA = DirectoryRecord.of(dirA, "aa");
		DirectoryRecord dirB = DirectoryRecord.of(ROOT, "b");
		DirectoryRecord dirD = DirectoryRecord.of(ROOT, "d");
		FileRecord fileC = FileRecord.of(ROOT, "c");
		FileRecord fileAB = FileRecord.of(dirA, "ab");
		FileRecord fileBA = FileRecord.of(dirB, "ba");
		FileRecord fileAAA = FileRecord.of(dirAA, "aaa");

		FileTree fileTree = new FileTree(ROOT);
		fileTree.add(ROOT, dirA);
		fileTree.add(dirA, dirAA);
		fileTree.add(ROOT, dirB);
		fileTree.add(ROOT, dirD);
		fileTree.add(ROOT, fileC);
		fileTree.add(dirA, fileAB);
		fileTree.add(dirB, fileBA);
		fileTree.add(dirAA, fileAAA);

		assertEquals(ROOT, fileTree.root());
		assertEquals(Set.of(dirA, dirB, fileC, dirD), fileTree.filesOf(ROOT));
		assertEquals(Set.of(dirAA, fileAB), fileTree.filesOf(dirA));
		assertEquals(Set.of(fileAAA), fileTree.filesOf(dirAA));
		assertEquals(Set.of(fileBA), fileTree.filesOf(dirB));
		assertEquals(Set.of(), fileTree.filesOf(dirD));
	}

	private Set<ICommonFile> createFiles(RootRecord parent, String... names) {
		Set<ICommonFile> files = new HashSet<>();
		for (String name : names) {
			files.add(FileRecord.of(parent, name));
		}
		return files;
	}
}