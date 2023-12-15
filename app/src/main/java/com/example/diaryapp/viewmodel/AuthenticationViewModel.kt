package com.example.diaryapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryapp.BuildConfig
import io.realm.kotlin.Configuration
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.GoogleAuthType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthenticationViewModel : ViewModel() {
    var authenticated = mutableStateOf(false)
        private set
    var loadingState = mutableStateOf(false)
        private set

    fun setLoadingState(loading: Boolean) {
        loadingState.value = loading
    }

    fun signInWithMongoAtlas(
        tokenId: String,
        onSuccess: (Boolean) -> Unit,
        onError: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    App.create(BuildConfig.MONGO_DB_API_KEY)
                        .login(
                            Credentials.jwt(tokenId)
//                            Credentials.google(tokenId, GoogleAuthType.ID_TOKEN)
                        )
                }.loggedIn
                withContext(Dispatchers.Main) {
                    onSuccess.invoke(result)
                    delay(800L)
                    authenticated.value = true
                }
            } catch (exception: Exception) {
                withContext(Dispatchers.Main) {
                    onError.invoke(exception)
                }
            }
        }
    }
}