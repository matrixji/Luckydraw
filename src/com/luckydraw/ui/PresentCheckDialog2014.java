package com.luckydraw.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.luckydraw.dto.Employee;
import com.luckydraw.enumset.DrawnFlag;
import com.luckydraw.enumset.PresentFlag;
import com.luckydraw.enumset.PrizeLevelFlag;
import com.luckydraw.exception.EmployeeNonExistException;
import com.luckydraw.logic.LuckyDraw;
import com.luckydraw.logic.LuckyDrawImpl;
import com.luckydraw.util.Constant;
import com.luckydraw.util.Util;

public class PresentCheckDialog2014 extends JDialog implements ActionListener{	
	
	private static final long serialVersionUID = 1L;
	
	private JPanel headPanel;
	private JPanel resultPanel;
	private JPanel footPanel;
	
	private JLabel employeeIdLabel;
	private JTextField employeeIdTextField;	
	private JLabel resultLabel;
		
	private JButton queryButton;
	private JButton cancelWinnerButton;	
	
	private LuckyDrawUI2014 parent;
	private int sequence = 1;
	
	private LuckyDraw luckyDrawService;
		
	public PresentCheckDialog2014(LuckyDrawUI2014 parent) {				
		super(parent,ModalityType.APPLICATION_MODAL);
		setButtonFont("微软雅黑",20);
		this.parent = parent;
		luckyDrawService = new LuckyDrawImpl();
		init();		
	}
	
	public void init(){			
		setHeadPanel();		
		setResultPanel();		
		setFootPanel();		
		addPanelsToDialog();		
		setDialog();				
	}

	private void setHeadPanel() {
		headPanel = new JPanel();
		setHeadPanelElements();
		headPanel.add(employeeIdLabel);
		headPanel.add(employeeIdTextField);
	}
	
	private void setHeadPanelElements(){
		employeeIdLabel = new JLabel("NSN ID:");
		employeeIdLabel.setForeground(Constant.FG_COLOR);
		employeeIdLabel.setFont( new Font( "Dialog", 1, 25 ) );
		employeeIdTextField = new JTextField(14);
		employeeIdTextField.setPreferredSize(new Dimension(40, 35));
		employeeIdTextField.setFont(new Font( "Dialog", 1, 25 ) );
		employeeIdTextField.setForeground(Constant.FG_COLOR);
	}
	
	private void setResultPanel() {
		resultPanel = new JPanel();
		resultPanel.setBackground(Constant.BG_COLOR);
		resultPanel.setLayout(new BorderLayout());	
		setResultPanelElements();
		resultPanel.add(resultLabel,BorderLayout.CENTER);
	}

	private void setResultPanelElements() {
		resultLabel = new JLabel("",JLabel.CENTER);	
		resultLabel.setForeground( Constant.FG_COLOR);
		resultLabel.setFont( new Font( "Dialog", 1, 70 ) );
	}
	
	private void setFootPanel() {
		footPanel = new JPanel();		
		setFootPanelElements();
		footPanel.add(queryButton);
		footPanel.add(cancelWinnerButton);				
	}

	private void setFootPanelElements() {
		queryButton = new JButton(Constant.BUTTON_TEXT_OF_PRESENT_QUERY);
		cancelWinnerButton = new JButton(Constant.BUTTON_TEXT_OF_CANCEL_WINNER);
		cancelWinnerButton.setEnabled(false);	
		queryButton.addActionListener(this);
		cancelWinnerButton.addActionListener(this);
	}
	
	private void addPanelsToDialog() {
		setLayout(new BorderLayout());
		add(headPanel,BorderLayout.NORTH);
		add(resultPanel,BorderLayout.CENTER);
		add(footPanel,BorderLayout.SOUTH);
	}
	
	private void setDialog() {		
		setSize(new Dimension( 800, 500 ));
		setTitle(Constant.TITLE_OF_PRESENT_CHECK_DIALOG);
		setLocationRelativeTo(parent);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == queryButton)
			setTheEmployPresentInfo();
		else if(e.getSource() == cancelWinnerButton){
			cancelTheWinner();
			parent.setReplenishState();
			parent.setTopPrizeButtonState();
			parent.setConsolationPrizeButtonState();			
		}
	}
	
	public void setTheEmployPresentInfo(){
		String nsnId = employeeIdTextField.getText().trim();
        if(!isNSNIDValid(nsnId)){
        	setTheShowMessage(Constant.MSG_NSN_ID_INVALID);
        	setTheButtonStateAndTooltip(false,"");
        	return;
        }
        setTheShowInfoByNSNID(nsnId);		
	}
	
	public void setTheShowInfoByNSNID(String nsnId){
		try{
			Employee employee = luckyDrawService.getEmployeeById(nsnId);
			if(employee == null){
				setTheShowMessage(Constant.MSG_EMPLOYEE_NON_EXIST);
				setTheButtonStateAndTooltip(false,"");
				return;
			}
			setTheShowMessage("<html><center>"+employee.getName()+":"+PresentFlag.valueOf(employee.getPresentFlag()).getDescription()+",<br>"+
					PresentFlag.valueOf(employee.getPresentFlag()).getNote()+"</center></html>");

			if(employee.getDrawnFlag() != DrawnFlag.DRAWN.getValue()){
				setTheButtonStateAndTooltip(false,Constant.MSG_EMPLOYEE_NOT_DRAWN);
				setTheShowMessage("<html><center>"+employee.getName()+":"+PresentFlag.valueOf(employee.getPresentFlag()).getDescription()+",<br>可我没中奖哦!</center></html>");
				return;
			}else if(employee.getPrizeLevel() == PrizeLevelFlag.SECOND_PRIZE.getValue()){
				setTheButtonStateAndTooltip(false,Constant.MSG_EMPLOYEE_NOT_DRAWN);
				setTheShowMessage("<html><center>"+employee.getName()+":"+PresentFlag.valueOf(employee.getPresentFlag()).getDescription()+",<br>可我中的是二等奖!</center></html>");
				return;
			}else if(employee.getPrizeLevel() == PrizeLevelFlag.THIRD_PRIZE.getValue()){
				setTheButtonStateAndTooltip(false,Constant.MSG_EMPLOYEE_NOT_DRAWN);
				setTheShowMessage("<html><center>"+employee.getName()+":"+PresentFlag.valueOf(employee.getPresentFlag()).getDescription()+",<br>可我中的是三等奖!</center></html>");
				return;
			}else if(employee.getPresentFlag() != PresentFlag.SHOULD_BE_PRESENT.getValue()){
				setTheButtonStateAndTooltip(false,"");
				return;
			}else{
				setTheButtonStateAndTooltip(true,"");
			}
		}catch(EmployeeNonExistException exception){
			resultLabel.setText(exception.getMessage());
			cancelWinnerButton.setEnabled(false);
		}catch(Exception exception){
			resultLabel.setText(Constant.ERROR_HINT);
			cancelWinnerButton.setEnabled(false);
		}
	}
	
	private void cancelTheWinner() {
		String nsnId = employeeIdTextField.getText().trim();
		if(!isNSNIDValid(nsnId)){
			setTheShowMessage(Constant.MSG_NSN_ID_INVALID);
			setTheButtonStateAndTooltip(false,"");
			return;
		}
		Employee employee = luckyDrawService.getEmployeeById(nsnId);
		if(employee == null){
			setTheShowMessage(Constant.MSG_EMPLOYEE_NON_EXIST);
			setTheButtonStateAndTooltip(false,"");
			return;
		}
		if(employee.getPresentFlag() != PresentFlag.SHOULD_BE_PRESENT.getValue()){
			setTheShowMessage(employee.getName()+":"+Constant.MSG_EMPLOYEE_CAN_BE_ABSENT);
			setTheButtonStateAndTooltip(false,"");
			return;
		}
		if(employee.getDrawnFlag() != DrawnFlag.DRAWN.getValue()){
			setTheShowMessage(employee.getName()+":"+Constant.MSG_EMPLOYEE_NOT_DRAWN);
			setTheButtonStateAndTooltip(false,"");
			return;
		}
		
		setOptionPaneMessageFont("宋体",25);
        setButtonFont("微软雅黑",19);
        setOptionPaneMiniSize();
        setNextSequence();
        Util.playSound(Constant.PATH_LIST_OF_SHOW_CANCEL_WINNER_SOUND[sequence]);
		int response = JOptionPane.showOptionDialog(this,Constant.TEXT_LIST_OF_SHOW_CANCEL_WINNER[sequence],"确认框",
			    JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,null,Constant.BUTTON_TEXT_LIST_OF_CONFIRM_DIALOG[sequence],null);
		
		if(JOptionPane.NO_OPTION == response){
			setTheShowMessage(Constant.TEXT_LIST_OF_CANCEL_WINNER_CANCEL[sequence]);
			Util.playSound(Constant.PATH_LIST_OF_CANCEL_WINNER_CANCEL_SOUND[sequence]);
		}
		else if(JOptionPane.OK_OPTION == response){
			cancelTheWinnerByNSNID(nsnId);
			Util.playSound(Constant.PATH_LIST_OF_CANCEL_WINNER_SUCCESS_SOUND[sequence]);
		}
	}	
	
	private void cancelTheWinnerByNSNID(String nsnId){
		try{
		   luckyDrawService.cancelTheWinner(nsnId);
	       setTheShowMessage(Constant.TEXT_LIST_OF_CANCEL_WINNER_SUCCESS[sequence]);
	       setTheButtonStateAndTooltip(false,"");	       
		}catch(Exception exception){
			resultLabel.setText(Constant.ERROR_HINT);
		}
	}
	
	private boolean isNSNIDValid(String nsnId){
		if("".equals(nsnId))
			return false;
		try{
			Integer.parseInt(nsnId);
		}catch(Exception exception){
			return false;
		}
		return true;
	}
	
	public void setTheShowMessage(String message){
		resultLabel.setText(message);
    	
	}	
	public void setTheButtonStateAndTooltip(boolean state,String message){		    	
    	cancelWinnerButton.setEnabled(state);
    	cancelWinnerButton.setToolTipText(message);
	}
	
	public void setButtonFont(String font,int size){
		Font buttonFont = new Font(font,1,size);
        UIManager.put("Button.font", buttonFont); 
	}
	
	public void setOptionPaneMessageFont(String font,int size){
		Font messageFont = new Font(font,1,size);
		UIManager.put( "OptionPane.messageFont",messageFont);
		UIManager.put( "OptionPane.messageForeground",Color.BLACK);		
	}
	
	public void setNextSequence(){
		if(sequence == Constant.TEXT_LIST_OF_SHOW_CANCEL_WINNER.length-1)
			sequence = 0;
		else
			sequence++;
		
	}
	
	public void setOptionPaneMiniSize(){
		UIManager.put( "OptionPane.minimumSize",new Dimension(480,200));
	}
	
	
}
