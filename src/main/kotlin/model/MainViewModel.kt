package model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

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
}