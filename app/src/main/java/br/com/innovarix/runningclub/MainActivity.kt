package br.com.innovarix.runningclub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.innovarix.runningclub.core_theme.components.input.RCInput
import br.com.innovarix.runningclub.core_theme.components.toolbar.RCToolbar
import br.com.innovarix.runningclub.core_theme.components.toolbar.RCToolbarStyleType
import br.com.innovarix.runningclub.core_theme.theme.RunningClubTheme
import br.com.innovarix.runningclub.register.RCRegisterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RunningClubTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                       RCRegisterScreen()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun GreetingPreview() {
    RunningClubTheme {
        Column {
            RCInput(label = "Nome completo")

            RCInput(label = "Nome completo", value = "jose", error = "Campo obrigatório")

            RCToolbar(text = "Teste Nivelamento")

            RCToolbar(colors = RCToolbarStyleType.SECONDARY, text = "Teste Nivelamento")
        }
    }
}