package com.luckydraw.test;

import java.util.List;
import java.util.Map;

import com.luckydraw.dao.DBAccess;
import com.luckydraw.dao.DBAccessImpl;
import com.luckydraw.dto.Employee;
import com.luckydraw.enumset.DrawnFlag;
import com.luckydraw.enumset.PrizeLevelFlag;
import com.luckydraw.logic.LuckyDraw;
import com.luckydraw.logic.LuckyDrawImpl;
import com.luckydraw.util.Constant;
import com.luckydraw.util.Util;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
        Test test = new Test();
       // test.testCancelTheWinner();
       // test.testGetEmployInfoById();
       // test.testGetDrawnEndNumberList();
       // test.testQueryConsolationData();
       // test.testQueryDrawnCount();
        test.testPlaySound();
	}
	
	public void testGetEmployInfoById(){
		LuckyDraw luckyDrawService = new LuckyDrawImpl();
		Employee employee = luckyDrawService.getEmployeeById("61417908");
		System.out.println(employee.toString());
	}
	
	public void testCancelTheWinner(){
		LuckyDraw luckyDrawService = new LuckyDrawImpl();
		luckyDrawService.cancelTheWinner("61417908");
	}
	
	public void testGetDrawnEndNumberList(){
		LuckyDraw luckyDrawService = new LuckyDrawImpl();
		List<Integer> drawnEndNumberList = luckyDrawService.getDrawnEndNumberList();
		System.out.println("drawnEndNumberList.size = "+drawnEndNumberList.size());
		for(Integer i:drawnEndNumberList){
			System.out.println("Drawn end number:"+i);
		}
	}
	
	public void testQueryConsolationData(){
		DBAccess dbAccess = new DBAccessImpl();
		Map<Integer,String> consolationMap = dbAccess.queryConsolationData("select * from employees where class="+PrizeLevelFlag.CONSOLATION_PRIZE.getValue()+" and chosen="+DrawnFlag.DRAWN.getValue());
	    System.out.println("size="+consolationMap.size());
	}

	public void testQueryDrawnCount(){
		String sql = "select count(*) as rowCount from employees where chosen=1 and class=1";
		DBAccess dbAccess = new DBAccessImpl();
        int drawnCount = dbAccess.queryCount( sql );
        System.out.println("drawnCount="+drawnCount);
	}
	
	public void testPlaySound(){
		//Util.playSound(Constant.PATH_OF_SHOW_CANCEL_WINNER_WINDOW_SOUND);
		//Util.playSound(Constant.PATH_OF_CANCEL_WINNER_SUCCESS_SOUND);
		//Util.playSound(Constant.PATH_OF_CANCEL_WINNER_CANCEL_SOUND);
	}
}
