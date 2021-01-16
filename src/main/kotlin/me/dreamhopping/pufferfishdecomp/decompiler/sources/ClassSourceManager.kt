package me.dreamhopping.pufferfishdecomp.decompiler.sources

import me.dreamhopping.pufferfishdecomp.decompiler.sources.impl.FolderClassSource
import me.dreamhopping.pufferfishdecomp.decompiler.sources.impl.JarClassSource

object ClassSourceManager {
    private val sources: MutableList<ClassSource> = mutableListOf()

    init {
        sources.add(JarClassSource())
        sources.add(FolderClassSource())
    }

    fun getClassSourceForFileName(fileName: String): ClassSource? {
        return sources.firstOrNull { it.supportedExtensions.any { ext -> fileName.endsWith(ext) } }
    }
}