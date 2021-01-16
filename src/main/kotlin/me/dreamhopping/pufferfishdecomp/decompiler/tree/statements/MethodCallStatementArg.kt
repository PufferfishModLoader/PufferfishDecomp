package me.dreamhopping.pufferfishdecomp.decompiler.tree.statements

import me.dreamhopping.pufferfishdecomp.decompiler.tree.types.DecompilerType

data class MethodCallStatementArg(val type: String, val value: DecompilerType)
