package com.ramasofts.iotdevtoolbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.ramasofts.iotdevtoolbox.presentation.navigation.AppNavGraph
import com.ramasofts.iotdevtoolbox.presentation.ui.AppEntry
import com.ramasofts.iotdevtoolbox.presentation.viewmodel.MainViewModel
import com.ramasofts.iotdevtoolbox.ui.theme.IOTDevToolBoxTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private var isLoading = true

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition {
            isLoading
        }

        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            delay(2000)
            isLoading = false
        }

        setContent {
            IOTDevToolBoxTheme {
                AppNavGraph()
            }
        }
    }
}