package com.sejong.health;

import com.sejong.health.common.test.SqlConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
public class ConstructorTest {

    @Autowired
    private SqlConstructor sqlConstructor;

    @BeforeEach
    void Init(){
        sqlConstructor.makeDbData(20);
        sqlConstructor.makeMemberAndQuestion(20);
        sqlConstructor.makeMemberAndQuestionAndAnswer(20);
    }

    @Test
    void 값_넣기() {
        System.out.println("데이터 넣기 성공!");
    }
}
