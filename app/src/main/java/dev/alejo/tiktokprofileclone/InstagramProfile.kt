package dev.alejo.tiktokprofileclone

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alejo.tiktokprofileclone.ui.theme.ButtonColor
import dev.alejo.tiktokprofileclone.ui.theme.DotNotificationColor
import dev.alejo.tiktokprofileclone.ui.theme.HighlightBorderColor
import dev.alejo.tiktokprofileclone.ui.theme.NoteEndColor
import dev.alejo.tiktokprofileclone.ui.theme.NoteStartBorderColor
import dev.alejo.tiktokprofileclone.ui.theme.NoteStartColor
import dev.alejo.tiktokprofileclone.ui.theme.NoteTextColor

val highLightsData = listOf(
    HighLightData(null, "New"),
    HighLightData(R.drawable.love_hl, "\uD83D\uDC95My Love "),
    HighLightData(R.drawable.artists_hl, "\uD83C\uDDE6\uD83C\uDDFA Artists ✨"),
    HighLightData(R.drawable.doha_hl, "\uD83C\uDDF6\uD83C\uDDE6Doha"),
    HighLightData(R.drawable.madrid_hl, "\uD83C\uDDEA\uD83C\uDDF8 Madrid")
)

@Composable
fun InstagramProfile() {
    val posts = listOf(
        R.drawable.ig_img_2,
        R.drawable.ig_img_1,
        R.drawable.ig_img_3,
        R.drawable.ig_img_4,
        R.drawable.ig_img_5,
        R.drawable.ig_img_6
    )
    Scaffold(
        bottomBar = { InstagramBottomBar(Modifier.fillMaxWidth()) },
        containerColor = Color.Black
    ) { innerPadding ->
        InstagramProfileContent(
            Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            highLightsData = highLightsData,
            posts = posts
        )
    }
}

@Composable
fun InstagramProfileContent(
    modifier: Modifier,
    highLightsData: List<HighLightData>,
    posts: List<Int>
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        TopOptions()
        Info(Modifier.fillMaxWidth())
        ProfileOptions()
        HighLights(highLightsData)
        Column(Modifier.padding(top = 16.dp)) {
            InstagramTabs()
            InstagramPosts(posts = posts)
        }
    }
}

@Composable
fun InstagramPosts(posts: List<Int>) {
    LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.fillMaxWidth()) {
        items(posts.size) {
            Box(Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = posts[it]),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                Icon(
                    painter = painterResource(id = R.drawable.ig_posts_ic),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun InstagramTabs() {
    val selectedTabIndex by remember { mutableIntStateOf(0) }
    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Black,
        contentColor = Color.White,
    ) {
        Tab(
            modifier = Modifier.padding(bottom = 10.dp),
            selected = selectedTabIndex == 0,
            onClick = { },
            selectedContentColor = Color.White
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ig_grid_ic),
                contentDescription = null,
                modifier = Modifier.size(26.dp)
            )
        }

        Tab(
            modifier = Modifier.padding(bottom = 10.dp),
            selected = selectedTabIndex == 1,
            onClick = { },
            unselectedContentColor = NoteStartBorderColor
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ig_reel_ic),
                contentDescription = null,
                modifier = Modifier.size(26.dp)
            )
        }

        Tab(
            modifier = Modifier.padding(bottom = 10.dp),
            selected = selectedTabIndex == 1,
            onClick = { },
            unselectedContentColor = NoteStartBorderColor
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ig_tags_ic),
                contentDescription = null,
                modifier = Modifier.size(26.dp)
            )
        }
    }
}

@Composable
fun HighlightItem(item: HighLightData) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(HighlightBorderColor)
        ) {
            item.cover?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxSize()
                        .clip(CircleShape)
                        .border(BorderStroke(4.dp, Color.Black), shape = CircleShape)
                )
            } ?: Icon(
                painter = painterResource(id = R.drawable.ig_add_ic),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(1.dp)
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(Color.Black)
                    .padding(18.dp)
                    .align(Alignment.Center)
            )
        }
        Text(
            text = item.label,
            color = Color.White,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            minLines = 1
        )
    }
}

@Composable
fun HighLights(highLightsData: List<HighLightData>) {
    LazyRow(
        Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(highLightsData.size) {
            HighlightItem(highLightsData[it])
        }
    }
}

@Composable
fun ProfileOptions() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = { },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonColor, contentColor = Color.White
            ),
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Edit profile")
        }
        Button(
            onClick = { },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonColor, contentColor = Color.White
            ),
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Share profile")
        }
        Button(
            onClick = { },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonColor, contentColor = Color.White
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ig_add_user_ic),
                contentDescription = null
            )
        }
    }
}

@Composable
fun Info(modifier: Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            InstagramProfilePicture()
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Alejo  \uD83C\uDDE8\uD83C\uDDF4",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "He/him/his",
                        color = NoteTextColor,
                        fontSize = 16.sp
                    )
                }
                Stats()
            }
        }
        Text(
            text = "\uD83C\uDDE8\uD83C\uDDF4 \uD83C\uDDF2\uD83C\uDDFD \uD83C\uDDEA\uD83C\uDDF8 \uD83C\uDDF6\uD83C\uDDE6 \uD83C\uDDE6\uD83C\uDDFA \uD83C\uDDE8\uD83C\uDDF1 \uD83C\uDDF5\uD83C\uDDEA \uD83C\uDDF8\uD83C\uDDEC \uD83C\uDDF9\uD83C\uDDED\n" +
                    "\uD83D\uDC68\u200D\uD83D\uDCBB Software Engineer\n" +
                    "✨ @boyaca_dev Coorganizer | Cofounder\n" +
                    "\uD83D\uDCF1 Android Developer\n" +
                    "\uD83D\uDD7A\uD83C\uDFB6 Dance Lover",
            color = Color.White
        )
    }
}

@Composable
fun Stats() {
    Row(
        Modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "500", color = Color.White, fontWeight = FontWeight.Bold)
            Text(text = "Posts", color = Color.White)
        }
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "600", color = Color.White, fontWeight = FontWeight.Bold)
            Text(text = "Followers", color = Color.White)
        }
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "300", color = Color.White, fontWeight = FontWeight.Bold)
            Text(text = "Following", color = Color.White)
        }
    }
}

@Composable
fun InstagramProfilePicture() {
    Box(Modifier.size(width = 100.dp, height = 128.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ig_profile_pic),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .align(Alignment.BottomCenter)
        )
        Box(
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.BottomEnd)
                .background(Color.White, CircleShape)
                .border(4.dp, Color.Black, CircleShape),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ig_add_ic),
                contentDescription = null,
                Modifier
                    .size(16.dp)
                    .align(Alignment.Center)
            )
        }
        val gradientBrush = Brush.linearGradient(
            colors = listOf(NoteStartColor, NoteEndColor),
            start = Offset(0f, 0f),
            end = Offset(80f, 80f)
        )
        val gradientBorderBrush = Brush.linearGradient(
            colors = listOf(NoteStartBorderColor, NoteEndColor),
            start = Offset(0f, 0f),
            end = Offset(80f, 80f)
        )
        Box(
            Modifier
                .background(brush = gradientBrush, shape = RoundedCornerShape(18.dp))
                .border(
                    BorderStroke(1.dp, brush = gradientBorderBrush),
                    shape = RoundedCornerShape(18.dp)
                )
        ) {
            Text(
                text = "Share a \nnote",
                fontSize = 12.sp,
                color = NoteTextColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp)
            )
        }
    }
}

@Composable
fun TopOptions() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ig_lock_ic),
            contentDescription = null,
            tint = Color.White,
        )
        Row {
            Text(
                text = "@aleejo_loopez",
                fontSize = 18.sp,
                color = Color.White
            )
            Icon(
                painter = painterResource(id = R.drawable.arrow_ic),
                contentDescription = null,
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.ig_add_post_ic),
            contentDescription = null,
            tint = Color.White
        )
        Icon(
            painter = painterResource(id = R.drawable.menu_ic),
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Composable
fun InstagramBottomBar(modifier: Modifier) {
    Row(
        modifier = modifier.padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ig_home_ic),
                contentDescription = null,
                tint = Color.White
            )
            Box(
                Modifier
                    .size(6.dp)
                    .background(DotNotificationColor, CircleShape)
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ig_search_ic),
            contentDescription = null,
            tint = Color.White
        )
        Icon(
            painter = painterResource(id = R.drawable.ig_add_post_ic),
            contentDescription = null,
            tint = Color.White
        )
        Icon(
            painter = painterResource(id = R.drawable.ig_likes_ic),
            contentDescription = null,
            tint = Color.White
        )
        Icon(
            painter = painterResource(id = R.drawable.ig_profile_ic),
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InstagramProfilePreview() {
    Box(Modifier.background(Color.Black)) {
        InstagramProfile()
        //TopOptions()
        //InstagramProfilePicture()
        //Stats()
        //Info(Modifier.fillMaxWidth())
        //ProfileOptions()
        //HighlightItem()
        //HighLights(highLightsData = highLightsData)
    }
}

data class HighLightData(
    val cover: Int?,
    val label: String
)