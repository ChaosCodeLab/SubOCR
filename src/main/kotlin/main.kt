import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import model.MainViewModel
import java.awt.FileDialog
import java.awt.Frame
import java.io.File

val ui = UIElements()
fun main() = application {

    val viewmodel = MainViewModel()


    val list = listOf<String>(".md",".jpg")
    val txt by viewmodel.text.collectAsState()

    val winstate = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(alignment = Alignment.Center),
        width = 1280.dp,
        height = 720.dp)

    Window(onCloseRequest = ::exitApplication, title = "SubOCR", resizable = false, state = winstate) {


        Row(modifier = Modifier.fillMaxSize().background(Color(40,40,40))) {

            buttonPanels(columnModifier = Modifier.weight(1f),viewmodel)
            outputPanel(columnModifier = Modifier.weight(1f))
        }


    }
}


@Composable
fun buttonPanels(columnModifier: Modifier, viewmodel: MainViewModel)
{
    val txt by viewmodel.text.collectAsState()
    val tessdatafolder by viewmodel.tessdatafolder.collectAsState()
    val imagefolder by viewmodel.imagesfolder.collectAsState()
    val suboutfolder by viewmodel.suboutfolder.collectAsState()

    Column(modifier = columnModifier.background(Color(40,40,40))
        .fillMaxHeight()
        .padding(25.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        ui.inputCard("Tesseract 'tessdata' folder location",{ viewmodel.setTessdatafolder(it) }, path = "")
        ui.inputCard("Subtitle 'TXTImages' folder location",{ viewmodel.setTessdatafolder(it) }, path = "")
        ui.inputCard("Subtitle out folder",{ viewmodel.setTessdatafolder(it) }, path = "")
        ui.inputCard("Select Tesseract language",{ viewmodel.setTessdatafolder(it) }, path = "")
        //ui.inputCard("Subtitle 'TXTImages' folder location")
        //ui.inputCard("Subtitle out folder")
        //ui.inputCard("Select Tesseract language")
        Button({
            /*
            var res = openFileDialog(window = ComposeWindow(),"File selector",list,true)
            for (item in res)
            {
                println(item.absolutePath)
            }

             */

               println(viewmodel.tessdatafolder.value)
        }, modifier = Modifier.align(Alignment.CenterHorizontally))
        {
            Text("START OCR")
        }
    }


}

@Composable
fun outputPanel(columnModifier: Modifier)
{
    Column(modifier = columnModifier.background(Color(40,40,40)).fillMaxHeight().padding(20.dp).padding(top = 30.dp)) {
        Box(modifier = Modifier.background(Color.DarkGray).fillMaxSize().weight(0.9f).padding(bottom = 15.dp))
        {
            Column {
                for (i in 1 ..50)
                {
                    Text("SOR: $i")
                }
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        ui.CustomLinearProgressBar(modifier = Modifier.height(60.dp))

    }
}


fun openFileDialog(window: ComposeWindow, title: String, allowedExtensions: List<String>, allowMultiSelection: Boolean = true): Set<File> {
    return FileDialog(window, title, FileDialog.LOAD).apply {
        isMultipleMode = allowMultiSelection

        // windows
        //file = allowedExtensions.joinToString(";") { "*$it" } // e.g. '*.jpg'

        // linux
        setFilenameFilter { _, name ->
            allowedExtensions.any {
                name.endsWith(it)
            }
        }
        isVisible = true
    }.files.toSet()
}




