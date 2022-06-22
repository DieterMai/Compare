package dev.dietermai.compare.model;

/**
 * Interface for files that can be parents. At the moment this is only a marker
 * interface
 */
public sealed interface IParentFile extends ICommonFile permits FileTreeRoot, Directory {
}
