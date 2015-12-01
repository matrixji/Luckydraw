package com.luckydraw.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config
{
    private Properties properties;

    public Properties getProperties()
    {
        if( properties == null )
        {
            try
            {
               InputStream in = new BufferedInputStream( new FileInputStream(Constant.PATH_OF_DB_CONFIGURATION) );          	
                properties = new Properties();
                properties.load( in );
            }
            catch( FileNotFoundException e )
            {
                e.printStackTrace();
            }
            catch( IOException e )
            {
                e.printStackTrace();
            }
        }

        return properties;
    }
}
