package com.videolibrary.tubetagger.validation;

import com.videolibrary.tubetagger.annotation.ValidYouTubeUrl;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class YouTubeUrlValidator implements ConstraintValidator<ValidYouTubeUrl, String> {

    private static final Pattern YOUTUBE_URL_PATTERN = Pattern.compile(
            "^(https?://)?(www\\.)?(youtube\\.com|youtu\\.be)/.+$",
            Pattern.CASE_INSENSITIVE
    );

    @Override
    public boolean isValid(String url, ConstraintValidatorContext context) {
        if (url == null || url.trim().isEmpty()) {
            return false;
        }
        return YOUTUBE_URL_PATTERN.matcher(url).matches();
    }
}
