package org.azolla.p.james.validater;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public interface Validater
{
    public Boolean validate(String s);

    default Validater setFactor(String s)
    {
        return this;
    }

    public Validater new0();
}
