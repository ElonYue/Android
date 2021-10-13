package com.cy.obfuscate.test;

import android.os.Bundle;

import com.cy.model.Engineer;
import com.cy.model.Student;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author : chengyue
 * @date : 4/16/21 23:54
 * @history : change on 4/16/21 23:54 by chengyue
 * @since : v
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        test();
    }

    public void test(){
        System.out.println("test");

        Engineer engineer=new Engineer();
        engineer.say();
        engineer.study();
        engineer.work();

        Student student=new Student();
        student.say();
        student.study();
        student.work();
    }
}
