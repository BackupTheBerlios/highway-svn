/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.legacy;

import org.highway.init.InitException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Base class for all converters configuration. This object enables to associate
 * <code>BasicConverter</code> or <code>AnnotationConverter</code> to a
 * logical name or simply to a java type. <br>
 * This class must be implemented:
 * 
 * <pre>
 *       public class ConverterMap4Testing extends ConverterMap
 *       {
 *         public static BasicConverter STRING_40 = new StringConverter(40, &quot; &quot;.charAt(0), StringConverter.LEGACY_PADDING_RIGHT);
 *                                                               
 *         public static BasicConverter STRING_32 = new StringConverter(32, &quot; &quot;.charAt(0), StringConverter.LEGACY_PADDING_RIGHT);
 *                                                                 
 *         ...
 *                                                                 
 *         public static BasicConverter DATE = new DateConverter(&quot;yyyyMMdd&quot;);
 *         //
 *         public static AnnotationConverter ANNOTATION_STRING = new StringConverter();
 *                                                                 
 *         public ConverterMap4Testing(){
 *           super();
 *           // 
 *           super.setDefaultAnnotationBasedConverter(int.class, new IntegerConverter());
 *           super.setDefaultAnnotationBasedConverter(Integer.class, new IntegerConverter());
 *           super.setDefaultAnnotationBasedConverter(String.class, new StringConverter());
 *         }
 * </pre>
 * 
 * During construction phase, all the public static attributes will be parsed
 * and attribute name will be associated to the declared instance. for instance,
 * a <code>BasicConverter</code> will be associated to the name
 * <code>STRING_40</code>. <br>
 * A converter can implement both <code>AnnotationConverter</code> or
 * <code>BasicConverter</code> but can be used only either as an
 * <code>AnnotationConverter</code> or a <code>BasicConverter</code>. The
 * declared type of the constant is used to know is a converter is used as an
 * <code>AnnotationConverter</code> or a <code>BasicConverter</code>.
 * 
 * <pre>
 *       public static BasicConverter STRING_40 = new StringConverter(...
 * </pre>
 * 
 * If the constant is strong typed, an exception is thrown.
 * 
 * <br>
 * Converters associated to a java type must be explicitly declared in the
 * implementation constructor.
 * 
 * @author Olivier Mallassi
 * @since 1.3
 * 
 */
public abstract class ConverterMap
{
    /**
     * key: String <br>
     * value: a BasicConverter
     */
    private Map namedBasicConverter = Collections.synchronizedMap(new HashMap());

    /**
     * key: String <br>
     * value: an AnnotationConverter
     */
    private Map namedAnnotationConverter = Collections.synchronizedMap(new HashMap());

    /**
     * key: Class <br>
     * value: an AnnotationConverter
     */
    private Map typedAnnotationConverter = Collections.synchronizedMap(new HashMap());

    /**
     * key: Class value: an BasicConverter
     */
    private Map typedBasicConverter = Collections.synchronizedMap(new HashMap());

    /**
     * Protected constructor. Parses all the constants defined on the
     * implementation and associates the constants name to the declared
     * converter.
     * 
     */
    protected ConverterMap()
    {
        super();
        //
        Class currentClass = this.getClass();
        Field[] fields = currentClass.getFields();
        Field currentField;

        for (int i = 0; i < fields.length; i++)
        {
            currentField = fields[i];

            Class fieldType = currentField.getType();

            if (BasicConverter.class.isAssignableFrom(fieldType)
                    && AnnotationConverter.class.isAssignableFrom(fieldType))
            {
                throw new InitException("ambiguous type for field <" + currentField.getName() + ">");
            }
            else if (BasicConverter.class.isAssignableFrom(fieldType))
            {
                try
                {
                    this.setNamedConverter(currentField.getName(), (BasicConverter) currentField.get(this));
                }
                catch (IllegalArgumentException e)
                {
                    throw new InitException(e);
                }
                catch (IllegalAccessException e)
                {
                    throw new InitException(e);
                }
            }
            else if (AnnotationConverter.class.isAssignableFrom(fieldType))
            {
                try
                {
                    this.setNamedConverter(currentField.getName(), (AnnotationConverter) currentField.get(this));
                }
                catch (IllegalArgumentException e)
                {
                    throw new InitException(e);
                }
                catch (IllegalAccessException e)
                {
                    throw new InitException(e);
                }
            }
            else
            {
                throw new InitException("unknown type for field <" + currentField.getName() + ">");
            }
        }
    }

    /**
     * Sets a converter for a given name.
     * 
     * @param name
     *            the logical name
     * @param converter
     *            the converter instance that must be associated to the logical
     *            name
     * @throws InitException
     *             if a converter has already been setted with this name
     */
    protected void setNamedConverter(String name, BasicConverter converter) throws InitException
    {
        // if a basic converter has already been setted with the same name
        if (namedBasicConverter.get(name) != null)
        {
            throw new InitException("a converter has already been setted for the given name: <" + name + ">");
        }

        // if an annotation converter has already been setted with the same name
        if (namedAnnotationConverter.get(name) != null)
        {
            throw new InitException("a converter has already been setted for the given name: <" + name + ">");
        }

        //
        namedBasicConverter.put(name, converter);
    }

    /**
     * Returns a <code>BasicConverter</code> for a given name.
     * 
     * @param name
     *            the logical name
     * @return the basicConverter or <code>null</code>
     */
    public BasicConverter getBasicConverter(String name) throws InitException
    {
        return (BasicConverter) namedBasicConverter.get(name);
    }

    /**
     * Sets a converter for a given name.
     * 
     * @param name
     *            a logical name
     * @param converter
     *            the converter to associate to the specified <code>name</code>
     * @throws InitException
     *             if a converter has already been setted with this name
     */
    protected void setNamedConverter(String name, AnnotationConverter converter) throws InitException
    {
        if (namedAnnotationConverter.get(name) != null)
        {
            throw new InitException("a converter has already been setted for the given name: <" + name + ">");
        }

        if (namedBasicConverter.get(name) != null)
        {
            throw new InitException("a converter has already been setted for the given name: <" + name + ">");
        }

        namedAnnotationConverter.put(name, converter);
    }

    /**
     * Returns a converter for a specified <code>name</code>.
     * 
     * @param name
     *            the logical name
     * @return the converter or <code>null</code> if none have been setted for
     *         the specified <code>name</code>
     */
    public AnnotationConverter getAnnotatedConverter(String name) throws InitException
    {
        return (AnnotationConverter) namedAnnotationConverter.get(name);
    }

    /**
     * Sets a converter for a specified java type.
     * 
     * @param type
     *            the java type
     * @param converter
     *            the converter
     */
    protected void setDefaultAnnotationBasedConverter(Class type, AnnotationConverter converter)
    {
        if (typedAnnotationConverter.get(type) != null)
        {
            throw new InitException("a converter has already been setted for the given type: <" + type + ">");
        }

        if (typedBasicConverter.get(type) != null)
        {
            throw new InitException("a converter has already been setted for the given type: <" + type + ">");
        }

        typedAnnotationConverter.put(type, converter);
    }

    /**
     * Returns a converter for a given type.
     * 
     * @param type
     *            the java type
     * @return the converter associated to the specified <code>type</code>
     */
    public AnnotationConverter getTypedAnnotationConverter(Class type)
    {
        AnnotationConverter bc = (AnnotationConverter) getConverter(typedAnnotationConverter, type);

        if (bc == null)
        {
            throw new InitException("no converter has been setted for the given name: <" + type + ">");
        }

        return bc;
    }

    /**
     * 
     * 
     */
    private Object getConverter(Map map, Class type)
    {
        Object value = null;
        // if type is null, that can mean that during the recursive calls, the
        // previous call was
        // on type java.lang.Object and so the superClass is null.
        if (type != null)
        {
            value = map.get(type);

            if (value == null)
            {
                value = getConverter(map, type.getSuperclass());
            }
        }

        return value;
    }

    /**
     * Returns a converter for specified <code>type</code>.
     * 
     * @param type
     *            the specified type
     * @return the converter associated to the type
     */
    public BasicConverter getTypedBasicConverter(Class type)
    {
        BasicConverter bc = (BasicConverter) getConverter(typedBasicConverter, type);

        if (bc == null)
        {
            throw new InitException("no converter has been setted for the given name: <" + type + ">");
        }

        return bc;
    }

    /**
     * Sets a converter for the specified <code>type</code>.
     * 
     * @param type
     *            the java type
     * @param converter
     *            the converter associated to the java type
     */
    protected void setDefaultBasicConverter(Class type, BasicConverter converter)
    {
        if (typedBasicConverter.get(type) != null)
        {
            throw new InitException("a converter has already been setted for the given type: <" + type + ">");
        }

        if (typedAnnotationConverter.get(type) != null)
        {
            throw new InitException("a converter has already been setted for the given type: <" + type + ">");
        }

        typedBasicConverter.put(type, converter);
    }

}