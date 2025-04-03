package dev.alejo.tiktokprofileclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alejo.tiktokprofileclone.ui.theme.GrayIcon
import dev.alejo.tiktokprofileclone.ui.theme.GrayIconTransparent
import dev.alejo.tiktokprofileclone.ui.theme.GrayText
import dev.alejo.tiktokprofileclone.ui.theme.TikTokBlue
import dev.alejo.tiktokprofileclone.ui.theme.TikTokProfileCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TikTokProfileCloneTheme {
                TikTokProfileContent()
            }
        }
    }
}

@Composable
fun TikTokProfileContent(modifier: Modifier = Modifier) {
    val bottomItems = listOf(
        BottomItem(
            icon = R.drawable.home_ic,
            label = "Home",
            isSelected = false
        ),
        BottomItem(
            icon = R.drawable.search_ic,
            label = "Discover",
            isSelected = false
        ),
        BottomItem(
            icon = R.drawable.add_button_shape,
            label = "",
            isSelected = false
        ),
        BottomItem(
            icon = R.drawable.message_ic,
            label = "Inbox",
            isSelected = false
        ),
        BottomItem(
            icon = R.drawable.account_ic,
            label = "Me",
            isSelected = false
        ),
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            TikTokBottomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                bottomItems = bottomItems
            )
        }
    ) { innerPadding ->
        ProfileContent(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
fun ProfileContent(modifier: Modifier = Modifier) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TopProfile(Modifier.fillMaxWidth())
        ProfilePicture()
        Text(text = "@aleejo_loopez", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Stats(Modifier.fillMaxWidth())
        Options(Modifier.fillMaxWidth())
        Text(text = "Tap to add bio", color = GrayText)
        Column {
            Tabs()
            Videos()
        }
    }
}

@Composable
fun Videos() {
    val videos = listOf(
        VideoItem(R.drawable.video, "500"),
        VideoItem(R.drawable.video_two, "1200"),
        VideoItem(R.drawable.video_three, "300"),
        VideoItem(R.drawable.video_four, "2800")
    )
    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
        items(videos.size) {
            VideoView(videos[it])
        }
    }
}

@Composable
fun VideoView(videoData: VideoItem) {
    Box() {
        Image(
            painter = painterResource(id = videoData.image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Row(Modifier.align(Alignment.BottomStart).padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = R.drawable.play_ic), contentDescription = null, tint = Color.White)
            Text(text = videoData.views, color = Color.White)
        }
    }
}

@Composable
fun Tabs() {
    val selectedTabIndex by remember { mutableIntStateOf(0) }
    TabRow(selectedTabIndex = selectedTabIndex, contentColor = Color.Black) {
        Tab(
            selected = selectedTabIndex == 0,
            onClick = { }
        ) {
            Icon(painter = painterResource(id = R.drawable.tabs_ic), contentDescription = null)
        }

        Tab(
            selected = selectedTabIndex == 1,
            onClick = { }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.heart_hide_ic),
                contentDescription = null
            )
        }
    }
}

@Composable
fun Options(modifier: Modifier) {
    Row(modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Spacer(Modifier.weight(1f))
        TextButton(
            modifier = Modifier.border(1.dp, GrayIcon, RoundedCornerShape(12.dp)),
            onClick = { /*TODO*/ }
        ) {
            Text("Edit profile", color = Color.Black)
        }
        TextButton(
            modifier = Modifier.border(1.dp, GrayIcon, RoundedCornerShape(12.dp)),
            onClick = { /*TODO*/ }
        ) {
            Text("Share profile", color = Color.Black)
        }
        TextButton(
            modifier = Modifier.border(1.dp, GrayIcon, RoundedCornerShape(12.dp)),
            onClick = { /*TODO*/ }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add_account_ic),
                contentDescription = null,
                tint = Color.Black
            )
        }
        Spacer(Modifier.weight(1f))
    }
}

@Composable
fun Stats(modifier: Modifier) {
    Row(modifier, horizontalArrangement = Arrangement.SpaceEvenly) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "120", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = "Following")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "2,545", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = "Followers")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "33.3K", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = "Likes")
        }
    }
}

@Composable
fun TopProfile(modifier: Modifier) {
    Row(modifier = modifier.height(56.dp), verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.weight(1f))
            Text("Alejo")
            Icon(
                modifier = Modifier.weight(1f),
                painter = painterResource(id = R.drawable.arrow_ic),
                contentDescription = null
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Row(Modifier.weight(1f)) {
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier.weight(1f),
                painter = painterResource(id = R.drawable.menu_ic),
                contentDescription = null
            )
        }
    }
}

@Composable
fun ProfilePicture() {
    Box(Modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
                .size(140.dp)
                .align(Alignment.BottomStart)
                .padding(top = 24.dp, bottom = 16.dp, start = 16.dp, end = 24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.jimmy),
                contentDescription = "Descripción de la imagen",
                contentScale = ContentScale.Crop
            )
            Icon(
                imageVector = Icons.Filled.Add,
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .background(TikTokBlue, CircleShape)
                    .size(26.dp)
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .background(GrayIconTransparent, RoundedCornerShape(12.dp))
                .padding(10.dp),
            text = "Thoughts?",
            color = Color.Black
        )
    }
}

@Composable
fun TikTokBottomAppBar(
    modifier: Modifier = Modifier,
    bottomItems: List<BottomItem>
) {
    BottomAppBar(modifier, containerColor = Color.White) {
        bottomItems.forEach {
            NavigationBarItem(
                icon = {
                    if (it.label.isEmpty()) {
                        Image(
                            painter = painterResource(id = it.icon),
                            contentDescription = it.label
                        )
                    } else {
                        Icon(painter = painterResource(id = it.icon), contentDescription = it.label)
                    }
                },
                label = {
                    Text(it.label)
                },
                selected = it.isSelected,
                onClick = { }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    TikTokProfileContent()
}

data class BottomItem(
    val icon: Int,
    val label: String,
    val isSelected: Boolean
)

val bottomItems = listOf(
    BottomItem(
        icon = R.drawable.home_ic,
        label = "Home",
        isSelected = false
    ),
    BottomItem(
        icon = R.drawable.search_ic,
        label = "Discover",
        isSelected = false
    ),
    BottomItem(
        icon = R.drawable.add_button_shape,
        label = "",
        isSelected = false
    ),
    BottomItem(
        icon = R.drawable.message_ic,
        label = "Inbox",
        isSelected = false
    ),
    BottomItem(
        icon = R.drawable.account_ic,
        label = "Me",
        isSelected = true
    ),
)

data class VideoItem(
    val image: Int,
    val views: String
)