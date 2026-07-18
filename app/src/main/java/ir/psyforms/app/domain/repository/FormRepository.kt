package ir.psyforms.app.domain.repository

import ir.psyforms.app.domain.model.Form

interface FormRepository {

    suspend fun getForms(): List<Form>

}
