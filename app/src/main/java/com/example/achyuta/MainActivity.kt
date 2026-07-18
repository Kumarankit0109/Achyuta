package com.example.achyuta

import android.Manifest
import android.app.Activity
import android.content.Intent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.achyuta.viewmodel.ChatViewModel
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.example.achyuta.ui.screens.HomeScreen
import com.example.achyuta.ui.theme.AchyutaTheme
import java.util.Locale

class MainActivity : ComponentActivity() {

    private var recognizedText by mutableStateOf("")
    private var askGroq: ((String) -> Unit)? = null
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { }

    private val speechLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            android.util.Log.d("ACHYUTA", "Result code = ${result.resultCode}")
            android.util.Log.d("ACHYUTA", "Intent = ${result.data}")

            if (result.resultCode == Activity.RESULT_OK) {

                val matches = result.data?.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS
                )

                android.util.Log.d("ACHYUTA", "Matches = $matches")

                recognizedText = matches?.firstOrNull() ?: "No speech detected"
                if (recognizedText != "No speech detected") {
                    askGroq?.invoke(recognizedText)
                }
            } else {
                recognizedText = "Cancelled: ${result.resultCode}"
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(
                Manifest.permission.RECORD_AUDIO
            )
        }

        enableEdgeToEdge()

        setContent {

            AchyutaTheme {
                val chatViewModel: ChatViewModel = viewModel()
                askGroq = { prompt ->
                    chatViewModel.askGroq(prompt)
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        recognizedText = recognizedText,
                        aiResponse = chatViewModel.response,
                        onMicClick = {
                            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                                putExtra(
                                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                                )

                                putExtra(
                                    RecognizerIntent.EXTRA_LANGUAGE,
                                    Locale.ENGLISH
                                )

                                putExtra(
                                    RecognizerIntent.EXTRA_MAX_RESULTS,
                                    1
                                )

                                putExtra(
                                    RecognizerIntent.EXTRA_PROMPT,
                                    "Speak to Achyuta"
                                )
                            }

                            if (intent.resolveActivity(packageManager) != null) {
                                speechLauncher.launch(intent)
                            } else {
                                recognizedText = "Speech recognition not available"
                            }
                        }
                    )
                }
            }
        }
    }
}