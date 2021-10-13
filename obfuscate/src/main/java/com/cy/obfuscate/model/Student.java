package com.cy.model;

import com.cy.interfaces.Person;

/**
 * @author : chengyue
 * @date : 4/16/21 23:51
 * @history : change on 4/16/21 23:51 by chengyue
 * @since : v
 */
public class Student implements Person {
    @Override
    public String say() {
        return "I am Student";
    }

    @Override
    public String work() {
        return "I can wash dishes!";
    }

    @Override
    public String study() {
        return "That's what I am doing!";
    }
}
