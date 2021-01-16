package me.dreamhopping.pufferfishdecomp.decompiler.tree

data class MethodTree(
    override val name: String,
    override val access: Int,
    val parameters: Map<String, String>,
    val statements: List<StatementTree>
) :
    MemberTree
