package com.myapi.infrastructure.validation.defaultRules;

import com.myapi.infrastructure.validation.BusinessRule;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public abstract class DateCompareDefaultRule implements BusinessRule {
    private final LocalDate date;
    private final LocalDate dateToCompare;

    protected boolean isNull() {
        return date == null;
    }

    protected boolean isEqual() {
        return date.isEqual(dateToCompare);
    }

    protected boolean isAfter() {
        return date.isAfter(dateToCompare);
    }

    protected boolean isBefore() {
        return date.isBefore(dateToCompare);
    }
}
