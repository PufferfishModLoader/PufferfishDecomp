package me.dreamhopping.pufferfishdecomp.decompiler.tree.statements

import me.dreamhopping.pufferfishdecomp.decompiler.tree.StatementTree

data class MethodCallStatement(val owner: String, val name: String, val args: List<MethodCallStatementArg>): StatementTree
