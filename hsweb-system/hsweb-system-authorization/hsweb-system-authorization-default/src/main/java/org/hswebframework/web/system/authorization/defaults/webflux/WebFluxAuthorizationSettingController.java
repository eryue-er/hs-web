package org.hswebframework.web.system.authorization.defaults.webflux;


import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.annotation.Resource;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.hswebframework.web.system.authorization.api.entity.AuthorizationSettingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autz-setting")
@Authorize
@Resource(id = "autz-setting",name = "权限分配",group = "system")
public class WebFluxAuthorizationSettingController implements ReactiveCrudController<AuthorizationSettingEntity, String> {

    @Autowired
    private ReactiveRepository<AuthorizationSettingEntity, String> reactiveRepository;

    @Override
    public ReactiveRepository<AuthorizationSettingEntity, String> getRepository() {
        return reactiveRepository;
    }
}
