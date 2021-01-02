package com.fooqoo56.kyogofinder.api.presentation.validation;

import com.fooqoo56.kyogofinder.api.application.service.UserService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisteredIdValidator implements ConstraintValidator<RegisterdId, String> {

    final UserService userService;

    /**
     * バリデーション
     *
     * @param value   ID
     * @param context ConstraintValidatorContext
     * @return バリデーション結果
     */
    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return userService.isExistUser(value);
    }
}
