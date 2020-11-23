package org.example.task_locker.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.example.task_locker.annotations.ExecutionLocking;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LockedAspectProcessor {

    @Pointcut(value = "@annotation(executionLocking)", argNames = "executionLocking")
    private void annotatedMethods(ExecutionLocking executionLocking) {

    }

//    @Pointcut(value = "execution(public * * (..)) && @within(executionLocking)", argNames = "executionLocking")
//    private void methodOfAnnotatedClass(ExecutionLocking executionLocking) {
//
//    }

//    @Pointcut(value = "annotatedMethods(executionLocking) || methodOfAnnotatedClass(executionLocking)", argNames = "executionLocking")
//    @Pointcut(value = "methodOfAnnotatedClass(executionLocking)", argNames = "executionLocking")
//    @Pointcut(value = "annotatedMethods(executionLocking)", argNames = "executionLocking")
//    private void annotatedMethodOrClass(ExecutionLocking executionLocking) {
//
//    }

    @Before(value = "annotatedMethods(executionLocking)", argNames = "joinPoint,executionLocking")
    public void beforeAdvice(JoinPoint joinPoint, ExecutionLocking executionLocking) {

        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Class<?> aClass = args[0].getClass();


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
