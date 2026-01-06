package org.example.step_function.service;

public interface Step<C> {

    //can open @Transaction in Implement class to Rollback in spring boot.
    //
    void execute(C context);

    //use for manual rollback if needed
    void rollback(C context);
}
