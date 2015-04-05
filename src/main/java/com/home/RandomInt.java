package com.home;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Igor on 03.04.15.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RandomInt {
    int min();
    int max();
}
