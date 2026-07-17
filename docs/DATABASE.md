# PsyForms

# Database Design Document

Version: 3.0 Final

Status: Approved

---

# 1. مقدمه

این سند ساختار پایگاه داده داخلی نرم‌افزار PsyForms را تعریف می‌کند.

پایگاه داده وظیفه نگهداری موارد زیر را دارد:

- ساختار پرسشنامه‌ها
- اطلاعات حیطه‌ها
- خرده‌مقیاس‌ها
- سوالات
- گزینه‌های پاسخ
- تنظیمات نمره‌گذاری
- جلسات پاسخ‌دهی
- پاسخ کاربران
- نتایج محاسبه‌شده

---

# 2. نوع پایگاه داده

پایگاه داده محلی برنامه:

SQLite

با استفاده از:

Room Database

پیاده‌سازی خواهد شد.

---

# 3. اصول طراحی

اصول اصلی:

- جداسازی ساختار پرسشنامه از داده پاسخ‌ها
- پشتیبانی از تغییر ساختار پرسشنامه
- حفظ تاریخچه پاسخ‌ها
- امکان خروجی Excel
- امکان Export ساختار پرسشنامه به JSON

---

# 4. موجودیت‌ها

ساختار اصلی:

```
Domain

  |

Questionnaire

  |

Subscale

  |

Question

  |

AnswerOption
```

داده پاسخ:

```
SubjectSession

 |

SessionQuestionnaire

 |

QuestionResponse
```

---

# 5. جدول Domain

## DomainEntity

ذخیره حیطه‌ها


| Field | Type | Description |
|-|-|-|
| id | Long | شناسه |
| name | String | نام حیطه |
| orderIndex | Int | ترتیب نمایش |
| createdAt | Long | تاریخ ایجاد |
| updatedAt | Long | تاریخ ویرایش |


نمونه:

```
آسیب‌شناسی روانی کودک
رفتاردرمانی
ذهنی‌سازی
```

---

# 6. جدول Questionnaire

## QuestionnaireEntity


| Field | Type | Description |
|-|-|-|
| id | Long | شناسه |
| domainId | Long | ارتباط با حیطه |
| title | String | نام پرسشنامه |
| instruction | String | دستورالعمل |
| hasTotalScore | Boolean | دارای نمره کل |
| isActive | Boolean | فعال بودن |
| orderIndex | Int | ترتیب |
| createdAt | Long | تاریخ ایجاد |
| updatedAt | Long | تاریخ تغییر |


---

# 7. جدول Subscale

## SubscaleEntity


| Field | Type | Description |
|-|-|-|
| id | Long | شناسه |
| questionnaireId | Long | پرسشنامه |
| name | String | نام خرده‌مقیاس |
| description | String | توضیح خرده‌مقیاس |
| orderIndex | Int | ترتیب |
| createdAt | Long | تاریخ ایجاد |


---

# 8. جدول Question

## QuestionEntity


| Field | Type | Description |
|-|-|-|
| id | Long | شناسه |
| subscaleId | Long | خرده‌مقیاس |
| text | String | متن سوال |
| type | Enum | نوع سوال |
| orderIndex | Int | شماره سوال |
| scoreEnabled | Boolean | دارای نمره |
| reverseScore | Boolean | معکوس بودن |
| createdAt | Long | تاریخ ایجاد |


مقادیر type:

```
TEXT

NUMBER

MULTIPLE_CHOICE
```

---

# 9. جدول OptionTemplate

برای جلوگیری از ورود مجدد قالب‌های چندگزینه‌ای استفاده می‌شود.


## OptionTemplateEntity


| Field | Type |
|-|-|
| id | Long |
| optionCount | Int |
| createdAt | Long |


نکته:

نام قالب ذخیره نمی‌شود.

---

# 10. جدول AnswerOption


## AnswerOptionEntity


| Field | Type | Description |
|-|-|-|
| id | Long | شناسه |
| questionId | Long | سوال |
| templateId | Long | قالب |
| text | String | متن گزینه |
| score | Float | امتیاز |


مثال:

```
هرگز      0

گاهی      1

اغلب      2

همیشه     3
```

---

# 11. جدول ReverseScoreRule


## ReverseScoreRuleEntity


| Field | Type |
|-|-|
| id | Long |
| subscaleId | Long |
| questionId | Long |


این جدول مشخص می‌کند کدام سوال در یک خرده‌مقیاس نمره‌گذاری معکوس دارد.

---

# 12. جدول Session

هر بار شروع پاسخ‌دهی یک Session ساخته می‌شود.


## SessionEntity


| Field | Type |
|-|-|
| id | Long |
| startedAt | Long |
| completedAt | Long |
| status | Enum |


Status:


```
IN_PROGRESS

COMPLETED
```

---

# 13. جدول DemographicData

اطلاعات جمعیت‌شناختی آزمودنی


## DemographicEntity


| Field | Type |
|-|-|
| id | Long |
| sessionId | Long |
| gender | String |
| age | Int |
| educationLevel | String |
| fatherEducation | String |
| motherEducation | String |


مقادیر تحصیلات:


```
بی‌سواد

سیکل

دیپلم

فوق دیپلم

لیسانس

فوق لیسانس

دکترا
```

---

# 14. جدول SessionQuestionnaire


یک Session می‌تواند چند پرسشنامه داشته باشد.


## SessionQuestionnaireEntity


| Field | Type |
|-|-|
| id | Long |
| sessionId | Long |
| questionnaireId | Long |
| startedAt | Long |
| completedAt | Long |


---

# 15. جدول SubscaleParticipation


برای ثبت وضعیت پاسخ‌دهی خرده‌مقیاس.


## SubscaleParticipationEntity


| Field | Type |
|-|-|
| id | Long |
| sessionQuestionnaireId | Long |
| subscaleId | Long |
| status | Enum |


Status:


```
ANSWERED

SKIPPED

INCOMPLETE
```

---

# 16. منطق Skip

اگر کاربر:

"برو به خرده‌مقیاس بعدی"

را انتخاب کند:


در جدول:

SubscaleParticipation


ثبت می‌شود:


```
status = SKIPPED
```


در این حالت:

- ثبت نهایی Session مجاز است.
- سوالات آن خرده‌مقیاس بررسی نمی‌شوند.
- نمره خرده‌مقیاس NULL خواهد بود.

---

# 17. جدول QuestionResponse


ذخیره پاسخ هر سوال


## QuestionResponseEntity


| Field | Type |
|-|-|
| id | Long |
| sessionQuestionnaireId | Long |
| questionId | Long |
| textAnswer | String |
| numericAnswer | Float |
| selectedOptionId | Long |
| score | Float |


---

# 18. جدول ScoreResult


نتایج محاسبه‌شده


## ScoreResultEntity


| Field | Type |
|-|-|
| id | Long |
| sessionQuestionnaireId | Long |
| subscaleId | Long |
| score | Float |


برای نمره خرده‌مقیاس استفاده می‌شود.

---

# 19. جدول QuestionnaireTotalScore


## TotalScoreEntity


| Field | Type |
|-|-|
| id | Long |
| sessionQuestionnaireId | Long |
| totalScore | Float |


فقط برای پرسشنامه‌هایی که:

hasTotalScore = true

استفاده می‌شود.

---

# 20. روابط اصلی


```
Domain

1 ---- N

Questionnaire


Questionnaire

1 ---- N

Subscale


Subscale

1 ---- N

Question


Question

1 ---- N

AnswerOption


Session

1 ---- N

SessionQuestionnaire


SessionQuestionnaire

1 ---- N

QuestionResponse
```

---

# 21. JSON Repository

فقط ساختار پرسشنامه Export می‌شود:


شامل:

- Domain
- Questionnaire
- Subscale
- Question
- Option
- Scoring Rules


شامل نمی‌شود:

- اطلاعات آزمودنی
- پاسخ‌ها
- Session

---

# 22. حذف داده‌ها

حذف ساختار پرسشنامه نباید باعث حذف پاسخ‌های قبلی شود.

بنابراین:

- داده‌های پاسخ مستقل نگهداری می‌شوند.
- حذف پرسشنامه فقط روی ساختار آینده اثر دارد.

---

# 23. مهاجرت Database

هر تغییر ساختاری باید با:

Room Migration

انجام شود.

---

# پایان سند
