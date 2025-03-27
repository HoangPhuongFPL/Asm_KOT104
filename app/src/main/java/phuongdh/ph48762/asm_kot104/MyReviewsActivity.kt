package phuongdh.ph48762.asm_kot104

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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

class MyReviewsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Asm_kot104Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    MyReviewsScreen()
                }
            }
        }
    }
}

data class Review(
    val id: String,
    val productName: String,
    val productPrice: Double,
    val rating: Int, // 1-5
    val date: String,
    val reviewText: String,
    val productImageRes: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyReviewsScreen() {
    val context = LocalContext.current

    // Danh sách đánh giá mẫu
    val reviews = listOf(
        Review(
            id = "1",
            productName = "Coffee Table",
            productPrice = 50.00,
            rating = 5,
            date = "20/03/2020",
            reviewText = "Nice Furniture with good delivery. The delivery time is very fast. Then products look like exactly the picture in the app. Besides, color is also the same and quality is very good despite very cheap price",
            productImageRes = R.drawable.sp1
        ),
        Review(
            id = "2",
            productName = "Coffee Table",
            productPrice = 50.00,
            rating = 5,
            date = "20/03/2020",
            reviewText = "Nice Furniture with good delivery. The delivery time is very fast. Then products look like exactly the picture in the app. Besides, color is also the same and quality is very good despite very cheap price",
            productImageRes = R.drawable.sp2
        ),
        Review(
            id = "3",
            productName = "Coffee Table",
            productPrice = 50.00,
            rating = 5,
            date = "20/03/2020",
            reviewText = "Nice Furniture with good delivery. The delivery time is very fast. Then products look like exactly the picture in the app. Besides, color is also the same and quality is very good despite very cheap price",
            productImageRes = R.drawable.sp3
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My reviews",
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
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(reviews) { review ->
                ReviewItem(review = review)
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ReviewItem(review: Review) {
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
            // Phần trên: Hình ảnh sản phẩm, tên và giá
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Hình ảnh sản phẩm
                Image(
                    painter = painterResource(id = review.productImageRes),
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))

                // Tên và giá sản phẩm
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = review.productName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "$ ${review.productPrice}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Phần giữa: Đánh giá sao và ngày
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Đánh giá sao
                Row {
                    repeat(5) { index ->
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = if (index < review.rating) Color(0xFFFFC107) else Color.LightGray,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                // Ngày đánh giá
                Text(
                    text = review.date,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Phần dưới: Nội dung đánh giá
            Text(
                text = review.reviewText,
                fontSize = 14.sp,
                color = Color.DarkGray,
                lineHeight = 20.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyReviewsScreenPreview() {
    Asm_kot104Theme {
        MyReviewsScreen()
    }
}