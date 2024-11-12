package com.quispe.ismael.poketinder2024_2_d

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel(
    val context: Context
) : ViewModel() {

    val inputsError = MutableLiveData<Boolean>()
    val emailFormatError = MutableLiveData<Boolean>()
    val passwordError = MutableLiveData<Boolean>()
    val passwordMismatchError = MutableLiveData<Boolean>()
    val registrationSuccess = MutableLiveData<Boolean>()

    private val sharedPreferencesRepository = SharedPreferencesRepository().apply {
        setSharedPreference(context)
    }

    fun registerUser(email: String, password: String, confirmPassword: String) {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            inputsError.postValue(true)
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailFormatError.postValue(true)
            return
        }


        if (password.length < 8) {
            passwordError.postValue(true)
            return
        }

        if (password != confirmPassword) {
            passwordMismatchError.postValue(true)
            return
        }

        sharedPreferencesRepository.saveUserEmail(email)
        sharedPreferencesRepository.saveUserPassword(password)
        registrationSuccess.postValue(true)
    }
}
