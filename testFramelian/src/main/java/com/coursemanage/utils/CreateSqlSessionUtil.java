package com.coursemanage.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class CreateSqlSessionUtil {
    public  static SqlSession getSession() throws IOException {
        //获取配置的资源文件
        Reader reader= Resources.getResourceAsReader("databaseconfig.xml");
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession=factory.openSession();
        return sqlSession;
        }
    }

