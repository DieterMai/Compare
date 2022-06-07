package dev.dietermai.compare.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class FileTreeTest {
	@Test
	void test_rootDirectory() {
		RootRecord rootA = RootRecord.of(Path.of("foo"));
		RootRecord rootB = RootRecord.of(Path.of("bar"));

		assertEquals(rootA, new FileTree(rootA).root());
		assertEquals(rootB, new FileTree(rootB).root());
	}

	@Test
	void test_addFile_filesOf() {
		RootRecord root = RootRecord.of(Path.of("foo"));
		Set<ICommonFile> files = createFiles(RootRecord.of(Path.of("foo")), "aaa", "bbb", "c");

		FileTree tree = new FileTree(root);
		files.forEach(f -> tree.addFile(root, (FileRecord) f));

		assertEquals(files, tree.filesOf(root));
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
		fileTree.addDirectory(ROOT, dirA);
		fileTree.addDirectory(dirA, dirAA);
		fileTree.addDirectory(ROOT, dirB);
		fileTree.addDirectory(ROOT, dirD);
		fileTree.addFile(ROOT, fileC);
		fileTree.addFile(dirA, fileAB);
		fileTree.addFile(dirB, fileBA);
		fileTree.addFile(dirAA, fileAAA);

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
