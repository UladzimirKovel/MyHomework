package com.example.myhomework.domain.use_case

import com.example.myhomework.domain.repository.Repository

class SaveUseCase(private val repository: Repository) {

    fun invokeSave() {
        repository.save()
    }

}