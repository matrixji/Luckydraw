package com.luckydraw.listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import com.luckydraw.ui.LuckyDrawUI2014;
import com.luckydraw.util.Constant;

public class ThirdPrizeComboBox extends JComboBox implements ItemListener {

	private static final long serialVersionUID = 1L;

	private LuckyDrawUI2014 parent;

	public ThirdPrizeComboBox(LuckyDrawUI2014 parent) {
		super(Constant.COUNT_FOR_THIRD_PRIZE_DRAW);
		this.parent = parent;
		addItemListener(this);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		parent.setThirdPrizeDrawState();
	}




}
