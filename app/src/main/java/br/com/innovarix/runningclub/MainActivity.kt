package br.com.innovarix.runningclub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.innovarix.runningclub.core_theme.components.input.RCInput
import br.com.innovarix.runningclub.core_theme.components.toolbar.RCToolbar
import br.com.innovarix.runningclub.core_theme.components.toolbar.RCToolbarStyleType
import br.com.innovarix.runningclub.core_theme.theme.RunningClubTheme
import br.com.innovarix.runningclub.register.finish.RCRegisterFinishScreen
import br.com.innovarix.runningclub.register.finish.RCRegisterFinishUiAction
import br.com.innovarix.runningclub.register.finish.RCRegisterFinishViewModel
import br.com.innovarix.runningclub.register.lead.RCRegisterScreen
import br.com.innovarix.runningclub.register.lead.RCRegisterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RunningClubTheme {
                val viewModel: RCRegisterFinishViewModel by viewModel()

                val state = viewModel.state.collectAsStateWithLifecycle().value

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)) {

                        viewModel.dispatchAction(RCRegisterFinishUiAction.OnInit)

                        RCRegisterFinishScreen(
                            state = state,
                            action = viewModel::dispatchAction
                        )

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