package com.luckydraw.listener;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JTable;

import com.luckydraw.util.Constant;

public class JUnifiedBgTable extends JTable {
	private static final long serialVersionUID = 1L;

	public JUnifiedBgTable(Object[][] buildTableDataForForthPrize,
			Object[] columnNames) {
		// TODO Auto-generated constructor stub
		super(buildTableDataForForthPrize, columnNames);
	}

	public void paintComponent(Graphics g) {
		ImageIcon icon = new ImageIcon( Constant.PATH_OF_BG_PICTURE);

		g.drawImage(icon.getImage(), 0, 0, this.getSize().width,
				this.getSize().height, this);
	}
}
