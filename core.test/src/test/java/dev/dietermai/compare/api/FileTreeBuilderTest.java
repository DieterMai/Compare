package dev.dietermai.compare.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.dietermai.compare.core.bl.FileTreeBuilder;
import dev.dietermai.compare.core.model.file.Directory;
import dev.dietermai.compare.core.model.file.FileTree;
import dev.dietermai.compare.core.model.file.FileTreeRoot;
import dev.dietermai.compare.core.model.file.ICommonFile;
import dev.dietermai.compare.core.model.file.IParentFile;
import dev.dietermai.compare.core.model.file.RegularFile;
import dev.dietermai.compare.core.service.FSService;

@ExtendWith(MockitoExtension.class)
class FileTreeBuilderTest {

	@Mock
	FSService fs;

	@InjectMocks
	FileTreeBuilder builder;

	@ParameterizedTest
	@ValueSource(strings = { "aaa", "bbb" })
	void test_build_root(String value) {
		FileTreeRoot root = FileTreeRoot.of(Path.of("foo", value));
		FileTree tree = builder.build(Paths.get("foo", value));
		assertEquals(root, tree.root());
	}

	@Test
	void test_build_emptyRoot() {
		FileTree tree = builder.build(Paths.get("foo", "bar"));
		assertEquals(Set.of(), tree.filesOf(tree.root()));
	}

	@Test
	void test_build_rootWithFiles() {
		FileTreeRoot root = FileTreeRoot.of(Path.of("foo", "bar"));
		Set<ICommonFile> files = files(root, "aaa", "bbb", "ccc", "ddd");
		when(fs.getFiles(root)).thenReturn(files);

		FileTree tree = builder.build(Paths.get("foo", "bar"));
		assertEquals(files, tree.filesOf(tree.root()));
	}

	@Test
	void test_build_deeplyNestedTree() {
		Path rootPath = Path.of("foo", "bar");

		// setup expected
		FileTreeRoot root = FileTreeRoot.of(rootPath);
		RegularFile fileA = RegularFile.of(root, "a");
		Directory dirB = Directory.of(root, "b");
		Directory dirC = Directory.of(root, "c");
		RegularFile fileCA = RegularFile.of(dirC, "ca");
		Directory dirCB = Directory.of(dirC, "cb");
		Directory dirCC = Directory.of(dirC, "cc");
		Directory dirCBA = Directory.of(dirCB, "cba");

		Set<ICommonFile> filesInRoot = Set.of(fileA, dirB, dirC);
		Set<ICommonFile> filesInDirC = Set.of(fileCA, dirCB, dirCC);
		Set<ICommonFile> filesInDirCB = Set.of(dirCBA);

		// setup mock
		when(fs.getFiles(root)).thenReturn(filesInRoot);
		when(fs.getFiles(dirB)).thenReturn(Set.of());
		when(fs.getFiles(dirC)).thenReturn(filesInDirC);
		when(fs.getFiles(dirCB)).thenReturn(filesInDirCB);
		when(fs.getFiles(dirCC)).thenReturn(Set.of());
		when(fs.getFiles(dirCBA)).thenReturn(Set.of());

		FileTree tree = builder.build(rootPath);

		assertEquals(root, tree.root());
		assertEquals(filesInRoot, tree.filesOf(root));
		assertEquals(Set.of(), tree.filesOf(dirB));
		assertEquals(filesInDirC, tree.filesOf(dirC));
		assertEquals(filesInDirCB, tree.filesOf(dirCB));
		assertEquals(Set.of(), tree.filesOf(dirCC));
		assertEquals(Set.of(), tree.filesOf(dirCBA));
	}

	private Set<ICommonFile> files(IParentFile parent, String... names) {
		return Stream.of(names).map(n -> RegularFile.of(parent, n)).collect(Collectors.toSet());
	}
}
