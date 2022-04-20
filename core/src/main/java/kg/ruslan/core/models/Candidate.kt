package kg.ruslan.core.models

data class Candidate(
    val name: String,
    val photoLink: String,
    val secondName: String,
    val education: Education,
    val description: String,
    val companies: List<Company>
)