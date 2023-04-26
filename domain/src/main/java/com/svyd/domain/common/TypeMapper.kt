package com.svyd.domain.common

interface TypeMapper<in I, out O> {
    fun map(input: I) : O
}
