package superscary.supercore.tools;

import net.minecraftforge.fml.relauncher.ReflectionHelper;

import javax.annotation.Nullable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
public class ReflectionUtil
{

	@Nullable
	public static MethodHandle findMethod(final Class<?> clazz, String methodName, String obfMethodName,
			final Class<?>... parameterTypes)
	{
		final Method method = ReflectionHelper.findMethod(clazz, methodName, obfMethodName, parameterTypes);
		try
		{
			return MethodHandles.lookup().unreflect(method);
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private static String[] getFieldNameArray(final String fieldName, @Nullable final String fieldObfName)
	{
		if (fieldObfName != null)
		{
			return new String[] { fieldName, fieldObfName };
		}
		else
		{
			return new String[] { fieldName };
		}
	}

	public static MethodHandle findFieldGetter(final Class<?> clazz, final String fieldName,
			@Nullable final String fieldObfName)
	{
		final Field field = ReflectionHelper.findField(clazz, getFieldNameArray(fieldName, fieldObfName));

		try
		{
			return MethodHandles.lookup().unreflectGetter(field);
		} catch (IllegalAccessException e)
		{
			throw new ReflectionHelper.UnableToAccessFieldException(new String[0], e);
		}
	}

	public static Object getPrivateValue(Object obj, Class c, String[] fields)
	{
		for (String field : fields)
		{
			try
			{
				Field f = c.getDeclaredField(field);
				f.setAccessible(true);
				return f.get(obj);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return null;
	}

	public static void setPrivateValue(Object obj, Object value, Class c, String[] fields)
	{
		for (String field : fields)
		{
			try
			{
				Field f = c.getDeclaredField(field);
				f.setAccessible(true);
				f.set(obj, value);
			} catch (Exception e)
			{
				continue;
			}
		}
	}

	public static Method getPrivateMethod(Class c, String[] methods, Class... params)
	{
		for (String method : methods)
		{
			try
			{
				Method m = c.getDeclaredMethod(method, params);
				m.setAccessible(true);
				return m;
			} catch (Exception e)
			{
				continue;
			}
		}

		return null;
	}

}
