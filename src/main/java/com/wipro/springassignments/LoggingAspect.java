package com.wipro.springassignments;

public class LoggingAspect {

    public void logBeforeMethod() {
        System.out.println("[LOG] Method execution started.");
    }

    public void logAfterReturning() {
        System.out.println("[LOG] Method executed successfully.");
    }

    public void logAfterThrowing(Exception ex) {
        System.out.println("[ERROR] Exception occurred: " + ex.getMessage());
    }

    public Object logAround(org.aopalliance.intercept.MethodInvocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("[LOG] Around - Before execution: " + invocation.getMethod().getName());

        Object result = invocation.proceed();

        long end = System.currentTimeMillis();
        System.out.println("[LOG] Around - After execution: " + invocation.getMethod().getName());
        System.out.println("[LOG] Execution time: " + (end - start) + " ms");

        return result;
    }
}
