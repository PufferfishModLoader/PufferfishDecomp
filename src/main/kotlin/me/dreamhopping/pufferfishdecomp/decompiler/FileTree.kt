package me.dreamhopping.pufferfishdecomp.decompiler

interface MemberTree {
    val name: String
    val access: Int
}

data class MethodTree(override val name: String, override val access: Int, val parameters: Map<String, String>) :
    MemberTree

data class ClassTree(override val name: String, override val access: Int, val methods: List<MethodTree>) :
    MemberTree

data class FileTree(
    val filePackage: String,
    val fileName: String,
    val imports: Set<String>,
    val members: List<MemberTree>
)
