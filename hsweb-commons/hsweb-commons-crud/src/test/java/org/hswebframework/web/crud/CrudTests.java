package org.hswebframework.web.crud;

import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.web.crud.entity.TestEntity;
import org.hswebframework.web.crud.service.TestEntityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CrudTests  {

    @Autowired
    private TestEntityService service;

    @Test
    public void test(){
        Mono.just(TestEntity.of("test",100))
                .as(service::insert)
                .as(StepVerifier::create)
                .expectNext(1)
                .verifyComplete();
    }
}
