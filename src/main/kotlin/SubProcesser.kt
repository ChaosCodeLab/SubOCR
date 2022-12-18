import model.MainViewModel
import net.sourceforge.tess4j.Tesseract
import java.io.File
import javax.sound.sampled.Line
import kotlinx.coroutines.*

class SubProcesser(viewModel: MainViewModel)
{

    val viewmodel = viewModel


    fun process()
    {
        val chk = checkLocations()
        if (chk != "OK")
        {
            return
        }

        println("EVERYTHING IS OK")

        val tesseract = initTesseract(viewmodel.tessdatafolder.value, lang = viewmodel.ocrlang.value)
        processImg(tesseract)


    }


    private fun checkLocations(): String
    {
        try {



            if (viewmodel.tessdatafolder.value.isNotEmpty())
            {
                val tessfiles = File(viewmodel.tessdatafolder.value).listFiles()
                //val files = emptyArray<File>()

                if (!tessfiles.isNullOrEmpty())
                {
                    var found = false
                    tessfiles.forEach {
                        if (it.name == "${viewmodel.ocrlang.value}.traineddata")
                        {
                            println("Tessdata OK")
                            found = true
                        }

                    }

                    if (!found)
                    {
                        println("Not valid tessdata folder")
                        return "Not valid tessdata folder"
                    }
                }
                else
                {
                    println("Not valid tessdata folder!")
                    return "Not valid tessdata folder"
                }



                val imgfiles = File(viewmodel.imagesfolder.value).listFiles()
                //val files = emptyArray<File>()


                if (!imgfiles.isNullOrEmpty())
                {
                    if (imgfiles.size > 2)
                    {
                        println("IMG folder OK")
                    }
                    else
                    {
                        return "Not valid images folder"
                    }
                }
                else
                {
                    println("Not valid images folder!")
                    return "Not valid images folder"
                }

                val subout = File(viewmodel.suboutfolder.value)

                if (File(viewmodel.suboutfolder.value).isDirectory)
                {
                    println("valid subout dir")
                }
                else
                {
                    println("Not valid suboutdir")
                    return "Not valid out folder"
                }





            }

        }
        catch (e: Exception)
        {
            return "Some problem happened"
        }

        return "OK"
        //if (viewmodel.tessdatafolder)
        //println(viewmodel.tessdatafolder.value)
    }

    private fun initTesseract(dataPath: String, lang: String): Tesseract
    {
        val tesseract = Tesseract()
        tesseract.setDatapath(dataPath)
        tesseract.setLanguage(lang)
        tesseract.setPageSegMode(1)
        tesseract.setOcrEngineMode(1)
        return tesseract
    }

    private fun processImg(tesseract: Tesseract): Array<LineData>
    {
        var lines = emptyArray<LineData>()


        val images = File(viewmodel.imagesfolder.value).listFiles()

        println(images.size)


        var progress = 0f

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {

            var counter = 1f

            images.forEach {
                progress = counter/images.size
                println(it.absolutePath)
                val result = tesseract.doOCR(it.absoluteFile)
                println(result)
                counter++
                println("STATUS: $progress")
                viewmodel.setProgress(progress)
            }
        }

        /*
        images.forEach {
            println(it.absolutePath)
            val result = tesseract.doOCR(it.absoluteFile)
            println(result)
        }

         */






        return lines
    }
}