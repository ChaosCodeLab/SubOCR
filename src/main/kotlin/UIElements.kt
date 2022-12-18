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

class UIElements
{

    @Composable
    fun inputCard(name: String) {


        var text by remember { mutableStateOf("") }


        Column(modifier = Modifier.background(Color(67,67,67), shape = RoundedCornerShape(15.dp)).fillMaxWidth().padding(10.dp).fillMaxWidth())
        {

            Text(name, color = Color.White)
            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.fillMaxWidth())
            {
                TextField(value = text,
                    onValueChange = {text = it},
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


}