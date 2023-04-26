package com.svyd.domain.repository

data class Directory(val title: String, val elements: List<Element>)

data class Element(val text: String, val children: List<Element>)
