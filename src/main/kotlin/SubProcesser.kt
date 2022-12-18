import model.MainViewModel
import net.sourceforge.tess4j.Tesseract
import java.io.File
import javax.sound.sampled.Line
import kotlinx.coroutines.*
import java.util.ArrayList

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

    private fun processImg(tesseract: Tesseract)
    {
        var lines = arrayListOf<LineData>()


        val images = File(viewmodel.imagesfolder.value).listFiles()

        println(images.size)


        var progress = 0f

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {

            var counter = 1f

            images.forEach {
                viewmodel.updateConsole("Image ${counter.toInt()}/${images.size} processed")
                progress = counter/images.size
                println(it.absolutePath)
                val result = tesseract.doOCR(it.absoluteFile)
                viewmodel.updateConsole(result)
                println(result)
                counter++
                println("STATUS: $progress")
                viewmodel.setProgress(progress)

                val name = it.name
                val starttime = "00:${name[2]}${name[3]}:${name[5]}${name[6]},${name[8]}${name[9]}${name[10]}"
                val endtime = "00:${name[15]}${name[16]}:${name[18]}${name[19]},${name[21]}${name[22]}${name[23]}"
                val data = LineData(startTime = starttime, endTime = endtime, text = result)
                lines.add(data)
            }

            writeSubtitle(lines = lines)

        }

        /*
        images.forEach {
            println(it.absolutePath)
            val result = tesseract.doOCR(it.absoluteFile)
            println(result)
        }

         */



    }

    private fun writeSubtitle(lines: ArrayList<LineData>)
    {

        val filteredData = arrayListOf<LineData>()

        for (txt in lines)
        {
            if (!txt.text.isNullOrEmpty())
            {
                if (txt.text.toString().lastIndex > 3)
                {
                    filteredData.add(txt)
                }
            }
        }

        filteredData.forEach {
            println(it.text)
        }

        println("""${viewmodel.suboutfolder.value}\converted.srt""")
        File("""${viewmodel.suboutfolder.value}\converted.srt""").printWriter().use { out ->

            var lineNum = 0

            for (data in filteredData)
            {
                lineNum++
                out.println(lineNum)
                out.println("${data.startTime} --> ${data.endTime}")
                out.println(data.text)
                out.println()
            }

        }


    }
}