package com.example.myfirstproject.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.myfirstproject.R

val EduTASBeginner= FontFamily(
    Font(R.font.edutasbeginner_bold),
    Font(R.font.edutasbeginner_medium),
    Font(R.font.edutasbeginner_regular),
    Font(R.font.edutasbeginner_semibold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = EduTASBeginner,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    displayMedium = TextStyle(
        fontFamily = EduTASBeginner,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    labelSmall = TextStyle(
        fontFamily = EduTASBeginner,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = EduTASBeginner,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)

