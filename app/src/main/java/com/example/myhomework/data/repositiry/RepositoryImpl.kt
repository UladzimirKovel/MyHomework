package com.example.myhomework.data.repositiry

import com.example.myhomework.data.api.NetworkApi
import com.example.myhomework.domain.repository.Repository

class RepositoryImpl(
    private val networkApi: NetworkApi
) : Repository {

    override fun save() {
        networkApi.save()
    }
}