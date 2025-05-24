package com.brhdc.binjwatch.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.brhdc.binjwatch.R
import com.brhdc.binjwatch.ui.theme.COMPONENT_ICON_SIZE_120DP
import com.brhdc.binjwatch.ui.theme.Gray

@Composable
fun EmptyScreen(message: String = "") {
    val modifier = Modifier

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.size(COMPONENT_ICON_SIZE_120DP),
            painter = painterResource(id = R.drawable.ic_sad_face),
            contentDescription = stringResource(id = R.string.ic_sad_face),
            tint = Gray
        )
        Text(
            text = stringResource(id = R.string.found_no_content),
            color = Gray,
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = message,
            color = Gray,
            style = MaterialTheme.typography.bodySmall
        )
    }
}