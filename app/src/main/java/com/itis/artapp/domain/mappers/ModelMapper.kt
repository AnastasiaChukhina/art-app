package com.itis.artapp.domain.mappers

interface ModelMapper<S, D> {
    fun map(source: S): D
}
