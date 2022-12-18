package model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File

class MainViewModel
{
    private val _text = MutableStateFlow("Tesseract 'tessdata' folder location")
    val text = _text.asStateFlow()

    private val _tessdatafolder = MutableStateFlow("""C:\Program Files\Tesseract-OCR\tessdata""")
    val tessdatafolder = _tessdatafolder.asStateFlow()

    private val _imagesfolder = MutableStateFlow("")
    val imagesfolder = _imagesfolder.asStateFlow()

    private val _suboutfolder = MutableStateFlow("")
    val suboutfolder = _suboutfolder.asStateFlow()

    private val _ocrlang = MutableStateFlow("eng")
    val ocrlang = _ocrlang.asStateFlow()

    private val _progress = MutableStateFlow(0f)
    val progress = _progress.asStateFlow()

    private val _consoleOut = MutableStateFlow(Array<String>(32) { "" })
    val consoleOut = _consoleOut.asStateFlow()

    init {
        val path = System.getProperty("user.dir")
        println(path)

        val savedata = """$path\savedata.data"""

        if (File(savedata).exists())
        {
            println("File exists!")

            val data = File(savedata).readLines()
            println(data)
            _tessdatafolder.value = data[0]
            _imagesfolder.value = data[1]
            _suboutfolder.value = data[2]
            _ocrlang.value = data[3]
        }
        else
        {
            File(savedata).printWriter().use {
                it.println(tessdatafolder.value)
                it.println(imagesfolder.value)
                it.println(suboutfolder.value)
                it.print(ocrlang.value)
            }

            println("Doesnt exist")
        }

        println("ARRAY:")
        consoleOut.value.forEach {
            println(it)
        }

        for (num in 0 until consoleOut.value.size)
        {
            _consoleOut.value[num] = ""
        }

        consoleOut.value.forEach {
            println(it)
        }


    }


    fun newText()
    {
        _text.value = "megvaltozott a szoveg"
    }

    fun setTessdatafolder(text: String)
    {
        _tessdatafolder.value = text
    }

    fun setImagesfolder(text: String)
    {
        _imagesfolder.value = text
    }

    fun setSuboutfolder(text: String)
    {
        _suboutfolder.value = text
    }

    fun setLang(text: String)
    {
        _ocrlang.value = text
    }

    fun setProgress(progress: Float)
    {
        _progress.value = progress
    }

    fun updateConsole(newline: String)
    {
        println("START")
        val oldData = _consoleOut.value

        var newArray = Array<String>(32,{""})

        for (i in 1..oldData.size-1)
        {
           newArray[i-1] = oldData[i]
        }
        newArray[31] = newline

        _consoleOut.value = newArray

        //consoleOut.value.forEach { println(it) }

    }
}