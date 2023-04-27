package com.svyd.domain.common.mapper

interface TypeMapper<in I, out O> {
    fun map(input: I) : O
}
