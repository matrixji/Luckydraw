package com.luckydraw.dao;

import java.util.List;
import java.util.Map;

import com.luckydraw.dto.Employee;

public interface DBAccess
{
    Map<Integer, String> queryData( String sql );

    int queryCount( String sql );

    int executeUpdate( String sql );

    int clearTable(String sql);

    Map<Integer, String> queryConsolationData( String sql );

    public Employee getEmployeeById(String employeeId);

    public List<Integer> getDrawnEndNumber();
}
