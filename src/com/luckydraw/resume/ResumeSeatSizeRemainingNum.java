package com.luckydraw.resume;

import com.luckydraw.ui.LuckyDrawUI2014;
import com.luckydraw.util.ConfigRW;
import com.luckydraw.util.Constant;
import com.luckydraw.util.ResumeSomething;

public class ResumeSeatSizeRemainingNum extends ConfigRW implements
		ResumeSomething {
	private LuckyDrawUI2014 mainUI;

	public ResumeSeatSizeRemainingNum(LuckyDrawUI2014 mainUI) {
		super();
		this.mainUI = mainUI;
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		String tmp;

		tmp = getProperty(Constant.RESTORE_TYPE_OF_PRIZE_SEAT_REMAINING);
		if (("" == tmp)
				|| (null == tmp))
		{
			return;
		}
		/*恢复数据*/
		mainUI.setSeatPrizeDrawRemainingNum(Integer.parseInt(tmp));
	}

	@Override
	public void snapshot() {
		// TODO Auto-generated method stub
		setProperty(Constant.RESTORE_TYPE_OF_PRIZE_SEAT_REMAINING, String.valueOf(mainUI.getSeatPrizeDrawRemainingNum()));
	}

}
