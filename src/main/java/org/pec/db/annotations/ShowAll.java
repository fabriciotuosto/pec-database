package org.pec.db.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;

/**
 * Used by Guice configuration in order to tell Guice that any method that
 * contains this annotation must be intercept calls with {@link ShowAll}
 * .
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.PARAMETER })
@BindingAnnotation
public @interface ShowAll {

}
