package ir.psyforms.app.ui.forms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.psyforms.app.domain.usecase.GetFormsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FormsViewModel(
    private val getFormsUseCase: GetFormsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FormsUiState(isLoading = true))
    val uiState: StateFlow<FormsUiState> = _uiState.asStateFlow()

    init {
        loadForms()
    }

    private fun loadForms() {
        viewModelScope.launch {
            try {
                val forms = getFormsUseCase()

                _uiState.value = FormsUiState(
                    isLoading = false,
                    forms = forms
                )
            } catch (e: Exception) {
                _uiState.value = FormsUiState(
                    isLoading = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }
}
