package phuongdh.ph48762.asm_kot104

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
                    color = Color(0xFFF5F5F5)
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

    val tabs = listOf("Delivered", "Processing", "Canceled")
    var selectedTabIndex by remember { mutableStateOf(0) }

    val orders = listOf(
        Order(
            id = "238562312",
            date = "20/03/2020",
            quantity = 3,
            totalAmount = 150,
            status = "Delivered"
        ),
        Order(
            id = "238562313",
            date = "22/03/2020",
            quantity = 5,
            totalAmount = 280,
            status = "Delivered"
        ),
        Order(
            id = "238562314",
            date = "25/03/2020",
            quantity = 2,
            totalAmount = 95,
            status = "Delivered"
        )
    )

    Scaffold(
        containerColor = Color(0xFFF5F5F5),
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                modifier = Modifier
                    .height(70.dp)
                    .shadow(8.dp, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            ) {
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        val intent = Intent(context, HomeActivity::class.java)
                        context.startActivity(intent)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home",
                            modifier = Modifier.size(26.dp)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF4A90E2),
                        unselectedIconColor = Color(0xFF9E9E9E),
                        indicatorColor = Color(0xFFE3F2FD)
                    )
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {
                        val intent = Intent(context, FavoritesActivity::class.java)
                        context.startActivity(intent)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.bookmark),
                            contentDescription = "Bookmark",
                            modifier = Modifier.size(26.dp)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF4A90E2),
                        unselectedIconColor = Color(0xFF9E9E9E),
                        indicatorColor = Color(0xFFE3F2FD)
                    )
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {
                        val intent = Intent(context, NotificationActivity::class.java)
                        context.startActivity(intent)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = "Notifications",
                            modifier = Modifier.size(26.dp)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF4A90E2),
                        unselectedIconColor = Color(0xFF9E9E9E),
                        indicatorColor = Color(0xFFE3F2FD)
                    )
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {
                        val intent = Intent(context, ProfileActivity::class.java)
                        context.startActivity(intent)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "Profile",
                            modifier = Modifier.size(26.dp)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF4A90E2),
                        unselectedIconColor = Color(0xFF9E9E9E),
                        indicatorColor = Color(0xFFE3F2FD)
                    )
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Header với gradient
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF4A90E2),
                                Color(0xFF5B9FE3)
                            )
                        )
                    )
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        IconButton(
                            onClick = {
                                if (context is ComponentActivity) {
                                    context.finish()
                                }
                            },
                            modifier = Modifier
                                .size(45.dp)
                                .background(Color.White.copy(alpha = 0.25f), CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Column {
                            Text(
                                text = "Order History",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.White.copy(alpha = 0.9f)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "My Orders",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }

                    // Badge số lượng orders
                    Box(
                        modifier = Modifier
                            .size(45.dp)
                            .background(Color.White.copy(alpha = 0.25f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${orders.size}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Tab Row với style mới
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    tabs.forEachIndexed { index, title ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    if (selectedTabIndex == index) Color(0xFF4A90E2)
                                    else Color.Transparent
                                )
                                .clickable { selectedTabIndex = index }
                                .padding(vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = title,
                                fontSize = 14.sp,
                                fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Medium,
                                color = if (selectedTabIndex == index) Color.White else Color(0xFF757575)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Danh sách đơn hàng
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                items(orders) { order ->
                    OrderCard(order = order)
                }
            }
        }
    }
}

@Composable
fun OrderCard(order: Order) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header: Order ID và Status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Order #${order.id}",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212121)
                )

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            when (order.status) {
                                "Delivered" -> Color(0xFF4CAF50).copy(alpha = 0.1f)
                                "Processing" -> Color(0xFFFFA726).copy(alpha = 0.1f)
                                "Canceled" -> Color(0xFFEF5350).copy(alpha = 0.1f)
                                else -> Color(0xFF9E9E9E).copy(alpha = 0.1f)
                            }
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = order.status,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = when (order.status) {
                            "Delivered" -> Color(0xFF4CAF50)
                            "Processing" -> Color(0xFFFFA726)
                            "Canceled" -> Color(0xFFEF5350)
                            else -> Color(0xFF9E9E9E)
                        }
                    )
                }
            }

            Divider(color = Color(0xFFE0E0E0))

            // Order Details
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = order.date,
                        fontSize = 13.sp,
                        color = Color(0xFF757575)
                    )

                    Text(
                        text = "Quantity: ${order.quantity.toString().padStart(2, '0')}",
                        fontSize = 13.sp,
                        color = Color(0xFF757575)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Total Amount",
                        fontSize = 12.sp,
                        color = Color(0xFF9E9E9E)
                    )
                    Text(
                        text = "$${order.totalAmount}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4A90E2)
                    )
                }
            }

            // Action Button
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4A90E2)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "View Details",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
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