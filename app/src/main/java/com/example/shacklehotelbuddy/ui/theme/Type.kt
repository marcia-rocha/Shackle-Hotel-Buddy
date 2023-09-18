package com.example.shacklehotelbuddy.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class CustomTypography constructor(
    val caption: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),

    val bodyMedium: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),

    val bodyMediumBold: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),

    val title: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 44.sp,
        lineHeight = 48.4.sp,
        letterSpacing = 2.sp
    ),

    val subtitle: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.5.sp
    ),

    val button: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    )
)

val LocalTypography = staticCompositionLocalOf {
    CustomTypography()
}