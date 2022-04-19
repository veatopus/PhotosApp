package kg.ruslan.core

typealias Mapper<T, R> = (T) -> R

fun <T, R> Mapper<T, R>.fromToList(input: List<T>?) =
    input?.map {
        invoke(it)
    }
