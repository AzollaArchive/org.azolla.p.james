package org.azolla.p.james.converter;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public interface Converter
{
    public Boolean convert(String[][] dataArray, Integer row, Integer col);

    public Converter new0();
}
