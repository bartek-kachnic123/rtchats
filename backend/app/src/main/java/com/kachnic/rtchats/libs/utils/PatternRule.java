package com.kachnic.rtchats.libs.utils;

import java.util.regex.Pattern;

public interface PatternRule {
    Pattern getPattern();

    String getPatternText();
}
