package com.luckydraw.util;

import java.io.File;
import java.io.IOException;

import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;

import com.luckydraw.enumset.PrizeLevelFlag;

public class Util {

	public static int getPrizeLevelFromText(String text){
        if( "一等奖".equals( text ) )
        {
            return PrizeLevelFlag.FIRST_PRIZE.getValue();
        }
        else if( "特等奖".equals( text ) )
        {
            return PrizeLevelFlag.TOP_PRIZE.getValue();
        }
        else if( "二等奖".equals( text ))
        {
        	return PrizeLevelFlag.SECOND_PRIZE.getValue();
        }
        throw new RuntimeException();
	}

	public static void playSound(String filePath) {
		try {
			Player player = Manager.createPlayer(new File(filePath).toURL());
			player.prefetch();
			player.start();
		} catch (NoPlayerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
