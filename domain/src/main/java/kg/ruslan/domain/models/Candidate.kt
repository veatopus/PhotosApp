package kg.ruslan.domain.models

data class Candidate(
    val name: String,
    val photoLink: String,
    val secondName: String,
    val education: Education,
    val companies: List<Company>
)