package com.luckydraw.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;

import net.miginfocom.swing.MigLayout;

import com.luckydraw.enumset.PrizeLevelFlag;
import com.luckydraw.listener.JUnifiedBgTable;
import com.luckydraw.listener.PrizeLevelComboBox;
import com.luckydraw.listener.SeatPrizeSeatNumOfSameTableComboBox;
import com.luckydraw.listener.SeatPrizeRemainingComboBox;
import com.luckydraw.listener.ThirdPrizeComboBox;
import com.luckydraw.listener.SecondPrizeComboBox;
import com.luckydraw.logic.LuckyDraw;
import com.luckydraw.logic.LuckyDrawImpl;
import com.luckydraw.resume.ResumeAllDrawnSeatPrize;
import com.luckydraw.resume.ResumeJButton;
import com.luckydraw.resume.ResumeJComboBox;
import com.luckydraw.resume.ResumeLuckyDrawUI;
import com.luckydraw.resume.ResumeSeatSizeRemainingNum;
import com.luckydraw.util.Config;
import com.luckydraw.util.Constant;
import com.luckydraw.util.Util;

public class LuckyDrawUI2014 extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;

	//Define button area elements
	private JPanel buttonPanel;
	private JButton resetButton;
	private JButton forthPrizeDrawButton;
	private JButton thirdPrizeDrawButton;
    private JButton secondPrizeDrawButton;
    private JButton firstPrizeDrawButton;
    private JButton topPrizeDrawButton;
    private JButton consolationPrizeDrawButton;
    private JButton replenishButton;
    private JButton forthPrizeStopButton;
    private JButton thirdPrizeStopButton;
    private JButton secondPrizeStopButton;
    private JButton firstPrizeStopButton;
    private JButton topPrizeStopButton;
    private JButton consolationPrizeStopButton;
    private JButton replenishStopButton;
    private JButton presentCheckButton;
    private JButton exportToExcelButton;
    private JButton seatPrizeDrawButton;
    private JButton seatPrizeStopButton;
    private JButton blankButton1;
    private JButton blankButton2;
    private JComboBox prizeLevelComboBox;
    private JComboBox thirdPrizeCountComboBox;
    private JComboBox secondPrizeCountComboBox;
    private JComboBox seatPrizeSeatNumOfSameTableCountComboBox;
    private JComboBox seatPrizeSeatRemainingCountComboBox;

    //Define result showing elements
    private JPanel contentPanel;
    private JLabel contentLabel;

    private Timer timer;

    private int thirdPrizeDrawCountMax;

    public int getThirdPrizeDrawCountMax() {
		return thirdPrizeDrawCountMax;
	}

	public void setThirdPrizeDrawCountMax(int thirdPrizeDrawCountMax) {
		this.thirdPrizeDrawCountMax = thirdPrizeDrawCountMax;
	}

	public int getSecondPrizeDrawCountMax() {
		return secondPrizeDrawCountMax;
	}

	public void setSecondPrizeDrawCountMax(int secondPrizeDrawCountMax) {
		this.secondPrizeDrawCountMax = secondPrizeDrawCountMax;
	}

	public int getSeatPrizeDrawCountMax() {
		return seatPrizeDrawCountMax;
	}

	public void setSeatPrizeDrawCountMax(int seatPrizeDrawCountMax) {
		this.seatPrizeDrawCountMax = seatPrizeDrawCountMax;
	}

	public int getForthPrizeDrewCount() {
		return forthPrizeDrewCount;
	}

	public void setForthPrizeDrewCount(int forthPrizeDrewCount) {
		this.forthPrizeDrewCount = forthPrizeDrewCount;
	}

	public int getThirdPrizeDrewCount() {
		return thirdPrizeDrewCount;
	}

	public void setThirdPrizeDrewCount(int thirdPrizeDrewCount) {
		this.thirdPrizeDrewCount = thirdPrizeDrewCount;
	}

	public int getSecondPrizeDrewCount() {
		return secondPrizeDrewCount;
	}

	public void setSecondPrizeDrewCount(int secondPrizeDrewCount) {
		this.secondPrizeDrewCount = secondPrizeDrewCount;
	}

	public int getFirstPrizeDrewCount() {
		return firstPrizeDrewCount;
	}

	public void setFirstPrizeDrewCount(int firstPrizeDrewCount) {
		this.firstPrizeDrewCount = firstPrizeDrewCount;
	}

	public int getSeatPrizeDrewCount() {
		return seatPrizeDrewCount;
	}

	public void setSeatPrizeDrewCount(int seatPrizeDrewCount) {
		this.seatPrizeDrewCount = seatPrizeDrewCount;
	}

	private int secondPrizeDrawCountMax;

    private int seatPrizeDrawCountMax;

    private String seatPrizeLotteryOption;

    //Define variables
    private int drawnEndNumber;
    private int forthPrizeDrewCount = 0;
    private int thirdPrizeDrewCount = 0;
    private int secondPrizeDrewCount = 0;
    private int firstPrizeDrewCount = 0;
    private int topPrizeDrewCount = 0;
    private int consolationPrizeDrewCount = 0;
    private int seatPrizeDrewCount = 0;
    private List<Integer> forthPrizeEndNumbers = new ArrayList<Integer>();
    private List<Integer> thirdPrizeEndNumbers = new ArrayList<Integer>();
    private List<Integer> secondPrizeEndNumbers = new ArrayList<Integer>();
    private int drewConsolaionNumber = 0;

    private Map<Integer, String> tempResultsForEveryDrawing;
    private Map<Integer, String> allDrawnFirstPrizeCache = new HashMap<Integer, String>();
    private Map<Integer, String> allDrawnSecondPrizeCache = new HashMap<Integer, String>();
    private Map<Integer, String> allDrawnThirdPrizeCache = new HashMap<Integer, String>();
    private List<Integer> allDrawnFirstPrizeNSNIds = new ArrayList<Integer>();
    private List<Integer> allDrawnSecondPrizeNSNIds = new ArrayList<Integer>();
    private List<Integer> allDrawnThirdPrizeNSNIds = new ArrayList<Integer>();
    private List<String[]> allDrawnSeatPrize = new ArrayList<String[]>();
    private List<String[]> exclusionOfSeatPrize = new ArrayList<String[]>();
    private LuckyDraw luckyDrawService;

	private List<String[]> tempResultOfSeatDraw = new ArrayList<String[]>();

	private ImageIcon bgOfPanel;

	private int seatPrizeDrawRemainingNum = 0;

    public int getSeatPrizeDrawRemainingNum() {
		return seatPrizeDrawRemainingNum;
	}

	public void setSeatPrizeDrawRemainingNum(int seatPrizeDrawRemainingNum) {
		this.seatPrizeDrawRemainingNum = seatPrizeDrawRemainingNum;
	}

	public LuckyDrawUI2014()
    {
    	init();
    }

    private void init()
    {
    	thirdPrizeDrawCountMax = Constant.THIRD_PRIZE_TOTAL_DRAWING_COUNT;
    	secondPrizeDrawCountMax = Constant.SECOND_PRIZE_TOTAL_DRAWING_COUNT;
    	seatPrizeDrawCountMax = Constant.SEAT_PRIZE_TOTAL_DRAWING_COUNT;
    	seatPrizeLotteryOption = Constant.LOTTERY_BY_TABLENUMPLUSSEATNUM;

        luckyDrawService = new LuckyDrawImpl();
//      Font buttonFont = new Font("楷体",Font.BOLD,15);
//      UIManager.put("Button.font", buttonFont);
        createButtons();
        setButtonInitialState();
        addButtonsToActionListener();
        setButtonPanel();
        setContentPanel();
        setContentLabel();
        initMainFrame();
        readExclusionOfSeatPrize();
        resumeUI();
    }

    private void readExclusionOfSeatPrize() {
		// TODO Auto-generated method stub
    	Config config = new Config();

    	String exclusion = config.getProperties().getProperty( "seatprize.exclusion" );

    	String[] strarray=exclusion.split("#");
    	for (int i = 0; i < strarray.length; i++)
    	{
    		exclusionOfSeatPrize.add(strarray[i].split("-"));
    	}
	}

    private boolean isInExclusionOfSeatPrizeForSeatPrize(String[] result) {
		for(String[] numbers:exclusionOfSeatPrize)
		{
			if (true == numbers[0].equals(result[0])
					&& ( true == numbers[1].equals(result[1])
							|| true == numbers[1].equals("ALL")))
			{
				return true;
			}
		}

		return false;
    }

	private void resumeUI() {
		// TODO Auto-generated method stub
    	/*恢复所有的按钮状态*/
    	new ResumeJButton(resetButton, Constant.RESTORE_TYPE_OF_RESET + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, "").resume();
    	new ResumeJButton(forthPrizeDrawButton, Constant.RESTORE_TYPE_OF_PRIZE_FOURTH + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, "").resume();
    	new ResumeJButton(forthPrizeStopButton, Constant.RESTORE_TYPE_OF_PRIZE_FOURTH_STOP + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, "").resume();
    	new ResumeJButton(thirdPrizeDrawButton, Constant.RESTORE_TYPE_OF_PRIZE_THIRD + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, "").resume();
    	new ResumeJButton(thirdPrizeStopButton, Constant.RESTORE_TYPE_OF_PRIZE_THIRD_STOP + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, "").resume();
    	new ResumeJButton(secondPrizeDrawButton, Constant.RESTORE_TYPE_OF_PRIZE_SECOND + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, "").resume();
    	new ResumeJButton(secondPrizeStopButton, Constant.RESTORE_TYPE_OF_PRIZE_SECOND_STOP + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, "").resume();
    	new ResumeJButton(firstPrizeDrawButton, Constant.RESTORE_TYPE_OF_PRIZE_FIRST + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, "").resume();
    	new ResumeJButton(firstPrizeStopButton, Constant.RESTORE_TYPE_OF_PRIZE_FIRST_STOP + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, "").resume();
    	new ResumeJButton(seatPrizeDrawButton, Constant.RESTORE_TYPE_OF_PRIZE_SEAT + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, "").resume();
    	new ResumeJButton(seatPrizeStopButton, Constant.RESTORE_TYPE_OF_PRIZE_SEAT_STOP + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, "").resume();
    	new ResumeJButton(replenishButton, Constant.RESTORE_TYPE_OF_PRIZE_REPLENISH + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, "").resume();
    	new ResumeJButton(replenishStopButton, Constant.RESTORE_TYPE_OF_PRIZE_REPLENISH_STOP + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, "").resume();

    	/*恢复所有的选择框*/
    	new ResumeJComboBox(prizeLevelComboBox, Constant.RESTORE_TYPE_OF_REPLENISH_PRIZE_LEVEL + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JCOMBOBOX, "").resume();
    	new ResumeJComboBox(secondPrizeCountComboBox, Constant.RESTORE_TYPE_OF_PRIZE_SECOND + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JCOMBOBOX, "").resume();
    	new ResumeJComboBox(seatPrizeSeatNumOfSameTableCountComboBox, Constant.RESTORE_TYPE_OF_PRIZE_SEAT_HYBRID + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JCOMBOBOX, "").resume();
    	new ResumeJComboBox(seatPrizeSeatRemainingCountComboBox, Constant.RESTORE_TYPE_OF_PRIZE_SEAT_TABLE_NUM + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JCOMBOBOX, "").resume();

    	/*恢复关键数据*/
    	new ResumeLuckyDrawUI(this, Constant.RESTORE_TYPE_OF_PRIZE_FOURTH).resume();
    	forthPrizeDrawButton.setText( getForthClassDrawButtonText() );
    	new ResumeLuckyDrawUI(this, Constant.RESTORE_TYPE_OF_PRIZE_THIRD).resume();
    	thirdPrizeDrawButton.setText( getThirdClassDrawButtonText() );
    	new ResumeLuckyDrawUI(this, Constant.RESTORE_TYPE_OF_PRIZE_SECOND).resume();
    	secondPrizeDrawButton.setText( getSecondClassDrawButtonText() );
    	new ResumeLuckyDrawUI(this, Constant.RESTORE_TYPE_OF_PRIZE_FIRST).resume();
    	firstPrizeDrawButton.setText( getFirstClassDrawButtonText() );
    	new ResumeLuckyDrawUI(this, Constant.RESTORE_TYPE_OF_PRIZE_SEAT).resume();
    	seatPrizeDrawButton.setText( getSeatDrawButtonText() );

    	/*恢复惊喜座位奖*/
    	new ResumeAllDrawnSeatPrize(allDrawnSeatPrize).resume();
    	new ResumeSeatSizeRemainingNum(this).resume();
	}

	private void createButtons() {
		resetButton = new JButton( Constant.BUTTON_TEXT_OF_RESET );
		forthPrizeDrawButton = new JButton(getForthClassDrawButtonText());
		thirdPrizeDrawButton = new JButton(getThirdClassDrawButtonText() );
		secondPrizeDrawButton = new JButton( getSecondClassDrawButtonText() );
		firstPrizeDrawButton = new JButton( getFirstClassDrawButtonText() );
		topPrizeDrawButton = new JButton( getSpecialDrawButtonText() );
		consolationPrizeDrawButton = new JButton( getComfortDrawButtonText() );
		forthPrizeStopButton = new JButton( getStopButtonText());
		thirdPrizeStopButton = new JButton( getStopButtonText());
        secondPrizeStopButton = new JButton( getStopButtonText());
        firstPrizeStopButton = new JButton( getStopButtonText());
        topPrizeStopButton = new JButton( getStopButtonText());
        consolationPrizeStopButton = new JButton( getStopButtonText());
        replenishStopButton = new JButton( getStopButtonText());
		replenishButton = new JButton( Constant.BUTTON_TEXT_OF_REPLENISH );
		presentCheckButton = new JButton( Constant.BUTTON_TEXT_OF_PRESENT_CHECK );
		prizeLevelComboBox = new PrizeLevelComboBox(this);
        exportToExcelButton = new JButton(Constant.BUTTON_TEXT_OF_EXPORT_TO_EXCEL);
        thirdPrizeCountComboBox = new ThirdPrizeComboBox(this);
        secondPrizeCountComboBox = new SecondPrizeComboBox(this);
        seatPrizeDrawButton = new JButton(getSeatDrawButtonText());
        seatPrizeStopButton = new JButton( getStopButtonText());
        blankButton1 = new JButton();
        blankButton2 = new JButton();
        seatPrizeSeatNumOfSameTableCountComboBox = new SeatPrizeSeatNumOfSameTableComboBox(this);
        seatPrizeSeatRemainingCountComboBox = new SeatPrizeRemainingComboBox(this);
	}

    private void setButtonInitialState() {
		resetButton.setEnabled( true );
		forthPrizeDrawButton.setEnabled( false );
		thirdPrizeDrawButton.setEnabled( false );
		secondPrizeDrawButton.setEnabled( false );
		firstPrizeDrawButton.setEnabled( false );
		topPrizeDrawButton.setEnabled( false );
		consolationPrizeDrawButton.setEnabled( false );
		replenishButton.setEnabled( false );
		forthPrizeStopButton.setEnabled( false );
		thirdPrizeStopButton.setEnabled( false );
	    secondPrizeStopButton.setEnabled( false );
	    firstPrizeStopButton.setEnabled( false );
	    topPrizeStopButton.setEnabled( false );
	    consolationPrizeStopButton.setEnabled( false );
	    replenishStopButton.setEnabled( false );
	    presentCheckButton.setEnabled(false);
		prizeLevelComboBox.setEnabled( false );
		thirdPrizeCountComboBox.setEnabled(false);
		secondPrizeCountComboBox.setEnabled(false);
        exportToExcelButton.setEnabled( true );
        blankButton1.setVisible(false);
        blankButton2.setVisible(false);
        seatPrizeDrawButton.setEnabled(false);
        seatPrizeStopButton.setEnabled(false);
		seatPrizeSeatNumOfSameTableCountComboBox.setEnabled(false);
		seatPrizeSeatRemainingCountComboBox.setEnabled(false);
	}

    private void addButtonsToActionListener() {
		resetButton.addActionListener( this );
		forthPrizeDrawButton.addActionListener( this );
		thirdPrizeDrawButton.addActionListener( this );
		secondPrizeDrawButton.addActionListener( this );
		firstPrizeDrawButton.addActionListener( this );
		topPrizeDrawButton.addActionListener( this );
		consolationPrizeDrawButton.addActionListener( this );
		replenishButton.addActionListener( this );
		forthPrizeStopButton.addActionListener( this );
		thirdPrizeStopButton.addActionListener( this );
        secondPrizeStopButton.addActionListener( this );
        firstPrizeStopButton.addActionListener( this );
        topPrizeStopButton.addActionListener( this );
        consolationPrizeStopButton.addActionListener( this );
        replenishStopButton.addActionListener( this );
        presentCheckButton.addActionListener(this);
		exportToExcelButton.addActionListener( this );
		seatPrizeStopButton.addActionListener(this);
		seatPrizeDrawButton.addActionListener(this);
		seatPrizeSeatNumOfSameTableCountComboBox.addActionListener(this);
		seatPrizeSeatRemainingCountComboBox.addActionListener(this);
	}

	private void setButtonPanel() {
		buttonPanel = new JPanel( new MigLayout( "", "10[]10[]10[]10[]10[]10[]10[]10[]10[]10[]10[]" ) );
        buttonPanel.add( resetButton, "span 1 2, growy" );
        buttonPanel.add( forthPrizeDrawButton );
        buttonPanel.add( thirdPrizeDrawButton );
        //buttonPanel.add( thirdPrizeCountComboBox, "span 1 2, growy" );
        buttonPanel.add( secondPrizeDrawButton );
        buttonPanel.add( secondPrizeCountComboBox, "span 1 2, growy" );
        buttonPanel.add(seatPrizeDrawButton);
        buttonPanel.add(seatPrizeSeatNumOfSameTableCountComboBox, "growx");
        buttonPanel.add( firstPrizeDrawButton, "wrap");
        //buttonPanel.add( consolationPrizeDrawButton );



        buttonPanel.add( forthPrizeStopButton, "growx" );
        buttonPanel.add( thirdPrizeStopButton, "growx" );
        buttonPanel.add( secondPrizeStopButton, "growx" );
        buttonPanel.add(seatPrizeStopButton, "growx");
        buttonPanel.add(seatPrizeSeatRemainingCountComboBox, "growx");
        buttonPanel.add( firstPrizeStopButton, "growx, wrap" );
        //buttonPanel.add( consolationPrizeStopButton, "growx" );
        buttonPanel.add(blankButton1, "span 1 1, growx");
        buttonPanel.add( replenishButton, "growx");
        buttonPanel.add( prizeLevelComboBox, "growx" );
        buttonPanel.add( exportToExcelButton, "span 1 2, growy, wrap" );
        buttonPanel.add(blankButton2, "span 1 1, growx");
        buttonPanel.add( replenishStopButton, "growx");
        //buttonPanel.add( presentCheckButton, "growx" );
	}

	private void setContentPanel() {
		bgOfPanel = new ImageIcon( Constant.PATH_OF_BG_PICTURE);
		contentPanel = new JPanel( new BorderLayout() ){
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				g.drawImage(bgOfPanel.getImage(), 0, 0, contentPanel.getSize().width,
						contentPanel.getSize().height, contentPanel);
			}
		};
		contentPanel.removeAll();
		contentPanel.repaint();
		contentPanel.revalidate();
	}

	private void setContentLabel() {
		contentLabel = new JLabel();
        contentLabel.setHorizontalAlignment(JLabel.CENTER );
        contentLabel.setForeground(Constant.FG_COLOR);
        contentLabel.setFont( new Font( "Dialog", 1, 250 ) );
	}

	private void initMainFrame() {
		setExtendedState( JFrame.MAXIMIZED_BOTH );
        setLayout( new BorderLayout() );
        add( buttonPanel, BorderLayout.NORTH );
        add( new JScrollPane( contentPanel ), BorderLayout.CENTER );
        setTitle(Constant.TITLE_OF_APPLICATION);
        setMinimumSize( new Dimension( 800, 600 ) );
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //setBackground(Constant.BG_COLOR);
	}

	private String getForthClassDrawButtonText(){
    	return "抽取四等奖("+forthPrizeDrewCount+"/"+Constant.FORTH_PRIZE_TOTAL_DRAWING_COUNT+")";
    }

	private String getThirdClassDrawButtonText(){
    	return "抽取三等奖("+thirdPrizeDrewCount+"/"+thirdPrizeDrawCountMax+")";
    }

    private String getSecondClassDrawButtonText(){
    	return "抽取二等奖("+secondPrizeDrewCount+"/"+secondPrizeDrawCountMax+")";
    }

    private String getFirstClassDrawButtonText(){
    	return "抽取一等奖("+firstPrizeDrewCount+"/"+Constant.FIRST_PRIZE_TOTAL_DRAWING_COUNT+")";
    }

    private String getSpecialDrawButtonText(){
    	return "抽取特等奖("+topPrizeDrewCount+"/"+Constant.TOP_PRIZE_TOTAL_DRAWING_COUNT+")";
    }

    private String getComfortDrawButtonText(){
    	return "峰回路转奖("+consolationPrizeDrewCount+"/"+Constant.CONSOLATION_PRIZE_TOTAL_DRAWING_COUNT+")";
    }

    private String getSeatDrawButtonText() {
    	return "惊喜座位奖("+seatPrizeDrewCount+"/"+seatPrizeDrawCountMax+")";
    }

    private String getStopButtonText(){
    	return Constant.BUTTON_TEXT_OF_STOP_DRAWING;
    }

	//Listener action
	public void actionPerformed( ActionEvent event )
    {
    	if( event.getSource() == resetButton )
            reset();
    	else if(event.getSource() == forthPrizeDrawButton)
    		startDrawingTheForthPrize();
    	else if(event.getSource() == forthPrizeStopButton)
    		stopDrawingTheForthPrize();
        else if(event.getSource() == thirdPrizeDrawButton)
        	startDrawingTheThirdPrize();
        else if(event.getSource() == thirdPrizeStopButton)
        	stopDrawingTheThirdPrize();
        else if(event.getSource() == secondPrizeDrawButton)
        	startDrawingTheSecondPrize();
        else if( event.getSource() == secondPrizeStopButton )
        	stopDrawingTheSecondPrize();
        else if( event.getSource() == firstPrizeDrawButton )
        	startDrawingTheFirstPrize();
        else if( event.getSource() == firstPrizeStopButton )
            stopDrawingTheFirstPrize();
        else if( event.getSource() == topPrizeDrawButton )
            startDrawingTheTopPrize();
        else if( event.getSource() == topPrizeStopButton )
            stopDrawingTheTopPrize();
        else if( event.getSource() == consolationPrizeDrawButton )
        	startDrawingTheConsolationPrize();
        else if( event.getSource() == consolationPrizeStopButton )
        	stopDrawingTheConsolationPrize();
        else if( event.getSource() == replenishButton )
            startReplenish();
        else if( event.getSource() == replenishStopButton )
            stopReplenish();
        else if( event.getSource() == seatPrizeDrawButton )
            startDrawingTheSeatPrize();
        else if( event.getSource() == seatPrizeStopButton )
        	stopDrawingTheSeatPrize();
        else if( event.getSource() == presentCheckButton )
            showPresentCheckDialog();
        else if( event.getSource() == exportToExcelButton )
            exportToExcel();
    }

	protected void putListIntoObjects(List<String[]> drawResult, Object[][] data) {
		// TODO Auto-generated method stub
		int i = 0;
		for(String[] numbers:drawResult)
		{
			data[i][0] = numbers[0] + "桌";
			data[i][1] = numbers[1] + "座";
			i++;
		}
	}

	protected List<String[]> getDrewNumberForSeatPrize(String wayOfCombination, int number) {
		String[] numbers = null;

		tempResultOfSeatDraw.clear();
		for (Integer i = 0; i < number; i++)
		{
			Random random = new Random();
			numbers = new String[]{"", ""};
			if (wayOfCombination.equals(Constant.LOTTERY_BY_TABLENUMPLUSSEATNUM))
			{
				numbers[0] = String.valueOf(random.nextInt(57));
				numbers[1] = String.valueOf(random.nextInt(11) + 1);

				while(isStringsContainInList(tempResultOfSeatDraw, numbers)
						|| isStringsContainInList(allDrawnSeatPrize, numbers)
						|| isInExclusionOfSeatPrizeForSeatPrize(numbers))
				{
					numbers[0] = String.valueOf(random.nextInt(57));
					numbers[1] = String.valueOf(random.nextInt(11) + 1);
				}
			}
			else if (wayOfCombination.equals(Constant.LOTTERY_BY_SSEATNUM))
			{
				numbers[0] = "ALL";
				numbers[1] = String.valueOf(random.nextInt(11) + 1);  //seat nubmer

				while(isStringsContainInList(tempResultOfSeatDraw, numbers)
						|| isStringsContainInList(allDrawnSeatPrize, numbers)
						|| isInExclusionOfSeatPrizeForSeatPrize(numbers))
				{
					numbers[0] = "ALL";
					numbers[1] = String.valueOf(random.nextInt(11) + 1);  //seat nubmer
				}
			}
			else if (wayOfCombination.equals(Constant.LOTTERY_BY_TABLENUM))
			{
				numbers[0] = String.valueOf(random.nextInt(57));
				numbers[1] = "ALL";  //seat nubmer

				while(isStringsContainInList(tempResultOfSeatDraw, numbers)
						|| isStringsContainInList(allDrawnSeatPrize, numbers)
						|| isInExclusionOfSeatPrizeForSeatPrize(numbers))
				{
					numbers[0] = String.valueOf(random.nextInt(57));
					numbers[1] = "ALL";  //seat nubmer
				}
			}
			tempResultOfSeatDraw.add(numbers);
			numbers = null;
		}

		return tempResultOfSeatDraw;
	}

	private void reset() {
		luckyDrawService.reset();
		forthPrizeDrawButton.setEnabled( true );
		resetButton.setEnabled( false );

		/*软件异常重启后不需要再reset，恢复所有数据*/
		new ResumeJButton(forthPrizeDrawButton, Constant.RESTORE_TYPE_OF_PRIZE_FOURTH + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_ENABLED).snapshot();
		new ResumeJButton(resetButton, Constant.RESTORE_TYPE_OF_RESET + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_DISABLED).snapshot();

		/*备份关键数据*/
		new ResumeLuckyDrawUI(this, Constant.RESTORE_TYPE_OF_PRIZE_FOURTH).snapshot();
    	new ResumeLuckyDrawUI(this, Constant.RESTORE_TYPE_OF_PRIZE_THIRD).snapshot();
    	new ResumeLuckyDrawUI(this, Constant.RESTORE_TYPE_OF_PRIZE_SECOND).snapshot();
    	new ResumeLuckyDrawUI(this, Constant.RESTORE_TYPE_OF_PRIZE_FIRST).snapshot();
    	new ResumeLuckyDrawUI(this, Constant.RESTORE_TYPE_OF_PRIZE_SEAT).snapshot();
	}

	private boolean isStringsContainInList(List<String[]> stringsList, String[] numberOfSeatPlusTable)
	{
		for(String[] numbers:stringsList)
		{
			if (true == numbers[0].equals(numberOfSeatPlusTable[0])
					&& true == numbers[1].equals(numberOfSeatPlusTable[1]))
			{
				return true;
			}
		}

		return false;
	}

	private void removePreviousBufferedData(List<String[]> stringsListDataSource, List<String[]> stringsListBufferedData)
	{
		for(String[] numbersDst:stringsListBufferedData)
		{
			stringsListDataSource.remove(numbersDst);
		}
	}

	private void startDrawingTheForthPrize()
	{
		bgOfPanel = new ImageIcon( Constant.PATH_OF_DRAW_BG_PICTURE);
		timer = new Timer( Constant.LABEL_REFRESH_PERIOD, new ActionListener()
        {
            public void actionPerformed( ActionEvent event )
            {
                Random random = new Random();
                drawnEndNumber = random.nextInt( 10 );
                while( isTheEndNumberHaveBeenDrew(drawnEndNumber) )
                {
                	drawnEndNumber = random.nextInt( 10 );
                }
                Object[] columnNames = {"尾号1","尾号2","尾号3"};
                JTable table = new JTable(buildTableDataForForthPrize(),columnNames);
                paintForthPrizeTableToUI(table);
            }
        } );
        timer.start();
        forthPrizeDrawButton.setEnabled( false );
        forthPrizeStopButton.setEnabled( true );
        /*备份前面的状态*/
        new ResumeJButton(forthPrizeDrawButton, Constant.RESTORE_TYPE_OF_PRIZE_FOURTH + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_ENABLED).snapshot();
        new ResumeJButton(forthPrizeStopButton, Constant.RESTORE_TYPE_OF_PRIZE_FOURTH_STOP + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_DISABLED).snapshot();
        getRootPane().setDefaultButton( forthPrizeStopButton );
	}

	private void stopDrawingTheForthPrize()
	{
    	timer.stop();
    	forthPrizeDrewCount++;
        if(forthPrizeDrewCount == Constant.FORTH_PRIZE_TOTAL_DRAWING_COUNT )
        {
        	thirdPrizeDrawButton.setText(getThirdClassDrawButtonText());
        	thirdPrizeDrawButton.setEnabled(true);
        	thirdPrizeCountComboBox.setEnabled( true );
        	new ResumeJButton(forthPrizeDrawButton, Constant.RESTORE_TYPE_OF_PRIZE_FOURTH + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_DISABLED).snapshot();
        	new ResumeJButton(thirdPrizeDrawButton, Constant.RESTORE_TYPE_OF_PRIZE_THIRD + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_ENABLED).snapshot();
        }
        else
        {
        	forthPrizeDrawButton.setEnabled( true );
        }
        forthPrizeDrawButton.setText( getForthClassDrawButtonText() );
        forthPrizeStopButton.setEnabled( false );
        forthPrizeEndNumbers.add(drawnEndNumber);
        luckyDrawService.saveSuffixWith( PrizeLevelFlag.FORTH_PRIZE.getValue(), drawnEndNumber);
        new ResumeLuckyDrawUI(this, Constant.RESTORE_TYPE_OF_PRIZE_FOURTH).snapshot();
	}

	private void startDrawingTheThirdPrize(){
		bgOfPanel = new ImageIcon( Constant.PATH_OF_DRAW_BG_PICTURE);

		timer = new Timer( Constant.LABEL_REFRESH_PERIOD, new ActionListener()
        {
            public void actionPerformed( ActionEvent event )
            {
                Random random = new Random();
                drawnEndNumber = random.nextInt( 10 );
                while( isTheEndNumberHaveBeenDrew(drawnEndNumber) )
                {
                	drawnEndNumber = random.nextInt( 10 );
                }
                Object[] columnNames = {"尾号1"};
                JTable table = new JTable(buildTableDataForThirdPrize(),columnNames);
                paintThirdPrizeTableToUI(table);
            }
        } );
        timer.start();
        thirdPrizeDrawButton.setEnabled( false );
        thirdPrizeStopButton.setEnabled( true );
        /*备份前面的状态*/
        new ResumeJButton(thirdPrizeDrawButton, Constant.RESTORE_TYPE_OF_PRIZE_THIRD + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_ENABLED).snapshot();
        new ResumeJButton(thirdPrizeStopButton, Constant.RESTORE_TYPE_OF_PRIZE_THIRD_STOP + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_DISABLED).snapshot();
        getRootPane().setDefaultButton( thirdPrizeStopButton );

		/*
		luckyDrawService.cacheData();
		timer = new Timer( Constant.TABLE_REFRESH_PERIOD, new ActionListener()
		{
		    public void actionPerformed( ActionEvent event )
		    {
		    	tempResultsForEveryDrawing = luckyDrawService.randomSelect( Constant.DREW_NUMBER_EVERYTIME_OF_THIRD_PRIZE);
		        Object[][] data = buildTableDataForThirdPrizeForConcreteDrawer();
		        String[] columnNames = { "NSN ID", "Name", "NSN ID", "Name"};
		        JTable table = new JTable( data, columnNames );
		        paintFirstPrizeTableToUI( table );
		    }
		} );
		timer.start();
		thirdPrizeDrawButton.setEnabled( false );
		thirdPrizeStopButton.setEnabled( true );
		thirdPrizeCountComboBox.setEnabled( false );
		getRootPane().setDefaultButton( thirdPrizeStopButton );
		*/
	}

    private void stopDrawingTheThirdPrize(){

    	timer.stop();
    	thirdPrizeDrewCount++;
        if(thirdPrizeDrewCount == thirdPrizeDrawCountMax)
        {
        	secondPrizeDrawCountMax = Integer.parseInt((String)secondPrizeCountComboBox.getSelectedItem());
        	secondPrizeDrawButton.setText(getSecondClassDrawButtonText());
	    	secondPrizeDrawButton.setEnabled(true);
	    	secondPrizeCountComboBox.setEnabled( true );
	    	new ResumeJComboBox(secondPrizeCountComboBox, Constant.RESTORE_TYPE_OF_PRIZE_SECOND + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JCOMBOBOX, Constant.JBUTTON_STATE_ENABLED).snapshot();
	    	new ResumeJButton(thirdPrizeDrawButton, Constant.RESTORE_TYPE_OF_PRIZE_THIRD + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_DISABLED).snapshot();
	    	new ResumeJButton(secondPrizeDrawButton, Constant.RESTORE_TYPE_OF_PRIZE_SECOND + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_ENABLED).snapshot();
        }
        else
        {
        	thirdPrizeDrawButton.setEnabled( true );
        }
        thirdPrizeDrawButton.setText( getThirdClassDrawButtonText() );
        thirdPrizeStopButton.setEnabled( false );
        thirdPrizeEndNumbers.add(drawnEndNumber);
        luckyDrawService.saveSuffixWith( PrizeLevelFlag.THIRD_PRIZE.getValue(), drawnEndNumber);
        new ResumeLuckyDrawUI(this, Constant.RESTORE_TYPE_OF_PRIZE_THIRD).snapshot();
    	/*
    	timer.stop();
    	thirdPrizeDrewCount++;
		if(thirdPrizeDrewCount == thirdPrizeDrawCountMax)
		{
			secondPrizeDrawButton.setEnabled(true);
		}
		else
			thirdPrizeDrawButton.setEnabled( true );

		thirdPrizeDrawButton.setText( getThirdClassDrawButtonText() );
		thirdPrizeStopButton.setEnabled(false);
		allDrawnThirdPrizeNSNIds.addAll( tempResultsForEveryDrawing.keySet());
		for( Integer nsnid : tempResultsForEveryDrawing.keySet() )
		{
			allDrawnThirdPrizeCache.put( nsnid, tempResultsForEveryDrawing.get( nsnid ) );
		}
		luckyDrawService.saveWithPrizeLevelAndIdSet(PrizeLevelFlag.THIRD_PRIZE.getValue(),tempResultsForEveryDrawing.keySet() );
		*/
    }

    private void startDrawingTheSecondPrize(){
    	/*
        timer = new Timer( Constant.LABEL_REFRESH_PERIOD, new ActionListener()
        {
            public void actionPerformed( ActionEvent event )
            {
                Random random = new Random();
                drawnEndNumber = random.nextInt( 10 );
                while( isTheEndNumberHaveBeenDrew(drawnEndNumber) )
                {
                	drawnEndNumber = random.nextInt( 10 );
                }
                Object[] columnNames = {"尾号1","尾号2"};
                JTable table = new JTable(buildTableDataForSecondPrize(),columnNames);
                paintSecondPrizeTableToUI(table);
            }
        } );
        timer.start();
        secondPrizeDrawButton.setEnabled( false );
        secondPrizeStopButton.setEnabled( true );
        getRootPane().setDefaultButton( secondPrizeStopButton );
        */
    	/*
    	secondPrizeCountComboBox.setEnabled(false);
    	luckyDrawService.cacheData();
		timer = new Timer( Constant.TABLE_REFRESH_PERIOD, new ActionListener()
		{
		    public void actionPerformed( ActionEvent event )
		    {
		    	tempResultsForEveryDrawing = luckyDrawService.randomSelect( Constant.DREW_NUMBER_EVERYTIME_OF_SECOND_PRIZE);
		        Object[][] data = buildTableDataForSecondPrizeForConcreteDrawer();
		        String[] columnNames = { "NSN ID", "Name", "NSN ID", "Name", "NSN ID", "Name"};
		        JTable table = new JTable( data, columnNames );
		        paintFirstPrizeTableToUI( table );
		    }
		} );
		timer.start();
		secondPrizeDrawButton.setEnabled( false );
		secondPrizeStopButton.setEnabled( true );
		getRootPane().setDefaultButton( secondPrizeStopButton );
		*/

    	bgOfPanel = new ImageIcon( Constant.PATH_OF_DRAW_BG_PICTURE);
    	secondPrizeCountComboBox.setEnabled(false);
    	new ResumeJComboBox(secondPrizeCountComboBox, Constant.RESTORE_TYPE_OF_PRIZE_SECOND + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JCOMBOBOX, Constant.JBUTTON_STATE_DISABLED).snapshot();
    	refreshContentPanel();
		luckyDrawService.cacheData();
		timer = new Timer( Constant.LABEL_REFRESH_PERIOD, new ActionListener()
		{
		    public void actionPerformed(ActionEvent event)
		    {
		    	String result = new String("");
		    	tempResultsForEveryDrawing = luckyDrawService.randomSelect( Constant.DREW_NUMBER_EVERYTIME_OF_SECOND_PRIZE);
		    	result = "<html><table border='0'cellspacing=\"0\" cellpadding=\"0\" >";
		        for( Integer nsnid : tempResultsForEveryDrawing.keySet() )
		        {
		        	result = result + "<tr align=\"center\"><td width=\"450\">" + nsnid
		        		+ "</td><td width=\"450\">" + tempResultsForEveryDrawing.get( nsnid ) + "</td></tr>";
		        }

		        result = result + "</table></html>";

		        contentLabel.setFont( new Font( "Dialog", 1, 80 ) );
		        contentLabel.setText(result);
		    }
		} );
		timer.start();
		secondPrizeDrawButton.setEnabled( false );
		secondPrizeStopButton.setEnabled( true );
		prizeLevelComboBox.setEnabled(false);
		getRootPane().setDefaultButton( secondPrizeStopButton );
		new ResumeJButton(secondPrizeDrawButton, Constant.RESTORE_TYPE_OF_PRIZE_SECOND + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_ENABLED).snapshot();
    	new ResumeJButton(secondPrizeStopButton, Constant.RESTORE_TYPE_OF_PRIZE_SECOND_STOP + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_DISABLED).snapshot();
    }

    private void stopDrawingTheSecondPrize(){
    	/*
    	timer.stop();
    	secondPrizeDrewCount++;
        if(secondPrizeDrewCount == Constant.SECOND_PRIZE_TOTAL_DRAWING_COUNT )
        	firstPrizeDrawButton.setEnabled( true );
        else
        	secondPrizeDrawButton.setEnabled( true );
        secondPrizeDrawButton.setText( getSecondClassDrawButtonText() );
        secondPrizeStopButton.setEnabled( false );
        secondPrizeEndNumbers.add(drawnEndNumber);
        luckyDrawService.saveSuffixWith( PrizeLevelFlag.SECOND_PRIZE.getValue(), drawnEndNumber);
        */

    	timer.stop();
    	secondPrizeDrewCount++;
		if(secondPrizeDrewCount == secondPrizeDrawCountMax)
		{
			seatPrizeDrawRemainingNum  = Integer.parseInt((String)seatPrizeSeatRemainingCountComboBox.getSelectedItem());
			seatPrizeDrawCountMax = Integer.parseInt((String)seatPrizeSeatNumOfSameTableCountComboBox.getSelectedItem()) + (int)Math.ceil((double)seatPrizeDrawRemainingNum / (int)Constant.SEAT_NUMBER_OF_EVERYTABLE);
			seatPrizeDrawButton.setText( getSeatDrawButtonText() );
			seatPrizeDrawButton.setEnabled(true);
			seatPrizeSeatNumOfSameTableCountComboBox.setEnabled(true);
			seatPrizeSeatRemainingCountComboBox.setEnabled(true);
			replenishButton.setEnabled( true );
			prizeLevelComboBox.setEnabled(true);

			new ResumeJButton(secondPrizeDrawButton, Constant.RESTORE_TYPE_OF_PRIZE_SECOND + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_DISABLED).snapshot();
			new ResumeJButton(seatPrizeDrawButton, Constant.RESTORE_TYPE_OF_PRIZE_SEAT + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_ENABLED).snapshot();
			new ResumeJComboBox(prizeLevelComboBox, Constant.RESTORE_TYPE_OF_REPLENISH_PRIZE_LEVEL + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JCOMBOBOX, Constant.JBUTTON_STATE_ENABLED).snapshot();
			new ResumeJComboBox(seatPrizeSeatNumOfSameTableCountComboBox, Constant.RESTORE_TYPE_OF_PRIZE_SEAT_HYBRID + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JCOMBOBOX, Constant.JBUTTON_STATE_ENABLED).snapshot();
	    	new ResumeJComboBox(seatPrizeSeatRemainingCountComboBox, Constant.RESTORE_TYPE_OF_PRIZE_SEAT_TABLE_NUM + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JCOMBOBOX, Constant.JBUTTON_STATE_ENABLED).snapshot();
	    	new ResumeJButton(replenishButton, Constant.RESTORE_TYPE_OF_PRIZE_REPLENISH + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_ENABLED).snapshot();
	    	new ResumeSeatSizeRemainingNum(this).snapshot();
		}
		else
		{
			secondPrizeDrawButton.setEnabled( true );
		}

		secondPrizeDrawButton.setText( getSecondClassDrawButtonText() );
		secondPrizeStopButton.setEnabled(false);
		allDrawnSecondPrizeNSNIds.addAll( tempResultsForEveryDrawing.keySet());
		for( Integer nsnid : tempResultsForEveryDrawing.keySet() )
		{
			allDrawnSecondPrizeCache.put( nsnid, tempResultsForEveryDrawing.get( nsnid ) );
		}
		luckyDrawService.saveWithPrizeLevelAndIdSet(PrizeLevelFlag.SECOND_PRIZE.getValue(),tempResultsForEveryDrawing.keySet() );
		new ResumeLuckyDrawUI(this, Constant.RESTORE_TYPE_OF_PRIZE_SECOND).snapshot();
    }

	private void startDrawingTheSeatPrize() {
		bgOfPanel = new ImageIcon( Constant.PATH_OF_DRAW_BG_PICTURE);
		replenishButton.setEnabled( false );
		prizeLevelComboBox.setEnabled(false);
		seatPrizeSeatRemainingCountComboBox.setEnabled(false);
		seatPrizeSeatNumOfSameTableCountComboBox.setEnabled(false);
		new ResumeJComboBox(prizeLevelComboBox, Constant.RESTORE_TYPE_OF_REPLENISH_PRIZE_LEVEL + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JCOMBOBOX, Constant.JBUTTON_STATE_DISABLED).snapshot();
		new ResumeJComboBox(seatPrizeSeatNumOfSameTableCountComboBox, Constant.RESTORE_TYPE_OF_PRIZE_SEAT_HYBRID + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JCOMBOBOX, Constant.JBUTTON_STATE_DISABLED).snapshot();
    	new ResumeJComboBox(seatPrizeSeatRemainingCountComboBox, Constant.RESTORE_TYPE_OF_PRIZE_SEAT_TABLE_NUM + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JCOMBOBOX, Constant.JBUTTON_STATE_DISABLED).snapshot();
    	new ResumeJButton(replenishButton, Constant.RESTORE_TYPE_OF_PRIZE_REPLENISH + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_DISABLED).snapshot();

		timer = new Timer( Constant.LABEL_REFRESH_PERIOD, new ActionListener()
        {
            public void actionPerformed( ActionEvent event )
            {
            	List<String[]> drawResult = null;
            	int number = 0;
            	int lastTimeNumber = seatPrizeDrawRemainingNum % Constant.SEAT_NUMBER_OF_EVERYTABLE;

            	removePreviousBufferedData(allDrawnSeatPrize, tempResultOfSeatDraw);

            	if (seatPrizeDrewCount >= (seatPrizeDrawCountMax - Math.ceil((double)seatPrizeDrawRemainingNum / (int)Constant.SEAT_NUMBER_OF_EVERYTABLE)))
            	{
	        		number = Constant.SEAT_NUMBER_OF_EVERYTABLE;
	        		if ((seatPrizeDrewCount == (seatPrizeDrawCountMax - 1))
            				&& (0 != lastTimeNumber))
    				{
	        			number = lastTimeNumber;
    				}
	        		seatPrizeLotteryOption = Constant.LOTTERY_BY_TABLENUMPLUSSEATNUM;
            	}
            	else
            	{
            		number = Constant.DREW_NUMBER_EVERYTIME_OF_SEAT_PRIZE_BY_TABLE;
            		seatPrizeLotteryOption = Constant.LOTTERY_BY_SSEATNUM;
            	}
            	Object[][] data = new Object[number][2];

            	drawResult = getDrewNumberForSeatPrize(seatPrizeLotteryOption, number);

            	putListIntoObjects(drawResult, data);

            	Object[] columnNames = {"桌号","座位号"};

            	JTable table = new JTable(data,columnNames);

            	paintSeatPrizeTableToUI(table, seatPrizeLotteryOption, number);

            	allDrawnSeatPrize.addAll(tempResultOfSeatDraw);
            	new ResumeAllDrawnSeatPrize(allDrawnSeatPrize).snapshot();
            }
        } );
		tempResultOfSeatDraw.clear(); /*Make sure the temporary data is saved.*/
        timer.start();
        seatPrizeDrawButton.setEnabled( false );
        seatPrizeStopButton.setEnabled( true );
        new ResumeJButton(seatPrizeDrawButton, Constant.RESTORE_TYPE_OF_PRIZE_SEAT + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_ENABLED).snapshot();
    	new ResumeJButton(seatPrizeStopButton, Constant.RESTORE_TYPE_OF_PRIZE_SEAT_STOP + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_DISABLED).snapshot();
    	getRootPane().setDefaultButton( seatPrizeStopButton );
    	new ResumeLuckyDrawUI(this, Constant.RESTORE_TYPE_OF_PRIZE_SEAT).snapshot();
    	new ResumeSeatSizeRemainingNum(this).snapshot();
	}

	private void stopDrawingTheSeatPrize() {
		// TODO Auto-generated method stub
    	timer.stop();

    	new ResumeJComboBox(seatPrizeSeatNumOfSameTableCountComboBox, Constant.RESTORE_TYPE_OF_PRIZE_SEAT_HYBRID + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JCOMBOBOX, Constant.JBUTTON_STATE_DISABLED).snapshot();
    	seatPrizeDrewCount++;

        if(seatPrizeDrewCount == seatPrizeDrawCountMax )
        {
        	firstPrizeDrawButton.setEnabled(true);
        	new ResumeJButton(seatPrizeDrawButton, Constant.RESTORE_TYPE_OF_PRIZE_SEAT + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_DISABLED).snapshot();
        	new ResumeJButton(firstPrizeDrawButton, Constant.RESTORE_TYPE_OF_PRIZE_FIRST + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_ENABLED).snapshot();
        }
        else
        {
        	seatPrizeDrawButton.setEnabled( true );
        }
        seatPrizeDrawButton.setText( getSeatDrawButtonText() );
        seatPrizeStopButton.setEnabled( false );
        new ResumeLuckyDrawUI(this, Constant.RESTORE_TYPE_OF_PRIZE_SEAT).snapshot();
        new ResumeSeatSizeRemainingNum(this).snapshot();
	}

    private void startDrawingTheFirstPrize() {
    	/*
		luckyDrawService.cacheData();
		timer = new Timer( Constant.TABLE_REFRESH_PERIOD, new ActionListener()
		{
		    public void actionPerformed( ActionEvent event )
		    {
		    	tempResultsForEveryDrawing = luckyDrawService.randomSelect( Constant.DREW_NUMBER_EVERYTIME_OF_FIRST_PRIZE);
		        Object[][] data = buildTableDataForFirstPrize();
		        String[] columnNames = { "NSN ID", "Name", "NSN ID", "Name"};
		        JTable table = new JTable( data, columnNames );
		        paintFirstPrizeTableToUI( table );
		    }
		} );
		timer.start();
		firstPrizeDrawButton.setEnabled( false );
		firstPrizeStopButton.setEnabled( true );
		getRootPane().setDefaultButton( firstPrizeStopButton );
		*/

    	bgOfPanel = new ImageIcon( Constant.PATH_OF_DRAW_BG_PICTURE);
    	replenishButton.setEnabled( false );
		prizeLevelComboBox.setEnabled(false);
    	refreshContentPanel();
		luckyDrawService.cacheData();
		timer = new Timer( Constant.LABEL_REFRESH_PERIOD, new ActionListener()
		{
		    public void actionPerformed(ActionEvent event)
		    {
		    	tempResultsForEveryDrawing = luckyDrawService.randomSelect( Constant.DREW_NUMBER_EVERYTIME_OF_TOP_PRIZE);
		        for( Integer nsnid : tempResultsForEveryDrawing.keySet() )
		        {
		            contentLabel.setFont( new Font( "Dialog", 1, 170 ) );
		            contentLabel.setText( "<html><center>" + nsnid + "<br>" + tempResultsForEveryDrawing.get( nsnid ) + "</center></html>" );
		        }
		    }
		} );
		timer.start();
		firstPrizeDrawButton.setEnabled( false );
		firstPrizeStopButton.setEnabled( true );
		prizeLevelComboBox.setEnabled(false);
		new ResumeJButton(firstPrizeDrawButton, Constant.RESTORE_TYPE_OF_PRIZE_FIRST + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_ENABLED).snapshot();
    	new ResumeJButton(firstPrizeStopButton, Constant.RESTORE_TYPE_OF_PRIZE_FIRST_STOP + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_DISABLED).snapshot();
		getRootPane().setDefaultButton( firstPrizeStopButton );
	}


	private void stopDrawingTheFirstPrize() {
		/*
		timer.stop();
		firstPrizeDrewCount++;
		if(firstPrizeDrewCount == Constant.FIRST_PRIZE_TOTAL_DRAWING_COUNT)
		{
			topPrizeDrawButton.setEnabled(true);
			prizeLevelComboBox.setEnabled(true);
			presentCheckButton.setEnabled(true);
		}
		else
			firstPrizeDrawButton.setEnabled( true );

		firstPrizeDrawButton.setText( getFirstClassDrawButtonText() );
		firstPrizeStopButton.setEnabled(false);
		allDrawnFirstPrizeNSNIds.addAll( tempResultsForEveryDrawing.keySet());
		for( Integer nsnid : tempResultsForEveryDrawing.keySet() )
		{
			allDrawnFirstPrizeCache.put( nsnid, tempResultsForEveryDrawing.get( nsnid ) );
		}
		luckyDrawService.saveWithPrizeLevelAndIdSet(PrizeLevelFlag.FIRST_PRIZE.getValue(),tempResultsForEveryDrawing.keySet() );
		*/
		timer.stop();
		firstPrizeDrewCount++;
		if( firstPrizeDrewCount == Constant.FIRST_PRIZE_TOTAL_DRAWING_COUNT){
			replenishButton.setEnabled( true );
			prizeLevelComboBox.setEnabled(true);
			new ResumeJButton(firstPrizeDrawButton, Constant.RESTORE_TYPE_OF_PRIZE_FIRST + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_DISABLED).snapshot();
			new ResumeJButton(replenishButton, Constant.RESTORE_TYPE_OF_PRIZE_REPLENISH + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JBUTTON, Constant.JBUTTON_STATE_ENABLED).snapshot();
			new ResumeJComboBox(prizeLevelComboBox, Constant.RESTORE_TYPE_OF_REPLENISH_PRIZE_LEVEL + Constant.RESTORE_TYPE_OF_UI_COMPONENT_JCOMBOBOX, Constant.JBUTTON_STATE_ENABLED).snapshot();
		}
		else
		{
			firstPrizeDrawButton.setEnabled( true );
		}
		firstPrizeDrawButton.setText(getFirstClassDrawButtonText());
		firstPrizeStopButton.setEnabled( false );
		luckyDrawService.saveWithPrizeLevelAndIdSet(PrizeLevelFlag.FIRST_PRIZE.getValue(), tempResultsForEveryDrawing.keySet());
		new ResumeLuckyDrawUI(this, Constant.RESTORE_TYPE_OF_PRIZE_FIRST).snapshot();
	}

	private void startDrawingTheTopPrize() {
		refreshContentPanel();
		luckyDrawService.cacheData();
		timer = new Timer( Constant.LABEL_REFRESH_PERIOD, new ActionListener()
		{
		    public void actionPerformed(ActionEvent event)
		    {
		    	tempResultsForEveryDrawing = luckyDrawService.randomSelect( Constant.DREW_NUMBER_EVERYTIME_OF_TOP_PRIZE);
		        for( Integer nsnid : tempResultsForEveryDrawing.keySet() )
		        {
		            contentLabel.setFont( new Font( "Dialog", 1, 170 ) );
		            contentLabel.setText( "<html><center>" + nsnid + "<br>" + tempResultsForEveryDrawing.get( nsnid ) + "</center></html>" );
		        }
		    }
		} );
		timer.start();
		topPrizeDrawButton.setEnabled( false );
		topPrizeStopButton.setEnabled( true );
		presentCheckButton.setEnabled(false);
		prizeLevelComboBox.setEnabled(false);
		getRootPane().setDefaultButton( topPrizeStopButton );
	}

	private void stopDrawingTheTopPrize() {
		timer.stop();
		topPrizeDrewCount++;
		if( topPrizeDrewCount == Constant.TOP_PRIZE_TOTAL_DRAWING_COUNT){
			consolationPrizeDrawButton.setEnabled( true );
			presentCheckButton.setEnabled(true);
			prizeLevelComboBox.setEnabled(true);
		}
		else
			topPrizeDrawButton.setEnabled( true );
		topPrizeDrawButton.setText(getSpecialDrawButtonText());
		topPrizeStopButton.setEnabled( false );
		luckyDrawService.saveWithPrizeLevelAndIdSet(PrizeLevelFlag.TOP_PRIZE.getValue(), tempResultsForEveryDrawing.keySet());
	}

	private void startDrawingTheConsolationPrize(){
		luckyDrawService.cacheData();
		drewConsolaionNumber = luckyDrawService.getDrawnNumberByPrizeLevel(PrizeLevelFlag.CONSOLATION_PRIZE.getValue());
		timer = new Timer( Constant.TABLE_REFRESH_PERIOD, new ActionListener()
		{
		    public void actionPerformed( ActionEvent event )
		    {
		        int count = Constant.DREW_NUMBER_EVERYTIME_OF_CONSOLATION_PRIZE;
		        if(consolationPrizeDrewCount == 0){
		        	tempResultsForEveryDrawing = luckyDrawService.randomSelect(20);
		        	String[] columnNames = { "Item No.", "NSN ID", "Name","Item No.", "NSN ID", "Name" };
		        	Object[][] data = buildTableDataForFirstDrawingOfConsolationPrize();
		        	JTable table = new JTable( data, columnNames );
		        	paintMultipleColumnsConsolationPrizeTableToUI(table);
		        }else if(consolationPrizeDrewCount >=5){
		        	tempResultsForEveryDrawing = luckyDrawService.randomSelect(1);
		        	String[] columnNames = { "Item No.", "NSN ID", "Name"};
		        	Object[][] data = buildTableDataForMultipleColumnsOfConsolationPrize();
		        	JTable table = new JTable( data, columnNames );
		        	paintSingleColumnConsolationPrizeTableToUI(table);
		        }else{
		        	if(consolationPrizeDrewCount == 1 || consolationPrizeDrewCount == 2)
		        	   count = 6;
		        	else if(consolationPrizeDrewCount == 3)
		        	   count = 10;
		        	else if(consolationPrizeDrewCount == 4)
		        	   count = 5;
		        	tempResultsForEveryDrawing = luckyDrawService.randomSelect(count);
		        	String[] columnNames = { "Item No.", "NSN ID", "Name"};
		        	Object[][] data = buildTableDataForSingleColumnOfConsolationPrize();
		        	JTable table = new JTable( data, columnNames );
		        	paintSingleDrawingConsolationPrizeTableToUI(table);
		        }
		    }
		} );
		timer.start();
		consolationPrizeDrawButton.setEnabled( false );
		consolationPrizeStopButton.setEnabled( true );
		prizeLevelComboBox.setEnabled(false);
		presentCheckButton.setEnabled(false);
		getRootPane().setDefaultButton( consolationPrizeStopButton );
	}

	private void stopDrawingTheConsolationPrize() {
		timer.stop();
		List<Integer> itemIds = new ArrayList<Integer>();
		for( int i = 0; i < tempResultsForEveryDrawing.size(); i++)
		{
		    itemIds.add( drewConsolaionNumber + i + 1 );
		}
		consolationPrizeDrewCount++;
		if( consolationPrizeDrewCount < Constant.CONSOLATION_PRIZE_TOTAL_DRAWING_COUNT )
		{
			consolationPrizeDrawButton.setEnabled( true );
		}
		consolationPrizeDrawButton.setText(getComfortDrawButtonText());
		consolationPrizeStopButton.setEnabled( false );
		luckyDrawService.saveConsolationPrize( tempResultsForEveryDrawing.keySet(), itemIds );
	}

	private void startReplenish() {
		bgOfPanel = new ImageIcon( Constant.PATH_OF_DRAW_BG_PICTURE);
		refreshContentPanel();
		luckyDrawService.cacheData();
		timer = new Timer( Constant.LABEL_REFRESH_PERIOD, new ActionListener()
		{
		    public void actionPerformed( ActionEvent event )
		    {
		    	tempResultsForEveryDrawing = luckyDrawService.randomSelect(Constant.DREW_NUMBER_EVERYTIME_OF_REPLENISH);
		        for( Integer nsnid : tempResultsForEveryDrawing.keySet() )
		        {
		            contentLabel.setFont( new Font( "Dialog", 1, 170 ) );
		            contentLabel.setText( "<html>" + nsnid + "<br>" + tempResultsForEveryDrawing.get( nsnid ) + "</html>" );
		        }
		    }
		} );
		timer.start();
        replenishButton.setEnabled( false );
		replenishStopButton.setEnabled( true );
		getRootPane().setDefaultButton( replenishStopButton );
	}

	private void stopReplenish(){
		timer.stop();
		prizeLevelComboBox.setEnabled( true );
		replenishStopButton.setEnabled( false );
		String prizeLevelText = ( String ) prizeLevelComboBox.getSelectedItem();
		luckyDrawService.saveReplenishPrize(Util.getPrizeLevelFromText(prizeLevelText), tempResultsForEveryDrawing.keySet() );
		setReplenishState();
		setTopPrizeButtonState();
		setConsolationPrizeButtonState();
	}

	private void showPresentCheckDialog(){
		new PresentCheckDialog2014(this);
	}

	private void exportToExcel() {
//		for(String[] numbers : allDrawnSeatPrize)
//		{
//			System.out.println(numbers[0] + "-----" + numbers[1]);
//		}
		luckyDrawService.generateExcel2014(allDrawnSeatPrize);
	}

	//Common methods
	private boolean isTheEndNumberHaveBeenDrew(int endNumber){
		List<Integer> drawnEndNumberList = luckyDrawService.getDrawnEndNumberList();
    	for(Integer drewEndNumber:drawnEndNumberList){
    		if(endNumber == drewEndNumber.intValue()){
    			return true;
    		}
    	}
    	return false;
    }

    public void setReplenishState(){
    	String prizeLevelText = ( String ) prizeLevelComboBox.getSelectedItem();
		int prizeLevel = Util.getPrizeLevelFromText(prizeLevelText);
		if(prizeLevel == PrizeLevelFlag.FIRST_PRIZE.getValue()){
			if(firstPrizeDrewCount < Constant.FIRST_PRIZE_TOTAL_DRAWING_COUNT){
				replenishButton.setEnabled(false);
				return;
			}
		}else if(prizeLevel == PrizeLevelFlag.TOP_PRIZE.getValue()){
			if(topPrizeDrewCount < Constant.TOP_PRIZE_TOTAL_DRAWING_COUNT){
				replenishButton.setEnabled(false);
				return;
			}
		} else if(prizeLevel == PrizeLevelFlag.SECOND_PRIZE.getValue()){
			if(secondPrizeDrewCount < secondPrizeDrawCountMax){
				replenishButton.setEnabled(false);
				return;
			}
		}
		boolean repleishEnalbed = true;
		replenishButton.setEnabled(repleishEnalbed);
	}

    public void setThirdPrizeDrawState()
    {
    	//thirdPrizeDrawCountMax = Integer.parseInt((String)thirdPrizeCountComboBox.getSelectedItem());
    	thirdPrizeDrawButton.setText(getThirdClassDrawButtonText());
    	thirdPrizeDrawButton.setEnabled(true);
    	thirdPrizeCountComboBox.setEnabled(false);
    }

    public void setSecondPrizeDrawState()
    {
    	secondPrizeDrawCountMax = Integer.parseInt((String)secondPrizeCountComboBox.getSelectedItem());
    	secondPrizeDrawButton.setText(getSecondClassDrawButtonText());
    	secondPrizeDrawButton.setEnabled(true);
    	//secondPrizeCountComboBox.setEnabled(false);
    }

    public void setDrawnSeatNumOfSameTable()
    {
    	seatPrizeDrawCountMax = Integer.parseInt((String)seatPrizeSeatNumOfSameTableCountComboBox.getSelectedItem()) + (int)Math.ceil((double)seatPrizeDrawRemainingNum / (int)Constant.SEAT_NUMBER_OF_EVERYTABLE);
    	seatPrizeDrawButton.setText(getSeatDrawButtonText());
    	seatPrizeDrawButton.setEnabled(true);
    }

    public void setSeatPrizeRemainingNum()
    {
    	seatPrizeDrawRemainingNum  = Integer.parseInt((String)seatPrizeSeatRemainingCountComboBox.getSelectedItem());
    	seatPrizeDrawCountMax = Integer.parseInt((String)seatPrizeSeatNumOfSameTableCountComboBox.getSelectedItem()) + (int)Math.ceil((double)seatPrizeDrawRemainingNum / (int)Constant.SEAT_NUMBER_OF_EVERYTABLE);
    	seatPrizeDrawButton.setText(getSeatDrawButtonText());
    	seatPrizeDrawButton.setEnabled(true);
    	//seatPrizeDrawButton.setText(getSeatDrawButtonText());
    	//seatPrizeLotteryOption = (String)seatPrizeTableCountComboBox.getSelectedItem();
    }

    public void setTopPrizeButtonState(){
    	if(topPrizeDrewCount < Constant.TOP_PRIZE_TOTAL_DRAWING_COUNT){
	    	boolean isFirstPrizeLeft = luckyDrawService.isReplenishEnabled(PrizeLevelFlag.FIRST_PRIZE.getValue());
	        topPrizeDrawButton.setEnabled(!isFirstPrizeLeft);
	        if(isFirstPrizeLeft)
	        	topPrizeDrawButton.setToolTipText(Constant.TOOLTIP_OF_TOP_PRIZE_BUTTON);
	        else
	        	topPrizeDrawButton.setToolTipText("");
    	}
    }

    public void setConsolationPrizeButtonState(){
		boolean isTopPrizeLeft = luckyDrawService.isReplenishEnabled(PrizeLevelFlag.TOP_PRIZE.getValue());
        consolationPrizeDrawButton.setEnabled(!isTopPrizeLeft);
        if(isTopPrizeLeft)
        	consolationPrizeDrawButton.setToolTipText(Constant.TOOLTIP_OF_CONSOLATION_PRIZE_BUTTON);
        else
        	consolationPrizeDrawButton.setToolTipText("");
	}

    private void refreshContentPanel()
    {
        contentPanel.removeAll();
        contentLabel.setOpaque(false);
        contentPanel.add( contentLabel, BorderLayout.CENTER );
        contentPanel.repaint();
        contentPanel.revalidate();
    }

    private Object[][] buildTableDataForForthPrize(){
		Object[][] data = {{"","",""}};
		List<Integer> temp = new ArrayList<Integer>();
		temp.addAll(forthPrizeEndNumbers);
		temp.add(drawnEndNumber);
		for(int i=0;i<temp.size();i++){
			data[0][i] = temp.get(i);
		}
		return data;
	}

    private Object[][] buildTableDataForThirdPrize(){
		Object[][] data = {{""}};
		List<Integer> temp = new ArrayList<Integer>();
		temp.addAll(thirdPrizeEndNumbers);
		temp.add(drawnEndNumber);
		for(int i=0;i<temp.size();i++){
			data[0][i] = temp.get(i);
		}
		return data;
	}

    private Object[][] buildTableDataForSecondPrize(){
		Object[][] data = {{"",""}};
		List<Integer> temp = new ArrayList<Integer>();
		temp.addAll(secondPrizeEndNumbers);
		temp.add(drawnEndNumber);
		for(int i=0;i<temp.size();i++){
			data[0][i] = temp.get(i);
		}
		return data;
	}

    private Object[][] buildTableDataForThirdPrizeForConcreteDrawer()
    {
        Object[][] data = new Object[Constant.DREW_NUMBER_EVERYTIME_OF_THIRD_PRIZE * 2 + 1][2*Constant.THIRD_PRIZE_SHOW_COLUMN];
        List<Integer> nsnids = new ArrayList<Integer>();
        nsnids.addAll( allDrawnThirdPrizeNSNIds );
        nsnids.addAll( tempResultsForEveryDrawing.keySet() );
        int k = 0;
        while (true)
        {
	        for( int j : new int[]{ 0, 2} )
	        {
	            for( int i = 0; i < Constant.DREW_NUMBER_EVERYTIME_OF_THIRD_PRIZE; i++ )
	            {
	                if( k < allDrawnThirdPrizeCache.size() )
	                {
	                    data[i][j] = nsnids.get( k++ );
	                    data[i][j + 1] = allDrawnThirdPrizeCache.get( data[i][j] );
	                }
	                else if( allDrawnThirdPrizeCache.size() <= k && k < allDrawnThirdPrizeCache.size() + tempResultsForEveryDrawing.size() )
	                {
	                    data[i][j] = nsnids.get( k++ );
	                    data[i][j + 1] = tempResultsForEveryDrawing.get( data[i][j] );
	                }
	                else
	                {
	                    data[i][j] = "";
	                    data[i][j + 1] = "";
	                }
	            }
	        }

	        for(int j=0;j<3;j++){
	        	data[Constant.DREW_NUMBER_EVERYTIME_OF_THIRD_PRIZE][j] = "";
	        }

	        for( int j : new int[]{ 0, 2} )
	        {
	            for( int i = Constant.DREW_NUMBER_EVERYTIME_OF_THIRD_PRIZE + 1; i < (Constant.DREW_NUMBER_EVERYTIME_OF_THIRD_PRIZE * 2 + 1); i++ )
	            {
	                if( k < allDrawnThirdPrizeCache.size() )
	                {
	                    data[i][j] = nsnids.get( k++ );
	                    data[i][j + 1] = allDrawnThirdPrizeCache.get( data[i][j] );
	                }
	                else if( allDrawnThirdPrizeCache.size() <= k && k < allDrawnThirdPrizeCache.size() + tempResultsForEveryDrawing.size() )
	                {
	                    data[i][j] = nsnids.get( k++ );
	                    data[i][j + 1] = tempResultsForEveryDrawing.get( data[i][j] );
	                }
	                else
	                {
	                    data[i][j] = "";
	                    data[i][j + 1] = "";
	                }
	            }
	        }

	        /*显示下一页*/
	        if ((allDrawnThirdPrizeCache.size() + tempResultsForEveryDrawing.size()) <= (k + 1))
	        {
	        	break;
	        }
        }

        return data;
    }

    private Object[][] buildTableDataForSecondPrizeForConcreteDrawer()
    {
        Object[][] data = new Object[Constant.DREW_NUMBER_EVERYTIME_OF_SECOND_PRIZE][2*Constant.SECOND_PRIZE_SHOW_COLUMN];
        List<Integer> nsnids = new ArrayList<Integer>();
        nsnids.addAll( allDrawnSecondPrizeNSNIds );
        nsnids.addAll( tempResultsForEveryDrawing.keySet() );
        int k = 0;
        while (true)
        {
	        for( int j : new int[]{ 0, 2, 4} )
	        {
	            for( int i = 0; i < Constant.DREW_NUMBER_EVERYTIME_OF_SECOND_PRIZE; i++ )
	            {
	                if( k < allDrawnSecondPrizeCache.size() )
	                {
	                    data[i][j] = nsnids.get( k++ );
	                    data[i][j + 1] = allDrawnSecondPrizeCache.get( data[i][j] );
	                }
	                else if( allDrawnSecondPrizeCache.size() <= k && k < allDrawnSecondPrizeCache.size() + tempResultsForEveryDrawing.size() )
	                {
	                    data[i][j] = nsnids.get( k++ );
	                    data[i][j + 1] = tempResultsForEveryDrawing.get( data[i][j] );
	                }
	                else
	                {
	                    data[i][j] = "";
	                    data[i][j + 1] = "";
	                }
	            }
	        }

	        /*显示下一页*/
	        if ((allDrawnSecondPrizeCache.size() + tempResultsForEveryDrawing.size()) <= (k + 1))
	        {
	        	break;
	        }
        }

        return data;
    }

    private Object[][] buildTableDataForFirstPrize()
    {
        Object[][] data = new Object[Constant.FIRST_PRIZE_SHOW_ROW][2*Constant.FIRST_PRIZE_SHOW_COLUMN];
        List<Integer> nsnids = new ArrayList<Integer>();
        nsnids.addAll( allDrawnFirstPrizeNSNIds );
        nsnids.addAll( tempResultsForEveryDrawing.keySet() );
        int k = 0;
        for( int j : new int[]{ 0, 2} )
        {
            for( int i = 0; i < 7; i++ )
            {
                if( k < allDrawnFirstPrizeCache.size() )
                {
                    data[i][j] = nsnids.get( k++ );
                    data[i][j + 1] = allDrawnFirstPrizeCache.get( data[i][j] );
                }
                else if( allDrawnFirstPrizeCache.size() <= k && k < allDrawnFirstPrizeCache.size() + tempResultsForEveryDrawing.size() )
                {
                    data[i][j] = nsnids.get( k++ );
                    data[i][j + 1] = tempResultsForEveryDrawing.get( data[i][j] );
                }
                else
                {
                    data[i][j] = "";
                    data[i][j + 1] = "";
                }
            }
        }

        for(int j=0;j<3;j++){
        	data[7][j] = "";
        }

        for( int j : new int[]{ 0, 2} )
        {
            for( int i = 8; i < Constant.FIRST_PRIZE_SHOW_ROW; i++ )
            {
                if( k < allDrawnFirstPrizeCache.size() )
                {
                    data[i][j] = nsnids.get( k++ );
                    data[i][j + 1] = allDrawnFirstPrizeCache.get( data[i][j] );
                }
                else if( allDrawnFirstPrizeCache.size() <= k && k < allDrawnFirstPrizeCache.size() + tempResultsForEveryDrawing.size() )
                {
                    data[i][j] = nsnids.get( k++ );
                    data[i][j + 1] = tempResultsForEveryDrawing.get( data[i][j] );
                }
                else
                {
                    data[i][j] = "";
                    data[i][j + 1] = "";
                }
            }
        }
        return data;
    }

    private Object[][] buildTableDataForFirstDrawingOfConsolationPrize()
    {
       Object[][] data = new Object[10][6];
       int m = 0;
       for( Integer nsnid : tempResultsForEveryDrawing.keySet() )
       {
            if(m<10){
        		data[m][0] = m + 1;
                data[m][1] = nsnid;
                data[m][2] = tempResultsForEveryDrawing.get( nsnid );

            }else if(m<20){
            	data[m-10][3] = m + 1;
                data[m-10][4] = nsnid;
                data[m-10][5] = tempResultsForEveryDrawing.get( nsnid );
            }
            m++;
        }
        return data;
    }

    private Object[][] buildTableDataForMultipleColumnsOfConsolationPrize()
    {
    	Object[][] data = new Object[ tempResultsForEveryDrawing.size()][ 6 ];
    	int i = 0;
        for(Integer nsnid : tempResultsForEveryDrawing.keySet() )
        {
            data[i][0] = drewConsolaionNumber + i + 1;
            data[i][1] = nsnid;
            data[i][2] = tempResultsForEveryDrawing.get( nsnid );
            i++;
        }
        return data;
    }

    private Object[][] buildTableDataForSingleColumnOfConsolationPrize()
    {
    	int rows = tempResultsForEveryDrawing.size();
    	Object[][] data = new Object[rows][3];
        int i = 0;
        for(Integer nsnid : tempResultsForEveryDrawing.keySet() )
        {
            data[i][0] = drewConsolaionNumber + i + 1;
            data[i][1] = nsnid;
            data[i][2] = tempResultsForEveryDrawing.get( nsnid );
            i++;
        }
        return data;
    }

    private void paintSeatPrizeTableToUI(JTable table, String wayOfCombination, int number) {
//    	int scale = Constant.SEAT_NUMBER_OF_EVERYTABLE / number;
//    	int baseScale = Constant.SEAT_NUMBER_OF_EVERYTABLE_BASE / Constant.SEAT_NUMBER_OF_EVERYTABLE;
    	if (wayOfCombination.equals(Constant.LOTTERY_BY_TABLENUMPLUSSEATNUM))
		{
    		table.setRowHeight( Constant.FONT_SIZE_WITH_DIFFERENT_QUATITY_DATA.get(number - 1)[0] );
    		table.setFont( new Font( "Dialog", Font.BOLD, Constant.FONT_SIZE_WITH_DIFFERENT_QUATITY_DATA.get(number - 1)[1] ) );
		}
		else if ((wayOfCombination.equals(Constant.LOTTERY_BY_SSEATNUM))
				|| (wayOfCombination.equals(Constant.LOTTERY_BY_TABLENUM)))
		{
			table.setRowHeight( 400 );
    		table.setFont( new Font( "Dialog", Font.BOLD, 200 ) );
		}
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        table.setOpaque(false);
        render.setOpaque(false);
        render.setHorizontalAlignment( JLabel.CENTER );
        render.setForeground(Constant.FG_COLOR);
        render.setBackground(Constant.BG_COLOR);
        table.setDefaultRenderer( Object.class, render );
        table.setShowGrid(false);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.enable( false );
        table.setBackground(Constant.BG_COLOR);
        contentPanel.removeAll();
        contentPanel.add( table, BorderLayout.CENTER );
        contentPanel.repaint();
        contentPanel.revalidate();
        contentPanel.setBackground(Constant.BG_COLOR);
	}

    private void paintForthPrizeTableToUI(JTable table) {
		table.setRowHeight( 625 );
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        table.setOpaque(false);
        render.setOpaque(false);
        render.setHorizontalAlignment( JLabel.CENTER );
        render.setForeground(Constant.FG_COLOR);
        //render.setBackground(Constant.BG_COLOR);
        table.setDefaultRenderer( Object.class, render );
        table.setFont( new Font( "Dialog", Font.BOLD, 500 ) );
        table.setShowGrid(false);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.enable( false );
        //table.setBackground(Constant.BG_COLOR);
        contentPanel.removeAll();
        contentPanel.add( table, BorderLayout.CENTER );
        contentPanel.repaint();
        contentPanel.revalidate();
        //contentPanel.setBackground(Constant.BG_COLOR);
	}

    private void paintThirdPrizeTableToUI(JTable table) {
		table.setRowHeight( 625 );
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        table.setOpaque(false);
        render.setOpaque(false);
        render.setHorizontalAlignment( JLabel.CENTER );
        render.setForeground(Constant.FG_COLOR);
        table.setDefaultRenderer( Object.class, render );
        table.setFont( new Font( "Dialog", Font.BOLD, 500 ) );
        table.setShowGrid(false);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.enable( false );
        contentPanel.removeAll();
        contentPanel.add( table, BorderLayout.CENTER );
        contentPanel.repaint();
        contentPanel.revalidate();
        contentPanel.setBackground(Constant.BG_COLOR);
	}

    private void paintSecondPrizeTableToUI(JTable table) {
		table.setRowHeight( 625 );
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment( JLabel.CENTER );
        render.setForeground(Constant.FG_COLOR);
        render.setBackground(Constant.BG_COLOR);
        table.setDefaultRenderer( Object.class, render );
        table.setFont( new Font( "Dialog", Font.BOLD, 500 ) );
        table.enable( false );
        table.setBackground(Constant.BG_COLOR);
        contentPanel.removeAll();
        contentPanel.add( table, BorderLayout.CENTER );
        contentPanel.repaint();
        contentPanel.revalidate();
        contentPanel.setBackground(Constant.BG_COLOR);
	}


    private void paintFirstPrizeTableToUI( JTable table )
    {
        table.setRowHeight( 42 );
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment( JLabel.CENTER );
        render.setForeground( Constant.FG_COLOR);
        render.setBackground( Constant.BG_COLOR );
        table.setDefaultRenderer( Object.class, render );
        table.getTableHeader().setFont( new Font( "Dialog", Font.BOLD, 38 ) );
        table.getTableHeader().setForeground(Constant.HEAD_FG_COLOR);
        table.getTableHeader().setBackground( Constant.HEAD_BG_COLOR);
        table.setFont( new Font( "Dialog", Font.BOLD, 32 ) );
        table.setEnabled(false);
        table.setBackground(Constant.BG_COLOR);
        contentPanel.removeAll();
        //contentPanel.add( table.getTableHeader(), BorderLayout.NORTH );
        contentPanel.add( table, BorderLayout.CENTER );
        contentPanel.repaint();
        contentPanel.revalidate();
        contentPanel.setBackground(Constant.BG_COLOR);
    }

    private void paintMultipleColumnsConsolationPrizeTableToUI( JTable table )
    {
        table.setRowHeight( 62 );
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment( JLabel.CENTER );
        render.setForeground(Constant.FG_COLOR);
        render.setBackground(Constant.BG_COLOR);
        table.setDefaultRenderer( Object.class, render );
        table.getTableHeader().setFont( new Font( "Dialog", Font.BOLD, 50 ) );
        table.getTableHeader().setForeground(Constant.HEAD_FG_COLOR);
        table.getTableHeader().setBackground(Constant.HEAD_BG_COLOR);
        table.setFont( new Font( "Dialog", Font.BOLD, 50 ) );
        table.getColumnModel().getColumn(0).setMaxWidth(100);
        table.getColumnModel().getColumn(0).setMinWidth(100);
        table.getColumnModel().getColumn(3).setMaxWidth(100);
        table.getColumnModel().getColumn(3).setMinWidth(100);
        table.enable( false );
        table.setBackground(Constant.BG_COLOR);
        contentPanel.removeAll();
        //contentPanel.add( table.getTableHeader(), BorderLayout.NORTH );
        contentPanel.add( table, BorderLayout.CENTER );
        contentPanel.repaint();
        contentPanel.revalidate();
        contentPanel.setBackground(Constant.BG_COLOR);
    }

    private void paintSingleColumnConsolationPrizeTableToUI( JTable table )
    {
        table.setRowHeight( 620 );
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment( JLabel.CENTER );
        render.setForeground(Constant.FG_COLOR);
        render.setBackground(Constant.BG_COLOR);
        table.setDefaultRenderer( Object.class, render );
        table.getTableHeader().setFont( new Font( "Dialog", Font.BOLD, 50 ) );
        table.getTableHeader().setForeground(Constant.HEAD_FG_COLOR);
        table.getTableHeader().setBackground(Constant.HEAD_BG_COLOR);
        table.setFont( new Font( "Dialog", Font.BOLD, 110 ) );
        table.getColumnModel().getColumn(0).setMaxWidth(200);
        table.getColumnModel().getColumn(0).setMinWidth(200);
        table.enable( false );
        table.setBackground(Constant.BG_COLOR);
        contentPanel.removeAll();
        //contentPanel.add( table.getTableHeader(), BorderLayout.NORTH );
        contentPanel.add( table, BorderLayout.CENTER );
        contentPanel.repaint();
        contentPanel.revalidate();
        contentPanel.setBackground(Constant.BG_COLOR);
    }

    private void paintSingleDrawingConsolationPrizeTableToUI(JTable table)
    {
        int rowHeight = 630/tempResultsForEveryDrawing.size();
        int fontSize = rowHeight>100?rowHeight-40:rowHeight-15;
    	table.setRowHeight( rowHeight);
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment( JLabel.CENTER );
        render.setForeground(Constant.FG_COLOR);
        render.setBackground(Constant.BG_COLOR);
        table.setDefaultRenderer( Object.class, render );
        table.getTableHeader().setFont( new Font( "Dialog", Font.BOLD, 50 ) );
        table.getTableHeader().setForeground(Constant.HEAD_FG_COLOR);
        table.getTableHeader().setBackground(Constant.HEAD_BG_COLOR);
        table.setFont( new Font( "Dialog", Font.BOLD, fontSize) );
        table.getColumnModel().getColumn(0).setMaxWidth(200);
        table.getColumnModel().getColumn(0).setMinWidth(200);
        table.enable( false );
        table.setBackground(Constant.BG_COLOR);
        contentPanel.removeAll();
        //contentPanel.add( table.getTableHeader(), BorderLayout.NORTH );
        contentPanel.add( table, BorderLayout.CENTER );
        contentPanel.repaint();
        contentPanel.revalidate();
        contentPanel.setBackground(Constant.BG_COLOR);
    }

    public static void main( String[] args )
    {
        SwingUtilities.invokeLater( new Runnable()
        {
            public void run()
            {
                new LuckyDrawUI2014().setVisible( true );
            }
        } );
    }

	public void resumePrizeMaxCount(String prizeType, int maxCount) {
		// TODO Auto-generated method stub
		if (Constant.RESTORE_TYPE_OF_PRIZE_THIRD == prizeType)
		{
			thirdPrizeDrawCountMax = maxCount;
		}
		else if (Constant.RESTORE_TYPE_OF_PRIZE_SECOND == prizeType)
		{
			secondPrizeDrawCountMax = maxCount;
		}
		else if (Constant.RESTORE_TYPE_OF_PRIZE_SEAT == prizeType)
		{
			seatPrizeDrawCountMax = maxCount;
		}
	}

	public void resumePrizeCurrentCount(String prizeType, int currentCount) {
		// TODO Auto-generated method stub
		if (Constant.RESTORE_TYPE_OF_PRIZE_FOURTH == prizeType)
		{
			forthPrizeDrewCount = currentCount;
		}
		else if (Constant.RESTORE_TYPE_OF_PRIZE_THIRD == prizeType)
		{
			thirdPrizeDrewCount = currentCount;
		}
		else if (Constant.RESTORE_TYPE_OF_PRIZE_SECOND == prizeType)
		{
			secondPrizeDrewCount = currentCount;
		}
		else if (Constant.RESTORE_TYPE_OF_PRIZE_FIRST == prizeType)
		{
			firstPrizeDrewCount = currentCount;
		}
		else if (Constant.RESTORE_TYPE_OF_PRIZE_SEAT == prizeType)
		{
			seatPrizeDrewCount = currentCount;
		}
	}

	public int getPrizeMaxCount(String prizeType) {
		// TODO Auto-generated method stub
		if (Constant.RESTORE_TYPE_OF_PRIZE_THIRD == prizeType)
		{
			return thirdPrizeDrawCountMax;
		}
		else if (Constant.RESTORE_TYPE_OF_PRIZE_SECOND == prizeType)
		{
			return secondPrizeDrawCountMax;
		}
		else if (Constant.RESTORE_TYPE_OF_PRIZE_SEAT == prizeType)
		{
			return seatPrizeDrawCountMax;
		}

		return 0;
	}

	public int getPrizeCurrentCount(String prizeType) {
		// TODO Auto-generated method stub
		if (Constant.RESTORE_TYPE_OF_PRIZE_FOURTH == prizeType)
		{
			return forthPrizeDrewCount;
		}
		else if (Constant.RESTORE_TYPE_OF_PRIZE_THIRD == prizeType)
		{
			return thirdPrizeDrewCount;
		}
		else if (Constant.RESTORE_TYPE_OF_PRIZE_SECOND == prizeType)
		{
			return secondPrizeDrewCount;
		}
		else if (Constant.RESTORE_TYPE_OF_PRIZE_FIRST == prizeType)
		{
			return firstPrizeDrewCount;
		}
		else if (Constant.RESTORE_TYPE_OF_PRIZE_SEAT == prizeType)
		{
			return seatPrizeDrewCount;
		}

		return 0;
	}

}
