package org.hsweb.web.service.impl.form;

import org.hsweb.web.core.authorize.ExpressionScopeBean;
import org.hsweb.web.bean.po.form.Form;
import org.hsweb.web.service.form.FormService;
import org.hsweb.web.service.impl.AbstractTestCase;
import org.hsweb.web.core.utils.RandomUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.webbuilder.sql.DataBase;
import org.webbuilder.sql.param.insert.InsertParam;
import org.webbuilder.sql.param.query.QueryParam;
import org.webbuilder.sql.support.executor.SqlExecutor;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * Created by zhouhao on 16-4-20.
 */
@Transactional
@Rollback
public class FormServiceImplTest extends AbstractTestCase {

    @Resource
    protected FormService formService;

    @Resource
    protected DataBase dataBase;

    @Resource
    protected SqlExecutor sqlExecutor;

    @Autowired(required = false)
    private Map<String, ExpressionScopeBean> expressionScopeBeanMap;

    protected Form form;

    private String[] meta = {
            "{" +
                    "\"id1\":[" +
                    "{\"key\":\"name\",\"value\":\"u_id\",\"describe\":\"名称\"}," +
                    "{\"key\":\"comment\",\"value\":\"ID\",\"describe\":\"字段描述\"}," +
                    "{\"key\":\"javaType\",\"value\":\"string\",\"describe\":\"java类型\"}," +
                    "{\"key\":\"dataType\",\"value\":\"varchar2(32)\",\"describe\":\"数据库类型\"}" +
                    "]" +
                    ",\"id2\":[" +
                    "{\"key\":\"name\",\"value\":\"name\",\"describe\":\"名称\"}," +
                    "{\"key\":\"comment\",\"value\":\"test\",\"describe\":\"字段描述\"}," +
                    "{\"key\":\"javaType\",\"value\":\"string\",\"describe\":\"java类型\"}," +
                    "{\"key\":\"dataType\",\"value\":\"varchar2(32)\",\"describe\":\"数据库类型\"}" +
                    "]" +
                    "}",
            "{" +
                    "\"id1\":[" +
                    "{\"key\":\"name\",\"value\":\"u_id\",\"describe\":\"名称\"}," +
                    "{\"key\":\"comment\",\"value\":\"ID\",\"describe\":\"字段描述\"}," +
                    "{\"key\":\"javaType\",\"value\":\"string\",\"describe\":\"java类型\"}," +
                    "{\"key\":\"dataType\",\"value\":\"varchar2(32)\",\"describe\":\"数据库类型\"}" +
                    "]" +
                    ",\"id2\":[" +
                    "{\"key\":\"name\",\"value\":\"name\",\"describe\":\"名称\"}," +
                    "{\"key\":\"comment\",\"value\":\"test\",\"describe\":\"字段描述\"}," +
                    "{\"key\":\"javaType\",\"value\":\"string\",\"describe\":\"java类型\"}," +
                    "{\"key\":\"dataType\",\"value\":\"varchar2(32)\",\"describe\":\"数据库类型\"}" +
                    "]" +
                    ",\"id3\":[" +
                    "{\"key\":\"name\",\"value\":\"sex\",\"describe\":\"名称\"}," +
                    "{\"key\":\"comment\",\"value\":\"test\",\"describe\":\"性别\"}," +
                    "{\"key\":\"javaType\",\"value\":\"string\",\"describe\":\"java类型\"}," +
                    "{\"key\":\"dataType\",\"value\":\"varchar2(32)\",\"describe\":\"数据库类型\"}" +
                    "]" +
                    "}"
    };

    @Before
    public void setup() throws Exception {
        form = new Form();
        form.setName("test_form");
        form.setCreate_date(new Date());
        form.setHtml("<input field-id='id1'/><input field-id='id2'/>");
        form.setMeta(meta[0]);
        form.setU_id(RandomUtil.randomChar());
        formService.insert(form);
    }

    @Test
    public void testDeploy() throws Exception {
        //部署
        formService.deploy(form.getU_id());
        dataBase.getTable("test_form").createInsert()
                .insert(new InsertParam().value("name", "张三").value("u_id", "test"));
        Map<String, Object> data = dataBase.getTable("test_form")
                .createQuery().single(new QueryParam().where("name$LIKE", "张三"));
        Assert.assertEquals("张三", data.get("name"));

        System.out.println(formService.createDeployHtml(form.getName()));
        formService.createDeployHtml(form.getName());
        formService.deploy(form.getU_id());
        formService.createDeployHtml(form.getName());

//        form.setMeta(meta[1]);
//        formService.update(form);
//        formService.deploy(form.getU_id());
    }


}