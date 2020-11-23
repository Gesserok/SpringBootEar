package org.example.task_locker.aspects;

import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.example.task_locker.annotations.ExecutionLocking;
import org.example.task_locker.services.TaskLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class LockedAspectProcessor {
    private final TaskLockService taskLockService;

    @Pointcut(value = "@annotation(executionLocking)", argNames = "executionLocking")
    private void annotatedMethods(ExecutionLocking executionLocking) {

    }

    @Pointcut(value = "execution(public * * (..)) && @within(executionLocking)", argNames = "executionLocking")
    private void methodOfAnnotatedClass(ExecutionLocking executionLocking) {

    }

    @Pointcut(value = "annotatedMethods(executionLocking) || methodOfAnnotatedClass(executionLocking)", argNames = "executionLocking")
    private void annotatedMethodOrClass(ExecutionLocking executionLocking) {

    }

    @Before(value = "annotatedMethodOrClass(executionLocking)", argNames = "joinPoint,executionLocking")
    public void beforeAdvice(JoinPoint joinPoint, ExecutionLocking executionLocking) {
        Method[] declaredMethods = joinPoint.getTarget().getClass().getDeclaredMethods();

        long l1 = executionLocking.minTime();
        long l2 = executionLocking.maxTime();

    }

    @AfterReturning(value = "annotatedMethodOrClass(executionLocking)", argNames = "executionLocking")
    public void afterReturningAdvice(ExecutionLocking executionLocking) {

    }

    @AfterThrowing(value = "annotatedMethodOrClass(executionLocking)", argNames = "executionLocking")
    public void afterThrowingAdvice(ExecutionLocking executionLocking) {

    }

}
