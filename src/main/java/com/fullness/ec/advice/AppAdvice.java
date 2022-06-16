package com.fullness.ec.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * アプリ全体のAOPクラス
 */
@Component
@Aspect
public class AppAdvice {

    /**
     * ロガーオブジェクト
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * AOPによるメソッドの引数と戻り値のデバッグメソッド
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.fullness.ec..*.*(..))")
    public Object debug(ProceedingJoinPoint joinPoint) throws Throwable{
        // ($className.kt:$line)$mthd
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        //logger.info(joinPoint.getStaticPart().getSourceLocation().getLine()+"");
        //StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        //int line = trace[trace.length-1].getLineNumber();
        //String link = String.format("(%s.java:%d)",className,line);
        //logger.info(link);
        logger.info("▼▼▼▼▼▼▼▼▼▼"+getClassAndMethod(joinPoint)+"▼▼▼▼▼▼▼▼▼▼");
        Arrays.stream(joinPoint.getArgs()).forEach( arg -> logger.info("引数の値:" + arg) );
        //for (Object o : joinPoint.getArgs()){ logger.info("引数の値:" + o); }
        Object result = joinPoint.proceed();
        logger.info("戻り値:"+result);
        logger.info("▲▲▲▲▲▲▲▲▲▲"+getClassAndMethod(joinPoint)+"▲▲▲▲▲▲▲▲▲▲");
        return result;
    }

    /**
     * ベースパッケージ名を削除
     * @param fqcn 完全修飾クラス名
     * @return ベースパッケージ名を省略したパッケージ・クラス名
     */
    private String trimBasePackage(String fqcn){
        return fqcn.replace("com.fullness.ec.","");
    }

    /**
     * デバッグ対象のパッケージ、クラス、メソッドの文字列を生成
     * @param joinPoint
     * @return
     */
    private String getClassAndMethod(ProceedingJoinPoint joinPoint){
        return trimBasePackage(joinPoint.getSignature().getDeclaringTypeName())+"."+joinPoint.getSignature().getName()+"()";
    }
}
