package dev.dietermai.compare.model.file;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class FileTreeTest {
	@Test
	void test_root_afterConsturctore() {
		FileTreeRoot rootA = FileTreeRoot.of(Path.of("foo"));
		FileTreeRoot rootB = FileTreeRoot.of(Path.of("bar"));

		assertEquals(rootA, new FileTree(rootA).root());
		assertEquals(rootB, new FileTree(rootB).root());
	}

	@Test
	void test_filesOf_afterConsturctore() {
		FileTreeRoot rootA = FileTreeRoot.of(Path.of("foo"));

		assertEquals(Set.of(), new FileTree(rootA).filesOf(rootA));
	}

	@Test
	void test_addFile_filesOf() {
		FileTreeRoot root = FileTreeRoot.of(Path.of("foo"));
		Set<ICommonFile> rootContent = createFiles(root, "aaa", "bbb", "c");

		FileTree tree = new FileTree(root);
		rootContent.forEach(f -> tree.add(root, (RegularFile) f));

		assertEquals(rootContent, tree.filesOf(root));
	}

	@Test
	void test_complexTree() {
		FileTreeRoot ROOT = FileTreeRoot.of(Path.of("root"));
		Directory dirA = Directory.of(ROOT, "a");
		Directory dirAA = Directory.of(dirA, "aa");
		Directory dirB = Directory.of(ROOT, "b");
		Directory dirD = Directory.of(ROOT, "d");
		RegularFile fileC = RegularFile.of(ROOT, "c");
		RegularFile fileAB = RegularFile.of(dirA, "ab");
		RegularFile fileBA = RegularFile.of(dirB, "ba");
		RegularFile fileAAA = RegularFile.of(dirAA, "aaa");

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

	private Set<ICommonFile> createFiles(FileTreeRoot parent, String... names) {
		Set<ICommonFile> files = new HashSet<>();
		for (String name : names) {
			files.add(RegularFile.of(parent, name));
		}
		return files;
	}
}
