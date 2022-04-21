package kg.ruslan.core.models

data class CandidateDto(
    val user: CandidateUserDto
)

data class CandidateUserDto(
    val first_name: String,
    val photo: String,
    val second_name: String,
    val education: Int,
    val description: String,
    val company: List<Company>
)

data class Candidate(
    val name: String,
    val photoLink: String,
    val secondName: String,
    val education: Education,
    val description: String,
    val companies: List<Company>
)

fun CandidateDto.toCandidate() = Candidate(name = user.first_name, photoLink = user.photo, secondName = user.second_name, educationFromInt(user.education), user.description, user.company)