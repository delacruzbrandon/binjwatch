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
fun LoadingScreen() {
    val modifier = Modifier
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = modifier.size(COMPONENT_ICON_SIZE_120DP),
            painter = painterResource(id = R.drawable.ic_hour_glass),
            contentDescription = stringResource(id = R.string.ic_hour_glass),
            tint = Gray
        )
        Text(
            text = stringResource(id = R.string.loading_content),
            color = Gray,
            style = MaterialTheme.typography.titleSmall
        )
    }
}