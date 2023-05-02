package com.svyd.domain.common.mapper

interface TypeModifier<I, in M> {
    fun modify(input: I, modifier: M) : I
}
