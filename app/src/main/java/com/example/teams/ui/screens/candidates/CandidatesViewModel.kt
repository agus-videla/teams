package com.example.teams.ui.screens.candidates

import androidx.lifecycle.*
import com.example.teams.data.model.entities.Candidate
import com.example.teams.data.repository.TeamsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch


class CandidatesViewModel(private val repository: TeamsRepository) : ViewModel() {
    private val _state = MutableStateFlow(CandidateScreenState())
    val state :StateFlow<CandidateScreenState> get() = _state

    val candidateList = repository.allCandidates

    init {
        viewModelScope.launch {
            candidateList.collect {
                _state.value = CandidateScreenState(it)
            }
        }
    }

    fun insert(candidate: Candidate)  = viewModelScope.launch {
       repository.insert(candidate)
    }
}

data class CandidateScreenState(
    val candidateList: List<Candidate> = emptyList()
)

@Suppress("UNCHECKED_CAST")
class CandidatesViewModelFactory(private val repository: TeamsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(TeamsRepository::class.java).newInstance(repository)
    }
}