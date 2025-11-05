package br.com.innovarix.runningclub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.innovarix.runningclub.core_theme.components.input.RCInput
import br.com.innovarix.runningclub.core_theme.components.price.RCPlanPrice
import br.com.innovarix.runningclub.core_theme.components.price.RCPlanPriceUiModel
import br.com.innovarix.runningclub.core_theme.components.toolbar.RCToolbar
import br.com.innovarix.runningclub.core_theme.components.toolbar.RCToolbarStyleType
import br.com.innovarix.runningclub.core_theme.theme.RunningClubTheme
import br.com.innovarix.runningclub.feedback.RCFeedBackScreen
import br.com.innovarix.runningclub.feedback.RCFeedBackType
import br.com.innovarix.runningclub.feedback.RCFeedBackUiModel
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

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)) {


                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {

                            RCPlanPrice(
                                priceUiModel = RCPlanPriceUiModel(
                                    description = "Mensal",
                                    price = "R$ 100,00",
                                    isSelected = true
                                )
                            )

                            Spacer(modifier = Modifier.size(100.dp))
                            
                            RCPlanPrice(
                                priceUiModel = RCPlanPriceUiModel(
                                    description = "Trimestal",
                                    price = "R$ 200,00"
                                )
                            )
                        }
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
            RCFeedBackScreen(feedBackUiModel = RCFeedBackUiModel(RCFeedBackType.SUCCESS))
        }
    }
}