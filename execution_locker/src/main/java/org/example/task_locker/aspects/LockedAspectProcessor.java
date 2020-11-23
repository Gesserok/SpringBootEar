package org.example.task_locker.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.example.task_locker.annotations.ExecutionLocking;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LockedAspectProcessor {

    @Pointcut(value = "@annotation(executionLocking)", argNames = "joinPoint,executionLocking")
    private void annotatedMethods(JoinPoint joinPoint, ExecutionLocking executionLocking) {

    }

    @Pointcut(value = "execution(public * * (..)) && @within(executionLocking)", argNames = "joinPoint,executionLocking")
    private void methodOfAnnotatedClass(JoinPoint joinPoint, ExecutionLocking executionLocking) {

    }

    @Pointcut(value = "annotatedMethods(joinPoint,executionLocking) || methodOfAnnotatedClass(joinPoint,executionLocking)", argNames = "joinPoint,executionLocking")
    private void annotatedMethodOrClass(JoinPoint joinPoint, ExecutionLocking executionLocking) {

    }

    @Before(value = "annotatedMethodOrClass(joinPoint,executionLocking)", argNames = "joinPoint,executionLocking")
    public void beforeAdvice(JoinPoint joinPoint, ExecutionLocking executionLocking) {
        Method[] declaredMethods = joinPoint.getTarget().getClass().getDeclaredMethods();

        long l1 = executionLocking.minTime();
        long l2 = executionLocking.maxTime();

    }

    @AfterReturning(value = "annotatedMethodOrClass(joinPoint,executionLocking)", argNames = "joinPoint,executionLocking")
    public void afterReturningAdvice(JoinPoint joinPoint, ExecutionLocking executionLocking) {

    }

    @AfterThrowing(value = "annotatedMethodOrClass(joinPoint,executionLocking)", argNames = "joinPoint,executionLocking")
    public void afterThrowingAdvice(JoinPoint joinPoint, ExecutionLocking executionLocking) {

    }

}
