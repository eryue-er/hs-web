package org.hswebframework.web.datasource;

import org.hswebframework.web.datasource.switcher.DataSourceSwitcher;

import javax.sql.DataSource;

/**
 * 动态数据源
 *
 * @author zhouhao
 * @since 3.0
 */
public interface DynamicDataSource extends DataSource {

    /**
     * @return 数据源ID
     * @see DataSourceSwitcher#currentDataSourceId()
     */
    String getId();

    /**
     * @return 数据库类型
     * @see DatabaseType
     */
    DatabaseType getType();
}
