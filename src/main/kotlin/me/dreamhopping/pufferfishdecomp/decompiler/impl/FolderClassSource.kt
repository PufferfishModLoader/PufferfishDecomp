package me.dreamhopping.pufferfishdecomp.decompiler.impl

import me.dreamhopping.pufferfishdecomp.decompiler.ClassSource
import org.objectweb.asm.ClassReader
import org.objectweb.asm.tree.ClassNode
import java.io.File

class FolderClassSource(val source: File) : ClassSource {
    override val supportedExtensions: List<String> = listOf("")

    override fun discoverClasses(): List<String> {
        val classes = mutableListOf<String>()

        source.listFiles()?.forEach { file ->
            if (file.name.endsWith(".class")) {
                classes.add(file.absolutePath)
            }
        }

        return classes
    }

    override fun getClass(name: String): ClassNode? {
        val entry = File(name)
        if (!entry.exists()) return null

        val node = ClassNode()
        entry.inputStream().use { stream ->
            ClassReader(stream)
        }.accept(node, 0)

        return node
    }
}
