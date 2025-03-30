package com.example.loginfirebase

import android.app.Activity
import android.credentials.GetCredentialRequest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping.Companion.Identity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import androidx.compose.foundation.layout.*

@Composable
fun LoginScreen(navController:NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HeaderContainer()
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            LoginContainer()
        }

        BottomContent()
    }
}

@Composable
fun BottomContent() {
    Text(
        "© UTHSmartTasks",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        fontSize = 17.sp

    )
}

@Composable
fun LoginContainer() {
    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ){
        Text(
            "Welcome",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
        Text(
            "Ready to explore? Log in to get started.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            textAlign = TextAlign.Center,
            fontSize = 17.sp
        )
        GoogleSignIn()
    }
}

@Composable
fun GoogleSignIn() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Surface (
            shape = RoundedCornerShape(14.dp),
            color = colorResource(R.color.background_logo_color),
            modifier = Modifier
                .clickable {

                }
        ) {
            Row (
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp, start = 40.dp, end = 40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier
                        .height(20.dp)
                        .width(20.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo_gg),
                        contentDescription = null
                    )
                }
                Text(
                    "SIGN IN WITH GOOGLE",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
        }
    }
}

@Composable
fun HeaderContainer() {
    Column {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .alpha(0.2f)
            ) {
                Image(
                    painter = painterResource(R.drawable.geometric_network),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(500.dp)
                )
            }
            Box(

            ) {
                Surface(
                    color = colorResource(R.color.background_logo_color),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                        .offset(y = 50.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo_uth),
                        contentDescription = null,
                        modifier = Modifier.padding(20.dp)
                    )
                }

            }
        }
        Text(
            "Smart Tasks",
            color = colorResource(R.color.main_color),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            "A simple and efficient to-do app",
            color = colorResource(R.color.main_color),
            fontSize = 17.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }

}

@Preview (showBackground = true)
@Composable
fun PvLoginScreen() {
    val navController = rememberNavController()
    LoginScreen(navController)
}