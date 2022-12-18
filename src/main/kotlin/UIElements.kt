import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.StateFlow

class UIElements
{

    @Composable
    fun inputCard(name: String, onTxtChange: (String)-> Unit, path: String) {


        var inputText by remember { mutableStateOf(path) }


        Column(modifier = Modifier.background(Color(67,67,67), shape = RoundedCornerShape(15.dp)).fillMaxWidth().padding(10.dp).fillMaxWidth())
        {

            Text(name, color = Color.White)
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth())
            {
                TextField(value = inputText,
                    onValueChange = {inputText = it
                                    onTxtChange(it)},
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color(40,40,40), textColor = Color(255,255,255)), shape = RoundedCornerShape(10.dp), modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(20.dp))

                Button({

                }, modifier = Modifier.width(50.dp).weight(0.2f)){
                    Text("TEST")
                }
            }
        }


    }

    @Composable
    fun CustomLinearProgressBar(modifier: Modifier, progress: Float){
        Column(modifier = Modifier.fillMaxWidth()) {
            LinearProgressIndicator(
                modifier = modifier
                    .fillMaxWidth()
                    .height(15.dp),
                backgroundColor = Color(95,95,95),
                color = Color.White, //progress color
                progress = progress
            )
        }
    }


}