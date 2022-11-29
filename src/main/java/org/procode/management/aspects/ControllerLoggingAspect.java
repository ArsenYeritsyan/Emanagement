package org.procode.management.aspects;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Will log every invokation of @RequestMapping annotated methods
 * in @Controller annotated beans.
 */
@Slf4j
@Aspect
public class ControllerLoggingAspect {

    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void controller() {}

    @Pointcut("execution(* *(..))")
    public void methodPointcut() {}

    @Pointcut("within(@org.springframework.web.bind.annotation.RequestMapping *)")
    public void requestMapping() {}

    @Before("controller() && methodPointcut() && requestMapping()")
    public void aroundControllerMethod(JoinPoint joinPoint) {
        log.debug("Invoked: " + niceName(joinPoint));
    }

    @AfterReturning("controller() && methodPointcut() && requestMapping()")
    public void afterControllerMethod(JoinPoint joinPoint) {
        log.debug("Finished: " + niceName(joinPoint));
    }

    private String niceName(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass()
                + "#" + joinPoint.getSignature().getName()
                + "\n\targs:" + Arrays.toString(joinPoint.getArgs());
    }

}
