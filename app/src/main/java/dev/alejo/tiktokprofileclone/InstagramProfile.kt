package dev.alejo.tiktokprofileclone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alejo.tiktokprofileclone.ui.theme.DotNotificationColor

@Composable
fun InstagramProfile() {
    Scaffold(
        bottomBar = { InstagramBottomBar(Modifier.fillMaxWidth()) },
        containerColor = Color.Black
    ) { innerPadding ->
        InstagramProfileContent(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
fun InstagramProfileContent(modifier: Modifier) {
    Column {
        TopOptions()
        InstagramProfilePicture()
    }
}

@Composable
fun InstagramProfilePicture() {
    Box() {
        Image(
            painter = painterResource(id = R.drawable.ig_img_1),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
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
        //InstagramProfile()
        //TopOptions()
        InstagramProfilePicture()
    }
}