package com.example.loginfirebase

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.google.firebase.auth.FirebaseUser
import gaur.himanshu.login.GoogleSignInUtils

@Composable
fun inforUI(navController: NavController) {
    Column (
        modifier = Modifier
            .fillMaxSize().padding(top = 30.dp)
    ) {
        Header(navController)
        Box(
            modifier = Modifier
            .weight(1f)
        ) {
            inforUser()
        }
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.main_color)
            ),
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
            
        ) {
            Text(
                "Back",
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp)
            )
        }
    }

}


@Composable
fun Header(navController: NavController) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface (
            color = colorResource(R.color.main_color),
            shape = RoundedCornerShape(14.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 10.dp, start = 15.dp, end = 10.dp, bottom = 10.dp)
            )
        }

        Text(
            text = "Profile",
            color = colorResource(R.color.main_color),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(1f),
            textAlign = TextAlign.Center
        )
        Image(
            painter = painterResource(R.drawable.baseline_output_24),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    GoogleSignInUtils.signOut(context) {
                        Log.d("Succes", "Đăng xuất thành công")
                        navController.navigate("login")
                    }

                }
        )
    }
}

@Preview (showBackground = true)
@Composable
fun pvHeader() {
//    Header()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun inforUser() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
//                .padding(10.dp)
                     ,
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
            )
            Image(
                painter = painterResource(R.drawable.baseline_camera_alt_24),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .offset(x = 50.dp, y = 70.dp)
            )
        }
        Column (
            modifier = Modifier
                .padding(top = 40.dp, start = 20.dp, end = 20.dp)
        ) {
            Text(
                "Name",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
            TextField(
                value = "Đoàn Quốc Thông",
                onValueChange = {},
                readOnly = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                textStyle = TextStyle(fontSize = 20.sp, color = Color.Gray),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
//                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
                    .border(1.dp, Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
            )
            Text(
                "Email",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
            TextField(
                value = "thongdoan742@gmail.com",
                onValueChange = {},
                readOnly = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                textStyle = TextStyle(fontSize = 20.sp, color = Color.Gray),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
//                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
                    .border(1.dp, Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
            )
            Text(
                "Date of Birth",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextField(
                    value = "16/12/2004",
                    onValueChange = {},
                    readOnly = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    textStyle = TextStyle(fontSize = 20.sp, color = Color.Gray),
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth()
//                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
                        .border(1.dp, Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                )
                Image(
                    painter = painterResource(R.drawable.baseline_keyboard_arrow_down_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .offset(x = 300.dp, y = 15.dp),
                )
            }
            


        }
    }
}

@Preview (showBackground = true)
@Composable
fun pvinforUser() {
    inforUser()
}



