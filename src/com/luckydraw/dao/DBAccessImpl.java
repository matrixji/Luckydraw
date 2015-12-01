package com.luckydraw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.luckydraw.dto.Employee;
import com.luckydraw.enumset.DrawnFlag;
import com.luckydraw.enumset.PrizeLevelFlag;
import com.luckydraw.util.Config;

public class DBAccessImpl implements DBAccess
{
    private DBConnectionPool dbConnectionPool;

    public DBAccessImpl()
    {
        Config config = new Config();
    	String url = config.getProperties().getProperty( "database.url" );
        String user = config.getProperties().getProperty( "database.username" );
        String password = config.getProperties().getProperty( "database.password" );
        final int maxConn = 100;
        dbConnectionPool = new DBConnectionPool( "ConnectionPool", url, user, password, maxConn );
    }

    public int executeUpdate( String sql )
    {
        Connection conn = dbConnectionPool.getConnection();
        try
        {
            Statement statement = conn.createStatement();
            return statement.executeUpdate( sql );
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        finally
        {
            dbConnectionPool.freeConnection( conn );
        }

        return -1;
    }

    public int queryCount( String sql )
    {
        Connection conn = dbConnectionPool.getConnection();
        try
        {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery( sql );
            resultSet.next();
            int rowCount = resultSet.getInt( "rowCount" );
            return rowCount;
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        finally
        {
            dbConnectionPool.freeConnection( conn );
        }
        return 0;
    }

    public Map<Integer, String> queryData( String sql )
    {
        Connection conn = dbConnectionPool.getConnection();
        Map<Integer, String> resultMap = new HashMap<Integer, String>();
        try
        {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery( sql );
            while( rs.next() )
            {
                int nsnId = rs.getInt( "nsnid" );
                String ename = rs.getString( "ename" );
                resultMap.put( nsnId, ename );
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        finally
        {
            dbConnectionPool.freeConnection( conn );
        }

        return resultMap;
    }

    public Map<Integer, String> queryConsolationData( String sql )
    {
        Connection conn = dbConnectionPool.getConnection();
        Map<Integer, String> resultMap = new HashMap<Integer, String>();
        try
        {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery( sql );
            while( rs.next() )
            {
                int nsnId = rs.getInt( "nsnid" );
                String ename = rs.getString( "ename" );
                int itemId = rs.getInt( "memo" );
                resultMap.put( nsnId, ename + "," + itemId );
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        finally
        {
            dbConnectionPool.freeConnection( conn );
        }

        return resultMap;
    }

	@Override
	public Employee getEmployeeById(String employeeId) {

		Connection conn = dbConnectionPool.getConnection();
		Employee employee = null;
		String sql = "select * from employees where nsnId = "+employeeId;
        try
        {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery( sql );
            while( rs.next() )
            {
            	employee = new Employee();
            	employee.setId(rs.getInt("id"));
            	employee.setNsnId(rs.getInt("nsnid"));
            	employee.setName(rs.getString("ename"));
            	employee.setDrawnFlag(rs.getInt("chosen"));
            	employee.setPrizeLevel(rs.getInt("class"));
            	employee.setSequenceId(rs.getInt("memo"));
            	employee.setPresentFlag(rs.getInt("present"));
            	break;
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        finally
        {
            dbConnectionPool.freeConnection( conn );
        }

        return employee;
	}

	@Override
	public List<Integer> getDrawnEndNumber() {

		Connection conn = dbConnectionPool.getConnection();
		List<Integer> drawnNumberList = new ArrayList<Integer>();
		String sql = "select DISTINCT nsnid%10 from employees where chosen="+DrawnFlag.DRAWN.getValue()+" and class in("+
			PrizeLevelFlag.FORTH_PRIZE.getValue()+","+PrizeLevelFlag.THIRD_PRIZE.getValue()+","+
			PrizeLevelFlag.SECOND_PRIZE.getValue()+","+PrizeLevelFlag.FIRST_PRIZE.getValue()+")";
        try
        {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery( sql );
            while( rs.next() )
            {
            	drawnNumberList.add(rs.getInt(1));
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        finally
        {
            dbConnectionPool.freeConnection( conn );
        }

        return drawnNumberList;
	}

}
