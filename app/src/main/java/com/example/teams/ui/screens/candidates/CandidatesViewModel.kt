package com.example.teams.ui.screens.candidates

import androidx.lifecycle.*
import com.example.teams.data.model.entities.Candidate
import com.example.teams.data.repository.TeamsRepository
import kotlinx.coroutines.launch


class CandidatesViewModel(private val repository: TeamsRepository) : ViewModel() {
    val allCandidates: LiveData<List<Candidate>> = repository.allCandidates.asLiveData()

   fun insert(candidate: Candidate)  = viewModelScope.launch {
       repository.insert(candidate)
   }
}

data class CandidateScreenState(
    val candidateList: List<Candidate>
)

@Suppress("UNCHECKED_CAST")
class CandidatesViewModelFactory(private val repository: TeamsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CandidatesViewModel::class.java)) {
            return CandidatesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}