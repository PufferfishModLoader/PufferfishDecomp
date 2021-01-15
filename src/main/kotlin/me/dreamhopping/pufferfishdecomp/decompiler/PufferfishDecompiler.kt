package me.dreamhopping.pufferfishdecomp.decompiler

import me.dreamhopping.pufferfishdecomp.decompiler.impl.FolderClassSource
import me.dreamhopping.pufferfishdecomp.decompiler.impl.JarClassSource
import me.dreamhopping.pufferfishdecomp.logger.Logger
import org.objectweb.asm.Opcodes
import java.io.File

class PufferfishDecompiler(val inputJar: File, val output: File) {
    private val sources: MutableList<ClassSource> = mutableListOf()

    init {
        sources.add(JarClassSource(inputJar))
        sources.add(FolderClassSource(inputJar))
    }

    fun decompile() {
        val source: ClassSource = getClassSourceForFileName(inputJar.name) ?: error("This source isn't supported")

        source.discoverClasses().forEach {
            // Get ClassNode for file
            val classNode = source.getClass(it) ?: error("Failed to get ClassNode for $it")
            classNode.methods.forEach { method ->
                Logger.debug("${classNode.name}#${method.name} is public? ${((method.access and Opcodes.ACC_PUBLIC) != 0)}")
            }

            val fields = classNode.fields
        }
    }

    fun getClassSourceForFileName(fileName: String): ClassSource? {
        return sources.firstOrNull { it.supportedExtensions.any { ext -> fileName.endsWith(ext) } }
    }
}
