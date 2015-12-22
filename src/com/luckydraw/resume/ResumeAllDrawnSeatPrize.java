package com.luckydraw.resume;

import java.util.List;

import com.luckydraw.ui.LuckyDrawUI2014;
import com.luckydraw.util.ConfigRW;
import com.luckydraw.util.Constant;
import com.luckydraw.util.ResumeSomething;

public class ResumeAllDrawnSeatPrize extends ConfigRW implements
		ResumeSomething {
	private List<String[]> allDrawnSeatPrize;

	public ResumeAllDrawnSeatPrize(List<String[]> allDrawnSeatPrize) {
		super();
		this.allDrawnSeatPrize = allDrawnSeatPrize;
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

		String tmp;

		tmp = getProperty(Constant.RESTORE_TYPE_OF_PRIZE_SEAT_ALL_DRAWN_SNAPSHOT);
		if (("" == tmp)
				|| (null == tmp))
		{
			return;
		}

		String[] strarray=tmp.split("#");
    	for (int i = 0; i < strarray.length; i++)
    	{
    		allDrawnSeatPrize.add(strarray[i].split("-"));
    	}
	}

	@Override
	public void snapshot() {
		// TODO Auto-generated method stub
		String tmp = new String("");
		for (String[] numbers:allDrawnSeatPrize)
		{
			tmp = tmp + numbers[0] + "-" + numbers[1];
			tmp = tmp + "#";
		}

		setProperty(Constant.RESTORE_TYPE_OF_PRIZE_SEAT_ALL_DRAWN_SNAPSHOT, tmp.substring(0,tmp.length()-1));
	}

}
