package me.dreamhopping.pufferfishdecomp.decompiler.impl

import me.dreamhopping.pufferfishdecomp.decompiler.ClassSource
import org.objectweb.asm.ClassReader
import org.objectweb.asm.tree.ClassNode
import java.io.File
import java.util.zip.ZipFile

class JarClassSource(source: File) : ClassSource {
    override val supportedExtensions: List<String> = listOf(".jar", ".zip")

    private val zipFile = ZipFile(source)

    override fun discoverClasses(): List<String> =
        zipFile.entries().toList().filter { it.name.endsWith(".class") }.map { it.name }

    override fun getClass(name: String): ClassNode? {
        val entry = zipFile.getEntry(name) ?: return null
        val node = ClassNode()

        zipFile.getInputStream(entry).use { stream ->
            ClassReader(stream)
        }.accept(node, 0)

        return node
    }
}