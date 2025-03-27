package phuongdh.ph48762.asm_kot104

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import phuongdh.ph48762.asm_kot104.ui.theme.Asm_kot104Theme

class AddPaymentMethodActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Asm_kot104Theme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AddPaymentScreen()
                }
            }
        }
    }
}

@Composable
fun AddPaymentScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Chức năng sắp được cập nhật!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Chúng tôi đang nỗ lực phát triển tính năng này. Hãy quay lại sau nhé!",
            fontSize = 14.sp,
            color = androidx.compose.ui.graphics.Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { /* quay lại màn hình trước */
            if (context is ComponentActivity) {
                context.finish()
            }
        }) {
            Text(text = "Quay lại")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddPaymentPreview() {
    Asm_kot104Theme {
        AddPaymentScreen()
    }
}
