package com.luckydraw.logic;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.luckydraw.dto.Employee;

public interface LuckyDraw
{

    void saveSuffixWith( int level, int suffixNum );

    void saveClass2( Set<Integer> results );

    void saveClass1( Set<Integer> results );

    void saveSpecialAward( Set<Integer> results );

    void saveConsolationPrize( Set<Integer> results, List<Integer> itemIds );

    void saveReplenishPrize( int level, Set<Integer> results );

    void reset();

    void cacheData();

    Map<Integer, String> randomSelect( int count );

    int queryTotalCandidates();

    void generateExcel();
    
    public void cancelTheWinner(String employeeId);
    
    public String getPresentInfoOfTheEmployee(String employeeId);
    
    public void saveWithPrizeLevelAndIdSet(int prizeLevel,Set<Integer> idSet);
    
    public Employee getEmployeeById(String employeeId);
    
    public void generateExcel2014();
    
    public List<Integer> getDrawnEndNumberList();
    
    public boolean isReplenishEnabled(int prizeLevel);
    
    public int getDrawnNumberByPrizeLevel(int prizeLevel);
}
