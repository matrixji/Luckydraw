package com.luckydraw.listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import com.luckydraw.ui.LuckyDrawUI2014;
import com.luckydraw.util.Constant;

public class SeatPrizeComboBox extends JComboBox implements ItemListener {
	private static final long serialVersionUID = 1L;

	private LuckyDrawUI2014 parent;

	public SeatPrizeComboBox(LuckyDrawUI2014 parent) {
		super(Constant.COUNT_FOR_SEAT_PRIZE_DRAW);
		this.parent = parent;
		addItemListener(this);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		parent.setSeatPrizeDrawState();
	}

}
