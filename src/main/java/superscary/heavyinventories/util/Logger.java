package superscary.heavyinventories.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import static superscary.heavyinventories.util.Constants.MODID;

/**
 * Copyright (c) 2017 SuperScary(ERBF) http://codesynced.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class Logger
{

	public static final Marker MOD_MARKER = MarkerManager.getMarker(MODID);

	private static org.apache.logging.log4j.Logger logger;

	public static void log(Level level, String format, Object... data)
	{
		logger.printf(level, format, data);
	}

	public static void log(Level level, Throwable throwable, String format, Object... data)
	{
		logger.log(level, String.format(format, data), throwable);
	}

	public static void log(Level level, Marker marker, String format, Object... data)
	{
		logger.printf(level, marker, format, data);
	}

	public static void log(Level level, Marker marker, Throwable throwable, String format, Object... data)
	{
		logger.log(level, marker, String.format(format, data), throwable);
	}

	public static void fatal(String format, Object... data)
	{
		log(Level.FATAL, format, data);
	}

	public static void fatal(Marker marker, String format, Object... data)
	{
		log(Level.FATAL, marker, format, data);
	}

	public static void fatal(Throwable throwable, String format,
			Object... data)
	{
		log(Level.FATAL, throwable, format, data);
	}

	public static void fatal(Marker marker, Throwable throwable, String format,
			Object... data)
	{
		log(Level.FATAL, marker, throwable, format, data);
	}

	public static void error(String format, Object... data)
	{
		log(Level.ERROR, format, data);
	}

	public static void error(Marker marker, String format, Object... data)
	{
		log(Level.ERROR, marker, format, data);
	}

	public static void error(Throwable throwable, String format,
			Object... data)
	{
		log(Level.ERROR, throwable, format, data);
	}

	public static void error(Marker marker, Throwable throwable, String format,
			Object... data)
	{
		log(Level.ERROR, marker, throwable, format, data);
	}

	public static void warn(String format, Object... data)
	{
		log(Level.WARN, format, data);
	}

	public static void warn(Marker marker, String format, Object... data)
	{
		log(Level.WARN, marker, format, data);
	}

	public static void info(String format, Object... data)
	{
		log(Level.INFO, format, data);
	}

	public static void info(Marker marker, String format, Object... data)
	{
		log(Level.INFO, marker, format, data);
	}

	public static void info(Throwable throwable, String format,
			Object... data)
	{
		log(Level.INFO, throwable, format, data);
	}

	public static void info(Marker marker, Throwable throwable, String format,
			Object... data)
	{
		log(Level.INFO, marker, throwable, format, data);
	}

	public static void debug(String format, Object... data)
	{
		log(Level.DEBUG, format, data);
	}

	public static void debug(Marker marker, String format, Object... data)
	{
		log(Level.DEBUG, marker, format, data);
	}

	public static void debug(Marker marker, Throwable throwable, String format,
			Object... data)
	{
		log(Level.DEBUG, marker, throwable, format, data);
	}

	public static void setLogger(org.apache.logging.log4j.Logger logger)
	{
		if (Logger.logger != null)
		{
			throw new IllegalStateException("Attempt to replace logger");
		}

		Logger.logger = logger;
	}

}
