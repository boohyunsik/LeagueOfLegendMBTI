package model

data class RiotData<out T> (
    val type: String,
    val format: String,
    val version: String,
    val data: T
)