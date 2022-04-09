package com.example.profile.service

import org.koin.dsl.module

val serviceModule = module {
    factory { RetrofitHelper }
}