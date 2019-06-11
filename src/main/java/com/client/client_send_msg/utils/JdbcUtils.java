package com.client.client_send_msg.utils;


import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.util.ResourceBundle;

/**
 * 用来封装第三方对象的类，加入spring容器
 *
 * @author xiaorui
 */
public class JdbcUtils {

    //创建DataSource接口的实现类对象(BasicDataSource)，我们的dbcp工具包已经帮我们实现好了，咱们直接用就好！
    private static BasicDataSource dataSource = new BasicDataSource();

    //定义数据库链接变量
    private static final String DRIVERNAME;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    private static final int INITIALSIZE;
    private static final int MAXIDLE;
    private static final int MINLDEL;
    private static final int MAXACTIVE;

    static {
        DRIVERNAME = ResourceBundle.getBundle("application").getString("spring.datasource.driver-class-name");
        URL = ResourceBundle.getBundle("application").getString("spring.datasource.url");
        USERNAME = ResourceBundle.getBundle("application").getString("spring.datasource.username");
        PASSWORD = ResourceBundle.getBundle("application").getString("spring.datasource.password");
        INITIALSIZE = Integer.parseInt(ResourceBundle.getBundle("application").getString("spring.datasource.initial-size"));
        MAXIDLE = Integer.parseInt(ResourceBundle.getBundle("application").getString("spring.datasource.max-idle"));
        MINLDEL = Integer.parseInt(ResourceBundle.getBundle("application").getString("spring.datasource.min-idle"));
        MAXACTIVE = Integer.parseInt(ResourceBundle.getBundle("application").getString("spring.datasource.max-wait"));
    }

    //静态代码块，对象BasicDataSource对象中的配置，自定义
    static {
        //链接数据库的4个最基本信息，通过对象的set方法进行设置如下：
        dataSource.setDriverClassName(DRIVERNAME);                    //设置数据库驱动
        dataSource.setUrl(URL);                                        //设置访问数据库的路径
        dataSource.setUsername(USERNAME);                            //设置登录数据库的用户名
        dataSource.setPassword(PASSWORD);                            //设置登录数据库的密码

        //对象连接池中的常见配置项，以下的四个配置可以不配置(因为有默认配置)，但是上面的四个是必须要配置的！
        dataSource.setInitialSize(INITIALSIZE);            //指定初始化的连接数
        dataSource.setMaxTotal(MAXIDLE);                  //指定最大链接数量
        dataSource.setMaxIdle(MINLDEL);                    //指定最大空闲数
        dataSource.setMinIdle(MAXACTIVE);               //指定最小空闲数
    }

    //定义静态方法，返回BasicDataSource类的对象
    public static DataSource getDataSource() {
        return dataSource;
    }

}

