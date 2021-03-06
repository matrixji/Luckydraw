package com.luckydraw.util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Constant {

	public static final int FORTH_PRIZE_TOTAL_DRAWING_COUNT = 3;
	public static final int THIRD_PRIZE_TOTAL_DRAWING_COUNT = 1;
	public static final int SECOND_PRIZE_TOTAL_DRAWING_COUNT = 3;
	public static final int TOP_PRIZE_TOTAL_DRAWING_COUNT = 6;
	public static final int FIRST_PRIZE_TOTAL_DRAWING_COUNT = 6;
	public static final int CONSOLATION_PRIZE_TOTAL_DRAWING_COUNT = 8;
	public static final int SEAT_PRIZE_TOTAL_DRAWING_COUNT = 3;

	public static final int DREW_NUMBER_EVERYTIME_OF_SEAT_PRIZE_BY_TABLE_AND_SEAT = 5;
	public static final int DREW_NUMBER_EVERYTIME_OF_SEAT_PRIZE_BY_SEAT = 1;
	public static final int DREW_NUMBER_EVERYTIME_OF_SEAT_PRIZE_BY_TABLE = 1;
	public static final int SEAT_NUMBER_OF_EVERYTABLE = 5;
	public static final int SEAT_NUMBER_OF_EVERYTABLE_BASE = 5;
	public static final int DREW_NUMBER_EVERYTIME_OF_SECOND_PRIZE = 5;
	public static final int DREW_NUMBER_EVERYTIME_OF_THIRD_PRIZE = 5;
	public static final int DREW_NUMBER_EVERYTIME_OF_FIRST_PRIZE = 1;
	public static final int DREW_NUMBER_EVERYTIME_OF_TOP_PRIZE = 1;
	public static final int DREW_NUMBER_EVERYTIME_OF_REPLENISH = 1;
	public static final int DREW_NUMBER_EVERYTIME_OF_CONSOLATION_PRIZE = 10;
	public static final int DREW_NUMBER_FIRST_TIME_OF_CONSOLATION_PRIZE = 20;
	public static final int DREW_NUMBER_SECOND_TIME_OF_CONSOLATION_PRIZE = 15;
	public static final int DREW_NUMBER_THIRD_TIME_OF_CONSOLATION_PRIZE = 2;
	public static final int DREW_NUMBER_FOURTH_TIME_OF_CONSOLATION_PRIZE = 1;
	public static final int FIRST_PRIZE_TOTAL_NUMBER = FIRST_PRIZE_TOTAL_DRAWING_COUNT*DREW_NUMBER_EVERYTIME_OF_FIRST_PRIZE;
	public static final int TOP_PRIZE_TOTAL_NUMBER = TOP_PRIZE_TOTAL_DRAWING_COUNT*DREW_NUMBER_EVERYTIME_OF_TOP_PRIZE;
	public static final int SECOND_PRIZE_TOTAL_NUMBER = SECOND_PRIZE_TOTAL_DRAWING_COUNT*DREW_NUMBER_EVERYTIME_OF_SECOND_PRIZE;

	public static final int THIRD_PRIZE_SHOW_ROW = 15;
	public static final int THIRD_PRIZE_SHOW_COLUMN = 2;

	public static final int SECOND_PRIZE_SHOW_COLUMN = 3;

	public static final int FIRST_PRIZE_SHOW_ROW = 15;
	public static final int FIRST_PRIZE_SHOW_COLUMN = 2;
	public static int BAKE_UP_FILE_NUMBER = 0;

	public static final int LABEL_REFRESH_PERIOD = 30;
    public static final int TABLE_REFRESH_PERIOD = 50;

    public static final String LOTTERY_BY_TABLENUMPLUSSEATNUM = "桌号+座位号";
    public static final String LOTTERY_BY_SSEATNUM = "座位号";
    public static final String LOTTERY_BY_TABLENUM = "桌号";

    public static final String[] PRIZE_LEVEL_FOR_REPLENISH = new String[]{"一等奖", "二等奖"};

    public static final String[] COUNT_FOR_THIRD_PRIZE_DRAW = new String [] {"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};

    public static final String[] COUNT_FOR_SECOND_PRIZE_DRAW = new String [] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    public static final String[] COUNT_FOR_SEAT_PRIZE_DRAW_SEAT_NUM = new String [] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};

    public static final String[] COUNT_FOR_SEAT_PRIZE_DRAW_REMAINING = new String [] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56"};

    public static final List<Integer[]> FONT_SIZE_WITH_DIFFERENT_QUATITY_DATA = new ArrayList<Integer[]>(){

		private static final long serialVersionUID = 1L;

		{
    		add(new Integer []{400, 200});  //1
    		add(new Integer []{300, 150});  //2
    		add(new Integer []{200, 100});  //3
    		add(new Integer []{150, 80});  //4
    		add(new Integer []{120, 70});  //5
    		add(new Integer []{100, 50});  //6
    		add(new Integer []{80, 50});  //7
    		add(new Integer []{70, 50});  //8
    		add(new Integer []{60, 50});  //9
    		add(new Integer []{50, 40});  //10
    	}
    };

	public static final String ERROR_HINT = "<html><center>我都持续工作好几分钟了,<br>让我休息会儿</center></html>";
	public static final String MSG_NSN_ID_INVALID = "<html><center>您这是火星的员工号,<br>地球识别不了</center></html>";
	public static final String MSG_EMPLOYEE_NON_EXIST = "该员工还没出生";
	public static final String MSG_EMPLOYEE_NOT_DRAWN = "我本来就没中奖";
	public static final String MSG_EMPLOYEE_CAN_BE_ABSENT = "我在休假";

	public static final String TITLE_OF_APPLICATION = "Nokia 2016年会";
	public static final String TITLE_OF_PRESENT_CHECK_DIALOG = "员工到场情况查询";

	public static final String BUTTON_TEXT_OF_RESET = "初始化数据";
	public static final String BUTTON_TEXT_OF_REPLENISH = "开始补抽";
	public static final String BUTTON_TEXT_OF_PRESENT_CHECK = "到场查询";
	public static final String BUTTON_TEXT_OF_STOP_DRAWING = "停止滚动";
	public static final String BUTTON_TEXT_OF_EXPORT_TO_EXCEL = "导出Excel";
	public static final String BUTTON_TEXT_OF_PRESENT_QUERY = "查询";
	public static final String BUTTON_TEXT_OF_CANCEL_WINNER = "取消中奖";

	public static final String TOOLTIP_OF_CONSOLATION_PRIZE_BUTTON = "特等奖还没补抽完";
	public static final String TOOLTIP_OF_TOP_PRIZE_BUTTON = "一等奖还没补抽完";

	public static final String PATH_OF_BG_PICTURE = "./resources/2016.png";
	public static final String PATH_OF_DRAW_BG_PICTURE = "./resources/draw.jpg";
	public static final String PATH_OF_DB_CONFIGURATION = "./resources/config.properties";
	public static final String PATH_OF_PROGRAM_RESTORE = "./resources/restore.properties";

	//public static final Color BG_COLOR = new Color(188,210,238);
	public static final Color BG_COLOR = new Color(18,65,145);
	//public static final Color FG_COLOR = new Color(245,106,22);
	public static final Color FG_COLOR = new Color(232,180,32);
	//new Color(74,132,202)
	public static final Color HEAD_BG_COLOR = Color.RED;
	public static final Color HEAD_FG_COLOR = Color.BLACK;

	public static final String[] PATH_LIST_OF_SHOW_CANCEL_WINNER_SOUND = {
		"./resources/show.mp3",
		"./resources/show1.mp3"
	};

	public static final String[] TEXT_LIST_OF_SHOW_CANCEL_WINNER = {
		"你们忍心伤害我这么幼小的心灵吗?",
		"求你们高抬贵爪(双膝跪地)!跪求!"
	};

	public static final String[][] BUTTON_TEXT_LIST_OF_CONFIRM_DIALOG = {
		{"必须忍心，赶紧的","狠不下心啊"},
		{"坚决不抬","行吧,抬抬吧"}
	};

	public static final String[] PATH_LIST_OF_CANCEL_WINNER_SUCCESS_SOUND = {
		"./resources/success.mp3",
		"./resources/success1.mp3"
	};

	public static final String[] TEXT_LIST_OF_CANCEL_WINNER_SUCCESS = {
		"<html><center>唉,你麻麻知道<br>你这么残忍吗?</center></html>",
		"<html><center>唉,心都碎了!</center></html>"
	};

	public static final String[] PATH_LIST_OF_CANCEL_WINNER_CANCEL_SOUND = {
		"./resources/cancel.mp3",
		"./resources/cancel1.mp3"
	};

	public static final String[] TEXT_LIST_OF_CANCEL_WINNER_CANCEL = {
		"<html><center>点个赞,你麻麻知道<br>你人品这么好吗?</center></html>",
		"<html><center>哈,满血复活!</center></html>"
	};

	public static final String RESTORE_TYPE_SUFFIX_COUNT = "Count";
	public static final String RESTORE_TYPE_SUFFIX_ENABLED_STATE = "EnabledState";
	public static final String RESTORE_TYPE_SUFFIX_MAX_COUNT = "MaxCount";
	public static final String RESTORE_TYPE_SUFFIX_CURRENT_COUNT = "CurrentCount";

	public static final String JBUTTON_STATE_ENABLED = "Enabled";
	public static final String JBUTTON_STATE_DISABLED = "Disabled";

	public static final String RESTORE_TYPE_OF_RESET = "Reset";
	public static final String RESTORE_TYPE_OF_PRIZE_FIRST = "FirstPrize";
	public static final String RESTORE_TYPE_OF_PRIZE_SECOND = "SecondPrize";
	public static final String RESTORE_TYPE_OF_PRIZE_THIRD = "ThirdPrize";
	public static final String RESTORE_TYPE_OF_PRIZE_FOURTH = "FourthPrize";
	public static final String RESTORE_TYPE_OF_PRIZE_SEAT = "SeatPrize";
	public static final String RESTORE_TYPE_OF_PRIZE_SEAT_TABLE_NUM = "SeatPrizeTableNum";
	public static final String RESTORE_TYPE_OF_PRIZE_SEAT_HYBRID = "SeatPrizeHybrid";
	public static final String RESTORE_TYPE_OF_IS_SEAT_PRIZE_DRAW_BY_TABLENUM = "IsSeatPrizeDrawnByTableNum";
	public static final String RESTORE_TYPE_OF_REPLENISH_PRIZE_LEVEL = "ReplenishPrizeLevel";
	public static final String RESTORE_TYPE_OF_PRIZE_REPLENISH = "ReplenishPrize";

	public static final String RESTORE_TYPE_OF_PRIZE_FIRST_STOP = "FirstPrizeStop";
	public static final String RESTORE_TYPE_OF_PRIZE_SECOND_STOP = "SecondPrizeStop";
	public static final String RESTORE_TYPE_OF_PRIZE_THIRD_STOP = "ThirdPrizeStop";
	public static final String RESTORE_TYPE_OF_PRIZE_FOURTH_STOP = "FourthPrizeStop";
	public static final String RESTORE_TYPE_OF_PRIZE_SEAT_STOP = "SeatPrizeStop";
	public static final String RESTORE_TYPE_OF_PRIZE_REPLENISH_STOP = "ReplenishPrizeStop";

	public static final String RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON = "JButton";
	public static final String RESTORE_TYPE_OF_UI_COMPONENT_JCOMBOBOX = "JComboBox";

	public static final String RESTORE_TYPE_OF_PRIZE_SEAT_ALL_DRAWN_SNAPSHOT = "PrizeSeatAllDrawnSnapshot";
	public static final String RESTORE_TYPE_OF_PRIZE_SEAT_REMAINING = "PrizeSeatRemaining";
}
