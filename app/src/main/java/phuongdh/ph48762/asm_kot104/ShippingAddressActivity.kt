package phuongdh.ph48762.asm_kot104

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import phuongdh.ph48762.asm_kot104.ui.theme.Asm_kot104Theme

class ShippingAddressActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Asm_kot104Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    ShippingAddressScreen()
                }
            }
        }
    }
}

data class Address(
    val id: String,
    val name: String,
    val address: String,
    var isSelected: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShippingAddressScreen() {
    val context = LocalContext.current

    // Danh sách địa chỉ mẫu
    val addresses = remember {
        mutableStateListOf(
            Address(
                id = "1",
                name = "Bruno Fernandes",
                address = "25 rue Robert Latouche, Nice, 06200, Côte D'azur, France",
                isSelected = true
            ),
            Address(
                id = "2",
                name = "Bruno Fernandes",
                address = "25 rue Robert Latouche, Nice, 06200, Côte D'azur, France",
                isSelected = false
            ),
            Address(
                id = "3",
                name = "Bruno Fernandes",
                address = "25 rue Robert Latouche, Nice, 06200, Côte D'azur, France",
                isSelected = false
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Shipping address",
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
                    // Xử lý thêm địa chỉ mới
                    val intent = Intent(context, AddShippingAddressActivity::class.java)
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
                    contentDescription = "Add Address",
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
            items(addresses) { address ->
                AddressCard(
                    address = address,
                    onEditClick = {
                        // Xử lý chỉnh sửa địa chỉ
                    },
                    onSelectClick = { isSelected ->
                        // Cập nhật trạng thái chọn của địa chỉ
                        if (isSelected) {
                            // Bỏ chọn tất cả các địa chỉ khác
                            addresses.forEachIndexed { index, addr ->
                                addresses[index] = addr.copy(isSelected = addr.id == address.id)
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
fun AddressCard(
    address: Address,
    onEditClick: () -> Unit,
    onSelectClick: (Boolean) -> Unit
) {
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
            // Checkbox và label
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = address.isSelected,
                    onCheckedChange = { isChecked ->
                        onSelectClick(isChecked)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Black,
                        uncheckedColor = Color.Gray
                    )
                )

                Text(
                    text = "Use as the shipping address",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Tên và nút chỉnh sửa
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = address.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )

                IconButton(
                    onClick = onEditClick,
                    modifier = Modifier.size(24.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Edit",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Địa chỉ chi tiết
            Text(
                text = address.address,
                fontSize = 14.sp,
                color = Color.Gray,
                lineHeight = 20.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShippingAddressScreenPreview() {
    Asm_kot104Theme {
        ShippingAddressScreen()
    }
}