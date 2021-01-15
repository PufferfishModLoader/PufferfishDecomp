package me.dreamhopping.pufferfishdecomp.decompiler

import org.objectweb.asm.tree.ClassNode

interface ClassSource {
    /**
     * Returns a list of entries
     */
    fun discoverClasses(): List<String>

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
