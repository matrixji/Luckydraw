package com.luckydraw.resume;

import com.luckydraw.ui.LuckyDrawUI2014;
import com.luckydraw.util.ConfigRW;
import com.luckydraw.util.Constant;
import com.luckydraw.util.ResumeSomething;

public class ResumeLuckyDrawUI extends ConfigRW implements ResumeSomething {
	private LuckyDrawUI2014 mainUI;
	private String prizeType;

	public ResumeLuckyDrawUI(LuckyDrawUI2014 mainUI, String prizeType) {
		super();
		this.mainUI = mainUI;
		this.prizeType = prizeType;
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		String tmp;

		tmp = getProperty(prizeType + Constant.RESTORE_TYPE_SUFFIX_MAX_COUNT);
		if (("" == tmp)
				|| (null == tmp))
		{
			return;
		}
		/*恢复数据*/
		mainUI.resumePrizeMaxCount(prizeType, Integer.parseInt(tmp));

		tmp = "";
		tmp = getProperty(prizeType + Constant.RESTORE_TYPE_SUFFIX_CURRENT_COUNT);
		if (("" == tmp)
				|| (null == tmp))
		{
			return;
		}
		/*恢复数据*/
		mainUI.resumePrizeCurrentCount(prizeType, Integer.parseInt(tmp));
	}

	@Override
	public void snapshot() {
		// TODO Auto-generated method stub
		setProperty(prizeType + Constant.RESTORE_TYPE_SUFFIX_MAX_COUNT, String.valueOf(mainUI.getPrizeMaxCount(prizeType)));
		setProperty(prizeType + Constant.RESTORE_TYPE_SUFFIX_CURRENT_COUNT, String.valueOf(mainUI.getPrizeCurrentCount(prizeType)));
	}

}
