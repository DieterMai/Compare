package dev.dietermai.compare.core.api.service;

import java.util.Set;

import dev.dietermai.compare.core.model.file.ICommonFile;
import dev.dietermai.compare.core.model.file.IParentFile;

public interface IFSService {
	Set<ICommonFile> getFiles(IParentFile parent);
}
