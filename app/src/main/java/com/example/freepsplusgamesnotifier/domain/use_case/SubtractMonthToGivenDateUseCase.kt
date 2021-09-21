package com.example.freepsplusgamesnotifier.domain.use_case

import java.util.*
import javax.inject.Inject

class SubtractMonthToGivenDateUseCase
@Inject
constructor() {

    operator fun invoke(date: Calendar): Calendar {
        date.add(Calendar.MONTH, -1)
        return date
    }

}