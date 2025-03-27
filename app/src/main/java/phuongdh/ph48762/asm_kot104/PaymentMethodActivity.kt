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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import phuongdh.ph48762.asm_kot104.ui.theme.Asm_kot104Theme

class PaymentMethodActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Asm_kot104Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    PaymentMethodScreen()
                }
            }
        }
    }
}

data class PaymentCard(
    val id: String,
    val cardType: String, // "mastercard" hoặc "visa"
    val lastFourDigits: String,
    val cardHolderName: String,
    val expiryDate: String,
    var isDefault: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentMethodScreen() {
    val context = LocalContext.current

    // Danh sách thẻ thanh toán mẫu
    val paymentCards = remember {
        mutableStateListOf(
            PaymentCard(
                id = "1",
                cardType = "mastercard",
                lastFourDigits = "3947",
                cardHolderName = "Jennyfer Doe",
                expiryDate = "05/23",
                isDefault = true
            ),
            PaymentCard(
                id = "2",
                cardType = "visa",
                lastFourDigits = "3947",
                cardHolderName = "Jennyfer Doe",
                expiryDate = "05/23",
                isDefault = false
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Payment method",
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
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Xử lý thêm phương thức thanh toán mới
                    val intent = Intent(context, AddPaymentMethodActivity::class.java)
                    context.startActivity(intent)
                },
                containerColor = Color.White,
                contentColor = Color.Black,
                shape = CircleShape,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Payment Method",
                    tint = Color.Black
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(paymentCards) { card ->
                PaymentCardItem(
                    card = card,
                    onDefaultChange = { isDefault ->
                        // Cập nhật trạng thái mặc định của thẻ
                        if (isDefault) {
                            // Bỏ chọn tất cả các thẻ khác
                            paymentCards.forEachIndexed { index, c ->
                                paymentCards[index] = c.copy(isDefault = c.id == card.id)
                            }
                        }
                    }
                )
            }

            // Thêm khoảng trống ở cuối để tránh FAB che phủ nội dung
            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}

@Composable
fun PaymentCardItem(
    card: PaymentCard,
    onDefaultChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Thẻ thanh toán
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            // Hiển thị hình ảnh thẻ dựa vào loại thẻ
            Image(
                painter = painterResource(
                    id = if (card.cardType == "mastercard") R.drawable.visa1
                    else R.drawable.visa2
                ),
                contentDescription = "Payment Card",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.FillWidth
            )
        }

        // Checkbox "Use as default payment method"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = card.isDefault,
                onCheckedChange = { isChecked ->
                    onDefaultChange(isChecked)
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Black,
                    uncheckedColor = Color.Gray
                )
            )

            Text(
                text = "Use as default payment method",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PaymentMethodScreenPreview() {
    Asm_kot104Theme {
        PaymentMethodScreen()
    }
}