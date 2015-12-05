package com.luckydraw.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.luckydraw.dao.DBAccess;
import com.luckydraw.dao.DBAccessImpl;
import com.luckydraw.dao.FileAccess;
import com.luckydraw.dao.FileAccessImpl;
import com.luckydraw.dto.Employee;
import com.luckydraw.enumset.DrawnFlag;
import com.luckydraw.enumset.PresentFlag;
import com.luckydraw.enumset.PrizeLevelFlag;
import com.luckydraw.exception.EmployeeNonExistException;
import com.luckydraw.ui.LuckyDrawUI2014;
import com.luckydraw.util.Constant;

public class LuckyDrawImpl implements LuckyDraw
{
    private static DBAccess dbAccess;

    private FileAccess fileAccess;

    //cache data
    private Map<Integer, String> available;

    public LuckyDrawImpl()
    {
        dbAccess = new DBAccessImpl();
        fileAccess = new FileAccessImpl( "D:\\luckydraw" );
    }

    //用于保存抽取尾号的奖项
    public void saveSuffixWith( int level, int suffixNum )
    {
        String sql = "update employees set chosen="+DrawnFlag.DRAWN.getValue()+", class=" + level + " where nsnid % 10=" + suffixNum;
        dbAccess.executeUpdate( sql );
    }

    @Override
	public void saveWithPrizeLevelAndIdSet(int prizeLevel, Set<Integer> idSet) {

    	updateDb( prizeLevel, idSet );
	}

    public void saveClass2( Set<Integer> results )
    {
        updateDb( 2, results );
    }

    public void saveClass1( Set<Integer> results )
    {
        updateDb( 1, results );
    }

    public void saveSpecialAward( Set<Integer> results )
    {
        updateDb( 0, results );
    }

    /**
     * 安慰奖，级别标记为7
     *
     * @param results
     */
    public void saveConsolationPrize( Set<Integer> results, List<Integer> itemIds )
    {
        int i = 0;
        for( Integer nsnid : results )
        {
            String sql =
                "update employees set chosen="+DrawnFlag.DRAWN.getValue()+", class="+PrizeLevelFlag.CONSOLATION_PRIZE.getValue()+", memo=" + itemIds.get( i++ ) + " where nsnid=" + nsnid;
            dbAccess.executeUpdate( sql );
        }
    }

    public void saveReplenishPrize( int level, Set<Integer> results )
    {
        updateDb( level, results );
    }

    public void reset()
    {
        available = null;
        String sql = "update employees set chosen="+DrawnFlag.UNDRAWN.getValue()+",class="+PrizeLevelFlag.DEFAULT.getValue()+",memo=-1";
        dbAccess.executeUpdate(sql);
        fileAccess.deleteOutputFile();
    }

    public void cacheData()
    {
        available = dbAccess.queryData( "select * from employees where chosen="+DrawnFlag.UNDRAWN.getValue());
    }

    public Map<Integer, String> randomSelect( int count )
    {
        Map<Integer, String> selected = new HashMap<Integer, String>();
        Random random = new Random();
        int tatal = available.size();
        List<Integer> ids = new ArrayList<Integer>();
        ids.addAll( available.keySet() );
        while( selected.size() < count )
        {
            int randomIndex = random.nextInt( tatal );
            Integer select = ids.get( randomIndex );
            if( selected.get( select ) == null )
            {
                selected.put( select, available.get( select ) );
            }
        }
        return selected;
    }

    private void updateDb( int level, Set<Integer> results )
    {
        StringBuilder sqlBuilder = new StringBuilder( "update employees set chosen="+DrawnFlag.DRAWN.getValue()+", class=" );
        sqlBuilder.append( level ).append( " where nsnid in(" );
        for( Integer nsnid : results )
        {
            sqlBuilder.append( nsnid ).append( "," );
        }
        String sql = sqlBuilder.toString();
        int index = sql.lastIndexOf( "," );
        sql = sql.substring( 0, index );
        sql = sql + ")";
        dbAccess.executeUpdate( sql );
        available = null;
    }

    public int queryTotalCandidates()
    {
        String sql = "select count(*) as rowCount from employees";
        return dbAccess.queryCount( sql );
    }

    public void generateExcel()
    {
        Map<Integer, String> class4 = dbAccess.queryData( "select * from employees where class=4" );
        Map<Integer, String> class3 = dbAccess.queryData( "select * from employees where class=3");
        Map<Integer, String> class2 = dbAccess.queryData( "select * from employees where class=2");
        Map<Integer, String> class1 = dbAccess.queryData( "select * from employees where class=1");
        Map<Integer, String> class0 = dbAccess.queryData( "select * from employees where class=0");
        Map<Integer, String> class7 = dbAccess.queryConsolationData( "select * from employees where class=7");

        boolean isEmpty = true;
        if( class4.size() > 0 )
        {
            isEmpty = false;
            fileAccess.writeToExcel( "四等奖", class4, 0 );
        }
        if( class3.size() > 0 )
        {
            isEmpty = false;
            fileAccess.writeToExcel( "三等奖", class3, 3 );
        }
        if( class2.size() > 0 )
        {
            isEmpty = false;
            fileAccess.writeToExcel( "二等奖", class2, 6 );
        }
        if( class1.size() > 0 )
        {
            isEmpty = false;
            fileAccess.writeToExcel( "一等奖", class1, 9 );
        }
        if( class0.size() > 0 )
        {
            isEmpty = false;
            fileAccess.writeToExcel( "特等奖", class0, 12 );
        }
        if( class7.size() > 0 )
        {
            isEmpty = false;
            fileAccess.writeToExcel( "安慰奖", class7, 15 );
        }
        if( isEmpty )
        {
            Map<Integer, String> all = dbAccess.queryData( "select * from employees where chosen="+DrawnFlag.UNDRAWN.getValue() );
            fileAccess.writeToExcel( "总名单", all, 0 );
        }
    }

    @Override
	public void generateExcel2014(List<String[]> seatPriceList) {
    	Map<Integer, String> class8 = new HashMap<Integer, String>();
    	Map<Integer, String> class4 = dbAccess.queryData( "select * from employees where class="+PrizeLevelFlag.FORTH_PRIZE.getValue()+" and chosen="+DrawnFlag.DRAWN.getValue());
        Map<Integer, String> class3 = dbAccess.queryData( "select * from employees where class="+PrizeLevelFlag.THIRD_PRIZE.getValue()+" and chosen="+DrawnFlag.DRAWN.getValue());
        Map<Integer, String> class2 = dbAccess.queryData( "select * from employees where class="+PrizeLevelFlag.SECOND_PRIZE.getValue()+" and chosen="+DrawnFlag.DRAWN.getValue() );
        Map<Integer, String> class1 = dbAccess.queryData( "select * from employees where class="+PrizeLevelFlag.FIRST_PRIZE.getValue()+" and chosen="+DrawnFlag.DRAWN.getValue() );
        Map<Integer, String> class0 = dbAccess.queryData( "select * from employees where class="+PrizeLevelFlag.TOP_PRIZE.getValue()+" and chosen="+DrawnFlag.DRAWN.getValue() );
        Map<Integer, String> class7 = dbAccess.queryConsolationData( "select * from employees where class="+PrizeLevelFlag.CONSOLATION_PRIZE.getValue()+" and chosen="+DrawnFlag.DRAWN.getValue()+" order by memo");
        Map<Integer, String> calcelledFirstPriceMap = dbAccess.queryData( "select * from employees where class="+PrizeLevelFlag.FIRST_PRIZE.getValue()+" and chosen="+DrawnFlag.CANCELLED.getValue() );
        Map<Integer, String> calcelledTopPriceMap = dbAccess.queryData( "select * from employees where class="+PrizeLevelFlag.TOP_PRIZE.getValue()+" and chosen="+DrawnFlag.CANCELLED.getValue() );

        int i = 0;
        for (String[] tmp:seatPriceList)
        {
        	class8.put(i, tmp[0] + "---" + tmp[1]);
        	i++;
        }

        boolean isEmpty = true;
        if(class4.size() > 0)
        {
        	isEmpty = false;
            fileAccess.writeToExcel( "四等奖", class4, 3 );
        }
        if( class3.size() > 0 )
        {
            isEmpty = false;
            fileAccess.writeToExcel( "三等奖", class3, 6 );
        }
        if( class2.size() > 0 )
        {
            isEmpty = false;
            fileAccess.writeToExcel( "二等奖", class2, 9 );
        }
        if( class1.size() > 0 )
        {
        	for(Object key:calcelledFirstPriceMap.keySet()){
        		class1.put((Integer)key, calcelledFirstPriceMap.get(key)+"(被取消中奖资格)");
        	}
        	isEmpty = false;
            fileAccess.writeToExcel( "一等奖", class1, 12 );
        }
        if( class0.size() > 0 )
        {
        	for(Object key:calcelledTopPriceMap.keySet()){
        		class0.put((Integer)key, calcelledTopPriceMap.get(key)+"(被取消中奖资格)");
        	}
        	isEmpty = false;
            fileAccess.writeToExcel( "特等奖", class0, 15 );
        }
        if( class7.size() > 0 )
        {
            isEmpty = false;
            fileAccess.writeToExcel( "安慰奖", class7, 18 );
        }
        if( class8.size() > 0 )
        {
            isEmpty = false;
            fileAccess.writeToExcel( "座位惊喜奖", class8, 21 );
        }
        if( isEmpty )
        {
            Map<Integer, String> all = dbAccess.queryData( "select * from employees where chosen="+DrawnFlag.UNDRAWN.getValue() );
            fileAccess.writeToExcel( "总名单", all, 0 );
        }
        Constant.BAKE_UP_FILE_NUMBER++;
	}

    @Override
	public void cancelTheWinner(String employeeId) {
		String sql = "update employees set chosen="+DrawnFlag.CANCELLED.getValue()+",memo=-1 where nsnid="+employeeId;
    	dbAccess.executeUpdate(sql);
	}

    @Override
	public String getPresentInfoOfTheEmployee(String employeeId) {
		String presentInfo = "";
		Employee employee = dbAccess.getEmployeeById(employeeId);
		if(employee == null)
			throw new EmployeeNonExistException(Constant.MSG_EMPLOYEE_NON_EXIST);
		else{
			presentInfo = PresentFlag.valueOf(employee.getPresentFlag()).getDescription();
		}
		return presentInfo;
	}

    @Override
	public Employee getEmployeeById(String employeeId) {
		return dbAccess.getEmployeeById(employeeId);
	}

    @Override
	public List<Integer> getDrawnEndNumberList() {
		return dbAccess.getDrawnEndNumber();
	}

    public static void main( String[] args )
    {
        dbAccess = new DBAccessImpl();
        dbAccess.executeUpdate( "delete from employees" );
    }

	@Override
	public boolean isReplenishEnabled(int prizeLevel) {
		String sql = "select count(*) as rowCount from employees where chosen="+DrawnFlag.DRAWN.getValue()+" and class="+prizeLevel;
        int drawnNumber = dbAccess.queryCount( sql );
        if(prizeLevel == PrizeLevelFlag.FIRST_PRIZE.getValue()){
        	if(drawnNumber < Constant.FIRST_PRIZE_TOTAL_NUMBER)
        		return true;
        }else if(prizeLevel == PrizeLevelFlag.TOP_PRIZE.getValue()){
        	if(drawnNumber < Constant.TOP_PRIZE_TOTAL_NUMBER)
        		return true;
        }else if(prizeLevel == PrizeLevelFlag.SECOND_PRIZE.getValue()){
        	if(drawnNumber < Constant.SECOND_PRIZE_TOTAL_NUMBER)
        		return true;
        }
		return false;
	}

	@Override
	public int getDrawnNumberByPrizeLevel(int prizeLevel) {
		String sql = "select count(*) as rowCount from employees where chosen="+DrawnFlag.DRAWN.getValue()+" and class="+prizeLevel;
        return dbAccess.queryCount( sql );
	}














}
