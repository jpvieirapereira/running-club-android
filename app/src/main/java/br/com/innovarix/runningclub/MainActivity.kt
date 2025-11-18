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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import br.com.innovarix.runningclub.core_theme.theme.RunningClubTheme
import br.com.innovarix.runningclub.feedback.RCFeedBackScreen
import br.com.innovarix.runningclub.feedback.RCFeedBackType
import br.com.innovarix.runningclub.feedback.RCFeedBackUiModel
import br.com.innovarix.runningclub.register.domain.RCRegisterModel
import br.com.innovarix.runningclub.register.finish.RCRegisterFinishScreen
import br.com.innovarix.runningclub.register.finish.RCRegisterFinishUiAction
import br.com.innovarix.runningclub.register.finish.RCRegisterFinishUiEvent.NavigateToSelectPlan
import br.com.innovarix.runningclub.register.finish.RCRegisterFinishUiEvent.OnBackPressed
import br.com.innovarix.runningclub.register.finish.RCRegisterFinishViewModel
import br.com.innovarix.runningclub.register.lead.RCRegisterScreen
import br.com.innovarix.runningclub.register.lead.RCRegisterUiEvent
import br.com.innovarix.runningclub.register.lead.RCRegisterViewModel
import br.com.innovarix.runningclub.register.navigation.RCRegisterNavigation
import br.com.innovarix.runningclub.register.plans.RCRegisterPlansOffersScreen
import br.com.innovarix.runningclub.register.plans.RCRegisterPlansOffersUiAction
import br.com.innovarix.runningclub.register.plans.RCRegisterPlansOffersUiEvent
import br.com.innovarix.runningclub.register.plans.RCRegisterPlansOffersViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RunningClubTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        RegisterNavigation()
                    }
                }
            }
        }
    }
}

@Composable
fun RegisterNavigation() {
    val backStack = remember { mutableStateListOf<RCRegisterNavigation>(RCRegisterNavigation.Lead) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeAt(backStack.lastIndex) },
        entryProvider = { key ->
            when (key) {
                RCRegisterNavigation.Lead -> NavEntry(key) {
                    StartRegister(backStack = backStack)
                }

                is RCRegisterNavigation.Register -> NavEntry(key) {
                    FinishRegister(backStack, key.register)
                }

                RCRegisterNavigation.Plans -> NavEntry(key) {
                    SelectPlan(backStack)
                }

                else -> {
                    error("Unknown route: $key")
                }
            }
        }
    )
}

@Composable
fun StartRegister(backStack: SnapshotStateList<RCRegisterNavigation>) {
    val viewModel: RCRegisterViewModel = koinViewModel()

    val state = viewModel.state.collectAsStateWithLifecycle().value

    RCRegisterScreen(state = state) { action ->
        viewModel.dispatchAction(action)
    }

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is RCRegisterUiEvent.NavigateToFinish -> {
                    backStack.add(RCRegisterNavigation.Register(event.register))
                }
            }
        }
    }
}

@Composable
fun FinishRegister(
    backStack: SnapshotStateList<RCRegisterNavigation>,
    register: RCRegisterModel
) {
    val viewModel: RCRegisterFinishViewModel = koinViewModel()

    val state = viewModel.state.collectAsStateWithLifecycle().value

    RCRegisterFinishScreen(state = state) { action ->
        viewModel.dispatchAction(action)
    }

    LaunchedEffect(Unit) {
        viewModel.dispatchAction(RCRegisterFinishUiAction.OnInit(register))

        viewModel.event.collect { event ->
            when (event) {
                OnBackPressed -> backStack.removeAt(backStack.lastIndex)
                NavigateToSelectPlan -> backStack.add(RCRegisterNavigation.Plans)
            }
        }
    }
}

@Composable
fun SelectPlan(backStack: SnapshotStateList<RCRegisterNavigation>) {
    val viewModel: RCRegisterPlansOffersViewModel = koinViewModel()

    val state = viewModel.state.collectAsStateWithLifecycle().value

    RCRegisterPlansOffersScreen(state = state) { action ->
        viewModel.dispatchAction(action)
    }

    LaunchedEffect(Unit) {
        viewModel.dispatchAction(RCRegisterPlansOffersUiAction.OnInit)

        viewModel.event.collect { event ->
            when(event) {
                RCRegisterPlansOffersUiEvent.OnBackPressed -> backStack.removeAt(backStack.lastIndex)
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