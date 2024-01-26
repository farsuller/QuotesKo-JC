package com.quotesapp.quotesko.model

data class Verse(
    val verse: VerseDetail? = null,
)

data class VerseDetail(
    val details: Details,
    val notice: String,
)

data class Details(
    val reference: String,
    val text: String,
    val verseurl: String,
    val version: String,
)
