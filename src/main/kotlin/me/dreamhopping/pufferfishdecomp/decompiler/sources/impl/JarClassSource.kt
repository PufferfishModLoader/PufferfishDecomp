package me.dreamhopping.pufferfishdecomp.decompiler.sources.impl

import me.dreamhopping.pufferfishdecomp.decompiler.sources.ClassSource
import org.objectweb.asm.ClassReader
import org.objectweb.asm.tree.ClassNode
import java.io.File
import java.util.zip.ZipFile

class JarClassSource : ClassSource {
    override val supportedExtensions: List<String> = listOf(".jar", ".zip")

    private lateinit var zipFile: ZipFile

    override fun discoverClasses(source: File): List<String> {
        zipFile = ZipFile(source)
        return zipFile.entries().toList().filter { it.name.endsWith(".class") }.map { it.name }
    }

    override fun getClass(name: String): ClassNode? {
        val entry = zipFile.getEntry(name) ?: return null
        val node = ClassNode()

        zipFile.getInputStream(entry).use { stream ->
            ClassReader(stream)
        }.accept(node, 0)

        return node
    }
}