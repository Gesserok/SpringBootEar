package org.example.task_locker.aspects;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.example.task_locker.services.TaskLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class LockedAspectProcessor {
    private final TaskLockService taskLockService;

    @Pointcut("@annotation(org.example.task_locker.annotations.ExecutionLocking)")
    private void annotatedMethods(){

    }
    @Pointcut("execution(public * * (..)) && @within(org.example.task_locker.annotations.ExecutionLocking)")
    private void methodOfAnnotatedClass(){

    }
    @Pointcut("annotatedMethods() || methodOfAnnotatedClass()")
    private void annotatedMethodOrClass(){

    }

    @Before("annotatedMethodOrClass()")
    public void beforeAdvice(JoinPoint joinPoint) {
        Method[] declaredMethods = joinPoint.getTarget().getClass().getDeclaredMethods();
        System.out.println("asdasdasdasdasd");
    }

    @AfterReturning("annotatedMethodOrClass()")
    public void afterReturningAdvice() {

    }

    @AfterThrowing("annotatedMethodOrClass()")
    public void afterThrowingAdvice() {

    }

}
