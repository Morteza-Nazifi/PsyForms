package ir.psyforms.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.psyforms.app.database.dao.DomainDao
import ir.psyforms.app.database.dao.OptionItemDao
import ir.psyforms.app.database.dao.OptionTemplateDao
import ir.psyforms.app.database.dao.QuestionDao
import ir.psyforms.app.database.dao.QuestionnaireDao
import ir.psyforms.app.database.dao.ScoringRuleDao
import ir.psyforms.app.database.dao.SessionDao
import ir.psyforms.app.database.dao.SubscaleDao
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
import ir.psyforms.app.database.entity.ScoringRuleEntity
import ir.psyforms.app.database.entity.SessionEntity
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
        ScoringRuleEntity::class,

        SessionEntity::class,

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

    abstract fun subscaleDao(): SubscaleDao

    abstract fun questionDao(): QuestionDao

    abstract fun optionTemplateDao(): OptionTemplateDao

    abstract fun optionItemDao(): OptionItemDao

    abstract fun scoringRuleDao(): ScoringRuleDao

    abstract fun sessionDao(): SessionDao
}
