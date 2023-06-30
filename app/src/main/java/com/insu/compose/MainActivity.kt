package com.insu.compose

import android.content.res.Configuration
import android.graphics.drawable.Icon
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MyApp() } // MyApp() 함수 실행
    }
}

//data class Message(val author: String, val body: String, @DrawableRes val picture: Int)
//
//@Composable
//fun MessageCard(msg: Message) {
//    Row(modifier = Modifier.padding(all = 8.dp)) {
//        Image(
//            painter = painterResource(msg.picture),
//            contentDescription = "img",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .size(40.dp)
//                .clip(CircleShape)
//                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
//        )
//
//        Spacer(modifier = Modifier.width(8.dp))
//
//        var isExpanded by remember { mutableStateOf(false) }
//        val surfaceColor by animateColorAsState(
//            if(isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
//        )
//
//        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
//            Text(
//                text = msg.author,
//                color = MaterialTheme.colors.secondaryVariant,
//                style = MaterialTheme.typography.subtitle2
//
//            )
//
//            Spacer(modifier = Modifier.height(4.dp))
//
//            Surface(
//                shape = MaterialTheme.shapes.medium,
//                elevation = 1.dp,
//                color = surfaceColor,
//                modifier = Modifier
//                    .animateContentSize()
//                    .padding(1.dp)
//            ) {
//                Text(
//                    text = msg.body,
//                    modifier = Modifier.padding(all = 4.dp),
//                    maxLines = if(isExpanded) Int.MAX_VALUE else 1,
//                    style = MaterialTheme.typography.body2
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun Conversation(messages: List<Message>) {
//    LazyColumn {
//        items(messages) { message ->
//            MessageCard(message)
//        }
//    }
//}
//
//@Composable
//fun SearchBar(
//    modifier: Modifier = Modifier
//) {
//    TextField(
//        value = "",
//        onValueChange = {},
//        leadingIcon =  {
//            Icon(
//                imageVector = Icons.Default.Search,
//                contentDescription = null
//            )
//        },
//        colors = TextFieldDefaults.textFieldColors(
//            backgroundColor = MaterialTheme.colors.surface
//        ),
//        modifier = modifier
//            .fillMaxWidth()
//            .heightIn(min = 56.dp)
//    )
//}
//
////@Preview(name = "Light Mode")
////@Preview(
////    uiMode = Configuration.UI_MODE_NIGHT_YES,
////    showBackground = true,
////    name = "Dark Mode"
////)
//
//@Preview
//@Composable
//fun PreviewConversation() {
//    ComposeExampleTheme {
//        Conversation(SampleData.conversationSample)
//    }
//}