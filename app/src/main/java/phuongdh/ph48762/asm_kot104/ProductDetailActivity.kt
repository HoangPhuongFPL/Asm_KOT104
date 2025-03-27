package phuongdh.ph48762.asm_kot104

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import phuongdh.ph48762.asm_kot104.ui.theme.Asm_kot104Theme

class ProductDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Asm_kot104Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    ProductDetailScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen() {
    //lấy context hiện tại
    val context = LocalContext.current

    var quantity by remember { mutableStateOf(1) }
    var selectedColorIndex by remember { mutableStateOf(0) }

    val colors = listOf(
        Color.LightGray,
        Color(0xFFBF8D67), // Màu nâu
        Color(0xFFE6D2B5)  // Màu be
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = {
                            /* Quay lại màn hình trước */
                            // Kiểm tra xem context có phải là Activity không
                            if (context is ComponentActivity) {
                                context.finish() // Quay lại màn hình trước

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
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Phần hình ảnh sản phẩm và lựa chọn màu sắc
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            ) {
                // Hình ảnh sản phẩm
                Image(
                    painter = painterResource(id = R.drawable.sp2),
                    contentDescription = "Minimal Stand",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize().padding(start = 35.dp)
                        .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
                )

                // Lựa chọn màu sắc
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White.copy(alpha = 0.8f))
                        .padding(vertical = 8.dp, horizontal = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    colors.forEachIndexed { index, color ->
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(color)
                                .border(
                                    width = if (selectedColorIndex == index) 2.dp else 0.dp,
                                    color = if (selectedColorIndex == index) Color.Black else Color.Transparent,
                                    shape = CircleShape
                                )
                                .clickable { selectedColorIndex = index }
                        )
                    }
                }
            }

            // Phần thông tin sản phẩm
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Tên sản phẩm và giá
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Minimal Stand",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    // Bộ chọn số lượng
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        IconButton(
                            onClick = { if (quantity > 1) quantity-- },
                            modifier = Modifier
                                .size(30.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color.LightGray.copy(alpha = 0.3f))
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.remove),
                                contentDescription = "Decrease",
                                tint = Color.Black,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        Text(
                            text = String.format("%02d", quantity),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )

                        IconButton(
                            onClick = { quantity++ },
                            modifier = Modifier
                                .size(30.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color.LightGray.copy(alpha = 0.3f))
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Increase",
                                tint = Color.Black,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }

                // Giá
                Text(
                    text = "$ 50",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // Đánh giá
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(20.dp)
                    )

                    Text(
                        text = "4.5",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 4.dp)
                    )

                    Text(
                        text = "(50 reviews)",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                // Mô tả sản phẩm
                Text(
                    text = "Minimal Stand is made of by natural wood. The design that is very simple and minimal. This is truly one of the best furnitures in any family for now. With 3 different colors, you can easily select the best match for your home.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Nút thêm vào giỏ hàng
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { /* Xử lý bookmark */ },
                        modifier = Modifier
                            .size(48.dp)
                            .border(
                                width = 1.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.bookmark),
                            contentDescription = "Bookmark",
                            tint = Color.Black
                        )
                    }

                    Button(
                        onClick = { /* Xử lý thêm vào giỏ hàng */ },
                        modifier = Modifier
                            .height(48.dp)
                            .width(250.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Add to cart",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductDetailScreenPreview() {
    Asm_kot104Theme {
        ProductDetailScreen()
    }
}