package ir.psyforms.app.data.repository

import ir.psyforms.app.domain.model.Form
import ir.psyforms.app.domain.repository.FormRepository

class FormRepositoryImpl : FormRepository {

    override suspend fun getForms(): List<Form> {
        return listOf(
            Form(
                id = 1,
                title = "نمونه فرم",
                description = "اولین فرم برنامه"
            )
        )
    }
}
