package com.example.aspect.TestAspect.aspects;

        import com.example.aspect.TestAspect.services.ApplicationHandleErrorImpl;
        import org.aspectj.lang.annotation.AfterThrowing;
        import org.aspectj.lang.annotation.Aspect;
        import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class ApplicationErrorAspect {

    @Autowired
    private ApplicationHandleErrorImpl applicationHandleErrorImpl;

    @AfterThrowing(pointcut = "execution(* com.example.aspect.TestAspect.controller.ApplicationController.*(..))", throwing = "ex")
    public void handleErrors(Exception ex) {
        applicationHandleErrorImpl.handleError(ex);
    }
}
