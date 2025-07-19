package com.example.voca.core.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.voca.ui.theme.VocaTheme

@Composable
fun VocaTextFieldAnimated(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    value: String,
    label: String,
    error: String? = null,
    isPassword: Boolean = false,
    imeAction: ImeAction = ImeAction.Next,
    keyBoardType: KeyboardType = KeyboardType.Text,
    strokeWidth: Dp = 2.dp,
    focusedStrokeWidth: Dp = 4.dp,
    animationDuration: Int = 1000,
    hint: String? = null
) {
    val focusManager = LocalFocusManager.current
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    // Animated border color
    val animatedBorderColor by animateColorAsState(
        targetValue = when {
            error != null -> MaterialTheme.colorScheme.error
            isFocused -> MaterialTheme.colorScheme.primary
            else -> MaterialTheme.colorScheme.outline.copy(
                alpha = 0.5f
            )
        },
        animationSpec = tween(durationMillis = animationDuration),
        label = "border_color_animation"
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge.copy(
                color = Color.Gray,
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .height(80.dp)
                .focusRequester(focusRequester)
                .onFocusChanged {focusState->
                    isFocused = focusState.isFocused
                },
            textStyle = MaterialTheme.typography.titleLarge,
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                hint?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.Gray
                        )
                    )
                }
            },
            maxLines = 1,
            isError = error != null,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isPassword) KeyboardType.Password else keyBoardType,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            singleLine = true,
            supportingText = {
                if (error != null) {
                    Text(text = error)
                }
            },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = animatedBorderColor,
                focusedBorderColor = animatedBorderColor,
                errorBorderColor = animatedBorderColor,
                disabledBorderColor = animatedBorderColor
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun VocaTextFieldPreview() {
    VocaTheme {
        VocaTextFieldAnimated(
            onValueChange = {},
            value = "",
            label = "Email",
        )
    }
}