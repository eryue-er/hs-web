package org.hswebframework.web.service.organizational.simple.terms;

import org.hswebframework.web.service.organizational.DepartmentService;
import org.hswebframework.web.service.organizational.DistrictService;
import org.hswebframework.web.service.organizational.OrganizationalService;
import org.hswebframework.web.service.organizational.PositionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhouhao
 * @since 3.0.0-RC
 */
@Configuration
public class CustomSqlTermConfiguration {

    @Bean
    public InServiceTreeInSqlTerm<String> distInSqlTerm(DistrictService districtService) {
        return new InServiceTreeInSqlTerm<>(districtService, "dist", "s_district", false);
    }

    @Bean
    public InServiceTreeInSqlTerm<String> distNotInSqlTerm(DistrictService districtService) {
        return new InServiceTreeInSqlTerm<>(districtService, "dist", "s_district", true);
    }


    @Bean
    public InServiceTreeInSqlTerm<String> orgInSqlTerm(OrganizationalService organizationalService) {
        return new InServiceTreeInSqlTerm<>(organizationalService, "org", "s_organization", false);
    }

    @Bean
    public InServiceTreeInSqlTerm<String> orgNotInSqlTerm(OrganizationalService organizationalService) {
        return new InServiceTreeInSqlTerm<>(organizationalService, "org", "s_organization", true);
    }

    @Bean
    public InServiceTreeInSqlTerm<String> departmentInSqlTerm(DepartmentService departmentService) {
        return new InServiceTreeInSqlTerm<>(departmentService, "dept", "s_department", false);
    }

    @Bean
    public InServiceTreeInSqlTerm<String> departmentNotInSqlTerm(DepartmentService departmentService) {
        return new InServiceTreeInSqlTerm<>(departmentService, "dept", "s_department", true);
    }

    @Bean
    public InServiceTreeInSqlTerm<String> positionInSqlTerm(PositionService positionService) {
        return new InServiceTreeInSqlTerm<>(positionService, "pos", "s_position", false);
    }

    @Bean
    public InServiceTreeInSqlTerm<String> positionNotInSqlTerm(PositionService positionService) {
        return new InServiceTreeInSqlTerm<>(positionService, "pos", "s_position", true);
    }

    @Bean
    public PersonInPositionSqlTerm personInPositionSqlTerm() {
        return new PersonInPositionSqlTerm(false);
    }

    @Bean
    public PersonInPositionSqlTerm personNotInPositionSqlTerm() {
        return new PersonInPositionSqlTerm(true);
    }

    /*====================================================================================*/

    @Bean
    public UserInPositionSqlTerm userInPositionSqlTerm(PositionService positionService) {

        return new UserInPositionSqlTerm(false, false, "user-in-position", positionService);
    }

    @Bean
    public UserInPositionSqlTerm userNotInPositionSqlTerm(PositionService positionService) {

        return new UserInPositionSqlTerm(true, false, "user-not-in-position", positionService);
    }

    @Bean
    public UserInPositionSqlTerm userInPositionChildSqlTerm(PositionService positionService) {

        return new UserInPositionSqlTerm(false, true, "user-in-position-child", positionService);
    }

    @Bean
    public UserInPositionSqlTerm userNotInPositionChildSqlTerm(PositionService positionService) {

        return new UserInPositionSqlTerm(true, true, "user-not-in-position-child", positionService);
    }
 /*====================================================================================*/

    @Bean
    public UserInSqlTerm personInPositionSqlTerm(PositionService positionService) {

        return new UserInPositionSqlTerm(false, false, "person-in-position", positionService).forPerson();
    }

    @Bean
    public UserInSqlTerm personNotInPositionSqlTerm(PositionService positionService) {

        return new UserInPositionSqlTerm(true, false, "person-not-in-position", positionService).forPerson();
    }

    @Bean
    public UserInSqlTerm personInPositionChildSqlTerm(PositionService positionService) {

        return new UserInPositionSqlTerm(false, true, "person-in-position-child", positionService).forPerson();
    }

    @Bean
    public UserInSqlTerm personNotInPositionChildSqlTerm(PositionService positionService) {

        return new UserInPositionSqlTerm(true, true, "person-not-in-position-child", positionService).forPerson();
    }

    /*====================================================================================*/
    @Bean
    public UserInSqlTerm userInDepartmentSqlTerm(DepartmentService departmentService) {

        return new UserInDepartmentSqlTerm(false, false, "user-in-department", departmentService);
    }

    @Bean
    public UserInSqlTerm userNotInDepartmentSqlTerm(DepartmentService departmentService) {

        return new UserInDepartmentSqlTerm(true, false, "user-not-in-department", departmentService);
    }

    @Bean
    public UserInSqlTerm userInDepartmentChildSqlTerm(DepartmentService departmentService) {

        return new UserInDepartmentSqlTerm(false, true, "user-in-department-child", departmentService);
    }

    @Bean
    public UserInSqlTerm userNotInDepartmentChildSqlTerm(DepartmentService departmentService) {

        return new UserInDepartmentSqlTerm(true, true, "user-not-in-department-child", departmentService);
    }

    /*====================================================================================*/
    @Bean
    public UserInSqlTerm personInDepartmentSqlTerm(DepartmentService departmentService) {

        return new UserInDepartmentSqlTerm(false, false, "person-in-department", departmentService).forPerson();
    }

    @Bean
    public UserInSqlTerm personNotInDepartmentSqlTerm(DepartmentService departmentService) {

        return new UserInDepartmentSqlTerm(true, false, "person-not-in-department", departmentService).forPerson();
    }

    @Bean
    public UserInSqlTerm personInDepartmentChildSqlTerm(DepartmentService departmentService) {

        return new UserInDepartmentSqlTerm(false, true, "person-in-department-child", departmentService).forPerson();
    }

    @Bean
    public UserInSqlTerm personNotInDepartmentChildSqlTerm(DepartmentService departmentService) {

        return new UserInDepartmentSqlTerm(true, true, "person-not-in-department-child", departmentService).forPerson();
    }

    /*====================================================================================*/
    @Bean
    public UserInSqlTerm userInOrgSqlTerm(OrganizationalService organizationalService) {

        return new UserInOrgSqlTerm(false, false, "user-in-org", organizationalService);
    }

    @Bean
    public UserInSqlTerm userNotInOrgSqlTerm(OrganizationalService organizationalService) {

        return new UserInOrgSqlTerm(true, false, "user-not-in-org", organizationalService);
    }

    @Bean
    public UserInSqlTerm userInOrgChildSqlTerm(OrganizationalService organizationalService) {

        return new UserInOrgSqlTerm(false, true, "user-in-org-child", organizationalService);
    }

    @Bean
    public UserInSqlTerm userNotInOrgChildSqlTerm(OrganizationalService organizationalService) {

        return new UserInOrgSqlTerm(true, true, "user-not-in-org-child", organizationalService);
    }


    /*====================================================================================*/
    @Bean
    public UserInSqlTerm personInOrgSqlTerm(OrganizationalService organizationalService) {

        return new UserInOrgSqlTerm(false, false, "person-in-org", organizationalService).forPerson();
    }

    @Bean
    public UserInSqlTerm personNotInOrgSqlTerm(OrganizationalService organizationalService) {

        return new UserInOrgSqlTerm(true, false, "person-not-in-org", organizationalService).forPerson();
    }

    @Bean
    public UserInSqlTerm personInOrgChildSqlTerm(OrganizationalService organizationalService) {

        return new UserInOrgSqlTerm(false, true, "person-in-org-child", organizationalService).forPerson();
    }

    @Bean
    public UserInSqlTerm personNotInOrgChildSqlTerm(OrganizationalService organizationalService) {

        return new UserInOrgSqlTerm(true, true, "person-not-in-org-child", organizationalService).forPerson();
    }


    /*====================================================================================*/
    @Bean
    public UserInSqlTerm userInDistSqlTerm(DistrictService districtService) {

        return new UserInDistSqlTerm(false, false, "user-in-dist", districtService);
    }

    @Bean
    public UserInSqlTerm userNotInDistSqlTerm(DistrictService districtService) {

        return new UserInDistSqlTerm(true, false, "user-not-in-dist", districtService);
    }

    @Bean
    public UserInSqlTerm userInDistChildSqlTerm(DistrictService districtService) {

        return new UserInDistSqlTerm(false, true, "user-in-dist-child", districtService);
    }

    @Bean
    public UserInSqlTerm userNotInDistChildSqlTerm(DistrictService districtService) {

        return new UserInDistSqlTerm(true, true, "user-not-in-dist-child", districtService);
    }

    /*====================================================================================*/
    @Bean
    public UserInSqlTerm personInDistSqlTerm(DistrictService districtService) {

        return new UserInDistSqlTerm(false, false, "person-in-dist", districtService).forPerson();
    }

    @Bean
    public UserInSqlTerm personNotInDistSqlTerm(DistrictService districtService) {

        return new UserInDistSqlTerm(true, false, "person-not-in-dist", districtService).forPerson();
    }

    @Bean
    public UserInSqlTerm personInDistChildSqlTerm(DistrictService districtService) {

        return new UserInDistSqlTerm(false, true, "person-in-dist-child", districtService).forPerson();
    }

    @Bean
    public UserInSqlTerm personNotInDistChildSqlTerm(DistrictService districtService) {

        return new UserInDistSqlTerm(true, true, "person-not-in-dist-child", districtService).forPerson();
    }


}
