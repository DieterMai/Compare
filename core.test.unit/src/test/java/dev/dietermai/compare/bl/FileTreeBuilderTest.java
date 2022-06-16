package dev.dietermai.compare.bl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.dietermai.compare.model.FileRecord;
import dev.dietermai.compare.model.FileTree;
import dev.dietermai.compare.model.ICommonFile;
import dev.dietermai.compare.model.IParent;
import dev.dietermai.compare.model.RootRecord;
import dev.dietermai.compare.service.FSService;

@ExtendWith(MockitoExtension.class)
class FileTreeBuilderTest {

	@Mock
	FSService fs;

	FileTreeBuilder builder;

	@BeforeEach
	void beforeEach() {
		builder = new FileTreeBuilder(fs);
	}

	@ParameterizedTest
	@ValueSource(strings = { "aaa", "bbb" })
	void test_build_root(String value) {
		RootRecord root = RootRecord.of(Path.of("foo", value));
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
		RootRecord root = RootRecord.of(Path.of("foo", "bar"));
		Set<ICommonFile> files = files(root, "aaa", "bbb", "ccc", "ddd");
		when(fs.getFiles(root)).thenReturn(files);

		FileTree tree = builder.build(Paths.get("foo", "bar"));
		assertEquals(files, tree.filesOf(tree.root()));
	}

	private Set<ICommonFile> files(IParent parent, String... names) {
		return Stream.of(names).map(n -> FileRecord.of(parent, n)).collect(Collectors.toSet());
	}
}
