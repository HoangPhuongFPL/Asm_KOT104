package phuongdh.ph48762.asm_kot104

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import phuongdh.ph48762.asm_kot104.ui.theme.Asm_kot104Theme

class NotificationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Asm_kot104Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    NotificationScreen()
                }
            }
        }
    }
}

data class NotificationItem(
    val id: String,
    val title: String,
    val description: String,
    val imageRes: Int,
    val tag: String? = null,
    val isPromo: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen() {
    val context = LocalContext.current

    // Danh sách thông báo mẫu
    val notifications = listOf(
        NotificationItem(
            id = "1",
            title = "Your order #12345789 has been confirmed",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis pretium et in arcu adipiscing nec. Turpis pretium et in arcu adipiscing nec.",
            imageRes = R.drawable.sp1,
            tag = "New"
        ),
        NotificationItem(
            id = "2",
            title = "Your order #12345789 has been canceled",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis pretium et in arcu adipiscing nec. Turpis pretium et in arcu adipiscing nec.",
            imageRes = R.drawable.sp2
        ),
        NotificationItem(
            id = "3",
            title = "Discover hot sale furnitures this week.",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis pretium et in arcu adipiscing nec. Turpis pretium et in arcu adipiscing nec.",
            imageRes = R.drawable.sp3,
            tag = "HOT!",
            isPromo = true
        ),
        NotificationItem(
            id = "4",
            title = "Your order #12345789 has been shipped successfully",
            description = "Please help to confirm and rate your order to get 10% discount code for next order.",
            imageRes = R.drawable.sp4
        ),
        NotificationItem(
            id = "5",
            title = "Your order #12345789 has been confirmed",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis pretium et in arcu adipiscing nec. Turpis pretium et in arcu adipiscing nec.",
            imageRes = R.drawable.sp1
        ),
        NotificationItem(
            id = "6",
            title = "Your order #12345789 has been canceled",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis pretium et in arcu adipiscing nec. Turpis pretium et in arcu adipiscing nec.",
            imageRes = R.drawable.sp2
        ),
        NotificationItem(
            id = "7",
            title = "Your order #12345789 has been shipped successfully",
            description = "Please help to confirm and rate your order to get 10% discount code for next order.",
            imageRes = R.drawable.sp4
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Notification",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                actions = {
                    IconButton(onClick = { /* Xử lý tìm kiếm */ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.Black
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(60.dp),
                containerColor = Color.White
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        val intent = Intent(context, HomeActivity::class.java)
                        context.startActivity(intent)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home",
                            tint = Color.Gray
                        )
                    }

                    IconButton(onClick = {
                        val intent = Intent(context, FavoritesActivity::class.java)
                        context.startActivity(intent)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.bookmark),
                            contentDescription = "Favorites",
                            tint = Color.Gray
                        )
                    }

                    IconButton(onClick = { /* Đã ở màn hình thông báo */ }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.Black
                        )
                    }

                    IconButton(onClick = {
                        val intent = Intent(context, ProfileActivity::class.java)
                        context.startActivity(intent)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile",
                            tint = Color.Gray
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            items(notifications) { notification ->
                NotificationCard(notification = notification)

                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.LightGray.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Composable
fun NotificationCard(notification: NotificationItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { /* Xử lý khi click vào thông báo */ },
        verticalAlignment = Alignment.Top
    ) {
        // Hình ảnh sản phẩm
        Image(
            painter = painterResource(id = notification.imageRes),
            contentDescription = "Product Image",
            modifier = Modifier
                .size(70.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = notification.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )

                notification.tag?.let {
                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                if (it == "HOT!") Color.Red.copy(alpha = 0.8f)
                                else Color.Green.copy(alpha = 0.8f)
                            )
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = it,
                            fontSize = 12.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = notification.description,
                fontSize = 12.sp,
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotificationScreenPreview() {
    Asm_kot104Theme {
        NotificationScreen()
    }
}