package dev.dietermai.compare.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Set;

import org.junit.jupiter.api.Test;

class FileAccessTest {

	public static final String BASE_PATH = "./testGround/";
	public static final String DIR_EMPTY = BASE_PATH + "emptyDirectory";
	public static final String DIR_FILES = BASE_PATH + "directoryWithFiles";
	public static final String DIR_DIRS = BASE_PATH + "directoryWithDirectories";
	public static final String DIR_HIDDEN = BASE_PATH + "directoryWithHiddenFiles";
	
	
	@Test
	void testGetDirectoryContentReturnsEmptySetForEmtpyDirectory() {
		FileAccess fileAccess = new FileAccess();
		Set<File> content = fileAccess.getDirectoryContent(DIR_EMPTY);
		assertTrue(content.isEmpty());
	}
	
	@Test
	void testGetDirectoryContentReturnsFileSetForFileDirectory() {
		FileAccess fileAccess = new FileAccess();
		Set<File> content = fileAccess.getDirectoryContent(DIR_FILES);
		assertEquals(3, content.size(), content.toString());
		assertTrue(content.contains(new File(DIR_FILES+"/fileA")));
		assertTrue(content.contains(new File(DIR_FILES+"/fileB")));
		assertTrue(content.contains(new File(DIR_FILES+"/fileC")));
	}
}
