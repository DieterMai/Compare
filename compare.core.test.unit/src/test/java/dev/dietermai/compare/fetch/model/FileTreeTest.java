package dev.dietermai.compare.fetch.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class FileTreeTest {

	/*
	 * Verifies that the root file given as root is the root of the FileTree 
	 */
	@Test
	void testMethodRootReturnsRoot() {
		File root = new File("root");
		FileTree fileTreeModel = new FileTree(root, Map.of(), Map.of());
		assertEquals(root, fileTreeModel.root());
	}

	
	@Test
	void testGetRegularFileOfEmptyRootReturnsEmptySet() {
		FileTree fileTreeModel = new FileTree(new File("root"), Map.of(), Map.of());

		Set<RegularFile> regularFiles = fileTreeModel.getRegularFiles(".");
		assertEquals(Set.of(), regularFiles);
	}

	@Test
	void testGetRegularFileOfRootReturnsContainingFiles() {
		var rootDirs = toDirectorySet("dir1", "dir2", "dir3");
		var rootFiles = toRegularFileSet("fileRootA", "fileRootB", "fileRootC");
		var dir1Files = toRegularFileSet("file1A", "file1B", "file1C");
		var dir2Files = toRegularFileSet("file1A", "file1B", "file1C");
		var dir3Files = toRegularFileSet();

		var filesMap = Map.of(".", rootFiles, "dir1", dir1Files, "dir2", dir2Files, "dir3", dir3Files);
		var dirMap = Map.of(".", rootDirs);

		FileTree fileTreeModel = new FileTree(new File("root"), filesMap, dirMap);

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
		FileTree fileTreeModel = new FileTree(new File("root"), Map.of(), Map.of());

		Set<Directory> directories = fileTreeModel.getDirectories(".");
		assertEquals(Set.of(), directories);
	}

	private Set<RegularFile> toRegularFileSet(String... names) {
		return Arrays.stream(names).map(CommonFile::new).map(RegularFile::new).collect(Collectors.toSet());
	}

	private Set<Directory> toDirectorySet(String... names) {
		return Arrays.stream(names).map(CommonFile::new).map(Directory::new).collect(Collectors.toSet());
	}

}
