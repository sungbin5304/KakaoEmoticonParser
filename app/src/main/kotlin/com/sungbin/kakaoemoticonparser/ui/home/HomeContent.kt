package com.sungbin.kakaoemoticonparser.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationSpec
import com.airbnb.lottie.compose.rememberLottieAnimationState
import com.sungbin.kakaoemoticonparser.R
import com.sungbin.kakaoemoticonparser.theme.typography
import me.sungbin.androidutils.util.toastutil.ToastUtil

@ExperimentalComposeUiApi
@Composable
fun HomeContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = stringResource(R.string.app_name),
                            style = typography.body1
                        )
                        Text(
                            text = stringResource(R.string.copyright),
                            style = typography.caption
                        )
                    }
                },
                elevation = dimensionResource(R.dimen.margin_half)
            )
        },
        content = {
            BindHomeContent()
        }
    )
}

@ExperimentalComposeUiApi
@Composable
private fun BindHomeContent() {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchText by remember { mutableStateOf(TextFieldValue()) }
    val animationSpec = remember { LottieAnimationSpec.RawRes(R.raw.search) }
    val animationState =
        rememberLottieAnimationState(autoPlay = true, repeatCount = Integer.MAX_VALUE)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(dimensionResource(R.dimen.margin_default))
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchText,
            label = { Text(text = stringResource(R.string.main_search_emoticon)) },
            onValueChange = { searchText = it },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    tint = Color.Gray,
                )
            },
            maxLines = 1,
            singleLine = true,
            keyboardActions = KeyboardActions(
                onDone = {
                    ToastUtil.show(context, "onDone")
                    keyboardController?.hideSoftwareKeyboard()
                }
            )
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight()
        ) {
            LottieAnimation(
                spec = animationSpec,
                animationState = animationState,
                modifier = Modifier
                    .height(50.dp)
                    .width(250.dp)
                    .padding(top = dimensionResource(R.dimen.margin_default))
            )
            Text(
                text = stringResource(R.string.main_search_first),
                style = typography.body1,
                modifier = Modifier.padding(top = dimensionResource(R.dimen.margin_twice))
            )
        }
    }
}
