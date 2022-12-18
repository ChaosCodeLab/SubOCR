import model.MainViewModel
import java.io.File

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
}