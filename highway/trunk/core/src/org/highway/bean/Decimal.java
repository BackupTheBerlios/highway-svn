/*
 * Copyright (c) 2005. All rights reserved.
 */

package org.highway.bean;

import org.highway.exception.TechnicalException;
import org.highway.helper.MathHelper;
import org.highway.helper.StringHelper;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * A Decimal object represents an exact decimal number.<br>
 * Decimal must be used instead of float or double values when exact
 * calculations must be done. Decimal is for example used to represent money
 * values.<br>
 * 
 * @since 1.1
 * 
 */
public final class Decimal extends Number implements Comparable, Cloneable
{
    /**
     * Equals 0.
     */
    public static final Decimal ZERO = new Decimal(0, 0);

    /**
     * Equals 1.
     */
    public static final Decimal ONE = new Decimal(1, 0);

    /**
     * Equals -1.
     */
    public static final Decimal MINUS_ONE = new Decimal(-1, 0);

    /**
     * Equals 10.
     */
    public static final Decimal TEN = new Decimal(10, 0);

    /**
     * Equals 100.
     */
    public static final Decimal HUNDRED = new Decimal(100, 0);

    /**
     * Equals 0.1.
     */
    public static final Decimal DISIEME = new Decimal(1, 1);

    /**
     * Equals 0.01.
     */
    public static final Decimal CENTIEME = new Decimal(1, 2);

    // Rounding Modes

    /**
     * Rounding mode to round away from zero. Always increments the digit prior
     * to a non-zero discarded fraction. Note that this rounding mode never
     * decreases the magnitude of the calculated value.
     */
    public static final int ROUND_UP = 0;

    /**
     * Rounding mode to round towards zero. Never increments the digit prior to
     * a discarded fraction (i.e., truncates). Note that this rounding mode
     * never increases the magnitude of the calculated value.
     */
    public static final int ROUND_DOWN = 1;

    /**
     * Rounding mode to round towards positive infinity. If the Decimal is
     * positive, behaves as for <tt>ROUND_UP</tt>; if negative, behaves as
     * for <tt>ROUND_DOWN</tt>. Note that this rounding mode never decreases
     * the calculated value.
     */
    public static final int ROUND_CEILING = 2;

    /**
     * Rounding mode to round towards negative infinity. If the Decimal is
     * positive, behave as for <tt>ROUND_DOWN</tt>; if negative, behave as
     * for <tt>ROUND_UP</tt>. Note that this rounding mode never increases
     * the calculated value.
     */
    public static final int ROUND_FLOOR = 3;

    /**
     * Rounding mode to round towards "nearest neighbor" unless both neighbors
     * are equidistant, in which case round up.<br>
     * Behaves as for <tt>ROUND_UP</tt> if the discarded fraction is &gt;= .5;
     * otherwise, behaves as for <tt>ROUND_DOWN</tt>. Note that this is the
     * rounding mode that most of us were taught in grade school.
     */
    public static final int ROUND_HALF_UP = 4;

    /**
     * Rounding mode to round towards "nearest neighbor" unless both neighbors
     * are equidistant, in which case round down.<br>
     * Behaves as for <tt>ROUND_UP</tt> if the discarded fraction is &gt; .5;
     * otherwise, behaves as for <tt>ROUND_DOWN</tt>.
     */
    public static final int ROUND_HALF_DOWN = 5;

    /**
     * Rounding mode to round towards the "nearest neighbor" unless both
     * neighbors are equidistant, in which case, round towards the even
     * neighbor. Behaves as for ROUND_HALF_UP if the digit to the left of the
     * discarded fraction is odd; behaves as for ROUND_HALF_DOWN if it's even.<br>
     * Note that this is the rounding mode that minimizes cumulative error when
     * applied repeatedly over a sequence of calculations.
     */
    public static final int ROUND_HALF_EVEN = 6;

    /**
     * Rounding mode to assert that the requested operation has an exact result,
     * hence no rounding is necessary. If this rounding mode is specified on an
     * operation that yields an inexact result, an <tt>ArithmeticException</tt>
     * is thrown.
     */
    public static final int ROUND_UNNECESSARY = 7;

    /**
     * The unscale of this Decimal, as returned by unscaleValue().
     * 
     * @serial
     */
    private long m_unscaledValue;

    /**
     * The scale of this Decimal, as returned by scaleValue().
     * 
     * @serial
     */
    private int m_scale = 0;

    // //////////////////////
    // /// Constructors /////
    // //////////////////////

    /**
     * Constructs a Decimal equal to the specified unscaledValue divided by the
     * specified scale power 100.<br>
     * <br>
     * The scale is in fact the position of the dot from the right side.<br>
     * <br>
     * Example :<br>
     * 
     * <pre>
     *                   new Decimal(123456, 0) &lt;==&gt; 123456
     *                   new Decimal(123456, 2) &lt;==&gt; 1234.56
     *                   new Decimal(123456, 8) &lt;==&gt; 0.00123456
     * </pre>
     * 
     * @param unscaledValue
     *            the decimal unscaled value
     * @param scale
     *            the scale (0 to what ever)
     * @throws IllegalArgumentException
     *             if the scale is negative
     */
    public Decimal(long unscaledValue, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("negative scale");
        }

        m_unscaledValue = unscaledValue;
        m_scale = scale;
        trimZeros();
    }

    /**
     * Constructs a Decimal equal to the specified String value.
     * 
     * @param value
     *            the decimal value
     * @throws IllegalArgumentException
     *             if the value is null or empty
     * @throws NumberFormatException
     *             if the value is not a number
     */
    public Decimal(String value)
    {
        value = StringHelper.removeAllSpaces(value);

        if ((value == null) || (value.length() == 0))
        {
            throw new IllegalArgumentException("String argument null or empty");
        }

        value = value.replace('.', ',');

        if ((value.charAt(0) == '-') && (value.length() == 1))
        {
            m_scale = 0;
            m_unscaledValue = 0;
        }
        else
        {
            // Deal with leading plus sign if present
            if (value.charAt(0) == '+')
            {
                // "+" illegal!
                // "+-123.456" illegal!
                if ((value.length() == 1) || (value.charAt(1) == '-'))
                {
                    throw new NumberFormatException(value + " is not a number");
                }

                // discard leading '+'
                value = value.substring(1);
            }

            int pointIndex = value.indexOf(',');

            // e.g. "123"
            if ((pointIndex != -1) && (value.length() == 1))
            {
                m_scale = 0;
                m_unscaledValue = 0;
            }
            else if (pointIndex == -1)
            {
                m_unscaledValue = Long.parseLong(value);
            }
            else if (value.length() == 1)
            {
                m_scale = 0;
                m_unscaledValue = 0;
            }
            else
            {
                String integerValue = value.substring(0, pointIndex);
                String decimalValue = value.substring(pointIndex + 1);
                m_unscaledValue = Long.parseLong(integerValue + decimalValue);
                m_scale = value.length() - pointIndex - 1;
            }
        }

        trimZeros();
    }

    /**
     * Called by constructors to remove the unnecessary zeros.<br>
     * 
     * <pre>
     *               new Decimal(&quot;10.0100&quot;) =&gt; 10.01
     *               new Decimal(1010, 3) =&gt; 1.01
     * </pre>
     * 
     */
    private void trimZeros()
    {
        while ((m_scale > 0) && ((m_unscaledValue % 10) == 0))
        {
            --m_scale;
            m_unscaledValue = m_unscaledValue / 10;
        }
    }

    // //////////////////////////////////
    // //// Static Factory Methods //////
    // //////////////////////////////////

    /**
     * Translates a long unscaled value and an int m_scale into a Decimal.<br>
     * This "static factory method" is provided in preference to a (long, int)
     * constructor because it allows for reuse of frequently used BigDecimals.
     * 
     * @param unscaledValue
     *            unscaled value of the Decimal.
     * @param scale
     *            m_scale of the Decimal.
     * @return a Decimal whose value is
     *         <tt>(unscaledVal/10<sup>m_scale</sup>)</tt>.
     */
    public static Decimal valueOf(long unscaledValue, int scale)
    {
        return new Decimal(unscaledValue, scale);
    }

    /**
     * Method valueOf
     * 
     * @param value
     *            long
     * @return Decimal
     */
    public static Decimal valueOf(long value)
    {
        return new Decimal(value, 0);
    }

    /**
     * Method valueOf
     * 
     * @param value
     *            String
     * @return Decimal
     */
    public static Decimal valueOf(String value)
    {
        return new Decimal(value);
    }

    /**
     * Method valueOf
     * 
     * @param value
     *            BigInteger
     * @return Decimal
     */
    public static Decimal valueOf(BigInteger value)
    {
        // 63 because bitLength excludes the signbit
        if (value.bitLength() > 63)
        {
            throw new TechnicalException("BigInteger too big to fit into a Decimal");
        }

        return new Decimal(value.longValue(), 0);
    }

    /**
     * Method valueOf
     * 
     * @param value
     *            BigDecimal
     * @return Decimal
     */
    public static Decimal valueOf(BigDecimal value)
    {
        // 63 because bitLength excludes the signbit
        if (value.unscaledValue().bitLength() > 63)
        {
            throw new TechnicalException("BigDecimal too big to fit into a Decimal");
        }

        return new Decimal(value.unscaledValue().longValue(), value.scale());
    }

    // Arithmetic Operations

    /**
     * Returns a Decimal whose value is <tt>(this - value)</tt>, and whose
     * m_scale is <tt>max(this.m_scale(), value.m_scale())</tt>.
     * 
     * @param sd
     *            value to be subtracted from this Decimal.
     * @return <tt>this - value</tt>
     */
    public Decimal subtract(Decimal sd)
    {
        if (sd == null)
        {
            return this;
        }

        if (m_scale < sd.m_scale)
        {
            int expo = sd.m_scale - m_scale;
            long unscale = m_unscaledValue * (long) Math.pow(10, expo);

            return new Decimal(unscale - sd.m_unscaledValue, sd.m_scale);
        }
        else if (m_scale > sd.m_scale)
        {
            int expo = m_scale - sd.m_scale;
            long unscale = sd.m_unscaledValue * (long) Math.pow(10, expo);

            return new Decimal(m_unscaledValue - unscale, m_scale);
        }
        else
        {
            return new Decimal(m_unscaledValue - sd.m_unscaledValue, m_scale);
        }
    }

    /**
     * Returns a Decimal whose value is <tt>(this - value)</tt>, and whose
     * m_scale is <tt>max(this.m_scale(), value.m_scale())</tt>.
     * 
     * @param sd
     *            value to be subtracted from this Decimal.
     * @return <tt>this - value</tt>
     */
    public Decimal add(Decimal sd)
    {
        if (sd == null)
        {
            return this;
        }

        if (m_scale < sd.m_scale)
        {
            int expo = sd.m_scale - m_scale;
            long unscale = m_unscaledValue * (long) Math.pow(10, expo);

            return new Decimal(unscale + sd.m_unscaledValue, sd.m_scale);
        }
        else if (m_scale > sd.m_scale)
        {
            int expo = m_scale - sd.m_scale;
            long unscale = sd.m_unscaledValue * (long) Math.pow(10, expo);

            return new Decimal(m_unscaledValue + unscale, m_scale);
        }
        else
        {
            return new Decimal(m_unscaledValue + sd.m_unscaledValue, m_scale);
        }
    }

    /**
     * Returns a Decimal whose value is <tt>(this * value)</tt>, and whose
     * m_scale is <tt>(this.m_scale() + value.m_scale())</tt>.
     * 
     * @param value
     *            value to be multiplied by this Decimal.
     * @return <tt>this * value</tt>
     */
    public Decimal multiply(Decimal value)
    {
        return new Decimal(m_unscaledValue * value.m_unscaledValue, m_scale + value.m_scale);
    }

    // public Decimal divide(Decimal value)
    // {
    // return Decimal.valueOf(doubleValue() / value.doubleValue());
    // }
    //    

    /**
     * Returns a Decimal whose value is <tt>(this / value)</tt>, and whose
     * m_scale is as specified. If rounding must be performed to generate a
     * result with the specified m_scale, the specified rounding mode is
     * applied.
     * 
     * @return <tt>this / value</tt>
     * @throws ArithmeticException
     *             <tt>value</tt> is zero, <tt>m_scale</tt> is negative, or
     *             <tt>roundingMode==ROUND_UNNECESSARY</tt> and the specified
     *             m_scale is insufficient to represent the result of the
     *             division exactly.
     * @throws IllegalArgumentException
     *             <tt>roundingMode</tt> does not represent a valid rounding
     *             mode.
     * @see #ROUND_UP
     * @see #ROUND_DOWN
     * @see #ROUND_CEILING
     * @see #ROUND_FLOOR
     * @see #ROUND_HALF_UP
     * @see #ROUND_HALF_DOWN
     * @see #ROUND_HALF_EVEN
     * @see #ROUND_UNNECESSARY
     */

    // public Decimal divide(Decimal value, int scale, int roundingMode)
    // {
    // if (m_scale < 0)
    // {
    // throw new ArithmeticException("Negative m_scale");
    // }
    // if (roundingMode < ROUND_UP || roundingMode > ROUND_UNNECESSARY)
    // {
    // throw new IllegalArgumentException("Invalid rounding mode");
    // }
    //
    // /*
    // * Rescale dividend or divisor (whichever can be "upscaled" to
    // * produce correctly scaled quotient).
    // */
    // Decimal dividend;
    //
    // /*
    // * Rescale dividend or divisor (whichever can be "upscaled" to
    // * produce correctly scaled quotient).
    // */
    // Decimal divisor;
    // if (m_scale + value.m_scale >= this.m_scale)
    // {
    // dividend = this.setScale(m_scale + value.m_scale);
    // divisor = value;
    // }
    // else
    // {
    // dividend = this;
    // divisor = value.setScale(this.m_scale - m_scale);
    // }
    //
    // /* Do the division and return result if it's exact */
    // BigInteger[] i = dividend.intVal.divideAndRemainder(divisor.intVal);
    // BigInteger q = i[0];
    // BigInteger r = i[1];
    // if (r.signum() == 0)
    // {
    // return new Decimal(q, m_scale);
    // }
    // else if (roundingMode == ROUND_UNNECESSARY) /* Rounding prohibited */
    // {
    // throw new ArithmeticException("Rounding necessary");
    // }
    //
    // /* Round as appropriate */
    // int signum = dividend.signum() * divisor.signum(); /* Sign of result */
    // boolean increment;
    // if (roundingMode == ROUND_UP)
    // { /* Away from zero */
    // increment = true;
    // }
    // else if (roundingMode == ROUND_DOWN)
    // { /* Towards zero */
    // increment = false;
    // }
    // else if (roundingMode == ROUND_CEILING)
    // { /* Towards +infinity */
    // increment = (signum > 0);
    // }
    // else if (roundingMode == ROUND_FLOOR)
    // { /* Towards -infinity */
    // increment = (signum < 0);
    // }
    // else
    // { /* Remaining modes based on nearest-neighbor determination */
    // int cmpFracHalf =
    // r.abs().multiply(BigInteger.valueOf(2)).compareTo(divisor.intVal.abs());
    // if (cmpFracHalf < 0)
    // { /* We're closer to higher digit */
    // increment = false;
    // }
    // else if (cmpFracHalf > 0)
    // { /* We're closer to lower digit */
    // increment = true;
    // }
    // else
    // { /* We're dead-center */
    // if (roundingMode == ROUND_HALF_UP)
    // {
    // increment = true;
    // }
    // else if (roundingMode == ROUND_HALF_DOWN)
    // {
    // increment = false;
    // }
    // else{ /* roundingMode == ROUND_HALF_EVEN */
    // increment = q.testBit(0); /* true iff q is odd */
    // }
    // }
    // }
    // return (increment ? new Decimal(q.add(BigInteger.valueOf(signum)),
    // m_scale) : new Decimal(q, m_scale));
    // return null;
    // }
    /**
     * Returns a Decimal whose value is <tt>(this / value)</tt>, and whose
     * m_scale is <tt>this.m_scale()</tt>. If rounding must be performed to
     * generate a result with the given m_scale, the specified rounding mode is
     * applied.
     * 
     * @param value
     *            value by which this Decimal is to be divided.
     * @param roundingMode
     *            rounding mode to apply.
     * @return <tt>this / value</tt>
     * @throws ArithmeticException
     *             <tt>value==0</tt>, or
     *             <tt>roundingMode==ROUND_UNNECESSARY</tt> and
     *             <tt>this.m_scale()</tt> is insufficient to represent the
     *             result of the division exactly.
     * @throws IllegalArgumentException
     *             <tt>roundingMode</tt> does not represent a valid rounding
     *             mode.
     * @see #ROUND_UP
     * @see #ROUND_DOWN
     * @see #ROUND_CEILING
     * @see #ROUND_FLOOR
     * @see #ROUND_HALF_UP
     * @see #ROUND_HALF_DOWN
     * @see #ROUND_HALF_EVEN
     * @see #ROUND_UNNECESSARY
     */

    // public Decimal divide(Decimal value, int roundingMode)
    // {
    // // return this.divide(value, m_scale, roundingMode);
    // return null;
    // }
    /**
     * Returns a Decimal whose value is the absolute value of this Decimal, and
     * whose m_scale is <tt>this.m_scale()</tt>.
     * 
     * @return <tt>abs(this)</tt>
     */
    public Decimal abs()
    {
        return isNegative() ? negate() : this;
    }

    /**
     * Returns a Decimal whose value is <tt>(-this)</tt>, and whose scale is
     * <tt>this.scale()</tt>.
     * 
     * @return <tt>-this</tt>
     */
    public Decimal negate()
    {
        return new Decimal(-m_unscaledValue, m_scale);
    }

    /**
     * Returns true if the number is negative.
     * 
     * @return boolean
     */
    public boolean isNegative()
    {
        return m_unscaledValue < 0;
    }

    /**
     * Returns the <i>m_scale</i> of this Decimal. (The m_scale is the number
     * of digits to the right of the decimal point.)
     * 
     * @return the m_scale of this Decimal.
     */
    public int scale()
    {
        return m_scale;
    }

    /**
     * Returns a BigInteger whose value is the <i>unscaled value</i> of this
     * Decimal. (Computes <tt>(this * 10<sup>this.m_scale()</sup>)</tt>.)
     * 
     * @return the unscaled value of this Decimal.
     * @since 1.2
     */
    public long unscaledValue()
    {
        return m_unscaledValue;
    }

    // Comparison Operations

    /**
     * Compares this Decimal with the specified Object. If the Object is a
     * Decimal, this method behaves like {@link #compareTo}.<br>
     * Otherwise, it throws a <tt>ClassCastException</tt> (as BigDecimals are
     * comparable only to other BigDecimals).
     * 
     * @param o
     *            Object to which this Decimal is to be compared.
     * @return a negative number, zero, or a positive number as this Decimal is
     *         numerically less than, equal to, or greater than <tt>o</tt>,
     *         which must be a Decimal.
     * @throws ClassCastException
     *             <tt>o</tt> is not a Decimal.
     * @see #compareTo(java.math.Decimal)
     * @see Comparable
     * @since 1.2
     */
    public int compareTo(Object o)
    {
        return compareTo((Decimal) o);
    }

    /**
     * Method compareTo
     * 
     * @param sd
     *            Decimal
     * @return int
     */
    public int compareTo(Decimal sd)
    {
        sd = zeroIfNull(sd);

        int expo = Math.abs(m_scale - sd.m_scale);

        if (m_scale < sd.m_scale)
        {
            long unscaled = m_unscaledValue * MathHelper.pow(10, expo);

            return MathHelper.compare(unscaled, sd.m_unscaledValue);
        }
        else if (m_scale > sd.m_scale)
        {
            long unscaled = sd.m_unscaledValue * MathHelper.pow(10, expo);

            return MathHelper.compare(m_unscaledValue, unscaled);
        }
        else
        {
            return MathHelper.compare(m_unscaledValue, sd.m_unscaledValue);
        }
    }

    /**
     * Method superior
     * 
     * @param Decimal
     *            Decimal
     * @return boolean
     */
    public boolean superior(Decimal Decimal)
    {
        return compareTo(Decimal) > 0;
    }

    /**
     * Method inferior
     * 
     * @param Decimal
     *            Decimal
     * @return boolean
     */
    public boolean inferior(Decimal Decimal)
    {
        return compareTo(Decimal) < 0;
    }

    /**
     * Compares this Decimal with the specified Object for equality. Unlike
     * {@link #compareTo}, this method considers two BigDecimals equal only if
     * they are equal in value and m_scale (thus 2.0 is not equal to 2.00 when
     * compared by this method).
     * 
     * @param obj
     *            Object to which this Decimal is to be compared.
     * @return <tt>true</tt> if and only if the specified Object is a Decimal
     *         whose value and m_scale are equal to this Decimal's.
     * @see #compareTo(java.math.Decimal)
     */
    public boolean equals(Object obj)
    {
        if (obj != null)
        {
            if (obj instanceof Decimal)
            {
                return compareTo((Decimal) obj) == 0;
            }
        }

        return false;
    }

    /**
     * Returns the minimum of this Decimal and <tt>value</tt>.
     * 
     * @param value
     *            value with with the minimum is to be computed.
     * @return the Decimal whose value is the lesser of this Decimal and
     *         <tt>value</tt>. If they are equal, as defined by the
     *         {@link #compareTo} method, either may be returned.
     * @see #compareTo(java.math.Decimal)
     */
    public Decimal min(Decimal value)
    {
        return ((compareTo(value) < 0) ? this : value);
    }

    /**
     * Returns the maximum of this Decimal and <tt>value</tt>.
     * 
     * @param value
     *            value with with the maximum is to be computed.
     * @return the Decimal whose value is the greater of this Decimal and
     *         <tt>value</tt>. If they are equal, as defined by the
     *         {@link #compareTo} method, either may be returned.
     * @see #compareTo(java.math.Decimal)
     */
    public Decimal max(Decimal value)
    {
        return ((compareTo(value) > 0) ? this : value);
    }

    // Hash Function

    /**
     * Returns the hash code for this Decimal. Note that two BigDecimals that
     * are numerically equal but differ in m_scale (like 2.0 and 2.00) will
     * generally <i>not</i> have the same hash code.
     * 
     * @return hash code for this Decimal.
     */
    public int hashCode()
    {
        return (int) (m_unscaledValue ^ (m_unscaledValue >>> 32));
    }

    // Format Converters

    /**
     * Returns the string representation of this Decimal. The digit-to-
     * character mapping provided by {@link Character#forDigit} is used.<br>
     * A leading minus sign is used to indicate sign, and the number of digits
     * to the right of the decimal point is used to indicate m_scale. (This
     * representation is compatible with the (String) constructor.)
     * 
     * @return String representation of this Decimal.
     * @see Character#forDigit
     * @see #Decimal(java.lang.String)
     */
    public String toString()
    {
        StringBuffer buffer = new StringBuffer(Long.toString(Math.abs(m_unscaledValue)));

        if (m_scale > 0)
        {
            int pointIndex = buffer.length() - m_scale;

            for (; pointIndex < 0; pointIndex++)
            {
                buffer.insert(0, 0);
            }

            if (pointIndex == 0)
            {
                buffer.insert(pointIndex, "0.");
            }
            else
            {
                buffer.insert(pointIndex, ".");
            }
        }

        if (isNegative())
        {
            buffer.insert(0, "-");
        }

        return buffer.toString();
    }

    // public String format2(int digit)
    // {
    // long pow =(long)StrictMath.pow(10, m_scale);
    // long ent = (long)(m_unscaledValue / pow);
    // long dec = Math.abs(m_unscaledValue) % pow;
    //
    // StringBuffer buffer = new StringBuffer();
    // buffer.append(s_numberFormat.format(ent)).append(',').append(dec);
    //
    // long n = digit - String.valueOf(dec).length();
    // if(n == 1){
    // buffer.append('0');
    // }
    // else if(n == 2){
    // buffer.append("00");
    // }
    // else{
    // while(n-- > 0){
    // buffer.append("0");
    // }
    // }
    // return buffer.toString();
    // }

    /**
     * Method format
     * 
     * @param digit
     *            int
     * @return String
     */
    public String format(int digit)
    {
        String tmp = toString().replace('.', ',');
        int length = tmp.length();
        StringBuffer buffer = new StringBuffer(tmp);
        int index = tmp.indexOf(",");

        if ((index == -1) && (digit == 0))
        {
            return tmp;
        }
        else if (index == -1)
        {
            buffer.append(",");
            index = length++;
        }

        // else if (digit == 0)
        // {
        // return tmp.substring(0, index);
        // }
        tmp = buffer.toString();
        index = tmp.indexOf(',');

        while ((length++ - index - 1) < digit)
        {
            buffer.append("0");
        }

        StringTokenizer tokens = new StringTokenizer(buffer.toString(), ",");
        buffer = new StringBuffer(tokens.nextToken());
        buffer.reverse();

        int n = (int) (index / 3);

        if ((index % 3) == 0)
        {
            n--;
        }

        for (; n > 0; n--)
        {
            buffer.insert(n * 3, " ");
        }

        String decimal = tokens.nextToken();

        while (decimal.endsWith("0") && (decimal.length() > digit))
        {
            decimal = decimal.substring(0, decimal.length() - 1);
        }

        return buffer.reverse().append(",").append(decimal).toString();
    }

    /**
     * Converts this Decimal to an int. This is standard <i>narrowing primitive
     * conversion</i> as defined in <i>The Java Language Specification</i>:
     * any fractional part of this Decimal will be discarded, and if the
     * resulting "BigInteger" is too big to fit in an int, only the low-order 32
     * bits are returned.
     * 
     * @return this Decimal converted to an int.
     */
    public long longValue()
    {
        if (m_scale == 0)
        {
            return m_unscaledValue;
        }
        else
        {
            return m_unscaledValue / (long) Math.pow(10, m_scale);
        }
    }

    /**
     * Converts this Decimal to a long. This is standard <i>narrowing primitive
     * conversion</i> as defined in <i>The Java Language Specification</i>:
     * any fractional part of this Decimal will be discarded, and if the
     * resulting "BigInteger" is too big to fit in a long, only the low-order 64
     * bits are returned.
     * 
     * @return this Decimal converted to an int.
     */
    public int intValue()
    {
        return (int) longValue();
    }

    /**
     * Converts this Decimal to a float. Similar to the double-to-float
     * <i>narrowing primitive conversion</i> defined in <i>The Java Language
     * Specification</i>: if this Decimal has too great a magnitude to
     * represent as a float, it will be converted to
     * <tt>Float.NEGATIVE_INFINITY</tt> or <tt>Float.POSITIVE_INFINITY</tt>
     * as appropriate.
     * 
     * @return this Decimal converted to a float.
     */
    public float floatValue()
    {
        return (float) doubleValue();
    }

    /**
     * Converts this Decimal to a double. Similar to the double-to-float
     * <i>narrowing primitive conversion</i> defined in <i>The Java Language
     * Specification</i>: if this Decimal has too great a magnitude to
     * represent as a double, it will be converted to
     * <tt>Double.NEGATIVE_INFINITY</tt> or <tt>Double.POSITIVE_INFINITY</tt>
     * as appropriate.
     * 
     * @return this Decimal converted to a double.
     * @deprecated ne jamais utiliser cette méthode
     */
    public double doubleValue()
    {
        return m_unscaledValue * Math.pow(10, -m_scale);
    }

    /**
     * Method clone
     * 
     * @return Object
     */
    public Object clone()
    {
        try
        {
            return super.clone();
        }
        catch (CloneNotSupportedException cnse)
        {
            throw new TechnicalException(cnse);
        }
    }

    // Scaling/Rounding Operations

    /**
     * Method floor
     * 
     * @param digit
     *            int
     * @return Decimal
     */
    public Decimal floor(int digit)
    {
        if (m_scale > digit)
        {
            int expo = m_scale - digit;

            return new Decimal((int) Math.floor(m_unscaledValue / Math.pow(10, expo)), digit);
        }

        return this;
    }

    /**
     * Method ceil
     * 
     * @param digit
     *            int
     * @return Decimal
     */
    public Decimal ceil(int digit)
    {
        if (m_scale > digit)
        {
            int expo = m_scale - digit;

            return new Decimal((int) Math.ceil(m_unscaledValue / Math.pow(10, expo)), digit);
        }

        return this;
    }

    /**
     * Method round
     * 
     * @param digit
     *            int
     * @return Decimal
     */
    public Decimal round(int digit)
    {
        if (m_scale > digit)
        {
            int expo = m_scale - digit;

            return new Decimal(Math.round(m_unscaledValue / Math.pow(10, expo)), digit);
        }

        return this;
    }

    /**
     * @param value
     *            Decimal
     * @return Decimal
     */
    public Decimal divide(Decimal value)
    {
        BigDecimal b1 = new BigDecimal(toString());
        BigDecimal b2 = new BigDecimal(value.toString());
        BigDecimal result = b1.divide(b2, 10, BigDecimal.ROUND_HALF_EVEN);

        return new Decimal(result.unscaledValue().longValue(), result.scale());
    }

    /**
     * Method main
     * 
     * @param args
     *            String[]
     */
    public static void main(String[] args)
    {
        if (true)
        {
            Decimal sd = Decimal.valueOf(1960, 2);

            System.out.println(sd.multiply(new Decimal(1, 2)));
            System.out.println(sd.divide(new Decimal(10000, 2)));

            // System.out.println(new
            // DecimalFormat("##############.######").format(1356));
            // System.out.println(new
            // Decimal(1234567891245L,3).round(2).format(2));
            // System.out.println(new Decimal(9,2).round(1).format(1));
            // stem.out.println(superTrim(" 1 2 3 4 "));
            return;
        }

        for (int i = 0; i < 100000; i++)
        {
            int scale1 = (int) (Math.random() * 5);
            int scale2 = (int) (Math.random() * 5);

            long unscale1 = (long) ((Math.random() * Math.sqrt(Long.MAX_VALUE)) / Math.pow(10, scale1));
            long unscale2 = (long) ((Math.random() * Math.sqrt(Long.MAX_VALUE)) / Math.pow(10, scale1));

            if (Math.random() > 0.5)
            {
                unscale1 = -unscale1;
            }

            if (Math.random() > 0.5)
            {
                unscale2 = -unscale2;
            }

            Decimal sd1 = new Decimal(unscale1, scale1);
            Decimal sd2 = new Decimal(unscale2, scale2);

            if ((i % 1000) == 0)
            {
                System.out.println(i);
            }

            // System.out.println(sd1);
            // System.out.println(sd2);
            BigDecimal bd1 = new BigDecimal(BigInteger.valueOf(unscale1), scale1);
            BigDecimal bd2 = new BigDecimal(BigInteger.valueOf(unscale2), scale2);

            try
            {
                Decimal sd = new Decimal(1114, 3);
                sd.floor(2);
                System.out.println(sd.floor(2));
                System.out.println(sd.ceil(2));
                System.out.println(sd.round(2));

                return;
            }
            catch (Exception e)
            {
                sd1.toString();
            }

            // toString
            sd1.toString();
            sd1.format((int) (Math.random() * 10));

            // doubleValue
            if ((sd1.doubleValue() - bd1.doubleValue()) > 0.000005)
            {
                throw new RuntimeException("Pb in doubleValue");
            }

            // compareTo
            if (sd1.compareTo(sd2) != bd1.compareTo(bd2))
            {
                throw new RuntimeException("Pb in compareTo");
            }

            if (sd1.compareTo(sd1) != 0)
            {
                throw new RuntimeException("Pb in compareTo =");
            }

            // add
            if ((sd1.add(sd2).doubleValue() - bd1.add(bd2).doubleValue()) > 0.000005)
            {
                throw new RuntimeException("Pb in add");
            }

            // sub
            if ((sd1.subtract(sd2).doubleValue() - bd1.subtract(bd2).doubleValue()) > 0.000005)
            {
                throw new RuntimeException("Pb in sub");
            }

            // mutliply
            if ((new Double(sd1.multiply(sd2).toString()).doubleValue() - new Double(bd1.multiply(bd2).toString())
                    .doubleValue()) > 0.000005)
            {
                throw new RuntimeException("Pb in multiply");
            }

            // divide
            // if((sd1.div(sd2).doubleValue() - bd1.multiply(bd2).doubleValue())
            // > 0.000005)
            // {
            // throw new RuntimeException("Pb in multiply");
            // }
        }

        // System.out.println(new Decimal(1,3).compareTo(new Decimal(1,3)));
        // System.out.println(new Decimal(10,3).compareTo(new Decimal(1,3)));
        // System.out.println(new Decimal(1,3).compareTo(new Decimal(10,3)));
        // System.out.println(new Decimal(1234,3).compareTo(new
        // Decimal(123400,5)));
        // System.out.println(new Decimal(123400,5).compareTo(new
        // Decimal(1234,3)));
        // System.out.println(new Decimal(1,3).subtract(new
        // Decimal(1,2)).doubleValue() - (0.001-0.01) < 0.0000001);
    }

    /**
     * Method zeroIfNull
     * 
     * @param decimal
     *            Decimal
     * @return Decimal
     */
    public static Decimal zeroIfNull(Decimal decimal)
    {
        return (decimal == null) ? Decimal.ZERO : decimal;
    }

    /**
     * Returns the integer part of the Decimal. For instance
     * 
     * <pre>
     *      1234,56 will return 1234.
     * </pre>
     */
    public long getIntegerPart()
    {
        String unscaleValueAsString = Long.toString(this.m_unscaledValue);
        String integerPartAsString = unscaleValueAsString.substring(0, unscaleValueAsString.length() - m_scale);

        return Long.parseLong(integerPartAsString);
    }

    /**
     * 
     * Returns the decimal part of the Decimal. For instance
     * 
     * <pre>
     *   1234,56 will return 56.
     * </pre>
     * 
     */
    public long getDecimalPart()
    {
        String unscaleValueAsString = Long.toString(this.m_unscaledValue);
        String integerPartAsString = unscaleValueAsString.substring(unscaleValueAsString.length() - m_scale,
                unscaleValueAsString.length());

        return Long.parseLong(integerPartAsString);
    }
}
