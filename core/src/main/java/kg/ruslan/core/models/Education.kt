package kg.ruslan.core.models

import java.lang.RuntimeException

enum class Education {
    HIGH_SCHOOL,
    BACHELOR,
    MASTER,
    DOCTORAL
}

/**
 * This method allows you to convert int value to the [Education]
 *
 * @param value is some int from 1 to 4.
 * value of it is:
 * 1 is [Education.HIGH_SCHOOL]
 * 2 is [Education.BACHELOR]
 * 3 is [Education.MASTER]
 * 4 is [Education.DOCTORAL]
 *
 * @return [Education] is some Education from int
 *
 * @throws RuntimeException when incoming int value is not from expected rage [1..4]
 */
fun Education.fromInt(value: Int): Education {
    return when(value) {
        1 -> Education.HIGH_SCHOOL
        2 -> Education.BACHELOR
        3 -> Education.MASTER
        4 -> Education.DOCTORAL
        else -> throw RuntimeException("Unidentified int value. Value must be from 1..4")
    }
}

fun Education.toNormalString(): String = when(this) {
    Education.HIGH_SCHOOL -> "High School"
    Education.BACHELOR -> "Bachelor"
    Education.MASTER -> "Master"
    Education.DOCTORAL -> "Doctoral"
}