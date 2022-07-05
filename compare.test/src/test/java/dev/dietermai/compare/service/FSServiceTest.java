package dev.dietermai.compare.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.dietermai.compare.core.error.ICompareErrorHandler;
import dev.dietermai.compare.core.model.file.Directory;
import dev.dietermai.compare.core.model.file.FileTreeRoot;
import dev.dietermai.compare.core.model.file.ICommonFile;
import dev.dietermai.compare.core.model.file.IParentFile;
import dev.dietermai.compare.core.model.file.RegularFile;
import dev.dietermai.compare.service.wrapper.FilesWrapper;

@ExtendWith(MockitoExtension.class)
class FSServiceTest {
	public static enum FileType {
		regular, directory, none;
	}

	@Mock
	FilesWrapper files;

	@Mock
	ICompareErrorHandler errorHandler;

	@InjectMocks
	FSService fsService;

	@Test
	void test_getFiles_IOExcpetion() throws IOException {
		// Arrange
		IParentFile parent = FileTreeRoot.of(Path.of("foo", "bar"));
		var ioe = new IOException();
		when(files.newDirectoryStream(parent.path())).thenThrow(ioe);

		// Act
		Set<ICommonFile> actual = fsService.getFiles(parent);

		// Assert
		assertEquals(Set.of(), actual);
		verify(errorHandler).handleError(ioe);
	}

	@Test
	void test_getFiles_pathToFileIsUnsupported() throws IOException {
		// Arrange
		IParentFile parent = FileTreeRoot.of(Path.of("foo", "bar"));
		Path path = mock(Path.class);
		var uoe = new UnsupportedOperationException();
		when(path.toFile()).thenThrow(uoe);
		when(files.newDirectoryStream(parent.path())).thenReturn(List.of(path));

		// Act
		Set<ICommonFile> actual = fsService.getFiles(parent);

		// Assert
		assertEquals(Set.of(), actual);
		verify(errorHandler).handleError(uoe);
	}

	@Test
	void test_getFiles_fileExistsCauseseSecurityException() throws IOException {
		// Arrange
		IParentFile parent = FileTreeRoot.of(Path.of("foo", "bar"));
		var se = new SecurityException();
		Path path = mock(Path.class);
		File file = mock(File.class);
		when(path.toFile()).thenReturn(file);
		when(file.exists()).thenThrow(se);
		when(files.newDirectoryStream(parent.path())).thenReturn(List.of(path));

		// Act
		Set<ICommonFile> actual = fsService.getFiles(parent);

		// Assert
		assertEquals(Set.of(), actual);
		verify(errorHandler).handleError(se);
	}

	@Test
	void test_getFiles_fileDoesNotExist() throws IOException {
		// Arrange
		IParentFile parent = FileTreeRoot.of(Path.of("foo", "bar"));
		Path pathA = mockPath(Boolean.TRUE, "a", FileType.regular);
		Path pathB = mockPath(Boolean.FALSE, "b", FileType.regular);
		Path pathC = mockPath(Boolean.TRUE, "c", FileType.regular);

		when(files.newDirectoryStream(parent.path())).thenReturn(List.of(pathA, pathB, pathC));

		// Act
		Set<ICommonFile> actual = fsService.getFiles(parent);

		// Assert
		RegularFile fileRecordA = new RegularFile(parent, "a");
		RegularFile fileRecordC = new RegularFile(parent, "c");
		assertEquals(Set.of(fileRecordA, fileRecordC), actual);
		verifyNoInteractions(errorHandler);
	}

	@Test
	void test_getFiles_fileIsRegularFileAndDirectory() throws IOException {
		// Arrange
		IParentFile parent = FileTreeRoot.of(Path.of("foo", "bar"));
		Path pathA = mockPath(Boolean.TRUE, "a", FileType.regular);
		Path pathB = mockPath(Boolean.TRUE, "b", FileType.directory);
		Path pathC = mockPath(Boolean.TRUE, "c", FileType.regular);
		Path pathD = mockPath(Boolean.TRUE, "d", FileType.directory);

		when(files.newDirectoryStream(parent.path())).thenReturn(List.of(pathA, pathB, pathC, pathD));

		// Act
		Set<ICommonFile> actual = fsService.getFiles(parent);

		// Assert
		RegularFile fileRecordA = new RegularFile(parent, "a");
		Directory directoryRecordB = new Directory(parent, "b");
		RegularFile fileRecordC = new RegularFile(parent, "c");
		Directory directoryRecordD = new Directory(parent, "d");

		assertEquals(Set.of(fileRecordA, directoryRecordB, fileRecordC, directoryRecordD), actual);
		verifyNoInteractions(errorHandler);
	}

	@Test
	void test_getFiles_fileIsNitherRegularNorDirectory() throws IOException {
		// Arrange
		IParentFile parent = FileTreeRoot.of(Path.of("foo", "bar"));
		Path pathA = mockPath(Boolean.TRUE, "a", FileType.regular);
		Path pathB = mockPath(Boolean.TRUE, "b", FileType.none);
		Path pathC = mockPath(Boolean.TRUE, "c", FileType.regular);

		when(files.newDirectoryStream(parent.path())).thenReturn(List.of(pathA, pathB, pathC));

		// Act
		Set<ICommonFile> actual = fsService.getFiles(parent);

		// Assert
		RegularFile fileRecordA = new RegularFile(parent, "a");
		RegularFile fileRecordC = new RegularFile(parent, "c");
		assertEquals(Set.of(fileRecordA, fileRecordC), actual);
		verify(errorHandler).handleError("File [absolute]/b is not a file and not a directory");
		verifyNoMoreInteractions(errorHandler);
	}

	private Path mockPath(Boolean exist, String name, FileType type) {
		Path path = mock(Path.class);
		File file = mock(File.class);
		when(path.toFile()).thenReturn(file);
		when(file.exists()).thenReturn(exist);
		if (exist) {
			switch (type) {
			case directory:
				when(file.getName()).thenReturn(name);
				when(file.isDirectory()).thenReturn(true);
				break;
			case regular:
				when(file.getName()).thenReturn(name);
				when(file.isFile()).thenReturn(true);
				break;
			case none:
				when(file.getAbsolutePath()).thenReturn("[absolute]/" + name);
				break;
			default:
				break;

			}
		}
		return path;
	}
}
