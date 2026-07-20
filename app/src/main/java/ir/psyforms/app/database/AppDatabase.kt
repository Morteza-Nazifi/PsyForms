package ir.psyforms.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.psyforms.app.database.dao.DomainDao
import ir.psyforms.app.database.dao.QuestionnaireDao
import ir.psyforms.app.database.entity.AnswerEntity
import ir.psyforms.app.database.entity.AssessmentSessionEntity
import ir.psyforms.app.database.entity.DomainEntity
import ir.psyforms.app.database.entity.NumericAnswerEntity
import ir.psyforms.app.database.entity.OptionAnswerEntity
import ir.psyforms.app.database.entity.OptionItemEntity
import ir.psyforms.app.database.entity.OptionTemplateEntity
import ir.psyforms.app.database.entity.QuestionEntity
import ir.psyforms.app.database.entity.QuestionnaireEntity
import ir.psyforms.app.database.entity.QuestionnaireSessionEntity
import ir.psyforms.app.database.entity.SubscaleEntity
import ir.psyforms.app.database.entity.TextAnswerEntity

@Database(
    entities = [
        DomainEntity::class,
        QuestionnaireEntity::class,
        SubscaleEntity::class,
        QuestionEntity::class,
        OptionTemplateEntity::class,
        OptionItemEntity::class,
        AssessmentSessionEntity::class,
        QuestionnaireSessionEntity::class,
        AnswerEntity::class,
        OptionAnswerEntity::class,
        NumericAnswerEntity::class,
        TextAnswerEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun domainDao(): DomainDao

    abstract fun questionnaireDao(): QuestionnaireDao
}
