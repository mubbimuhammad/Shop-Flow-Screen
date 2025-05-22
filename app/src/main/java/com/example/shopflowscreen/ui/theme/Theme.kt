package com.example.shopflowscreen.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF8ACE00),
    onPrimary = Color.Black,
    secondary = Color(0xFF242424),
    onSecondary = Color.White,
    tertiary = Color(0xFF333333),
    onTertiary = Color.White,
    background = Color(0xFF1A1A1A),
    onBackground = Color.White,
    surface = Color(0xFF242424),
    onSurface = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF4CAF50),
    onPrimary = Color.White,
    secondary = Color(0xFF86BC25),
    onSecondary = Color.White,
    tertiary = Color(0xFF1E3620),
    onTertiary = Color(0xFFE3F2E3),
    background = Color(0xFF121212),
    onBackground = Color.White,
    surface = Color(0xFF1E1E1E),
    onSurface = Color.White
)

@Composable
fun ShopFlowTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),

    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}


object AppTheme {

    object Spacing {
        val small = 8
        val medium = 16
        val large = 24
        val xlarge = 32
    }


    object Shapes {
        val small = 4
        val medium = 8
        val large = 16
    }


    object BrandColors {
        val successGreen = Color(0xFF4CAF50)
        val errorRed = Color(0xFFE53935)
        val warningYellow = Color(0xFFFFC107)
        val infoBlue = Color(0xFF2196F3)
    }


    object Animation {
        const val shortDuration = 150
        const val mediumDuration = 300
        const val longDuration = 500
    }
}