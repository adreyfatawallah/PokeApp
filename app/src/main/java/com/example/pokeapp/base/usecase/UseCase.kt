package com.example.pokeapp.base.usecase

interface SuspendUseCase<out R, in P> {
    suspend operator fun invoke(param: P): R
}

interface UseCase<out R, in P> {
    operator fun invoke(param: P): R
}