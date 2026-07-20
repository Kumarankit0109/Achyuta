package com.example.achyuta

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognizerIntent
import com.example.achyuta.actions.AssistantActions
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.achyuta.tts.TextToSpeechManager
import com.example.achyuta.ui.screens.HomeScreen
import com.example.achyuta.ui.theme.AchyutaTheme
import com.example.achyuta.viewmodel.ChatViewModel
import java.util.Locale
import com.example.achyuta.model.AssistantState

class MainActivity : ComponentActivity() {

    private var askGroq: ((String) -> Unit)? = null

    private lateinit var ttsManager: TextToSpeechManager

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

                val spokenText = matches?.firstOrNull()
                android.util.Log.d("ACHYUTA", "Spoken Text = $spokenText")
                if (!spokenText.isNullOrBlank()) {

                    if (!AssistantActions.handleCommand(this, spokenText)) {
                        askGroq?.invoke(spokenText)
                    }

                } else {
                    android.util.Log.d(
                        "ACHYUTA",
                        "No speech detected"
                    )
                }

            } else {

                android.util.Log.d(
                    "ACHYUTA",
                    "Speech cancelled: ${result.resultCode}"
                )
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

        // Initialize Text-to-Speech
        ttsManager = TextToSpeechManager(this)
        window.decorView.postDelayed({
            ttsManager.speak("Hello, I am Achyuta.")
        }, 4000)
        setContent {

            AchyutaTheme {

                val chatViewModel: ChatViewModel = viewModel()

                // Speak every new AI response
                LaunchedEffect(chatViewModel.uiState.response) {

                    val response = chatViewModel.uiState.response

                    android.util.Log.d("ACHYUTA_TTS", "Response = $response")

                    if (response.isNotBlank()) {
                        ttsManager.speak(response)
                    }
                }

                askGroq = { prompt ->
                    chatViewModel.askGroq(prompt)
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        uiState = chatViewModel.uiState,
                        onMicClick = {

                            val intent = Intent(
                                RecognizerIntent.ACTION_RECOGNIZE_SPEECH
                            ).apply {

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

                                chatViewModel.setListening()

                                speechLauncher.launch(intent)

                            } else {

                                Toast.makeText(
                                    this,
                                    "Speech recognition not available",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ttsManager.shutdown()
    }
}