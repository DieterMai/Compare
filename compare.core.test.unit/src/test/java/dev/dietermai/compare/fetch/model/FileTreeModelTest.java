package dev.dietermai.compare.fetch.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class FileTreeModelTest {

	@Test
	void testMethodRootReturnsRoot() {
		File root = new File("root");
		FileTreeModel fileTreeModel = new FileTreeModel(root, Map.of(), Map.of());
		assertEquals(root, fileTreeModel.root());
	}

	@Test
	void testGetRegularFileOfEmptyRootReturnsEmptySet() {
		FileTreeModel fileTreeModel = new FileTreeModel(new File("root"), Map.of(), Map.of());

		Set<RegularFileModel> regularFiles = fileTreeModel.getRegularFiles(".");
		assertEquals(Set.of(), regularFiles);
	}

	@Test
	void testGetRegularFileOfRootReturnsContainingFiles() {
		var rootDirs = toDirectoriesModelSet("dir1", "dir2", "dir3");
		var rootFiles = toRegFileModelSet("fileRootA", "fileRootB", "fileRootC");
		var dir1Files = toRegFileModelSet("file1A", "file1B", "file1C");
		var dir2Files = toRegFileModelSet("file1A", "file1B", "file1C");
		var dir3Files = toRegFileModelSet();

		var filesMap = Map.of(".", rootFiles, "dir1", dir1Files, "dir2", dir2Files, "dir3", dir3Files);
		var dirMap = Map.of(".", rootDirs);

		FileTreeModel fileTreeModel = new FileTreeModel(new File("root"), filesMap, dirMap);

		assertEquals(rootFiles, fileTreeModel.getRegularFiles("."));
		assertEquals(dir1Files, fileTreeModel.getRegularFiles("dir1"));
		assertEquals(dir2Files, fileTreeModel.getRegularFiles("dir2"));
		assertEquals(dir3Files, fileTreeModel.getRegularFiles("dir3"));
	}

	@Test
	void testGetDirectories() {
//		fail();
	}

	@Test
	void testGetDirectoriesOfEmptyRootReturnsEmptySet() {
		FileTreeModel fileTreeModel = new FileTreeModel(new File("root"), Map.of(), Map.of());

		Set<DirectoryModel> directories = fileTreeModel.getDirectories(".");
		assertEquals(Set.of(), directories);
	}

	private Set<RegularFileModel> toRegFileModelSet(String... names) {
		return Arrays.stream(names).map(FileModel::new).map(RegularFileModel::new).collect(Collectors.toSet());
	}

	private Set<DirectoryModel> toDirectoriesModelSet(String... names) {
		return Arrays.stream(names).map(FileModel::new).map(DirectoryModel::new).collect(Collectors.toSet());
	}

}
