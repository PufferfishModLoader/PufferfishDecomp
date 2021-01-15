package me.dreamhopping.pufferfishdecomp

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.file
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import me.dreamhopping.pufferfishdecomp.decompiler.PufferfishDecompiler
import me.dreamhopping.pufferfishdecomp.logger.Logger
import java.io.File

object PufferfishDecompCommand : CliktCommand() {
    private val sourceJar: File by argument("source", "Path to the jar that needs to be decompiled").file()
    private val destination: File by argument(
        "destination",
        "Path to the decompiler output, can be a .ZIP, .JAR or a folder"
    ).file()
    val verbose: Boolean by option("-v", "--verbose", help = "Enables verbose mode")
        .flag()

    override fun run() {
        Logger.info("Welcome to PufferfishDecomp (v1.0.0)")

        Logger.debug("Input: $sourceJar")
        Logger.debug("Output: $destination")

        if (!sourceJar.exists()) {
            Logger.error("Source jar does not exist!")
            return
        }

        val decompiler = PufferfishDecompiler(sourceJar, destination)
        decompiler.decompile()
    }
}

fun main(args: Array<String>) = PufferfishDecompCommand.main(args)
