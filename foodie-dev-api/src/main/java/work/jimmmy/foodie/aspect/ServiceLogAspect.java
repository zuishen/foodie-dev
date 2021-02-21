package work.jimmmy.foodie.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLogAspect {
    public static final Logger LOGGER = LoggerFactory.getLogger(ServiceLogAspect.class);
    /**
     * AOP通知：
     * 1.前置通知：在方法调用之前执行
     * 2.后置通知：在方法正常调用之后执行
     * 3.环绕通知：在方法调用之前和之后，都分别可以执行的通知
     * 4.异常通知：如果在方法调用过程中发生异常，则通知
     * 5.最终通知：在方法调用之后执行(无论是否发生异常，均会执行，类似finally)
     */

    /**
     * 切面表达式
     * execution 代表索要执行的表达式主体
     * 第一处 * 代表方法返回类型 * 代表所有类型
     * 第二处 包名代表aop监控的类所在的包
     * 第三处 .. 代表该包以及其子包下所有类的方法
     * 第四处 * 代表类名，*代表所有类
     * 第五处 *(..)  * 代表方法名， (..)表示方法中的任何参数
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* work.jimmmy.foodie.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info("========= 开始执行 {}.{} ===========", joinPoint.getTarget().getClass(), joinPoint.getSignature());
        // 记录开始时间
        long begin = System.currentTimeMillis();
        // 执行目标service
        Object result = joinPoint.proceed();

        // 记录结束时间
        long end = System.currentTimeMillis();
        long duration = end - begin;

        if (duration > 3000) {
            LOGGER.error("======= 执行结束 =======, 耗时: {} ms", duration);
        } else if (duration > 2000) {
            LOGGER.warn("======= 执行结束 =======, 耗时: {} ms", duration);
        } else {
            LOGGER.info("======= 执行结束 =======, 耗时: {} ms", duration);
        }

        return result;
    }
}
