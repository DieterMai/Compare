package dev.dieter.compare.jdk.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * This tests whether java.io.File behaves as expected
 */
class FileTest {

	public static final String BASE_PATH = "./testGround/";
	public static final String DIR_EMPTY = BASE_PATH + "emptyDirectory";
	public static final String DIR_FILES = BASE_PATH + "directoryWithFiles";
	public static final String DIR_DIRS = BASE_PATH + "directoryWithDirectories";
	public static final String DIR_HIDDEN = BASE_PATH + "directoryWithHiddenFiles";

	@BeforeAll
	static void beforeAll() {
		new File(DIR_EMPTY).mkdir();
	}

	@Test
	void testEmptyDirectory() {
		assertArrayEquals(new String[0], new File(DIR_EMPTY).list());
	}

	@Test
	void testDirectoryWithFiles() {
		String[] expected = { "fileA", "fileB", "fileC" };
		assertArrayEquals(expected, new File(DIR_FILES).list());
	}

	@Test
	void testDirectoryWithDirectories() {
		String[] expected = { "dirA", "dirB", "dirC" };
		assertArrayEquals(expected, new File(DIR_DIRS).list());
	}

	@Test
	void testDirectoryWithHiddenFile() {
		String[] expected = { ".hidden" };
		assertArrayEquals(expected, new File(DIR_HIDDEN).list());
	}
}
