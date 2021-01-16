package me.dreamhopping.pufferfishdecomp.decompiler.tree

data class ClassTree(override val name: String, override val access: Int, val methods: List<MemberTree>) :
    MemberTree
