package it.redhat.demo.controller;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Stereotype;
import javax.inject.Named;

import it.redhat.demo.interceptor.Logged;

@ConversationScoped
@Named
@Logged
@Stereotype
@Retention(RUNTIME)
@Target(TYPE)
public @interface Controller {

}
