package com.luckydraw.resume;

import javax.swing.JComboBox;

import com.luckydraw.util.ConfigRW;
import com.luckydraw.util.Constant;
import com.luckydraw.util.ResumeSomething;

public class ResumeJComboBox extends ConfigRW implements ResumeSomething {
	private JComboBox comboBox;
	private String enabledState;
	private String comboBoxType;

	public ResumeJComboBox(JComboBox comboBox, String comboBoxType, String enabledState) {
		super();
		this.comboBox = comboBox;
		this.enabledState = enabledState;
		this.comboBoxType = comboBoxType;
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		String tmp;

		tmp = getProperty(comboBoxType);
		if (("" == tmp)
				|| (null == tmp))
		{
			return;
		}
		/*恢复数据*/
		if (tmp.equals(Constant.JBUTTON_STATE_ENABLED))
		{
			comboBox.setEnabled(true);
		}
		else if (tmp.equals(Constant.JBUTTON_STATE_ENABLED))
		{
			comboBox.setEnabled(false);
		}
	}

	@Override
	public void snapshot() {
		// TODO Auto-generated method stub
		setProperty(comboBoxType, enabledState);
	}
}
