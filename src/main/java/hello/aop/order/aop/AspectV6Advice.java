package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {

    @Around("hello.aop.order.aop.Pointcuts.allOrderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            //@Before
            log.info("[Transaction start] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            //@AfterReturning
            log.info("[Transaction commit] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            //AfterThrowing
            log.info("[Transaction rollback] {}", joinPoint.getSignature());
            throw e;
        } finally {
            //@After
            log.info("[Resource release] {}", joinPoint.getSignature());
        }
    }


    @Before("hello.aop.order.aop.Pointcuts.allOrderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[Before] {}", joinPoint.getSignature());
    }

    @AfterReturning(value="hello.aop.order.aop.Pointcuts.allOrderAndService()", returning="result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("[AfterReturning] {} result={}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value="hello.aop.order.aop.Pointcuts.allOrderAndService()", throwing="ex")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[AfterThrowing] {} ex={}", joinPoint.getSignature(), ex);
    }

    @After(value="hello.aop.order.aop.Pointcuts.allOrderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[After] {}", joinPoint.getSignature());
    }
}
