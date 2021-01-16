package me.dreamhopping.pufferfishdecomp.decompiler.sources

import org.objectweb.asm.tree.ClassNode
import java.io.File

interface ClassSource {
    /**
     * Returns a list of entries
     */
    fun discoverClasses(source: File): List<String>

    /**
     * Return class node for entry
     */
    fun getClass(name: String): ClassNode?

    /**
     * The extensions that this class supports
     */
    val supportedExtensions: List<String>
        get() = listOf()
}
