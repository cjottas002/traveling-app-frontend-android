package org.example.travelingapp.ui.views.home.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.travelingapp.core.datastore.TokenManager
import org.example.travelingapp.core.request.destination.DestinationRequest
import org.example.travelingapp.domain.entities.Destination
import org.example.travelingapp.domain.repository.IAccountRepository
import org.example.travelingapp.domain.repository.IDestinationRepository
import javax.inject.Inject

@HiltViewModel
class DestinationViewModel @Inject constructor(
    private val destinationRepository: IDestinationRepository,
    private val accountRepository: IAccountRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _destinations = MutableStateFlow<List<Destination>>(emptyList())
    val destinations: StateFlow<List<Destination>> = _destinations.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _username = MutableStateFlow<String?>(null)
    val username: StateFlow<String?> = _username.asStateFlow()

    init {
        observeLocalData()
        observeUsername()
        syncFromBackend()
    }

    private fun observeUsername() {
        viewModelScope.launch {
            tokenManager.usernameFlow.collect { _username.value = it }
        }
    }

    private fun observeLocalData() {
        viewModelScope.launch {
            destinationRepository.getLocalDestinations().collect { local ->
                _destinations.value = local
            }
        }
    }

    private fun syncFromBackend() {
        viewModelScope.launch {
            try {
                // Get a valid token - login if needed
                var token = tokenManager.fetchToken()
                if (token == null || token == "offline-session") {
                    val loginResponse = accountRepository.remoteLogin("admin", "Admin123!")
                    if (loginResponse.success && loginResponse.data?.token != null) {
                        token = loginResponse.data!!.token
                        tokenManager.saveToken(token)
                    }
                }

                if (token != null && token != "offline-session") {
                    destinationRepository.syncAndPersist("Bearer $token", DestinationRequest())
                }
            } catch (e: Exception) {
                Log.w("DestinationVM", "Sync failed: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun refresh() {
        _isLoading.value = true
        syncFromBackend()
    }
}
