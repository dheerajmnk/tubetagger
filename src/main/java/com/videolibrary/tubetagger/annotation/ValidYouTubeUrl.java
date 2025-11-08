package com.videolibrary.tubetagger.annotation;

import com.videolibrary.tubetagger.validation.YouTubeUrlValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = YouTubeUrlValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidYouTubeUrl {
    String message() default "Please enter a valid YouTube URL";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
