package br.com.alura.aluraesporte.repository

import android.content.SharedPreferences
import androidx.core.content.edit

private const val CHAVE_LOGADO = "CHAVE_LOGADO"

class LoginRepository(private  val preferences: SharedPreferences) {

    fun salavarLogin() = salavarLogin(true)

    fun deslogar() = salavarLogin(false)

    fun isLogado(): Boolean = preferences.getBoolean(CHAVE_LOGADO, false)

    private fun salavarLogin(logar: Boolean){
        preferences.edit {
            putBoolean(CHAVE_LOGADO, logar)
        }
    }
}