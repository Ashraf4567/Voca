package com.example.voca.feature.auth.presentation

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.voca.core.presentation.components.VocaTextFieldAnimated
import com.example.voca.feature.auth.presentation.components.GoogleSignInButton
import com.example.voca.feature.auth.presentation.components.VocaLoginButton
import com.example.voca.ui.theme.VocaTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    val loginViewModel: LoginViewModel = koinViewModel()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val activity = LocalContext.current as Activity

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp , vertical = 32.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Text(
            text = "Let's Login",
            style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.Bold
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "And Record Your Voice",
            style = MaterialTheme.typography.titleSmall.copy(
                color = Color.Gray.copy(alpha = 0.7f)
            ),
        )
        Spacer(modifier = Modifier.height(32.dp))

        VocaTextFieldAnimated(
            onValueChange = {
                email = it
            },
            value = email,
            hint = "Example: johndoe@gmail.com",
            label = "Email Address",
            imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(16.dp))

        VocaTextFieldAnimated(
            onValueChange = {
                password = it
            },
            value = password,
            hint = "*******",
            label = "Password",
            imeAction = ImeAction.Done,
            isPassword = true
        )
        Text(
            text = "Forgot Password?",
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.primary
            ),
            textDecoration = TextDecoration.Underline,
        )

        Spacer(modifier = Modifier.height(32.dp))

        VocaLoginButton{

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Or Login With",
            style = MaterialTheme.typography.titleSmall.copy(
                color = Color.Gray.copy(alpha = 0.7f)
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        GoogleSignInButton{
            loginViewModel.signInWithGoogle(activity)
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview () {
    VocaTheme {
        LoginScreen()
    }
}