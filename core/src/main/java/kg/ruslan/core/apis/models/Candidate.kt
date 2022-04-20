package kg.ruslan.core.apis.models

data class Candidate(
    val name: String,
    val photoLink: String,
    val secondName: String,
    val education: Education,
    val companies: List<Company>
)