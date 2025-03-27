package phuongdh.ph48762.asm_kot104

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import phuongdh.ph48762.asm_kot104.ui.theme.Asm_kot104Theme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Asm_kot104Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

data class Category(
    val name: String,
    val icon: Int,
    val isSelected: Boolean = false
)

data class Product(
    val name: String,
    val price: Double,
    val image: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    // Lấy context hiện tại để sử dụng cho Intent
    val context = LocalContext.current

    val categories = listOf(
        Category("Popular", R.drawable.star, isSelected = true),
        Category("Chair", R.drawable.chair2),
        Category("Table", R.drawable.table),
        Category("Armchair", R.drawable.armchair),
        Category("Bed", R.drawable.bed)
    )

    val products = listOf(
        Product("Black Simple Lamp", 12.00, R.drawable.sp4),
        Product("Minimal Stand", 25.00, R.drawable.sp2),
        Product("Coffee Chair", 20.00, R.drawable.sp3),
        Product("Simple Desk", 50.00, R.drawable.sp1)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Make home",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "BEAUTIFUL",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /* Xử lý tìm kiếm */ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.Gray
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        val intent = Intent(context, CartActivity::class.java)
                        context.startActivity(intent)
                    }) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Cart",
                            tint = Color.Gray
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                contentColor = Color.Gray
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = { /* Xử lý home */ },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home"
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        unselectedIconColor = Color.Gray,
                        indicatorColor = Color.White
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
                            contentDescription = "Bookmark"
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        unselectedIconColor = Color.Gray,
                        indicatorColor = Color.White
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
                            contentDescription = "Notifications"
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        unselectedIconColor = Color.Gray,
                        indicatorColor = Color.White
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
                            contentDescription = "Profile"
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        unselectedIconColor = Color.Gray,
                        indicatorColor = Color.White
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
            // Categories
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                categories.forEach { category ->
                    CategoryItem(
                        category = category,
                        isSelected = category.isSelected
                    )
                }
            }

            // Products Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(products) { product ->
                    ProductItem(
                        product = product,
                        onClick = {
                            // Chỉ xử lý sự kiện click cho sản phẩm "Minimal Stand"
                            if (product.name == "Minimal Stand") {
                                val intent = Intent(context, ProductDetailActivity::class.java)
                                // Truyền thông tin sản phẩm qua Intent
                                intent.putExtra("productName", product.name)
                                intent.putExtra("productPrice", product.price)
                                intent.putExtra("productImage", product.image)
                                context.startActivity(intent)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(60.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp)),

            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = category.icon),
                contentDescription = category.name,
                modifier = Modifier.size(24.dp),
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = category.name,
            fontSize = 12.sp,
            color = if (isSelected) Color.Black else Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ProductItem(
    product: Product,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clickable(onClick = onClick),  // Thêm sự kiện click vào Card
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray.copy(alpha = 0.1f)
        )
    ) {
        Column {
            // Product Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                Image(
                    painter = painterResource(id = product.image),
                    contentDescription = product.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Bookmark icon
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.White.copy(alpha = 0.8f))
                        .align(Alignment.BottomEnd),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bookmark),
                        contentDescription = "Bookmark",
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            // Product Info
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = product.name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "$ ${product.price}0",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    Asm_kot104Theme {
        HomeScreen()
    }
}