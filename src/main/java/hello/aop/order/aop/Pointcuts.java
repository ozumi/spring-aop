package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("execution(* hello.aop.order..*(..))")
    public void allOrder() {}     //pointcut signature

    //클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {}     //pointcut signature

    //위 두개의 조합
    @Pointcut("allOrder() && allService()")
    public void allOrderAndService() {}     //pointcut signature
}
