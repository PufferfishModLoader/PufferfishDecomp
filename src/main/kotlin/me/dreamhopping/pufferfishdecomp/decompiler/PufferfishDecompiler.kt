package me.dreamhopping.pufferfishdecomp.decompiler

import me.dreamhopping.pufferfishdecomp.decompiler.sources.ClassSource
import me.dreamhopping.pufferfishdecomp.decompiler.sources.ClassSourceManager
import me.dreamhopping.pufferfishdecomp.decompiler.tree.ClassTree
import me.dreamhopping.pufferfishdecomp.decompiler.tree.FileTree
import me.dreamhopping.pufferfishdecomp.decompiler.tree.MemberTree
import me.dreamhopping.pufferfishdecomp.decompiler.tree.MethodTree
import me.dreamhopping.pufferfishdecomp.logger.Logger
import org.objectweb.asm.Type
import java.io.File
import kotlin.system.measureNanoTime

class PufferfishDecompiler(private val inputJar: File, private val output: File) {
    fun decompile() {
        val source: ClassSource =
            ClassSourceManager.getClassSourceForFileName(inputJar.name) ?: error("This source isn't supported")

        val files = mutableListOf<FileTree>()
        val time = measureNanoTime {
            source.discoverClasses(inputJar).forEach { clazz ->
                // Get ClassNode for file
                val classNode = source.getClass(clazz) ?: error("Failed to get ClassNode for $clazz")
                val classPackage = classNode.name.substringBeforeLast("/")
                val className = classNode.name.substringAfterLast("/")

                val imports = mutableSetOf<String>()
                val members = mutableListOf<MemberTree>()

                // Parse all method in ClassNode
                classNode.methods.forEach { method ->
                    val arguments = mutableMapOf<String, String>()
                    val types = Type.getArgumentTypes(method.desc)

                    // Loop over all the types in the descriptor
                    var paramIndex = 0
                    types?.forEach { type ->
                        val paramName = method.parameters?.get(paramIndex)?.name ?: "var$paramIndex"
                        arguments[paramName] = type.className
                        paramIndex += 1
                    }

                    // Add the MethodTree as a member to the file
                    members.add(MethodTree(method.name, method.access, arguments, emptyList()))
                }

                val classTree = ClassTree(className, classNode.access, members)
                files.add(FileTree(classPackage, className, imports, listOf(classTree)))
            }
        }

        Logger.info("Parsed ${files.size} classes in ${(time / 1000000.0)}ms")
    }
}
