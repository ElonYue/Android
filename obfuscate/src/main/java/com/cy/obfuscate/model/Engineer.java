package com.cy.model;

import com.cy.interfaces.Person;

/**
 * @author : chengyue
 * @date : 4/16/21 23:47
 * @history : change on 4/16/21 23:47 by chengyue
 * @since : v
 */
public class Engineer implements Person {


    @Override
    public String say() {
        return "I am en engineer!";
    }

    @Override
    public String work() {
        return "I work very hard!";
    }

    @Override
    public String study() {
        return "I do study sometimes!";
    }
}
