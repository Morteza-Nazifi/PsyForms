# PsyForms

# Software Architecture Design Document

Version: 3.0 Final

Status: Approved

---

# 1. معرفی

این سند معماری نرم‌افزار PsyForms را تعریف می‌کند.

هدف معماری:

- قابلیت توسعه بلندمدت
- جداسازی منطق کسب‌وکار از رابط کاربری
- نگهداری آسان
- امکان تولید دو نسخه Developer و User
- امکان توسعه فقط از طریق GitHub

---

# 2. فناوری‌های اصلی

## Platform

Android


## Language

Kotlin


## UI Framework

Jetpack Compose


## Architecture Pattern

Clean Architecture


## Database

Room Database


## Build System

Gradle + GitHub Actions


## Data Format

JSON Repository

Excel Export

---

# 3. ساختار کلی معماری

معماری شامل چهار لایه اصلی است:


```
Presentation Layer

        |

Domain Layer

        |

Data Layer

        |

Local Storage
```

---

# 4. Presentation Layer

مسئول:

- نمایش صفحات
- دریافت ورودی کاربر
- مدیریت State
- ارتباط با ViewModel


ساختار:

```
presentation/

    screens/

    components/

    navigation/

    theme/

    viewmodel/
```

---

# 5. صفحات اصلی برنامه


## Common Screens

بین Developer و User مشترک هستند:


```
SplashScreen

DomainListScreen

QuestionnaireListScreen

QuestionnaireIntroScreen

SessionScreen

ResultScreen

ExportScreen
```

---

# 6. Developer Screens


```
DeveloperLoginScreen

DomainEditorScreen

QuestionnaireEditorScreen

SubscaleEditorScreen

QuestionEditorScreen

ScoringSettingScreen

PublishScreen
```

---

# 7. User Screens


```
SubjectInfoScreen

SubscaleIntroductionScreen

QuestionAnswerScreen

SessionReviewScreen

SubmitScreen
```

---

# 8. Domain Layer

هسته اصلی برنامه است.

این لایه:

- مستقل از Android
- مستقل از Database
- مستقل از UI

است.


ساختار:


```
domain/

    model/

    repository/

    usecase/
```

---

# 9. Domain Models


مدل‌های اصلی:


```
Domain

Questionnaire

Subscale

Question

AnswerOption

Session

Response

Score
```

---

# 10. Use Case ها


## Developer Use Cases


```
CreateDomain

UpdateDomain

DeleteDomain

CreateQuestionnaire

CreateSubscale

CreateQuestion

ConfigureScoring

PublishRepository
```


---

## User Use Cases


```
StartSession

SaveDemographicData

AnswerQuestion

SkipSubscale

ValidateSession

CalculateScore

ExportExcel
```

---

# 11. Data Layer

مسئول:

- دریافت داده
- ذخیره داده
- تبدیل Entity و Model


ساختار:


```
data/

    local/

    repository/

    mapper/

    exporter/
```

---

# 12. Local Database


Room شامل:


```
database/

    PsyFormsDatabase


entity/

dao/

migration/
```

---

# 13. Repository Pattern


ارتباط بین Domain و Database:


```
ViewModel

    |

UseCase

    |

Repository Interface

    |

Repository Implementation

    |

Room DAO
```

---

# 14. Dependency Injection


برای مدیریت وابستگی‌ها استفاده می‌شود.


ساختار:


```
di/

    DatabaseModule

    RepositoryModule

    AppModule
```


---

# 15. Navigation Architecture


Navigation Compose استفاده می‌شود.


مسیر کلی:


```
Splash

 |

Domain List

 |

Questionnaire List

 |

Questionnaire Intro

 |

Subscale Intro

 |

Question Answer

 |

Submit

 |

Result
```

---

# 16. مدیریت نسخه Developer و User


دو Flavor ایجاد می‌شود:


```
developer

user
```


ساختار:


```
app/

    src/

        main/

        developer/

        user/
```


---

# 17. تفاوت Flavor ها


## Developer


فعال:

- Login
- Builder
- Editor
- Publish


---

## User


غیرفعال:

- Builder
- Editor
- Publish


فعال:

- Answer
- Save
- Export

---

# 18. Repository JSON Architecture


فایل:


```
psyforms_repository.json
```


ساختار:


```
Domain

 |

Questionnaire

 |

Subscale

 |

Question

 |

Option

 |

ScoringRule
```


---

# 19. فرآیند انتشار پرسشنامه


Developer App:


```
Create Questionnaire

        |

Generate JSON

        |

Upload to GitHub Repository

        |

GitHub Actions Build

        |

New APK Generated
```

---

# 20. مدیریت پاسخ‌ها


پاسخ‌ها:

- فقط داخل Database دستگاه ذخیره می‌شوند.
- هیچ‌گاه وارد Repository نمی‌شوند.
- داخل JSON قرار نمی‌گیرند.


---

# 21. Excel Export Architecture


ساختار:


```
Exporter

 |

Session Data

 |

Score Calculator

 |

Excel Generator

 |

User Selected Location
```


---

# 22. Score Engine


مسئول:

- محاسبه نمره سوال
- اعمال Reverse Score
- محاسبه نمره خرده‌مقیاس
- محاسبه نمره کل


ساختار:


```
score/

    ScoreCalculator

    ReverseScoreProcessor

    TotalScoreProcessor
```

---

# 23. Validation Engine


مسئول:

- بررسی سوالات بدون پاسخ
- بررسی خرده‌مقیاس‌های انتخاب‌شده
- جلوگیری از ثبت ناقص


ساختار:


```
validation/

    SessionValidator
```

---

# 24. GitHub Actions Architecture


با هر Commit:


```
GitHub Push

        |

Actions Workflow

        |

Gradle Build

        |

Generate APK

        |

Artifact Upload
```


---

# 25. Build Outputs


خروجی‌ها:


```
PsyForms-Developer.apk

PsyForms-User.apk
```

---

# 26. امنیت Developer


اطلاعات ورود:

- فقط برای فعال‌سازی نسخه Developer
- ذخیره امن
- عدم نمایش در نسخه User


---

# 27. اصول توسعه


هر تغییر باید:

- یک Commit مشخص داشته باشد.
- Build موفق داشته باشد.
- قابلیت بازگشت داشته باشد.


---

# 28. ساختار نهایی Repository


```
PsyForms

├── app

├── docs

├── .github

│    └── workflows

├── psyforms_repository.json

├── LICENSE

├── README.md

├── build.gradle.kts

├── settings.gradle.kts

└── gradle
```

---

# 29. رعایت License


پروژه پایه تحت Apache License 2.0 است.

در توسعه PsyForms:

- LICENSE حفظ می‌شود.
- NOTICE حفظ می‌شود (در صورت وجود).
- حقوق کدهای جدید متعلق به توسعه‌دهنده PsyForms خواهد بود.
- تغییرات مهم نسبت به پروژه پایه قابل تشخیص خواهند بود.

---

# 30. نتیجه نهایی معماری


این معماری امکان موارد زیر را فراهم می‌کند:

- توسعه تدریجی
- تولید دو نسخه برنامه
- ساخت پرسشنامه بدون برنامه‌نویسی
- ذخیره امن داده‌ها
- خروجی استاندارد Excel
- انتشار آسان نسخه‌های جدید
- توسعه کامل از طریق GitHub

---

# پایان سند
