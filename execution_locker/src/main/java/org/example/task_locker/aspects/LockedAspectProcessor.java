package org.example.task_locker.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.example.task_locker.annotations.ExecutionLocking;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LockedAspectProcessor {

    @Pointcut(value = "@annotation(executionLocking)", argNames = "executionLocking")
    private void annotatedMethods(ExecutionLocking executionLocking) {

    }

    @Pointcut(value = "execution(public * * (..)) && @within(executionLocking)", argNames = "executionLocking")
    private void methodOfAnnotatedClass(ExecutionLocking executionLocking) {

    }

    @Pointcut(value = "annotatedMethods(executionLocking) || methodOfAnnotatedClass(executionLocking)", argNames = "executionLocking")
//    @Pointcut(value = "methodOfAnnotatedClass(executionLocking)", argNames = "executionLocking")
//    @Pointcut(value = "annotatedMethods(executionLocking)", argNames = "executionLocking")
    private void annotatedMethodOrClass(ExecutionLocking executionLocking) {

    }

    @Before(value = "annotatedMethodOrClass(executionLocking)", argNames = "executionLocking")
    public void beforeAdvice(ExecutionLocking executionLocking) {
//        Method[] declaredMethods = joinPoint.getTarget().getClass().getDeclaredMethods();

        System.out.println("HEllO");

        long l1 = executionLocking.minTime();
        long l2 = executionLocking.maxTime();

    }

//    @AfterReturning(value = "annotatedMethodOrClass(joinPoint,executionLocking)", argNames = "joinPoint,executionLocking")
//    public void afterReturningAdvice(JoinPoint joinPoint, ExecutionLocking executionLocking) {
//
//    }
//
//    @AfterThrowing(value = "annotatedMethodOrClass(joinPoint,executionLocking)", argNames = "joinPoint,executionLocking")
//    public void afterThrowingAdvice(JoinPoint joinPoint, ExecutionLocking executionLocking) {
//
//    }

}
