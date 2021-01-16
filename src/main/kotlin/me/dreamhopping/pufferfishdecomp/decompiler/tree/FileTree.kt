package me.dreamhopping.pufferfishdecomp.decompiler.tree

data class FileTree(
    val filePackage: String,
    val fileName: String,
    val imports: Set<String>,
    val members: List<MemberTree>
)
