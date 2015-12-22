package com.luckydraw.resume;

import javax.swing.JButton;
import com.luckydraw.util.ConfigRW;
import com.luckydraw.util.Constant;
import com.luckydraw.util.ResumeSomething;

public class ResumeJButton extends ConfigRW implements ResumeSomething{
	private JButton button;
	private String enabledState;
	private String buttonType;

	public ResumeJButton(JButton button, String buttonType, String enabledState) {
		super();
		this.button = button;
		this.enabledState = enabledState;
		this.buttonType = buttonType;
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		String tmp;

		tmp = getProperty(buttonType);
		if (("" == tmp)
				|| (null == tmp))
		{
			return;
		}
		/*恢复数据*/
		if (tmp.equals(Constant.JBUTTON_STATE_ENABLED))
		{
			button.setEnabled(true);
		}
		else if (tmp.equals(Constant.JBUTTON_STATE_DISABLED))
		{
			button.setEnabled(false);
		}
	}

	@Override
	public void snapshot() {
		// TODO Auto-generated method stub

		setProperty(buttonType, enabledState);
	}
}
