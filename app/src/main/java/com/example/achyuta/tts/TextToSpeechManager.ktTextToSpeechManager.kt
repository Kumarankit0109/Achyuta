package com.example.achyuta.tts

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import java.util.Locale

class TextToSpeechManager(
    context: Context,
    private val onStart: () -> Unit = {},
    private val onDone: () -> Unit = {}
) : TextToSpeech.OnInitListener {

    companion object {
        private const val TAG = "ACHYUTA_TTS"
    }

    private var ready = false

    private val tts = TextToSpeech(context, this)

    override fun onInit(status: Int) {

        Log.d(TAG, "onInit() status = $status")

        if (status == TextToSpeech.SUCCESS) {

            val result = tts.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED
            ) {

                Log.e(TAG, "Language not supported")

                ready = false
                return
            }

            ready = true

            tts.setSpeechRate(1.0f)

            tts.setPitch(1.0f)

            Log.d(TAG, "TTS Ready")

            tts.setOnUtteranceProgressListener(
                object : UtteranceProgressListener() {

                    override fun onStart(utteranceId: String?) {
                        Log.d(TAG, "Speaking started")
                        onStart()
                    }

                    override fun onDone(utteranceId: String?) {
                        Log.d(TAG, "Speaking finished")
                        onDone()
                    }

                    override fun onError(utteranceId: String?) {
                        Log.e(TAG, "Speaking error")
                    }
                }
            )

        } else {

            Log.e(TAG, "TTS Initialization Failed")
        }
    }

    fun speak(text: String) {

        Log.d(TAG, "speak() called")
        Log.d(TAG, "Ready = $ready")
        Log.d(TAG, "Text = $text")

        if (!ready) {
            Log.e(TAG, "TTS not ready")
            return
        }

        val result = tts.speak(
            text,
            TextToSpeech.QUEUE_FLUSH,
            null,
            "ACHYUTA"
        )

        Log.d(TAG, "Speak result = $result")
    }

    fun shutdown() {
        tts.stop()
        tts.shutdown()
    }
}