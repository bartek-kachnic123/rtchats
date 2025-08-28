package com.kachnic.gradle.format

import com.diffplug.gradle.spotless.FormatExtension

class SpotlessConfig {

    static void applyCommonMiscFormatting(FormatExtension format) {
        format.trimTrailingWhitespace()
        format.leadingSpacesToTabs()
        format.endWithNewline()
    }
}

