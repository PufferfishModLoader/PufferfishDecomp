package me.dreamhopping.pufferfishdecomp.logger

import me.dreamhopping.pufferfishdecomp.PufferfishDecompCommand

object Logger {
    fun debug(message: String) {
        if (PufferfishDecompCommand.verbose) println("[DEBUG] $message")
    }

    fun info(message: String) {
        println("[INFO] $message")
    }

    fun error(message: String) {
        System.err.println("[ERROR] $message")
    }
}
