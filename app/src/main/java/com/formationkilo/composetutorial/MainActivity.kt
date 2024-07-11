package com.formationkilo.composetutorial

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.formationkilo.composetutorial.ui.theme.ComposeTutorialTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
          ComposeTutorialTheme {
             /* Surface (modifier = Modifier.fillMaxSize()){
                  MessageCard(Message("Lexi"," Hello everyone"))
              }*/
                  Conversation(SampleData.conversationSample)
          }
        }
            }
}
data class Message(val author:String,val body:String)
@Composable
fun MessageCard(msg: Message) {
       ComposeTutorialTheme {
           Surface (modifier = Modifier.fillMaxSize()){
               // Add padding around our message
               Row(modifier = Modifier.padding(all = 8.dp)) {
                   Image(
                       painter = painterResource(R.drawable.pic_pixel2_api_24),
                       contentDescription = "Contact profile picture",
                       modifier = Modifier
                           // Set image size to 40 dp
                           .size(40.dp)
                           // Clip image to be shaped as a circle
                           .clip(CircleShape)
                           //Ajoutez un style de bordure à l'image
                           .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
                   )


                   // Add a horizontal space between the image and the column
                   Spacer(modifier = Modifier.width(8.dp))

                   // We keep track if the message is expanded or not in this
                   // variable
                   var isExpanded by remember { mutableStateOf(false)}
                   // surfaceColor will be updated gradually from one color to the other
                   val surfaceColor by animateColorAsState(
                       if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                   )

                   Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                       Text(text = msg.author, color = MaterialTheme.colorScheme.secondary,
                           style = MaterialTheme.typography.titleSmall)
                       // Add a vertical space between the author and message texts
                       Spacer(modifier = Modifier.height(4.dp))
                       //Les styles de typographie Material
                      Surface( shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp,
                          // surfaceColor color will be changing gradually from primary to surface
                          color = surfaceColor,
                          // animateContentSize will change the Surface size gradually
                          modifier = Modifier.animateContentSize().padding(1.dp)
                          ){
                          Text(text = msg.body,
                              // If the message is expanded, we display all its content
                              // otherwise we only display the first line
                              maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                              style =MaterialTheme.typography.bodyMedium
                          )
                      }
                   }
               }
           }
       }
}


@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    ComposeTutorialTheme {
        Surface {
            MessageCard(
                msg = Message("Lexi", "Hello everyone")
            )
        }
    }
}


@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    ComposeTutorialTheme {
        Conversation(SampleData.conversationSample)
    }
}






/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTutorialTheme {
        Greeting("Android")
    }
}*/