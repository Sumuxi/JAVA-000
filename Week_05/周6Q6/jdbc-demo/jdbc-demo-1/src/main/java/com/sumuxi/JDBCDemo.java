package com.sumuxi;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCDemo {

    private static String drivername;
    private static String url;
    private static String user;
    private static String password;

    /**
     * 通过静态代码块，初始化数据库连接配置数据，并且注册数据库驱动
     */
    static {
        try {
            Properties pr = new Properties();
            //通过读取Properties文件给属性赋值，即每次使用该工具类都会读取最新配置进行连接
            pr.load(JDBCDemo.class.getResourceAsStream("/jdbc_config.properties"));
            drivername = pr.getProperty("drivername");
            url = pr.getProperty("url");
            user = pr.getProperty("user");
            password = pr.getProperty("password");
            Class.forName(drivername);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException("获取数据库连接异常，请检查配置数据");
        }
    }


    public static void main(String[] args) throws Exception {
        System.out.println("使用JDBC元素接口：");
        useOriginal();

        System.out.println("使用事务：");
        useTransaction();

        System.out.println("使用PrepareStatement方式：");
        usePrepareStatement();

        System.out.println("使用批处理方式：");
        useBatch();

        System.out.println("使用Hikari连接池：");
        useHikari();
    }


    /**
     * 使用 JDBC 原生接口，实现数据库的增删改查操作。
     * @author: 杨雄辉
     * @update: 2020-11-19 14:55
     * @return: void
     **/
    public static void useOriginal() throws SQLException {

        //通过工具类获取数据库连接对象
        Connection con = getConnection();
        //通过连接创建数据库执行对象
        Statement sta = con.createStatement();

        //查询
        String sqlStatement = "SELECT * FROM category WHERE category_id = 10";
        executeQuery(sta, sqlStatement);
        //增加
        sqlStatement = "INSERT INTO category VALUES('50','1','CHINA','CHINA','2020-11-19')";
        System.out.println("插入执行结果:" + sta.executeUpdate(sqlStatement));
        //更新
        sqlStatement = "UPDATE category SET category_name='甜点' WHERE category_id = 50";
        System.out.println("更新执行结果:" + sta.executeUpdate(sqlStatement));
        //删除
        sqlStatement = "DELETE FROM category WHERE category_id = 50";
        System.out.println("删除执行结果:" + sta.executeUpdate(sqlStatement));
        closeResource(con, sta);
    }

    public static void useTransaction() {
        //通过工具类获取数据库连接对象
        Connection con = getConnection();
        //通过连接创建数据库执行对象
        PreparedStatement ps = null;

        try {
            con.setAutoCommit(false);   // 设置连接不自动提交，即用该连接进行的操作都不更新到数据库

            String sqlStatement = "INSERT INTO category VALUES(?,?,?,?,?)";
            ps = con.prepareStatement(sqlStatement);
            setObject(ps, "0", "1", "甜点", "china", "2020-11-11");
            int result = ps.executeUpdate();
            System.out.println("插入执行结果:" + result);

            con.commit();   // 提交给数据库处理
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        closeResource(con, ps);
    }

    /**
     * PrepareStatement方式
     * @author: 杨雄辉
     * @update: 2020-11-20 21:35
     * @return: void
     **/
    public static void usePrepareStatement() throws SQLException {
        //通过工具类获取数据库连接对象
        Connection con = getConnection();
        //通过连接创建数据库执行对象
        PreparedStatement ps = null;

        //查询
        String sqlStatement = "SELECT * FROM category WHERE category_id = ?";
        ps = con.prepareStatement(sqlStatement);
        ps.setObject(1, "10");
        executeQuery(ps, sqlStatement);

        //增加
        sqlStatement = "INSERT INTO category VALUES(?,?,?,?,?)";
        ps = con.prepareStatement(sqlStatement);
        setObject(ps, "0", "1", "甜点", "china", "2020-11-11");
        System.out.println("插入执行结果:" + ps.executeUpdate());
        //更新
        sqlStatement = "UPDATE category SET category_name=? WHERE category_id = ?";
        ps = con.prepareStatement(sqlStatement);
        ps.setObject(1, "甜点");
        ps.setObject(2, "1");
        System.out.println("更新执行结果:" + ps.executeUpdate());
        //删除
        sqlStatement = "DELETE FROM category WHERE category_id = ?";
        ps = con.prepareStatement(sqlStatement);
        ps.setObject(1, "1");
        System.out.println("删除执行结果:" + ps.executeUpdate());
        closeResource(con, ps);

    }

    /**
     * 批处理方式
     * @author: 杨雄辉
     * @update: 2020-11-20 14:52
     * @return: void
     **/
    public static void useBatch() throws SQLException {
        //通过工具类获取数据库连接对象
        Connection con = getConnection();
        //通过连接创建数据库执行对象
        PreparedStatement ps = null;

        //增加
        String sqlStatement = "INSERT INTO category VALUES(?,?,?,?,?)";
        ps = con.prepareStatement(sqlStatement);
        setObject(ps, "0", "1", "甜点", "china", "2020-11-11");
        ps.addBatch(); //添加一次预定义参数
        setObject(ps, "0", "1", "甜点", "china", "2020-11-12");
        ps.addBatch();//再添加一次预定义参数
        int[] result = ps.executeBatch();
        System.out.print("批处理操作结果：");
        for (int r : result) {
            System.out.print(r + "\t");
        }
        System.out.println();
        closeResource(con, ps);
    }

    /**
     * 使用Hikari连接池
     * @author: 杨雄辉
     * @update: 2020-11-20 21:35
     * @return: void
     **/
    public static void useHikari() {
        //实例化类
        HikariConfig hikariConfig = new HikariConfig();
        //设置url
        hikariConfig.setJdbcUrl(url);
        //数据库帐号
        hikariConfig.setUsername(user);
        //数据库密码
        hikariConfig.setPassword(password);
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        HikariDataSource ds = new HikariDataSource(hikariConfig);
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = ds.getConnection();
            String sqlStatement = "SELECT * FROM category WHERE category_id = ?";
            statement = con.prepareStatement(sqlStatement);
            statement.setObject(1, "10");
            executeQuery(statement, sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResource(con, statement);
        }
    }

    /**
     * 获取数据库连接对象
     * @return
     */
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("获取数据库连接异常，请检查配置数据");
        }
        return con;
    }

    /**
     * 关闭JDBC相关资源
     * @param con
     * @param sta
     */
    public static void closeResource(Connection con, Statement sta) {
        try {
            if (con != null) {
                con.close();
            }
            if (sta != null) {
                sta.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void executeQuery(Statement statement, String sql) throws SQLException {
        ResultSet rs;
        if (statement instanceof PreparedStatement){
            rs = ((PreparedStatement)statement).executeQuery();
        }else {
            rs = statement.executeQuery(sql);
        }

        while (rs.next()) {
            System.out.println("查询操作结果："
                    + rs.getObject(1)
                    + "\t" + rs.getObject(2)
                    + "\t" + rs.getObject(3)
                    + "\t" + rs.getObject(4)
                    + "\t" + rs.getObject(5));
        }
    }

    private static void setObject(PreparedStatement ps, String param1, String param2, String param3, String param4, String param5) throws SQLException {
        ps.setObject(1, param1);
        ps.setObject(2, param2);
        ps.setObject(3, param3);
        ps.setObject(4, param4);
        ps.setObject(5, param5);
    }

    /**
     * 增删改
     *
     * @param sta
     * @param sql
     * @return 受影响的行数
     * @throws SQLException
     */
    private static int executeUpdate(Statement sta, String sql) throws SQLException {
        if (sta instanceof PreparedStatement){
            return ((PreparedStatement)sta).executeUpdate();
        }
        return sta.executeUpdate(sql);
    }

}