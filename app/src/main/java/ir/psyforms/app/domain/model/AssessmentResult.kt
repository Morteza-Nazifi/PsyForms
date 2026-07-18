package ir.psyforms.app.domain.model

data class AssessmentResult(
    val questionnaireId: Long,
    val totalScore: Int,
    val completedAt: Long,
)
