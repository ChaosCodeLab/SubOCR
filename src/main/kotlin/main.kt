import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import java.awt.FileDialog
import java.awt.Frame
import java.io.File

@Composable
@Preview
fun App() {
    Box(modifier = Modifier.fillMaxSize().background(Color(40,40,40)).width(1280.dp).height(720.dp))
    {
        Text("sieufhgi")
    }
}

fun main() = application {

    val winstate = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition.Aligned(alignment = Alignment.Center),
        width = 1280.dp,
        height = 720.dp)

    Window(onCloseRequest = ::exitApplication, title = "SubOCR", resizable = false, state = winstate) {


        Row(modifier = Modifier.fillMaxSize().background(Color(40,40,40))) {

            buttonPanels(columnModifier = Modifier.weight(1f))
            outputPanel(columnModifier = Modifier.weight(1f))
        }


    }
}


@Composable
fun buttonPanels(columnModifier: Modifier)
{

    val list = listOf<String>(".md",".jpg")

    Column(modifier = columnModifier.background(Color(40,40,40))
        .fillMaxHeight()
        .padding(25.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val ui = UIElements()
        ui.inputCard("Tesseract 'tessdata' folder location")
        ui.inputCard("Subtitle 'TXTImages' folder location")
        ui.inputCard("Subtitle out folder")
        ui.inputCard("Select Tesseract language")
        Button({
            var res = openFileDialog(window = ComposeWindow(),"File selector",list,true)
            for (item in res)
            {
                println(item.absolutePath)
            }
        }, modifier = Modifier.align(Alignment.CenterHorizontally))
        {
            Text("START OCR")
        }
    }


}

@Composable
fun outputPanel(columnModifier: Modifier)
{
    Column(modifier = columnModifier.background(Color.Blue).fillMaxHeight()) {

    }
}


fun openFileDialog(window: ComposeWindow, title: String, allowedExtensions: List<String>, allowMultiSelection: Boolean = true): Set<File> {
    return FileDialog(window, title, FileDialog.LOAD).apply {
        isMultipleMode = allowMultiSelection

        // windows
        file = allowedExtensions.joinToString(";") { "*$it" } // e.g. '*.jpg'

        // linux
        setFilenameFilter { _, name ->
            allowedExtensions.any {
                name.endsWith(it)
            }
        }

        isVisible = true
    }.files.toSet()
}

