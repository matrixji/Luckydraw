package com.luckydraw.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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
import com.luckydraw.listener.PrizeLevelComboBox;
import com.luckydraw.listener.ThirdPrizeComboBox;
import com.luckydraw.logic.LuckyDraw;
import com.luckydraw.logic.LuckyDrawImpl;
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
    private JComboBox prizeLevelComboBox;
    private JComboBox thirdPrizeCountComboBox;

    //Define result showing elements
    private JPanel contentPanel;
    private JLabel contentLabel;

    private Timer timer;

    private int thirdPrizeDrawCountMax;

    //Define variables
    private int drawnEndNumber;
    private int forthPrizeDrewCount = 0;
    private int thirdPrizeDrewCount = 0;
    private int secondPrizeDrewCount = 0;
    private int firstPrizeDrewCount = 0;
    private int topPrizeDrewCount = 0;
    private int consolationPrizeDrewCount = 0;
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
    private LuckyDraw luckyDrawService;

    public LuckyDrawUI2014()
    {
    	init();
    }

    private void init()
    {
    	thirdPrizeDrawCountMax = Constant.FORTH_PRIZE_TOTAL_DRAWING_COUNT;

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
        exportToExcelButton.setEnabled( true );
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
	}

	private void setButtonPanel() {
		buttonPanel = new JPanel( new MigLayout( "", "10[]10[]10[]10[]10[]10[]10[]10[]10[]" ) );
        buttonPanel.add( resetButton, "span 1 2, growy" );
        buttonPanel.add( forthPrizeDrawButton );
        buttonPanel.add( thirdPrizeDrawButton );
        buttonPanel.add( thirdPrizeCountComboBox, "span 1 2, growy" );
        buttonPanel.add( secondPrizeDrawButton );
        buttonPanel.add( firstPrizeDrawButton );
        //buttonPanel.add( consolationPrizeDrawButton );
        buttonPanel.add( replenishButton, "split 2" );
        buttonPanel.add( prizeLevelComboBox, "growx" );
        buttonPanel.add( exportToExcelButton, "span 1 2, growy, wrap" );

        buttonPanel.add( forthPrizeStopButton, "growx" );
        buttonPanel.add( thirdPrizeStopButton, "growx" );
        buttonPanel.add( secondPrizeStopButton, "growx" );
        buttonPanel.add( firstPrizeStopButton, "growx" );
        //buttonPanel.add( consolationPrizeStopButton, "growx" );
        buttonPanel.add( replenishStopButton, "split 2"  );
        //buttonPanel.add( presentCheckButton, "growx" );
	}

	private void setContentPanel() {
		contentPanel = new JPanel( new BorderLayout() );
        contentPanel.setBackground(Constant.BG_COLOR);
        Icon icon = new ImageIcon( Constant.PATH_OF_BG_PICTURE);
		JLabel myLabel = new JLabel( icon, JLabel.CENTER );
		contentPanel.removeAll();
		contentPanel.add( myLabel, BorderLayout.CENTER );
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
        setBackground(Constant.BG_COLOR);
	}

	private String getForthClassDrawButtonText(){
    	return "抽取四等奖("+forthPrizeDrewCount+"/"+Constant.FORTH_PRIZE_TOTAL_DRAWING_COUNT+")";
    }

	private String getThirdClassDrawButtonText(){
    	return "抽取三等奖("+thirdPrizeDrewCount+"/"+thirdPrizeDrawCountMax+")";
    }

    private String getSecondClassDrawButtonText(){
    	return "抽取二等奖("+secondPrizeDrewCount+"/"+Constant.SECOND_PRIZE_TOTAL_DRAWING_COUNT+")";
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
        else if( event.getSource() == presentCheckButton )
            showPresentCheckDialog();
        else if( event.getSource() == exportToExcelButton )
            exportToExcel();
    }

	private void reset() {
		luckyDrawService.reset();
		forthPrizeDrawButton.setEnabled( true );
		resetButton.setEnabled( false );
	}

	private void startDrawingTheForthPrize()
	{
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
        getRootPane().setDefaultButton( forthPrizeStopButton );
	}

	private void stopDrawingTheForthPrize()
	{
    	timer.stop();
    	forthPrizeDrewCount++;
        if(forthPrizeDrewCount == Constant.FORTH_PRIZE_TOTAL_DRAWING_COUNT )
        {
        	thirdPrizeDrawCountMax = Integer.parseInt((String)thirdPrizeCountComboBox.getSelectedItem());
        	thirdPrizeDrawButton.setText(getThirdClassDrawButtonText());
        	thirdPrizeDrawButton.setEnabled(true);
        	thirdPrizeCountComboBox.setEnabled( true );
        }
        else
        	forthPrizeDrawButton.setEnabled( true );
        forthPrizeDrawButton.setText( getForthClassDrawButtonText() );
        forthPrizeStopButton.setEnabled( false );
        forthPrizeEndNumbers.add(drawnEndNumber);
        luckyDrawService.saveSuffixWith( PrizeLevelFlag.FORTH_PRIZE.getValue(), drawnEndNumber);
	}

	private void startDrawingTheThirdPrize(){
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
                Object[] columnNames = {"尾号1","尾号2","尾号3"};
                JTable table = new JTable(buildTableDataForThirdPrize(),columnNames);
                paintThirdPrizeTableToUI(table);
            }
        } );
        timer.start();
        thirdPrizeDrawButton.setEnabled( false );
        thirdPrizeStopButton.setEnabled( true );
        getRootPane().setDefaultButton( thirdPrizeStopButton );
        */

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
	}

    private void stopDrawingTheThirdPrize(){
    	/*
    	timer.stop();
    	thirdPrizeDrewCount++;
        if(thirdPrizeDrewCount == thirdPrizeDrawCountMax)
        	secondPrizeDrawButton.setEnabled( true );
        else
        	thirdPrizeDrawButton.setEnabled( true );
        thirdPrizeDrawButton.setText( getThirdClassDrawButtonText() );
        thirdPrizeStopButton.setEnabled( false );
        thirdPrizeEndNumbers.add(drawnEndNumber);
        luckyDrawService.saveSuffixWith( PrizeLevelFlag.THIRD_PRIZE.getValue(), drawnEndNumber);
        */
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
		if(secondPrizeDrewCount == Constant.SECOND_PRIZE_TOTAL_DRAWING_COUNT)
		{
			firstPrizeDrawButton.setEnabled(true);
			replenishButton.setEnabled(true);
			prizeLevelComboBox.setEnabled(true);
		}
		else
			secondPrizeDrawButton.setEnabled( true );

		secondPrizeDrawButton.setText( getSecondClassDrawButtonText() );
		secondPrizeStopButton.setEnabled(false);
		allDrawnSecondPrizeNSNIds.addAll( tempResultsForEveryDrawing.keySet());
		for( Integer nsnid : tempResultsForEveryDrawing.keySet() )
		{
			allDrawnSecondPrizeCache.put( nsnid, tempResultsForEveryDrawing.get( nsnid ) );
		}
		luckyDrawService.saveWithPrizeLevelAndIdSet(PrizeLevelFlag.SECOND_PRIZE.getValue(),tempResultsForEveryDrawing.keySet() );
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
			consolationPrizeDrawButton.setEnabled( true );
			prizeLevelComboBox.setEnabled(true);
		}
		else
			firstPrizeDrawButton.setEnabled( true );
		firstPrizeDrawButton.setText(getFirstClassDrawButtonText());
		firstPrizeStopButton.setEnabled( false );
		luckyDrawService.saveWithPrizeLevelAndIdSet(PrizeLevelFlag.FIRST_PRIZE.getValue(), tempResultsForEveryDrawing.keySet());
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
		luckyDrawService.generateExcel2014();
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
			if(secondPrizeDrewCount < Constant.SECOND_PRIZE_TOTAL_DRAWING_COUNT){
				replenishButton.setEnabled(false);
				return;
			}
		}
		boolean repleishEnalbed = true;
		replenishButton.setEnabled(repleishEnalbed);
	}

    public void setThirdPrizeDrawState()
    {
    	thirdPrizeDrawCountMax = Integer.parseInt((String)thirdPrizeCountComboBox.getSelectedItem());
    	thirdPrizeDrawButton.setText(getThirdClassDrawButtonText());
    	thirdPrizeDrawButton.setEnabled(true);
    	thirdPrizeCountComboBox.setEnabled(false);
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
		Object[][] data = {{"","",""}};
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

    private void paintForthPrizeTableToUI(JTable table) {
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

    private void paintThirdPrizeTableToUI(JTable table) {
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

}
