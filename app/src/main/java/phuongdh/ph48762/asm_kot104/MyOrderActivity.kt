package phuongdh.ph48762.asm_kot104

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import phuongdh.ph48762.asm_kot104.ui.theme.Asm_kot104Theme

class MyOrderActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Asm_kot104Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    MyOrderScreen()
                }
            }
        }
    }
}

data class Order(
    val id: String,
    val date: String,
    val quantity: Int,
    val totalAmount: Int,
    val status: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyOrderScreen() {
    val context = LocalContext.current

    // Danh sách các tab
    val tabs = listOf("Delivered", "Processing", "Canceled")
    var selectedTabIndex by remember { mutableStateOf(0) }

    // Danh sách đơn hàng mẫu
    val orders = listOf(
        Order(
            id = "No238562312",
            date = "20/03/2020",
            quantity = 3,
            totalAmount = 150,
            status = "Delivered"
        ),
        Order(
            id = "No238562312",
            date = "20/03/2020",
            quantity = 3,
            totalAmount = 150,
            status = "Delivered"
        ),
        Order(
            id = "No238562312",
            date = "20/03/2020",
            quantity = 3,
            totalAmount = 150,
            status = "Delivered"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My order",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        // Quay lại màn hình trước
                        if (context is ComponentActivity) {
                            context.finish()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
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

                    IconButton(onClick = {
                        val intent = Intent(context, NotificationActivity::class.java)
                        context.startActivity(intent)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.Gray
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            // Tab Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                tabs.forEachIndexed { index, title ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { selectedTabIndex = index },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = title,
                                fontSize = 16.sp,
                                fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedTabIndex == index) Color.Black else Color.Gray
                            )

                            if (selectedTabIndex == index) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Box(
                                    modifier = Modifier
                                        .width(40.dp)
                                        .height(2.dp)
                                        .background(Color.Black)
                                )
                            }
                        }
                    }
                }
            }

            // Danh sách đơn hàng
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(orders) { order ->
                    OrderCard(order = order)

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun OrderCard(order: Order) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Dòng 1: Mã đơn hàng và ngày
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Order ${order.id}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )

                Text(
                    text = order.date,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Dòng 2: Số lượng và tổng tiền
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Quantity: ${order.quantity.toString().padStart(2, '0')}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Text(
                    text = "Total Amount: $${order.totalAmount}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Dòng 3: Nút chi tiết và trạng thái
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        // Xử lý chuyển đến màn hình chi tiết đơn hàng
                    },
                    modifier = Modifier
                        .width(100.dp)
                        .height(36.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = "Detail",
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }

                Text(
                    text = order.status,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = when (order.status) {
                        "Delivered" -> Color.Green
                        "Processing" -> Color.Blue
                        "Canceled" -> Color.Red
                        else -> Color.Gray
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyOrderScreenPreview() {
    Asm_kot104Theme {
        MyOrderScreen()
    }
}